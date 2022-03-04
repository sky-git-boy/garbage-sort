<template>
	<view class="content">
		<view class="nav">
			<view class="nav-left">
				<scroll-view scroll-y>
					<view @tap="selectClassify(item.index)" class="nav-left-item" v-for="(item,index) in garbageSort" :class="item.active?item.class:item.classborder"
					 :key='index'>
						{{item.name}}
					</view>
				</scroll-view>
			</view>
			<view class="nav-right">
				<scroll-view scroll-y :style="'height:'+height+'px'" scroll-with-animation>
					<!-- <view class="view-img">
						<view>
							<image v-if="index!=null" class="show-img" :src="'../../static/showimg/style1-'+index+'.jpg'"></image>
						</view>
					</view> -->
					<view @tap="showDetailGarbage(item)" class="nav-right-item" v-for="(item,i) in currentDetail" :key="i" :class="i%2==0?'style1':''">
						<view>{{item.garbageName}}</view>
					</view>
				</scroll-view>
			</view>
		</view>
		<!-- <view class="">
			<my-popup :show="detailPopupShow" :detail="detailShowObject" @hideMypopup="hideMypopup"></my-popup>
		</view> -->
		<share />
	</view>
</template>

<script>
	import myPopup from "@/components/myPopup.vue"
	import share from "@/components/share.vue"

	export default {
		components: {
			myPopup,
			share
		},
		data() {
			return {
				height: 0,
				index: null,
				detailPopupShow: false,
				detailShowObject: {},
				garbageSort: [{
						index: 1,
						name: "干垃圾",
						class: 'garbage-gan',
						classborder: 'garbage-gan-border',
						active: true,
					},
					{
						index: 2,
						name: "湿垃圾",
						class: 'garbage-shi',
						classborder: 'garbage-shi-border',
						active: false,
					},
					{
						index: 3,
						name: "可回收物",
						class: 'garbage-huishou',
						classborder: 'garbage-huishou-border',
						active: false,
					},
					{
						index: 4,
						name: "有害垃圾",
						class: 'garbage-youhai',
						classborder: 'garbage-youhai-border',
						active: false,
					}
				],
				hadVisit: [],
				garbageSortDetail: [
					[],
					[],
					[],
					[],
					[]
				],
				currentDetail: [],

			}
		},

		onLoad() {
			uni.login({
				provider: 'weixin',
				success: function(loginRes) {
					console.log(loginRes)
					console.log(loginRes.authResult);
					// 获取用户信息
					uni.getUserInfo({
						provider: 'weixin',
						success: function(infoRes) {
							console.log(infoRes)
							console.log('用户昵称为：' + infoRes.userInfo.nickName);
						}
					});
				}
			});
			this.height = uni.getSystemInfoSync().windowHeight;
		},
		
		onShow() {
			this.selectClassify(getApp().globalData.typeid);
		},
		
		methods: {
			showDetailGarbage(item) {
				this.detailShowObject = {
					garbageType: item.garbageType,
					keyword: item.garbageName,
					remark: item.remark
				};
				this.detailPopupShow = true;
			},
			hideMypopup() {
				this.detailPopupShow = false;
			},
			selectClassify(index) {
				let me = this;
				if (me.index == index) return false;
				getApp().globalData.typeid = index
				me.index = index;
				// console.log("choose index"+index);
				me.garbageSort.forEach(item => {
					if (item.index != index) {
						item.active = false;
					} else {
						item.active = true;
					}

				});
				console.log(me.hadVisit.indexOf(me.index))
				if (me.hadVisit.indexOf(me.index) != -1) {
					console.log("加载缓存中的数据")
					me.currentDetail = me.garbageSortDetail[index];
					return;
				}
				me.hadVisit.push(me.index);

				//  根据 id 去后台获取接口信息； 判断一下id 是否有变化；无变化则不改变
				uni.request({
					url: me.serverUrl + `/qb/type/${index}`,
					success: (res) => {
						console.log(res)
						// console.log(res.data.data);
						me.garbageSortDetail[index] = res.data.data;
						me.currentDetail = me.garbageSortDetail[index];
						// console.log(me.garbageSortDetail[1])
					}
				});
			}
		}
	}
</script>

<style>
	@import url("type.css");
</style>
