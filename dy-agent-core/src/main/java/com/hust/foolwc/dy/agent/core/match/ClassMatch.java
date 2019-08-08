package com.hust.foolwc.dy.agent.core.match;

import net.bytebuddy.description.type.TypeDescription;

public interface ClassMatch {

    boolean isMatch(TypeDescription typeDescription);
}
