package Controllers;

import Helpers.HelperFunctions;
import Helpers.TableActions;
import Models.Budget;
import Models.Daily;
import Models.Monthly;
import Models.Yearly;
import Repository.BudgetRepository;
import Repository.BuyGoldRepository;
import Repository.CreditPaymentRepository;
import Repository.DailyReportRepository;
import Repository.MonthlyReportRepository;
import Repository.PaymentsRepository;
import Repository.YearlyReportRepository;
import java.time.Month;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nyark
 */
public class DashboardController {

    MonthlyReportRepository monthlyReportRepository = new MonthlyReportRepository();
    YearlyReportRepository yearlyReportRepository = new YearlyReportRepository();
    DailyReportRepository dailyReportRepository = new DailyReportRepository();
    BuyGoldRepository buyGoldRepository = new BuyGoldRepository();
    PaymentsRepository paymentsRepository = new PaymentsRepository();
    BudgetRepository budgetRepository = new BudgetRepository();
    CreditPaymentRepository creditPaymentRepository = new CreditPaymentRepository();

    ReportController reportController = new ReportController();

    HelperFunctions helper = new HelperFunctions();

    public void dashboardData(
            String createdDate,
            JTextField totalGoldBought,
            JTextField totalGoldPayments,
            JTextField totalGoldBalance,
            JTextField totalDailyBudget,
            JTextField totalBudgetUsed,
            JTextField txtTotalBudgetBalance,
            JTextField txtBudgetUsedPayment,
            JTextField txtBudgetUsedCredit,
            JTextField txtBudgetUsedExpense
    ) {

        double total_gold_bought = buyGoldRepository.summationDaily(createdDate);
        double total_payments_gold = paymentsRepository.summationOfPayments(createdDate);
        double total_used_to_pay_credit = creditPaymentRepository.summationAmountPaidToday(createdDate, 0);
        double totalPayments = total_payments_gold + total_used_to_pay_credit;
        
        
        
        
        Budget budget = budgetRepository.todayBudget(helper.returnDate());
        double budget_used = reportController.budgetUsedAll(budget.getId());
        double budgetUsedOnPayment = reportController.budgetUsedOnPayment(budget.getId());
        double budgetUsedOnCredit = reportController.budgetUsedOnCredit(budget.getId());
        double budgetUsedOnExpenses = reportController.budgetUsedOnExpenses(budget.getId());

        totalGoldBought.setText(helper.priceToString(total_gold_bought));
        totalGoldPayments.setText(helper.priceToString(totalPayments));
        totalGoldBalance.setText(helper.priceToString(total_gold_bought - totalPayments));
        totalDailyBudget.setText(helper.priceToString(budget.getTotal_amount()));
        totalBudgetUsed.setText(helper.priceToString(budget_used));
        txtTotalBudgetBalance.setText(helper.priceToString(budget.getTotal_amount() - budget_used));
        txtBudgetUsedPayment.setText(helper.priceToString(budgetUsedOnPayment));
        txtBudgetUsedCredit.setText(helper.priceToString(budgetUsedOnCredit));
        txtBudgetUsedExpense.setText(helper.priceToString(budgetUsedOnExpenses));
    }

    public void populateMonthlyTable(JTable table, String year, boolean showButtonColumn) {
        if (year.equals("")) {
            year = helper.returnCurrentYear();
        }
        List<Monthly> monthlys = monthlyReportRepository.monthlyPurchasesReport(year);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Monthly monthly : monthlys) {
            double totalPayment = this.getMonthlyPayment(year, monthly.getMonth());

            if (showButtonColumn) {
                object = new Object[]{
                    monthly.getMonth(),
                    Month.of(Integer.parseInt(monthly.getMonth())),
                    helper.priceToString(monthly.getTotal()),
                    helper.priceToString(totalPayment),
                    helper.priceToString(monthly.getTotal() - totalPayment),
                    TableActions.Daily.toString()
                };

            } else {
                object = new Object[]{
                    monthly.getMonth(),
                    Month.of(Integer.parseInt(monthly.getMonth())),
                    helper.priceToString(monthly.getTotal()),
                    helper.priceToString(totalPayment),
                    helper.priceToString(monthly.getTotal() - totalPayment)
                };
            }

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    private double getMonthlyPayment(String year, String month) {
        double total = monthlyReportRepository.monthlyPaymentTotal(year, month);
        return total;
    }

    public void populateYearlyTable(JTable table) {
        List<Yearly> yearlys = yearlyReportRepository.yearlyPurchaseReport();

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Yearly yearly : yearlys) {
            double totalPayment = this.getYearlyPayment(yearly.getYear());

            object = new Object[]{
                yearly.getYear(),
                helper.priceToString(yearly.getTotal()),
                helper.priceToString(totalPayment),
                helper.priceToString(yearly.getTotal() - totalPayment),
                TableActions.Monthly.toString()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    private double getYearlyPayment(String year) {
        double total = yearlyReportRepository.yearlyPaymentTotal(year);
        return total;
    }

    public void populateDailyTable(JTable table, String month, String year) {
        List<Daily> dailys = dailyReportRepository.dailyPurchasesReport(month, year);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Daily daily : dailys) {
            double totalPayment = this.getDailyPayment(year, month, daily.getDay());

            object = new Object[]{
                daily.getDay(),
                helper.priceToString(daily.getTotal()),
                helper.priceToString(totalPayment),
                helper.priceToString(daily.getTotal() - totalPayment)
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    private double getDailyPayment(String year, String month, String day) {
        double total = dailyReportRepository.dailyPaymentTotal(year, month, day);
        return total;
    }

}
