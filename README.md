# NativeNewsDemo
演示新闻客户端访问本地搭建的服务器数据

## 访问网络
android-async-http

android-async-http是一个基于Apache的HttpClient的异步的Android请求框架，所有的请求全在UI（主）线程之外执行，而callback成功失败的回调都是在主线程中执行。 

用法：compile 'com.loopj.android:android-async-http:1.4.9'


## 加载图片
SmartImageView

用法：需要下载jar包引入自己项目中


文件下载需要配置服务器，配置服务器步骤如下：

1、配置tomcat环境变量

2、在tomcat的根目录下找到bin文件夹，在该文件夹下运行startup.bat

3、在根目录下找到webapps文件夹，在该文件夹下找到ROOT文件夹，然后将img文件夹和newsInfo.xml文件复制到ROOT文件夹即可

4、需要将“http://192.168.132.63:8080/newsInfo.xml”替换成本机tomcat地址