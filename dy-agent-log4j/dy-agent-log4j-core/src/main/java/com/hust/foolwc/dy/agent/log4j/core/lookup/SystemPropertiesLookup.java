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
package com.hust.foolwc.dy.agent.log4j.core.lookup;

import com.hust.foolwc.dy.agent.log4j.Logger;
import com.hust.foolwc.dy.agent.log4j.Marker;
import com.hust.foolwc.dy.agent.log4j.MarkerManager;
import com.hust.foolwc.dy.agent.log4j.core.LogEvent;
import com.hust.foolwc.dy.agent.log4j.core.config.plugins.Plugin;
import com.hust.foolwc.dy.agent.log4j.status.StatusLogger;

import com.hust.foolwc.dy.agent.log4j.Logger;
import com.hust.foolwc.dy.agent.log4j.Marker;
import com.hust.foolwc.dy.agent.log4j.MarkerManager;

/**
 * Looks up keys from system properties.
 */
@Plugin(name = "sys", category = StrLookup.CATEGORY)
public class SystemPropertiesLookup extends AbstractLookup {

    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final Marker LOOKUP = MarkerManager.getMarker("LOOKUP");

    /**
     * Looks up the value for the key using the data in the LogEvent.
     * @param event The current LogEvent.
     * @param key  the key to be looked up, may be null
     * @return The value associated with the key.
     */
    @Override
    public String lookup(final LogEvent event, final String key) {
        try {
            return System.getProperty(key);
        } catch (final Exception ex) {
            LOGGER.warn(LOOKUP, "Error while getting system property [{}].", key, ex);
            return null;
        }
    }
}
