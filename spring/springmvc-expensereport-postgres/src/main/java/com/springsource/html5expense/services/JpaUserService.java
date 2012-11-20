package com.springsource.html5expense.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springsource.html5expense.model.User;

@Service
public class JpaUserService implements UserService {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User getUserById(Long userId) {
        return getEntityManager().find(User.class, userId);
    }

    @Override
    @Transactional
    public User getUserByUserName(String userName) {
        Query query = getEntityManager().createQuery("from User u where "
                + "u.userName =:username");
        query.setParameter("username", userName);
        return  (query.getResultList() != null && query.getResultList().size() > 0)
                ? (User) (query.getResultList().get(0)) : null;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        getEntityManager().persist(user);
        return user;
    }
}
