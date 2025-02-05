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
    public List<Budget> list(int year) {
        List<Budget> budgetsList = new ArrayList<>();
        try {
            String query = "SELECT *,user.fullname AS user FROM " + BudgetDTO.getBUDGET_DB() + " bud LEFT JOIN " + UserDTO.getUSERS_DB() + " user "
                    + "ON bud.user_id=user.id WHERE strftime('%Y',bud.raw_date) = '" + year + "' ORDER BY " + BudgetDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Budget budget = new Budget();

                budget.setId(rs.getInt(BudgetDTO.getID()));
                budget.setName(rs.getString(BudgetDTO.getNAME()));
                budget.setTotal_amount(rs.getDouble(BudgetDTO.getTOTAL_AMOUNT()));
                budget.setAmount_used(rs.getDouble(BudgetDTO.getAMOUNT_USED()));
                budget.setAmount_left(rs.getDouble(BudgetDTO.getAMOUNT_LEFT()));
                budget.setStatus(rs.getBoolean(BudgetDTO.getSTATUS()));
                budget.setStart_date(rs.getString(BudgetDTO.getSTART_DATE()));
                budget.setEnd_date(rs.getString(BudgetDTO.getEND_DATE()));
                budget.setCreated_date(rs.getString(BudgetDTO.getCREATED_DATE()));
                budget.setCreated_time(rs.getString(BudgetDTO.getCREATED_TIME()));
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
                budget.setAmount_used(rs.getDouble(BudgetDTO.getAMOUNT_USED()));
                budget.setAmount_left(rs.getDouble(BudgetDTO.getAMOUNT_LEFT()));
                budget.setStatus(rs.getBoolean(BudgetDTO.getSTATUS()));
                budget.setStart_date(rs.getString(BudgetDTO.getSTART_DATE()));
                budget.setEnd_date(rs.getString(BudgetDTO.getEND_DATE()));
                budget.setCreated_date(rs.getString(BudgetDTO.getCREATED_DATE()));
                budget.setCreated_time(rs.getString(BudgetDTO.getCREATED_TIME()));
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
            String query = "SELECT *,user.fullname FROM " + BudgetDTO.getBUDGET_DB() + " bud LEFT JOIN " + UserDTO.getUSERS_DB() + " user "
                    + "ON bud.user_id=user.id WHERE id = " + id + "";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                budget.setId(rs.getInt(BudgetDTO.getID()));
                budget.setName(rs.getString(BudgetDTO.getNAME()));
                budget.setTotal_amount(rs.getDouble(BudgetDTO.getTOTAL_AMOUNT()));
                budget.setAmount_used(rs.getDouble(BudgetDTO.getAMOUNT_USED()));
                budget.setAmount_left(rs.getDouble(BudgetDTO.getAMOUNT_LEFT()));
                budget.setStatus(rs.getBoolean(BudgetDTO.getSTATUS()));
                budget.setStart_date(rs.getString(BudgetDTO.getSTART_DATE()));
                budget.setEnd_date(rs.getString(BudgetDTO.getEND_DATE()));
                budget.setCreated_date(rs.getString(BudgetDTO.getCREATED_DATE()));
                budget.setCreated_time(rs.getString(BudgetDTO.getCREATED_TIME()));
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
                    + BudgetDTO.getAMOUNT_USED() + ","
                    + BudgetDTO.getAMOUNT_LEFT() + ","
                    + BudgetDTO.getSTATUS() + ","
                    + BudgetDTO.getSTART_DATE() + ","
                    + BudgetDTO.getEND_DATE() + ","
                    + BudgetDTO.getUSER_ID() + ","
                    + BudgetDTO.getCREATED_DATE() + ","
                    + BudgetDTO.getCREATED_TIME() + " ) VALUES (?,?,?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setString(1, budget.getName());
            pst.setDouble(2, budget.getTotal_amount());
            pst.setDouble(3, budget.getAmount_used());
            pst.setDouble(4, budget.getAmount_left());
            pst.setBoolean(5, budget.isStatus());
            pst.setString(6, budget.getStart_date());
            pst.setString(7, budget.getEnd_date());
            pst.setInt(8, budget.getUser_id());
            pst.setString(9, budget.getCreated_date());
            pst.setString(10, budget.getCreated_time());

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
                    + BudgetDTO.getAMOUNT_USED() + "='" + budget.getAmount_used() + "',"
                    + BudgetDTO.getAMOUNT_LEFT() + "='" + budget.getAmount_left() + "',"
                    + BudgetDTO.getSTATUS() + "='" + budget.isStatus() + "',"
                    + BudgetDTO.getSTART_DATE() + "='" + budget.getStart_date() + "',"
                    + BudgetDTO.getEND_DATE() + "='" + budget.getEnd_date() + "',"
                    + BudgetDTO.getUSER_ID() + "='" + budget.getUser_id() + "',"
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

}
