package com.nitsenkov;

import com.nitsenkov.entity.Account;
import com.nitsenkov.entity.Card;
import com.nitsenkov.entity.Payment;
import com.nitsenkov.entity.User;
import com.nitsenkov.entity.enums.AccountType;
import com.nitsenkov.entity.enums.Currency;
import com.nitsenkov.entity.enums.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import static java.math.BigDecimal.valueOf;

public class HibernateRunner {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(Card.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            User user = User.builder()
                    .email("alex.smith@gmail.com")
                    .name("alex")
                    .surname("smith")
                    .build();

            Account account = Account.builder()
                    .currency(Currency.UAE)
                    .minorAmount(valueOf(1000L))
                    .number("12")
                    .ownerId(user)
                    .status(Status.ACTIVE)
                    .type(AccountType.DEBIT)
                    .build();

            session.persist(user);
            session.getTransaction().commit();
        }
    }
}
