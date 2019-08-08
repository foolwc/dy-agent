package com.hust.foolwc.dy.agent.core.match;

import net.bytebuddy.description.type.TypeDescription;

public class NameMatch implements ClassMatch {

    private String className;

    private NameMatch(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public static NameMatch byName(String className) {
        return new NameMatch(className);
    }

    @Override
    public boolean isMatch(TypeDescription typeDescription) {
        //NOTE: 静态内部类actualName 是a.b.c$D 对应的canonicalName是  a.b.c.D。AgentBuilder$Default#type()
        //使用的是actualName。
        return typeDescription.getActualName().equals(className);
    }
}
