package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.BudgetAdditionDTO;
import ModelDTO.UserDTO;
import Models.BudgetAddition;
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
public class BudgetAdditionRepository implements AnonymousInterface<BudgetAddition> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public BudgetAdditionRepository() {
        conn = connectDB.ConnecrDb();
    }

    public List<BudgetAddition> listBudgetAddUp(int budget_id) {
        List<BudgetAddition> budgetAdditions = new ArrayList<>();
        try {
            String query = "SELECT bud.*,user.fullname AS user FROM " + BudgetAdditionDTO.getBUDGET_ADDITION_DB() + " bud "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user "
                    + "ON bud.user_id=user.id WHERE bud.budget_id = '" + budget_id + "' ORDER BY " + BudgetAdditionDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                BudgetAddition budgetAddition = new BudgetAddition();

                budgetAddition.setId(rs.getInt(BudgetAdditionDTO.getID()));
                budgetAddition.setSource(rs.getString(BudgetAdditionDTO.getSOURCE()));
                budgetAddition.setAmount(rs.getDouble(BudgetAdditionDTO.getAMOUNT()));
                budgetAddition.setBudget_id(rs.getInt(BudgetAdditionDTO.getBUDGET_ID()));
                budgetAddition.setUser_id(rs.getInt(BudgetAdditionDTO.getUSER_ID()));
                budgetAddition.setCreated_time(rs.getString(BudgetAdditionDTO.getCREATED_TIME()));
                budgetAddition.setCreated_date(rs.getString(BudgetAdditionDTO.getCREATED_DATE()));

                budgetAddition.setUser(rs.getString(BudgetAdditionDTO.getUSER()));

                budgetAdditions.add(budgetAddition);
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

        return budgetAdditions;
    }

    @Override
    public List<BudgetAddition> list(String year) {
        List<BudgetAddition> budgetAdditions = new ArrayList<>();
        try {
            String query = "SELECT bud.*,user.fullname AS user FROM " + BudgetAdditionDTO.getBUDGET_ADDITION_DB() + " bud "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON bud.user_id=user.id ORDER BY " + BudgetAdditionDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                BudgetAddition budgetAddition = new BudgetAddition();

                budgetAddition.setId(rs.getInt(BudgetAdditionDTO.getID()));
                budgetAddition.setSource(rs.getString(BudgetAdditionDTO.getSOURCE()));
                budgetAddition.setAmount(rs.getDouble(BudgetAdditionDTO.getAMOUNT()));
                budgetAddition.setBudget_id(rs.getInt(BudgetAdditionDTO.getBUDGET_ID()));
                budgetAddition.setUser_id(rs.getInt(BudgetAdditionDTO.getUSER_ID()));
                budgetAddition.setCreated_time(rs.getString(BudgetAdditionDTO.getCREATED_TIME()));
                budgetAddition.setCreated_date(rs.getString(BudgetAdditionDTO.getCREATED_DATE()));

                budgetAddition.setUser(rs.getString(BudgetAdditionDTO.getUSER()));

                budgetAdditions.add(budgetAddition);
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

        return budgetAdditions;
    }

    @Override
    public List<BudgetAddition> list() {
        List<BudgetAddition> budgetAdditions = new ArrayList<>();
        try {
            String query = "SELECT bud.*,user.fullname AS user FROM " + BudgetAdditionDTO.getBUDGET_ADDITION_DB() + " bud "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON bud.user_id=user.id ORDER BY " + BudgetAdditionDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                BudgetAddition budgetAddition = new BudgetAddition();

                budgetAddition.setId(rs.getInt(BudgetAdditionDTO.getID()));
                budgetAddition.setSource(rs.getString(BudgetAdditionDTO.getSOURCE()));
                budgetAddition.setAmount(rs.getDouble(BudgetAdditionDTO.getAMOUNT()));
                budgetAddition.setBudget_id(rs.getInt(BudgetAdditionDTO.getBUDGET_ID()));
                budgetAddition.setUser_id(rs.getInt(BudgetAdditionDTO.getUSER_ID()));
                budgetAddition.setCreated_time(rs.getString(BudgetAdditionDTO.getCREATED_TIME()));
                budgetAddition.setCreated_date(rs.getString(BudgetAdditionDTO.getCREATED_DATE()));

                budgetAddition.setUser(rs.getString(BudgetAdditionDTO.getUSER()));

                budgetAdditions.add(budgetAddition);
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

        return budgetAdditions;
    }

    @Override
    public BudgetAddition find(int id) {
        BudgetAddition budgetAddition = new BudgetAddition();
        try {
            String query = "SELECT bud.*,user.fullname AS user FROM " + BudgetAdditionDTO.getBUDGET_ADDITION_DB() + " bud "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON bud.user_id=user.id WHERE bud.id='" + id + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {

                budgetAddition.setId(rs.getInt(BudgetAdditionDTO.getID()));
                budgetAddition.setSource(rs.getString(BudgetAdditionDTO.getSOURCE()));
                budgetAddition.setAmount(rs.getDouble(BudgetAdditionDTO.getAMOUNT()));
                budgetAddition.setBudget_id(rs.getInt(BudgetAdditionDTO.getBUDGET_ID()));
                budgetAddition.setUser_id(rs.getInt(BudgetAdditionDTO.getUSER_ID()));
                budgetAddition.setCreated_time(rs.getString(BudgetAdditionDTO.getCREATED_TIME()));
                budgetAddition.setCreated_date(rs.getString(BudgetAdditionDTO.getCREATED_DATE()));

                budgetAddition.setUser(rs.getString(BudgetAdditionDTO.getUSER()));
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

        return budgetAddition;
    }

    @Override
    public int save(BudgetAddition budgetAddition) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + BudgetAdditionDTO.getBUDGET_ADDITION_DB() + " ("
                    + BudgetAdditionDTO.getBUDGET_ID() + ","
                    + BudgetAdditionDTO.getSOURCE() + ","
                    + BudgetAdditionDTO.getAMOUNT() + ","
                    + BudgetAdditionDTO.getUSER_ID() + ","
                    + BudgetAdditionDTO.getCREATED_TIME() + ","
                    + BudgetAdditionDTO.getCREATED_DATE() + " ) VALUES (?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setInt(1, budgetAddition.getBudget_id());
            pst.setString(2, budgetAddition.getSource());
            pst.setDouble(3, budgetAddition.getAmount());
            pst.setInt(4, budgetAddition.getUser_id());
            pst.setString(5, budgetAddition.getCreated_time());
            pst.setString(6, budgetAddition.getCreated_date());

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
    public void update(BudgetAddition budgetAddition, int id) {
        try {
            String query = "UPDATE " + BudgetAdditionDTO.getBUDGET_ADDITION_DB() + " SET "
                    + BudgetAdditionDTO.getBUDGET_ID() + "='" + budgetAddition.getBudget_id() + "',"
                    + BudgetAdditionDTO.getSOURCE() + "='" + budgetAddition.getSource() + "',"
                    + BudgetAdditionDTO.getAMOUNT() + "='" + budgetAddition.getAmount() + "',"
                    + BudgetAdditionDTO.getUSER_ID() + "='" + budgetAddition.getUser_id() + "',"
                    + BudgetAdditionDTO.getCREATED_TIME() + "='" + budgetAddition.getCreated_time() + "',"
                    + BudgetAdditionDTO.getCREATED_DATE() + "='" + budgetAddition.getCreated_date() + "' WHERE " + BudgetAdditionDTO.getID() + "='" + id + "'";

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
            String query = "DELETE FROM " + BudgetAdditionDTO.getBUDGET_ADDITION_DB() + " WHERE " + BudgetAdditionDTO.getID() + "='" + id + "'";
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
            String query = "DELETE FROM " + BudgetAdditionDTO.getBUDGET_ADDITION_DB();
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
            String query = "SELECT COUNT(" + BudgetAdditionDTO.getID() + ") AS total FROM " + BudgetAdditionDTO.getBUDGET_ADDITION_DB();
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

    public double totalAdditionalAmount(int budget_id) {
        double total = 0;
        try {
            String query = "SELECT SUM(" + BudgetAdditionDTO.getAMOUNT() + ") AS total FROM " + BudgetAdditionDTO.getBUDGET_ADDITION_DB() + " "
                    + "WHERE " + BudgetAdditionDTO.getBUDGET_ID() + "='" + budget_id + "'";
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
