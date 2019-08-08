package com.hust.foolwc.dy.agent.core.enhance;

import java.util.Iterator;

import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterDescription.InDefinedShape;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.matcher.ElementMatcher;

public class ParametersMatcher implements ElementMatcher<Iterable<? extends ParameterDescription>> {

    private ParameterList<InDefinedShape> otherPds;

    public ParametersMatcher(ParameterList<InDefinedShape> parameterDescriptions) {
        if (parameterDescriptions == null) {
            throw new IllegalArgumentException("parameter descriptions can't be null.");
        }
        this.otherPds = parameterDescriptions;
    }

    public boolean matches(Iterable<? extends ParameterDescription> thisPds) {
        boolean matched = true;
        Iterator<? extends ParameterDescription> thisPdsItr = thisPds.iterator();
        Iterator<InDefinedShape> otherPdsItr = otherPds.iterator();

        while (thisPdsItr.hasNext() && otherPdsItr.hasNext()) {
            ParameterDescription thisPd = thisPdsItr.next();
            ParameterDescription otherPd = otherPdsItr.next();
            if (!thisPd.equals(otherPd)) {
                matched = false;
            }
        }
        if (matched && (thisPdsItr.hasNext() || otherPdsItr.hasNext())) {
            matched = false;
        }
        return matched;
    }
}
