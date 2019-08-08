package com.hust.foolwc.dy.agent.core.enhance;

import com.hust.foolwc.dy.agent.core.InterceptorChainFactoryUtils;
import com.hust.foolwc.dy.agent.core.utils.MethodUtils;
import com.hust.foolwc.dy.agent.log4j.LogManager;
import com.hust.foolwc.dy.agent.log4j.Logger;

import java.lang.reflect.Method;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperMethod;
import net.bytebuddy.implementation.bind.annotation.This;

public class MethodInterceptorDispatcher {

    private static final Logger LOG = LogManager.getLogger(MethodInterceptorDispatcher.class);

    //for instance method
    @BindingPriority(9)
    @RuntimeType
    public static Object intercept(@This Object proxy, @SuperMethod Method originAccessor,
            @Origin Method originMethod, @AllArguments Object[] args) throws Throwable {

        String key = MethodUtils.signature(originMethod.getDeclaringClass(), originMethod);
        String mn = originMethod.getName();

        LOG.debug("Intercept {}, InterceptorChain key is {}.", mn, key);
        InterceptorChain<MethodInterceptor> mic = InterceptorChainFactoryUtils.METHOD.interceptorChainOf(key);
        if (mic == null) {
            LOG.error("{} be intercepted but has no InterceptorChain.", mn);
        }

        //build ExecuteContext
        ExecuteContext executeContext = new ExecuteContext(mic,
                new MethodProceeding(proxy, originAccessor, originMethod, args));
        executeContext.proceed();
        return executeContext.getMethodProceeding().getRtVal();
    }

    //for static method
    @RuntimeType
    public static Object intercept(@SuperMethod Method originAccessor, @Origin Method originMethod,
            @AllArguments Object[] args) throws Throwable {
        //forward to overloaded intercept method.
        return intercept(null, originAccessor, originMethod, args);
    }
}
