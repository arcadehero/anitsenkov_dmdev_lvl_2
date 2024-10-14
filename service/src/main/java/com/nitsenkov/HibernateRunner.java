package com.nitsenkov;

import com.nitsenkov.dao.UserRepository;
import com.nitsenkov.entity.User;
import com.nitsenkov.entity.enums.UserRole;
import com.nitsenkov.util.HibernateUtil;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;

public class HibernateRunner {

    @Transactional
    public static void main(String[] args) {
        User user = User.builder()
                .name("alex")
                .surname("smith")
                .email("alex999.smith@gmail.com")
                .password("pass")
                .role(UserRole.CLIENT)
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = (Session)Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                    ((proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1)));

            session.beginTransaction();

            UserRepository userRepository = new UserRepository(session);
            userRepository.save(user);

            session.getTransaction().commit();
        }
    }
}
