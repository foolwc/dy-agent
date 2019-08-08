package com.hust.foolwc.dy.agent.core.enhance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConstructorInterceptorChainFactory extends AbstractInterceptorChainFactory<ConstructorInterceptor> {

    private final ConcurrentMap<String, InterceptorChain<ConstructorInterceptor>> interceptorChains = new ConcurrentHashMap<>();
    private final List<ConstructorInterceptor> interceptors = new ArrayList<>(8);

    @Override
    protected void addInterceptors(Interceptor itrcpt) {
        if (itrcpt instanceof ConstructorInterceptor) {
            this.interceptors.add((ConstructorInterceptor) itrcpt);
        }
    }

    @Override
    protected List<ConstructorInterceptor> getInterceptors() {
        return Collections.unmodifiableList(this.interceptors);
    }

    @Override
    protected InterceptorChain<ConstructorInterceptor> interceptorChainOf(String key) {
        InterceptorChain<ConstructorInterceptor> itrcptChain = this.interceptorChains.get(key);
        if (itrcptChain == null) {
            itrcptChain = new InterceptorChain<>();
            this.interceptorChains.put(key, itrcptChain);
        }
        return itrcptChain;
    }
}
