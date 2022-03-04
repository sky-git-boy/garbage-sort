<template>
  <div :class="className" :style="{ height: height,width: width }" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { Accuracy } from '@/api/system/firstpage'

// const animationDuration = 3000

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
    setInterval(this.initChart(), 300)
  },

  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },

  methods: {
    initChart() {
      Accuracy().then(res => {
        let angle = 0 // 角度，用来做简单的动画效果的
        const value = res.data
        console.log(value)
        angle = angle + 3
        function getCirlPoint(x0, y0, r, angle) {
          const x1 = x0 + r * Math.cos((angle * Math.PI) / 180)
          const y1 = y0 + r * Math.sin((angle * Math.PI) / 180)
          return {
            x: x1,
            y: y1
          }
        }
        this.chart = echarts.init(this.$el, 'macarons')
        this.chart.setOption({
          backgroundColor: '#ffffff',
          title: {
            text: '{a|' + value + '}{c|%}',
            x: 'center',
            y: 'center',
            textStyle: {
              rich: {
                a: {
                  fontSize: 40,
                  color: '#29EEF3'
                },
                c: {
                  fontSize: 20,
                  color: '#00ff7f'
                  // padding: [5,0]
                }
              }
            }
          },
          legend: {
            type: 'plain',
            orient: 'vertical',
            right: 0,
            top: '10%',
            align: 'auto',
            data: [
              {
                name: '涨价后没吃过',
                icon: 'circle'
              },
              {
                name: '天天吃',
                icon: 'circle'
              },
              {
                name: '三五天吃一次',
                icon: 'circle'
              },
              {
                name: '半个月吃一次',
                icon: 'circle'
              }
            ],
            textStyle: {
              color: 'white',
              fontSize: 16,
              padding: [10, 1, 10, 0]
            },
            selectedMode: false
          },
          series: [
            {
              name: 'ring5',
              type: 'custom',
              coordinateSystem: 'none',
              renderItem: function(params, api) {
                return {
                  type: 'arc',
                  shape: {
                    cx: api.getWidth() / 2,
                    cy: api.getHeight() / 2,
                    r: (Math.min(api.getWidth(), api.getHeight()) / 2) * 0.6,
                    startAngle: ((0 + angle) * Math.PI) / 180,
                    endAngle: ((90 + angle) * Math.PI) / 180
                  },
                  style: {
                    stroke: '#0CD3DB',
                    fill: 'transparent',
                    lineWidth: 1.5
                  },
                  silent: true
                }
              },
              data: [0]
            },
            {
              name: 'ring5',
              type: 'custom',
              coordinateSystem: 'none',
              renderItem: function(params, api) {
                return {
                  type: 'arc',
                  shape: {
                    cx: api.getWidth() / 2,
                    cy: api.getHeight() / 2,
                    r: (Math.min(api.getWidth(), api.getHeight()) / 2) * 0.6,
                    startAngle: ((180 + angle) * Math.PI) / 180,
                    endAngle: ((270 + angle) * Math.PI) / 180
                  },
                  style: {
                    stroke: '#0CD3DB',
                    fill: 'transparent',
                    lineWidth: 1.5
                  },
                  silent: true
                }
              },
              data: [0]
            },
            {
              name: 'ring5',
              type: 'custom',
              coordinateSystem: 'none',
              renderItem: function(params, api) {
                return {
                  type: 'arc',
                  shape: {
                    cx: api.getWidth() / 2,
                    cy: api.getHeight() / 2,
                    r: (Math.min(api.getWidth(), api.getHeight()) / 2) * 0.65,
                    startAngle: ((270 + -angle) * Math.PI) / 180,
                    endAngle: ((40 + -angle) * Math.PI) / 180
                  },
                  style: {
                    stroke: '#0CD3DB',
                    fill: 'transparent',
                    lineWidth: 1.5
                  },
                  silent: true
                }
              },
              data: [0]
            },
            {
              name: 'ring5',
              type: 'custom',
              coordinateSystem: 'none',
              renderItem: function(params, api) {
                return {
                  type: 'arc',
                  shape: {
                    cx: api.getWidth() / 2,
                    cy: api.getHeight() / 2,
                    r: (Math.min(api.getWidth(), api.getHeight()) / 2) * 0.65,
                    startAngle: ((90 + -angle) * Math.PI) / 180,
                    endAngle: ((220 + -angle) * Math.PI) / 180
                  },
                  style: {
                    stroke: '#0CD3DB',
                    fill: 'transparent',
                    lineWidth: 1.5
                  },
                  silent: true
                }
              },
              data: [0]
            },
            {
              name: 'ring5',
              type: 'custom',
              coordinateSystem: 'none',
              renderItem: function(params, api) {
                const x0 = api.getWidth() / 2
                const y0 = api.getHeight() / 2
                const r = (Math.min(api.getWidth(), api.getHeight()) / 2) * 0.65
                const point = getCirlPoint(x0, y0, r, 90 + -angle)
                return {
                  type: 'circle',
                  shape: {
                    cx: point.x,
                    cy: point.y,
                    r: 4
                  },
                  style: {
                    stroke: '#0CD3DB', // 粉
                    fill: '#0CD3DB'
                  },
                  silent: true
                }
              },
              data: [0]
            },
            {
              name: 'ring5', // 绿点
              type: 'custom',
              coordinateSystem: 'none',
              renderItem: function(params, api) {
                const x0 = api.getWidth() / 2
                const y0 = api.getHeight() / 2
                const r = (Math.min(api.getWidth(), api.getHeight()) / 2) * 0.65
                const point = getCirlPoint(x0, y0, r, 270 + -angle)
                return {
                  type: 'circle',
                  shape: {
                    cx: point.x,
                    cy: point.y,
                    r: 4
                  },
                  style: {
                    stroke: '#0CD3DB', // 绿
                    fill: '#0CD3DB'
                  },
                  silent: true
                }
              },
              data: [0]
            },
            {
              name: '吃猪肉频率',
              type: 'pie',
              radius: ['58%', '45%'],
              silent: true,
              clockwise: true,
              startAngle: 90,
              z: 0,
              zlevel: 0,
              label: {
                normal: {
                  position: 'center'
                }
              },
              data: [
                {
                  value: value,
                  name: '',
                  itemStyle: {
                    normal: {
                      color: {
                        // 完成的圆环的颜色
                        colorStops: [
                          {
                            offset: 0,
                            color: '#4FADFD' // 0% 处的颜色
                          },
                          {
                            offset: 1,
                            color: '#28E8FA' // 100% 处的颜色
                          }
                        ]
                      }
                    }
                  }
                },
                {
                  value: 100 - value,
                  name: '',
                  label: {
                    normal: {
                      show: false
                    }
                  },
                  itemStyle: {
                    normal: {
                      color: '#173164'
                    }
                  }
                }
              ]
            },
            {
              name: '',
              type: 'gauge',
              radius: '58%',
              center: ['50%', '50%'],
              startAngle: 0,
              endAngle: 359.9,
              splitNumber: 8,
              hoverAnimation: true,
              axisTick: {
                show: false
              },
              splitLine: {
                length: 60,
                lineStyle: {
                  width: 5,
                  color: '#ffffff'
                }
              },
              axisLabel: {
                show: false
              },
              pointer: {
                show: false
              },
              axisLine: {
                lineStyle: {
                  opacity: 0
                }
              },
              detail: {
                show: false
              },
              data: [
                {
                  value: 0,
                  name: ''
                }
              ]
            }
          ]
        })
      })
    }
  }
}
</script>
