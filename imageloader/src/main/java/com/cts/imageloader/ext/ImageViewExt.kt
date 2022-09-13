package com.cts.imageloader.ext

import android.widget.ImageView
import com.cts.imageloader.CacheType
import com.cts.imageloader.ImageLoader
import com.cts.imageloader.R
import com.cts.imageloader.ScaleType

/**
 * @Description: 扩展函数
 * @Author: CTS
 * @Date: 2020/12/22 18:44
 */
//因为加载策略其实一个app内都不会再变了，所以直接用一个ImageLoader也可以
var imageLoader = ImageLoader()

inline fun ImageView.loadUrl(
    url: Any?,
    thumbnail: Float? = null,
    placeholder: Int? = null,//占位图资源id
    errorResId: Int? = null,//失败图片资源id
    cacheType: CacheType? = CacheType.LOCAL2MEMORY,//缓存方式
    scaleType: ScaleType? = ScaleType.CENTER_CROP,//显示范围类型，默认CENTER_CROP
    blurSampling: Int? = 0,//高斯模糊数值，默认为0表示不添加高斯模糊
) {
    imageLoader.loadUrl(
        this,
        url,
        thumbnail = thumbnail,
        placeholder = placeholder,
        errorResId = errorResId,
        cacheType = cacheType,
        scaleType = scaleType,
        blurSampling = blurSampling
    )
}

//加载圆角图片
inline fun ImageView.loadRoundedCorner(
    url: String?, roundedCorners: Int?, thumbnail: Float? = null,
    placeholder: Int? = null,//占位图资源id
    errorResId: Int? = R.drawable.sdkc_shape_placeholder_rec_rad3,//失败图片资源id
    cacheType: CacheType? = CacheType.LOCAL2MEMORY,//缓存方式
    scaleType: ScaleType? = ScaleType.CENTER_CROP,//显示范围类型，默认CENTER_CROP
    blurSampling: Int? = 0,//高斯模糊数值，默认为0表示不添加高斯模糊
) {
    imageLoader.loadRoundedCorner(
        this, url, roundedCorners = roundedCorners,
        thumbnail = thumbnail, placeholder = placeholder, errorResId = errorResId,
        cacheType = cacheType, scaleType = scaleType, blurSampling = blurSampling
    )
}

//加载圆形图片
inline fun ImageView.loadCircle(
    url: String?, thumbnail: Float? = null,
    placeholder: Int? = R.drawable.sdkc_shape_placeholder_circle,//占位图资源id
    errorResId: Int? = R.drawable.sdkc_shape_placeholder_circle,//失败图片资源id
    cacheType: CacheType? = CacheType.LOCAL2MEMORY,//缓存方式
    scaleType: ScaleType? = ScaleType.CIRCLE_CROP,//显示范围类型，默认CENTER_CROP
    blurSampling: Int? = 0,//高斯模糊数值，默认为0表示不添加高斯模糊
) {
    imageLoader.loadCircle(
        this, url, thumbnail = thumbnail,
        placeholder = placeholder, errorResId = errorResId,
        cacheType = cacheType, scaleType = scaleType, blurSampling = blurSampling
    )
}

//加载webP
inline fun ImageView.loadWebP(
    url: Any?, loopCount: Int? = 0, thumbnail: Float? = null,//缩略图比例
    roundedCorners: Int? = 0,//圆角度数,默认为0，为了兼容圆形，这里规定如果是-1则为圆形（其实这里更应该用kotlin的带有值的那个枚举类型，不过为了兼容java，先用-1）
    placeholder: Int? = null,//占位图资源id
    errorResId: Int? = R.drawable.sdkc_shape_placeholder_rec_rad3,//失败图片资源id
    cacheType: CacheType? = CacheType.LOCAL2MEMORY,//缓存方式
    scaleType: ScaleType? = ScaleType.CENTER_CROP,//显示范围类型，默认CENTER_CROP
) {
    imageLoader.loadWebP(
        this, url, loopCount = loopCount, thumbnail = thumbnail, roundedCorners = roundedCorners,
        placeholder = placeholder, errorResId = errorResId, cacheType = cacheType,
        scaleType = scaleType
    )
}