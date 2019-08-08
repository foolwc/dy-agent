package com.hust.foolwc.dy.agent.core.enhance;

import com.hust.foolwc.dy.agent.log4j.LogManager;
import com.hust.foolwc.dy.agent.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public abstract class AbstractMethodInterceptor extends WitnessClassSupport implements MethodInterceptor {

    protected final Logger logger = LogManager.getLogger(getClass());

    @Override
    public void intercept(ExecuteContext context) throws Throwable {
        MethodProceeding proceeding = context.getMethodProceeding();

        Method method = proceeding.getMethod();
        Class<?> clazz = method.getDeclaringClass();
        Object instance = proceeding.getInstance();
        Class<?>[] argTypes = method.getParameterTypes();
        Object[] args = proceeding.getArgs();

        try {
            beforeIntercept(instance, method, args, argTypes);
        } catch (Throwable t) {
            logger.error("class[{}] before method[{}] intercept failed.", clazz, method.getName(), t);
        }

        try {
            context.proceed();
        } catch (Throwable t) {
            try {
                handleError(instance, method, args, argTypes, t);
            } catch (Throwable t2) {
                logger.error("class[{}] handle error[{}] exception failed.", clazz, method.getName(), t);
            }
            //NOTE unwrap InvocationTargetException.
            if (t instanceof InvocationTargetException) {
                throw ((InvocationTargetException) t).getTargetException();
            }
            throw t;
        } finally {
            try {
                Object rtVal = afterIntercept(instance, method, args, argTypes, proceeding.getRtVal());
                if (rtVal != null) {
                    proceeding.setRtVal(rtVal);
                }
            } catch (Throwable t) {
                logger.error("class[{}] after method[{}] intercept failed.", clazz, method.getName(), t);
            }
        }
    }

    protected abstract void beforeIntercept(Object instance, Method method, Object[] allArguments,
            Class<?>[] argumentsTypes) throws Throwable;

    protected abstract Object afterIntercept(Object objInst, Method method, Object[] allArguments,
            Class<?>[] argumentsTypes,
            Object ret) throws Throwable;

    protected abstract void handleError(Object objInst, Method method, Object[] allArguments,
            Class<?>[] argumentsTypes,
            Throwable t) throws Throwable;
}
