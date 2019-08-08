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

package com.hust.foolwc.dy.agent.log4j.core.config.plugins.visitors;

import com.hust.foolwc.dy.agent.log4j.core.LogEvent;
import com.hust.foolwc.dy.agent.log4j.core.config.Configuration;
import com.hust.foolwc.dy.agent.log4j.core.config.Node;
import com.hust.foolwc.dy.agent.log4j.core.config.plugins.PluginValue;
import com.hust.foolwc.dy.agent.log4j.util.StringBuilders;
import com.hust.foolwc.dy.agent.log4j.util.Strings;

/**
 * PluginVisitor implementation for {@link PluginValue}.
 */
public class PluginValueVisitor extends AbstractPluginVisitor<PluginValue> {
    public PluginValueVisitor() {
        super(PluginValue.class);
    }

    @Override
    public Object visit(final Configuration configuration, final Node node, final LogEvent event,
            final StringBuilder log) {
        final String name = this.annotation.value();
        final String elementValue = node.getValue();
        final String attributeValue = node.getAttributes().get("value");
        String rawValue = null; // if neither is specified, return null (LOG4J2-1313)
        if (Strings.isNotEmpty(elementValue)) {
            if (Strings.isNotEmpty(attributeValue)) {
                LOGGER.error("Configuration contains {} with both attribute value ({}) AND element" +
                                " value ({}). Please specify only one value. Using the element value.",
                        node.getName(), attributeValue, elementValue);
            }
            rawValue = elementValue;
        } else {
            rawValue = removeAttributeValue(node.getAttributes(), "value");
        }
        final String value = this.substitutor.replace(event, rawValue);
        StringBuilders.appendKeyDqValue(log, name, value);
        return value;
    }
}
