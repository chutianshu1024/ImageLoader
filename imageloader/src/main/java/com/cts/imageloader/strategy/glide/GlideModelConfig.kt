package com.cts.imageloader.strategy.glide

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * @Description: glide全局配置
 * @Author: CTS
 * @Date: 2020/12/22 15:04
 * @Note: Glide4.11.0支持加载http和https，所以证书信任策略不用再设置了
 *        而且这里缓存相关也并没有配置，只是预留一下
 *        如果自定义的话添加：
 *        @Excludes({com.cts.imageloader.strategy.glide.GlideModule})
 */
@GlideModule
class GlideModelConfig : AppGlideModule() {
//    // 图片缓存最大容量，150M，根据自己的需求进行修改
//    val GLIDE_CATCH_SIZE = 350 * 1000 * 1000
//
//    // 图片缓存子目录
//    val GLIDE_CARCH_DIR = "image_catch"
//    var memorySize = Runtime.getRuntime().maxMemory().toInt() / 8 // 取1/8最大内存作为最大缓存
//
//    override fun applyOptions(context: Context, builder: GlideBuilder) {
////        // 定义缓存大小和位置
////        builder.setDiskCache(InternalCacheDiskCacheFactory(context, GLIDE_CARCH_DIR, GLIDE_CATCH_SIZE)) //内存中
////        //        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "cache", diskSize)); //sd卡中
////
////        // 默认内存和图片池大小
////        val calculator: MemorySizeCalculator = Builder(context)
////                .setMemoryCacheScreens(2)
////                .build()
////        val defaultMemoryCacheSize: Int = calculator.getMemoryCacheSize() // 默认内存大小
////        val defaultBitmapPoolSize: Int = calculator.getBitmapPoolSize() // 默认图片池大小
////        builder.setMemoryCache(LruResourceCache(defaultMemoryCacheSize)) // 该两句无需设置，是默认的
////        builder.setBitmapPool(LruBitmapPool(defaultBitmapPoolSize))
////
////        // 自定义内存和图片池大小
////        builder.setMemoryCache(LruResourceCache(memorySize))
////        builder.setBitmapPool(LruBitmapPool(memorySize))
////
////        // 定义图片格式
////        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
////        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565) // 默认
//    }
}