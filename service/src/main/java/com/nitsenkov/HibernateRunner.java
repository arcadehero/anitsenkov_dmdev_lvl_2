package com.nitsenkov;

import com.nitsenkov.entity.Account;
import com.nitsenkov.entity.Card;
import com.nitsenkov.entity.Payment;
import com.nitsenkov.entity.User;
import com.nitsenkov.entity.enums.AccountStatus;
import com.nitsenkov.entity.enums.AccountType;
import com.nitsenkov.entity.enums.Currency;
import com.nitsenkov.entity.enums.UserRole;
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

        User user = User.builder()
                .name("alex")
                .surname("smith")
                .email("alex123.smith@gmail.com")
                .password("pass")
                .role(UserRole.CLIENT)
                .build();

        Account account = Account.builder()
                .type(AccountType.DEBIT)
                .currency(Currency.USD)
                .number("123")
                .amount(valueOf(100))
                .user(user)
                .status(AccountStatus.ACTIVE)
                .build();


        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.persist(user);
            session.persist(account);

            session.getTransaction().commit();
        }
    }
}
