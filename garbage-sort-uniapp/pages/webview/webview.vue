<template>
	<view>
		<web-view :webview-styles="webviewStyles" :src="url"></web-view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				url:"",
				webviewStyles: {
					progress: {
						color: '#FF3333'
					}
				}
			}
		},
		onLoad: function(option) { //option为object类型，会序列化上个页面传递的参数
			console.log(option.url); //打印出上个页面传递的参数。
			this.url=option.url;
		},
		methods: {

		}
	}
</script>

<style>

</style>
