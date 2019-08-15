# dy-agent
+ 基于Bytebuddy字节码增强技术及Java Agent实现的无侵入式AOP框架
+ 借鉴skywalking的设计原理开发，只保留最基本的match功能
+ 代码简单，容易直接上手二次开发

## 架构设计
+ dy-agent-core：核心功能代码，代码量不大，有兴趣可以瞧一瞧。
+ dy-agent-log4j：为了不与目标应用的日志框架产生冲突，自己实现的log4j。

## 如何添加新的拦截器
+ 继承AbstractMethodInterceptor类，需要override两个方法: **focusOn** 以及 **match**。

### focusOn
在focusOn中定义需要拦截的特定类,目前的matcher有**NameMatch**和**MultiNameOrMatch**。可自己实现更多Matcher。

### match
对类中的相应方法进行拦截。也可直接返回true，表示拦截所有方法。

## Demo
参考DemoInterceptor编写拦截器，该类拦截了JDBC的两个实现，可以拦截到Mysql连接数据库的操作，控制台打印出connection url。
## 使用
mvn clean package后，将release下打包好的dy-agent-release-1.0-SNAPSHOT.jar添加到目标java应用启动参数中：-javaagent:path/of/dy-agent-release-1.0-SNAPSHOT.jar。应用如果产生JDBC连接，控制台即会打印出url。

## 注意事项
若编写的Interceptor没有实现效果，可检查TypeMatcher里面的**ignoreRule**（启动优化，避免扫描所有类）是否已经排除了目标类，手动删除即可。