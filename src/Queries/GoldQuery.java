package Queries;

import Classess.Customers;
import Classess.Gold;
import Interface.GoldInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GoldQuery implements GoldInterface {

    Connect mets = new Connect();
    CustomerQuery cusQ = new CustomerQuery();
    UserQuery useQ = new UserQuery();

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public GoldQuery() {
        conn = Connect.ConnecrDb();
    }

    @Override
    public void save(Gold gold) {
        try {
            String sql = "INSERT INTO gold (top,down,density,karat,base,value,money,customer_id,user_id,created_at) VALUES (?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, gold.getTop());
            pst.setDouble(2, gold.getDown());
            pst.setDouble(3, gold.getDensity());
            pst.setDouble(4, gold.getKarat());
            pst.setDouble(5, gold.getBase());
            pst.setDouble(6, gold.getValue());
            pst.setDouble(7, gold.getMoney());
            pst.setInt(8, gold.getCustomer_id());
            pst.setInt(9, gold.getUser_id());
            pst.setString(10, gold.getCreated_at());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record is saved");
        } catch (Exception e) {
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void update(Gold gold, int id) {
        try {
            String sql = "UPDATE gold SET top='" + gold.getTop() + "', down='" + gold.getDown() + "', density='" + gold.getDensity() + "',"
                    + "karat='" + gold.getKarat() + "', base='" + gold.getBase() + "', value='" + gold.getValue() + "', money='" + gold.getMoney() + "',"
                    + "WHERE id='" + id + "'";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record is updated");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM gold WHERE id='" + id + "'";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Customer is deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Gold> findOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.   
    }

    @Override
    public List<Gold> findAll() {
        List<Gold> goldList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM gold ORDER BY id DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Gold gold = new Gold();
                gold.setId(rs.getInt("id"));
                gold.setTop(rs.getDouble("top"));
                gold.setDown(rs.getDouble("down"));
                gold.setDensity(rs.getDouble("density"));
                gold.setKarat(rs.getDouble("karat"));
                gold.setBase(rs.getDouble("base"));
                gold.setValue(rs.getDouble("value"));
                gold.setMoney(rs.getDouble("money"));
                gold.setCustomer_id(rs.getInt("customer_id"));
                gold.setUser_id(rs.getInt("user_id"));
                gold.setCreated_at(rs.getString("created_at"));
                goldList.add(gold);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
        return goldList;
    }

    public List<Gold> findAllDate() {
        List<Gold> goldList = new ArrayList<>();
        try {
            String sql = "SELECT created_at FROM gold GROUP BY created_at ORDER BY id DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Gold gold = new Gold();
                gold.setCreated_at(rs.getString("created_at"));
                goldList.add(gold);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
        return goldList;
    }

    public List<Gold> findAllSpecific(String date) {
        List<Gold> goldList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM gold WHERE created_at='" + date + "' ORDER BY id DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Gold gold = new Gold();
                gold.setId(rs.getInt("id"));
                gold.setTop(rs.getDouble("top"));
                gold.setDown(rs.getDouble("down"));
                gold.setDensity(rs.getDouble("density"));
                gold.setKarat(rs.getDouble("karat"));
                gold.setBase(rs.getDouble("base"));
                gold.setValue(rs.getDouble("value"));
                gold.setMoney(rs.getDouble("money"));
                gold.setCustomer_id(rs.getInt("customer_id"));
                gold.setUser_id(rs.getInt("user_id"));
                gold.setCreated_at(rs.getString("created_at"));
                goldList.add(gold);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
        return goldList;
    }

    //===================== Method ==================================
    public void saveGold(JTextField top,
            JTextField down,
            JTextField density,
            JTextField karat,
            JComboBox base,
            JTextField value,
            JLabel money,
            JLabel cus_id,
            JLabel use_id, JComboBox customer) {

        DateFormat dateFormat = new SimpleDateFormat("MMMM EE dd, yyyy");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());

        if (customer.getSelectedIndex() <= 0 || top.getText().isEmpty() || down.getText().isEmpty() || base.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(null, "Fill all required fields before saving");
        } else {

            double tp = Double.parseDouble(top.getText());
            double dow = Double.parseDouble(down.getText());
            double den = Double.parseDouble(density.getText());
            double kar = Double.parseDouble(karat.getText());
            double bas = Double.parseDouble(base.getSelectedItem().toString());
            double val = Double.parseDouble(value.getText());
            double mon = Double.parseDouble(money.getText());

            int c_id = Integer.parseInt(cus_id.getText());
            int u_id = Integer.parseInt(use_id.getText());

            Gold g = new Gold(c_id, u_id, tp, dow, den, kar, bas, val, mon, date);
            save(g);
        }
    }

    public void deleteUser(JLabel lbl_id) {
        int id = Integer.parseInt(lbl_id.getText());
        delete(id);
    }

    public void setGoldToTable(JTable table) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        tmodel.setRowCount(0);

        Object[] object;

        List<Gold> gold = findAll();

        if (gold.size() <= 0) {
            JOptionPane.showMessageDialog(null, "No Record is Added", "NO RECORDS", JOptionPane.INFORMATION_MESSAGE);
        }

        int id = 0;
        for (Gold gol : gold) {

            double top = gol.getTop();
            double down = gol.getDown();
            double density = gol.getDensity();
            double karat = gol.getKarat();
            double base = gol.getBase();
            double pounds = gol.getValue();
            double money = gol.getMoney();
            String customer = cusQ.returnName(gol.getCustomer_id());
            String user = useQ.returnName(gol.getUser_id());
            String date = gol.getCreated_at();

            id++;
            object = new Object[]{id, mets.capitalizer(customer), top, pounds, down, density, karat, base, money, mets.capitalizer(user), date};
            tmodel.addRow(object);
        }
    }

    public void setGoldToCombo(JComboBox cmb, String title) {
        List<Gold> gold = findAllDate();
        DefaultComboBoxModel cust = (DefaultComboBoxModel) cmb.getModel();
        cust.removeAllElements();
        cust.addElement(title);

        for (Gold cus : gold) {
            cust.addElement(cus.getCreated_at());
        }
    }

    public void setGoldToTableSale(JTable table, JComboBox dateOne) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        tmodel.setRowCount(0);

        Object[] object;

        List<Gold> gold = findAllSpecific(dateOne.getSelectedItem().toString());

        if (gold.size() <= 0) {
            JOptionPane.showMessageDialog(null, "No Record is Added", "NO RECORDS", JOptionPane.INFORMATION_MESSAGE);
        }

        int id = 0;
        for (Gold gol : gold) {

            double top = gol.getTop();
            double down = gol.getDown();
            double density = gol.getDensity();
            double karat = gol.getKarat();
            double base = gol.getBase();
            double pound = gol.getValue();
            double money = gol.getMoney();
            String customer = cusQ.returnName(gol.getCustomer_id());
            String user = useQ.returnName(gol.getUser_id());
            String date = gol.getCreated_at();

            id++;
            object = new Object[]{id, mets.capitalizer(customer), top, pound, down, density, karat, base, money, mets.capitalizer(user), date};
            tmodel.addRow(object);
        }
    }
    //==================== End of Method ============================
}
