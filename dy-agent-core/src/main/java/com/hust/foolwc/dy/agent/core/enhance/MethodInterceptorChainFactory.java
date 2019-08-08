package com.hust.foolwc.dy.agent.core.enhance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MethodInterceptorChainFactory extends AbstractInterceptorChainFactory<MethodInterceptor> {

    private final ConcurrentMap<String, InterceptorChain<MethodInterceptor>> interceptorChains = new ConcurrentHashMap<>();
    private final List<MethodInterceptor> interceptors = new ArrayList<>(8);

    @Override
    protected void addInterceptors(Interceptor itrcpt) {
        if (itrcpt instanceof MethodInterceptor) {
            this.interceptors.add((MethodInterceptor) itrcpt);
        }
    }

    @Override
    protected List<MethodInterceptor> getInterceptors() {
        return Collections.unmodifiableList(this.interceptors);
    }

    @Override
    protected InterceptorChain<MethodInterceptor> interceptorChainOf(String key) {
        InterceptorChain<MethodInterceptor> itrcptChain = this.interceptorChains.get(key);
        if (itrcptChain == null) {
            itrcptChain = new InterceptorChain<>();
            this.interceptorChains.put(key, itrcptChain);
        }
        return itrcptChain;
    }

}
