package Controllers;

import Helpers.HelperFunctions;
import Models.BuyGold;
import Repository.BudgetRepository;
import Repository.BuyGoldRepository;
import Repository.CreditPaymentRepository;
import Repository.CreditRepository;
import Repository.ExpensesRepository;
import Repository.PaymentsRepository;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author nyark
 */
public class ReportController {

    BuyGoldRepository buyGoldRepository = new BuyGoldRepository();
    PaymentsRepository paymentsRepository = new PaymentsRepository();
    BudgetRepository budgetRepository = new BudgetRepository();
    CreditRepository creditRepository = new CreditRepository();
    ExpensesRepository expensesRepository = new ExpensesRepository();
    CreditPaymentRepository creditPaymentRepository = new CreditPaymentRepository();

    HelperFunctions helper = new HelperFunctions();

    public void dashboardData(
            String startDate,
            String endDate,
            JTextField totalBudgetText,
            JTextField totalGoldBoughtText,
            JTextField totalPaymentsText,
            JTextField totalCreditsText,
            JTextField totalExpensesText,
            JTextField totalCreditPaymentText,
            JTextField totalPaymentBalanceText,
            JTextField totalCreditBalanceText,
            JTextField totalGoldPaymentFromBudgetText,
            JTextField totalCredittersBalanceText,
            JTextField totalBudgetExpensesText,
            JTextField totalBudgetExpensesLeft,
            JTextField totalBudgetLeft,
            JTextField profitBudgetLeft,
            JTextField profitGoldPaymentBalance,
            JTextField txtTotalBugetFromIncome
    ) {

        if (startDate.isEmpty() && endDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select both start date and end date");
            return;
        }

        double totalBudget = budgetRepository.budgetSummationBetweenDates(startDate, endDate);

        double totalGoldBought = buyGoldRepository.buyGoldSummationBetweenDates(startDate, endDate);
        double totalPayments = paymentsRepository.paymentSummationBetweenDates(startDate, endDate);
        double totalPaymentBalance = totalGoldBought - totalPayments;

        double totalExpenses = expensesRepository.expensesSummationBetweenDates(startDate, endDate);

        double totalCredits = creditRepository.paymentSummationBetweenDates(startDate, endDate);
        double totalCreditPayment = creditPaymentRepository.creditPaymentSummationBetweenDates(startDate, endDate);
        double totalCreditBalance = totalCredits - totalCreditPayment;

        // Total budget useage
        double budgetUsed = totalPayments + totalCreditBalance + totalExpenses;

        // Budget left after expenses
        double budgetLeftAfterExpenses = totalBudget - budgetUsed;

        totalBudgetText.setText(helper.priceToString(totalBudget));
        totalGoldBoughtText.setText(helper.priceToString(totalGoldBought));
        totalPaymentsText.setText(helper.priceToString(totalPayments));
        totalGoldPaymentFromBudgetText.setText(helper.priceToString(totalPayments));
        totalCreditsText.setText(helper.priceToString(totalCredits));
        totalExpensesText.setText(helper.priceToString(totalExpenses));
        totalCreditPaymentText.setText(helper.priceToString(totalCreditPayment));
        totalPaymentBalanceText.setText(helper.priceToString(totalPaymentBalance));
        totalCreditBalanceText.setText(helper.priceToString(totalCreditBalance));
        totalCredittersBalanceText.setText(helper.priceToString(totalCreditBalance));
        totalBudgetExpensesText.setText(helper.priceToString(budgetUsed));

        totalBudgetExpensesLeft.setText(helper.priceToString(budgetUsed));
        totalBudgetLeft.setText(helper.priceToString(budgetLeftAfterExpenses));

        profitBudgetLeft.setText(helper.priceToString(budgetLeftAfterExpenses));
        profitGoldPaymentBalance.setText(helper.priceToString(totalPaymentBalance));

        txtTotalBugetFromIncome.setText(helper.priceToString(totalBudget));
    }

    public void calculateGoldDetails(
            String startDate,
            String endDate,
            JTextField topText,
            JTextField downText,
            JTextField densityText,
            JTextField karatText,
            JTextField poundsText,
            JTextField totalWeightText,
            JTextField totalAmountText
    ) {
        if (startDate.isEmpty() && endDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select both start date and end date");
            return;
        }

        BuyGold buyGold = buyGoldRepository.goldSummation(startDate, endDate);
        topText.setText(String.valueOf(buyGold.getTop()));
        downText.setText(String.valueOf(buyGold.getDown()));
        densityText.setText(String.valueOf(buyGold.getDensity()));
        karatText.setText(String.valueOf(buyGold.getKarat()));
        poundsText.setText(String.valueOf(buyGold.getPounds()));
        totalWeightText.setText(String.valueOf(buyGold.getTotal_weight()));
        totalAmountText.setText(helper.priceToString(buyGold.getTotal_amount()));
    }

}
