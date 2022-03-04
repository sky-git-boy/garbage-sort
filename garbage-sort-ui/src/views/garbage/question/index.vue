<template>
  <div class="app-container">
    <!-- 查询条件开始 -->
    <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
      <el-form-item label="垃圾名称" prop="garbageName">
        <el-input
          v-model="queryParams.garbageName"
          placeholder="请输入垃圾名称"
          clearable
          size="small"
          style="width:240px"
        /></el-form-item>
      <el-form-item label="垃圾类型" prop="garbageType">
        <el-select
          v-model="queryParams.garbageType"
          placeholder="请输入垃圾类型"
          clearable
          size="small"
          style="width:240px"
        >
          <el-option
            v-for="dict in typeOptions"
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
    </el-row>
    <!-- 表格工具按钮结束 -->

    <!-- 数据表格开始 -->
    <el-table v-loading="loading" border :data="questionTableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="垃圾ID" align="center" prop="questionId" />
      <el-table-column label="垃圾类型" align="center" prop="garbageType" :formatter="questionFormatter" />
      <el-table-column label="垃圾名称" align="center" prop="garbageName" />
      <el-table-column label="解析" align="center" prop="analysis" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="text" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button :disabled="scope.row.userId===1" type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)">删除</el-button>
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
      width="350px"
      center
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="50px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="类型" prop="garbageType">
              <el-select
                v-model="form.garbageType"
                placeholder="类型"
                clearable
                size="small"
                style="width:240px"
              >
                <el-option
                  v-for="d in typeOptions"
                  :key="d.dictValue"
                  :label="d.dictLabel"
                  :value="d.dictValue"
                >{{ d.dictLabel }}</el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="garbageName">
              <el-input v-model="form.garbageName" style="width:240px" placeholder="请输入名称" clearable size="small" />
            </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="解析" prop="analysis">
              <el-input v-model="form.analysis" style="width:240px" placeholder="请输入解析" clearable size="small" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" style="width:240px" placeholder="请输入备注" clearable size="small" />
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
    <!-- 分配角色的弹出层开始 -->
    <!-- 分配角色的弹出层结束 -->
  </div>
</template>
<script>
// 引入api
import { listQuest, addQuestion, updateQuestion, deleteQuestion, selectQuestionBankById } from '@/api/garbage/question'
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
      questionTableList: [],
      typeOptions: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        garbageName: undefined
      },
      // 表单数据
      form: {},
      // 表单校验
      rules: {
        garbageName: [
          { required: true, message: '名称不能为空', trigger: 'blur' }
        ],
        garbageType: [
          { required: true, message: '类型不能为空', trigger: 'blur' }
        ]
      },
      // 是否显示分配权限的弹出层
      selectRoleOpen: false,
      // roleIds 分配角色列表选择状态
      roleIds: [],
      // 角色数据
      roleTableList: [],
      // 当前选中的用户
      currentUserId: undefined
    }
  },
  created() {
    // 使用全局的根据字典类型查询字典数据的方法查询字典数据
    this.getDataByType('garbage_type').then(res => {
      console.log(res)
      this.typeOptions = res.data
    })
    // 查询表格数据
    this.getQuestionList()
  },
  // 方法
  methods: {
    // 查询表格数据
    getQuestionList() {
      this.loading = true // 打开遮罩
      listQuest(this.queryParams).then(res => {
        this.questionTableList = res.data
        this.total = res.total
        this.loading = false// 关闭遮罩
      })
    },
    // 条件查询
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getQuestionList()
    },
    // 重置查询条件
    resetQuery() {
      this.queryParams.pageNum = 1
      this.resetForm('queryForm')
      this.dateRange = []
      this.getQuestionList()
    },
    // 数据表格的多选择框选择时触发
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    // 分页pageSize变化时触发
    handleSizeChange(val) {
      this.queryParams.pageSize = val
      // 重新查询
      this.getQuestionList()
    },

    questionFormatter(row) {
      return this.selectDictLabel(this.typeOptions, row.garbageType)
    },

    // 点击上一页  下一页，跳转到哪一页面时触发
    handleCurrentChange(val) {
      this.queryParams.pageNum = val
      // 重新查询
      this.getQuestionList()
    },
    // 打开添加的弹出层
    handleAdd() {
      this.open = true
      this.reset()
      this.title = '添加用户信息'
    },
    // 打开修改的弹出层
    handleUpdate(row) {
      this.title = '修改用户信息'
      const questionId = row.questionId || this.ids
      this.open = true
      this.reset()
      // 根据Id查询一个用户信息
      this.loading = true
      selectQuestionBankById(questionId).then(res => {
        this.form = res.data
        this.loading = false
      })
    },
    // 执行删除
    handleDelete(row) {
      const questionIds = row.questionId || this.ids
      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loading = true
        deleteQuestion(questionIds).then(res => {
          this.loading = false
          this.msgSuccess('删除成功')
          this.getQuestionList()// 全查询
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
          if (this.form.questionId === undefined) {
            addQuestion(this.form).then(res => {
              this.msgSuccess('保存成功')
              this.getQuestionList()// 列表重新查询
              this.open = false// 关闭弹出层
            }).catch(() => {
              this.loading = false
              this.msgSuccess('保存失败')
            })
          } else { // 做修改
            updateQuestion(this.form).then(res => {
              this.msgSuccess('修改成功')
              this.getQuestionList()// 列表重新查询
              this.open = false// 关闭弹出层
            }).catch(() => {
              this.loading = false
              this.msgSuccess('修改失败')
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
        questionId: undefined,
        garbageType: undefined,
        garbageName: undefined,
        analysis: undefined,
        remark: undefined
      }
    },
    // 数据表格的多选择框选择时触发
    handleRoleTableSelectionChange(selection) {
      this.roleIds = selection.map(item => item.roleId)
    }
  }
}
</script>
<style scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
