package Controllers;

import Helpers.HelperFunctions;
import Repository.CustomerRepository;
import Repository.UserRepository;
import javax.swing.JTextField;

/**
 *
 * @author nyark
 */
public class DashboardController {

    CustomerRepository customerRepository = new CustomerRepository();
    UserRepository userRepository = new UserRepository();

    HelperFunctions helper = new HelperFunctions();

    public void dashboardCount(
            JTextField customers,
            JTextField users
    ) {
        customers.setText(String.valueOf(customerRepository.count()));
        users.setText(String.valueOf(userRepository.count()));
    }

}
