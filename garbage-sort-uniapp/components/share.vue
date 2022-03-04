<template>
	<view>
		<view class="share-popup">
			<view class="button-view">
				<button class="text-img" open-type="share">
					<view class="buttom-view">
						<image class="share-img" src="../static/icos/share.png"></image>
						<view class="text">分享</view>
					</view>
				</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: "share",
		data() {
			return {

			};
		}
	}
</script>

<style scoped>
	.button-view{
		position: fixed;
		z-index: 999;
		bottom: 20upx;
		right: 10upx;
	}
	.share-popup {
		position: fixed;
		z-index: 999;
		display: flex;
		flex-direction: column;
	}

	.text-img {
		height: 60upx;
		background-color: #fff;
		font-size: 28upx;
		margin-bottom: 20upx;
		box-shadow: 0upx 0upx 6upx #E0E0E0;
		padding-left: 10upx;
		padding-right: 10upx;

	}

	.buttom-view {
		display: flex;
		justify-content: center;
		align-content: center;
		flex-direction: row;
	}

	.share-img {
		width: 40upx;
		height: 40upx;
		padding: 10upx;
	}

	.text {
		height: 60upx;
		line-height: 60upx;
	}
</style>
