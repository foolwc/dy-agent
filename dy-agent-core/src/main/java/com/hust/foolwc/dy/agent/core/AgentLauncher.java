package com.hust.foolwc.dy.agent.core;

import com.hust.foolwc.dy.agent.core.enhance.BuilderDecorator;
import com.hust.foolwc.dy.agent.core.enhance.Interceptor;
import com.hust.foolwc.dy.agent.core.enhance.TypeMatcher;
import com.hust.foolwc.dy.agent.core.itrcpt.DemoInterceptor;
import com.hust.foolwc.dy.agent.log4j.LogManager;
import com.hust.foolwc.dy.agent.log4j.Logger;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.AgentBuilder.Default;
import net.bytebuddy.agent.builder.AgentBuilder.Transformer;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.ForDeclaredMethods;
import net.bytebuddy.utility.JavaModule;

/**
 * @Author: foolwc
 * @Date: 2019/8/7 13:40
 */
public class AgentLauncher {

    private static final Logger LOG = LogManager.getLogger(AgentLauncher.class);

    public static void premain(String args, Instrumentation inst) throws Exception {
        initConfigAndVariables();
        decorateInst(inst);
        LOG.info("Agent started!");
    }

    private static void decorateInst(Instrumentation inst) {
        List<Interceptor> itrcpts = Arrays.asList(new DemoInterceptor());

        InterceptorChainFactoryUtils.INTERCEPTORS = Collections.unmodifiableList(itrcpts);
        InterceptorChainFactoryUtils.METHOD.addInterceptors(itrcpts);
        InterceptorChainFactoryUtils.CONSTRUCTOR.addInterceptors(itrcpts);

        new Default()
                .ignore(TypeMatcher.INSTANCE.ignoreRule())
                .type(TypeMatcher.INSTANCE.passRule(itrcpts))
                .transform(new Transformer() {
                    public Builder<?> transform(Builder<?> builder, TypeDescription typeDescription,
                            ClassLoader classLoader,
                            JavaModule module) {
                        //decorate builder.
                        return BuilderDecorator.INSTANCE.decorate(builder, typeDescription, classLoader, module);
                    }
                })
                .with(new ByteBuddy().with(ForDeclaredMethods.INSTANCE))
                .with(new DefaultAgentListener())
                .installOn(inst);
    }

    /**
     * 获取程序需要的各种参数
     */
    private static void initConfigAndVariables() {
    }

    static class DefaultAgentListener implements AgentBuilder.Listener {

        @Override
        public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule javaModule, boolean b) {
        }

        public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module,
                boolean loaded, DynamicType dynamicType) {
        }

        public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module,
                boolean loaded) {
        }

        public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded,
                Throwable throwable) {
            LOG.error("{} load {} error.", classLoader, typeName, throwable);
        }

        public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
        }
    }
}
