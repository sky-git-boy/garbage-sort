import request from '@/utils/request'

// 分页查询
export function listImageForPage(query) {
  return request({
    url: '/recognition/image/listImageForPage',
    method: 'get',
    params: query
  })
}
