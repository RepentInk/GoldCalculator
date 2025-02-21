package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.BudgetDTO;
import ModelDTO.CreditDTO;
import ModelDTO.CustomerDTO;
import ModelDTO.ShopDTO;
import ModelDTO.UserDTO;
import Models.Credit;
import Models.Shop;
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
            String query = "SELECT credit.*,user.fullname AS user,customer.fullname AS customer,budget.name AS budget FROM " + CreditDTO.getCREDIT_DB() + " credit "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON credit.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON credit.customer_id=customer.id "
                    + "LEFT JOIN " + BudgetDTO.getBUDGET_DB() + " budget ON credit.budget_id=budget.id "
                    + "WHERE credit.created_date= '" + createdDate + "' ORDER BY credit.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Credit credit = new Credit();

                credit.setId(rs.getInt(CreditDTO.getID()));
                credit.setCode(rs.getString(CreditDTO.getCODE()));
                credit.setAmount(rs.getDouble(CreditDTO.getAMOUNT()));
                credit.setPrevious_balance(rs.getDouble(CreditDTO.getPREVIOUS_AMOUNT()));
                credit.setBudget_before(rs.getDouble(CreditDTO.getBUDGET_BEFORE()));
                credit.setBudget_after(rs.getDouble(CreditDTO.getBUDGET_AFTER()));
                credit.setBudget_id(rs.getInt(CreditDTO.getBUDGET_ID()));
                credit.setCustomer_id(rs.getInt(CreditDTO.getCUSTOMER_ID()));
                credit.setUser_id(rs.getInt(CreditDTO.getUSER_ID()));
                credit.setCreated_date(rs.getString(CreditDTO.getCREATED_DATE()));
                credit.setCreated_time(rs.getString(CreditDTO.getCREATED_TIME()));
                credit.setRaw_date(rs.getString(CreditDTO.getRAW_DATE()));

                credit.setUser(rs.getString(CreditDTO.getUSER()));
                credit.setCustomer(rs.getString(CreditDTO.getCUSTOMER()));
                credit.setBudget(rs.getString(CreditDTO.getBUDGET()));

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
            String query = "SELECT credit.*,user.fullname AS user,customer.fullname AS customer,budget.name AS budget FROM " + CreditDTO.getCREDIT_DB() + " credit "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON credit.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON credit.customer_id=customer.id "
                    + "LEFT JOIN " + BudgetDTO.getBUDGET_DB() + " budget ON credit.budget_id=budget.id "
                    + "ORDER BY credit.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Credit credit = new Credit();

                credit.setId(rs.getInt(CreditDTO.getID()));
                credit.setCode(rs.getString(CreditDTO.getCODE()));
                credit.setAmount(rs.getDouble(CreditDTO.getAMOUNT()));
                credit.setPrevious_balance(rs.getDouble(CreditDTO.getPREVIOUS_AMOUNT()));
                credit.setBudget_before(rs.getDouble(CreditDTO.getBUDGET_BEFORE()));
                credit.setBudget_after(rs.getDouble(CreditDTO.getBUDGET_AFTER()));
                credit.setBudget_id(rs.getInt(CreditDTO.getBUDGET_ID()));
                credit.setCustomer_id(rs.getInt(CreditDTO.getCUSTOMER_ID()));
                credit.setUser_id(rs.getInt(CreditDTO.getUSER_ID()));
                credit.setCreated_date(rs.getString(CreditDTO.getCREATED_DATE()));
                credit.setCreated_time(rs.getString(CreditDTO.getCREATED_TIME()));
                credit.setRaw_date(rs.getString(CreditDTO.getRAW_DATE()));

                credit.setUser(rs.getString(CreditDTO.getUSER()));
                credit.setCustomer(rs.getString(CreditDTO.getCUSTOMER()));
                credit.setBudget(rs.getString(CreditDTO.getBUDGET()));

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
            String query = "SELECT credit.*,user.fullname AS user,customer.fullname AS customer,budget.name AS budget FROM " + CreditDTO.getCREDIT_DB() + " credit "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON credit.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON credit.customer_id=customer.id "
                    + "LEFT JOIN " + BudgetDTO.getBUDGET_DB() + " budget ON credit.budget_id=budget.id "
                    + "WHERE credit.id ='" + id + "' ORDER BY credit.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                credit.setId(rs.getInt(CreditDTO.getID()));
                credit.setCode(rs.getString(CreditDTO.getCODE()));
                credit.setAmount(rs.getDouble(CreditDTO.getAMOUNT()));
                credit.setPrevious_balance(rs.getDouble(CreditDTO.getPREVIOUS_AMOUNT()));
                credit.setBudget_before(rs.getDouble(CreditDTO.getBUDGET_BEFORE()));
                credit.setBudget_after(rs.getDouble(CreditDTO.getBUDGET_AFTER()));
                credit.setBudget_id(rs.getInt(CreditDTO.getBUDGET_ID()));
                credit.setCustomer_id(rs.getInt(CreditDTO.getCUSTOMER_ID()));
                credit.setUser_id(rs.getInt(CreditDTO.getUSER_ID()));
                credit.setCreated_date(rs.getString(CreditDTO.getCREATED_DATE()));
                credit.setCreated_time(rs.getString(CreditDTO.getCREATED_TIME()));
                credit.setRaw_date(rs.getString(CreditDTO.getRAW_DATE()));

                credit.setUser(rs.getString(CreditDTO.getUSER()));
                credit.setCustomer(rs.getString(CreditDTO.getCUSTOMER()));
                credit.setBudget(rs.getString(CreditDTO.getBUDGET()));
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
                    + CreditDTO.getBUDGET_ID() + ","
                    + CreditDTO.getAMOUNT() + ","
                    + CreditDTO.getPREVIOUS_AMOUNT() + ","
                    + CreditDTO.getBUDGET_BEFORE() + ","
                    + CreditDTO.getBUDGET_AFTER() + ","
                    + CreditDTO.getUSER_ID() + ","
                    + CreditDTO.getCREATED_DATE() + ","
                    + CreditDTO.getCREATED_TIME() + ","
                    + CreditDTO.getRAW_DATE() + " ) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setString(1, credit.getCode());
            pst.setInt(2, credit.getCustomer_id());
            pst.setInt(3, credit.getBudget_id());
            pst.setDouble(4, credit.getAmount());
            pst.setDouble(5, credit.getPrevious_balance());
            pst.setDouble(6, credit.getBudget_before());
            pst.setDouble(7, credit.getBudget_after());
            pst.setInt(8, credit.getUser_id());
            pst.setString(9, credit.getCreated_date());
            pst.setString(10, credit.getCreated_time());
            pst.setString(11, credit.getRaw_date());
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
                    + CreditDTO.getBUDGET_ID() + "='" + credit.getBudget_id() + "',"
                    + CreditDTO.getAMOUNT() + "='" + credit.getAmount() + "',"
                    + CreditDTO.getPREVIOUS_AMOUNT() + "='" + credit.getPrevious_balance() + "',"
                    + CreditDTO.getBUDGET_BEFORE() + "='" + credit.getBudget_before() + "',"
                    + CreditDTO.getBUDGET_AFTER() + "='" + credit.getBudget_after() + "',"
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
            String query = "SELECT SUM(" + CreditDTO.getAMOUNT() + ") AS total FROM " + CreditDTO.getCREDIT_DB() + " WHERE " + CreditDTO.getCUSTOMER_ID() + "='" + customer_id + "'";
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

    public double summationOfBudget(int budget_id) {
        double total = 0;
        try {
            String query = "SELECT SUM(" + CreditDTO.getAMOUNT() + ") AS total FROM " + CreditDTO.getCREDIT_DB() + " WHERE " + CreditDTO.getBUDGET_ID() + "='" + budget_id + "'";
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
                credit.setAmount(rs.getDouble(CreditDTO.getAMOUNT()));
                credit.setPrevious_balance(rs.getDouble(CreditDTO.getPREVIOUS_AMOUNT()));
                credit.setBudget_before(rs.getDouble(CreditDTO.getBUDGET_BEFORE()));
                credit.setBudget_after(rs.getDouble(CreditDTO.getBUDGET_AFTER()));
                credit.setBudget_id(rs.getInt(CreditDTO.getBUDGET_ID()));
                credit.setCustomer_id(rs.getInt(CreditDTO.getCUSTOMER_ID()));
                credit.setUser_id(rs.getInt(CreditDTO.getUSER_ID()));
                credit.setCreated_date(rs.getString(CreditDTO.getCREATED_DATE()));
                credit.setCreated_time(rs.getString(CreditDTO.getCREATED_TIME()));
                credit.setRaw_date(rs.getString(CreditDTO.getRAW_DATE()));
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

}
