package com.springsource.html5expense.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.services.ExpenseTypeService;

@Service
public class JpaExpenseTypeService implements ExpenseTypeService {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ExpenseType> getAllExpenseType() {
        return getEntityManager().createQuery("from ExpenseType", ExpenseType.class).getResultList();
    }

    public ExpenseType getExpenseTypeById(Long id) {
        return getEntityManager().find(ExpenseType.class, id);
    }
}
