package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.BuyGoldDTO;
import ModelDTO.CustomerDTO;
import ModelDTO.UserDTO;
import Models.BuyGold;
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
public class BuyGoldRepository implements AnonymousInterface<BuyGold> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public BuyGoldRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<BuyGold> list(int year) {
        List<BuyGold> buyGoldsList = new ArrayList<>();
        try {
            String query = "SELECT *,user.fullname AS user,customer.fullname AS customer FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " gold "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON gold.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON gold.customer_id=customer.id "
                    + "WHERE strftime('%Y', gold.raw_date) = '" + year + "' ORDER BY " + CustomerDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                BuyGold buyGold = new BuyGold();

                buyGold.setId(rs.getInt(BuyGoldDTO.getID()));
                buyGold.setCode(rs.getString(BuyGoldDTO.getCODE()));
                buyGold.setTop(rs.getDouble(BuyGoldDTO.getTOP()));
                buyGold.setDown(rs.getDouble(BuyGoldDTO.getDOWN()));
                buyGold.setDensity(rs.getDouble(BuyGoldDTO.getDENSITY()));
                buyGold.setKarat(rs.getDouble(BuyGoldDTO.getKARAT()));
                buyGold.setPounds(rs.getDouble(BuyGoldDTO.getPOUNDS()));
                buyGold.setBase_price(rs.getDouble(BuyGoldDTO.getBASE_PRICE()));
                buyGold.setTotal_weight(rs.getDouble(BuyGoldDTO.getTOTAL_WEIGHT()));
                buyGold.setTotal_amount(rs.getDouble(BuyGoldDTO.getTOTAL_AMOUNT()));
                buyGold.setCreated_date(rs.getString(BuyGoldDTO.getCREATED_DATE()));
                buyGold.setCreated_time(rs.getString(BuyGoldDTO.getCREATED_TIME()));
                buyGold.setRaw_date(rs.getString(BuyGoldDTO.getRAW_DATE()));
                buyGold.setUser_id(rs.getInt(BuyGoldDTO.getUSER_ID()));
                buyGold.setCustomer_id(rs.getInt(BuyGoldDTO.getCUSTOMER_ID()));

                buyGold.setUser(rs.getString(BuyGoldDTO.getUSER()));
                buyGold.setCustomer(rs.getString(BuyGoldDTO.getCUSTOMER()));

                buyGoldsList.add(buyGold);
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
        return buyGoldsList;
    }

    @Override
    public List<BuyGold> list() {
        List<BuyGold> buyGoldsList = new ArrayList<>();
        try {
            String query = "SELECT *,user.fullname AS user,customer.fullname AS customer FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " gold "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON gold.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON gold.customer_id=customer.id "
                    + "ORDER BY " + CustomerDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                BuyGold buyGold = new BuyGold();

                buyGold.setId(rs.getInt(BuyGoldDTO.getID()));
                buyGold.setCode(rs.getString(BuyGoldDTO.getCODE()));
                buyGold.setTop(rs.getDouble(BuyGoldDTO.getTOP()));
                buyGold.setDown(rs.getDouble(BuyGoldDTO.getDOWN()));
                buyGold.setDensity(rs.getDouble(BuyGoldDTO.getDENSITY()));
                buyGold.setKarat(rs.getDouble(BuyGoldDTO.getKARAT()));
                buyGold.setPounds(rs.getDouble(BuyGoldDTO.getPOUNDS()));
                buyGold.setBase_price(rs.getDouble(BuyGoldDTO.getBASE_PRICE()));
                buyGold.setTotal_weight(rs.getDouble(BuyGoldDTO.getTOTAL_WEIGHT()));
                buyGold.setTotal_amount(rs.getDouble(BuyGoldDTO.getTOTAL_AMOUNT()));
                buyGold.setCreated_date(rs.getString(BuyGoldDTO.getCREATED_DATE()));
                buyGold.setCreated_time(rs.getString(BuyGoldDTO.getCREATED_TIME()));
                buyGold.setRaw_date(rs.getString(BuyGoldDTO.getRAW_DATE()));
                buyGold.setUser_id(rs.getInt(BuyGoldDTO.getUSER_ID()));
                buyGold.setCustomer_id(rs.getInt(BuyGoldDTO.getCUSTOMER_ID()));

                buyGold.setUser(rs.getString(BuyGoldDTO.getUSER()));
                buyGold.setCustomer(rs.getString(BuyGoldDTO.getCUSTOMER()));

                buyGoldsList.add(buyGold);
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
        return buyGoldsList;
    }

    @Override
    public BuyGold find(int id) {
        BuyGold buyGold = new BuyGold();

        try {
            String query = "SELECT *,user.fullname AS user,customer.fullname AS customer FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " gold "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON gold.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON gold.customer_id=customer.id "
                    + "WHERE " + BuyGoldDTO.getID() + " = '" + id + "' ORDER BY " + CustomerDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {

                buyGold.setId(rs.getInt(BuyGoldDTO.getID()));
                buyGold.setCode(rs.getString(BuyGoldDTO.getCODE()));
                buyGold.setTop(rs.getDouble(BuyGoldDTO.getTOP()));
                buyGold.setDown(rs.getDouble(BuyGoldDTO.getDOWN()));
                buyGold.setDensity(rs.getDouble(BuyGoldDTO.getDENSITY()));
                buyGold.setKarat(rs.getDouble(BuyGoldDTO.getKARAT()));
                buyGold.setPounds(rs.getDouble(BuyGoldDTO.getPOUNDS()));
                buyGold.setBase_price(rs.getDouble(BuyGoldDTO.getBASE_PRICE()));
                buyGold.setTotal_weight(rs.getDouble(BuyGoldDTO.getTOTAL_WEIGHT()));
                buyGold.setTotal_amount(rs.getDouble(BuyGoldDTO.getTOTAL_AMOUNT()));
                buyGold.setCreated_date(rs.getString(BuyGoldDTO.getCREATED_DATE()));
                buyGold.setCreated_time(rs.getString(BuyGoldDTO.getCREATED_TIME()));
                buyGold.setRaw_date(rs.getString(BuyGoldDTO.getRAW_DATE()));
                buyGold.setUser_id(rs.getInt(BuyGoldDTO.getUSER_ID()));
                buyGold.setCustomer_id(rs.getInt(BuyGoldDTO.getCUSTOMER_ID()));

                buyGold.setUser(rs.getString(BuyGoldDTO.getUSER()));
                buyGold.setCustomer(rs.getString(BuyGoldDTO.getCUSTOMER()));
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

        return buyGold;
    }

    @Override
    public int save(BuyGold buyGold) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + BuyGoldDTO.getBUY_GOLD_DB() + " ("
                    + BuyGoldDTO.getCODE() + ","
                    + BuyGoldDTO.getTOP() + ","
                    + BuyGoldDTO.getDOWN() + ","
                    + BuyGoldDTO.getDENSITY() + ","
                    + BuyGoldDTO.getKARAT() + ","
                    + BuyGoldDTO.getPOUNDS() + ","
                    + BuyGoldDTO.getBASE_PRICE() + ","
                    + BuyGoldDTO.getTOTAL_WEIGHT() + ","
                    + BuyGoldDTO.getTOTAL_AMOUNT() + ","
                    + BuyGoldDTO.getUSER_ID() + ","
                    + BuyGoldDTO.getCUSTOMER_ID() + ","
                    + BuyGoldDTO.getRAW_DATE() + ","
                    + BuyGoldDTO.getCREATED_DATE() + ","
                    + BuyGoldDTO.getCREATED_TIME() + " ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setString(1, buyGold.getCode());
            pst.setDouble(2, buyGold.getTop());
            pst.setDouble(3, buyGold.getDown());
            pst.setDouble(4, buyGold.getDensity());
            pst.setDouble(5, buyGold.getKarat());
            pst.setDouble(6, buyGold.getPounds());
            pst.setDouble(7, buyGold.getBase_price());
            pst.setDouble(8, buyGold.getTotal_weight());
            pst.setDouble(9, buyGold.getTotal_amount());
            pst.setInt(10, buyGold.getUser_id());
            pst.setInt(11, buyGold.getCustomer_id());
            pst.setString(12, buyGold.getRaw_date());
            pst.setString(13, buyGold.getCreated_date());
            pst.setString(14, buyGold.getCreated_time());

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
    public void update(BuyGold buyGold, int id) {
        try {
            String query = "UPDATE " + BuyGoldDTO.getBUY_GOLD_DB() + " SET "
                    + BuyGoldDTO.getCODE() + "='" + buyGold.getCode() + "',"
                    + BuyGoldDTO.getTOP() + "='" + buyGold.getTop() + "',"
                    + BuyGoldDTO.getDOWN() + "='" + buyGold.getDown() + "',"
                    + BuyGoldDTO.getDENSITY() + "='" + buyGold.getDensity() + "',"
                    + BuyGoldDTO.getKARAT() + "='" + buyGold.getKarat() + "',"
                    + BuyGoldDTO.getPOUNDS() + "='" + buyGold.getPounds() + "',"
                    + BuyGoldDTO.getBASE_PRICE() + "='" + buyGold.getBase_price() + "',"
                    + BuyGoldDTO.getTOTAL_WEIGHT() + "='" + buyGold.getTotal_weight() + "',"
                    + BuyGoldDTO.getTOTAL_AMOUNT() + "='" + buyGold.getTotal_amount() + "',"
                    + BuyGoldDTO.getUSER_ID() + "='" + buyGold.getUser_id() + "',"
                    + BuyGoldDTO.getCUSTOMER_ID() + "='" + buyGold.getCustomer_id() + "',"
                    + BuyGoldDTO.getRAW_DATE() + "='" + buyGold.getRaw_date() + "',"
                    + BuyGoldDTO.getCREATED_DATE() + "='" + buyGold.getCreated_date() + "',"
                    + BuyGoldDTO.getCREATED_TIME() + "='" + buyGold.getCreated_time() + "' WHERE " + BuyGoldDTO.getID() + "='" + id + "'";

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
            String query = "DELETE FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " WHERE " + BuyGoldDTO.getID() + "='" + id + "'";
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
            String query = "DELETE FROM " + BuyGoldDTO.getBUY_GOLD_DB();
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
            String query = "SELECT COUNT(" + BuyGoldDTO.getID() + ") AS total FROM " + BuyGoldDTO.getBUY_GOLD_DB();
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
