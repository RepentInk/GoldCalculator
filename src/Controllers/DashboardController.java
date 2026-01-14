package Controllers;

import Helpers.HelperFunctions;
import Helpers.TableActions;
import Models.Daily;
import Models.Monthly;
import Models.Yearly;
import Repository.BudgetRepository;
import Repository.BuyGoldRepository;
import Repository.CreditPaymentRepository;
import Repository.CreditRepository;
import Repository.DailyReportRepository;
import Repository.ExpensesRepository;
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
    CreditRepository creditRepository = new CreditRepository();
    ExpensesRepository expensesRepository = new ExpensesRepository();
    CreditPaymentRepository creditPaymentRepository = new CreditPaymentRepository();

    HelperFunctions helper = new HelperFunctions();

    public void dashboardData(
            String startDate,
            String currentDate,
            JTextField totalBudgetText,
            JTextField totalGoldBoughtText,
            JTextField totalPaymentsText,
            JTextField totalCreditsText,
            JTextField totalExpensesText,
            JTextField totalCreditPaymentText
    ) {
        double totalBudget = 0, totalGoldBought = 0, totalPayments = 0, totalCredits = 0, totalExpenses = 0, totalCreditPayment = 0;

        if (!startDate.isEmpty() && !currentDate.isEmpty()) {
            totalBudget = budgetRepository.budgetSummationBetweenDates(startDate, currentDate);
            totalGoldBought = buyGoldRepository.buyGoldSummationBetweenDates(startDate, currentDate);
            totalPayments = paymentsRepository.paymentSummationBetweenDates(startDate, currentDate);
            totalCredits = creditRepository.paymentSummationBetweenDates(startDate, currentDate);
            totalExpenses = expensesRepository.expensesSummationBetweenDates(startDate, currentDate);
            totalCreditPayment = creditPaymentRepository.creditPaymentSummationBetweenDates(startDate, currentDate);
        } else if (startDate.isEmpty() && !currentDate.isEmpty()) {
            totalBudget = budgetRepository.budgetSummation(currentDate);
            totalGoldBought = buyGoldRepository.goldBuySummation(currentDate);
            totalPayments = paymentsRepository.paymentSummation(currentDate);
            totalCredits = creditRepository.creditSummation(currentDate);
            totalExpenses = expensesRepository.expensesSummation(currentDate);
            totalCreditPayment = creditPaymentRepository.creditPaymentSummation(currentDate);
        }

        totalBudgetText.setText(helper.priceToString(totalBudget));
        totalGoldBoughtText.setText(helper.priceToString(totalGoldBought));
        totalPaymentsText.setText(helper.priceToString(totalPayments));
        totalCreditsText.setText(helper.priceToString(totalCredits));
        totalExpensesText.setText(helper.priceToString(totalExpenses));
        totalCreditPaymentText.setText(helper.priceToString(totalCreditPayment));
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

            if (showButtonColumn) {
                object = new Object[]{
                    monthly.getMonth(),
                    Month.of(Integer.parseInt(monthly.getMonth())),
                    helper.priceToStringNotAmount(monthly.getTop()),
                    helper.priceToStringNotAmount(monthly.getDown()),
                    helper.priceToStringNotAmount(monthly.getDensity()),
                    helper.priceToStringNotAmount(monthly.getKarat()),
                    helper.priceToStringNotAmount(monthly.getPounds()),
                    helper.priceToStringNotAmount(monthly.getWeight()),
                    helper.priceToString(monthly.getTotalAmount()),
                    TableActions.Daily.toString()
                };
            } else {
                object = new Object[]{
                    monthly.getMonth(),
                    Month.of(Integer.parseInt(monthly.getMonth())),
                    helper.priceToStringNotAmount(monthly.getTop()),
                    helper.priceToStringNotAmount(monthly.getDown()),
                    helper.priceToStringNotAmount(monthly.getDensity()),
                    helper.priceToStringNotAmount(monthly.getKarat()),
                    helper.priceToStringNotAmount(monthly.getPounds()),
                    helper.priceToStringNotAmount(monthly.getWeight()),
                    helper.priceToString(monthly.getTotalAmount())
                };
            }

            defaultTableModel.addRow(object);
        }
    }

    public void populateYearlyTable(JTable table) {
        List<Yearly> yearlys = yearlyReportRepository.yearlyPurchaseReport();

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Yearly yearly : yearlys) {
            object = new Object[]{
                yearly.getYear(),
                helper.priceToStringNotAmount(yearly.getTop()),
                helper.priceToStringNotAmount(yearly.getDown()),
                helper.priceToStringNotAmount(yearly.getDensity()),
                helper.priceToStringNotAmount(yearly.getKarat()),
                helper.priceToStringNotAmount(yearly.getPounds()),
                helper.priceToStringNotAmount(yearly.getWeight()),
                helper.priceToString(yearly.getTotalAmount()),
                TableActions.Monthly.toString()
            };

            defaultTableModel.addRow(object);
        }
    }

    private double getYearlyPayment(String year) {
        double total = yearlyReportRepository.yearlyPaymentTotal(year);
        double totalCreditPayment = yearlyReportRepository.yearlyCreditPaymentTotal(year, 0);
        return total + totalCreditPayment;
    }

    public void populateDailyTable(JTable table, String month, String year) {
        List<Daily> dailys = dailyReportRepository.dailyPurchasesReport(month, year);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Daily daily : dailys) {
            object = new Object[]{
                daily.getDay(),
                helper.priceToStringNotAmount(daily.getTop()),
                helper.priceToStringNotAmount(daily.getDown()),
                helper.priceToStringNotAmount(daily.getDensity()),
                helper.priceToStringNotAmount(daily.getKarat()),
                helper.priceToStringNotAmount(daily.getPounds()),
                helper.priceToStringNotAmount(daily.getWeight()),
                helper.priceToString(daily.getTotalAmount())
            };

            defaultTableModel.addRow(object);
        }
    }

    public void populateDailyPerDayTable(JTable table, String currentDate) {
        List<Daily> dailys = dailyReportRepository.dailyPurchases(currentDate);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Daily daily : dailys) {
            object = new Object[]{
                daily.getDay(),
                helper.priceToStringNotAmount(daily.getTop()),
                helper.priceToStringNotAmount(daily.getDown()),
                helper.priceToStringNotAmount(daily.getDensity()),
                helper.priceToStringNotAmount(daily.getKarat()),
                helper.priceToStringNotAmount(daily.getPounds()),
                helper.priceToStringNotAmount(daily.getWeight()),
                helper.priceToString(daily.getTotalAmount())
            };

            defaultTableModel.addRow(object);
        }
    }

}
