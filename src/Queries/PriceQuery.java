package Queries;

import Classess.Customers;
import Classess.Gold;
import Classess.Price;
import Interface.GoldInterface;
import Interface.PriceInterface;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PriceQuery implements PriceInterface {

    Connect mets = new Connect();

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public PriceQuery() {
        conn = Connect.ConnecrDb();
    }

    @Override
    public void save(Price price) {
        try {
            String sql = "INSERT INTO price (baseprice,date) VALUES (?,?)";
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, price.getPrice());
            pst.setString(2, price.getDate());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Price is saved");
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
    public void update(Price price, int id) {
        try {
            String sql = "UPDATE price SET baseprice='" + price.getPrice() + "', date='" + price.getDate() + "' WHERE id='" + id + "'";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Price is updated");
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
            String sql = "DELETE FROM price WHERE id='" + id + "'";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Price is deleted");
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
    public List<Price> findOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.   
    }

    @Override
    public List<Price> findAll() {
        List<Price> priceList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM price";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Price price = new Price();
                price.setId(rs.getInt("id"));
                price.setPrice(rs.getDouble("baseprice"));
                price.setDate(rs.getString("date"));
                priceList.add(price);
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
        return priceList;
    }

    public boolean priceExit(double bprice) {
        boolean nam = false;
        try {
            String sql = "SELECT baseprice FROM price WHERE baseprice = '" + bprice + "'";
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

    //================= Method Create with overriding ===========
    public void savePrice(JDateChooser date, JTextField price) {
        if (date == null || price.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter New Base Price and Select Date");
        } else {
            String date1 = ((JTextField) date.getDateEditor().getUiComponent()).getText();
            double pric = Double.parseDouble(price.getText());
            if (priceExit(pric) == true) {
                JOptionPane.showMessageDialog(null, "Value of Base Price Already exist");
            } else {
                Price p = new Price(pric, date1);
                save(p);
            }
        }
    }

    public void setPriceToCombo(JComboBox cmb, String title) {
        List<Price> price = findAll();
        DefaultComboBoxModel pric = (DefaultComboBoxModel) cmb.getModel();
        pric.removeAllElements();
        pric.addElement(title);

        for (Price p : price) {
            pric.addElement(p.getPrice());
        }
    }

    public void deletePrice(JLabel lbl_id) {
        int id = Integer.parseInt(lbl_id.getText());
        delete(id);
    }

    public void updatePrice() {

    }

    //===================End =====================================
}
