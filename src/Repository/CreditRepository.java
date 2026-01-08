package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.CreditDTO;
import ModelDTO.CustomerDTO;
import ModelDTO.UserDTO;
import Models.Credit;
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
public class CreditRepository implements AnonymousInterface<Credit> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public CreditRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<Credit> list(String createdDate) {
        List<Credit> creditsList = new ArrayList<>();
        try {
            String query = "SELECT credit.*,user.fullname AS user,customer.fullname AS customer FROM " + CreditDTO.getCREDIT_DB() + " credit "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON credit.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON credit.customer_id=customer.id "
                    + "WHERE credit.created_date= '" + createdDate + "' ORDER BY credit.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Credit credit = new Credit();

                credit.setId(rs.getInt(CreditDTO.getID()));
                credit.setCode(rs.getString(CreditDTO.getCODE()));
                credit.setTotal_amount(rs.getDouble(CreditDTO.getTOTAL_AMOUNT()));
                credit.setCustomer_id(rs.getInt(CreditDTO.getCUSTOMER_ID()));
                credit.setUser_id(rs.getInt(CreditDTO.getUSER_ID()));
                credit.setCreated_date(rs.getString(CreditDTO.getCREATED_DATE()));
                credit.setCreated_time(rs.getString(CreditDTO.getCREATED_TIME()));
                credit.setRaw_date(rs.getString(CreditDTO.getRAW_DATE()));
                credit.setStatus(rs.getBoolean(CreditDTO.getSTATUS()));

                credit.setUser(rs.getString(CreditDTO.getUSER()));
                credit.setCustomer(rs.getString(CreditDTO.getCUSTOMER()));

                creditsList.add(credit);
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
        return creditsList;
    }

    @Override
    public List<Credit> list() {
        List<Credit> creditsList = new ArrayList<>();
        try {
            String query = "SELECT credit.*,user.fullname AS user,customer.fullname AS customer FROM " + CreditDTO.getCREDIT_DB() + " credit "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON credit.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON credit.customer_id=customer.id "
                    + "ORDER BY credit.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Credit credit = new Credit();

                credit.setId(rs.getInt(CreditDTO.getID()));
                credit.setCode(rs.getString(CreditDTO.getCODE()));
                credit.setTotal_amount(rs.getDouble(CreditDTO.getTOTAL_AMOUNT()));
                credit.setCustomer_id(rs.getInt(CreditDTO.getCUSTOMER_ID()));
                credit.setUser_id(rs.getInt(CreditDTO.getUSER_ID()));
                credit.setCreated_date(rs.getString(CreditDTO.getCREATED_DATE()));
                credit.setCreated_time(rs.getString(CreditDTO.getCREATED_TIME()));
                credit.setRaw_date(rs.getString(CreditDTO.getRAW_DATE()));
                credit.setStatus(rs.getBoolean(CreditDTO.getSTATUS()));

                credit.setUser(rs.getString(CreditDTO.getUSER()));
                credit.setCustomer(rs.getString(CreditDTO.getCUSTOMER()));

                creditsList.add(credit);
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
        return creditsList;
    }

    @Override
    public Credit find(int id) {
        Credit credit = new Credit();

        try {
            String query = "SELECT credit.*,user.fullname AS user,customer.fullname AS customer FROM " + CreditDTO.getCREDIT_DB() + " credit "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON credit.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON credit.customer_id=customer.id "
                    + "WHERE credit.id ='" + id + "' ORDER BY credit.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                credit.setId(rs.getInt(CreditDTO.getID()));
                credit.setCode(rs.getString(CreditDTO.getCODE()));
                credit.setTotal_amount(rs.getDouble(CreditDTO.getTOTAL_AMOUNT()));
                credit.setCustomer_id(rs.getInt(CreditDTO.getCUSTOMER_ID()));
                credit.setUser_id(rs.getInt(CreditDTO.getUSER_ID()));
                credit.setCreated_date(rs.getString(CreditDTO.getCREATED_DATE()));
                credit.setCreated_time(rs.getString(CreditDTO.getCREATED_TIME()));
                credit.setRaw_date(rs.getString(CreditDTO.getRAW_DATE()));
                credit.setStatus(rs.getBoolean(CreditDTO.getSTATUS()));

                credit.setUser(rs.getString(CreditDTO.getUSER()));
                credit.setCustomer(rs.getString(CreditDTO.getCUSTOMER()));
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
        return credit;
    }

    @Override
    public int save(Credit credit) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + CreditDTO.getCREDIT_DB() + " ("
                    + CreditDTO.getCODE() + ","
                    + CreditDTO.getCUSTOMER_ID() + ","
                    + CreditDTO.getTOTAL_AMOUNT() + ","
                    + CreditDTO.getUSER_ID() + ","
                    + CreditDTO.getCREATED_DATE() + ","
                    + CreditDTO.getCREATED_TIME() + ","
                    + CreditDTO.getRAW_DATE() + " ) VALUES (?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setString(1, credit.getCode());
            pst.setInt(2, credit.getCustomer_id());
            pst.setDouble(3, credit.getTotal_amount());
            pst.setInt(4, credit.getUser_id());
            pst.setString(5, credit.getCreated_date());
            pst.setString(6, credit.getCreated_time());
            pst.setString(7, credit.getRaw_date());
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
    public void update(Credit credit, int id) {
        try {
            String query = "UPDATE " + CreditDTO.getCREDIT_DB() + " SET "
                    + CreditDTO.getCUSTOMER_ID() + "='" + credit.getCustomer_id() + "',"
                    + CreditDTO.getTOTAL_AMOUNT() + "='" + credit.getTotal_amount() + "',"
                    + CreditDTO.getUSER_ID() + "='" + credit.getUser_id() + "',"
                    + CreditDTO.getCREATED_DATE() + "='" + credit.getCreated_date() + "',"
                    + CreditDTO.getCREATED_TIME() + "='" + credit.getCreated_time() + "',"
                    + CreditDTO.getRAW_DATE() + "='" + credit.getRaw_date() + "' WHERE " + CreditDTO.getID() + "='" + id + "'";

            pst = conn.prepareStatement(query);
            pst.executeUpdate();

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
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM " + CreditDTO.getCREDIT_DB() + " WHERE " + CreditDTO.getID() + "='" + id + "'";
            pst = conn.prepareStatement(query);
            pst.executeUpdate();
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
    }

    @Override
    public void clear() {
        try {
            String query = "DELETE FROM " + CreditDTO.getCREDIT_DB();
            pst = conn.prepareStatement(query);
            pst.executeUpdate();

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
    }

    @Override
    public int count() {
        int total = 0;
        try {
            String query = "SELECT COUNT(" + CreditDTO.getID() + ") AS total FROM " + CreditDTO.getCREDIT_DB();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
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

    public double summationOfCredit(int customer_id) {
        double total = 0;
        try {
            String query = "SELECT SUM(" + CreditDTO.getTOTAL_AMOUNT() + ") AS total FROM " + CreditDTO.getCREDIT_DB() + " WHERE " + CreditDTO.getCUSTOMER_ID() + "='" + customer_id + "'";
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

    public Credit findCustomerLastCredit(int customer_id) {
        Credit credit = new Credit();

        try {
            String query = "SELECT * FROM " + CreditDTO.getCREDIT_DB() + " WHERE " + CreditDTO.getCUSTOMER_ID() + "='" + customer_id + "' ORDER BY " + CreditDTO.getID() + " DESC LIMIT 1";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                credit.setId(rs.getInt(CreditDTO.getID()));
                credit.setCode(rs.getString(CreditDTO.getCODE()));
                credit.setTotal_amount(rs.getDouble(CreditDTO.getTOTAL_AMOUNT()));
                credit.setCustomer_id(rs.getInt(CreditDTO.getCUSTOMER_ID()));
                credit.setUser_id(rs.getInt(CreditDTO.getUSER_ID()));
                credit.setCreated_date(rs.getString(CreditDTO.getCREATED_DATE()));
                credit.setCreated_time(rs.getString(CreditDTO.getCREATED_TIME()));
                credit.setRaw_date(rs.getString(CreditDTO.getRAW_DATE()));
                credit.setStatus(rs.getBoolean(CreditDTO.getSTATUS()));
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

        return credit;
    }

    public void updateCreditAmount(int credit_id, double amount) {
        try {
            String query = "UPDATE " + CreditDTO.getCREDIT_DB() + " SET " + CreditDTO.getTOTAL_AMOUNT() + "= amount + '" + amount + "' WHERE " + CreditDTO.getID() + "='" + credit_id + "'";
            pst = conn.prepareStatement(query);
            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public void updateStatus(int id, int status) {
        try {
            String query = "UPDATE " + CreditDTO.getCREDIT_DB() + " SET " + CreditDTO.getSTATUS() + "='" + status + "' WHERE " + CreditDTO.getID() + "='" + id + "'";
            pst = conn.prepareStatement(query);
            pst.executeUpdate();

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
    }

    public List<Credit> findCreditBetweenDates(String startDate, String endDate) {
        List<Credit> creditsList = new ArrayList<>();
        try {
            String query = "SELECT credit.*,user.fullname AS user,customer.fullname AS customer FROM " + CreditDTO.getCREDIT_DB() + " credit "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON credit.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON credit.customer_id=customer.id "
                    + "WHERE credit.created_date >= '" + startDate + "' AND credit.created_date <= '" + endDate + "' ORDER BY credit.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Credit credit = new Credit();

                credit.setId(rs.getInt(CreditDTO.getID()));
                credit.setCode(rs.getString(CreditDTO.getCODE()));
                credit.setTotal_amount(rs.getDouble(CreditDTO.getTOTAL_AMOUNT()));
                credit.setCustomer_id(rs.getInt(CreditDTO.getCUSTOMER_ID()));
                credit.setUser_id(rs.getInt(CreditDTO.getUSER_ID()));
                credit.setCreated_date(rs.getString(CreditDTO.getCREATED_DATE()));
                credit.setCreated_time(rs.getString(CreditDTO.getCREATED_TIME()));
                credit.setRaw_date(rs.getString(CreditDTO.getRAW_DATE()));
                credit.setStatus(rs.getBoolean(CreditDTO.getSTATUS()));

                credit.setUser(rs.getString(CreditDTO.getUSER()));
                credit.setCustomer(rs.getString(CreditDTO.getCUSTOMER()));

                creditsList.add(credit);
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

        return creditsList;
    }

    public double creditSummation(String createdDate) {
        double totalCredit = 0;
        try {
            String query = "SELECT SUM(" + CreditDTO.getTOTAL_AMOUNT() + ") AS total FROM " + CreditDTO.getCREDIT_DB() + " WHERE " + CreditDTO.getRAW_DATE() + " = '" + createdDate + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                totalCredit = rs.getDouble("total");
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

        return totalCredit;
    }

    public double paymentSummationBetweenDates(String startDate, String endDate) {
        double totalCredit = 0;
        try {
            String query = "SELECT SUM(" + CreditDTO.getTOTAL_AMOUNT() + ") AS total FROM " + CreditDTO.getCREDIT_DB() + " "
                    + "WHERE " + CreditDTO.getRAW_DATE() + " >= '" + startDate + "' AND " + CreditDTO.getRAW_DATE() + " <= '" + endDate + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                totalCredit = rs.getDouble("total");
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

        return totalCredit;
    }
}
