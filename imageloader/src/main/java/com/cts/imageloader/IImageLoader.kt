package com.cts.imageloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView

/**
 * @Description: 图片加载器接口
 * @Author: CTS
 * @Date: 2020/12/21 18:15
 */
interface IImageLoader {

    /**
     * 以下为最基础方法，可添加功能。不建议修改
     */
    //包含所有功能的网络图片加载
    fun load(
        imageView: ImageView,
        any: Any?,//加载图片的类型：url、File、Bitmap等
        thumbnail: Float? = null,//缩略图比例
        roundedCorners: Int? = 0,//圆角度数,默认为0，为了兼容圆形，这里规定如果是-1则为圆形（其实这里更应该用kotlin的带有值的那个枚举类型，不过为了兼容java，先用-1）
        placeholder: Int? = null,//占位图资源id
        errorResId: Int? = R.drawable.sdkc_shape_placeholder_rec_rad3,//失败图片资源id
        cacheType: CacheType? = CacheType.LOCAL2MEMORY,//缓存方式
        scaleType: ScaleType? = ScaleType.CENTER_CROP,//显示范围类型，默认CENTER_CROP
        format: Format? = Format.IMAGE,//图片格式
        loopCount: Int? = -1,//播放次数，用于动态webp播放次数
        blurSampling: Int? = 0,//高斯模糊数值，默认为0表示不添加高斯模糊
    )

    fun getDrawable(context: Context, url: String, iDrawableTarget: IDrawableTarget<Drawable>)
}

interface IDrawableTarget<T> {
    fun onResourceReady(resource: T)

    //如果访问的缓存资源被清除，则需要将引用的地方重置。否则容易造成显示错乱或者奔溃。
    fun onLoadCleared(resource: T?)
}

enum class CacheType {
    /**
     * 三级缓存，网络层不关注，仅配置内存和本地缓存。 一般情况下磁盘缓存都是要启用的，内存缓存需要控制开关。
     * 注：其实，本地缓存还分为很多种，比如glide的DiskCacheStrategy磁盘缓存策略，但是一般情况都是默认，
     * 如果需要自定义，在这里继续添加策略就行，没必要单独再加参数区分内存和磁盘缓存了
     */
    LOCAL_ONLY,//仅本地缓存
    MEMORY_ONLY,//仅内存缓存
    LOCAL2MEMORY,//内存和本地都缓存
}

//显示范围类型
enum class ScaleType {
    CENTER_CROP,
    CENTER_INSIDE,
    FIT_CENTER,
    CIRCLE_CROP,
}

//显示格式
enum class Format {
    IMAGE,
    WEBP,
}