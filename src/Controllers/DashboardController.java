package Controllers;

import Helpers.HelperFunctions;
import Helpers.PricingData;
import Helpers.TableActions;
import Models.Daily;
import Models.Monthly;
import Models.Yearly;
import Repository.CustomerRepository;
import Repository.DailyReportRepository;
import Repository.MonthlyReportRepository;
import Repository.ReportRespository;
import Repository.UserRepository;
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

    CustomerRepository customerRepository = new CustomerRepository();
    UserRepository userRepository = new UserRepository();
    MonthlyReportRepository monthlyReportRepository = new MonthlyReportRepository();
    YearlyReportRepository yearlyReportRepository = new YearlyReportRepository();
    ReportRespository reportRespository = new ReportRespository();
    DailyReportRepository dailyReportRepository = new DailyReportRepository();

    HelperFunctions helper = new HelperFunctions();

    public void dashboardCount(
            JTextField customers,
            JTextField users,
            JTextField basePrice,
            JTextField totalGoldBought,
            JTextField toalPayments,
            JTextField totalBalance
    ) {
        customers.setText(String.valueOf(customerRepository.count()));
        users.setText(String.valueOf(userRepository.count()));
        basePrice.setText(helper.priceToString(PricingData.getCurrent_price()));
        totalGoldBought.setText(helper.priceToString(reportRespository.totalPurchaseSummation()));
        toalPayments.setText(helper.priceToString(reportRespository.totalPaymentSummation()));
        totalBalance.setText(helper.priceToString(reportRespository.totalPurchaseSummation() - reportRespository.totalPaymentSummation()));
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
