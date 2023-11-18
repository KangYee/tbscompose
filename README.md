# TbsCompose

![\$latestVersion](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1c6d5326ecc049e399180bd2d10acdc8~tplv-k3u1fbpfcp-image.image#?w=150\&h=20\&s=1172\&e=svg\&b=565656)

TBS（腾讯浏览服务）的 Jetpack Compose 封装。无可用 X5 内核时自动切换为系统 WebView。

# 前言

[Accompanist Web](https://google.github.io/accompanist/web/) 已经被官方弃用，为了方便后续使用，就直接 fork 了官方项目，并在基础上修改并集成了 [TBS](https://x5.tencent.com/)

需要注意的是，TBS 初始化和配置等功能（[官方文档](https://x5.tencent.com/docs/access.html)），需使用者自行实现，本库只是做了封装操作

# 集成

```
dependencies {
    implementation 'io.github.kangyee:tbscompose:$latestVersion'
}
```

# 使用
```
TbsWebView(...)
```

具体可参考 example 中的 BasicExample、SaveStateExample 和 WrappedContentExample
