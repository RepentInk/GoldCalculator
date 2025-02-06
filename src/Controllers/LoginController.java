package Controllers;

import Main.Dashboard;
import Models.User;
import Repository.UserRepository;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author nyark
 */
public class LoginController {

    UserRepository userRepository = new UserRepository();
    SetStaticDataController setStaticDataController = new SetStaticDataController();

    public void loginUser(JTextField userName, JPasswordField password, JFrame frame) {
        String user_name = userName.getText().trim();
        String password_user = password.getText().trim();

        User user = new User(user_name, password_user);
        User authUser = userRepository.login(user);

        if (authUser.getId() > 0) {
            setStaticDataController.setAuthUser(authUser);
            setStaticDataController.setPricing();
            setStaticDataController.setShopDetail();

            Dashboard dashboard = new Dashboard();
            dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
            dashboard.setVisible(true);

            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Account does not exist");
        }
    }
}
