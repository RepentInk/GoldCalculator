package Controllers;

import Repository.CreditRepository;
import Repository.ExpensesRepository;
import Repository.PaymentsRepository;

/**
 *
 * @author nyark
 */
public class ReportController {

    PaymentsRepository paymentsRepository = new PaymentsRepository();
    CreditRepository creditRepository = new CreditRepository();
    ExpensesRepository expensesRepository = new ExpensesRepository();

    public double budgetUsedInAll(int id) {
        double budgetUsedOnCredit = creditRepository.summationOfBudget(id);
        double budgetUsedOnPayment = paymentsRepository.summationOfBudget(id);
        double budgetUsedOnExpenses = expensesRepository.summationOfBudgetUsed(id);
        return budgetUsedOnCredit + budgetUsedOnPayment + budgetUsedOnExpenses;
    }

}
