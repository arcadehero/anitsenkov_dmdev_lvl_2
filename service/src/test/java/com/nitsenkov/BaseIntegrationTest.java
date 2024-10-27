package com.nitsenkov;

import com.nitsenkov.configuration.TestApplicationConfiguration;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class BaseIntegrationTest {

    protected static Session session;
    protected static AnnotationConfigApplicationContext context;

    @BeforeAll
    static void createContext() {
        context = new AnnotationConfigApplicationContext(TestApplicationConfiguration.class);
    }

    @AfterAll
    static void closeContext() {
        context.close();
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
