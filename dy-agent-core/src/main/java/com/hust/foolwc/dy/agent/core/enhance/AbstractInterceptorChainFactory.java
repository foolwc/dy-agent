package com.hust.foolwc.dy.agent.core.enhance;

import com.hust.foolwc.dy.agent.core.match.ClassMatch;
import com.hust.foolwc.dy.agent.core.utils.InterceptorUtils;
import com.hust.foolwc.dy.agent.core.utils.MethodUtils;
import com.hust.foolwc.dy.agent.log4j.LogManager;
import com.hust.foolwc.dy.agent.log4j.Logger;

import java.util.List;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription.InDefinedShape;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDescription;

/**
 * @Author: foolwc
 * @Date: 2019/8/7 14:22
 */
public abstract class AbstractInterceptorChainFactory<ITRCPT extends Interceptor> {

    private Logger logger = LogManager.getLogger(getClass());

    /**
     * Add interceptors to InterceptorChainFactory, sub class should select concrete Interceptor.
     *
     * @param itrcpts interceptors.
     */
    public void addInterceptors(List<Interceptor> itrcpts) {
        if (itrcpts == null || itrcpts.isEmpty()) {
            return;
        }
        for (Interceptor itrcpt : itrcpts) {
            if (itrcpt instanceof WitnessClassSupport) {
                if (((WitnessClassSupport) itrcpt).witness()) {
                    addInterceptors(itrcpt);
                    continue;
                }
                logger.info("Interceptor()'s witness class not exist.", itrcpt);
            } else {
                addInterceptors(itrcpt);
            }
        }
        logger.info("Add interceptors {}.", getInterceptors());
    }

    /**
     * Check whether the given method has it's interceptors, and build InterceptorChain meanwhile.
     *
     * @param typeDescription       type description, equal to Class.
     * @param methodDescription     method description, equal to Method or Constructor.
     * @param parameterDescriptions parameter description. equal to Class[].
     * @return true represent the given method has it's interceptors. false otherwise.
     */
    public boolean hasInterceptors(TypeDescription typeDescription, MethodDescription.InDefinedShape methodDescription,
            ParameterList<InDefinedShape> parameterDescriptions) {
        boolean hasInterceptors = false;
        InterceptorChain<ITRCPT> itrcptChain = null;

        for (ITRCPT itrcpt : this.getInterceptors()) {
            //There are interceptors for given method (include static method) or constructor.
            ClassMatch cm = itrcpt.focusOn();
            if (cm == null) {
                logger.info("Interceptor ({})'s focusOn return null.", itrcpt.getClass().getName());
                continue;
            }
            if (cm.isMatch(typeDescription)
                    && itrcpt.match(typeDescription, methodDescription, parameterDescriptions)) {

                String key = MethodUtils.signature(typeDescription, methodDescription);
                itrcptChain = interceptorChainOf(key);
                hasInterceptors = true;
                itrcptChain.addInterceptor(itrcpt);
            }
        }

        if (itrcptChain != null) {
            //log method and it's interceptors.
            logger.info("{} has interceptors {}.", methodDescription,
                    InterceptorUtils.arrayStrOfInterceptors(itrcptChain.getInterceptors()));
        }

        return hasInterceptors;
    }

    protected abstract void addInterceptors(Interceptor itrcpt);

    @Deprecated
    protected abstract List<ITRCPT> getInterceptors();

    protected abstract InterceptorChain<ITRCPT> interceptorChainOf(String key);

}
