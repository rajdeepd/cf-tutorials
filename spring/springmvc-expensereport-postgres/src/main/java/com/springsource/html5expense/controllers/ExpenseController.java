package com.springsource.html5expense.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.services.ExpenseService;
import com.springsource.html5expense.services.ExpenseTypeService;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseTypeService expenseTypeService;

    private final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @RequestMapping(value = "/expense/{expenseId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExpense(@PathVariable("expenseId") Long expenseId) {
        expenseService.deleteExpense(expenseId);
    }

    @RequestMapping(value = "/expense/{expenseId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Expense getExpense(@PathVariable("expenseId") Long expenseId) {
        Expense expense = expenseService.getExpense(expenseId);
        if (expense != null) {
            logger.info("Get ExpenseReports " + expense.getDescription());
        }
        return expense;
    }

    @ResponseBody
    @RequestMapping(value = "/expense/{expenseId}", method = RequestMethod.PUT)
    public Long updateExpense(@PathVariable("expenseId") Long expenseId,
            @RequestParam("description") String description, @RequestParam("amount") Double amount,
            @RequestParam("expenseTypeId") String expenseTypeVal) {
        Expense expense = expenseService.getExpense(expenseId);
        ExpenseType expenseType = expenseTypeService.getExpenseTypeById(new Long(expenseTypeVal));
        expense.setAmount(amount);
        expense.setExpenseType(expenseType);
        expense.setDescription(description);
        expenseService.updateExpense(expense);
        return expense.getId();
    }

    @ResponseBody
    @RequestMapping(value = "/expense", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Long createNewExpense(@RequestParam("description") String description,
            @RequestParam("amount") Double amount,
            @RequestParam("expenseTypeId") Long expenseTypeVal) {
        ExpenseType expenseType = expenseTypeService.getExpenseTypeById(expenseTypeVal);
        Long expenseId = expenseService.createExpense(description, expenseType, new Date(), amount);
        return expenseId;
    }

    @ResponseBody
    @RequestMapping(value = "/expenses", method = RequestMethod.GET)
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return expenses;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getExpenses(ModelMap model) {
        List<ExpenseType> expenseTypeList = expenseTypeService.getAllExpenseType();
        model.addAttribute("expenseTypeList", expenseTypeList);
        return "expensereports";
    }

    @RequestMapping(value = "/expense/approvals", method = RequestMethod.GET)
    @ResponseBody
    public List<Expense> loadApprovedExpenses() {
        List<Expense> approvedExpenseList = expenseService.getPendingExpensesList();
        return approvedExpenseList;
    }

    @RequestMapping(value = "/expense/{expenseId}/state/{state}", method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void changeState(@PathVariable("expenseId") Long expenseId,
            @PathVariable("state") String state) {
        expenseService.changeExpenseStatus(expenseId, state);
    }
}
