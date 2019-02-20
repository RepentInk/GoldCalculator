package Queries;

import Classess.User;
import Interface.UserInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UserQuery implements UserInterface {

    Connect mets = new Connect();

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public UserQuery() {
        conn = Connect.ConnecrDb();
    }

    @Override
    public void save(User user) {
        try {
            String sql = "INSERT INTO user (username,fullname,password,created_at) VALUES (?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getFullname());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getCreated_at());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "User is saved");
        } catch (Exception e) {
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
    public void update(User user, int id) {
        try {
            String sql = "UPDATE user SET username='" + user.getName() + "', fullname='" + user.getFullname() + "', password='" + user.getPassword() + "' WHERE id='" + id + "'";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "User with id " + id + " is updated");
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
            String sql = "DELETE FROM user WHERE id='" + id + "'";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "User is deleted");
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
    public List<User> findOne(String id) {
        List<User> userList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user WHERE username = '" + id + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setPassword(rs.getString("password"));
                user.setCreated_at(rs.getString("created_at"));
                userList.add(user);
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
        return userList;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setPassword(rs.getString("password"));
                user.setCreated_at(rs.getString("created_at"));
                userList.add(user);
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
        return userList;
    }

    @Override
    public boolean userLogin(String username, String password) {
        boolean exit = false;
        try {
            String sql = "SELECT username,password FROM user WHERE username='" + username + "' and password='" + password + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                exit = true;
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
        return exit;
    }

    @Override
    public int userID(String username, String password) {
        int id = 0;
        try {
            String sql = "SELECT id FROM user WHERE username = '" + username.toLowerCase() + "' and password = '" + password.toLowerCase() + "'";
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
    public boolean userExit(String username, String password) {
        boolean exit = false;
        try {
            String sql = "SELECT username,password FROM user WHERE username = '" + username.toLowerCase() + "' or password = '" + password.toLowerCase() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                exit = true;
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
        return exit;
    }

    public String returnName(int id) {
        String name = "";
        try {
            String sql = "SELECT fullname FROM user WHERE id='" + id + "'";
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
    public void saveCustomer(JTextField name, JTextField fulname, JPasswordField password, JPasswordField cpass) {
        if (name.getText().isEmpty() || fulname.getText().isEmpty() || password.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No field can be empty");
        } else {
            String username = name.getText().toLowerCase().trim();
            String fname = fulname.getText().toLowerCase().trim();
            String pass = password.getText().toLowerCase().trim();
            String conpass = cpass.getText().toLowerCase().trim();

            DateFormat dateFormat = new SimpleDateFormat("MMMM EE dd, yyyy");
            Calendar cal = Calendar.getInstance();
            String date = dateFormat.format(cal.getTime());

            if (pass.compareToIgnoreCase(conpass) == 0) {
                if (userExit(username, pass) == true) {
                    JOptionPane.showMessageDialog(null, "Same username already exist");
                } else {
                    User u = new User(username, fname, pass, date);
                    save(u);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Confirm Password");
            }
        }
    }

    public void setUserToTable(JTable table) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        tmodel.setRowCount(0);

        Object[] object;

        List<User> user = findAll();

        if (user.size() <= 0) {
            JOptionPane.showMessageDialog(null, "No User is Added", "NO USER", JOptionPane.INFORMATION_MESSAGE);
        }

        int id = 0;
        for (User use : user) {
            String username = use.getName();
            String fullname = use.getFullname();
            String date = use.getCreated_at();
            id++;
            object = new Object[]{id, username, fullname, date};
            tmodel.addRow(object);
        }
    }

    public void deleteUser(JLabel lbl_id) {
        int id = Integer.parseInt(lbl_id.getText());
        delete(id);
    }

    public void updateCustomer(JLabel lbl_id, JTextField name, JTextField fulname, JPasswordField password) {
        if (name.getText().isEmpty() || fulname.getText().isEmpty() || password.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No field can be empty");
        } else {
            int id = Integer.parseInt(lbl_id.getText());
            String username = name.getText().toLowerCase().trim();
            String fname = fulname.getText().toLowerCase().trim();
            String pass = password.getText().toLowerCase().trim();

            DateFormat dateFormat = new SimpleDateFormat("MMMM EE dd, yyyy");
            Calendar cal = Calendar.getInstance();
            String date = dateFormat.format(cal.getTime());

            User u = new User(username, fname, pass, date);
            update(u, id);

        }
    }

    public int getUserID(JTextField username, JPasswordField password) {
        int id = 0;
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
        } else {
            String uname = username.getText().toLowerCase().trim();
            String pass = password.getText().toLowerCase().trim();
            id = userID(uname, pass);
        }
        return id;
    }

    public void userLogin(JTextField username, JPasswordField password, JFrame one, JFrame two) {
        String uname = username.getText();
        String pass = password.getText();

        if (userLogin(uname.toLowerCase(), pass.toLowerCase()) == true) {
            two.setVisible(true);
            one.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Sorry! Credentials cannot be found");
        }
    }

    public void tableClick(JTable table, JLabel id, JTextField uname, JTextField fname, JPasswordField pass, JTextField date) {
        try {
            int row = table.getSelectedRow();
            String idd = table.getModel().getValueAt(row, 1).toString();

            List<User> user = findOne(idd);

            for (User cus : user) {
                id.setText("" + cus.getId());
                uname.setText(cus.getName());
                fname.setText(cus.getFullname());
                pass.setText(cus.getPassword());
                date.setText(cus.getCreated_at());
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
