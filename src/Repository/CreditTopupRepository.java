package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.CreditTopUpDTO;
import ModelDTO.UserDTO;
import Models.CreditTopup;
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
public class CreditTopupRepository implements AnonymousInterface<CreditTopup> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public CreditTopupRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<CreditTopup> list(String crediter_id) {
        List<CreditTopup> creditTopUpList = new ArrayList<>();
        try {

            String query = "SELECT credit_topup.*,user.fullname AS user FROM " + CreditTopUpDTO.getCREDIT_TOPUP_DB() + " credit_topup "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON credit_topup.user_id=user.id "
                    + "WHERE " + CreditTopUpDTO.getCREDIT_ID() + "= '" + crediter_id + "' ORDER BY " + CreditTopUpDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                CreditTopup creditTopup = new CreditTopup();

                creditTopup.setId(rs.getInt(CreditTopUpDTO.getID()));
                creditTopup.setCredit_id(rs.getInt(CreditTopUpDTO.getCREDIT_ID()));
                creditTopup.setAmount_paid(rs.getDouble(CreditTopUpDTO.getAMOUNT_PAID()));
                creditTopup.setTotal_amount(rs.getDouble(CreditTopUpDTO.getTOTAL_AMOUNT()));
                creditTopup.setUser_id(rs.getInt(CreditTopUpDTO.getUSER_ID()));
                creditTopup.setCreated_date(rs.getString(CreditTopUpDTO.getCREATED_DATE()));
                creditTopup.setCreated_time(rs.getString(CreditTopUpDTO.getCREATED_TIME()));

                creditTopup.setUser(rs.getString(CreditTopUpDTO.getUSER()));

                creditTopUpList.add(creditTopup);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return creditTopUpList;
    }

    @Override
    public List<CreditTopup> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CreditTopup find(int id) {
        CreditTopup creditTopup = new CreditTopup();
        try {

            String query = "SELECT credit_topup.*,user.fullname AS user FROM " + CreditTopUpDTO.getCREDIT_TOPUP_DB() + " credit_topup "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON credit_topup.user_id=user.id "
                    + "WHERE credit_topup.id= '" + id + "' ORDER BY " + CreditTopUpDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                creditTopup.setId(rs.getInt(CreditTopUpDTO.getID()));
                creditTopup.setCredit_id(rs.getInt(CreditTopUpDTO.getCREDIT_ID()));
                creditTopup.setAmount_paid(rs.getDouble(CreditTopUpDTO.getAMOUNT_PAID()));
                creditTopup.setTotal_amount(rs.getDouble(CreditTopUpDTO.getTOTAL_AMOUNT()));
                creditTopup.setUser_id(rs.getInt(CreditTopUpDTO.getUSER_ID()));
                creditTopup.setCreated_date(rs.getString(CreditTopUpDTO.getCREATED_DATE()));
                creditTopup.setCreated_time(rs.getString(CreditTopUpDTO.getCREATED_TIME()));

                creditTopup.setUser(rs.getString(CreditTopUpDTO.getUSER()));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return creditTopup;
    }

    @Override
    public int save(CreditTopup creditTopup) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + CreditTopUpDTO.getCREDIT_TOPUP_DB() + " ("
                    + CreditTopUpDTO.getCREDIT_ID() + ","
                    + CreditTopUpDTO.getAMOUNT_PAID() + ","
                    + CreditTopUpDTO.getTOTAL_AMOUNT() + ","
                    + CreditTopUpDTO.getUSER_ID() + ","
                    + CreditTopUpDTO.getCREATED_DATE() + ","
                    + CreditTopUpDTO.getCREATED_TIME() + " ) VALUES (?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setInt(1, creditTopup.getCredit_id());
            pst.setDouble(2, creditTopup.getAmount_paid());
            pst.setDouble(3, creditTopup.getTotal_amount());
            pst.setInt(4, creditTopup.getUser_id());
            pst.setString(5, creditTopup.getCreated_date());
            pst.setString(6, creditTopup.getCreated_time());
            pst.executeUpdate();

            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
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

        return last_inserted_id;
    }

    @Override
    public void update(CreditTopup creditTopup, int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
