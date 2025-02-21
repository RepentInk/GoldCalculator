package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.BudgetDTO;
import ModelDTO.BuyGoldDTO;
import ModelDTO.CustomerDTO;
import ModelDTO.PaymentDTO;
import ModelDTO.UserDTO;
import Models.Payments;
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
public class PaymentsRepository implements AnonymousInterface<Payments> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public PaymentsRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<Payments> list(String createdDate) {
        List<Payments> paymentsList = new ArrayList<>();
        try {
            String query = "SELECT payment.*,user.fullname AS user,gold.code AS buy_gold,customer.fullname AS customer,budget.name AS budget FROM " + PaymentDTO.getPAYMENT_DB() + " payment "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON payment.user_id=user.id "
                    + "LEFT JOIN " + BuyGoldDTO.getBUY_GOLD_DB() + " gold ON payment.buy_gold_id=gold.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON gold.customer_id=customer.id "
                    + "LEFT JOIN " + BudgetDTO.getBUDGET_DB() + " budget ON payment.budget_id=budget.id "
                    + "WHERE payment.created_date = '" + createdDate + "' ORDER BY payment.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Payments payment = new Payments();

                payment.setId(rs.getInt(PaymentDTO.getID()));
                payment.setAmount_paid(rs.getDouble(PaymentDTO.getAMOUNT_PAID()));
                payment.setBalance(rs.getDouble(PaymentDTO.getBALANCE()));
                payment.setBudget_after_payment(rs.getDouble(PaymentDTO.getBUDGET_AFTER_PAYMENT()));
                payment.setBudget_before_payment(rs.getDouble(PaymentDTO.getBUDGET_BEFORE_PAYMENT()));
                payment.setBudget_id(rs.getInt(PaymentDTO.getBUDGET_ID()));
                payment.setBuy_gold_id(rs.getInt(PaymentDTO.getBUY_GOLD_ID()));
                payment.setUser_id(rs.getInt(PaymentDTO.getUSER_ID()));
                payment.setCreated_date(rs.getString(PaymentDTO.getCREATED_DATE()));
                payment.setCreated_time(rs.getString(PaymentDTO.getCREATED_TIME()));
                payment.setRaw_date(rs.getString(PaymentDTO.getRAW_DATE()));

                payment.setUser(rs.getString(PaymentDTO.getUSER()));
                payment.setCustomer(rs.getString(PaymentDTO.getCUSTOMER()));
                payment.setBudget(rs.getString(PaymentDTO.getBUDGET()));
                payment.setBuy_gold(rs.getString(PaymentDTO.getBUY_GOLD()));

                paymentsList.add(payment);
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
        return paymentsList;
    }

    @Override
    public List<Payments> list() {
        List<Payments> paymentsList = new ArrayList<>();
        try {
            String query = "SELECT payment.*,user.fullname AS user,gold.code AS buy_gold,customer.fullname AS customer,budget.name AS budget FROM " + PaymentDTO.getPAYMENT_DB() + " payment "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON payment.user_id=user.id "
                    + "LEFT JOIN " + BuyGoldDTO.getBUY_GOLD_DB() + " gold ON payment.buy_gold_id=gold.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON gold.customer_id=customer.id "
                    + "LEFT JOIN " + BudgetDTO.getBUDGET_DB() + " budget ON payment.budget_id=budget.id "
                    + "ORDER BY payment.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Payments payment = new Payments();

                payment.setId(rs.getInt(PaymentDTO.getID()));
                payment.setAmount_paid(rs.getDouble(PaymentDTO.getAMOUNT_PAID()));
                payment.setBalance(rs.getDouble(PaymentDTO.getBALANCE()));
                payment.setBudget_after_payment(rs.getDouble(PaymentDTO.getBUDGET_AFTER_PAYMENT()));
                payment.setBudget_before_payment(rs.getDouble(PaymentDTO.getBUDGET_BEFORE_PAYMENT()));
                payment.setBudget_id(rs.getInt(PaymentDTO.getBUDGET_ID()));
                payment.setBuy_gold_id(rs.getInt(PaymentDTO.getBUY_GOLD_ID()));
                payment.setUser_id(rs.getInt(PaymentDTO.getUSER_ID()));
                payment.setCreated_date(rs.getString(PaymentDTO.getCREATED_DATE()));
                payment.setCreated_time(rs.getString(PaymentDTO.getCREATED_TIME()));
                payment.setRaw_date(rs.getString(PaymentDTO.getRAW_DATE()));

                payment.setUser(rs.getString(PaymentDTO.getUSER()));
                payment.setCustomer(rs.getString(PaymentDTO.getCUSTOMER()));
                payment.setBudget(rs.getString(PaymentDTO.getBUDGET()));
                payment.setBuy_gold(rs.getString(PaymentDTO.getBUY_GOLD()));

                paymentsList.add(payment);
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
        return paymentsList;
    }

    @Override
    public Payments find(int id) {
        Payments payment = new Payments();

        try {
            String query = "SELECT payment.*,user.fullname AS user,gold.code AS buy_gold,customer.fullname AS customer,budget.name AS budget FROM " + PaymentDTO.getPAYMENT_DB() + " payment "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON payment.user_id=user.id "
                    + "LEFT JOIN " + BuyGoldDTO.getBUY_GOLD_DB() + " gold ON payment.buy_gold_id=gold.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON gold.customer_id=customer.id "
                    + "LEFT JOIN " + BudgetDTO.getBUDGET_DB() + " budget ON payment.budget_id=budget.id "
                    + "WHERE payment.id = '" + id + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {

                payment.setId(rs.getInt(PaymentDTO.getID()));
                payment.setAmount_paid(rs.getDouble(PaymentDTO.getAMOUNT_PAID()));
                payment.setBalance(rs.getDouble(PaymentDTO.getBALANCE()));
                payment.setBudget_after_payment(rs.getDouble(PaymentDTO.getBUDGET_AFTER_PAYMENT()));
                payment.setBudget_before_payment(rs.getDouble(PaymentDTO.getBUDGET_BEFORE_PAYMENT()));
                payment.setBudget_id(rs.getInt(PaymentDTO.getBUDGET_ID()));
                payment.setBuy_gold_id(rs.getInt(PaymentDTO.getBUY_GOLD_ID()));
                payment.setUser_id(rs.getInt(PaymentDTO.getUSER_ID()));
                payment.setCreated_date(rs.getString(PaymentDTO.getCREATED_DATE()));
                payment.setCreated_time(rs.getString(PaymentDTO.getCREATED_TIME()));
                payment.setRaw_date(rs.getString(PaymentDTO.getRAW_DATE()));

                payment.setUser(rs.getString(PaymentDTO.getUSER()));
                payment.setCustomer(rs.getString(PaymentDTO.getCUSTOMER()));
                payment.setBudget(rs.getString(PaymentDTO.getBUDGET()));
                payment.setBuy_gold(rs.getString(PaymentDTO.getBUY_GOLD()));
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
        return payment;
    }

    @Override
    public int save(Payments payment) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + PaymentDTO.getPAYMENT_DB() + " ("
                    + PaymentDTO.getAMOUNT_PAID() + ","
                    + PaymentDTO.getBALANCE() + ","
                    + PaymentDTO.getBUDGET_AFTER_PAYMENT() + ","
                    + PaymentDTO.getBUDGET_BEFORE_PAYMENT() + ","
                    + PaymentDTO.getBUDGET_ID() + ","
                    + PaymentDTO.getBUY_GOLD_ID() + ","
                    + PaymentDTO.getUSER_ID() + ","
                    + PaymentDTO.getRAW_DATE() + ","
                    + PaymentDTO.getCREATED_DATE() + ","
                    + PaymentDTO.getCREATED_TIME() + " ) VALUES (?,?,?,?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setDouble(1, payment.getAmount_paid());
            pst.setDouble(2, payment.getBalance());
            pst.setDouble(3, payment.getBudget_after_payment());
            pst.setDouble(4, payment.getBudget_before_payment());
            pst.setInt(5, payment.getBudget_id());
            pst.setInt(6, payment.getBuy_gold_id());
            pst.setInt(7, payment.getUser_id());
            pst.setString(8, payment.getRaw_date());
            pst.setString(9, payment.getCreated_date());
            pst.setString(10, payment.getCreated_time());
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
    public void update(Payments payment, int id) {
        try {
            String query = "UPDATE " + PaymentDTO.getPAYMENT_DB() + " SET "
                    + PaymentDTO.getAMOUNT_PAID() + "='" + payment.getAmount_paid() + "',"
                    + PaymentDTO.getBALANCE() + "='" + payment.getBalance() + "',"
                    + PaymentDTO.getBUDGET_AFTER_PAYMENT() + "='" + payment.getBudget_after_payment() + "',"
                    + PaymentDTO.getBUDGET_BEFORE_PAYMENT() + "='" + payment.getBudget_before_payment() + "',"
                    + PaymentDTO.getBUDGET_ID() + "='" + payment.getBudget_id() + "',"
                    + PaymentDTO.getBUY_GOLD_ID() + "='" + payment.getBuy_gold_id() + "',"
                    + PaymentDTO.getUSER_ID() + "='" + payment.getUser_id() + "',"
                    + PaymentDTO.getRAW_DATE() + "='" + payment.getRaw_date() + "',"
                    + PaymentDTO.getCREATED_DATE() + "='" + payment.getCreated_date() + "',"
                    + PaymentDTO.getCREATED_TIME() + "='" + payment.getCreated_time() + "' WHERE " + PaymentDTO.getID() + "='" + id + "'";

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
            String query = "DELETE FROM " + PaymentDTO.getPAYMENT_DB() + " WHERE " + PaymentDTO.getID() + "='" + id + "'";
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
            String query = "DELETE FROM " + PaymentDTO.getPAYMENT_DB();
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
            String query = "SELECT COUNT(" + PaymentDTO.getID() + ") AS total FROM " + PaymentDTO.getPAYMENT_DB();
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

    public double summationOfBudget(int budget_id) {
        double total = 0;
        try {
            String query = "SELECT SUM(" + PaymentDTO.getAMOUNT_PAID() + ") AS total_budget FROM " + PaymentDTO.getPAYMENT_DB() + " WHERE " + PaymentDTO.getBUDGET_ID() + "='" + budget_id + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(PaymentDTO.getTOTAL_BUDGET());
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

    public double summationOfPurchasePayment(int buy_gold_id) {
        double total = 0;
        try {
            String query = "SELECT SUM(" + PaymentDTO.getAMOUNT_PAID() + ") AS total_payment FROM " + PaymentDTO.getPAYMENT_DB() + " WHERE " + PaymentDTO.getBUY_GOLD_ID() + "='" + buy_gold_id + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(PaymentDTO.getTOTAL_GOLD_PAYMENT());
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
            String query = "SELECT payment.amount_paid,payment.balance,gold.total_amount,gold.base_price,gold.credit_balance FROM " + PaymentDTO.getPAYMENT_DB() + " payment "
                    + "LEFT JOIN " + BuyGoldDTO.getBUY_GOLD_DB() + " gold ON payment.buy_gold_id=gold.id "
                    + "WHERE payment.id = '" + id + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                receipt.setTotalAmount(rs.getDouble("total_amount"));
                receipt.setAmountPaid(rs.getDouble("amount_paid"));
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

    public List<Payments> paymentHistory(int buy_gold_id) {
        List<Payments> paymentsList = new ArrayList<>();
        try {
            String query = "SELECT payment.*,user.fullname AS user,gold.code AS buy_gold,customer.fullname AS customer,budget.name AS budget FROM " + PaymentDTO.getPAYMENT_DB() + " payment "
                    + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON payment.user_id=user.id "
                    + "LEFT JOIN " + BuyGoldDTO.getBUY_GOLD_DB() + " gold ON payment.buy_gold_id=gold.id "
                    + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON gold.customer_id=customer.id "
                    + "LEFT JOIN " + BudgetDTO.getBUDGET_DB() + " budget ON payment.budget_id=budget.id "
                    + "WHERE " + PaymentDTO.getBUY_GOLD_ID() + " = '" + buy_gold_id + "' ORDER BY payment.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Payments payment = new Payments();

                payment.setId(rs.getInt(PaymentDTO.getID()));
                payment.setAmount_paid(rs.getDouble(PaymentDTO.getAMOUNT_PAID()));
                payment.setBalance(rs.getDouble(PaymentDTO.getBALANCE()));
                payment.setBudget_after_payment(rs.getDouble(PaymentDTO.getBUDGET_AFTER_PAYMENT()));
                payment.setBudget_before_payment(rs.getDouble(PaymentDTO.getBUDGET_BEFORE_PAYMENT()));
                payment.setBudget_id(rs.getInt(PaymentDTO.getBUDGET_ID()));
                payment.setBuy_gold_id(rs.getInt(PaymentDTO.getBUY_GOLD_ID()));
                payment.setUser_id(rs.getInt(PaymentDTO.getUSER_ID()));
                payment.setCreated_date(rs.getString(PaymentDTO.getCREATED_DATE()));
                payment.setCreated_time(rs.getString(PaymentDTO.getCREATED_TIME()));
                payment.setRaw_date(rs.getString(PaymentDTO.getRAW_DATE()));

                payment.setUser(rs.getString(PaymentDTO.getUSER()));
                payment.setCustomer(rs.getString(PaymentDTO.getCUSTOMER()));
                payment.setBudget(rs.getString(PaymentDTO.getBUDGET()));
                payment.setBuy_gold(rs.getString(PaymentDTO.getBUY_GOLD()));

                paymentsList.add(payment);
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
        return paymentsList;
    }

}
