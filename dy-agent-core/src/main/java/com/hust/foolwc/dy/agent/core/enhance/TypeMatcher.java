package com.hust.foolwc.dy.agent.core.enhance;

import com.hust.foolwc.dy.agent.core.match.ClassMatch;
import com.hust.foolwc.dy.agent.core.match.IndirectMatch;
import com.hust.foolwc.dy.agent.core.match.MultiNameOrMatch;
import com.hust.foolwc.dy.agent.core.match.NameMatch;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

import static net.bytebuddy.matcher.ElementMatchers.isInterface;
import static net.bytebuddy.matcher.ElementMatchers.nameContains;
import static net.bytebuddy.matcher.ElementMatchers.nameStartsWith;
import static net.bytebuddy.matcher.ElementMatchers.not;

/**
 * @Author: foolwc
 * @Date: 2019/8/7 14:02
 */
public enum TypeMatcher {
    INSTANCE;

    private final String BYTEBUDDY_AUXILIARY = "auxiliary";
    private Set<String> nameMatchSet = new HashSet<>(16);

    private ElementMatcher.Junction<NamedElement> ignoreRule = nameStartsWith("java.")
            .or(nameStartsWith("javax."))
            .or(nameStartsWith("com.intellij.rt."))
            .or(nameStartsWith("sun."))
            .or(nameStartsWith("third."))
            .or(nameStartsWith("org.slf4j."))
            .or(nameStartsWith("com.uber."))
            .or(nameStartsWith("com.alibaba.fastjson."))
            .or(nameStartsWith("com.alibaba.dubbo.cache."))
            .or(nameStartsWith("com.alibaba.dubbo.common."))
            .or(nameStartsWith("com.alibaba.dubbo.config."))
            .or(nameStartsWith("com.alibaba.dubbo.container."))
            .or(nameStartsWith("com.alibaba.dubbo.registry."))
            .or(nameStartsWith("com.alibaba.dubbo.remoting."))
            .or(nameStartsWith("com.alibaba.dubbo.rpc."))
            .or(nameStartsWith("com.alibaba.dubbo.validation."))
            .or(nameStartsWith("org.apache.http.auth."))
            .or(nameStartsWith("org.apache.http.client."))
            .or(nameStartsWith("org.apache.http.conn."))
            .or(nameStartsWith("org.apache.http.cookie."))
            .or(nameStartsWith("org.apache.http.impl.auth."))
            .or(nameStartsWith("org.apache.http.impl.conn."))
            .or(nameStartsWith("org.apache.http.impl.cookie."))
            .or(nameStartsWith("org.apache.http.impl.execchain."))
            .or(nameStartsWith("org.apache.http.impl.client.cache."))
            .or(nameStartsWith("org.apache.http.annotation."))
            .or(nameStartsWith("org.apache.http.concurrent."))
            .or(nameStartsWith("org.apache.http.config."))
            .or(nameStartsWith("org.apache.http.entity."))
            .or(nameStartsWith("org.apache.http.io."))
            .or(nameStartsWith("org.apache.http.message."))
            .or(nameStartsWith("org.apache.http.params."))
            .or(nameStartsWith("org.apache.http.pool."))
            .or(nameStartsWith("org.apache.http.protocol."))
            .or(nameStartsWith("org.apache.http.ssl."))
            .or(nameStartsWith("org.apache.http.util."))
            .or(nameStartsWith("org.apache.http.impl.bootstrap."))
            .or(nameStartsWith("org.apache.http.impl.entity."))
            .or(nameStartsWith("org.apache.http.impl.io."))
            .or(nameStartsWith("org.apache.http.impl.pool."))
            .or(nameStartsWith("com.mysql.jdbc.authentication."))
            .or(nameStartsWith("com.mysql.jdbc.configs."))
            .or(nameStartsWith("com.mysql.jdbc.exceptions."))
            .or(nameStartsWith("com.mysql.jdbc.integration."))
            .or(nameStartsWith("com.mysql.jdbc.interceptors."))
            .or(nameStartsWith("com.mysql.jdbc.jdbc2."))
            .or(nameStartsWith("com.mysql.jdbc.jmx."))
            .or(nameStartsWith("com.mysql.jdbc.log."))
            .or(nameStartsWith("com.mysql.jdbc.profiler."))
            .or(nameStartsWith("com.mysql.jdbc.util."))
            .or(nameStartsWith("org.springframework.boot.admin."))
            .or(nameStartsWith("org.springframework.boot.ansi."))
            .or(nameStartsWith("org.springframework.boot.bind."))
            .or(nameStartsWith("org.springframework.boot.builder."))
            .or(nameStartsWith("org.springframework.boot.cloud."))
            .or(nameStartsWith("org.springframework.boot.context.annotation."))
            .or(nameStartsWith("org.springframework.boot.context.config."))
            .or(nameStartsWith("org.springframework.boot.context.embedded.undertow."))
            .or(nameStartsWith("org.springframework.boot.context.event."))
            .or(nameStartsWith("org.springframework.boot.context.properties."))
            .or(nameStartsWith("org.springframework.boot.diagnostics."))
            .or(nameStartsWith("org.springframework.boot.env."))
            .or(nameStartsWith("org.springframework.boot.info."))
            .or(nameStartsWith("org.springframework.boot.jackson."))
            .or(nameStartsWith("org.springframework.boot.jdbc."))
            .or(nameStartsWith("org.springframework.boot.json."))
            .or(nameStartsWith("org.springframework.boot.jta."))
            .or(nameStartsWith("org.springframework.boot.lang."))
            .or(nameStartsWith("org.springframework.boot.liquibase."))
            .or(nameStartsWith("org.springframework.boot.logging."))
            .or(nameStartsWith("org.springframework.boot.orm."))
            .or(nameStartsWith("org.springframework.boot.system."))
            .or(nameStartsWith("org.springframework.boot.validation."))
            .or(nameStartsWith("org.springframework.boot.yaml."))
            .or(nameStartsWith("org.springframework.boot.autoconfigure."))
            .or(nameStartsWith("org.springframework.boot.test."))
            .or(nameStartsWith("org.springframework.data."))
            .or(nameStartsWith("org.springframework.aop."))
            .or(nameStartsWith("org.springframework.beans."))
            .or(nameStartsWith("org.springframework.context."))
            .or(nameStartsWith("org.springframework.cache."))
            .or(nameStartsWith("org.springframework.ejb."))
            .or(nameStartsWith("org.springframework.format."))
            .or(nameStartsWith("org.springframework.instrument."))
            .or(nameStartsWith("org.springframework.jmx."))
            .or(nameStartsWith("org.springframework.jndi."))
            .or(nameStartsWith("org.springframework.remoting."))
            .or(nameStartsWith("org.springframework.scheduling."))
            .or(nameStartsWith("org.springframework.scripting."))
            .or(nameStartsWith("org.springframework.ui."))
            .or(nameStartsWith("org.springframework.mail."))
            .or(nameStartsWith("org.springframework.validation."))
            .or(nameStartsWith("org.springframework.beansbeans."))
            .or(nameStartsWith("org.springframework.asm."))
            .or(nameStartsWith("org.springframework.cglib."))
            .or(nameStartsWith("org.springframework.core."))
            .or(nameStartsWith("org.springframework.lang."))
            .or(nameStartsWith("org.springframework.objenesis."))
            .or(nameStartsWith("org.springframework.util."))
            .or(nameStartsWith("org.springframework.expression."))
            .or(nameStartsWith("org.springframework.jdbc."))
            .or(nameStartsWith("org.springframework.oxm."))
            .or(nameStartsWith("org.springframework.mock."))
            .or(nameStartsWith("org.springframework.dao."))
            .or(nameStartsWith("org.springframework.jca."))
            .or(nameStartsWith("org.springframework.transaction."))
            .or(nameStartsWith("org.springframework.http."))
            .or(nameStartsWith("org.springframework.remoting."))
            .or(nameStartsWith("org.springframework.web.accept."))
            .or(nameStartsWith("org.springframework.web.bind."))
            .or(nameStartsWith("org.springframework.web.client."))
            .or(nameStartsWith("org.springframework.web.context."))
            .or(nameStartsWith("org.springframework.web.cors."))
            .or(nameStartsWith("org.springframework.web.filter."))
            .or(nameStartsWith("org.springframework.web.jsf."))
            .or(nameStartsWith("org.springframework.web.method."))
            .or(nameStartsWith("org.springframework.web.multipart."))
            .or(nameStartsWith("org.springframework.web.util."))
            .or(nameStartsWith("org.springframework.web.servlet.config."))
            .or(nameStartsWith("org.springframework.web.servlet.handler."))
            .or(nameStartsWith("org.springframework.web.servlet.i18n."))
            .or(nameStartsWith("org.springframework.web.servlet.resource."))
            .or(nameStartsWith("org.springframework.web.servlet.support."))
            .or(nameStartsWith("org.springframework.web.servlet.tags."))
            .or(nameStartsWith("org.springframework.web.servlet.theme."))
            .or(nameStartsWith("org.springframework.web.servlet.view."))
            .or(nameStartsWith("com.caucho."))
            .or(nameStartsWith("com.fasterxml."))
            .or(nameStartsWith("com.google."))
            .or(nameStartsWith("com.jayway."))
            .or(nameStartsWith("org.jboss."))
            .or(nameStartsWith("org.apache.log4j."))
            .or(nameStartsWith("net.minidev."))
            .or(nameStartsWith("skyscreamer."))
            .or(nameStartsWith("org.objectweb."))
            .or(nameStartsWith("org.apache.curator."))
            .or(nameStartsWith("org.apache.jute."))
            .or(nameStartsWith("org.apache.juli."))
            .or(nameStartsWith("org.apache.zookeeper."))
            .or(nameStartsWith("org.aspectj."))
            .or(nameStartsWith("org.apache.ibatis."))
            .or(nameStartsWith("org.mybatis."))
            .or(nameStartsWith("org.nutz."))
            .or(nameStartsWith("org.objenesis."))
            .or(nameStartsWith("aj.org."))
            .or(nameStartsWith("org.mockito."))
            .or(nameStartsWith("org.hamcrest."))
            .or(nameStartsWith("javassist."))
            .or(nameStartsWith("org.hibernate."))
            .or(nameStartsWith("org.apache.logging.log4j.message."))
            .or(nameStartsWith("org.apache.logging.log4j.spi."))
            .or(nameStartsWith("org.apache.logging.log4j.status."))
            .or(nameStartsWith("org.apache.logging.log4j.util."))
            .or(nameStartsWith("org.apache.logging.log4j.core.appender."))
            .or(nameStartsWith("org.apache.logging.log4j.core.async."))
            .or(nameStartsWith("org.apache.logging.log4j.core.config."))
            .or(nameStartsWith("org.apache.logging.log4j.core.filter."))
            .or(nameStartsWith("org.apache.logging.log4j.core.impl."))
            .or(nameStartsWith("org.apache.logging.log4j.core.jackson."))
            .or(nameStartsWith("org.apache.logging.log4j.core.jmx."))
            .or(nameStartsWith("org.apache.logging.log4j.core.layout."))
            .or(nameStartsWith("org.apache.logging.log4j.core.lookup."))
            .or(nameStartsWith("org.apache.logging.log4j.core.net."))
            .or(nameStartsWith("org.apache.logging.log4j.core.osgi."))
            .or(nameStartsWith("org.apache.logging.log4j.core.pattern."))
            .or(nameStartsWith("org.apache.logging.log4j.core.script."))
            .or(nameStartsWith("org.apache.logging.log4j.core.selector."))
            .or(nameStartsWith("org.apache.logging.log4j.core.tools."))
            .or(nameStartsWith("org.apache.logging.log4j.core.util."))
            .or(nameStartsWith("org.apache.commons."))
            .or(nameStartsWith("org.drools."))
            .or(nameStartsWith("defaultpkg."))
            .or(nameContains(BYTEBUDDY_AUXILIARY));

    public ElementMatcher.Junction<NamedElement> ignoreRule() {
        return ignoreRule;
    }

    public ElementMatcher<? super TypeDescription> passRule(List<Interceptor> itrcpts) {
        ElementMatcher.Junction<TypeDescription> judge = new ElementMatcher.Junction<TypeDescription>() {
            @Override
            public <U extends TypeDescription> Junction<U> and(ElementMatcher<? super U> elementMatcher) {
                return new Conjunction<>(this, elementMatcher);
            }

            @Override
            public <U extends TypeDescription> Junction<U> or(ElementMatcher<? super U> elementMatcher) {
                return new Disjunction<>(this, elementMatcher);
            }

            @Override
            public boolean matches(TypeDescription target) {
                return nameMatchSet.contains(target.getActualName());
            }
        };

        judge = judge.and(not(isInterface()));
        for (Interceptor itrcpt : itrcpts) {
            ClassMatch match = itrcpt.focusOn();
            if (match instanceof IndirectMatch) {
                judge = judge.or(((IndirectMatch) match).buildJunction());
            } else if (match instanceof NameMatch) {
                nameMatchSet.add(((NameMatch) match).getClassName());
            } else if (match instanceof MultiNameOrMatch) {
                nameMatchSet.addAll(((MultiNameOrMatch) match).getClassNames());
            }
        }
        return judge;
    }
}

