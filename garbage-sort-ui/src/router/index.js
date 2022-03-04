import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

/* Layout */
import Layout from '@/layout'
/**
 * constantRoutes 常量路由，这些路由由后台的数据控制是否显示
 */
export const constantRoutes = [
  {
    path: '/resetPwd',
    component: () => import('@/views/resetPwd/index'),
    hidden: true
  },
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: '首页',
        meta: { title: '首页', icon: 'el-icon-house', affix: true }
      }
    ]
  },
  {
    path: '/profile',
    component: Layout,
    redirect: '/profile/index',
    hidden: true,
    children: [
      {
        path: 'index',
        component: () => import('@/views/profile/index'),
        name: 'Profile',
        meta: { title: '个人信息', icon: 'user', noCache: true }
      }
    ]
  }
]
// 异步路由
export const asyncRoutes = [
  {
    path: '/system',
    component: Layout,
    redirect: 'noRedirect',
    alwaysShow: true,
    name: '/system',
    meta: {
      title: '系统管理',
      icon: 'lock'
    },
    children: [
      {
        path: 'user',
        component: () => import('@/views/system/user/index'),
        name: '/system/user',
        meta: {
          title: '用户管理',
          icon: 'user'
        }
      },
      {
        path: 'role',
        component: () => import('@/views/system/role/index'),
        name: '/system/role',
        meta: {
          title: '角色管理',
          icon: 'tab'
        }
      },
      {
        path: 'menu',
        component: () => import('@/views/system/menu/index'),
        name: '/system/menu',
        meta: {
          title: '菜单管理',
          icon: 'bug'
        }
      },
      {
        path: 'dict',
        component: () => import('@/views/system/dict/type'),
        name: '/system/dict',
        meta: {
          title: '字典管理',
          icon: 'zip'
        }
      },
      {
        path: 'notice',
        component: () => import('@/views/system/notice/index'),
        name: '/system/notice',
        meta: {
          title: '通知公告',
          icon: 'theme'
        }
      }
    ]
  },
  {
    path: '/log',
    component: Layout,
    redirect: 'noRedirect',
    name: '/log',
    meta: {
      title: '日志管理',
      icon: 'clipboard'
    },
    children: [
      {
        path: 'log_login',
        component: () => import('@/views/system/loginInfo/index'),
        name: '/log/log_login',
        meta: {
          title: '登陆日志管理',
          icon: 'user'
        }
      },
      {
        path: 'log_opt',
        component: () => import('@/views/system/operLog/index'),
        name: '/log/log_opt',
        meta: {
          title: '操作日志管理',
          icon: 'edit'
        }
      },
      {
        path: 'sms',
        component: () => import('@/views/system/sms/index'),
        name: '/log/sms',
        meta: {
          title: '短信日志管理',
          icon: 'message'
        }
      }
    ]
  },
  {
    path: '/garbage',
    component: Layout,
    redirect: 'noRedirect',
    name: '/garbage',
    meta: {
      title: '垃圾分类管理',
      icon: 'example'
    },
    children: [
      {
        path: 'question',
        component: () => import('@/views/garbage/question/index'),
        name: '/garbage/question',
        meta: { title: '题库管理', icon: 'edit' }
      },
      {
        path: 'slide',
        component: () => import('@/views/garbage/slide/index'),
        name: '/garbage/slide',
        meta: { title: '轮播图管理', icon: 'list' }
      }
    ]
  },
  {
    path: '/recognition',
    component: Layout,
    redirect: 'noRedirect',
    name: '/recognition',
    meta: {
      title: '垃圾识别管理',
      icon: 'nested'
    },
    children: [
      {
        path: 'photo',
        component: () => import('@/views/recognition/photo/index'),
        name: '/recognition/photo',
        meta: { title: '图像识别管理', icon: 'el-icon-camera' }
      },
      {
        path: 'speech',
        component: () => import('@/views/recognition/speech/index'),
        name: '/recognition/speech',
        meta: { title: '语音识别管理', icon: 'el-icon-microphone' },
        hidden: true
      }
    ]
  }
]
export const lastRoute = [
  {
    path: '/dict',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'data/:dictId(\\d+)',
        component: () => import('@/views/system/dict/data'),
        name: 'data',
        meta: { title: '数据字典', icon: 'list' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]
const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  // 初始化时将所有路由都加载上，否则会出现刷新页面404的情况
  routes: constantRoutes
})
const router = createRouter()
// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}
export default router
