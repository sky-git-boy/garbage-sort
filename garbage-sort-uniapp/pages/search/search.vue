<template>
	<view class="content">

		<view class="form-view">
			<form @submit="formSubmit" @reset="formReset" class="form-form">
				<view class="input-view">
					<view @click="takePhoto" class="input-view-item input-view-camera">
						<image class="search-img" src="../../static/icos/camera.png"></image>
					</view>
					<view @click="readyRecord" class="input-view-item input-view-speech">
						<image class="search-img" src="../../static/icos/record.png"></image>
					</view>
					<view class="input-view-item input-view-search">
						<input confirm-type="search" :placeholder="defaultKeyword" @search="doSearch(false)"
							@input="inputChange" @confirm="doSearch(false)" v-model="keyword" class="input-search"
							name="input" placeholder="输入搜索关键词" />
					</view>
					<view class=" font-search" @click="doSearch(false)">查询</view>
				</view>
			</form>
		</view>

		<view class="search-keyword" @touchstart="blur">
			<scroll-view class="keyword-list-box" v-show="isShowKeywordList" scroll-y>
				<view class="keyword-entry" @tap="showDetail(row)" hover-class="keyword-entry-tap"
					v-for="row in keywordList" :key="row.keyword">
					<view class="keyword-text" @tap="doSearch(row.keyword)">
						<rich-text :nodes="row.htmlStr"></rich-text>
					</view>
					<view class="">
						<view v-if="row.garbageType==1" class="keyword-type garbage-gan">干垃圾</view>
						<view v-else-if="row.garbageType==2" class="keyword-type garbage-shi">湿垃圾</view>
						<view v-else-if="row.garbageType==3" class="keyword-type garbage-huishou">可回收物</view>
						<view v-else-if="row.garbageType==4" class="keyword-type garbage-youhai">有害垃圾</view>
					</view>
					<view class="keyword-img" @tap="setkeyword(row)">
						<image src="/static/HM-search/back.png"></image>
					</view>
				</view>
			</scroll-view>
			<scroll-view class="keyword-box" v-show="!isShowKeywordList" scroll-y>
				<view class="keyword-block" v-if="oldKeywordList.length>0">
					<view class="keyword-list-header">
						<view>历史搜索</view>
						<view>
							<image @tap="oldDelete" src="/static/HM-search/delete.png"></image>
						</view>
					</view>
					<view class="keyword">
						<view v-for="(keyword,index) in oldKeywordList" @tap="doSearch(keyword)" :key="index">
							{{keyword}}
						</view>
					</view>
				</view>
				<view class="keyword-block">
					<view class="keyword-list-header">
						<view>热门搜索</view>
						<view>
							<image @tap="hotToggle" :src="'/static/HM-search/attention'+forbid+'.png'"></image>
						</view>
					</view>
					<view class="keyword" v-if="forbid==''">
						<view v-for="(keyword,index) in hotKeywordList" @tap="doSearch(keyword)" :key="index">
							<view class="" v-if="index<3">
								<image class="image-hot" src="../../static/icos/hot.png" mode=""></image>
							</view>
							<view class="">{{keyword}}</view>
						</view>
					</view>
					<view class="hide-hot-tis" v-else>
						<view>当前搜热门搜索已隐藏</view>
					</view>
				</view>
			</scroll-view>
		</view>

		<!-- popup start -->
		<view class="">
			<uni-popup :show="popupShow" position="bottom" @hidePopup="hidePopup">
				<view class="view-popup">
					<view class="recording-title">按住 说话</view>
					<view class="recording-box">
						<canvas id="canvas" canvas-id="canvas">
							<view :animation="animationData" class="recording-button" @touchstart="start"
								@touchend="end"></view>
						</canvas>
					</view>
					<button type="primary" @click="playVoice">播放</button>
				</view>
			</uni-popup>
		</view>
		<!-- popup end -->

		<view class="">
			<my-popup :show="detailPopupShow" :detail="detailShowObject" @hideMypopup="hideMypopup"></my-popup>
		</view>

		<uni-popup :show="imagesResultShow" position="middle" mode="fixed" @hidePopup="hideUnipPpup">
			<view class="imagePopup">
				<view class="image-left">
					<image class="tempImage" :src="imagePath"></image>
				</view>
				<view class="image-right">
					<view class="image-close">
						<view class="">无法精准识别,请选择最接近的</view>
					</view>
					<view class="">
						<view v-for="(item, index) in imageResults" :key="index" class="list-item"
							@tap="tapKeyword(item.keyword)">
							<view class="item-keyword"> {{item.keyword}}</view>
							<view class="item-pi">
								<view class="">匹配度</view>
								<view class=""> {{Math.floor(item.score*100)}}% > </view>
							</view>
						</view>
					</view>
				</view>
			</view>
			<view class="">
				<button class="image-button" type="primary" @tap="hideUnipPpup">返回</button>
			</view>
		</uni-popup>

		<share />

	</view>
</template>

<script>
	import myPopup from "@/components/myPopup.vue"
	import share from "@/components/share.vue"

	const recorderManager = uni.getRecorderManager();
	const innerAudioContext = uni.createInnerAudioContext();

	innerAudioContext.autoplay = true;

	export default {
		components: {
			myPopup,
			share
		},
		data() {
			return {
				defaultKeyword: "",
				keyword: "",
				oldKeywordList: [],
				hotKeywordList: [],
				keywordList: [],
				forbid: '',
				isShowKeywordList: false,
				imagePath: "",

				// 录音相关的 值	start
				popupShow: false,
				voicePath: '',
				startTiming: false,
				drawTiming: false,
				timeoutTiming: false,
				animaTiming1: false,
				animaTiming2: false,
				animationData: {},
				maxTime: 5000,
				frame: 50,
				// 录音相关的 值	end

				// image 相关的值 start
				possible: [],
				reference: null,
				results: [],
				// imageResults: [],
				imageResults: [{
					"score": 0.944264,
					"root": "商品-箱包",
					"keyword": "手提袋"
				}, {
					"score": 0.799999,
					"root": "Logo",
					"keyword": "85度c"
				}, {
					"score": 0.589144,
					"root": "商品-箱包",
					"keyword": "购物袋"
				}, {
					"score": 0.227161,
					"root": "商品-箱包",
					"keyword": "塑料购物袋"
				}, {
					"score": 0.018175,
					"root": "商品-箱包",
					"keyword": "宣传袋"
				}],

				imagesResultShow: false,
				replyWord: [
					"很抱歉,未能匹配到,将会尽快完善",
					"未能匹配到结果, 试试其他的吧",
					"抱歉，未能给您提供合适的结果",
					"请尝试换一个搜索关键词试试看吧",
				],

				// image 相关的值 end
				detailPopupShow: false,
				detailShowObject: null,
			}
		},

		onShow() {
			// 热门搜索
			this.loadHotKeyword();
			// 历史搜索
			this.loadOldKeyword();
		},

		onLoad(option) {
			let me = this;
			if (option.type == 1) { // 图像识别
				me.takePhoto();
			} else if (option.type == 2) { // 语音识别
				me.readyRecord();
			} else if (option.type == 3) { // 文字搜索
				if (option.keyword) { // 如果有关键字
					me.keyword = option.keyword
					if (option.uni != "null") {
						let uniOne = JSON.parse(option.uni);
						me.detailPopupShow = true;
						me.detailShowObject = {
							keyword: uniOne.garbageName,
							garbageType: uniOne.garbageType,
							remark: uniOne.remark,
						}
						me.saveKeyword(uniOne.garbageName);
					}
					if (option.keywordList != "[]") { // 如果关键字列表不为空
						let list = JSON.parse(option.keywordList);
						me.isShowKeywordList = true;
						me.keywordList = me.drawCorrelativeKeyword(JSON.parse(option.keywordList), me.keyword)
					}
					if (me.keywordList == 0 && !me.detailPopupShow) {
						me.noTitlemodalTap();
					}
				}
			}

			me.init();

			// 录音结束的事件监听
			recorderManager.onStop(function(res) {
				console.log("onstop")
				uni.showLoading({
					title: '正在努力识别中...'
				});
				console.log('recorder stop' + JSON.stringify(res));
				me.voicePath = res.tempFilePath;
				console.log(me.voicePath)
				me.popupShow = false;

				uni.uploadFile({
					url: me.serverUrl + '/baidu/record',
					filePath: me.voicePath,
					name: 'file',
					success: (uploadFileRes) => {
						let res = JSON.parse(uploadFileRes.data);
						let data = res.data;
						let uniOne = data.uni;
						me.keyword = data.keyword;
						if (data.results != null) {
							me.isShowKeywordList = true;
							me.keywordList = me.drawCorrelativeKeyword(data.results, me.keyword);
						}
						if (uniOne != null) {
							me.detailPopupShow = true;
							me.detailShowObject = {
								keyword: uniOne.garbageName,
								garbageType: uniOne.garbageType,
								remark: uniOne.remark,
							}
						}
						if (me.keywordList == 0 && !me.detailPopupShow) {
							me.noTitlemodalTap();
						}
					},
					complete() {
						uni.hideLoading();
					}
				});
			})
		},

		methods: {

			tapKeyword(keyword) {
				this.keyword = keyword;
				this.doSearch();
				this.imagesResultShow = false;
			},

			noTitlemodalTap() {
				uni.showModal({
					title: "未能匹配成功",
					content: this.replyWord[Math.round(Math.random() * 5)],
					confirmText: "知道了",
					showCancel: false,
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				})
			},

			showDetail(row) {
				this.detailShowObject = row;
				this.detailPopupShow = true;
			},

			hideMypopup() {
				this.detailPopupShow = false;
			},

			hideUnipPpup() {
				this.imagesResultShow = false;
			},

			readyRecord() {
				this.popupShow = true;
			},

			start() {
				// this.get_record_auth();
				this.recording();
				// this.startTiming = setTimeout(this.recording, 10);
			},

			end() {
				recorderManager.stop();
				clearInterval(this.animaTiming1);
				clearInterval(this.animaTiming2);
				this.animaTiming1 = false;
				this.animaTiming2 = false;
			},
			recording() {
				const options = {
					duration: 10000,
					sampleRate: 16000,
					numberOfChannels: 1,
					encodeBitRate: 64000,
					format: 'mp3',
					frameSize: 50
				}
				let me = this;
				console.log('录音开始');
				recorderManager.start(options);

				me.animaTiming1 = setInterval(function() {
					var animation = uni.createAnimation({
						duration: 500,
						timingFunction: 'ease',
					})
					me.animation = animation
					animation.scale(1.3, 1.3).step()
					me.animationData = animation.export()
				}, 500)

				me.animaTiming2 = setInterval(function() {
					var animation = uni.createAnimation({
						duration: 500,
						timingFunction: 'ease',
					})
					me.animation = animation
					animation.scale(1, 1).step()
					me.animationData = animation.export()
				}, 1000)
			},

			playVoice() {
				let me = this;
				if (me.voicePath) {
					innerAudioContext.src = me.voicePath;
					innerAudioContext.play();
					// console.log("播放了声音")
				}
			},
			
			hidePopup() {
				this.popupShow = false;
			},

			takePhoto() {
				let me = this;
				uni.chooseImage({
					count: 1,
					success: res => {
						uni.showLoading({
							title: '正在努力识别中...'
						});
						me.imagePath = res.tempFilePaths[0];
						console.log(me.imagePath)
						uni.uploadFile({
							url: me.serverUrl + '/baidu/image',
							filePath: res.tempFilePaths[0],
							name: 'file',
							success: (uploadFileRes) => {
								let res = JSON.parse(uploadFileRes.data);
								let data = res.data;
								let response = JSON.parse(data.response);
								let uniOne = data.uni;
								me.keyword = data.keyword;

								me.keywordList = me.drawCorrelativeKeyword(data.results, me.keyword);
								if (uniOne) { // 查找到的唯一值不等于空
									me.isShowKeywordList = true;
									me.detailPopupShow = true;

									me.detailShowObject = {
										keyword: uniOne.garbageName,
										garbageType: uniOne.garbageType,
										remark: uniOne.remark,
									}
								} else if (me.keywordList.length > 0) {
									me.isShowKeywordList = true;

								} else {
									me.imageResults = response.result;
									me.imagesResultShow = true;
								}

								/* if (me.keywordList == 0 && !me.detailPopupShow) {
									me.noTitlemodalTap();
								} */

							},
							complete() {
								uni.hideLoading();
							}
						});
					}
				});
			},

			init() {
				this.loadDefaultKeyword();
				this.loadOldKeyword();
				this.loadHotKeyword();
			},

			blur() {
				uni.hideKeyboard()
			},

			//加载默认搜索关键字
			loadDefaultKeyword() {
				//定义默认搜索关键字
				this.defaultKeyword = "苹果";
			},

			//加载历史搜索,自动读取本地Storage
			loadOldKeyword() {
				uni.getStorage({
					key: 'OldKeys',
					success: (res) => {
						var OldKeys = JSON.parse(res.data);
						this.oldKeywordList = OldKeys;
					}
				});
			},

			//加载热门搜索
			loadHotKeyword() {
				let me = this;
				me.hotKeywordList = ['键盘', '鼠标', '显示器', '电脑主机', '蓝牙音箱', '笔记本电脑', '鼠标垫', 'USB', 'USB3.0'];
				uni.request({
					url: me.serverUrl + "/search/top10",
					success(res) {
						me.hotKeywordList = res.data.data.map(item => item.keyword)
					}
				})
			},

			//监听输入
			inputChange() {
				var keyword = this.keyword;

				//兼容引入组件时传入参数情况
				setTimeout(() => {
					if (!keyword) {
						this.keywordList = [];
						this.isShowKeywordList = false;
						return;
					}
					this.isShowKeywordList = true;

					uni.request({
						url: this.serverUrl + "/search/name/" + keyword,
						success: (res) => {
							this.keywordList = this.drawCorrelativeKeyword(res.data.data, keyword);
						}
					});
				}, 300)
			},

			//高亮关键字
			drawCorrelativeKeyword(keywords, keyword) {
				var len = keywords.length,
					keywordArr = [];
				for (var i = 0; i < len; i++) {
					var row = keywords[i];
					//定义高亮#9f9f9f
					var html = row.garbageName.replace(keyword, "<span style='color: #72c69c;'>" + keyword + "</span>");
					html = '<div>' + html + '</div>';
					var tmpObj = {
						keyword: row.garbageName,
						htmlStr: html,
						garbageType: row.garbageType,
						remark: row.remark,
					};
					keywordArr.push(tmpObj)
				}
				return keywordArr;
			},

			//顶置关键字
			setkeyword(data) {
				this.keyword = data.keyword;
			},

			//清除历史搜索
			oldDelete() {
				uni.showModal({
					content: '确定清除历史搜索记录？',
					success: (res) => {
						if (res.confirm) {
							console.log('用户点击确定');
							this.oldKeywordList = [];
							uni.removeStorage({
								key: 'OldKeys'
							});
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},

			//热门搜索开关
			hotToggle() {
				this.forbid = this.forbid ? '' : '_forbid';
			},

			//执行搜索
			doSearch(key) {
				uni.showLoading({
					title: '正在努力查询中...'
				});
				key = key ? key : this.keyword ? this.keyword : this.defaultKeyword;
				this.keyword = key;
				this.saveKeyword(key); //保存为历史 
				this.inputChange();
				uni.request({
					url: this.serverUrl + "/search/uname/" + this.keyword,
					success: (res) => {
						console.log(res);
						let data = res.data.data;
						let uniOne = data.uni;
						let results = data.results;
						if (uniOne) {
							this.detailPopupShow = true;
							this.detailShowObject = {
								keyword: uniOne.garbageName,
								garbageType: uniOne.garbageType,
								remark: uniOne.remark,
							}
						}
						if (results.length > 0) {
							this.keywordList = this.drawCorrelativeKeyword(results, this.keyword);
						}
						if (this.keywordList == 0 && !this.detailPopupShow) {
							this.noTitlemodalTap();
						}
					},
					complete() {
						uni.hideLoading();
					}
				});
			},

			//保存关键字到历史记录
			saveKeyword(keyword) {
				uni.getStorage({
					key: 'OldKeys',
					success: (res) => {
						console.log(res.data);
						var OldKeys = JSON.parse(res.data);
						var findIndex = OldKeys.indexOf(keyword);
						if (findIndex == -1) {
							OldKeys.unshift(keyword);
						} else {
							OldKeys.splice(findIndex, 1);
							OldKeys.unshift(keyword);
						}
						//最多10个纪录
						OldKeys.length > 10 && OldKeys.pop();
						uni.setStorage({
							key: 'OldKeys',
							data: JSON.stringify(OldKeys)
						});
						this.oldKeywordList = OldKeys; //更新历史搜索
					},
					fail: (e) => {
						var OldKeys = [keyword];
						uni.setStorage({
							key: 'OldKeys',
							data: JSON.stringify(OldKeys)
						});
						this.oldKeywordList = OldKeys; //更新历史搜索
					}
				});
			}
		}
	}
</script>
<style>
	@import url("search.css");
</style>
