package Repository;

import Helpers.connectDB;
import Interfaces.AnonymousInterface;
import ModelDTO.UserDTO;
import Models.User;
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
public class UserRepository implements AnonymousInterface<User> {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public UserRepository() {
        conn = connectDB.ConnecrDb();
    }

    @Override
    public List<User> list() {
        List<User> usersList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + UserDTO.getUSERS_DB() + " WHERE user_type= '" + "user" + "' ORDER BY id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt(UserDTO.getID()));
                user.setFullname(rs.getString(UserDTO.getFULL_NAME()));
                user.setPhone_number(rs.getString(UserDTO.getPHONE_NUMBER()));
                user.setUsername(rs.getString(UserDTO.getUSERNAME()));
                user.setPassword(rs.getString(UserDTO.getPASSWORD()));
                user.setCreated_at(rs.getString(UserDTO.getCREATED_AT()));
                user.setUpdated_at(rs.getString(UserDTO.getUPDATED_AT()));

                usersList.add(user);
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
        return usersList;
    }

    @Override
    public List<User> list(int year) {
        List<User> usersList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + UserDTO.getUSERS_DB() + " user WHERE strftime('%Y',cus.created_at) = '" + year + "' ORDER BY user.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt(UserDTO.getID()));
                user.setFullname(rs.getString(UserDTO.getFULL_NAME()));
                user.setPhone_number(rs.getString(UserDTO.getPHONE_NUMBER()));
                user.setUsername(rs.getString(UserDTO.getUSERNAME()));
                user.setPassword(rs.getString(UserDTO.getPASSWORD()));
                user.setCreated_at(rs.getString(UserDTO.getCREATED_AT()));
                user.setUpdated_at(rs.getString(UserDTO.getUPDATED_AT()));

                usersList.add(user);
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
        return usersList;
    }

    @Override
    public User find(int id) {
        User user = new User();
        try {
            String query = "SELECT * FROM " + UserDTO.getUSERS_DB() + " user WHERE user.id= '" + id + "' ORDER BY user.id DESC";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt(UserDTO.getID()));
                user.setFullname(rs.getString(UserDTO.getFULL_NAME()));
                user.setPhone_number(rs.getString(UserDTO.getPHONE_NUMBER()));
                user.setUsername(rs.getString(UserDTO.getUSERNAME()));
                user.setPassword(rs.getString(UserDTO.getPASSWORD()));
                user.setCreated_at(rs.getString(UserDTO.getCREATED_AT()));
                user.setUpdated_at(rs.getString(UserDTO.getUPDATED_AT()));
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

        return user;
    }

    @Override
    public int save(User user) {
        int last_inserted_id = 0;
        try {

            String query = "INSERT INTO " + UserDTO.getUSERS_DB() + " ("
                    + UserDTO.getFULL_NAME() + ","
                    + UserDTO.getPHONE_NUMBER() + ","
                    + UserDTO.getUSERNAME() + ","
                    + UserDTO.getPASSWORD() + ","
                    + UserDTO.getCREATED_AT() + ","
                    + UserDTO.getUPDATED_AT() + " ) VALUES (?,?,?,?,?,?)";
            pst = conn.prepareStatement(query);

            pst.setString(1, user.getFullname());
            pst.setString(2, user.getPhone_number());
            pst.setString(3, user.getUsername());
            pst.setString(4, user.getPassword());
            pst.setString(5, user.getCreated_at());
            pst.setString(6, user.getUpdated_at());

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
    public void update(User user, int id) {
        try {
            String query = "UPDATE " + UserDTO.getUSERS_DB() + " SET "
                    + UserDTO.getFULL_NAME() + "='" + user.getFullname() + "',"
                    + UserDTO.getPHONE_NUMBER() + "='" + user.getPhone_number() + "',"
                    + UserDTO.getUSERNAME() + "='" + user.getUsername() + "',"
                    + UserDTO.getPASSWORD() + "='" + user.getPassword() + "',"
                    + UserDTO.getCREATED_AT() + "='" + user.getCreated_at() + "',"
                    + UserDTO.getUPDATED_AT() + "='" + user.getUpdated_at() + "' WHERE " + UserDTO.getID() + "='" + id + "'";

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
            String query = "DELETE FROM " + UserDTO.getUSERS_DB() + " WHERE " + UserDTO.getID() + "='" + id + "'";
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
            String query = "DELETE FROM " + UserDTO.getUSERS_DB();
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
            String query = "SELECT COUNT(" + UserDTO.getID() + ") AS total FROM " + UserDTO.getUSERS_DB();
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

    public User login(User account) {
        User authUser = new User();
        try {
            String query = "SELECT * FROM " + UserDTO.getUSERS_DB() + " WHERE " + UserDTO.getUSERNAME() + "=? AND " + UserDTO.getPASSWORD() + "=?";
            pst = conn.prepareStatement(query);
            pst.setString(1, account.getUsername());
            pst.setString(2, account.getPassword());
            rs = pst.executeQuery();

            if (rs.next()) {
                authUser.setId(rs.getInt(UserDTO.getID()));
                authUser.setFullname(rs.getString(UserDTO.getFULL_NAME()));
                authUser.setUsername(rs.getString(UserDTO.getUSERNAME()));
                authUser.setPhone_number(rs.getString(UserDTO.getPHONE_NUMBER()));
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

        return authUser;
    }

}
