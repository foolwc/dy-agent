package com.hust.foolwc.dy.agent.core.enhance;

import com.hust.foolwc.dy.agent.core.InterceptorChainFactoryUtils;
import com.hust.foolwc.dy.agent.core.utils.MethodUtils;
import com.hust.foolwc.dy.agent.log4j.LogManager;
import com.hust.foolwc.dy.agent.log4j.Logger;

import java.lang.reflect.Constructor;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;


public class ConstructorWeaver {

    private static final Logger LOG = LogManager.getLogger(ConstructorWeaver.class);

    //for constructor
    @BindingPriority(9)
    @RuntimeType
    public static void intercept(@This Object proxy, @Origin Constructor constructor, @AllArguments Object[] args) {
        String key = MethodUtils.signature(constructor.getDeclaringClass(), constructor);
        String cn = constructor.getName();

//        LOG.info("Intercept {}, InterceptorChain key is {}.", cn, key);
        InterceptorChain<ConstructorInterceptor> cic = InterceptorChainFactoryUtils.CONSTRUCTOR.interceptorChainOf(key);
        if (cic == null) {
            //if Constructor interceptor chain is null, log the error and return intercept.
            LOG.error("{} be intercepted but has no InterceptorChain.", cn);
            return;
        }
        //foreach constructor interceptors and execute.
        for (ConstructorInterceptor ci : cic.getInterceptors()) {
            try {
                ci.onConstruct(proxy, args);
            } catch (Throwable t) {
                LOG.error("Invoke {}'s onConstruct error.", ci.getClass().getName(), t);
            }
        }
    }

}
