import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const state = {
  token: getToken(),
  name: '',
  avatar: '',
  introduction: '',
  roles: [],
  permissions: [],
  email: '',
  strong: ''
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_INTRODUCTION: (state, introduction) => {
    state.introduction = introduction
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_PERMISSIONS: (state, permissions) => {
    state.permissions = permissions
  },
  SET_EMAIL: (state, email) => {
    state.email = email
  },
  SET_STRONG: (state, strong) => {
    state.strong = strong
  },
  SET_HONOR: (state, honor) => {
    state.honor = honor
  }
}

const actions = {
  // 用户登录
  login({ commit }, userInfo) {
    // 从 userInfo 中取出password和username
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { token } = response
        commit('SET_TOKEN', token)
        setToken(token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 获取用户信息
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { roles, permissions, username, picture, email, introduction, strong, honor } = response
        console.log(picture)
        if (!username) {
          reject('请登录.')
        }

        commit('SET_ROLES', roles)// 用户角色
        commit('SET_NAME', username)// 用户名
        commit('SET_AVATAR', picture)// 头像
        commit('SET_PERMISSIONS', permissions)// 权限
        commit('SET_EMAIL', email)// 邮箱
        commit('SET_INTRODUCTION', introduction)// 简介
        commit('SET_STRONG', strong)// 擅长
        commit('SET_HONOR', honor)// 荣耀
        resolve(response)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 用户退出
  logout({ commit, state, dispatch }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        commit('SET_PERMISSIONS', [])
        commit('SET_EMAIL', [])
        commit('SET_INTRODUCTION', [])
        commit('SET_STRONG', [])
        commit('SET_HONOR', [])
        removeToken()
        resetRouter()

        // reset visited views and cached views
        // to fixed https://github.com/PanJiaChen/vue-element-admin/issues/2485
        dispatch('tagsView/delAllViews', null, { root: true })

        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      commit('SET_PERMISSIONS', [])
      commit('SET_EMAIL', [])
      commit('SET_INTRODUCTION', [])
      commit('SET_STRONG', [])
      commit('SET_HONOR', [])
      removeToken()
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
