package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.BudgetDTO;
import ModelDTO.UserDTO;
import Models.Budget;
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
public class BudgetRepository implements AnonymousInterface<Budget> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public BudgetRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<Budget> list(String createdDate) {
        List<Budget> budgetsList = new ArrayList<>();
        try {
            String query = "SELECT bud.*,user.fullname AS user FROM " + BudgetDTO.getBUDGET_DB() + " bud LEFT JOIN " + UserDTO.getUSERS_DB() + " user "
                    + "ON bud.user_id=user.id WHERE bud.created_date = '" + createdDate + "' ORDER BY " + BudgetDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Budget budget = new Budget();

                budget.setId(rs.getInt(BudgetDTO.getID()));
                budget.setName(rs.getString(BudgetDTO.getNAME()));
                budget.setTotal_amount(rs.getDouble(BudgetDTO.getTOTAL_AMOUNT()));
                budget.setSource_of_fund(rs.getString(BudgetDTO.getSOURCE_OF_FUND()));
                budget.setStatus(rs.getBoolean(BudgetDTO.getSTATUS()));
                budget.setCreated_time(rs.getString(BudgetDTO.getCREATED_TIME()));
                budget.setCreated_date(rs.getString(BudgetDTO.getCREATED_DATE()));
                budget.setRaw_date(rs.getString(BudgetDTO.getRAW_DATE()));
                budget.setUser_id(rs.getInt(BudgetDTO.getUSER_ID()));

                budget.setUser(rs.getString(BudgetDTO.getUSER()));

                budgetsList.add(budget);
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
        return budgetsList;
    }

    @Override
    public List<Budget> list() {
        List<Budget> budgetsList = new ArrayList<>();
        try {
            String query = "SELECT *,user.fullname as user FROM " + BudgetDTO.getBUDGET_DB() + " bud LEFT JOIN " + UserDTO.getUSERS_DB() + " user "
                    + "ON bud.user_id=user.id ORDER BY " + BudgetDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Budget budget = new Budget();

                budget.setId(rs.getInt(BudgetDTO.getID()));
                budget.setName(rs.getString(BudgetDTO.getNAME()));
                budget.setTotal_amount(rs.getDouble(BudgetDTO.getTOTAL_AMOUNT()));
                budget.setSource_of_fund(rs.getString(BudgetDTO.getSOURCE_OF_FUND()));
                budget.setStatus(rs.getBoolean(BudgetDTO.getSTATUS()));
                budget.setCreated_time(rs.getString(BudgetDTO.getCREATED_TIME()));
                budget.setCreated_date(rs.getString(BudgetDTO.getCREATED_DATE()));
                budget.setRaw_date(rs.getString(BudgetDTO.getRAW_DATE()));
                budget.setUser_id(rs.getInt(BudgetDTO.getUSER_ID()));

                budget.setUser(rs.getString(BudgetDTO.getUSER()));

                budgetsList.add(budget);
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
        return budgetsList;
    }

    @Override
    public Budget find(int id) {
        Budget budget = new Budget();

        try {
            String query = "SELECT bud.*,user.fullname AS user FROM " + BudgetDTO.getBUDGET_DB() + " bud LEFT JOIN " + UserDTO.getUSERS_DB() + " user "
                    + "ON bud.user_id=user.id WHERE bud.id = " + id + "";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                budget.setId(rs.getInt(BudgetDTO.getID()));
                budget.setName(rs.getString(BudgetDTO.getNAME()));
                budget.setTotal_amount(rs.getDouble(BudgetDTO.getTOTAL_AMOUNT()));
                budget.setSource_of_fund(rs.getString(BudgetDTO.getSOURCE_OF_FUND()));
                budget.setStatus(rs.getBoolean(BudgetDTO.getSTATUS()));
                budget.setCreated_time(rs.getString(BudgetDTO.getCREATED_TIME()));
                budget.setCreated_date(rs.getString(BudgetDTO.getCREATED_DATE()));
                budget.setRaw_date(rs.getString(BudgetDTO.getRAW_DATE()));
                budget.setUser_id(rs.getInt(BudgetDTO.getUSER_ID()));

                budget.setUser(rs.getString(BudgetDTO.getUSER()));
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
        return budget;
    }

    @Override
    public int save(Budget budget) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + BudgetDTO.getBUDGET_DB() + " ("
                    + BudgetDTO.getNAME() + ","
                    + BudgetDTO.getTOTAL_AMOUNT() + ","
                    + BudgetDTO.getSOURCE_OF_FUND() + ","
                    + BudgetDTO.getSTATUS() + ","
                    + BudgetDTO.getUSER_ID() + ","
                    + BudgetDTO.getRAW_DATE() + ","
                    + BudgetDTO.getCREATED_DATE() + ","
                    + BudgetDTO.getCREATED_TIME() + " ) VALUES (?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setString(1, budget.getName());
            pst.setDouble(2, budget.getTotal_amount());
            pst.setString(3, budget.getSource_of_fund());
            pst.setBoolean(4, budget.isStatus());
            pst.setInt(5, budget.getUser_id());
            pst.setString(6, budget.getRaw_date());
            pst.setString(7, budget.getCreated_date());
            pst.setString(8, budget.getCreated_time());

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
    public void update(Budget budget, int id) {
        try {
            String query = "UPDATE " + BudgetDTO.getBUDGET_DB() + " SET "
                    + BudgetDTO.getNAME() + "='" + budget.getName() + "',"
                    + BudgetDTO.getTOTAL_AMOUNT() + "='" + budget.getTotal_amount() + "',"
                    + BudgetDTO.getSOURCE_OF_FUND() + "='" + budget.getSource_of_fund() + "',"
                    + BudgetDTO.getSTATUS() + "='" + budget.isStatus() + "',"
                    + BudgetDTO.getUSER_ID() + "='" + budget.getUser_id() + "',"
                    + BudgetDTO.getRAW_DATE() + "='" + budget.getRaw_date() + "',"
                    + BudgetDTO.getCREATED_DATE() + "='" + budget.getCreated_date() + "',"
                    + BudgetDTO.getCREATED_TIME() + "='" + budget.getCreated_time() + "' WHERE " + BudgetDTO.getID() + "='" + id + "'";

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
            String query = "DELETE FROM " + BudgetDTO.getBUDGET_DB() + " WHERE " + BudgetDTO.getID() + "='" + id + "'";
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
            String query = "DELETE FROM " + BudgetDTO.getBUDGET_DB();
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
            String query = "SELECT COUNT(" + BudgetDTO.getID() + ") AS total FROM " + BudgetDTO.getBUDGET_DB();
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

    public Budget lastBudget() {
        Budget budget = new Budget();

        try {
            String query = "SELECT * FROM " + BudgetDTO.getBUDGET_DB() + " ORDER BY " + BudgetDTO.getID() + " DESC LIMIT 1";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                budget.setId(rs.getInt(BudgetDTO.getID()));
                budget.setName(rs.getString(BudgetDTO.getNAME()));
                budget.setTotal_amount(rs.getDouble(BudgetDTO.getTOTAL_AMOUNT()));
                budget.setSource_of_fund(rs.getString(BudgetDTO.getSOURCE_OF_FUND()));
                budget.setStatus(rs.getBoolean(BudgetDTO.getSTATUS()));
                budget.setCreated_time(rs.getString(BudgetDTO.getCREATED_TIME()));
                budget.setCreated_date(rs.getString(BudgetDTO.getCREATED_DATE()));
                budget.setRaw_date(rs.getString(BudgetDTO.getRAW_DATE()));
                budget.setUser_id(rs.getInt(BudgetDTO.getUSER_ID()));
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

        return budget;
    }

    public void updateDailyBudget(int budget_id, double amount) {
        try {
            String query = "UPDATE " + BudgetDTO.getBUDGET_DB() + " SET " + BudgetDTO.getTOTAL_AMOUNT() + "= total_amount + '" + amount + "' WHERE " + BudgetDTO.getID() + "='" + budget_id + "'";
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

    public Budget todayBudget(String createdDate) {
        Budget budget = new Budget();

        try {
            String query = "SELECT * FROM " + BudgetDTO.getBUDGET_DB() + " WHERE " + BudgetDTO.getCREATED_DATE() + "='" + createdDate + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                budget.setId(rs.getInt(BudgetDTO.getID()));
                budget.setName(rs.getString(BudgetDTO.getNAME()));
                budget.setTotal_amount(rs.getDouble(BudgetDTO.getTOTAL_AMOUNT()));
                budget.setSource_of_fund(rs.getString(BudgetDTO.getSOURCE_OF_FUND()));
                budget.setStatus(rs.getBoolean(BudgetDTO.getSTATUS()));
                budget.setCreated_time(rs.getString(BudgetDTO.getCREATED_TIME()));
                budget.setCreated_date(rs.getString(BudgetDTO.getCREATED_DATE()));
                budget.setRaw_date(rs.getString(BudgetDTO.getRAW_DATE()));
                budget.setUser_id(rs.getInt(BudgetDTO.getUSER_ID()));
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

        return budget;
    }

    public void updateStatus(int id, int status) {
        try {
            String query = "UPDATE " + BudgetDTO.getBUDGET_DB() + " SET " + BudgetDTO.getSTATUS() + "='" + status + "' WHERE " + BudgetDTO.getID() + "='" + id + "'";
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

    public List<Budget> findBudgetBetweenDates(String startDate, String endDate) {
        List<Budget> budgetsList = new ArrayList<>();
        try {
            String query = "SELECT bud.*,user.fullname AS user FROM " + BudgetDTO.getBUDGET_DB() + " bud LEFT JOIN " + UserDTO.getUSERS_DB() + " user "
                    + "ON bud.user_id=user.id WHERE bud.created_date >= '" + startDate + "' AND bud.created_date <= '" + endDate + "' ORDER BY bud.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Budget budget = new Budget();

                budget.setId(rs.getInt(BudgetDTO.getID()));
                budget.setName(rs.getString(BudgetDTO.getNAME()));
                budget.setTotal_amount(rs.getDouble(BudgetDTO.getTOTAL_AMOUNT()));
                budget.setSource_of_fund(rs.getString(BudgetDTO.getSOURCE_OF_FUND()));
                budget.setStatus(rs.getBoolean(BudgetDTO.getSTATUS()));
                budget.setCreated_time(rs.getString(BudgetDTO.getCREATED_TIME()));
                budget.setCreated_date(rs.getString(BudgetDTO.getCREATED_DATE()));
                budget.setRaw_date(rs.getString(BudgetDTO.getRAW_DATE()));
                budget.setUser_id(rs.getInt(BudgetDTO.getUSER_ID()));

                budget.setUser(rs.getString(BudgetDTO.getUSER()));

                budgetsList.add(budget);
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

        return budgetsList;
    }

    public double budgetSummation(String createdDate) {
        double totalBudget = 0;
        try {
            String query = "SELECT SUM(" + BudgetDTO.getTOTAL_AMOUNT() + ") AS total FROM " + BudgetDTO.getBUDGET_DB() + " WHERE " + BudgetDTO.getRAW_DATE() + " = '" + createdDate + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                totalBudget = rs.getDouble("total");
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

        return totalBudget;
    }

    public double budgetSummationBetweenDates(String startDate, String endDate) {
        double totalBudget = 0;
        try {
            String query = "SELECT SUM(" + BudgetDTO.getTOTAL_AMOUNT() + ") AS total FROM " + BudgetDTO.getBUDGET_DB() + " "
                    + "WHERE " + BudgetDTO.getRAW_DATE() + " >= '" + startDate + "' AND " + BudgetDTO.getRAW_DATE() + " <= '" + endDate + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                totalBudget = rs.getDouble("total");
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

        return totalBudget;
    }

}
