# fzqLib
retrofit网络请求封装框架

图片拍照,相册获取封装

ZXing扫码库(已经添加权限,不需要提前申请,但是可以提前申请)

工具类的库






Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
  
		repositories {
    
			...
      
			maven { url 'https://jitpack.io' }
      
		}
    
	}
  
Step 2. Add the dependency

	dependencies {
  
	        compile 'com.github.f15997123593.fzqLib:retrofitmanager:v3.1'
    	compile 'com.github.f15997123593.fzqLib:takephoto_library:v3.1'
    	compile 'com.github.f15997123593.fzqLib:zxinglib:v3.1'
    	compile 'com.github.f15997123593.fzqLib:utillib:v3.1'
          
	}
