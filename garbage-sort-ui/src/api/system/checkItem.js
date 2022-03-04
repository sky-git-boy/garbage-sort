import request from '@/utils/request'

// 分页查询
export function listCheckItemForPage(query) {
  return request({
    url: '/system/checkItem/listCheckItemForPage',
    method: 'get',
    params: query
  })
}
// 添加
export function addCheckItem(data) {
  return request({
    url: '/system/checkItem/addCheckItem',
    method: 'post',
    params: data
  })
}
// 修改
export function updateCheckItem(data) {
  return request({
    url: '/system/checkItem/updateCheckItem',
    method: 'put',
    params: data
  })
}
// 删除
export function deleteCheckItemByIds(id) {
  return request({
    url: '/system/checkItem/deleteCheckItemByIds/' + id,
    method: 'delete'
  })
}
// 查询一个
export function getCheckItemById(id) {
  return request({
    url: '/system/checkItem/getCheckItemById/' + id,
    method: 'get'
  })
}
// 查询所有有效的检查项目信息
export function selectAllCheckItem() {
  return request({
    url: '/system/checkItem/selectAllCheckItem',
    method: 'get'
  })
}
