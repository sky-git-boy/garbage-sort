<template>
  <div class="app-container">
    <!-- 查询条件开始 -->
    <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="88px">
      <el-form-item label="公告标题" prop="noticeTitle">
        <el-input
          v-model="queryParams.noticeTitle"
          placeholder="请输入通知公告标题"
          clearable
          size="small"
          style="width:240px"
        />
      </el-form-item>
      <el-form-item label="发布者" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入发布者"
          clearable
          size="small"
          style="width:240px"
        />
      </el-form-item>
      <el-form-item label="类型" prop="noticeType">
        <el-select
          v-model="queryParams.noticeType"
          placeholder="类型"
          clearable
          size="small"
          style="width:240px"
        >
          <el-option
            v-for="dict in noticeTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
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
    <el-table v-loading="loading" border :data="noticeTableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="公告ID" align="center" prop="noticeId" width="120px" />
      <el-table-column label="标题" align="center" prop="noticeTitle" />
      <el-table-column label="类型" prop="noticeType" align="center" :formatter="noticeTypeFormatter" width="125px" />
      <el-table-column label="状态" prop="status" align="center" :formatter="statusFormatter" width="133px" />
      <el-table-column label="发布者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" />
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="text" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)">删除</el-button>
          <el-button type="text" icon="el-icon-view" size="mini" @click="handleView(scope.row)">查看</el-button>
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
      width="800px"
      center
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="标题" prop="noticeTitle">
              <el-input v-model="form.noticeTitle" placeholder="请输入通知公告标题" clearable size="small" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                  :value="dict.dictValue"
                >{{ dict.dictLabel }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类型" prop="noticeType">
              <el-radio-group v-model="form.noticeType">
                <el-radio
                  v-for="dict in noticeTypeOptions"
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
            <el-form-item label="公告内容">
              <markdown-editor
                ref="noticeContent"
                v-model="form.noticeContent"
                height="300px"
                :options="{hideModeSwitch:true,previewStyle:'tab'}"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注" clearable size="small" />
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

    <!--公告内容 弹出层开始 -->
    <el-dialog
      :title="title"
      :visible.sync="noticeContentOpen"
      width="800px"
      center
      append-to-body
    >
      <MarkdownEditor ref="noticeContent" v-model="noticeContent" :aria-disabled="true" :options="{hideModeSwitch:true,previewStyle:'tab'}" />
    </el-dialog>
    <!-- 公告内容弹出层结束 -->

  </div>
</template>
<script>
import MarkdownEditor from '@/components/MarkdownEditor'

// 引入api
import { listNoticeForPage, addNotice, updateNotice, getNoticeById, deleteNoticeByIds } from '@/api/system/notice'
export default {
  components: { MarkdownEditor },
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
      noticeTableList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,

      // 状态数据字典
      statusOptions: [],
      // 公告类型
      noticeTypeOptions: [],
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        noticeTitle: undefined,
        createBy: undefined,
        noticeType: undefined,
        status: undefined
      },
      // 表单数据
      form: {},
      // 表单校验
      rules: {
        noticeTitle: [
          { required: true, message: '通知公告标题不能为空', trigger: 'blur' }
        ]
      },
      // 查看内容的弹出层
      noticeContentOpen: false,
      // 查看内容
      noticeContent: undefined
    }
  },
  // 勾子
  created() {
    // 使用全局的根据字典类型查询字典数据的方法查询字典数据
    this.getDataByType('sys_normal_disable').then(res => {
      this.statusOptions = res.data
    })
    this.getDataByType('sys_notice_type').then(res => {
      this.noticeTypeOptions = res.data
    })
    // 查询表格数据
    this.getNoticeList()
  },
  // 方法
  methods: {
    // 查询表格数据
    getNoticeList() {
      this.loading = true // 打开遮罩
      listNoticeForPage(this.addDateRange(this.queryParams, this.dateRange)).then(res => {
        this.noticeTableList = res.data
        this.total = res.total
        this.loading = false// 关闭遮罩
      })
    },
    // 条件查询
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getNoticeList()
    },
    // 重置查询条件
    resetQuery() {
      this.queryParams.pageNum = 1
      this.resetForm('queryForm')
      this.dateRange = []
      this.getNoticeList()
    },
    // 数据表格的多选择框选择时触发
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.noticeId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    // 分页pageSize变化时触发
    handleSizeChange(val) {
      this.queryParams.pageSize = val
      // 重新查询
      this.getNoticeList()
    },
    // 点击上一页  下一页，跳转到哪一页面时触发
    handleCurrentChange(val) {
      this.queryParams.pageNum = val
      // 重新查询
      this.getNoticeList()
    },
    // 翻译状态
    statusFormatter(row) {
      return this.selectDictLabel(this.statusOptions, row.status)
    },
    // 翻译类型
    noticeTypeFormatter(row) {
      return this.selectDictLabel(this.noticeTypeOptions, row.noticeType)
    },
    // 打开添加的弹出层
    handleAdd() {
      this.open = true
      this.reset()
      this.title = '添加通知公告信息'
    },
    // 打开修改的弹出层
    handleUpdate(row) {
      this.title = '修改通知公告信息'
      const noticeId = row.noticeId || this.ids
      this.open = true
      this.reset()
      // 根据noticeId查询一个公告信息
      this.loading = true
      getNoticeById(noticeId).then(res => {
        this.form = res.data
        this.loading = false
      })
    },
    // 执行删除
    handleDelete(row) {
      const noticeIds = row.noticeId || this.ids
      this.$confirm('此操作将永久删除该通知公告数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loading = true
        deleteNoticeByIds(noticeIds).then(res => {
          this.loading = false
          this.msgSuccess('删除成功')
          this.getNoticeList()// 全查询
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
          if (this.form.noticeId === undefined) {
            addNotice(this.form).then(res => {
              this.msgSuccess('保存成功')
              this.loading = false
              this.getNoticeList()// 列表重新查询
              this.open = false// 关闭弹出层
            }).catch(() => {
              this.loading = false
            })
          } else { // 做修改
            updateNotice(this.form).then(res => {
              this.msgSuccess('修改成功')
              this.loading = false
              this.getNoticeList()// 列表重新查询
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
        noticeId: undefined,
        noticeTitle: undefined,
        noticeContent: undefined,
        noticeType: '0',
        status: '0',
        remark: undefined
      }
    },
    // 打开修改的弹出层
    handleView(row) {
      this.title = row.noticeTitle
      this.noticeContentOpen = true
      this.noticeContent = row.noticeContent
    }
  }
}
</script>
