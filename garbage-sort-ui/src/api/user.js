import request from '@/utils/request'
// 登录
export function login(data) {
  return request({
    url: '/login/doLogin',
    method: 'post',
    data
  })
}
// 获取用户信息
export function getInfo(token) {
  return request({
    url: '/login/getInfo',
    method: 'get',
    params: { token }
  })
}
// 退出
export function logout() {
  return request({
    url: '/login/logout',
    method: 'post'
  })
}
// 获取菜单
export function getMenus() {
  return request({
    url: '/login/getMenus',
    method: 'get'
  })
}
