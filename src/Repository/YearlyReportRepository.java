package Repository;

import Helpers.connectDB;
import ModelDTO.BuyGoldDTO;
import ModelDTO.PaymentDTO;
import Models.Yearly;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Repent
 */
public class YearlyReportRepository {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public YearlyReportRepository() {
        conn = connectDB.ConnecrDb();
    }

    public List<Yearly> yearlyPurchaseReport() {
        List<Yearly> yearlyList = new ArrayList<>();
        try {
            String sql = "SELECT id,strftime('%Y'," + BuyGoldDTO.getRAW_DATE() + ") AS year, SUM(" + BuyGoldDTO.getTOTAL_AMOUNT() + ") AS total FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " GROUP BY year ORDER BY year DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Yearly yearly = new Yearly();

                yearly.setYear(rs.getString("year"));
                yearly.setTotal(rs.getDouble("total"));
                yearlyList.add(yearly);
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

        return yearlyList;
    }

    public double yearlyPaymentTotal(String year) {
        double monthTotal = 0;
        try {
            String query = "SELECT id,SUM(" + PaymentDTO.getAMOUNT_PAID() + ") AS total FROM " + PaymentDTO.getPAYMENT_DB() + " "
                    + "WHERE strftime('%Y'," + PaymentDTO.getRAW_DATE() + ")='" + year + "'";
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
