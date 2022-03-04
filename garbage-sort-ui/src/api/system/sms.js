import request from '@/utils/request'
// 分页查询
export function listForPage(query) {
  return request({
    url: '/system/sms/listSmsForPage',
    method: 'get',
    params: query
  })
}
// 通过id删除
export function deleteSmsByIds(ids) {
  return request({
    url: '/system/sms/deleteSmsByIds/' + ids,
    method: 'delete'
  })
}

