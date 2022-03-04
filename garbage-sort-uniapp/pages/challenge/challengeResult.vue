<template>
	<view class="content">
		<view class="exam_over">
			<view class="exam_over_title">垃圾分类小测试</view>
			
			<view class="" style="margin-bottom: 20upx;">
				
				<view class="level">
					<view class="exam_iver_scope">
						{{score!=0?score:''}}0分
					</view>
				</view>
				
				<view class="">
					<view class="table">
						<view class="table-item" :class="'gray'" style="font-weight: bold;">
							<view class="garbage-name">题目</view>
							<view class="selected">我的答案</view>
							<view class="result">我的答案</view>
						</view>
						<view class="table-item" v-for="(item,index) in list" :key="index" :class="index%2==1?'gray':''">
							<view class="garbage-name">{{item.garbageName}}</view>
							<view class="selected" :class="item.garbageType!=item.selectedType?'through':''">
								<view v-if="item.selectedType==1" class="">干垃圾</view>
								<view v-else-if="item.selectedType==2" class="">湿垃圾</view>
								<view v-else-if="item.selectedType==3" class="">可回收物</view>
								<view v-else-if="item.selectedType==4" class="">有害垃圾</view>
							</view>	
							<view class="result">
								<view v-if="item.garbageType==item.selectedType" class="">
									<image class="icon-img" src="../../static/icos/right-full.png" mode=""></image>
								</view>
								<view v-else class="">
									<view v-if="item.garbageType==1" class="garbage-gan-item">干垃圾</view>
									<view v-else-if="item.garbageType==2" class="garbage-shi-item">湿垃圾</view>
									<view v-else-if="item.garbageType==3" class="garbage-huishou-item">可回收物</view>
									<view v-else-if="item.garbageType==4" class="garbage-youhai-item">有害垃圾</view>
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		
		<view class="button-show">
			<view class="">
				<button class="goonButton button-left" type="warn" @tap="goOnChallenge">继续挑战</button>
			</view>
			<view class="">
				<button class="goonButton" type="primary" open-type="share">考考别人</button>
			</view>
		</view>
		
		<share />
	</view>
</template>

<script>
	import share from "@/components/share.vue"
	export default {
		data() {
			return {
				popupShow: false,
				level: null,
				score: null,
				list: [],
			}
		},
		components: {
			share
		},
		onLoad: function(option) { //option为object类型，会序列化上个页面传递的参数
			// console.log(option); //打印出上个页面传递的参数。
			// console.log(option.score); //打印出上个页面传递的参数。
			// console.log(option.list); //打印出上个页面传递的参数。
			this.score = option.score
			this.list = JSON.parse(option.list);
			if (this.score <= 1) {
				this.level = 1
			} else if (this.score <= 4) {
				this.level = 2
			} else if (this.score <= 7) {
				this.level = 3
			} else if (this.score <= 9) {
				this.level = 4
			} else {
				this.level = 5;
			}
			this.popupShow = true;
		},
		methods: {
			
			hidePopup() {
				this.popupShow = false;
			},
			
			goOnChallenge() {
				uni.reLaunch({
					url: '/pages/challenge/challenge'
				});
			}
		}
	}
</script>

<style>
	@import url("challengeResult.css");
</style>
