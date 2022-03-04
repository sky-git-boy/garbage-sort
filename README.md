1. # 垃圾识别小程序

   #### 项目介绍

   本项目用于参加学校迪美杯的软件设计大赛中。

   声明本项目的小程序端是学习[程序员二师兄](https://gitee.com/aaluoxiang/GarbageSort)的 [GarbageSort ](https://gitee.com/aaluoxiang/GarbageSort)进行的二次开发。非常感谢二师兄，一直想做一款小程序，奈何自己不会搞小程序的界面。

   项目分成前台和后台，前台就是小程序端，后台就是后台管理系统。项目稍微粗糙了点，因为主要以学习并整合技术栈为目的做的项目，所以本项目中涉及大量技术，有的技术可以不用的但是还是有用到，哈哈哈。

   因为小程序的上线好像需要域名，所以没办法演示给大家看，抱歉了。

   觉得还不错的话，帮忙点个` star` 鼓励一下吧，如果有问题的可以加我微信哦！！！

   ![输入图片说明](https://images.gitee.com/uploads/images/2021/0924/001928_443d0082_7564778.png "微信图片_20210924001854.png")

   账号（手机号）：数据库里有
   密码：一般为手机号后六位

   #### 后台管理系统

   登录页：

   ![image-20210923221822969](https://images.gitee.com/uploads/images/2021/0924/000608_2eb427ff_7564778.png)

   首页：

   ![image-20210923221755762](https://images.gitee.com/uploads/images/2021/0924/000630_dc7e5700_7564778.png)

   轮播图管理：

   ![轮播图](https://images.gitee.com/uploads/images/2021/0924/000735_bf861c42_7564778.png "image-20210923233444514.png")

   图像识别管理：

   ![图像识别](https://images.gitee.com/uploads/images/2021/0924/000830_07821e69_7564778.png "image-20210923233506602.png")

   语音识别管理：

   ![语音识别](https://images.gitee.com/uploads/images/2021/0924/000853_19a3f678_7564778.png "image-20210923222051854.png")

   短信日志管理：

   ![输入图片说明](https://images.gitee.com/uploads/images/2021/0924/000914_84c42bcf_7564778.png "image-20210923222005628.png")

   角色管理：

   ![输入图片说明](https://images.gitee.com/uploads/images/2021/0924/001013_ce6777ed_7564778.png "image-20210923222241887.png")

   个人信息：

   ![输入图片说明](https://images.gitee.com/uploads/images/2021/0924/001033_ee186c27_7564778.png "image-20210923235426746.png")

   

   #### 小程序效果图


   |                             首页                             |                            分类页                            |                            搜索页                            |                           搜索结果                           |
   | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
   | ![首页](https://images.gitee.com/uploads/images/2021/0924/001258_3dd60756_7564778.png "image-20210923233716261.png") | ![分类](https://images.gitee.com/uploads/images/2021/0924/001316_49958ac3_7564778.png "image-20210923233727927.png") | ![输入图片说明](https://images.gitee.com/uploads/images/2021/0924/001336_498a8984_7564778.png "image-20210923233804140.png") | ![输入图片说明](https://images.gitee.com/uploads/images/2021/0924/001350_ff716a0a_7564778.png "image-20210923233837133.png") |

   |                           图像识别                           |                            语音识别                            |                           知识小测                           |                           小测结果                           |
   | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
   | ![输入图片说明](https://images.gitee.com/uploads/images/2021/0924/002320_80cbd73b_7564778.png "微信图片_20210924002311.png") | ![输入图片说明](https://images.gitee.com/uploads/images/2021/0924/002240_dcc3a83d_7564778.png "微信图片_20210924002006.png") | ![输入图片说明](https://images.gitee.com/uploads/images/2021/0924/001527_3539e873_7564778.png "image-20210923233911673.png") | ![输入图片说明](https://images.gitee.com/uploads/images/2021/0924/001542_5c2ec63c_7564778.png "image-20210923233856519.png") |

   

   #### 介绍

   前端：后台管理系统采用 [vue-element-admin](https://panjiachen.github.io/vue-element-admin-site/zh/guide/) 后台集成方案进行二次开发。微信小程序采用 [uni-app](https://uniapp.dcloud.io/) 开发框架。

   后端：后台管理模块采用 SpringBoot + Dubbo 构建后端分布式服务，使用 Shiro 做系统安全框架，配合 RBAC 设计模式实现用户的权限管理，使用 Redis 对用户token及字典数据进行缓存；小程序模块采用 SpringBoot 构建后端服务；数据库采用 Mysql（5.7） ，使用 [Mybatis-Plus](https://mybatis.plus/) 做数据访问层，Druid 作为数据库连接池。

   短信服务采用腾讯云（唉，阿里的申请了七八次不给通过）。

   使用阿里OSS作为文件存储系统（最开始用的 FastDFS，奈何不想一直开着虚拟机）。

   语音识别和图像识别采用百度智能云平台服务。

   

   #### 安装教程

   1. 开发需要准备相关的 IDE 和  JDK8 开发环境 , 前端开发需要下载 [HbuilderX](https://www.dcloud.io/hbuilderx.html) 和 Visual Code，后端开发需要下载 `Eclipse` 或` IDEA`。

   2. 首先需要一台虚拟机，可以使用阿里的也可以本机安装；在虚拟机上我们需要搭建分布式环境：Zookeeper和 Dubbo ；还有安装 Redis 等。（虚拟机中的软件安装可以见软件安装文档）

   3. 短信服务使用腾讯云，需要自己去腾讯云申请签名及模板（比阿里的好申请），在后端 `system-web` 的配置文件中进行配置。 

   4. 文件存储系统使用的是阿里OSS，也可以自己搭建 FastDFS 服务，在后端 `system-web` 和 `uniapp-web` 的配置文件中进行配置。

   5. 语音识别和图像识别功能使用百度智能云平台服务，在后端 `uniapp-web` 当中的配置文件里面需要进行配置 `appid`、`apikey`、`secretkey`。

   6. 数据库准备，创建一个名称为 `garbage-sort` 的数据库，把跟目录下 `garbage-sort.sql`数据库文件进行导入进去即可。

   7. 后端启动：（需要虚拟机并安装好软件）

      后台管理系统：第一步、使用 IDEA 在 `garbagesort-parent` 目录下找到 `system-web` 和 `sort-service` 模块。第二步、在 application-dev.yml` 中修改 ip 地址，改为自己的虚拟机 ip 地址，修改腾讯云和阿里云的相关配置。  ` 第三步、运行 SystemApplication 和 SortApplication ，启动后端服务。可以在浏览器输入 ip:7001 账号和密码都是 root，查看服务。

      小程序端：第一步、使用 IDEA 在 `garbagesort-parent` 目录下找到 `uniapp-web` 模块。第二步、在 application-dev.yml` 中修改百度云和阿里云的相关配置。  ` 第三步、运行 UniappApplication，启动后端服务。

   8. 前端启动：

      后台管理系统：在 Visual Code 中打开  `garbage-sort-ui` , 在控制台输入 npm run install 下载依赖，依赖下载完后，使用命令 npm run dev 运行前端项目。

      小程序端：在 HbuilderX 中打开目录 `garbage-sort-uniapp` ，需要下载一个插件运行时会有提示下载，在运行菜单中 点击 `运行到小程序模拟器` —> `微信开发者工具`，或者 使用 微信开发者工具 打开  `garbage-sort-uniapp` -> `unpackage` -> `dis`t -> `dev` -> `mp-weixin`，即可运行微信小程序。（二次开发的时候在 Hbuilder 中修改文件，保存后可以到微信开发者工具看预览效果）

   9. 务必记得：在登录后台管理系统后，到字典管理模块点击同步缓存的按钮，把字典类型及数据添加到redis缓存中！！！
      

   #### 技术栈

   |       技术        |          名称          |                             官网                             |
   | :---------------: | :--------------------: | :----------------------------------------------------------: |
   |    SpringBoot     |       微服务框架       |           https://spring.io/projects/spring-boot/            |
   |   Mybatis-Plus    |        ORM框架         |                   https://mp.baomidou.com/                   |
   | Mybatis-Generator |      代码生成插件      | https://zhile.io/2019/04/23/mybatis-code-helper-pro-crack.html |
   |       Shiro       |        安全框架        |                   http://shiro.apache.org/                   |
   |     Zookeeper     |     分布式协调服务     |                 http://zookeeper.apache.org/                 |
   |       Druid       |      数据库连接池      |               https://github.com/alibaba/druid               |
   |    Dubbo-Admin    |      服务治理项目      |     http://dubbo.apache.org/zh-cn/blog/dubbo-admin.html      |
   |       Dubbo       |      远程调用框架      |                http://dubbo.apache.org/zh-cn/                |
   |       Log4J       |        日志组件        |             http://logging.apache.org/log4j/1.2/             |
   |        Git        |        版本管理        |                     https://git-scm.com/                     |
   |       Maven       |      项目构建管理      |                   http://maven.apache.org/                   |
   |      Jackson      |       数据序列化       |             https://github.com/FasterXML/jackson             |
   |      Lombok       |      简化编码插件      |                  https://projectlombok.org/                  |
   |      uni-app      |  基于 vue 的跨端框架   |                  https://uniapp.dcloud.io/                   |
   |       vuejs       | 渐进式 JavaScript 框架 |                    https://cn.vuejs.org/                     |
   | vue-element-admin |      后台集成框架      | https://panjiachen.github.io/vue-element-admin-site/zh/guide/ |
   |    Element UI     |         组件库         |             https://element.eleme.cn/2.0/#/zh-CN             |

   

   #### 有收获可以请作者喝奶茶哟

   If the project is very helpful to you, you can buy the author a cup of milk tea. 

   如果这个项目对您有帮助，请作者喝杯奶茶吧。

   | 支付宝                                                       | 微信                                                         |
   | ------------------------------------------------------------ | ------------------------------------------------------------ |
   | <img src="https://images.gitee.com/uploads/images/2021/0924/001109_b62123e9_7564778.png" alt="image-20210923233222129" style="zoom:67%;" /> | <img src="https://images.gitee.com/uploads/images/2021/0924/001129_973da5b9_7564778.png" alt="image-20210923233114191" style="zoom: 67%;" /> |

   PS：基于Apache License 2协议开源友好，使用、参考、转载请保留出处，严禁将项目直接打包出售。