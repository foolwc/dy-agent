package com.hust.foolwc.dy.agent.core.enhance;


import com.hust.foolwc.dy.agent.core.match.ClassMatch;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription.InDefinedShape;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDescription;

public interface Interceptor {

    /**
     * Tail SkyEye AOP which type you focus on,
     * NOTE
     * It's not supported that you tail SkyEye AOP not focus on some type.
     * Because SkyEye AOP use AgentBuilder's type() to register concerned type
     * for all plugin, for example, if one plugin tail SkyEye not focus on type under
     * org.springframework, but other plugin focus on type under org.springframework,
     * other plugin may work unsuccessfully.
     * <p>
     * We recommend that one plugin focus on one framework or third-party jar.
     *
     * @return
     */
    ClassMatch focusOn();

    /**
     * Check whether need intercept a method before class load.
     *
     * @param typeDescription       method's declaring class.
     * @param methodDescription     method's description.
     * @param parameterDescriptions method's parameters' description.
     * @return true represent intercept given method.
     */
    boolean match(TypeDescription typeDescription,
            MethodDescription.InDefinedShape methodDescription,
            ParameterList<InDefinedShape> parameterDescriptions);
}
