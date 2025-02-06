package Controllers;

import Helpers.HelperFunctions;
import Helpers.TableActions;
import Models.Customer;
import Repository.CustomerRepository;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nyark
 */
public class CustomerController {

    CustomerRepository customerRepository = new CustomerRepository();
    HelperFunctions helper = new HelperFunctions();

    public void populateTable(JTable table) {
        List<Customer> customers = customerRepository.list();

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Customer customer : customers) {
            object = new Object[]{
                customer.getId(),
                customer.getFullname(),
                customer.getPhone_number(),
                customer.getEmergency_number(),
                customer.getPlace_of_work(),
                customer.getCreated_at(),
                TableActions.View.toString(),
                TableActions.Delete.toString()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    public void saveUpdate(
            JLabel customerID,
            JTextField fullname,
            JTextField phoneNumber,
            JTextField emergencyNumber,
            JTextField placeOfStay,
            JTable table,
            int selectedRow,
            javax.swing.JDialog dialog
    ) {

        int customer_id = 0;
        String fullName = fullname.getText().trim();
        String phone_number = phoneNumber.getText().trim();
        String emergency_number = emergencyNumber.getText().trim();
        String place_of_sale = placeOfStay.getText().trim();
        String created_date = helper.returnDate();

        if (!customerID.getText().equals("")) {
            customer_id = Integer.parseInt(customerID.getText());
        }

        if (customer_id > 0) {

            Customer customer = new Customer(
                    customer_id,
                    fullName,
                    phone_number,
                    place_of_sale,
                    emergency_number,
                    created_date,
                    created_date
            );

            customerRepository.update(customer, customer_id);

            this.populateAfterUpdating(table, selectedRow, customer_id);

            dialog.setVisible(false);

        } else {

            Customer customer = new Customer(
                    fullName,
                    phone_number,
                    place_of_sale,
                    emergency_number,
                    created_date,
                    created_date
            );

            int last_insert_id = customerRepository.save(customer);

            this.populateAfterSaving(table, last_insert_id);

            dialog.setVisible(false);
        }

    }

    private void populateAfterSaving(JTable table, int customer_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;

        Customer customer = customerRepository.find(customer_id);

        object = new Object[]{
            customer.getId(),
            customer.getFullname(),
            customer.getPhone_number(),
            customer.getEmergency_number(),
            customer.getPlace_of_work(),
            customer.getCreated_at(),
            TableActions.View.toString(),
            TableActions.Delete.toString()
        };

        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int customer_id) {

        Customer customer = customerRepository.find(customer_id);

        table.setValueAt(customer.getId(), selectedRow, 0);
        table.setValueAt(customer.getFullname(), selectedRow, 1);
        table.setValueAt(customer.getPhone_number(), selectedRow, 2);
        table.setValueAt(customer.getEmergency_number(), selectedRow, 3);
        table.setValueAt(customer.getPlace_of_work(), selectedRow, 4);
        table.setValueAt(customer.getCreated_at(), selectedRow, 5);
    }

    public void deleteItem(JTable table, String customerID, int selectedRow) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(customerID);
        customerRepository.delete(id);
        tmodel.removeRow(selectedRow);
    }

    public void userTableClick(
            int customer_id,
            JLabel customerID,
            JTextField fullName,
            JTextField phoneNumber,
            JTextField emergencyNumber,
            JTextField placeOfStay
    ) {
        Customer customer = customerRepository.find(customer_id);

        customerID.setText(String.valueOf(customer.getId()));
        fullName.setText(customer.getFullname());
        phoneNumber.setText(customer.getPhone_number());
        emergencyNumber.setText(customer.getEmergency_number());
        placeOfStay.setText(customer.getPlace_of_work());
    }

    public void populateDropdownData(JComboBox comboBox, String title) {
        List<Customer> customers = customerRepository.list();
        comboBox.addItem(title);
        comboBox.setSelectedIndex(0);

        for (Customer customer : customers) {
            comboBox.addItem(customer.getFullname() + " | " + customer.getPhone_number());
        }
    }

}
