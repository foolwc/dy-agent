package com.hust.foolwc.dy.agent.core.enhance;

import com.hust.foolwc.dy.agent.core.InterceptorChainFactoryUtils;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription.InDefinedShape;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.utility.JavaModule;

import static net.bytebuddy.implementation.MethodDelegation.to;
import static net.bytebuddy.matcher.ElementMatchers.hasParameters;
import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @Author: foolwc
 * @Date: 2019/8/7 13:40
 */
public enum BuilderDecorator {
    INSTANCE;

    public Builder<?> decorate(Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader,
            JavaModule module) {
        Builder<?> decoratedBuilder = builder;
        if (decoratedBuilder == null) {
            throw new IllegalArgumentException("builder can't be null.");
        }
        if (typeUnmatched(typeDescription)) {
            return decoratedBuilder;
        }

        for (MethodDescription.InDefinedShape m : typeDescription.getDeclaredMethods().asDefined()) {
            /**
             * NOTE
             * m.getActualName() is "" when m is constructor, so use m.getName() instead.
             * getName() will return canonical name of constructor.
             *
             */
            final ParameterList<InDefinedShape> otherPds = m.getParameters();

            if (m.isConstructor()) {
                //decorate builder for constructor.
                decoratedBuilder = forConstructor(decoratedBuilder, typeDescription, m, otherPds);

            } else if (m.isMethod()) {
                //decorate builder for method.
                decoratedBuilder = forMethod(decoratedBuilder, typeDescription, m, otherPds);
            }
        }
        return decoratedBuilder;
    }

    private Builder<?> forMethod(Builder<?> builder, TypeDescription type, MethodDescription.InDefinedShape method,
            ParameterList<InDefinedShape> parameters) {
        if (InterceptorChainFactoryUtils.METHOD.hasInterceptors(type, method, parameters)) {
            //update builder.
            builder = builder.method(named(method.getActualName())
                    .and(hasParameters(new ParametersMatcher(parameters))))
                    .intercept(to(MethodInterceptorDispatcher.class));
        }
        return builder;
    }

    private boolean typeUnmatched(TypeDescription typeDescription) {
        for (Interceptor itr : InterceptorChainFactoryUtils.INTERCEPTORS) {
            if (itr.focusOn().isMatch(typeDescription)) {
                return false;
            }
        }
        return true;
    }

    private Builder<?> forConstructor(Builder<?> builder, TypeDescription type,
            MethodDescription.InDefinedShape constructor,
            ParameterList<InDefinedShape> parameters) {

        //update builder.
        if (InterceptorChainFactoryUtils.CONSTRUCTOR.hasInterceptors(type, constructor, parameters)) {
            builder = builder.constructor(named(constructor.getActualName())
                    .and(hasParameters(new ParametersMatcher(parameters))))
                    .intercept(SuperMethodCall.INSTANCE.andThen(to(ConstructorWeaver.class)));
        }
        return builder;
    }

}