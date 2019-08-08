package com.hust.foolwc.dy.agent.core.utils;

import java.util.Arrays;

import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;

public class ParameterUtils {

    private static final String EMPTY_PARAMETERS_TYPE_STR = "[]";
    private static final String EMPTY_PARENTHESES = "()";

    public static String parametersTypeStr(Class<?>[] parameterTypes) {
        if (parameterTypes == null) {
            return EMPTY_PARAMETERS_TYPE_STR;
        }
        return Arrays.toString(parameterTypes);
    }

    public static String parametersTypeStr(ParameterList<ParameterDescription.InDefinedShape> parameterTypes) {
        if (parameterTypes == null) {
            return EMPTY_PARAMETERS_TYPE_STR;
        }
        StringBuilder parametersTypeStr = new StringBuilder("[");
        for (ParameterDescription.InDefinedShape pdi : parameterTypes) {
            parametersTypeStr.append(pdi.getType().asErasure()).append(", ");
        }
        int length = parametersTypeStr.length();
        if (length > 1) {
            //delete last ", "
            parametersTypeStr.delete(length - 2, length);
        }
        parametersTypeStr.append("]");
        return parametersTypeStr.toString();
    }

    //[]改为()
    public static String parametersStr(ParameterList<ParameterDescription.InDefinedShape> parameterTypes) {
        if (parameterTypes == null) {
            return EMPTY_PARENTHESES;
        }
        StringBuilder parametersTypeStr = new StringBuilder("(");
        for (ParameterDescription.InDefinedShape pdi : parameterTypes) {

            parametersTypeStr.append(pdi.getType().getTypeName()).append(", ");
        }
        int length = parametersTypeStr.length();
        if (length > 1) {
            //delete last ", "
            parametersTypeStr.delete(length - 2, length);
        }
        parametersTypeStr.append(")");
        return parametersTypeStr.toString();
    }

    public static String parametersStr(Class<?>[] argumentsTypes) {
        if (argumentsTypes == null) {
            return EMPTY_PARENTHESES;
        }
        StringBuilder parametersTypeStr = new StringBuilder("(");
        for ( Class<?> argClass: argumentsTypes) {
            parametersTypeStr.append(argClass.getName()).append(", ");
        }
        int length = parametersTypeStr.length();
        if (length > 1) {
            //delete last ", "
            parametersTypeStr.delete(length - 2, length);
        }
        parametersTypeStr.append(")");
        return parametersTypeStr.toString();
    }
}
