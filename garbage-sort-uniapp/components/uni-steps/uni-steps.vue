<template>
	<view class="uni-steps">
		<view class="uni-steps-items" :class="'uni-steps-' + direction">
			<view class="uni-steps-item" v-for="(item,index) in steps" :key="index" :class="[item.status ? 'uni-steps-' + item.status : '']">

				<view class="uni-steps-item-circle-container">
					<view class="uni-steps-item-circle" v-if="item.status !== 'process'" :style="{backgroundColor:item.status === 'finish' ? activeColor : ''}"></view>
					<view class="uni-steps-item-circle" v-else :style="{backgroundColor:activeColor}"></view>
					<!-- <uni-icon v-else type="checkbox-filled" size="100" :color="activeColor"></uni-icon> -->
				</view>
				<view class="uni-steps-item-line" v-if="index !== steps.length-1" :style="{backgroundColor:item.status === 'finish' ? activeColor : ''}"></view>
				<view class="uni-steps-item-title-container" :style="{color:item.status === 'process' ? activeColor : ''}">
					<view class="uni-steps-item-title">{{ item.title }}</view>
					<view class="uni-steps-item-desc" v-if="item.desc">{{ item.desc}}</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import uniIcon from '../uni-icon/uni-icon.vue'
	export default {
		name: "uni-steps",
		components: {
			uniIcon
		},
		props: {
			direction: { //排列方向 row column
				type: String,
				default: 'row'
			},
			activeColor: { //激活状态颜色
				type: String,
				default: '#1aad19'
			},
			active: { //当前步骤
				type: [Number, String],
				default: 0
			},
			mydata: Array //数据
		},
		data() {
			return {}
		},
		computed: {
			steps() {
				let steps = []
				this.mydata.forEach((item, index) => {
					let step = {}
					step.title = item.title
					step.desc = item.desc
					step.status = this.getStatus(index)
					steps.push(step)
				})
				return steps
			}
		},
		methods: {
			getStatus(index) {
				if (index < Number(this.active)) {
					return 'finish'
				} else if (index === Number(this.active)) {
					return 'process'
				}
				return ''
			}
		}
	}
</script>

<style lang="scss">
	$steps-line-color:#ebedf0;
	$steps-text-color:#969799;
	$steps-title-size:28upx;
	$steps-desc-size:24upx;
	$steps-text-finish-color:#333333;
	$steps-bg-color:#ffffff;

	.uni-steps {
		width: 100%;
		box-sizing: border-box;
		display: flex;
		flex-direction: column;
		overflow: hidden;
		position: relative;

		&-items {
			position: relative;
			display: flex;
			flex-direction: row;
			margin: 10px 20px 0 20px;
			box-sizing: border-box;
			overflow: hidden;

			&.uni-steps-column {
				margin: 10px 0;
				padding-left: 31px;
				flex-direction: column;

				.uni-steps-item {
					&:after {
						content: ' ';
						position: absolute;
						height: 6upx;
						width: 100%;
						bottom: 9px;
						left: 0;
						background-color: #ebedf0;
						transform: scaleY(0.5);
					}

					&:last-child {
						position: relative;

						&:after {
							height: 0px;
						}

						.uni-steps-item-title-container {
							text-align: left;
						}

						.uni-steps-item-circle-container {
							left: -17px;
							right: auto
						}
					}

					&-title-container {
						transform: none;
						display: block;
						line-height: 36upx;
					}

					&-title {
						text-overflow: ellipsis;
						white-space: nowrap;
						overflow: hidden;
					}

					&-desc {
						white-space: normal;
						display: -webkit-box;
						-webkit-box-orient: vertical;
						-webkit-line-clamp: 2;
						overflow: hidden;
					}

					&-circle-container {
						left: -17px;
						top: -1px;
						bottom: auto;
						padding: 8px 0px;
						// z-index: 1;
					}

					&-line {
						height: 100%;
						width: 1px;
						left: -15px;
						top: -1px;
						bottom: auto;
					}


					&.uni-steps-process {
						.uni-steps-item-circle-container {
							bottom: auto;
							left: -21px;
						}
					}
				}
			}
		}

		&-item {
			flex: 1;
			position: relative;
			padding-bottom: 18px;

			&-title-container {
				text-align: left;
				margin-left: 3px;
				display: inline-block;
				transform: translateX(-50%);
				color: $steps-text-color;
			}

			&-title {
				font-size: $steps-title-size;
			}

			&-desc {
				font-size: $steps-desc-size;
			}

			&:first-child {
				.uni-steps-item-title-container {
					transform: none;
					margin-left: 0;
				}
			}

			&:last-child {
				position: absolute;
				right: 0;

				.uni-steps-item-title-container {
					transform: none;
					text-align: right;
				}

				.uni-steps-item-circle-container {
					left: auto;
					right: 0
				}
				.uni-steps-item-line{
					left: -14px
				}
			}

			&-circle-container {
				position: absolute;
				bottom: 6px;
				// left: -8px;
				// padding: 0 8px;
				background-color: $steps-bg-color;
				z-index: 1;
			}

			&-circle {
				width: 24upx;
				height: 24upx;
				background-color: $steps-text-color;
				border-radius: 50%;
			}

			&-line {
				background-color: $steps-line-color;
				position: absolute;
				bottom: 10px;
				left: 0;
				width: 100%;
				height: 8upx;
			}
			&-line:last-child{
				left: -14px;
			}

			&.uni-steps-finish {
				.uni-steps-item-title-container {
					color: $steps-text-finish-color;
				}
			}

			&.uni-steps-process {
				.uni-steps-item-circle-container {
					// bottom: 0px;
					display: flex;
				}
			}
		}
	}
</style>
