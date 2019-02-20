package Queries;

import Classess.Customers;
import Classess.User;
import Interface.CustomerInterface;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CustomerQuery implements CustomerInterface {

    Connect mets = new Connect();

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public CustomerQuery() {
        conn = Connect.ConnecrDb();
    }

    @Override
    public void save(Customers customer) {
        try {
            String sql = "INSERT INTO customer (fullname,contact,hometown, type) VALUES (?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, customer.getFullname());
            pst.setString(2, customer.getContact());
            pst.setString(3, customer.getHometown());
            pst.setString(4, customer.getType());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Customer is saved");
        } catch (Exception e) {
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
                ///JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void update(Customers customer, int id) {
        try {
            String sql = "UPDATE customer SET fullname='" + customer.getFullname() + "', contact='" + customer.getContact() + "', hometown='" + customer.getHometown() + "',type='" + customer.getType() + "' WHERE id='" + id + "'";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Customer with id " + id + " is updated");
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
            String sql = "DELETE FROM customer WHERE id='" + id + "'";
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
    public int returnID(String fullname) {
        int id = 0;
        try {
            String sql = "SELECT id FROM customer WHERE fullname='" + fullname.toLowerCase() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
        return id;
    }

    @Override
    public List<Customers> findAll() {
        List<Customers> customerList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM customer";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Customers cus = new Customers();
                cus.setId(rs.getInt("id"));
                cus.setFullname(rs.getString("fullname"));
                cus.setContact(rs.getString("contact"));
                cus.setHometown(rs.getString("hometown"));
                cus.setType(rs.getString("type"));
                customerList.add(cus);
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
        return customerList;
    }

    @Override
    public List<Customers> findOne(String id) {
        List<Customers> customerList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM customer WHERE fullname='" + id + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                Customers cus = new Customers();
                cus.setId(rs.getInt("id"));
                cus.setFullname(rs.getString("fullname"));
                cus.setContact(rs.getString("contact"));
                cus.setHometown(rs.getString("hometown"));
                cus.setType(rs.getString("type"));
                customerList.add(cus);
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
        return customerList;
    }

    public boolean cusExit(String fname) {
        boolean nam = false;
        try {
            String sql = "SELECT fullname FROM customer WHERE fullname = '" + fname.toLowerCase() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                nam = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
        return nam;
    }

    public String returnName(int id) {
        String name = "";
        try {
            String sql = "SELECT fullname FROM customer WHERE id='" + id + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                name = rs.getString("fullname");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
        return name;
    }

    //================= Method Create with overriding ===========
    public void saveCustomer(JTextField fullname, JFormattedTextField contact, JTextField hTown, JComboBox type) {
        if (fullname.getText().isEmpty() || hTown.getText().isEmpty() || contact.getText().isEmpty() || type.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(null, "No field can be empty");
        } else {
            String fame = fullname.getText().toLowerCase().trim();
            String con = contact.getText().trim();
            String hometown = hTown.getText().trim();
            String typ = type.getSelectedItem().toString();
            if (cusExit(fame) == true) {
                JOptionPane.showMessageDialog(null, "Same name already exist");
            } else {
                Customers c = new Customers(fame, con, hometown, typ);
                save(c);
            }
        }
    }

    public void setCustomerToCombo(JComboBox cmb, String title) {
        List<Customers> customer = findAll();
        DefaultComboBoxModel cust = (DefaultComboBoxModel) cmb.getModel();
        cust.removeAllElements();
        cust.addElement(title);

        for (Customers cus : customer) {
            cust.addElement(cus.getFullname().toUpperCase());
        }
    }

    public void deleteCustomer(JLabel lbl_id) {
        int id = Integer.parseInt(lbl_id.getText());
        delete(id);
    }

    public void updateCustomer(JLabel lbl_id, JTextField fullname, JFormattedTextField contact, JTextField hTown, JTextField type) {
        if (lbl_id.getText().isEmpty() || fullname.getText().isEmpty() || hTown.getText().isEmpty() || contact.getText().isEmpty() || type.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Select Customer to Update");
        } else {
            int id = Integer.parseInt(lbl_id.getText());
            String fame = fullname.getText();
            String con = contact.getText();
            String hometown = hTown.getText();
            String typ = type.getText();
            Customers c = new Customers(fame, con, hometown, typ);
            update(c, id);
        }
    }

    public int getCusID(JComboBox fulname) {
        int id = 0;
        if (fulname.getSelectedIndex() <= 0) {
        } else {
            String name = fulname.getSelectedItem().toString().toLowerCase();
            id = returnID(name);
        }
        return id;
    }

    public void setCustomerToTable(JTable table) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        tmodel.setRowCount(0);

        Object[] object;

        List<Customers> cus = findAll();

        if (cus.size() <= 0) {
            JOptionPane.showMessageDialog(null, "No Customer is Added", "NO CUSTOMER", JOptionPane.INFORMATION_MESSAGE);
        }

        int id = 0;
        for (Customers customer : cus) {

            String fullname = customer.getFullname();
            String contact = customer.getContact();
            String home = customer.getHometown();
            String type = customer.getType();
            id++;
            object = new Object[]{id, type, fullname.trim(), contact, home.trim()};
            tmodel.addRow(object);
        }
    }

    public void tableClick(JTable table, JLabel id, JTextField fname, JTextField contact, JTextField home, JTextField type) {
        try {
            int row = table.getSelectedRow();
            String idd = table.getModel().getValueAt(row, 2).toString();

            List<Customers> customer = findOne(idd);

            for (Customers cus : customer) {
                id.setText("" + cus.getId());
                fname.setText(cus.getFullname());
                contact.setText(cus.getContact());
                home.setText(cus.getHometown());
                type.setText(cus.getType());
            }

        } catch (Exception e) {
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }
    }

    //===================End =====================================
}
