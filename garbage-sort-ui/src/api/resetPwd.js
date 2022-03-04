import request from '@/utils/request'

export function sendMail(mobil) {
  return request({
    url: `/system/resetPassWord/sendMail?mobil=${mobil}`,
    method: 'post'
  })
}

export function selectUserByMobil(mobil) {
  return request({
    url: `/system/resetPassWord/selectUserByMobil?mobil=${mobil}`,
    method: 'post'
  })
}

export function isvalidPhone(str) {
  const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
  return reg.test(str)
}

export function reset(mobil, code) {
  return request({
    url: '/system/resetPassWord/reset',
    method: 'post',
    params: { mobil, code }
  })
}
