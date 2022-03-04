import request from '@/utils/request'
// 获取本周每天的登录次数
export function getWeekDayData() {
  return request({
    url: '/system/fistPageInfo/weekDayData',
    method: 'get'
  })
}
// 某一类型的总数
export function getCount() {
  return request({
    url: '/system/fistPageInfo/count',
    method: 'get'
  })
}
// 获取前五的热词
export function hotKeyword() {
  return request({
    url: '/system/fistPageInfo/hotKeyword',
    method: 'get'
  })
}
// 获取用户小测正确率
export function Accuracy() {
  return request({
    url: '/system/fistPageInfo/Accuracy',
    method: 'get'
  })
}
