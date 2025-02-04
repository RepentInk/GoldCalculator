package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.CustomerDTO;
import Models.Customer;
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
public class CustomerRepository implements AnonymousInterface<Customer> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public CustomerRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<Customer> list() {
        List<Customer> customersList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + CustomerDTO.getCUSTOMER_DB() + " ORDER BY id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();

                customer.setId(rs.getInt(CustomerDTO.getID()));
                customer.setFullname(rs.getString(CustomerDTO.getFULL_NAME()));
                customer.setPhone_number(rs.getString(CustomerDTO.getPHONE_NUMBER()));
                customer.setPlace_of_work(rs.getString(CustomerDTO.getPLACE_OF_WORK()));
                customer.setEmergency_number(rs.getString(CustomerDTO.getEMERGENCY_NUMBER()));
                customer.setCreated_at(rs.getString(CustomerDTO.getCREATED_AT()));
                customer.setUpdated_at(rs.getString(CustomerDTO.getUPDATED_AT()));

                customersList.add(customer);
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
        return customersList;
    }

    @Override
    public List<Customer> list(int year) {
        List<Customer> customerList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + CustomerDTO.getCUSTOMER_DB() + " cus WHERE strftime('%Y',cus.created_at) = '" + year + "' ORDER BY cus.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();

                customer.setId(rs.getInt(CustomerDTO.getID()));
                customer.setFullname(rs.getString(CustomerDTO.getFULL_NAME()));
                customer.setPhone_number(rs.getString(CustomerDTO.getPHONE_NUMBER()));
                customer.setPlace_of_work(rs.getString(CustomerDTO.getPLACE_OF_WORK()));
                customer.setEmergency_number(rs.getString(CustomerDTO.getEMERGENCY_NUMBER()));
                customer.setCreated_at(rs.getString(CustomerDTO.getCREATED_AT()));
                customer.setUpdated_at(rs.getString(CustomerDTO.getUPDATED_AT()));

                customerList.add(customer);
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

        return customerList;
    }

    @Override
    public Customer find(int id) {
        Customer customer = new Customer();
        try {
            String query = "SELECT * FROM " + CustomerDTO.getCUSTOMER_DB() + " cus WHERE cus.id = '" + id + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                customer.setId(rs.getInt(CustomerDTO.getID()));
                customer.setFullname(rs.getString(CustomerDTO.getFULL_NAME()));
                customer.setPhone_number(rs.getString(CustomerDTO.getPHONE_NUMBER()));
                customer.setPlace_of_work(rs.getString(CustomerDTO.getPLACE_OF_WORK()));
                customer.setEmergency_number(rs.getString(CustomerDTO.getEMERGENCY_NUMBER()));
                customer.setCreated_at(rs.getString(CustomerDTO.getCREATED_AT()));
                customer.setUpdated_at(rs.getString(CustomerDTO.getUPDATED_AT()));
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

        return customer;
    }

    @Override
    public int save(Customer customer) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + CustomerDTO.getCUSTOMER_DB() + " ("
                    + CustomerDTO.getFULL_NAME() + ","
                    + CustomerDTO.getPHONE_NUMBER() + ","
                    + CustomerDTO.getPLACE_OF_WORK() + ","
                    + CustomerDTO.getEMERGENCY_NUMBER() + ","
                    + CustomerDTO.getCREATED_AT() + ","
                    + CustomerDTO.getUPDATED_AT() + " ) VALUES (?,?,?,?,?,?)";
            pst = conn.prepareStatement(query);

            pst.setString(1, customer.getFullname());
            pst.setString(2, customer.getPhone_number());
            pst.setString(3, customer.getPlace_of_work());
            pst.setString(4, customer.getEmergency_number());
            pst.setString(5, customer.getCreated_at());
            pst.setString(6, customer.getUpdated_at());

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
    public void update(Customer customer, int id) {
        try {
            String query = "UPDATE " + CustomerDTO.getCUSTOMER_DB() + " SET "
                    + CustomerDTO.getFULL_NAME() + "='" + customer.getFullname() + "',"
                    + CustomerDTO.getPHONE_NUMBER() + "='" + customer.getPhone_number() + "',"
                    + CustomerDTO.getPLACE_OF_WORK() + "='" + customer.getPlace_of_work() + "',"
                    + CustomerDTO.getEMERGENCY_NUMBER() + "='" + customer.getEmergency_number() + "',"
                    + CustomerDTO.getCREATED_AT() + "='" + customer.getCreated_at() + "',"
                    + CustomerDTO.getUPDATED_AT() + "='" + customer.getUpdated_at() + "' WHERE " + CustomerDTO.getID() + "='" + id + "'";

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
            String query = "DELETE FROM " + CustomerDTO.getCUSTOMER_DB() + " WHERE " + CustomerDTO.getID() + "='" + id + "'";
            pst = conn.prepareStatement(query);
            pst.executeUpdate();
            conn.setAutoCommit(true);
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
            String query = "DELETE FROM " + CustomerDTO.getCUSTOMER_DB();
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
            String query = "SELECT COUNT(" + CustomerDTO.getID() + ") AS total FROM " + CustomerDTO.getCUSTOMER_DB();
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

    public Customer findByName(String fullname, String phoneNumber) {
        Customer customer = new Customer();
        try {
            String query = "SELECT * FROM " + CustomerDTO.getCUSTOMER_DB() + " WHERE fullname = '" + fullname + "' AND phone_number='" + phoneNumber + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                customer.setId(rs.getInt(CustomerDTO.getID()));
                customer.setFullname(rs.getString(CustomerDTO.getFULL_NAME()));
                customer.setPhone_number(rs.getString(CustomerDTO.getPHONE_NUMBER()));
                customer.setPlace_of_work(rs.getString(CustomerDTO.getPLACE_OF_WORK()));
                customer.setEmergency_number(rs.getString(CustomerDTO.getEMERGENCY_NUMBER()));
                customer.setCreated_at(rs.getString(CustomerDTO.getCREATED_AT()));
                customer.setUpdated_at(rs.getString(CustomerDTO.getUPDATED_AT()));
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

        return customer;
    }

}
