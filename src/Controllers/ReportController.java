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
        double budgetUsedOnCredit = creditRepository.summationOfBudget(id);
        double budgetUsedOnCreditWithoutRefund = creditRepository.summationByCreditPayment(id, 1);

        double budgetOnCredit = budgetUsedOnCredit - budgetUsedOnCreditWithoutRefund;
        double budgetUsedOnPayment = paymentsRepository.summationOfBudget(id);
        double budgetUsedOnExpenses = expensesRepository.summationOfBudgetUsed(id);

        return budgetOnCredit + budgetUsedOnPayment + budgetUsedOnExpenses;
    }

    public double budgetUsedOnCredit(int id) {
        double budgetUsedOnCredit = creditRepository.summationOfBudget(id);
        double budgetUsedOnCreditWithoutRefund = creditRepository.summationByCreditPayment(id, 1);
        return budgetUsedOnCredit - budgetUsedOnCreditWithoutRefund;
    }

    public double budgetUsedOnPayment(int id) {
        double budgetUsedOnPayment = paymentsRepository.summationOfBudget(id);
        return budgetUsedOnPayment;
    }

    public double budgetUsedOnExpenses(int id) {
        double budgetUsedOnExpenses = expensesRepository.summationOfBudgetUsed(id);
        return budgetUsedOnExpenses;
    }

}
