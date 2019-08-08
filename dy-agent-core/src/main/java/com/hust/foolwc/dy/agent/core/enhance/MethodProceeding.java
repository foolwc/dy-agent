package com.hust.foolwc.dy.agent.core.enhance;

import java.lang.reflect.Method;

public class MethodProceeding {

    private final Object instance;
    private final Method originAccessor;
    private final Method originMethod;
    private final Object[] args;
    private Object rtVal;

    public MethodProceeding(Object instance, Method originAccessor, Method originMethod, Object[] args) {
        this.instance = instance;
        this.originAccessor = originAccessor;
        this.originMethod = originMethod;
        this.args = args;
    }

    /**
     * Using reflect to invoke target's method.
     *
     * @return return value of target's method
     * @throws Exception exception occur during method execution.
     */
    public final void proceed() throws Exception {
        originAccessor.setAccessible(true);
        rtVal = originAccessor.invoke(instance, args);
    }

    public Method getMethod() {
        return originMethod;
    }

    public Object getInstance() {
        return instance;
    }

    public Class<?> getDeclaringClassOfMethod() {
        return originMethod.getDeclaringClass();
    }

    public Object[] getArgs() {
        return args;
    }

    public Object getRtVal() {
        return rtVal;
    }

    public void setRtVal(Object rtVal) {
        this.rtVal = rtVal;
    }

}
