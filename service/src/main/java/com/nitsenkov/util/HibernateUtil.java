package com.nitsenkov.util;

import com.nitsenkov.entity.Account;
import com.nitsenkov.entity.Card;
import com.nitsenkov.entity.Payment;
import com.nitsenkov.entity.User;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass

public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = buildConfiguration();
        configuration.configure();
        return configuration.buildSessionFactory();
    }

    public static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Card.class);
        return configuration;
    }
}
