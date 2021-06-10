package com.vladbadey.task4.dao;

import com.vladbadey.task4.models.Login;
import com.vladbadey.task4.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Component
public class UsersDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public UsersDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional(readOnly = true)
    public List<User> showAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from User p", User.class)
                .getResultList();
    }

    @Transactional
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        user.setRegDate(LocalDate.now());
        user.setLastLogIn(LocalDate.now());
        session.save(user);
    }

    @Transactional
    public boolean isUnique(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where email=:email");
        query.setParameter("email", user.getEmail());
        List<User> users = query.getResultList();
        return users.isEmpty();
    }

    @Transactional
    public User validateUser(Login login) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where email=:email and password=:password");
        query.setParameter("email", login.getEmail());
        query.setParameter("password", login.getPassword());
        List<User> users = query.getResultList();

        return users.size() > 0 ? users.get(0) : null;
    }

    @Transactional
    public void blockUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        user = find(user.getId());
        user.setStatus(false);
        session.update(user);

    }

    @Transactional
    public void unblockUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        user = find(user.getId());
        user.setStatus(true);
        session.update(user);
    }

    @Transactional
    public void deleteUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        user = find(user.getId());
        session.delete(user);
    }


    @Transactional
    public User find(int id) {
        for (User u : showAll()) {
            if (u.getId() == id) {
                return u;
            }
        }
        return new User();
    }
}
