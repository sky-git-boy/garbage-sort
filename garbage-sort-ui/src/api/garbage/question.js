import request from '@/utils/request'

// 分页查询
export function listQuest(query) {
  return request({
    url: '/garbage/question/listQuest',
    method: 'get',
    params: query
  })
}

export function selectType(query) {
  return request({
    url: '/garbage/question/selectType',
    method: 'get',
    params: query
  })
}

// 添加
export function addQuestion(data) {
  return request({
    url: '/garbage/question/addQuestion',
    method: 'post',
    params: data
  })
}
// 修改
export function updateQuestion(data) {
  return request({
    url: '/garbage/question/updateQuestion',
    method: 'put',
    params: data
  })
}
// 删除
export function deleteQuestion(id) {
  return request({
    url: '/garbage/question/deleteQuestion/' + id,
    method: 'delete'
  })
}
// 查询一个
export function selectQuestionBankById(id) {
  return request({
    url: '/garbage/question/selectQuestionBankById/' + id,
    method: 'get'
  })
}
