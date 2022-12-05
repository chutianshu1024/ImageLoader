package com.cts.imageloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.cts.imageloader.strategy.glide.GlideLoadStrategy

/**
 * @Description: 图片加载器
 * @Author: CTS
 * @Date: 2020/12/22 12:32
 * @Note: 策略模式
 */
class ImageLoader(loadStrategy: LoadStrategy = LoadStrategy.GLIDE) {

    //加载器，根据策略枚举加载不同策略
    private var iImageLoader: IImageLoader

    init {
        //初始化加载策略
        when (loadStrategy) {
            LoadStrategy.GLIDE -> {
                iImageLoader = GlideLoadStrategy()
            }
        }
    }

    /**
     * 下面是开放的功能
     */
    //加载网络图片，默认
    @JvmOverloads//兼容java
    fun loadUrl(
        iv: ImageView, url: Any?, thumbnail: Float? = null,
        placeholder: Int? = null,//占位图资源id
        errorResId: Int? = null,//失败图片资源id
        cacheType: CacheType? = CacheType.LOCAL2MEMORY,//缓存方式
        scaleType: ScaleType? = ScaleType.CENTER_CROP,//显示范围类型，默认CENTER_CROP
        blurSampling: Int? = 0,//高斯模糊数值，默认为0表示不添加高斯模糊
    ) {
        iImageLoader.load(
            imageView = iv,
            any = url,
            thumbnail = thumbnail,
            placeholder = placeholder,
            errorResId = errorResId,
            cacheType = cacheType,
            scaleType = scaleType,
            blurSampling = blurSampling
        )
    }

    //加载圆角图片
    @JvmOverloads//兼容java
    fun loadRoundedCorner(
        iv: ImageView, url: String?, roundedCorners: Int?, thumbnail: Float? = null,
        placeholder: Int? = null,//占位图资源id
        errorResId: Int? = R.drawable.sdkc_shape_placeholder_rec_rad3,//失败图片资源id
        cacheType: CacheType? = CacheType.LOCAL2MEMORY,//缓存方式
        scaleType: ScaleType? = ScaleType.CENTER_CROP,//显示范围类型，默认CENTER_CROP
        blurSampling: Int? = 0,//高斯模糊数值，默认为0表示不添加高斯模糊
    ) {
        iImageLoader.load(
            imageView = iv, any = url, roundedCorners = roundedCorners,
            thumbnail = thumbnail, placeholder = placeholder, errorResId = errorResId,
            cacheType = cacheType, scaleType = scaleType, blurSampling = blurSampling
        )
    }

    //加载圆形图片
    @JvmOverloads//兼容java
    fun loadCircle(
        iv: ImageView, url: String?, thumbnail: Float? = null,
        placeholder: Int? = null,//占位图资源id
        errorResId: Int? = R.drawable.sdkc_shape_placeholder_circle,//失败图片资源id
        cacheType: CacheType? = CacheType.LOCAL2MEMORY,//缓存方式
        scaleType: ScaleType? = ScaleType.CIRCLE_CROP,//显示范围类型，默认CENTER_CROP
        blurSampling: Int? = 0,//高斯模糊数值，默认为0表示不添加高斯模糊
    ) {
        iImageLoader.load(
            imageView = iv, any = url, thumbnail = thumbnail, roundedCorners = -1,
            placeholder = placeholder, errorResId = errorResId,
            cacheType = cacheType, scaleType = scaleType, blurSampling = blurSampling
        )
    }

    /**
     * 加载webP
     * @param url 加载webP（本地url或者网络url）
     */
    @JvmOverloads//兼容java
    fun loadWebP(
        iv: ImageView, url: Any?, loopCount: Int? = 0, thumbnail: Float? = null,//缩略图比例
        roundedCorners: Int? = 0,//圆角度数,默认为0，为了兼容圆形，这里规定如果是-1则为圆形（其实这里更应该用kotlin的带有值的那个枚举类型，不过为了兼容java，先用-1）
        placeholder: Int? = null,//占位图资源id
        errorResId: Int? = R.drawable.sdkc_shape_placeholder_rec_rad3,//失败图片资源id
        cacheType: CacheType? = CacheType.LOCAL2MEMORY,//缓存方式
        scaleType: ScaleType? = ScaleType.CENTER_CROP,//显示范围类型，默认CENTER_CROP
    ) {
        iImageLoader.load(
            imageView = iv, any = url, loopCount = loopCount, thumbnail = thumbnail,
            roundedCorners = roundedCorners, placeholder = placeholder, errorResId = errorResId,
            cacheType = cacheType, scaleType = scaleType,
            format = Format.WEBP
        )
    }

    //获取drawable
    fun getDrawable(context: Context, url: String?, iDrawableTarget: IDrawableTarget<Drawable>) {
        if (url.isNullOrBlank()) {
            Log.e("ImageLoader-error", "图片路径为空")
            return
        }

        iImageLoader.getDrawable(context, url, iDrawableTarget)
    }

}

//图片加载策略枚举
enum class LoadStrategy {
    GLIDE
}
