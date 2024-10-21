package com.nitsenkov.configuration;

import com.nitsenkov.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
@ComponentScan(basePackages = "com.nitsenkov")
public class ApplicationConfiguration {

    @Bean
    public SessionFactory getSessionFactory() {
        return HibernateTestUtil.buildSessionFactory();
    }

    @Bean
    public Session getProxySession(SessionFactory sessionFactory) {
        return (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
    }
}
