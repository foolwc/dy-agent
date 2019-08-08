package com.hust.foolwc.dy.agent.core.enhance;

import java.util.List;

/**
 * @Author: foolwc
 * @Date: 2019/8/7 14:20
 */
public class ExecuteContext {

    private final InterceptorChain<MethodInterceptor> interceptorChain;
    private final MethodProceeding methodProceeding;
    private int interceptorIndex;

    public ExecuteContext(InterceptorChain<MethodInterceptor> interceptorChain, MethodProceeding methodProceeding) {
        this.interceptorChain = interceptorChain;
        this.methodProceeding = methodProceeding;
    }

    public final void proceed() throws Throwable {
        List<MethodInterceptor> interceptors = this.interceptorChain.getInterceptors();
        if (interceptorIndex == interceptors.size()) {
            methodProceeding.proceed();
            return;
        }
        MethodInterceptor interceptor = interceptors.get(interceptorIndex++);
        interceptor.intercept(this);
    }

    public MethodProceeding getMethodProceeding() {
        return methodProceeding;
    }

}

