package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.ShopDTO;
import Models.Shop;
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
public class ShopRepository implements AnonymousInterface<Shop> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public ShopRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<Shop> list(int year) {
        List<Shop> shopsList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + ShopDTO.getSHOP_DB() + " ORDER BY " + ShopDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Shop shop = new Shop();

                shop.setId(rs.getInt(ShopDTO.getID()));
                shop.setName(rs.getString(ShopDTO.getNAME()));
                shop.setLocation(rs.getString(ShopDTO.getLOCATION()));
                shop.setContacts(rs.getString(ShopDTO.getCONTACTS()));
                shop.setEmail_address(rs.getString(ShopDTO.getEMAIL_ADDRESS()));
                shop.setDigital_address(rs.getString(ShopDTO.getDIGITAL_ADDRESS()));
                shop.setMotto(rs.getString(ShopDTO.getMOTTO()));

                shopsList.add(shop);
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
        return shopsList;
    }

    @Override
    public List<Shop> list() {
        List<Shop> shopsList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + ShopDTO.getSHOP_DB() + " ORDER BY " + ShopDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Shop shop = new Shop();

                shop.setId(rs.getInt(ShopDTO.getID()));
                shop.setName(rs.getString(ShopDTO.getNAME()));
                shop.setLocation(rs.getString(ShopDTO.getLOCATION()));
                shop.setContacts(rs.getString(ShopDTO.getCONTACTS()));
                shop.setEmail_address(rs.getString(ShopDTO.getEMAIL_ADDRESS()));
                shop.setDigital_address(rs.getString(ShopDTO.getDIGITAL_ADDRESS()));
                shop.setMotto(rs.getString(ShopDTO.getMOTTO()));

                shopsList.add(shop);
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
        return shopsList;
    }

    @Override
    public Shop find(int id) {
        Shop shop = new Shop();

        try {
            String query = "SELECT * FROM " + ShopDTO.getSHOP_DB() + " ORDER BY " + ShopDTO.getID() + " DESC LIMIT 1";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                shop.setId(rs.getInt(ShopDTO.getID()));
                shop.setName(rs.getString(ShopDTO.getNAME()));
                shop.setLocation(rs.getString(ShopDTO.getLOCATION()));
                shop.setContacts(rs.getString(ShopDTO.getCONTACTS()));
                shop.setEmail_address(rs.getString(ShopDTO.getEMAIL_ADDRESS()));
                shop.setDigital_address(rs.getString(ShopDTO.getDIGITAL_ADDRESS()));
                shop.setMotto(rs.getString(ShopDTO.getMOTTO()));
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
        return shop;
    }

    @Override
    public int save(Shop shop) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + ShopDTO.getSHOP_DB() + " ("
                    + ShopDTO.getNAME() + ","
                    + ShopDTO.getLOCATION() + ","
                    + ShopDTO.getCONTACTS() + ","
                    + ShopDTO.getEMAIL_ADDRESS() + ","
                    + ShopDTO.getDIGITAL_ADDRESS() + ","
                    + ShopDTO.getMOTTO() + " ) VALUES (?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setString(1, shop.getName());
            pst.setString(2, shop.getLocation());
            pst.setString(3, shop.getContacts());
            pst.setString(4, shop.getEmail_address());
            pst.setString(5, shop.getDigital_address());
            pst.setString(6, shop.getMotto());

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
    public void update(Shop shop, int id) {
        try {
            String query = "UPDATE " + ShopDTO.getSHOP_DB() + " SET "
                    + ShopDTO.getNAME() + "='" + shop.getName() + "',"
                    + ShopDTO.getLOCATION() + "='" + shop.getLocation() + "',"
                    + ShopDTO.getCONTACTS() + "='" + shop.getContacts() + "',"
                    + ShopDTO.getEMAIL_ADDRESS() + "='" + shop.getEmail_address() + "',"
                    + ShopDTO.getDIGITAL_ADDRESS() + "='" + shop.getDigital_address() + "',"
                    + ShopDTO.getMOTTO() + "='" + shop.getMotto() + "' WHERE " + ShopDTO.getID() + "='" + id + "'";

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
            String query = "DELETE FROM " + ShopDTO.getSHOP_DB() + " WHERE " + ShopDTO.getID() + "='" + id + "'";
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
            String query = "DELETE FROM " + ShopDTO.getSHOP_DB();
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
            String query = "SELECT COUNT(" + ShopDTO.getID() + ") AS total FROM " + ShopDTO.getSHOP_DB();
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
