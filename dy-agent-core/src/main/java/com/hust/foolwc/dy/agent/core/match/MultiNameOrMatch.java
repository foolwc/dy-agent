package com.hust.foolwc.dy.agent.core.match;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.bytebuddy.description.type.TypeDescription;

public class MultiNameOrMatch implements ClassMatch {

    private Set<String> classNames = new HashSet<>(4);

    public MultiNameOrMatch(String... classNames) {
        this.classNames.addAll(Arrays.asList(classNames));
    }

    public MultiNameOrMatch(String className) {
        this.classNames.add(className);
    }

    public MultiNameOrMatch or(String className) {
        classNames.add(className);
        return this;
    }

    public Set<String> getClassNames() {
        return classNames;
    }

    public static MultiNameOrMatch byName(String className) {
        return new MultiNameOrMatch(className);
    }

    public static MultiNameOrMatch byNames(String... classNames) {
        return new MultiNameOrMatch(classNames);
    }

    @Override
    public boolean isMatch(TypeDescription typeDescription) {
        return classNames.contains(typeDescription.getActualName());
    }
}
