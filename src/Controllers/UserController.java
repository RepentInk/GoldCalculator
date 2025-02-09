package Controllers;

import Helpers.HelperFunctions;
import Helpers.TableActions;
import Helpers.UserType;
import Models.User;
import Repository.UserRepository;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nyark
 */
public class UserController {

    UserRepository userRepository = new UserRepository();
    HelperFunctions helper = new HelperFunctions();

    public void populateTable(JTable table) {
        List<User> users = userRepository.list();

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (User user : users) {
            object = new Object[]{
                user.getId(),
                user.getFullname(),
                user.getPhone_number(),
                user.getUsername(),
                user.getUser_type(),
                user.getCreated_at(),
                TableActions.View.toString(),
                TableActions.Delete.toString()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    public void saveUpdate(
            JLabel userID,
            JTextField fullname,
            JTextField phoneNumber,
            JTextField userName,
            JPasswordField password,
            JPasswordField confirmPassword,
            String userType,
            JTable table,
            int selectedRow,
            javax.swing.JDialog dialog
    ) {

        int user_id = 0;
        String user_password = password.getText().trim();
        String confirm_password = confirmPassword.getText().trim();
        String fullName = fullname.getText().trim();
        String username = userName.getText().trim();
        String phone_number = phoneNumber.getText().trim();
        String created_date = helper.returnDate();

        if (!user_password.equals(confirm_password)) {
            JOptionPane.showMessageDialog(null, "Please password don't match. Recheck");
            return;
        }

        if (!userID.getText().equals("")) {
            user_id = Integer.parseInt(userID.getText());
        }

        if (user_id > 0) {
            User user = userRepository.find(user_id);

            String rawPass = user_password.isEmpty() ? user.getPassword() : user_password;

            User data = new User(
                    user_id,
                    fullName,
                    phone_number,
                    username,
                    rawPass,
                    userType,
                    created_date,
                    created_date
            );

            userRepository.update(data, user_id);

            this.populateAfterUpdating(table, selectedRow, user_id);

            dialog.setVisible(false);

        } else {

            User user = new User(
                    fullName,
                    phone_number,
                    username,
                    user_password,
                    userType,
                    created_date,
                    created_date
            );

            int last_insert_id = userRepository.save(user);

            this.populateAfterSaving(table, last_insert_id);

            dialog.setVisible(false);
        }

    }

    private void populateAfterSaving(JTable table, int user_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;

        User user = userRepository.find(user_id);

        object = new Object[]{
            user.getId(),
            user.getFullname(),
            user.getPhone_number(),
            user.getUsername(),
            user.getUser_type(),
            user.getCreated_at(),
            TableActions.View.toString(),
            TableActions.Delete.toString()
        };

        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int user_id) {

        User user = userRepository.find(user_id);

        table.setValueAt(user.getId(), selectedRow, 0);
        table.setValueAt(user.getFullname(), selectedRow, 1);
        table.setValueAt(user.getPhone_number(), selectedRow, 2);
        table.setValueAt(user.getUsername(), selectedRow, 3);
        table.setValueAt(user.getUser_type(), selectedRow, 4);
        table.setValueAt(user.getCreated_at(), selectedRow, 5);
    }

    public void deleteItem(JTable table, String userID, int selectedRow) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(userID);
        userRepository.delete(id);
        tmodel.removeRow(selectedRow);
    }

    public void userTableClick(
            int user_id,
            JLabel userID,
            JTextField fullName,
            JTextField phoneNumber,
            JTextField userName,
            JPasswordField password,
            JPasswordField confirmPassword,
            JRadioButton adminUserType,
            JRadioButton userUserType
    ) {
        User user = userRepository.find(user_id);

        userID.setText(String.valueOf(user.getId()));
        fullName.setText(user.getFullname());
        phoneNumber.setText(user.getPhone_number());
        userName.setText(user.getUsername());
        password.setText(user.getPassword());
        confirmPassword.setText(user.getPassword());

        if (user.getUser_type().equals(UserType.ADMIN.toString())) {
            adminUserType.setSelected(true);
        } else {
            userUserType.setSelected(true);
        }
    }

    public void createAccount(
            JTextField firstName,
            JTextField lastName,
            JTextField phoneNumber,
            JTextField userName,
            JPasswordField password,
            JPasswordField confirmPassword
    ) {

        String user_password = password.getText().trim();
        String confirm_password = confirmPassword.getText().trim();
        String first_name = firstName.getText().trim();
        String last_name = lastName.getText().trim();
        String username = userName.getText().trim();
        String phone_number = phoneNumber.getText().trim();
        String created_date = helper.returnDate();

        if (!user_password.equals(confirm_password)) {
            JOptionPane.showMessageDialog(null, "Please password don't match. Recheck");
            return;
        }

        User user = new User(
                last_name + " " + first_name,
                phone_number,
                username,
                user_password,
                UserType.ADMIN.toString(),
                created_date,
                created_date
        );

        userRepository.save(user);

    }

}
