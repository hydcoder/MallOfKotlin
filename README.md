# MallOfKotlin
项目Kotlin语言开发，以练习为目的，采用模块化结构，使用MVP架构，项目中用到了Dagger2、ARouter、RxKotlin、RxAndroid、Retrofit2等开源框架。

![image](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/module.png)

### Preview

![注册](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-173925.png)

![登录](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-173914.png)

![我的](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-171847.png)

![个人信息](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-173852.png)

![首页](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-171652.png)

![商品分类](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-171720.png)

![商品列表](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-171742.png)

![商品详情](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-171755.png)

![sku](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-171804.png)

![商品搜索](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-171827.png)

![购物车](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-171910.png)

![消息](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-171839.png)

![订单确认](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-173812.png)

![收银台](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-173827.png)

![地址管理](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-173840.png)

![订单列表](https://github.com/hydcoder/MallOfKotlin/blob/master/preview/device-2019-10-11-171859.png)



### Points

- 整个项目采用kotlin语言编写
- 使用ARouter框架进行模块间通信(页面的跳转以及服务管理)
- 使用MVP架构模式开发(Presenter直接是实现方法的,所以没有契约类Contract)
- 使用kotlin框架Kotlin-Android-Extensions、Anko
- 使用注解Dagger2,将P层注入V层,无需new,直接用对象
- 使用Rxjava进行业务数据处理
- 使用RxLifecycle对Rxjava订阅的生命周期进行管理,防止OOM
- 使用Rxbus进行组件间的通信
- 使用Glide做图片处理和加载
- 使用Retrofit网络请求框架
- 使用okhttp3对日志、请求内容类型(json)、头部信息进行配置
- 使用七牛云实现图片储存
- 使用BGA-refreshlayout实现上拉刷新、下拉加载
- 使用极光推送Jpush做消息推送服务



**Version 1.0.0**