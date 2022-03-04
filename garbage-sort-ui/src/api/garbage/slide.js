import request from '@/utils/request'

// 分页查询
export function listSlideForPage(query) {
  return request({
    url: '/garbage/slide/listSlideForPage',
    method: 'get',
    params: query
  })
}

// 添加
export function addSlide(data) {
  return request({
    url: '/garbage/slide/addSlide',
    method: 'post',
    params: data
  })
}

// 修改
export function updateSlide(data) {
  return request({
    url: '/garbage/slide/updateSlide',
    method: 'put',
    params: data
  })
}
// 删除
export function deleteSlideByIds(id) {
  return request({
    url: '/garbage/slide/deleteSlideByIds/' + id,
    method: 'delete'
  })
}
// 查询一个
export function getSlideById(id) {
  return request({
    url: '/garbage/slide/getSlideById/' + id,
    method: 'get'
  })
}
