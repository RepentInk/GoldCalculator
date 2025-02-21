package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.BudgetDTO;
import ModelDTO.ExpensesDTO;
import ModelDTO.ExpensesTypeDTO;
import ModelDTO.UserDTO;
import Models.Expenses;
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
public class ExpensesRepository implements AnonymousInterface<Expenses> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public ExpensesRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<Expenses> list(String createdDate) {
        List<Expenses> expensesList = new ArrayList<>();
        try {
            String query = "SELECT expense.*,user.fullname AS user,expenseType.name AS expense_type,budget.name AS budget FROM " + ExpensesDTO.getEXPENSES_DB() + " expense "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON expense.user_id=user.id "
                    + "LEFT JOIN " + ExpensesTypeDTO.getEXPENSE_TYPE_DB() + " expenseType ON expense.expenses_type_id=expenseType.id "
                    + "LEFT JOIN " + BudgetDTO.getBUDGET_DB() + " budget ON expense.budget_id=budget.id "
                    + "WHERE expense.created_date = '" + createdDate + "' ORDER BY expense.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Expenses expenses = new Expenses();

                expenses.setId(rs.getInt(ExpensesDTO.getID()));
                expenses.setExpenses_type_id(rs.getInt(ExpensesDTO.getEXPENSE_TYPE_ID()));
                expenses.setBudget_id(rs.getInt(ExpensesDTO.getBUDGET_ID()));
                expenses.setAmount(rs.getDouble(ExpensesDTO.getAMOUNT()));
                expenses.setPaid_to(rs.getString(ExpensesDTO.getPAID_TO()));
                expenses.setBudget_before(rs.getDouble(ExpensesDTO.getBUDGET_BEFORE()));
                expenses.setBudget_after(rs.getDouble(ExpensesDTO.getBUDGET_AFTER()));
                expenses.setUser_id(rs.getInt(ExpensesDTO.getUSER_ID()));
                expenses.setCreated_time(rs.getString(ExpensesDTO.getCREATED_TIME()));
                expenses.setCreated_date(rs.getString(ExpensesDTO.getCREATED_DATE()));

                expenses.setUser(rs.getString(ExpensesDTO.getUSER()));
                expenses.setBudget(rs.getString(ExpensesDTO.getBUDGET()));
                expenses.setExpense_type(rs.getString(ExpensesDTO.getEXPENSE_TYPE()));

                expensesList.add(expenses);
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

        return expensesList;
    }

    @Override
    public List<Expenses> list() {
        List<Expenses> expensesList = new ArrayList<>();
        try {
            String query = "SELECT expense.*,user.fullname AS user,expenseType.name AS expense_type,budget.name AS budget FROM " + ExpensesDTO.getEXPENSES_DB() + " expense "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON expense.user_id=user.id "
                    + "LEFT JOIN " + ExpensesTypeDTO.getEXPENSE_TYPE_DB() + " expenseType ON expense.expenses_type_id=expenseType.id "
                    + "LEFT JOIN " + BudgetDTO.getBUDGET_DB() + " budget ON expense.budget_id=budget.id "
                    + "ORDER BY expense.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Expenses expenses = new Expenses();

                expenses.setId(rs.getInt(ExpensesDTO.getID()));
                expenses.setExpenses_type_id(rs.getInt(ExpensesDTO.getEXPENSE_TYPE_ID()));
                expenses.setBudget_id(rs.getInt(ExpensesDTO.getBUDGET_ID()));
                expenses.setAmount(rs.getDouble(ExpensesDTO.getAMOUNT()));
                expenses.setPaid_to(rs.getString(ExpensesDTO.getPAID_TO()));
                expenses.setBudget_before(rs.getDouble(ExpensesDTO.getBUDGET_BEFORE()));
                expenses.setBudget_after(rs.getDouble(ExpensesDTO.getBUDGET_AFTER()));
                expenses.setUser_id(rs.getInt(ExpensesDTO.getUSER_ID()));
                expenses.setCreated_time(rs.getString(ExpensesDTO.getCREATED_TIME()));
                expenses.setCreated_date(rs.getString(ExpensesDTO.getCREATED_DATE()));

                expenses.setUser(rs.getString(ExpensesDTO.getUSER()));
                expenses.setBudget(rs.getString(ExpensesDTO.getBUDGET()));
                expenses.setExpense_type(rs.getString(ExpensesDTO.getEXPENSE_TYPE()));

                expensesList.add(expenses);
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

        return expensesList;
    }

    @Override
    public Expenses find(int id) {
        Expenses expenses = new Expenses();

        try {
            String query = "SELECT expense.*,user.fullname AS user,expenseType.name AS expense_type,budget.name AS budget FROM " + ExpensesDTO.getEXPENSES_DB() + " expense "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON expense.user_id=user.id "
                    + "LEFT JOIN " + ExpensesTypeDTO.getEXPENSE_TYPE_DB() + " expenseType ON expense.expenses_type_id=expenseType.id "
                    + "LEFT JOIN " + BudgetDTO.getBUDGET_DB() + " budget ON expense.budget_id=budget.id "
                    + "WHERE expense.id = '" + id + "' ORDER BY expense.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                expenses.setId(rs.getInt(ExpensesDTO.getID()));
                expenses.setExpenses_type_id(rs.getInt(ExpensesDTO.getEXPENSE_TYPE_ID()));
                expenses.setBudget_id(rs.getInt(ExpensesDTO.getBUDGET_ID()));
                expenses.setAmount(rs.getDouble(ExpensesDTO.getAMOUNT()));
                expenses.setPaid_to(rs.getString(ExpensesDTO.getPAID_TO()));
                expenses.setBudget_before(rs.getDouble(ExpensesDTO.getBUDGET_BEFORE()));
                expenses.setBudget_after(rs.getDouble(ExpensesDTO.getBUDGET_AFTER()));
                expenses.setUser_id(rs.getInt(ExpensesDTO.getUSER_ID()));
                expenses.setCreated_time(rs.getString(ExpensesDTO.getCREATED_TIME()));
                expenses.setCreated_date(rs.getString(ExpensesDTO.getCREATED_DATE()));

                expenses.setUser(rs.getString(ExpensesDTO.getUSER()));
                expenses.setBudget(rs.getString(ExpensesDTO.getBUDGET()));
                expenses.setExpense_type(rs.getString(ExpensesDTO.getEXPENSE_TYPE()));
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

        return expenses;
    }

    @Override
    public int save(Expenses expenses) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + ExpensesDTO.getEXPENSES_DB() + " ("
                    + ExpensesDTO.getEXPENSE_TYPE_ID() + ","
                    + ExpensesDTO.getBUDGET_ID() + ","
                    + ExpensesDTO.getAMOUNT() + ","
                    + ExpensesDTO.getPAID_TO() + ","
                    + ExpensesDTO.getBUDGET_BEFORE() + ","
                    + ExpensesDTO.getBUDGET_AFTER() + ","
                    + ExpensesDTO.getUSER_ID() + ","
                    + ExpensesDTO.getCREATED_TIME() + ","
                    + ExpensesDTO.getCREATED_DATE() + " ) VALUES (?,?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setInt(1, expenses.getExpenses_type_id());
            pst.setInt(2, expenses.getBudget_id());
            pst.setDouble(3, expenses.getAmount());
            pst.setString(4, expenses.getPaid_to());
            pst.setDouble(5, expenses.getBudget_before());
            pst.setDouble(6, expenses.getBudget_after());
            pst.setInt(7, expenses.getUser_id());
            pst.setString(8, expenses.getCreated_time());
            pst.setString(9, expenses.getCreated_date());
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
    public void update(Expenses expenses, int id) {
        try {
            String query = "UPDATE " + ExpensesDTO.getEXPENSES_DB() + " SET "
                    + ExpensesDTO.getEXPENSE_TYPE_ID() + "='" + expenses.getExpenses_type_id() + "',"
                    + ExpensesDTO.getBUDGET_ID() + "='" + expenses.getBudget_id() + "',"
                    + ExpensesDTO.getPAID_TO() + "='" + expenses.getPaid_to() + "',"
                    + ExpensesDTO.getBUDGET_BEFORE() + "='" + expenses.getBudget_before() + "',"
                    + ExpensesDTO.getBUDGET_AFTER() + "='" + expenses.getBudget_after() + "',"
                    + ExpensesDTO.getAMOUNT() + "='" + expenses.getAmount() + "',"
                    + ExpensesDTO.getUSER_ID() + "='" + expenses.getUser_id() + "',"
                    + ExpensesDTO.getCREATED_TIME() + "='" + expenses.getCreated_time() + "',"
                    + ExpensesDTO.getCREATED_DATE() + "='" + expenses.getCreated_date() + "' WHERE " + ExpensesDTO.getID() + "='" + id + "'";

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
            String query = "DELETE FROM " + ExpensesDTO.getEXPENSES_DB() + " WHERE " + ExpensesDTO.getID() + "='" + id + "'";
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
            String query = "DELETE FROM " + ExpensesDTO.getEXPENSES_DB();
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
            String query = "SELECT COUNT(" + ExpensesDTO.getID() + ") AS total FROM " + ExpensesDTO.getEXPENSES_DB();
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

    public double summationOfBudgetUsed(int budget_id) {
        double total = 0;
        try {
            String query = "SELECT SUM(" + ExpensesDTO.getAMOUNT() + ") AS total FROM " + ExpensesDTO.getEXPENSES_DB() + " WHERE " + ExpensesDTO.getBUDGET_ID() + "='" + budget_id + "'";
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

    public boolean expenseType(int exepenses_type_id) {
        boolean status = false;

        try {
            String query = "SELECT * FROM " + ExpensesDTO.getEXPENSES_DB() + " WHERE " + ExpensesDTO.getEXPENSE_TYPE_ID() + " = '" + exepenses_type_id + "' ORDER BY " + ExpensesDTO.getID() + " DESC LIMIT 1";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                status = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return status;
    }

}
