# ImageLoader

**一个简洁易用的图片加载库，基本满足所有常用的图片加载相关功能。  
使用kotlin编写，添加了扩展函数，kotlin使用更酸爽。  
现在加载策略用的是Glide，可自行扩展。（喜欢的可以Star一下，或者感兴趣的大家一起维护。 =.=。）**  

<br /><br />

## 效果图

![效果图](https://github.com/chutianshu1024/ImageLoader/blob/master/gif/SVID_20220209_104550_1.gif)

<br />

## 详细讲解
编写中...

<br />

## 支持功能  
1、Glide常规支持内容（加载本地图片、网络图片等）  
2、圆角，圆形图片  
3、动态webp  
4、高斯模糊  
5、其他    

<br />

## 导入方式
项目根目录build.gradle添加
``` javascript
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```

项目的build.gradle添加
``` javascript
	dependencies {
	        implementation 'com.github.chutianshu1024:ImageLoader:1.4'
	}
```

<br />

## 主要功能及参数
核心功能和参数：
``` javascript
interface IImageLoader {

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
```
<br />

## 使用
### 1.kotlin扩展函数使用方法（推荐）

```

		//加载图片（本地图/网络图皆可，路径必传，其他参数可选）
		imageview.loadUrl(...)
		
		//加载圆型图片（本地图/网络图皆可，路径必传，其他参数可选）
		imageview.loadCircle(...)
		
		//加载圆角图片（本地图/网络图皆可，路径、圆角度数必传，其他参数可选）
		imageview.loadRoundedCorner(...)
		
		//加载动态webp，可设置播放循环次数（本地图/网络图皆可，路径必传，其他参数可选）
		imageview.loadWebP(...)
		
		//高斯模糊，上面方法除了loadWebP()之外都支持配置高斯模糊，例如：
		imageview.loadUrl("图片url" , blurSampling = 2)
        
```

<br />

### 2.通用调用方法，java和kotlin皆可

``` 
		//通用用法，跟上面基本一样，只不过要把imageview传进去，不赘述
        	ImageLoader().loadUrl(imageview , "图片url")
		
		ImageLoader().loadCircle(imageview , ...)
		
		ImageLoader().loadRoundedCorner(imageview , ...)
		
		ImageLoader().loadWebP(imageview , ...)
```

<br />

**注：虽然方法是kotlin写的，但是都添加了@JvmOverloads注解生成了java多参方法，所以java也可以像kotlin一样，需要几个参数就传几个就行**

<br />
