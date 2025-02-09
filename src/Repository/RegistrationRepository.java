package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.RegistrationDTO;
import Models.Registration;
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
public class RegistrationRepository implements AnonymousInterface<Registration> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public RegistrationRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public int save(Registration registration) {
        int last_inserted_id = 0;
        try {
            String query = "INSERT INTO " + RegistrationDTO.getREGISTARTION_DB() + " ("
                    + RegistrationDTO.getCODE() + ","
                    + RegistrationDTO.getSTART_DATE() + ","
                    + RegistrationDTO.getEND_DATE() + ","
                    + RegistrationDTO.getSTATUS() + ") VALUES (?,?,?,?)";

            pst = conn.prepareStatement(query);

            pst.setString(1, registration.getCode());
            pst.setString(2, registration.getStart_date());
            pst.setString(3, registration.getEnd_date());
            pst.setBoolean(4, registration.isStatus());

            pst.execute();

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
    public void update(Registration registration, int id) {
        try {
            String query = "UPDATE " + RegistrationDTO.getREGISTARTION_DB() + " SET "
                    + RegistrationDTO.getCODE() + "='" + registration.getCode() + "',"
                    + RegistrationDTO.getSTART_DATE() + "='" + registration.getStart_date() + "',"
                    + RegistrationDTO.getEND_DATE() + "='" + registration.getEnd_date() + "',"
                    + RegistrationDTO.getSTATUS() + "='" + (registration.isStatus() ? 1 : 0) + "' WHERE " + RegistrationDTO.getID() + "='" + id + "'";

            pst = conn.prepareStatement(query);
            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM " + RegistrationDTO.getREGISTARTION_DB() + " WHERE " + RegistrationDTO.getID() + "='" + id + "'";
            pst = conn.prepareStatement(query);
            pst.executeUpdate();

            conn.setAutoCommit(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public Registration registrationDetails(int status) {
        Registration registration = new Registration();
        try {
            String query = "SELECT * FROM " + RegistrationDTO.getREGISTARTION_DB() + " WHERE " + RegistrationDTO.getSTATUS() + "='" + status + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                registration.setId(rs.getInt(RegistrationDTO.getID()));
                registration.setCode(rs.getString(RegistrationDTO.getCODE()));
                registration.setStart_date(rs.getString(RegistrationDTO.getSTART_DATE()));
                registration.setEnd_date(rs.getString(RegistrationDTO.getEND_DATE()));
                registration.setStatus(rs.getBoolean(RegistrationDTO.getSTATUS()));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {
            }
        }

        return registration;
    }

    @Override
    public List<Registration> list(String year) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Registration> list() {
        List<Registration> listRegistration = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + RegistrationDTO.getREGISTARTION_DB() + " ORDER BY " + RegistrationDTO.getID() + " DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Registration registration = new Registration();

                registration.setId(rs.getInt(RegistrationDTO.getID()));
                registration.setCode(rs.getString(RegistrationDTO.getCODE()));
                registration.setStart_date(rs.getString(RegistrationDTO.getSTART_DATE()));
                registration.setEnd_date(rs.getString(RegistrationDTO.getEND_DATE()));
                registration.setStatus(rs.getBoolean(RegistrationDTO.getSTATUS()));

                listRegistration.add(registration);
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

        return listRegistration;
    }

    @Override
    public Registration find(int id) {
        Registration registration = new Registration();
        try {
            String query = "SELECT * FROM " + RegistrationDTO.getREGISTARTION_DB() + " ORDER BY " + RegistrationDTO.getID() + " DESC LIMIT 1";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                registration.setId(rs.getInt(RegistrationDTO.getID()));
                registration.setCode(rs.getString(RegistrationDTO.getCODE()));
                registration.setStart_date(rs.getString(RegistrationDTO.getSTART_DATE()));
                registration.setEnd_date(rs.getString(RegistrationDTO.getEND_DATE()));
                registration.setStatus(rs.getBoolean(RegistrationDTO.getSTATUS()));
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

        return registration;
    }

    @Override
    public void clear() {
        try {
            String query = "DELETE FROM " + RegistrationDTO.getREGISTARTION_DB();
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
            String query = "SELECT COUNT(" + RegistrationDTO.getID() + ") AS total FROM " + RegistrationDTO.getREGISTARTION_DB();
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
