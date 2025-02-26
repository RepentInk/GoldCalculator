package Repository;

import Helpers.connectDB;
import ModelDTO.BuyGoldDTO;
import ModelDTO.CREDITPAYMENTDTO;
import ModelDTO.PaymentDTO;
import Models.Monthly;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nyark
 */
public class MonthlyReportRepository {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public MonthlyReportRepository() {
        conn = connectDB.ConnecrDb();
    }

    public List<Monthly> monthlyPurchasesReport(String year) {
        List<Monthly> monthlyList = new ArrayList<>();
        try {
            String query = "SELECT id,strftime('%m', " + BuyGoldDTO.getRAW_DATE() + ") AS month, SUM(" + BuyGoldDTO.getTOTAL_AMOUNT() + ") AS total FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " "
                    + "WHERE strftime('%Y'," + BuyGoldDTO.getRAW_DATE() + ")='" + year + "' GROUP BY month ORDER BY month DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Monthly month = new Monthly();

                month.setMonth(rs.getString("month"));
                month.setTotal(rs.getDouble("total"));
                monthlyList.add(month);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return monthlyList;
    }

    public double monthlyPaymentTotal(String year, String month) {
        double monthTotal = 0;
        try {
            String query = "SELECT id,SUM(" + PaymentDTO.getAMOUNT_PAID() + ") AS total FROM " + PaymentDTO.getPAYMENT_DB() + " "
                    + "WHERE strftime('%Y'," + PaymentDTO.getRAW_DATE() + ")='" + year + "' AND strftime('%m'," + PaymentDTO.getRAW_DATE() + ")='" + month + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                monthTotal = rs.getDouble("total");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return monthTotal;
    }

    public double monthlyCreditPaymentTotal(String year, String month, int paidFrom) {
        double monthTotal = 0;
        try {
            String query = "SELECT id,SUM(" + CREDITPAYMENTDTO.getPAID() + ") AS total FROM " + CREDITPAYMENTDTO.getCREDIT_PAYMENTS_DB() + " "
                    + "WHERE strftime('%Y'," + CREDITPAYMENTDTO.getRAW_DATE() + ")='" + year + "' AND strftime('%m'," + CREDITPAYMENTDTO.getRAW_DATE() + ")='" + month + "' "
                    + "AND " + CREDITPAYMENTDTO.getPAID_FROM() + "='" + paidFrom + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                monthTotal = rs.getDouble("total");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return monthTotal;
    }
}
