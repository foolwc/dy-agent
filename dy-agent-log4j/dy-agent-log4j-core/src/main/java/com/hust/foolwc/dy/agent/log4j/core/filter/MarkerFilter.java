/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package com.hust.foolwc.dy.agent.log4j.core.filter;


import com.hust.foolwc.dy.agent.log4j.Level;
import com.hust.foolwc.dy.agent.log4j.Marker;
import com.hust.foolwc.dy.agent.log4j.core.Filter;
import com.hust.foolwc.dy.agent.log4j.core.LogEvent;
import com.hust.foolwc.dy.agent.log4j.core.Logger;
import com.hust.foolwc.dy.agent.log4j.core.config.Node;
import com.hust.foolwc.dy.agent.log4j.core.config.plugins.Plugin;
import com.hust.foolwc.dy.agent.log4j.core.config.plugins.PluginAttribute;
import com.hust.foolwc.dy.agent.log4j.core.config.plugins.PluginFactory;
import com.hust.foolwc.dy.agent.log4j.message.Message;
import com.hust.foolwc.dy.agent.log4j.util.PerformanceSensitive;

import com.hust.foolwc.dy.agent.log4j.Level;
import com.hust.foolwc.dy.agent.log4j.Marker;

/**
 * This filter returns the onMatch result if the marker in the LogEvent is the same as or has the
 * configured marker as a parent.
 */
@Plugin(name = "MarkerFilter", category = Node.CATEGORY, elementType = Filter.ELEMENT_TYPE, printObject = true)
@PerformanceSensitive("allocation")
public final class MarkerFilter extends AbstractFilter {

    private final String name;

    private MarkerFilter(final String name, final Result onMatch, final Result onMismatch) {
        super(onMatch, onMismatch);
        this.name = name;
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object... params) {
        return filter(marker);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Object msg,
                         final Throwable t) {
        return filter(marker);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Message msg,
                         final Throwable t) {
        return filter(marker);
    }

    @Override
    public Result filter(final LogEvent event) {
        return filter(event.getMarker());
    }

    private Result filter(final Marker marker) {
        return marker != null && marker.isInstanceOf(name) ? onMatch : onMismatch;
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0) {
        return filter(marker);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1) {
        return filter(marker);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2) {
        return filter(marker);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3) {
        return filter(marker);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3,
            final Object p4) {
        return filter(marker);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3,
            final Object p4, final Object p5) {
        return filter(marker);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3,
            final Object p4, final Object p5, final Object p6) {
        return filter(marker);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3,
            final Object p4, final Object p5, final Object p6,
            final Object p7) {
        return filter(marker);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3,
            final Object p4, final Object p5, final Object p6,
            final Object p7, final Object p8) {
        return filter(marker);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3,
            final Object p4, final Object p5, final Object p6,
            final Object p7, final Object p8, final Object p9) {
        return filter(marker);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Create the MarkerFilter.
     * @param marker The Marker name to match.
     * @param match The action to take if a match occurs.
     * @param mismatch The action to take if no match occurs.
     * @return A MarkerFilter.
     */
    @PluginFactory
    public static MarkerFilter createFilter(
            @PluginAttribute("marker") final String marker,
            @PluginAttribute("onMatch") final Result match,
            @PluginAttribute("onMismatch") final Result mismatch) {

        if (marker == null) {
            LOGGER.error("A marker must be provided for MarkerFilter");
            return null;
        }
        return new MarkerFilter(marker, match, mismatch);
    }

}
