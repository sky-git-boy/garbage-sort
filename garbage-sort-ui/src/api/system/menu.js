import request from '@/utils/request'

// 分页查询
export function listAllMenus(query) {
  return request({
    url: '/system/menu/listAllMenus',
    method: 'get',
    params: query
  })
}
// 添加
export function addMenu(data) {
  return request({
    url: '/system/menu/addMenu',
    method: 'post',
    params: data
  })
}
// 修改
export function updateMenu(data) {
  return request({
    url: '/system/menu/updateMenu',
    method: 'put',
    params: data
  })
}
// 删除
export function deleteMenuById(id) {
  return request({
    url: '/system/menu/deleteMenuById/' + id,
    method: 'delete'
  })
}
// 查询一个
export function getMenuById(id) {
  return request({
    url: '/system/menu/getMenuById/' + id,
    method: 'get'
  })
}
// 查询菜单树
export function selectMenuTree() {
  return request({
    url: '/system/menu/selectMenuTree',
    method: 'get'
  })
}
// 根据角色ID查询当前角色ID拥有的权限菜单
export function getMenuIdsByRoleId(roleId) {
  return request({
    url: '/system/menu/getMenuIdsByRoleId/' + roleId,
    method: 'get'
  })
}
