package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.ExpensesTypeDTO;
import Models.ExpensesType;
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
public class ExpensesTypeRepository implements AnonymousInterface<ExpensesType> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public ExpensesTypeRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<ExpensesType> list(String year) {
        List<ExpensesType> expensesTypesList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + ExpensesTypeDTO.getEXPENSE_TYPE_DB() + " ORDER BY " + ExpensesTypeDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                ExpensesType expensesType = new ExpensesType();

                expensesType.setId(rs.getInt(ExpensesTypeDTO.getID()));
                expensesType.setName(rs.getString(ExpensesTypeDTO.getNAME()));
                expensesType.setCreated_time(rs.getString(ExpensesTypeDTO.getCREATED_TIME()));
                expensesType.setCreated_date(rs.getString(ExpensesTypeDTO.getCREATED_DATE()));

                expensesTypesList.add(expensesType);
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

        return expensesTypesList;
    }

    @Override
    public List<ExpensesType> list() {
        List<ExpensesType> expensesTypesList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + ExpensesTypeDTO.getEXPENSE_TYPE_DB() + " ORDER BY " + ExpensesTypeDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                ExpensesType expensesType = new ExpensesType();

                expensesType.setId(rs.getInt(ExpensesTypeDTO.getID()));
                expensesType.setName(rs.getString(ExpensesTypeDTO.getNAME()));
                expensesType.setCreated_time(rs.getString(ExpensesTypeDTO.getCREATED_TIME()));
                expensesType.setCreated_date(rs.getString(ExpensesTypeDTO.getCREATED_DATE()));

                expensesTypesList.add(expensesType);
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

        return expensesTypesList;
    }

    @Override
    public ExpensesType find(int id) {
        ExpensesType expensesType = new ExpensesType();

        try {
            String query = "SELECT * FROM " + ExpensesTypeDTO.getEXPENSE_TYPE_DB() + " WHERE " + ExpensesTypeDTO.getID() + "='" + id + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                expensesType.setId(rs.getInt(ExpensesTypeDTO.getID()));
                expensesType.setName(rs.getString(ExpensesTypeDTO.getNAME()));
                expensesType.setCreated_time(rs.getString(ExpensesTypeDTO.getCREATED_TIME()));
                expensesType.setCreated_date(rs.getString(ExpensesTypeDTO.getCREATED_DATE()));
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

        return expensesType;
    }

    @Override
    public int save(ExpensesType expensesType) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + ExpensesTypeDTO.getEXPENSE_TYPE_DB() + " ("
                    + ExpensesTypeDTO.getNAME() + ","
                    + ExpensesTypeDTO.getCREATED_TIME() + ","
                    + ExpensesTypeDTO.getCREATED_DATE() + " ) VALUES (?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setString(1, expensesType.getName());
            pst.setString(2, expensesType.getCreated_time());
            pst.setString(3, expensesType.getCreated_date());
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
    public void update(ExpensesType expensesType, int id) {
        try {
            String query = "UPDATE " + ExpensesTypeDTO.getEXPENSE_TYPE_DB() + " SET "
                    + ExpensesTypeDTO.getNAME() + "='" + expensesType.getName() + "',"
                    + ExpensesTypeDTO.getCREATED_TIME() + "='" + expensesType.getCreated_time() + "',"
                    + ExpensesTypeDTO.getCREATED_DATE() + "='" + expensesType.getCreated_date() + "' WHERE " + ExpensesTypeDTO.getID() + "='" + id + "'";

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
            String query = "DELETE FROM " + ExpensesTypeDTO.getEXPENSE_TYPE_DB() + " WHERE " + ExpensesTypeDTO.getID() + "='" + id + "'";
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
            String query = "DELETE FROM " + ExpensesTypeDTO.getEXPENSE_TYPE_DB();
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
            String query = "SELECT COUNT(" + ExpensesTypeDTO.getID() + ") AS total FROM " + ExpensesTypeDTO.getEXPENSE_TYPE_DB();
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
