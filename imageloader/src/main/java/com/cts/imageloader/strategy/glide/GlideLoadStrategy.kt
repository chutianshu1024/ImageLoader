package com.cts.imageloader.strategy.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.annotation.RawRes
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.webp.decoder.WebpDrawable
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.cts.imageloader.*
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.io.File

/**
 * @Description: Glide加载策略
 * @Author: CTS
 * @Date: 2020/12/22 14:33
 */
class GlideLoadStrategy : IImageLoader {

    override fun load(
        imageView: ImageView, any: Any?, thumbnail: Float?,
        roundedCorners: Int?, placeholder: Int?, errorResId: Int?,
        cacheType: CacheType?, scaleType: ScaleType?, format: Format?,
        loopCount: Int?, blurSampling: Int?,
    ) {
        if (imageView?.context == null) {//判空
            Log.e("ImageLoader-error", "iv或context为空")
            return
        }

        if (any == null) {//加载默认图片
            GlideApp.with(imageView.context)
                .load(R.drawable.sdkc_shape_placeholder_rec_rad3)
                .into(imageView)
            Log.e("ImageLoader-error", "图片路径为null")
            return
        }

        try {//类型校验
            when (any) {//遗留问题，RawRes判断异常，暂时直接加载
                is String -> {
                    //！！！！！注意注意注意注意，这里imageView.context不要改成imageView，会出很多奇奇怪怪的SIGABRT异常！！！！！！
                    GlideApp.with(imageView.context).load(any)
                }
                is File -> {
                    GlideApp.with(imageView.context).load(any)
                }
                is Drawable -> {
                    GlideApp.with(imageView.context).load(any)
                }
                is RawRes -> {
                    GlideApp.with(imageView.context).load(any)
                }
                is Uri -> {
                    GlideApp.with(imageView.context).load(any)
                }
                is Bitmap -> {
                    GlideApp.with(imageView.context).load(any)
                }
                else -> {
                    GlideApp.with(imageView.context).load(any)
                    //遗留问题，RawRes判断异常，暂时直接加载
                    //                Logger.d("不支持的类型")
                    //                null
                }
            }?.apply {
                //缩略图
                if (thumbnail != null) {
                    thumbnail(thumbnail)
                }

                //占位图片
                if (placeholder != null) {
                    placeholder(placeholder)
                }

                //失败图片
                if (errorResId != null) {
                    error(errorResId)
                }

                //缓存策略
                when (cacheType) {
                    CacheType.LOCAL_ONLY -> {//仅本地缓存
                        skipMemoryCache(true)
                        diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    }
                    CacheType.MEMORY_ONLY -> {//仅内存缓存
                        skipMemoryCache(false)
                        diskCacheStrategy(DiskCacheStrategy.NONE)
                    }
                    CacheType.LOCAL2MEMORY -> {//内存和本地都缓存
                        skipMemoryCache(false)
                        diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    }
                }

                //格式不同，需要设置不同转码器（并且如果需要圆角之类的效果的话需要在转码器上进行配置，单独配置无效）
                when (format) {
                    Format.IMAGE -> {
                        if (blurSampling ?: 0 > 0) {//高斯模糊
                            //圆角
                            if (roundedCorners != null && roundedCorners > 0) {
                                //带圆角
                                apply(
                                    RequestOptions().transform(
                                        getScaleType(scaleType),
                                        BlurTransformation(25, blurSampling ?: 0),
                                        RoundedCorners(roundedCorners)
                                    )
                                )
                            } else if (roundedCorners != null && roundedCorners == -1) {
                                //规定的-1是圆形
                                apply(
                                    RequestOptions().transform(
                                        CircleCrop(),
                                        BlurTransformation(25, blurSampling ?: 0)
                                    )
                                )
                            } else {
                                //默认是不带圆角的
                                apply(
                                    RequestOptions().transform(
                                        getScaleType(scaleType),
                                        BlurTransformation(25, blurSampling ?: 0)
                                    )
                                )
                            }
                        } else {
                            //圆角
                            if (roundedCorners != null && roundedCorners > 0) {
                                //带圆角
                                apply(
                                    RequestOptions().transform(
                                        getScaleType(scaleType), RoundedCorners(roundedCorners)
                                    )
                                )
                            } else if (roundedCorners != null && roundedCorners == -1) {
                                //规定的-1是圆形
                                apply(
                                    RequestOptions().transform(CircleCrop())
                                )
                            } else {
                                //默认是不带圆角的
                                apply(
                                    RequestOptions().transform(getScaleType(scaleType))
                                )
                            }
                        }
                    }
                    Format.WEBP -> {
                        //如果直接用glide内置的Transformation，则不能设置ScaleType和圆角，抽时间研究下
                        val multiTransformation: Transformation<Bitmap> =
                            if (roundedCorners != null && roundedCorners > 0) {
                                //带圆角
                                MultiTransformation(
                                    getScaleType(scaleType),
                                    RoundedCorners(roundedCorners)
                                )
                            } else if (roundedCorners != null && roundedCorners == -1) {
                                //规定的-1是圆形
                                MultiTransformation(getScaleType(ScaleType.CIRCLE_CROP))
                                MultiTransformation(CircleCrop())
                            } else {
                                //默认是不带圆角的
                                MultiTransformation(getScaleType(scaleType))
                            }

                        //设置转码器
                        optionalTransform(
                            WebpDrawable::class.java,
                            WebpDrawableTransformation(multiTransformation)
                        )
                    }
                    else -> {
                        Log.e("ImageLoader-error", "不支持的格式")
                    }
                }

                if (loopCount ?: 0 > 0) {
                    addListener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            val webp = resource as WebpDrawable
                            webp.loopCount = loopCount ?: 1
                            webp.setVisible(true, true)
                            return false
                        }

                    })

//                    //如果需要重复播放，一般要移除内存缓存，否则第二次不会重新播放
//                    skipMemoryCache(true)
                } else {

                }

            }?.into(imageView)
        } catch (e: Exception) {
            Log.e("ImageLoader-error", e.message ?: "")
        }

    }

    //获取图片drawable
    override fun getDrawable(
        context: Context,
        url: String,
        iDrawableTarget: IDrawableTarget<Drawable>
    ) {
        try {
            GlideApp.with(context)
                .asDrawable()
                .load(url)
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        iDrawableTarget.onResourceReady(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        iDrawableTarget.onLoadCleared(placeholder)
                    }
                })
        } catch (e: Exception) {
            Log.e("ImageLoader-error", e.message ?: "")
        }
    }

    //获取Glide的BitmapTransformation，默认为 CenterCrop()
    private fun getScaleType(scaleType: ScaleType?): BitmapTransformation = when (scaleType) {
        ScaleType.CENTER_CROP -> {
            CenterCrop()
        }
        ScaleType.CENTER_INSIDE -> {
            CenterInside()
        }
        ScaleType.CIRCLE_CROP -> {
            CircleCrop()
        }
        ScaleType.FIT_CENTER -> {
            FitCenter()
        }
        else -> {
            CenterCrop()
        }
    }
}