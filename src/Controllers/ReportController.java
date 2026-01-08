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

    public double budgetUsedAll(int id) {
        double budgetUsedOnCredit = 0;
        double budgetUsedOnCreditWithoutRefund = 0;

        double budgetOnCredit = budgetUsedOnCredit - budgetUsedOnCreditWithoutRefund;
        double budgetUsedOnPayment = 0;
        double budgetUsedOnExpenses = 0;

        return budgetOnCredit + budgetUsedOnPayment + budgetUsedOnExpenses;
    }

    public double budgetUsedOnCredit(int id) {
        double budgetUsedOnCredit = 0;
        double budgetUsedOnCreditWithoutRefund = 0;
        return budgetUsedOnCredit - budgetUsedOnCreditWithoutRefund;
    }

    public double budgetUsedOnPayment(int id) {
        double budgetUsedOnPayment = 0;
        return budgetUsedOnPayment;
    }

    public double budgetUsedOnExpenses(int id) {
        double budgetUsedOnExpenses = 0;
        return budgetUsedOnExpenses;
    }

}
