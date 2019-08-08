package com.hust.foolwc.dy.agent.core.itrcpt;

import com.hust.foolwc.dy.agent.core.enhance.AbstractMethodInterceptor;
import com.hust.foolwc.dy.agent.core.match.ClassMatch;
import com.hust.foolwc.dy.agent.core.match.MultiNameOrMatch;

import java.lang.reflect.Method;

import net.bytebuddy.description.method.MethodDescription.InDefinedShape;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDescription;

/**
 * @Author: foolwc
 * @Date: 2019/8/7 14:56
 */
public class DemoInterceptor extends AbstractMethodInterceptor {

    @Override
    protected void beforeIntercept(Object instance, Method method, Object[] allArguments, Class<?>[] argumentsTypes)
            throws Throwable {
        System.out.println("JDBC connection detected!");
        System.out.println("jdbc url: " + allArguments[0]);
    }

    @Override
    protected Object afterIntercept(Object objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes,
            Object ret) throws Throwable {
        System.out.println("method end");
        return ret;
    }

    @Override
    protected void handleError(Object objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes,
            Throwable t) throws Throwable {

    }

    @Override
    public ClassMatch focusOn() {
        return MultiNameOrMatch.byNames("com.mysql.fabric.jdbc.FabricMySQLDriver", "com.mysql.jdbc.NonRegisteringDriver");
    }

    @Override
    public boolean match(TypeDescription typeDescription, InDefinedShape methodDescription,
            ParameterList<ParameterDescription.InDefinedShape> parameterDescriptions) {
        return methodDescription.getActualName().equals("connect");
    }
}
