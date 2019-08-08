package com.hust.foolwc.dy.agent.core.enhance;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InterceptorChain<T> {

    private List<T> interceptors = new CopyOnWriteArrayList<>();

    public void addInterceptor(T interceptor) {
        interceptors.add(interceptor);
    }

    public List<T> getInterceptors() {
        return Collections.unmodifiableList(interceptors);
    }

}
