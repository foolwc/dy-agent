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

import java.util.Map;

import com.hust.foolwc.dy.agent.log4j.core.LogEvent;
import com.hust.foolwc.dy.agent.log4j.core.config.Configuration;
import com.hust.foolwc.dy.agent.log4j.core.config.Node;
import com.hust.foolwc.dy.agent.log4j.core.config.plugins.PluginBuilderAttribute;
import com.hust.foolwc.dy.agent.log4j.core.util.NameUtil;
import com.hust.foolwc.dy.agent.log4j.util.StringBuilders;

/**
 * PluginVisitor for PluginBuilderAttribute. If {@code null} is returned for the
 * {@link #visit(com.hust.foolwc.dy.agent.log4j.core.config.Configuration, com.hust.foolwc.dy.agent.log4j.core.config.Node, com.hust.foolwc.dy.agent.log4j.core.LogEvent, StringBuilder)}
 * method, then the default value of the field should remain untouched.
 *
 * @see com.hust.foolwc.dy.agent.log4j.core.config.plugins.util.PluginBuilder
 */
public class PluginBuilderAttributeVisitor extends AbstractPluginVisitor<PluginBuilderAttribute> {

    public PluginBuilderAttributeVisitor() {
        super(PluginBuilderAttribute.class);
    }

    @Override
    public Object visit(final Configuration configuration, final Node node, final LogEvent event,
                        final StringBuilder log) {
        final String overridden = this.annotation.value();
        final String name = overridden.isEmpty() ? this.member.getName() : overridden;
        final Map<String, String> attributes = node.getAttributes();
        final String rawValue = removeAttributeValue(attributes, name, this.aliases);
        final String replacedValue = this.substitutor.replace(event, rawValue);
        final Object value = convert(replacedValue, null);
        final Object debugValue = this.annotation.sensitive() ? NameUtil.md5(value + this.getClass().getName()) : value;
        StringBuilders.appendKeyDqValue(log, name, debugValue);
        return value;
    }
}
