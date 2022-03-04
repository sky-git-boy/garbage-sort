import request from '@/utils/request'
// 分页查询
export function listForPage(query) {
  return request({
    url: '/system/loginInfo/listForPage',
    method: 'get',
    params: query
  })
}
// 通过id删除登录日志
export function deleteLoginInfoByIds(ids) {
  return request({
    url: '/system/loginInfo/deleteLoginInfoByIds/' + ids,
    method: 'delete'
  })
}
// 清空
export function clearLoginInfo() {
  return request({
    url: '/system/loginInfo/clearLoginInfo',
    method: 'delete'
  })
}
