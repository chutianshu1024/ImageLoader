package com.cts.demo

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser
import com.bumptech.glide.request.RequestOptions
import com.cts.imageloader.ImageLoader
import com.cts.imageloader.ScaleType
import com.cts.imageloader.ext.loadCircle
import com.cts.imageloader.ext.loadRoundedCorner
import com.cts.imageloader.ext.loadUrl
import com.cts.imageloader.ext.loadWebP


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        http://static.melo-development.svc.cluster.local/imgs/images/bag/8001.webp
//        var url = "http://static.melo-development.svc.cluster.local/imgs/images/bag/8001.webp"
        var url = "https://p.upyun.com/demo/webp/webp/gif-0.webp"

        var urlImg =
            "https://raw.githubusercontent.com/chutianshu1024/ImageLoader/master/gif/karl-bewick-OuwIYzmmz0Y-unsplash.jpg"

        //扩展函数，参数可选，用到哪个加哪个
//        findViewById<ImageView>(R.id.iv1).loadUrl("https://alifei04.cfp.cn/creative/vcg/800/new/VCG41N1348844060.jpg")
//        findViewById<ImageView>(R.id.iv1).loadUrl("https://isparta.github.io/compare-webp/image/png_webp/webp_lossless/4.webp")
//        findViewById<ImageView>(R.id.iv1).loadUrl(url)

//        Glide.with(this@MainActivity)
//            .asDrawable()
//            .load(url)
//            .apply(RequestOptions().centerCrop())
//            .into(findViewById(R.id.iv1))

        findViewById<ImageView>(R.id.iv1).loadUrl(url)

        findViewById<ImageView>(R.id.iv2).loadCircle(urlImg)

        findViewById<ImageView>(R.id.iv3).loadWebP(
            urlImg, 20
        )
        //本地动态webp
        findViewById<ImageView>(R.id.iv4).loadWebP(R.drawable.animate, roundedCorners = 200)

        //网络动态webp，添加圆角，添加CENTER_CROP
        findViewById<ImageView>(R.id.iv5).loadRoundedCorner(
            "https://raw.githubusercontent.com/qq2225936589/ImageDemos/master/demo01.webp",
            roundedCorners = 20,
            scaleType = ScaleType.CENTER_CROP
        )

        //网络动态webp，添加圆角，添加CENTER_CROP，仅循环一次
        findViewById<ImageView>(R.id.iv6).loadWebP(
            "https://raw.githubusercontent.com/qq2225936589/ImageDemos/master/demo01.webp?sdssgsdgs",
            1,
            roundedCorners = 20,
            scaleType = ScaleType.CENTER_CROP
        )

        //高斯模糊，（能不用就别用，贼耗性能）
        findViewById<ImageView>(R.id.iv7).loadUrl(
            urlImg, blurSampling = 2
        )

        //普通用法，跟上面基本一样，java也用这种调用方式
        ImageLoader().loadUrl(
            findViewById(R.id.iv8), urlImg
        )

        //圆形图
        ImageLoader().loadCircle(
            findViewById(R.id.iv9), urlImg
        )

        //测试bug
        ImageLoader().loadRoundedCorner(
            findViewById(R.id.iv10), url, 20
        )
    }
}