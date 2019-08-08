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
 * This filter returns the {@code onMatch} result if the level in the {@code LogEvent} is in the range of the configured
 * min and max levels, otherwise it returns {@code onMismatch} value . For example, if the filter is configured with
 * {@link Level#ERROR} and {@link Level#INFO} and the LogEvent contains {@link Level#WARN} then the onMatch value will
 * be returned since {@link Level#WARN WARN} events are in between {@link Level#ERROR ERROR} and {@link Level#INFO
 * INFO}.
 * <p>
 * The default Levels are both {@link Level#ERROR ERROR}.
 * </p>
 */
@Plugin(name = "LevelRangeFilter", category = Node.CATEGORY, elementType = Filter.ELEMENT_TYPE, printObject = true)
@PerformanceSensitive("allocation")
public final class LevelRangeFilter extends AbstractFilter {

    /**
     * Create a ThresholdFilter.
     *
     * @param minLevel
     *            The minimum log Level.
     * @param maxLevel
     *            The maximum log Level.
     * @param match
     *            The action to take on a match.
     * @param mismatch
     *            The action to take on a mismatch.
     * @return The created ThresholdFilter.
     */
    @PluginFactory
    public static LevelRangeFilter createFilter(
            // @formatter:off
            @PluginAttribute("minLevel") final Level minLevel,
            @PluginAttribute("maxLevel") final Level maxLevel,
            @PluginAttribute("onMatch") final Result match,
            @PluginAttribute("onMismatch") final Result mismatch) {
            // @formatter:on
        final Level actualMinLevel = minLevel == null ? Level.ERROR : minLevel;
        final Level actualMaxLevel = maxLevel == null ? Level.ERROR : maxLevel;
        final Result onMatch = match == null ? Result.NEUTRAL : match;
        final Result onMismatch = mismatch == null ? Result.DENY : mismatch;
        return new LevelRangeFilter(actualMinLevel, actualMaxLevel, onMatch, onMismatch);
    }
    private final Level maxLevel;

    private final Level minLevel;

    private LevelRangeFilter(final Level minLevel, final Level maxLevel, final Result onMatch, final Result onMismatch) {
        super(onMatch, onMismatch);
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    private Result filter(final Level level) {
        return level.isInRange(this.minLevel, this.maxLevel) ? onMatch : onMismatch;
    }

    @Override
    public Result filter(final LogEvent event) {
        return filter(event.getLevel());
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Message msg,
            final Throwable t) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Object msg,
            final Throwable t) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object... params) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3,
            final Object p4) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3,
            final Object p4, final Object p5) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3,
            final Object p4, final Object p5, final Object p6) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3,
            final Object p4, final Object p5, final Object p6,
            final Object p7) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3,
            final Object p4, final Object p5, final Object p6,
            final Object p7, final Object p8) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
            final Object p0, final Object p1, final Object p2, final Object p3,
            final Object p4, final Object p5, final Object p6,
            final Object p7, final Object p8, final Object p9) {
        return filter(level);
    }

    public Level getMinLevel() {
        return minLevel;
    }

    @Override
    public String toString() {
        return minLevel.toString();
    }

}
