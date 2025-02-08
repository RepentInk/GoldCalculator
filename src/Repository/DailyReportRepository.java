package Repository;

import Helpers.connectDB;
import ModelDTO.BuyGoldDTO;
import ModelDTO.PaymentDTO;
import Models.Daily;
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
public class DailyReportRepository {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public DailyReportRepository() {
        conn = connectDB.ConnecrDb();
    }

    public List<Daily> dailyPurchasesReport(String month, String year) {
        List<Daily> dailyList = new ArrayList<>();
        try {
            String query = "SELECT id," + BuyGoldDTO.getRAW_DATE() + " AS day, SUM(" + BuyGoldDTO.getTOTAL_AMOUNT() + ") AS total FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " WHERE "
                    + "strftime('%m'," + BuyGoldDTO.getRAW_DATE() + ")='" + month + "' AND strftime('%Y'," + BuyGoldDTO.getRAW_DATE() + ")='" + year + "' GROUP BY day ORDER BY day DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Daily daily = new Daily();
                
                daily.setDay(rs.getString("day"));
                daily.setTotal(rs.getDouble("total"));
                dailyList.add(daily);
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

        return dailyList;
    }

    public double dailyPaymentTotal(String year, String month, String day) {
        double dailyTotal = 0;
        try {
            String query = "SELECT id,SUM(" + PaymentDTO.getAMOUNT_PAID() + ") AS total FROM " + PaymentDTO.getPAYMENT_DB() + " WHERE "
                    + "strftime('%Y'," + PaymentDTO.getRAW_DATE() + ")='" + year + "' AND "
                    + "strftime('%m'," + PaymentDTO.getRAW_DATE() + ")='" + month + "' AND " + PaymentDTO.getRAW_DATE() + "='" + day + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                dailyTotal = rs.getDouble("total");
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

        return dailyTotal;
    }

}
