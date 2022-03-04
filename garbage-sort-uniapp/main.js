import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

// prod 
// dev
 Vue.prototype.serverUrl="http://localhost:8122/uniapp"



App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
