<template>
	<view>
		<!-- 轮播图 -->
		<view v-if="true">
			<swiper :indicator-dots="true" :autoplay="true" :circular="true">
				<swiper-item class="swiper-flex" v-for="(item,index) in swiper" :key="index">
					<view @click="navigateTo(item.skipUrl)" class="swiper-item">
						<image class="swiper-img" :src="item.imageUrl"></image>
					</view>
				</swiper-item>
			</swiper>
		</view>

		<!-- 搜索框 -->
		<view class="form-view">
			<form @submit="formSubmit" @reset="formReset" class="form-form">
				<view class="input-view">
					<view @click="takePhoto" class="input-view-item">
						<image class="search-img" src="../../static/icos/camera.png"></image>
					</view>
					<view @click="readyRecord" class="input-view-item">
						<image class="search-img" src="../../static/icos/record.png"></image>
					</view>
					<view class="input-view-item">
						<input confirm-type="search" @confirm="searchKeyword" @search="searchKeyword" v-model="keyword"
							id="inputid" class="input-search" name="input" placeholder="输入搜索关键词" />
					</view>
					<view @tap="searchKeyword" class=" font-search">查询</view>
				</view>
			</form>
		</view>

		<!-- 提示语 -->
		<view v-show="true">
			<view class="">
				<uni-notice-bar show-icon="false" color="#999" backgroundColor="rgb(242,242,242)"
					text="温馨提示: 垃圾分一分,环境美十分"></uni-notice-bar>
			</view>
		</view>

		<view class="">
			<view class="simpleTips">
			</view>
		</view>

		<view>
			<view class="card1" @click="switchTabToClassify(2)">
				<text class="text1">湿垃圾</text>
				<text class="text2">蘑菇 糖果 饼干</text>
				<image src="../../static/index/wet.png" class="draw1"></image>
			</view>
			<view class="card2" @click="switchTabToClassify(3)">
				<text class="text1">可回收垃圾</text>
				<text class="text2">电脑 信封 泡沫</text>
				<image src="../../static/index/recyclable.png" class="draw1"></image>
			</view>
			<view class="card3" @click="switchTabToClassify(1)">
				<text class="text1">干垃圾</text>
				<text class="text2">纸巾 胶水 镜子</text>
				<image src="../../static/index/dry.png" class="draw1"></image>
			</view>
			<view class="card4" @click="switchTabToClassify(4)">
				<text class="text1">有害垃圾</text>
				<text class="text2">胶片 灯泡 水银</text>
				<image src="../../static/index/harmful.png" class="draw1"></image>
			</view>
		</view>

		<view class="plane" style="margin-top: 40upx;">
		  <image src="../../static/index/plane.png" class="plane_img"></image>
		</view>
		
		<!-- 分享组件 -->
		<share />
		
	</view>
</template>

<script>
	import share from "@/components/share.vue"

	export default {
		components: {
			share
		},
		data() {
			return {
				title: '',
				defaultKeyword: "",
				keyword: "",
				swiper: [], // 幻灯片 swiper 数据
				isShowKeywordList: false,
				keywordList: [],
				randOneObj: {
					garbageName: ""
				}
			}
		},
		
		onShow() {
			this.randOne();
		},
		
		onLoad() {
			let me = this;
			//  获取幻灯片
			uni.request({
				url: me.serverUrl + '/slideShow',
				success: (res) => {
					me.swiper = res.data.data;
				}
			});
		},

		methods: {
			// 查询一个
			randOne() {
				uni.request({
					url: this.serverUrl + '/qb/randNum',
					success: (res) => {
						this.randOneObj = res.data.data[0];
						this.keyword = res.data.data[0].garbageName;
					}
				});
			},
			
			// 类型跳转
			switchTabToClassify(index) {
				console.log(index)
				getApp().globalData.typeid = index
				uni.switchTab({
					url: '/pages/type/type',
				});
			},
			
			// 幻灯片 的点击跳转事件
			navigateTo(skipUrl) {
				uni.navigateTo({
					url: '/pages/webview/webview?url=' + skipUrl
				});
			},
			
			// 查询
			searchKeyword() {
				let me = this;
				if (!me.keyword) return;
				uni.request({
					url: me.serverUrl + "/search/uname/" + me.keyword,
					success: (res) => {
						let data = res.data.data;
						let uniOne = JSON.stringify(data.questionBank);
						let results = JSON.stringify(data.results);
						uni.navigateTo({
							url: `/pages/search/search?type=3&keyword=${me.keyword}&keywordList=${results}&uni=${uniOne}`
						});
					}
				});
			},
			// 跳转
			readyRecord() {
				uni.navigateTo({
					url: `/pages/search/search?type=2`
				});
			},

			takePhoto() {
				uni.navigateTo({
					url: `/pages/search/search?type=1`
				});
			}
		}
	}
</script>

<style>
	@import url("index.css");
</style>
