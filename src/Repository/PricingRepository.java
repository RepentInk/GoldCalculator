package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.PricingDTO;
import Models.Pricing;
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
public class PricingRepository implements AnonymousInterface<Pricing> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public PricingRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<Pricing> list(int year) {
        List<Pricing> pricingsList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + PricingDTO.getPRICING_DB() + " price WHERE strftime('%Y', price.created_date) = '" + year + "' ORDER BY " + PricingDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Pricing pricing = new Pricing();

                pricing.setId(rs.getInt(PricingDTO.getID()));
                pricing.setCurrent_price(rs.getDouble(PricingDTO.getCURRENT_PRICE()));
                pricing.setOld_price(rs.getDouble(PricingDTO.getOLD_PRICE()));
                pricing.setTop_divide_value(rs.getDouble(PricingDTO.getTOP_DIVIDE_VALUE()));
                pricing.setDensity_minus_value(rs.getDouble(PricingDTO.getDENSITY_MINUS_VALUE()));
                pricing.setDensity_multiply_value(rs.getDouble(PricingDTO.getDENSITY_MULTIPLY_VALUE()));
                pricing.setKarat_divide_value(rs.getDouble(PricingDTO.getKARAT_DIVIDE_VALUE()));
                pricing.setCreated_date(rs.getString(PricingDTO.getCREATED_DATE()));

                pricingsList.add(pricing);
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
        return pricingsList;
    }

    @Override
    public List<Pricing> list() {
        List<Pricing> pricingsList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + PricingDTO.getPRICING_DB() + " ORDER BY " + PricingDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Pricing pricing = new Pricing();

                pricing.setId(rs.getInt(PricingDTO.getID()));
                pricing.setCurrent_price(rs.getDouble(PricingDTO.getCURRENT_PRICE()));
                pricing.setOld_price(rs.getDouble(PricingDTO.getOLD_PRICE()));
                pricing.setTop_divide_value(rs.getDouble(PricingDTO.getTOP_DIVIDE_VALUE()));
                pricing.setDensity_minus_value(rs.getDouble(PricingDTO.getDENSITY_MINUS_VALUE()));
                pricing.setDensity_multiply_value(rs.getDouble(PricingDTO.getDENSITY_MULTIPLY_VALUE()));
                pricing.setKarat_divide_value(rs.getDouble(PricingDTO.getKARAT_DIVIDE_VALUE()));
                pricing.setCreated_date(rs.getString(PricingDTO.getCREATED_DATE()));

                pricingsList.add(pricing);
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
        return pricingsList;
    }

    @Override
    public Pricing find(int id) {
        Pricing pricing = new Pricing();
        try {
            String query = "SELECT * FROM " + PricingDTO.getPRICING_DB() + " ORDER BY " + PricingDTO.getID() + " DESC LIMIT 1";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                pricing.setId(rs.getInt(PricingDTO.getID()));
                pricing.setCurrent_price(rs.getDouble(PricingDTO.getCURRENT_PRICE()));
                pricing.setOld_price(rs.getDouble(PricingDTO.getOLD_PRICE()));
                pricing.setTop_divide_value(rs.getDouble(PricingDTO.getTOP_DIVIDE_VALUE()));
                pricing.setDensity_minus_value(rs.getDouble(PricingDTO.getDENSITY_MINUS_VALUE()));
                pricing.setDensity_multiply_value(rs.getDouble(PricingDTO.getDENSITY_MULTIPLY_VALUE()));
                pricing.setKarat_divide_value(rs.getDouble(PricingDTO.getKARAT_DIVIDE_VALUE()));
                pricing.setCreated_date(rs.getString(PricingDTO.getCREATED_DATE()));
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
        return pricing;
    }

    @Override
    public int save(Pricing pricing) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + PricingDTO.getPRICING_DB() + " ("
                    + PricingDTO.getCURRENT_PRICE() + ","
                    + PricingDTO.getOLD_PRICE() + ","
                    + PricingDTO.getTOP_DIVIDE_VALUE() + ","
                    + PricingDTO.getDENSITY_MINUS_VALUE() + ","
                    + PricingDTO.getDENSITY_MULTIPLY_VALUE() + ","
                    + PricingDTO.getKARAT_DIVIDE_VALUE() + ","
                    + PricingDTO.getCREATED_DATE() + " ) VALUES (?,?,?,?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setDouble(1, pricing.getCurrent_price());
            pst.setDouble(2, pricing.getOld_price());
            pst.setDouble(3, pricing.getTop_divide_value());
            pst.setDouble(4, pricing.getDensity_minus_value());
            pst.setDouble(5, pricing.getDensity_multiply_value());
            pst.setDouble(6, pricing.getKarat_divide_value());
            pst.setString(7, pricing.getCreated_date());

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
    public void update(Pricing pricing, int id) {
        try {
            String query = "UPDATE " + PricingDTO.getPRICING_DB() + " SET "
                    + PricingDTO.getCURRENT_PRICE() + "='" + pricing.getCurrent_price() + "',"
                    + PricingDTO.getOLD_PRICE() + "='" + pricing.getOld_price() + "',"
                    + PricingDTO.getTOP_DIVIDE_VALUE() + "='" + pricing.getTop_divide_value() + "',"
                    + PricingDTO.getDENSITY_MINUS_VALUE() + "='" + pricing.getDensity_multiply_value() + "',"
                    + PricingDTO.getDENSITY_MULTIPLY_VALUE() + "='" + pricing.getDensity_multiply_value() + "',"
                    + PricingDTO.getKARAT_DIVIDE_VALUE() + "='" + pricing.getKarat_divide_value() + "',"
                    + PricingDTO.getCREATED_DATE() + "='" + pricing.getCreated_date() + "' WHERE " + PricingDTO.getID() + "='" + id + "'";

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
            String query = "DELETE FROM " + PricingDTO.getPRICING_DB() + " WHERE " + PricingDTO.getID() + "='" + id + "'";
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
            String query = "DELETE FROM " + PricingDTO.getPRICING_DB();
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
            String query = "SELECT COUNT(" + PricingDTO.getID() + ") AS total FROM " + PricingDTO.getPRICING_DB();
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
