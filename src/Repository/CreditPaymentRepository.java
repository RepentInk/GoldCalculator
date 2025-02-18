package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.CREDITPAYMENTDTO;
import ModelDTO.UserDTO;
import Models.CreditPayment;
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
public class CreditPaymentRepository implements AnonymousInterface<CreditPayment> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public CreditPaymentRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<CreditPayment> list(String crediter_id) {
        List<CreditPayment> creditPaymentsList = new ArrayList<>();
        try {

            String query = "SELECT creditPayment.*,user.fullname AS user FROM " + CREDITPAYMENTDTO.getCREDIT_PAYMENTS_DB() + " creditPayment "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON creditPayment.user_id=user.id "
                    + "WHERE " + CREDITPAYMENTDTO.getCREDIT_ID() + "= '" + crediter_id + "' ORDER BY " + CREDITPAYMENTDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                CreditPayment creditPayment = new CreditPayment();

                creditPayment.setId(rs.getInt(CREDITPAYMENTDTO.getID()));
                creditPayment.setPaid(rs.getDouble(CREDITPAYMENTDTO.getPAID()));
                creditPayment.setBalance(rs.getDouble(CREDITPAYMENTDTO.getBALANCE()));
                creditPayment.setCreated_date(rs.getString(CREDITPAYMENTDTO.getCREATED_DATE()));
                creditPayment.setCreated_time(rs.getString(CREDITPAYMENTDTO.getCREATED_TIME()));
                creditPayment.setRaw_date(rs.getString(CREDITPAYMENTDTO.getRAW_DATE()));
                creditPayment.setUser(rs.getString(CREDITPAYMENTDTO.getUSER()));
                creditPaymentsList.add(creditPayment);
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

        return creditPaymentsList;
    }

    @Override
    public List<CreditPayment> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CreditPayment find(int id) {
        CreditPayment creditPayment = new CreditPayment();

        try {
            String query = "SELECT creditPayment.*,user.fullname AS user FROM " + CREDITPAYMENTDTO.getCREDIT_PAYMENTS_DB() + " creditPayment "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON creditPayment.user_id=user.id "
                    + "WHERE creditPayment.id = '" + id + "'";

            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                creditPayment.setId(rs.getInt(CREDITPAYMENTDTO.getID()));
                creditPayment.setPaid(rs.getDouble(CREDITPAYMENTDTO.getPAID()));
                creditPayment.setBalance(rs.getDouble(CREDITPAYMENTDTO.getBALANCE()));
                creditPayment.setCreated_date(rs.getString(CREDITPAYMENTDTO.getCREATED_DATE()));
                creditPayment.setCreated_time(rs.getString(CREDITPAYMENTDTO.getCREATED_TIME()));
                creditPayment.setRaw_date(rs.getString(CREDITPAYMENTDTO.getRAW_DATE()));
                creditPayment.setUser(rs.getString(CREDITPAYMENTDTO.getUSER()));
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

        return creditPayment;
    }

    @Override
    public int save(CreditPayment creditPayment) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + CREDITPAYMENTDTO.getCREDIT_PAYMENTS_DB() + " ("
                    + CREDITPAYMENTDTO.getCREDIT_ID() + ","
                    + CREDITPAYMENTDTO.getCUSTOMER_ID() + ","
                    + CREDITPAYMENTDTO.getPAID() + ","
                    + CREDITPAYMENTDTO.getBALANCE() + ","
                    + CREDITPAYMENTDTO.getUSER_ID() + ","
                    + CREDITPAYMENTDTO.getCREATED_DATE() + ","
                    + CREDITPAYMENTDTO.getCREATED_TIME() + ","
                    + CREDITPAYMENTDTO.getRAW_DATE() + " ) VALUES (?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setInt(1, creditPayment.getCredit_id());
            pst.setInt(2, creditPayment.getCustomer_id());
            pst.setDouble(3, creditPayment.getPaid());
            pst.setDouble(4, creditPayment.getBalance());
            pst.setInt(5, creditPayment.getUser_id());
            pst.setString(6, creditPayment.getCreated_date());
            pst.setString(7, creditPayment.getCreated_time());
            pst.setString(8, creditPayment.getRaw_date());
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
    public void update(CreditPayment t, int id) {
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

    public double summationOfAmountPaid(int customer_id) {
        double total = 0;
        try {
            String query = "SELECT SUM(" + CREDITPAYMENTDTO.getPAID() + ") AS total FROM " + CREDITPAYMENTDTO.getCREDIT_PAYMENTS_DB() + " WHERE " + CREDITPAYMENTDTO.getCUSTOMER_ID() + "='" + customer_id + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
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

        return total;
    }

    public double summationAmountPaid(int credit_id) {
        double total = 0;
        try {
            String query = "SELECT SUM(" + CREDITPAYMENTDTO.getPAID() + ") AS total FROM " + CREDITPAYMENTDTO.getCREDIT_PAYMENTS_DB() + " WHERE " + CREDITPAYMENTDTO.getCREDIT_ID() + "='" + credit_id + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
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

        return total;
    }

}
