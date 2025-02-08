package Repository;

import Helpers.connectDB;
import ModelDTO.BuyGoldDTO;
import ModelDTO.PaymentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author nyark
 */
public class ReportRespository {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public ReportRespository() {
        conn = connectDB.ConnecrDb();
    }

    public double totalPurchaseSummation() {
        double totalPurchaseSummation = 0;
        try {
            String sql = "SELECT SUM(" + BuyGoldDTO.getTOTAL_AMOUNT() + ") AS total FROM " + BuyGoldDTO.getBUY_GOLD_DB();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                totalPurchaseSummation = rs.getDouble("total");
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

        return totalPurchaseSummation;
    }

    public double totalPaymentSummation() {
        double totalPaymentSummation = 0;
        try {
            String query = "SELECT SUM(" + PaymentDTO.getAMOUNT_PAID() + ") AS total FROM " + PaymentDTO.getPAYMENT_DB();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                totalPaymentSummation = rs.getDouble("total");
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

        return totalPaymentSummation;
    }
}
