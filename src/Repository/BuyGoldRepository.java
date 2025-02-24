package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.BuyGoldDTO;
import ModelDTO.CREDITPAYMENTDTO;
import ModelDTO.CustomerDTO;
import ModelDTO.UserDTO;
import Models.BuyGold;
import Models.Receipt;
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
    public List<BuyGold> list(String createdDate) {
        List<BuyGold> buyGoldsList = new ArrayList<>();
        try {
            String query = "SELECT gold.*,user.fullname AS user,customer.fullname AS customer FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " gold "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON gold.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON gold.customer_id=customer.id "
                    + "WHERE gold.created_date = '" + createdDate + "' ORDER BY " + BuyGoldDTO.getID() + " DESC";
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
                buyGold.setCredit_balance(rs.getDouble(BuyGoldDTO.getCREDIT_BALANCE()));
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
            String query = "SELECT gold.*,user.fullname AS user,customer.fullname AS customer FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " gold "
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
                buyGold.setCredit_balance(rs.getDouble(BuyGoldDTO.getCREDIT_BALANCE()));
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
            String query = "SELECT gold.*,user.fullname AS user,customer.fullname AS customer FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " gold "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON gold.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON gold.customer_id=customer.id "
                    + "WHERE gold.id = '" + id + "'";
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
                buyGold.setCredit_balance(rs.getDouble(BuyGoldDTO.getCREDIT_BALANCE()));
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
                    + BuyGoldDTO.getCREDIT_BALANCE() + ","
                    + BuyGoldDTO.getUSER_ID() + ","
                    + BuyGoldDTO.getCUSTOMER_ID() + ","
                    + BuyGoldDTO.getRAW_DATE() + ","
                    + BuyGoldDTO.getCREATED_DATE() + ","
                    + BuyGoldDTO.getCREATED_TIME() + " ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pst.setDouble(10, buyGold.getCredit_balance());
            pst.setInt(11, buyGold.getUser_id());
            pst.setInt(12, buyGold.getCustomer_id());
            pst.setString(13, buyGold.getRaw_date());
            pst.setString(14, buyGold.getCreated_date());
            pst.setString(15, buyGold.getCreated_time());

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
                    + BuyGoldDTO.getCREDIT_BALANCE() + "='" + buyGold.getCredit_balance() + "',"
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

    public BuyGold findByCode(String code) {
        BuyGold buyGold = new BuyGold();

        try {
            String query = "SELECT gold.*,user.fullname AS user,customer.fullname AS customer FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " gold "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON gold.user_id=user.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON gold.customer_id=customer.id "
                    + "WHERE gold.code = '" + code + "'";
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
                buyGold.setCredit_balance(rs.getDouble(BuyGoldDTO.getCREDIT_BALANCE()));
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

    public double summationDaily(String createdDate) {
        double total = 0;
        try {
            String query = "SELECT SUM(" + BuyGoldDTO.getTOTAL_AMOUNT() + ") AS total FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " WHERE " + BuyGoldDTO.getCREATED_DATE() + "='" + createdDate + "'";
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

    public Receipt receiptData(int id) {
        Receipt receipt = new Receipt();

        try {
            String query = "SELECT gold.*,credit_payment.paid,credit_payment.balance FROM " + BuyGoldDTO.getBUY_GOLD_DB() + " gold "
                    + "LEFT JOIN " + CREDITPAYMENTDTO.getCREDIT_PAYMENTS_DB() + " credit_payment ON credit_payment.buy_gold_id=gold.id "
                    + "WHERE gold.id= '" + id + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                receipt.setTotalAmount(rs.getDouble("total_amount"));
                receipt.setAmountPaid(rs.getDouble("paid"));
                receipt.setBasePrice(rs.getDouble("base_price"));
                receipt.setBalance(rs.getDouble("balance"));
                receipt.setCredit_balance(rs.getDouble("credit_balance"));
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
        return receipt;
    }

}
