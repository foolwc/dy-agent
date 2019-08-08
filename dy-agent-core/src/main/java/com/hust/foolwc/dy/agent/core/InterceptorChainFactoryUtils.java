package com.hust.foolwc.dy.agent.core;

import com.hust.foolwc.dy.agent.core.enhance.AbstractInterceptorChainFactory;
import com.hust.foolwc.dy.agent.core.enhance.ConstructorInterceptor;
import com.hust.foolwc.dy.agent.core.enhance.ConstructorInterceptorChainFactory;
import com.hust.foolwc.dy.agent.core.enhance.Interceptor;
import com.hust.foolwc.dy.agent.core.enhance.MethodInterceptor;
import com.hust.foolwc.dy.agent.core.enhance.MethodInterceptorChainFactory;

import java.util.List;

/**
 * @Author: foolwc
 * @Date: 2019/8/7 14:18
 */
public class InterceptorChainFactoryUtils {

    public static List<Interceptor> INTERCEPTORS;

    public static final AbstractInterceptorChainFactory<MethodInterceptor> METHOD
            = new MethodInterceptorChainFactory();

    public static final AbstractInterceptorChainFactory<ConstructorInterceptor> CONSTRUCTOR
            = new ConstructorInterceptorChainFactory();

}

