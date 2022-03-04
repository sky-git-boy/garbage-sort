import request from '@/utils/request'

// 分页查询字典数据
export function listForPage(query) {
  return request({
    url: '/system/dict/data/listForPage',
    method: 'get',
    params: query
  })
}
// 添加字典数据
export function addDictData(data) {
  return request({
    url: '/system/dict/data/addDictData',
    method: 'post',
    params: data
  })
}
// 批量删除字典数据
export function deleteDictDataByIds(dictCodeIds) {
  return request({
    url: '/system/dict/data/deleteDictDataByIds/' + dictCodeIds,
    method: 'delete'
  })
}
// 修改字典数据
export function updateDictData(data) {
  return request({
    url: '/system/dict/data/updateDictData',
    method: 'put',
    params: data
  })
}
// 根据字典类型查询字典信息
export function getDataByType(dictType) {
  return request({
    url: '/system/dict/data/getDataByType/' + dictType,
    method: 'get'
  })
}
// 根据字典数据ID查询字典数据
export function getDictDataById(dictCode) {
  return request({
    url: '/system/dict/data/getOne/' + dictCode,
    method: 'get'
  })
}
