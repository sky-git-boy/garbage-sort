<template>
  <div class="app-container">
    <!-- 查询条件开始 -->
    <el-form ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
      <el-form-item label="用户姓名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户姓名"
          clearable
          size="small"
          style="width:240px"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号"
          clearable
          size="small"
          style="width:240px"
        />
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
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width:240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholde="开始日期"
          end-placeholde="结束日期"
        />
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

      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleResetPwd">重置密码</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-thumb" size="mini" :disabled="single" @click="handleSelectRole">分配角色</el-button>
      </el-col>
    </el-row>
    <!-- 表格工具按钮结束 -->

    <!-- 数据表格开始 -->
    <el-table v-loading="loading" border :data="userTableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="邮箱">
              <span>{{ props.row.email }}</span>
            </el-form-item>
            <el-form-item label="擅长">
              <span>{{ props.row.strong }}</span>
            </el-form-item>
            <el-form-item label="荣耀">
              <span>{{ props.row.honor }}</span>
            </el-form-item>
            <el-form-item label="简介">
              <span>{{ props.row.introduction }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="用户姓名" width="120px" align="center" prop="userName" />
      <el-table-column label="手机号码【登陆身份】" width="180" align="center" prop="phone" />
      <el-table-column label="性别" align="center" prop="sex" :formatter="sexFormatter" />
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column label="学历" prop="background" align="center" :formatter="backgroundFormatter" />
      <el-table-column label="状态" prop="status" align="center" :formatter="statusFormatter" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="200" />
      <el-table-column label="操作" align="center" width="250">
        <template slot-scope="scope">
          <el-button type="text" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button :disabled="scope.row.userId===1" type="text" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)">删除</el-button>
          <el-button :disabled="scope.row.userId===1" type="text" icon="el-icon-thumb" size="mini" @click="handleSelectRole(scope.row)">分配角色</el-button>
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
          <el-col :span="12">
            <el-form-item label="姓名" prop="userName">
              <el-input v-model="form.userName" style="width:240px" placeholder="请输入用户名称" clearable size="small" />
            </el-form-item></el-col>
          <el-col :span="12">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone" style="width:240px" placeholder="请输入手机号，作为登陆凭证" clearable size="small" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="级别" prop="userRank">
              <el-select
                v-model="form.userRank"
                placeholder="用户级别"
                clearable
                size="small"
                style="width:240px"
              >
                <el-option
                  v-for="d in userRankOptions"
                  :key="d.dictValue"
                  :label="d.dictLabel"
                  :value="d.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" style="width:240px" placeholder="请输入用户邮箱" clearable size="small" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="年龄" prop="age">
              <el-input-number v-model="form.age" clearable size="small" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="背景" prop="background">
              <el-select
                v-model="form.background"
                placeholder="学历"
                clearable
                size="small"
                style="width:240px"
              >
                <el-option
                  v-for="d in backgroundOptions"
                  :key="d.dictValue"
                  :label="d.dictLabel"
                  :value="d.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="性别" prop="sex">
              <el-radio-group v-model="form.sex">
                <el-radio
                  v-for="d in sexOptions"
                  :key="d.dictValue"
                  :label="d.dictValue"
                  :value="d.dictValue"
                >{{ d.dictLabel }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
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
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="擅长" prop="strong">
              <el-input v-model="form.strong" type="textarea" placeholder="请输入用户擅长" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="荣誉" prop="honor">
              <el-input v-model="form.honor" type="textarea" placeholder="请输入用户相关荣誉" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="简介" prop="introduction">
              <el-input v-model="form.introduction" type="textarea" placeholder="请输入用户简介" />
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
    <el-dialog
      :title="title"
      :visible.sync="selectRoleOpen"
      width="900px"
      center
      append-to-body
    >
      <el-table
        ref="roleListTable"
        v-loading="loading"
        border
        :data="roleTableList"
        @selection-change="handleRoleTableSelectionChange"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="角色ID" align="center" prop="roleId" />
        <el-table-column label="角色名称" align="center" prop="roleName" />
        <el-table-column label="权限编码" align="center" prop="roleCode" />
        <el-table-column label="备注" align="center" prop="remark" />
        <el-table-column label="创建时间" align="center" prop="createTime" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSaveRoleUserSubmit">确 定</el-button>
        <el-button @click="cancelRoleUser">取 消</el-button>
      </span>
    </el-dialog>
    <!-- 分配角色的弹出层结束 -->
  </div>
</template>
<script>
// 引入api
import { listUserForPage, addUser, updateUser, getUserById, deleteUserByIds, resetPwd } from '@/api/system/user'
import { selectAllRole, getRoleIdsByUserId, saveRoleUser } from '@/api/system/role'
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
      userTableList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 状态数据字典
      statusOptions: [],
      // 级别数据字典
      userRankOptions: [],
      // 背景数据字典
      backgroundOptions: [],
      // 是否参与排班
      schedulingFlagOptions: [],
      // 性别数据字典
      sexOptions: [],
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phone: undefined,
        status: undefined
      },
      // 表单数据
      form: {},
      // 表单校验
      rules: {
        userName: [
          { required: true, message: '用户姓名不能为空', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '电话号码不能为空', trigger: 'blur' },
          {
            pattern: /^1[345678]\d{9}$/,
            message: '请输入正确的手机号码',
            trigger: 'blur'
          }
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
  // 勾子
  created() {
    // 使用全局的根据字典类型查询字典数据的方法查询字典数据
    this.getDataByType('sys_normal_disable').then(res => {
      this.statusOptions = res.data
    })
    // 加载用户级别
    this.getDataByType('sys_user_level').then(res => {
      this.userRankOptions = res.data
    })
    // 加载用户背景
    this.getDataByType('sys_user_background').then(res => {
      this.backgroundOptions = res.data
    })
    // 加载用户性别
    this.getDataByType('sys_user_sex').then(res => {
      this.sexOptions = res.data
    })
    // 查询表格数据
    this.getUserList()
  },
  // 方法
  methods: {
    // 查询表格数据
    getUserList() {
      this.loading = true // 打开遮罩
      listUserForPage(this.addDateRange(this.queryParams, this.dateRange)).then(res => {
        this.userTableList = res.data
        this.total = res.total
        this.loading = false// 关闭遮罩
      })
    },
    // 条件查询
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getUserList()
    },
    // 重置查询条件
    resetQuery() {
      this.queryParams.pageNum = 1
      this.resetForm('queryForm')
      this.dateRange = []
      this.getUserList()
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
      this.getUserList()
    },
    // 点击上一页  下一页，跳转到哪一页面时触发
    handleCurrentChange(val) {
      this.queryParams.pageNum = val
      // 重新查询
      this.getUserList()
    },
    // 翻译状态
    statusFormatter(row) {
      return this.selectDictLabel(this.statusOptions, row.status)
    },
    // 翻译性别
    sexFormatter(row) {
      return this.selectDictLabel(this.sexOptions, row.sex)
    },
    // 翻译用户级别
    userRankFormatter(row) {
      return this.selectDictLabel(this.userRankOptions, row.userRank)
    },
    // 翻译用户背景（学历）
    backgroundFormatter(row) {
      return this.selectDictLabel(this.backgroundOptions, row.background)
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
      const userId = row.userId || this.ids
      this.open = true
      this.reset()
      // 根据userId查询一个用户信息
      this.loading = true
      getUserById(userId).then(res => {
        this.form = res.data
        this.loading = false
      })
    },
    // 执行删除
    handleDelete(row) {
      const userIds = row.userId || this.ids
      this.$confirm('此操作将永久删除该用户数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loading = true
        deleteUserByIds(userIds).then(res => {
          this.loading = false
          this.msgSuccess('删除成功')
          this.getUserList()// 全查询
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
          if (this.form.userId === undefined) {
            addUser(this.form).then(res => {
              this.msgSuccess('保存成功')
              this.getUserList()// 列表重新查询
              this.open = false// 关闭弹出层
            }).catch(() => {
              this.loading = false
              this.msgError('保存失败')
            })
          } else { // 做修改
            updateUser(this.form).then(res => {
              this.msgSuccess('修改成功')
              this.getUserList()// 列表重新查询
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
        userId: undefined,
        userName: undefined,
        phone: undefined,
        sex: '0',
        age: 0,
        status: '0',
        email: undefined,
        strong: undefined,
        honor: undefined,
        introduction: undefined,
        remark: undefined,
        schedulingFlag: '0'
      }
    },
    // 重置密码
    handleResetPwd() {
      const userIds = this.ids
      const tx = this
      tx.$confirm('是否确认重置用户ID为:' + userIds + '的密码?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(function() {
        resetPwd(userIds).then(res => {
          tx.msgSuccess('重置成功')
        }).catch(function() {
          tx.msgSuccess('重置失败')
        })
      }).catch(function() {
        tx.msgError('重置已取消')
      })
    },

    // 打开分配角色的弹出层
    handleSelectRole(row) {
      this.selectRoleOpen = true
      this.title = '分配角色'
      this.currentUserId = row.userId || this.ids[0]
      const tx = this
      selectAllRole().then(res => {
        tx.roleTableList = res.data
        this.$nextTick(() => {
          // 根据当前用户查找之前拥有的角色IDS
          getRoleIdsByUserId(tx.currentUserId).then(res2 => {
            res2.data.filter(r1 => {
              tx.roleTableList.filter(r2 => {
                if (r1 === r2.roleId) {
                  // 选中表格checkbox
                  tx.$refs.roleListTable.toggleRowSelection(r2, true)
                }
              })
            })
          })
        })
      })
    },
    cancelRoleUser() {
      this.selectRoleOpen = false
    },
    // 数据表格的多选择框选择时触发
    handleRoleTableSelectionChange(selection) {
      this.roleIds = selection.map(item => item.roleId)
    },
    // 保存用户和角色之间的关系
    handleSaveRoleUserSubmit() {
      saveRoleUser(this.currentUserId, this.roleIds).then(res => {
        this.msgSuccess('分配成功')
        this.selectRoleOpen = false
      }).catch(function() {
        this.msgError('分配失败')
      })
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
