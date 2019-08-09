# dy-agent
+ 基于Bytebuddy字节码增强技术及Java Agent实现的无侵入式AOP框架
+ 借鉴skywalking的设计原理开发
## 如何使用
+ 继承AbstractMethodInterceptor类，需要override两个方法: **focusOn** 以及 **match**
### focusOn
在focusOn中定义需要拦截的特定类。
### match
对类中的相应方法进行拦截。也可直接返回true，表示拦截所有方法。
