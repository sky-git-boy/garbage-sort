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
      <el-form-item label="关键字" prop="oneKeyword">
        <el-input
          v-model="queryParams.oneKeyword"
          placeholder="请输入关键字"
          clearable
          size="small"
          style="width:240px"
        />
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

    <!-- 数据表格开始 -->
    <el-table v-loading="loading" border :data="imageTableList">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="总关键字">
              <span>{{ props.row.allKeyword }}</span>
            </el-form-item>
          </el-form>
          <el-form label-position="left">
            <el-form-item label="总结果">
              <span>{{ props.row.allResult }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="图片" align="center" prop="filepath">
        <template slot-scope="scope">
          <img class="logoImg" :src="scope.row.filepath">
        </template>
      </el-table-column>
      <el-table-column label="关键字" align="center" prop="oneKeyword" />
      <el-table-column label="一个结果" align="center" prop="oneResult" />
      <el-table-column label="用户姓名" align="center" prop="userName" />
      <el-table-column label="创建时间" align="center" prop="times" />
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
  </div>
</template>
<script>
// 引入api
import { listImageForPage } from '@/api/recognition/image'
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
      imageTableList: [],
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        oneKeyword: undefined,
        userName: undefined
      }
    }
  },
  // 勾子
  created() {
    // 查询表格数据
    this.getImageList()
  },
  // 方法
  methods: {
    // 查询表格数据
    getImageList() {
      this.loading = true // 打开遮罩
      listImageForPage(this.addDateRange(this.queryParams, this.dateRange)).then(res => {
        this.imageTableList = res.data
        this.total = res.total
        this.loading = false// 关闭遮罩
      })
    },
    // 条件查询
    handleQuery() {
      this.queryParams.pageNum = 1
      this.queryParams.pageSize = 10
      this.getImageList()
    },
    // 重置查询条件
    resetQuery() {
      this.queryParams.pageNum = 1
      this.queryParams.pageSize = 10
      this.resetForm('queryForm')
      this.dateRange = []
      this.getImageList()
    },
    // 分页pageSize变化时触发
    handleSizeChange(val) {
      this.queryParams.pageSize = val
      // 重新查询
      this.getImageList()
    },
    // 点击上一页  下一页，跳转到哪一页面时触发
    handleCurrentChange(val) {
      this.queryParams.pageNum = val
      // 重新查询
      this.getImageList()
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
  .logoImg {
    width: 50px;
    height: auto;
    max-height: 100px;
  }
</style>
