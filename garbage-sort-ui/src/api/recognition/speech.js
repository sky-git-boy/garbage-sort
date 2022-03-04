import request from '@/utils/request'

// 分页查询
export function listSpeechForPage(query) {
  return request({
    url: '/recognition/speech/listSpeechForPage',
    method: 'get',
    params: query
  })
}
