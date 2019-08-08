package com.hust.foolwc.dy.agent.core.enhance;

public interface ConstructorInterceptor extends Interceptor {

    /**
     * When your concerned constructor is invoked, this method will be invoked,
     * so you can add constructor-related aop logic in this.
     *
     * @param instance   the intercepted object.
     * @param arguments constructor's arguments.
     */
    void onConstruct(Object instance, Object[] arguments);
}
