import request from '@/utils/request'

// 分页查询
export function listNoticeForPage(query) {
  return request({
    url: '/system/notice/listNoticeForPage',
    method: 'get',
    params: query
  })
}
// 添加
export function addNotice(data) {
  return request({
    url: '/system/notice/addNotice',
    method: 'post',
    params: data
  })
}

// 修改
export function updateNotice(data) {
  return request({
    url: '/system/notice/updateNotice',
    method: 'put',
    params: data
  })
}
// 删除
export function deleteNoticeByIds(id) {
  return request({
    url: '/system/notice/deleteNoticeByIds/' + id,
    method: 'delete'
  })
}
// 查询一个
export function getNoticeById(id) {
  return request({
    url: '/system/notice/getNoticeById/' + id,
    method: 'get'
  })
}

