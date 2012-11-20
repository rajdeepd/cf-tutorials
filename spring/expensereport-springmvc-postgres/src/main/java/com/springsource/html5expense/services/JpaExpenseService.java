package com.springsource.html5expense.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.model.State;
import com.springsource.html5expense.model.User;

@Service
public class JpaExpenseService implements ExpenseService {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Long createExpense(String description, ExpenseType expenseType, Date expenseDate,
            Double amount, User user) {
        Expense expense = new Expense(description, expenseType, expenseDate, amount, user);
        entityManager.persist(expense);
        return expense.getId();
    }

    @Transactional
    public Expense getExpense(Long expenseId) {
        return entityManager.find(Expense.class, expenseId);
    }

    @Override
    @Transactional
    public List<Expense> getAllExpenses() {
        Query query = getEntityManager().createQuery("select e from Expense e");
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Expense> getExpensesByUser(User user) {
        Query query = getEntityManager().createQuery("select e from Expense e where "
           + "e.user.userId =:userId ORDER BY e.expenseDate DESC", Expense.class);
        query.setParameter("userId", user.getUserId());
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Expense> getPendingExpensesList() {
        List<Enum<State>> state = new ArrayList<Enum<State>>();
        state.add(State.NEW);
        state.add(State.OPEN);
        state.add(State.IN_REVIEW);
        Query query = entityManager.createQuery("select e from Expense e where "
         + "e.state IN (:state) ORDER BY e.expenseDate DESC", Expense.class);
        query.setParameter("state", state);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Expense> getApprovedAndRejectedExpensesList() {
        List<Enum<State>> state = new ArrayList<Enum<State>>();
        state.add(State.REJECTED);
        state.add(State.APPROVED);
        Query query = entityManager.createQuery("select e from Expense e where "
           + "e.state IN (:state) e.expenseDate DESC", Expense.class);
        query.setParameter("state", state);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Expense changeExpenseStatus(Long expenseId, String state) {
        Expense expense = getExpense(expenseId);
        expense.setState(State.valueOf(state));
        getEntityManager().merge(expense);
        return expense;
    }

    @Override
    @Transactional
    public void deleteExpense(Long expenseId) {
        Expense expense = getExpense(expenseId);
        getEntityManager().remove(expense);
    }

    @Override
    @Transactional
    public void updateExpense(Expense expense) {
        getEntityManager().merge(expense);
    }
}
