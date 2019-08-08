package com.hust.foolwc.dy.agent.core.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import net.bytebuddy.description.method.MethodDescription.InDefinedShape;
import net.bytebuddy.description.type.TypeDescription;

import static com.hust.foolwc.dy.agent.core.utils.ParameterUtils.parametersStr;
import static com.hust.foolwc.dy.agent.core.utils.ParameterUtils.parametersTypeStr;


public class MethodUtils {

    private static final char DOT = '.';
    private static final char SPACE = ' ';

    public static String signature(Class<?> clazz, Constructor constructor) {
        /**
         * NOTE has the same reason with the follow method.
         * @see #signature(TypeDescription, InDefinedShape)
         */
        return constructor.getName() + DOT + parametersTypeStr(
                constructor.getParameterTypes());
    }

    public static String signature(Class<?> clazz, Method method) {
        return clazz.getCanonicalName() + DOT + method.getName() + DOT + parametersTypeStr(method.getParameterTypes());
    }

    public static String signature(TypeDescription typeDescription,
            InDefinedShape methodDescription) {
        if (methodDescription.isConstructor()) {
            /**
             *  NOTE
             *  if method description represents a constructor, getName() will return canonical name
             *  of this constructor.
             */
            return methodDescription.getName() + DOT + parametersTypeStr(methodDescription.getParameters());
        }
        return typeDescription.getCanonicalName() + DOT + methodDescription.getName() + DOT +
                parametersTypeStr(methodDescription.getParameters());
    }

    public static String methodStr(TypeDescription typeDescription,
            InDefinedShape methodDescription) {
        return methodDescription.getReturnType().getActualName() + SPACE + typeDescription.getCanonicalName() + DOT + methodDescription.getName() +
                parametersStr(methodDescription.getParameters());
    }

    public static String methodStr(Object instance, Method method, Class<?>[] argumentsTypes) {
        return method.getReturnType().getCanonicalName() + SPACE + instance.getClass().getCanonicalName() + DOT + method.getName() +
                parametersStr(argumentsTypes);
    }

}
