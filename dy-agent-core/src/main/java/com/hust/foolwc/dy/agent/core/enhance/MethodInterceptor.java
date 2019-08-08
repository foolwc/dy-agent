package com.hust.foolwc.dy.agent.core.enhance;

/**
 * @Author: foolwc
 * @Date: 2019/8/7 14:19
 */
public interface MethodInterceptor extends Interceptor {

    /**
     * Intercept the target method, you should call context proceed to make the intercepted
     * method proceed.
     *
     * @param context
     */
    void intercept(ExecuteContext context) throws Throwable;
}
