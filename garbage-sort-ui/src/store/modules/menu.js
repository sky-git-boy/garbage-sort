import { asyncRoutes, constantRoutes, lastRoute } from '@/router/index'
import { getMenus } from '@/api/user'

const state = {
  routes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.routes = routes
  }
}

// 动态菜单还是定义在前端，后台指挥返回有权限的菜单列表，通过便利服务段的菜单数据，没有的将对于菜单进行隐藏
// 这样的好处是服务端无需返回前端菜单相关结构，并且菜单显示有可以通过服务端来控制，进行菜单的动态控制
// 前端新增页面也无需先通过服务端进行菜单添加，遵循了前后端分离原则
function generateRoutes(routes, srvMenus) {
  // 遍历前端菜单数据
  for (let i = 0; i < routes.length; i++) {
    const routeItem = routes[i]
    // 默认不显示
    var showItem = false
    // 遍历后端菜单数据
    for (let j = 0; j < srvMenus.length; j++) {
      const srvItem = srvMenus[j]
      // 前后端数据通过 serPath 属性来匹配
      if (routeItem.name !== undefined && routeItem.name === srvItem.serPath && srvItem.show === true) {
        showItem = true // 此时当前路由要显示
        routes[i]['hidden'] = false
        break
      }
    }
    if (showItem === false) {
      // 隐藏菜单
      routes[i]['hidden'] = true
    }
    // 递归判断子菜单
    if (routeItem['children'] !== undefined && routeItem['children'].length > 0) {
      generateRoutes(routes[i]['children'], srvMenus)
    }
  }
}

const actions = {
  getMenus({ commit }, roles) {
    return new Promise(resolve => {
      // 查询后端的菜单数据
      getMenus(state.token).then(async function(res) {
        // 获取后端菜单数据
        const srvMenus = res.data
        // 获取前端异步菜单数据
        var pushRouter = asyncRoutes
        // 初始化路由，判断路由是否显示
        generateRoutes(pushRouter, srvMenus)
        // 整合路由对象
        const routeArr = []
        // 放入常量路由
        routeArr.push(...constantRoutes)
        // 放入我们构造的路由
        routeArr.push(...pushRouter)
        // 放入最终路由
        routeArr.push(...lastRoute)
        // 放到vuex里面，全局变量
        commit('SET_ROUTES', routeArr)
        resolve(routeArr)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
