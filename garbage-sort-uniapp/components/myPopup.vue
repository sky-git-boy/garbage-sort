<template>
	<view>
		<view class="">
			<view v-show="show" class="detail-show-outer" @tap="hide"></view>
			<view v-show="show" class="detail-show-inner">
				<view class="inner-display">
					<view class="inner-text inner-text-show">
						{{detail.keyword}} 属于
						<view v-if="detail.garbageType==1" class="text-margin-left garbage-gan-item"> 干垃圾</view>
						<view v-else-if="detail.garbageType==2" class="text-margin-left garbage-shi-item"> 湿垃圾</view>
						<view v-else-if="detail.garbageType==3" class="text-margin-left garbage-huishou-item"> 可回收物</view>
						<view v-else-if="detail.garbageType==4" class="text-margin-left garbage-youhai-item"> 有害垃圾</view>
					</view>
					<view v-if="detail.remark!=null && detail.remark!=undefined" :class="detail.garbageType==1?'garbage-gan-item':detail.garbageType==2?'garbage-shi-item':detail.garbageType==3?'garbage-huishou-item':'garbage-youhai-item'"
					 class="inner-text">
						{{detail.remark}}
					</view>

					<view class="inner-text">
						<image v-if="detail.garbageType==1" class="show-img" src="../../../static/classify/style-gan.jpg"></image>
						<image v-else-if="detail.garbageType==2" class="show-img-shi" src="../../../static/classify/style-shi.jpg"></image>
						<image v-else-if="detail.garbageType==3" class="show-img-huishou" src="../../../static/classify/style-huishou.jpg"></image>
						<image v-else-if="detail.garbageType==4" class="show-img" src="../../../static/classify/style-youhai.jpg"></image>
					</view>
					
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: "myPopup",
		props: {
			show: {
				type: Boolean,
				default: false
			},
			detail: {
				type: Object,
				default: null,
			}
		},
		data() {
			return {};
		},
		methods: {
			hide() {
				this.$emit('hideMypopup');
			}
		}
	}
</script>

<style>
	.detail-show-outer {
		position: fixed;
		z-index: 998;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		background-color: rgba(0, 0, 0, 0.5);
	}

	.detail-show-inner {
		position: fixed;
		z-index: 999;
		top: 100upx;
		right: 40upx;
		/* bottom: 160upx; */
		left: 40upx;
		background-color: #ffffff;
		border-radius: 20upx;
		display: flex;
		justify-content: center;
	}

	.inner-display {
		display: flex;
		flex-direction: column;
		width: 90%;
		/* border: 2upx solid red; */
		margin-top: 30upx;
		/* justify-content: center; */
	}

	.inner-text {
		/* justify-content: center; */
		text-align: center;
		margin-bottom: 10upx;
		font-size: 34upx;
	}

	.inner-text-kefu {
		font-size: 30upx;
		color: #72c69c;
		border: 1px solid red;
		font-size: 30upx;
		color: #72c69c;
		border: 1px solid blue;
		width: 50%;
		height: 50upx;
		padding: 0;
		line-height: 50upx;
		margin-bottom: 10upx;
		margin-top: 10upx;

	}

	.inner-text-show {
		display: flex;
		flex-direction: row;
		justify-content: center;
	}

	.inner-button {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.text-button {
		padding-left: 70upx;
		padding-right: 70upx;
		font-size: 28upx;
		height: 60upx;
		line-height: 60upx;

	}

	.i-know {
		background-color: #007aff;
		color: #fff;
	}

	.show-img {
		width: 530upx;
		height: 600upx;
		margin: 30upx 0;
	}

	.show-img-shi {
		width: 510upx;
		height: 620upx;
	}

	.show-img-huishou {
		width: 500upx;
		height: 620upx;
	}


	/* 文字颜色  start*/

	.garbage-gan-item {
		color: rgb(44, 43, 39);
	}

	.garbage-shi-item {
		color: rgb(102, 63, 52);
	}

	.garbage-huishou-item {
		color: rgb(36, 71, 131);
	}

	.garbage-youhai-item {
		color: rgb(229, 49, 34);
	}

	.text-margin-left {
		margin-left: 10upx;
	}

	/* 文字颜色 end */
</style>
