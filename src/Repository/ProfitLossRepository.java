package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.ProfitLossDTO;
import Models.ProfitLoss;
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
public class ProfitLossRepository implements AnonymousInterface<ProfitLoss> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public ProfitLossRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<ProfitLoss> list(String createdDate) {
        List<ProfitLoss> profitLossList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + ProfitLossDTO.getPROFIT_LOSS_DB() + " profit WHERE profit.created_date = '" + createdDate + "' ORDER BY " + ProfitLossDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                ProfitLoss profitLoss = new ProfitLoss();

                profitLoss.setId(rs.getInt(ProfitLossDTO.getID()));
                profitLoss.setStart_date(rs.getString(ProfitLossDTO.getSTART_DATE()));
                profitLoss.setEnd_date(rs.getString(ProfitLossDTO.getEND_DATE()));
                profitLoss.setTotal_budget(rs.getDouble(ProfitLossDTO.getTOTAL_BUDGET()));
                profitLoss.setBudget_left(rs.getDouble(ProfitLossDTO.getBUDGET_LEFT()));
                profitLoss.setAmount_from_gold(rs.getDouble(ProfitLossDTO.getAMOUNT_FROM_GOLD()));
                profitLoss.setProfit_loss(rs.getDouble(ProfitLossDTO.getPROFIT_LOSS()));
                profitLoss.setCreated_date(rs.getString(ProfitLossDTO.getCREATED_DATE()));
                profitLoss.setCreated_time(rs.getString(ProfitLossDTO.getCREATED_TIME()));
                profitLoss.setRaw_date(rs.getString(ProfitLossDTO.getRAW_DATE()));

                profitLossList.add(profitLoss);
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

        return profitLossList;
    }

    public List<ProfitLoss> findProfitLossDates(String startDate, String endDate) {
        List<ProfitLoss> profitLossList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + ProfitLossDTO.getPROFIT_LOSS_DB() + " profit WHERE profit.created_date >= '" + startDate + "' AND profit.created_date <= '" + endDate + "' ORDER BY profit.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                ProfitLoss profitLoss = new ProfitLoss();

                profitLoss.setId(rs.getInt(ProfitLossDTO.getID()));
                profitLoss.setStart_date(rs.getString(ProfitLossDTO.getSTART_DATE()));
                profitLoss.setEnd_date(rs.getString(ProfitLossDTO.getEND_DATE()));
                profitLoss.setTotal_budget(rs.getDouble(ProfitLossDTO.getTOTAL_BUDGET()));
                profitLoss.setBudget_left(rs.getDouble(ProfitLossDTO.getBUDGET_LEFT()));
                profitLoss.setAmount_from_gold(rs.getDouble(ProfitLossDTO.getAMOUNT_FROM_GOLD()));
                profitLoss.setProfit_loss(rs.getDouble(ProfitLossDTO.getPROFIT_LOSS()));
                profitLoss.setCreated_date(rs.getString(ProfitLossDTO.getCREATED_DATE()));
                profitLoss.setCreated_time(rs.getString(ProfitLossDTO.getCREATED_TIME()));
                profitLoss.setRaw_date(rs.getString(ProfitLossDTO.getRAW_DATE()));

                profitLossList.add(profitLoss);
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

        return profitLossList;
    }

    @Override
    public List<ProfitLoss> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ProfitLoss find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int save(ProfitLoss profitLoss) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + ProfitLossDTO.getPROFIT_LOSS_DB() + " ("
                    + ProfitLossDTO.getTOTAL_BUDGET() + ","
                    + ProfitLossDTO.getBUDGET_LEFT() + ","
                    + ProfitLossDTO.getTOTAL_EXPENSES() + ","
                    + ProfitLossDTO.getAMOUNT_FROM_GOLD() + ","
                    + ProfitLossDTO.getPROFIT_LOSS() + ","
                    + ProfitLossDTO.getSTART_DATE() + ","
                    + ProfitLossDTO.getEND_DATE() + ","
                    + ProfitLossDTO.getCREATED_DATE() + ","
                    + ProfitLossDTO.getCREATED_TIME() + ","
                    + ProfitLossDTO.getRAW_DATE() + " ) VALUES (?,?,?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setDouble(1, profitLoss.getTotal_budget());
            pst.setDouble(2, profitLoss.getBudget_left());
            pst.setDouble(3, profitLoss.getTotal_expenses());
            pst.setDouble(4, profitLoss.getAmount_from_gold());
            pst.setDouble(5, profitLoss.getProfit_loss());
            pst.setString(6, profitLoss.getStart_date());
            pst.setString(7, profitLoss.getEnd_date());
            pst.setString(8, profitLoss.getCreated_date());
            pst.setString(9, profitLoss.getCreated_time());
            pst.setString(10, profitLoss.getRaw_date());

            pst.executeUpdate();

            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }

            JOptionPane.showMessageDialog(null, "Record saved successful");
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
    public void update(ProfitLoss profitLoss, int id) {
        try {
            String query = "UPDATE " + ProfitLossDTO.getPROFIT_LOSS_DB() + " SET "
                    + ProfitLossDTO.getTOTAL_BUDGET() + "='" + profitLoss.getTotal_budget() + "',"
                    + ProfitLossDTO.getBUDGET_LEFT() + "='" + profitLoss.getBudget_left() + "',"
                    + ProfitLossDTO.getTOTAL_EXPENSES() + "='" + profitLoss.getTotal_expenses() + "',"
                    + ProfitLossDTO.getAMOUNT_FROM_GOLD() + "='" + profitLoss.getAmount_from_gold() + "',"
                    + ProfitLossDTO.getPROFIT_LOSS() + "='" + profitLoss.getProfit_loss() + "',"
                    + ProfitLossDTO.getSTART_DATE() + "='" + profitLoss.getStart_date() + "',"
                    + ProfitLossDTO.getEND_DATE() + "='" + profitLoss.getEnd_date() + "',"
                    + ProfitLossDTO.getCREATED_TIME() + "='" + profitLoss.getCreated_time() + "',"
                    + ProfitLossDTO.getCREATED_DATE() + "='" + profitLoss.getCreated_date() + "',"
                    + ProfitLossDTO.getRAW_DATE() + "='" + profitLoss.getRaw_date() + "' WHERE " + ProfitLossDTO.getID() + "='" + id + "'";

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
            String query = "DELETE FROM " + ProfitLossDTO.getPROFIT_LOSS_DB() + " WHERE " + ProfitLossDTO.getID() + "='" + id + "'";
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
            String query = "DELETE FROM " + ProfitLossDTO.getPROFIT_LOSS_DB();
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
            String query = "SELECT COUNT(" + ProfitLossDTO.getID() + ") AS total FROM " + ProfitLossDTO.getPROFIT_LOSS_DB();
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
