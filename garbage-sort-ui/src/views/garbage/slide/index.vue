<template>
  <div class="app-container">
    <!-- 查询条件开始 -->
    <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="88px">
      <el-form-item label="状态" prop="userId">
        <el-select
          v-model="queryParams.userId"
          placeholder="可用状态"
          clearable
          size="small"
          style="width:240px"
        >
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button type="primary" icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 查询条件结束 -->

    <!-- 表格工具按钮开始 -->
    <el-row :gutter="10" style="margin-bottom: 8px;">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除</el-button>
      </el-col>
    </el-row>
    <!-- 表格工具按钮结束 -->

    <!-- 数据表格开始 -->
    <el-table v-loading="loading" border :data="slideTableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="链接地址:">
              <a target="_blank" :href="props.row.skipUrl">{{ props.row.skipUrl }}</a>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="图片" align="center" prop="imageUrl">
        <template slot-scope="scope">
          <img class="logoImg" :src="scope.row.imageUrl">
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="userId" align="center" :formatter="statusFormatter" />
      <el-table-column label="排序" align="center" prop="sortId" />
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="text" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 数据表格结束 -->
    <!-- 分页控件开始 -->
    <el-pagination
      v-show="total>0"
      :current-page="queryParams.pageNum"
      :page-sizes="[5, 10, 20, 30]"
      :page-size="queryParams.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    <!-- 分页控件结束 -->

    <!-- 添加修改弹出层开始 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="600px"
      center
      append-to-body
    >
      <el-form ref="form" :model="form" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="状态" prop="userId">
              <el-radio-group v-model="form.userId">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                  :value="dict.dictValue"
                >{{ dict.dictLabel }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="排序" prop="sortId">
              <el-input v-model="form.sortId" placeholder="请输入序号" clearable size="small" />
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 上传组件 -->
        <el-row>
          <el-col :span="24">
            <el-form-item label="图片" prop="imageUrl">
              <el-upload
                :action="uploadPath"
                :headers="headers"
                :on-remove="handleRemove"
                :file-list="fileList"
                accept="image/*"
                name="file"
                :on-success="handleUploadSuccess"
                :on-error="handleUploadError"
                list-type="picture"
              >
                <el-button size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="链接地址" prop="skipUrl">
              <el-input v-model="form.skipUrl" placeholder="请输入跳转的链接地址" clearable size="small" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSubmit">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </span>
    </el-dialog>
    <!-- 添加修改弹出层结束 -->

  </div>
</template>
<script>

// 引入api
import { listSlideForPage, addSlide, updateSlide, getSlideById, deleteSlideByIds } from '@/api/garbage/slide'
import { getToken } from '@/utils/auth'
export default {
  // 定义页面数据
  data() {
    return {
      // 是否启用遮罩层
      loading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 分页数据总条数
      total: 0,
      // 字典表格数据
      slideTableList: [],
      // 弹出层标题
      title: '',
      url: '',
      // 是否显示弹出层
      open: false,
      // 状态数据字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        status: undefined
      },
      // 表单数据
      form: {
        imageUrl: undefined,
        sortId: '0',
        userId: '0',
        skipUrl: undefined
      },
      // 声明上传路径
      uploadPath: undefined,
      // 控件里面的文件列表
      fileList: [],
      // 头
      headers: undefined,
      // 文件列表的json对象
      fileListJsonObj: []
    }
  },
  // 勾子
  created() {
    // 使用全局的根据字典类型查询字典数据的方法查询字典数据
    this.getDataByType('sys_normal_disable').then(res => {
      this.statusOptions = res.data
    })
    // 查询表格数据
    this.getSlideList()
    // 文件上传的路径
    this.uploadPath = process.env.VUE_APP_BASE_API + '/upload/image'
    // 设置请求头加入token 避免求认证的问题
    this.headers = { 'token': getToken() }
  },
  // 方法
  methods: {
    // 查询表格数据
    getSlideList() {
      this.loading = true // 打开遮罩
      listSlideForPage(this.queryParams).then(res => {
        this.slideTableList = res.data
        this.total = res.total
        this.loading = false// 关闭遮罩
      })
    },
    // 条件查询
    handleQuery() {
      this.getSlideList()
    },
    // 重置查询条件
    resetQuery() {
      this.resetForm('queryForm')
      this.getSlideList()
    },
    // 数据表格的多选择框选择时触发
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.slide)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    // 分页pageSize变化时触发
    handleSizeChange(val) {
      this.queryParams.pageSize = val
      // 重新查询
      this.getSlideList()
    },
    // 点击上一页  下一页，跳转到哪一页面时触发
    handleCurrentChange(val) {
      this.queryParams.pageNum = val
      // 重新查询
      this.getSlideList()
    },
    // 翻译状态
    statusFormatter(row) {
      return this.selectDictLabel(this.statusOptions, row.userId)
    },
    // 打开添加的弹出层
    handleAdd() {
      this.open = true
      this.reset()
      this.title = '添加轮播图'
    },
    // 打开修改的弹出层
    handleUpdate(row) {
      this.title = '修改轮播图'
      const slide = row.slide || this.ids
      this.open = true
      this.reset()
      // 根据slide查询一个
      this.loading = true
      getSlideById(slide).then(res => {
        this.form = res.data
        this.form.userId = '' + res.data.userId
        this.fileList = [{ 'url': res.data.imageUrl }]
        this.loading = false
      })
    },
    // 执行删除
    handleDelete(row) {
      const slides = row.slide || this.ids
      this.$confirm('此操作将永久删除该轮播图, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loading = true
        deleteSlideByIds(slides).then(res => {
          this.loading = false
          this.msgSuccess('删除成功')
          this.getSlideList()// 全查询
        }).catch(() => {
          this.msgError('删除失败')
          this.loading = false
        })
      }).catch(() => {
        this.msgError('删除已取消')
        this.loading = false
      })
    },
    // 保存
    handleSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          // 做添加
          this.loading = true
          if (this.form.slide === undefined) {
            addSlide(this.form).then(res => {
              this.msgSuccess('保存成功')
              this.loading = false
              this.getSlideList()// 列表重新查询
              this.open = false// 关闭弹出层
            }).catch(() => {
              this.loading = false
            })
          } else { // 做修改
            updateSlide(this.form).then(res => {
              this.msgSuccess('修改成功')
              this.loading = false
              this.getSlideList()// 列表重新查询
              this.open = false// 关闭弹出层
            }).catch(() => {
              this.loading = false
            })
          }
        }
      })
    },
    // 取消
    cancel() {
      this.open = false
      this.title = ''
    },
    // 重置表单
    reset() {
      this.resetForm('form')
      this.form = {
        imageUrl: undefined,
        sortId: '0',
        userId: '0',
        skipUrl: undefined
      }
    },
    // 文件上传的相关方法
    // 移除回调
    handleRemove(file, fileList) {
      this.fileListJsonObj.filter(i1 => {
        if (file.response.data.url === i1.url) {
          this.fileListJsonObj.splice(this.fileListJsonObj.indexOf(i1), 1)
        }
      })
    },
    // 上传成功之后的回调
    handleUploadSuccess(response, file, fileList) {
      console.log('success', response, file, fileList)
      this.fileListJsonObj.push(response.data)
      this.form.imageUrl = response.msg
      console.log(this.fileListJsonObj)
    },
    // 上传失败之后的回调
    handleUploadError() {
      this.msgError('上传失败')
    }
  }
}
</script>
<style>
  .logoImg {
    width: 200px;
    height: auto;
    max-height: 100px;
  }
</style>
