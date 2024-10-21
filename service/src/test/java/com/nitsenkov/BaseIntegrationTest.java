package com.nitsenkov;

import com.nitsenkov.configuration.ApplicationConfiguration;
import com.nitsenkov.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Proxy;

public abstract class BaseIntegrationTest {

    private static SessionFactory sessionFactory;
    protected static AnnotationConfigApplicationContext context;
    protected static Session session;

    @BeforeAll
    static void createContext() {
//        sessionFactory = HibernateTestUtil.buildSessionFactory();
//        session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
//                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
        context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    }

    @AfterAll
    static void closeSessionFactory() {
        sessionFactory.close();
    }

    @BeforeEach
    void openSession() {
        session = context.getBean(Session.class);
        session.beginTransaction();
    }

    @AfterEach
    void closeSession() {
        session.getTransaction().rollback();
        session.close();
    }
}
