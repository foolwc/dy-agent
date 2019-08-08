package com.hust.foolwc.dy.agent.core.match;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

public interface IndirectMatch extends ClassMatch {

    <T extends TypeDescription> ElementMatcher.Junction<T> buildJunction();
}
