import request from '@/utils/request'

// 分页查询
export function listRegisteredItemForPage(query) {
  return request({
    url: '/system/registeredItem/listRegisteredItemForPage',
    method: 'get',
    params: query
  })
}
// 添加
export function addRegisteredItem(data) {
  return request({
    url: '/system/registeredItem/addRegisteredItem',
    method: 'post',
    params: data
  })
}
// 修改
export function updateRegisteredItem(data) {
  return request({
    url: '/system/registeredItem/updateRegisteredItem',
    method: 'put',
    params: data
  })
}
// 删除
export function deleteRegisteredItemByIds(id) {
  return request({
    url: '/system/registeredItem/deleteRegisteredItemByIds/' + id,
    method: 'delete'
  })
}
// 查询一个
export function getRegisteredItemById(id) {
  return request({
    url: '/system/registeredItem/getRegisteredItemById/' + id,
    method: 'get'
  })
}
// 查询所有有效的检查项目信息
export function selectAllRegisteredItem() {
  return request({
    url: '/system/registeredItem/selectAllRegisteredItem',
    method: 'get'
  })
}
