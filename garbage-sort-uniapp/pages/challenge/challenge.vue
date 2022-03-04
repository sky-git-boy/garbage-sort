<template>
	<view class="content" v-if="controlShow">
		<view :animation="animationData" class="main-panel">
			<view class="quesion-panel">
				<view class="quesion-panel-item1">
					<view class="quesion-panel-text">{{current+1}}. </view>
					<view class="quesion-panel-text">
						{{questionBanks.length>0?questionBanks[current].garbageName:"干电池"}}</view>
				</view>
			</view>
			<view class="exam_xuan">
				<view @click="switchTabToClassify(1)">
					<button over-class="exam_item_clover" class="exam_item">干垃圾</button>
					<!-- <image class="main-img" src="../../static/icos/ico-1.jpg"></image> -->
				</view>
				<view @click="switchTabToClassify(2)">
					<button over-class="exam_item_clover" class="exam_item">湿垃圾</button>
				</view>
				<view @click="switchTabToClassify(3)">
					<button over-class="exam_item_clover" class="exam_item">可回收垃圾</button>
				</view>
				<view @click="switchTabToClassify(4)">
					<button over-class="exam_item_clover" class="exam_item">有害垃圾</button>
				</view>
			</view>
			<view class="top-text">
				<view class="">{{current+1}}</view>
				<view class="gary"> / 10</view>
			</view>
		</view>
		<!-- 分享控件 -->
		<share />
	</view>
</template>

<script>
	import share from "@/components/share.vue"

	export default {
		components: {
			share,
		},

		data() {
			return {
				questionBanks: [],
				score: 0,
				current: 0,
				controlShow: true,
				animationData: {},
			}
		},

		onShow() {
			let map = new Map();
			for (let i = 0; i < 10000; i++) {
				let temp = Math.round(Math.random() * 10);
				if (map.has(temp)) {
					map.set(temp, (map.get(temp)) + 1);
				} else {
					map.set(temp, 1);
				}
			}
			console.log("size:" + map.size)
			uni.login({
				provider: 'weixin',
				success: function(loginRes) {
					console.log("loginRes" + loginRes);
					// 获取用户信息
					uni.getUserInfo({
						provider: 'weixin',
						success: function(infoRes) {
							console.log(infoRes);
							console.log('用户昵称为：' + infoRes.userInfo.nickName);
						}
					});
				}
			});
			this.randTen();
		},
		methods: {
			donghua() {
				var animation = uni.createAnimation({
					duration: 1000,
					timingFunction: 'ease',
				})
				this.animation = animation
				animation.opacity(0).translate(-300).step()
				this.animationData = animation.export()
				setTimeout(function() {
					animation.translate(10).opacity(0.5).step({
						duration: 0
					})
					this.animationData = animation.export()
				}.bind(this), 400);
				setTimeout(function() {
					animation.translate(0).opacity(1).step({
						duration: 800
					})
					this.animationData = animation.export()
					console.log("1000")
				}.bind(this), 500);
			},

			randTen() {
				if (this.questionBanks.length > 0 && this.current < 9) {
					return false;
				}
				this.current = 0;
				this.score = 0;
				this.controlShow = true;
				this.questionBanks = [];
				uni.request({
					url: this.serverUrl + '/qb/randNum?num=10',
					success: (res) => {
						this.questionBanks = res.data.data;
					}
				});
			},

			switchTabToClassify(index) {
				if (this.current == 9) this.controlShow = false;
				this.questionBanks[this.current]['selectedType'] = index;
				// 回答正确
				if (this.questionBanks[this.current].garbageType == index) {
					this.donghua();
					let me = this;
					setTimeout(function() {
						me.score++;
						me.current++;
						me.gotoNav();
					}, 400);
				} else { // 回答错误
					// let obj = this.questionBanks[this.current];
					// let type = obj.garbageType == 1 ? '干垃圾' : obj.garbageType == 2 ? '湿垃圾' : obj.garbageType == 3 ? '可回收物' : '有害垃圾';
					// let temp = obj.garbageName + " 属于 " + type;
					let me = this;
					this.donghua();
					setTimeout(function() {
						me.current++;
						me.gotoNav();
					}, 400);
					/* uni.showModal({
						title: "选错啦",
						content: temp,
						confirmText: "知道了",
						showCancel: false,
						success: function(res) {
							me.donghua();
							setTimeout(function() {
								me.current++;
								me.gotoNav();
							}, 400);
						}
					}) */
				}
			},
			gotoNav() {
				if (this.current >= 10) {
					let list = JSON.stringify(this.questionBanks);
					let score = this.score;
					uni.request({
						url: this.serverUrl + '/challenge',
						data: {
							"score": score,
							"userName": "SKY",
							"list": this.questionBanks
						},
						header: {
							'content-type': "application/json"
						},
						method: "POST",
						success: (res) => {
							console.log(res)
						}
					});
					uni.navigateTo({
						url: `/pages/challenge/challengeResult?score=${score}&list=${list}`
					});
				}
			}
		}
	}
</script>

<style>
	@import url("challenge.css");
</style>
