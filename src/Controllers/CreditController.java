package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Helpers.TableActions;
import ModelDTO.CreditDTO;
import Models.Credit;
import Models.Customer;
import Repository.AnonymousRepository;
import Repository.CreditPaymentRepository;
import Repository.CreditRepository;
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
public class CreditController {

    HelperFunctions helper = new HelperFunctions();
    CreditRepository creditRepository = new CreditRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    CreditPaymentRepository creditPaymentRepository = new CreditPaymentRepository();
    AnonymousRepository anonymousRepository = new AnonymousRepository();

    public void populateTable(JTable table, String startDate, String endDate) {
        if (endDate.equals("")) {
            endDate = helper.returnDate();
        }

        List<Credit> credits;
        if (!startDate.isEmpty() && !endDate.isEmpty()) {
            credits = creditRepository.findCreditBetweenDates(startDate, endDate);
        } else {
            credits = creditRepository.list(endDate);
        }

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Credit credit : credits) {

            double totalPayment = this.getTotalAmountPaid(credit.getId());
            double balance = credit.getTotal_amount() - totalPayment;

            object = new Object[]{
                credit.getId(),
                credit.getCode(),
                credit.getCustomer(),
                helper.priceToString(credit.getTotal_amount()),
                helper.priceToString(totalPayment),
                helper.priceToString(balance),
                this.statusOfCredit(credit.isStatus()),
                credit.getCreated_time(),
                credit.getCreated_date(),
                TableActions.View.toString(),
                TableActions.Payment.toString()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    public void saveUpdate(
            JLabel creditID,
            JLabel batchCode,
            JComboBox customer,
            JTextField totalAmount,
            JTable table,
            int selectedRow,
            javax.swing.JDialog dialog
    ) {

        int customer_id = 0;
        int credit_id = 0;
        String code = batchCode.getText();
        String selected_customer = customer.getSelectedItem().toString();
        double total_amount = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(totalAmount.getText());
        String created_date = helper.returnDate();
        String raw_date = helper.returnDate();
        String created_time = helper.returnTime();

        if (!selected_customer.isEmpty()) {
            String fullName = helper.splitWord(selected_customer, 0, "|");
            String phoneNumber = helper.splitWord(selected_customer, 1, "|");
            Customer customerInfo = customerRepository.findByName(fullName.trim(), phoneNumber.trim());
            customer_id = customerInfo.getId();
        }

        if (!creditID.getText().isEmpty()) {
            credit_id = Integer.parseInt(creditID.getText());
        }

        if (credit_id > 0) {

            Credit credit = new Credit(
                    credit_id,
                    customer_id,
                    total_amount,
                    Authuser.getId(),
                    created_date,
                    created_time,
                    raw_date
            );

            creditRepository.update(credit, credit_id);

            this.populateAfterUpdating(table, selectedRow, credit_id);

            dialog.setVisible(false);

        } else {
            Credit credit = new Credit(
                    code,
                    customer_id,
                    total_amount,
                    Authuser.getId(),
                    created_date,
                    created_time,
                    raw_date
            );

            int last_insert_id = creditRepository.save(credit);

            this.populateAfterSaving(table, last_insert_id);

            dialog.setVisible(false);
        }

    }

    private void populateAfterSaving(JTable table, int credit_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;

        Credit credit = creditRepository.find(credit_id);

        double totalPayment = this.getTotalAmountPaid(credit.getId());
        double balance = credit.getTotal_amount() - totalPayment;

        object = new Object[]{
            credit.getId(),
            credit.getCode(),
            credit.getCustomer(),
            helper.priceToString(credit.getTotal_amount()),
            helper.priceToString(totalPayment),
            helper.priceToString(balance),
            this.statusOfCredit(credit.isStatus()),
            credit.getCreated_time(),
            credit.getCreated_date(),
            TableActions.View.toString(),
            TableActions.History.toString(),
            TableActions.Close.toString()
        };

        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int credit_id) {

        Credit credit = creditRepository.find(credit_id);

        double totalPayment = this.getTotalAmountPaid(credit.getId());
        double balance = credit.getTotal_amount() - totalPayment;

        table.setValueAt(credit.getId(), selectedRow, 0);
        table.setValueAt(credit.getCode(), selectedRow, 1);
        table.setValueAt(credit.getCustomer(), selectedRow, 2);
        table.setValueAt(helper.priceToString(credit.getTotal_amount()), selectedRow, 3);
        table.setValueAt(helper.priceToString(totalPayment), selectedRow, 4);
        table.setValueAt(helper.priceToString(balance), selectedRow, 5);
        table.setValueAt(this.statusOfCredit(credit.isStatus()), selectedRow, 6);
        table.setValueAt(credit.getCreated_time(), selectedRow, 7);
        table.setValueAt(credit.getCreated_date(), selectedRow, 8);
    }

    public void deleteItem(JTable table, String rowID, int selectedRow) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(rowID);
        creditRepository.delete(id);
        tmodel.removeRow(selectedRow);
    }

    public void getCustomerPreviousBalance(JComboBox selected_customer, JTextField previousAmount) {
        String customer = selected_customer.getSelectedItem().toString();
        String fullName = helper.splitWord(customer, 0, "|");
        String phoneNumber = helper.splitWord(customer, 1, "|");
        Customer customerInfo = customerRepository.findByName(fullName.trim(), phoneNumber.trim());

        double amount_paid = 0;
        double credit_amount = creditRepository.summationOfCredit(customerInfo.getId());
        double balance_payable = credit_amount - amount_paid;

        previousAmount.setText(helper.priceToString(balance_payable));
    }

    public String generateCode() {
        String code = anonymousRepository.generateCode(
                CreditDTO.getCREDIT_DB(),
                CreditDTO.getCODE(),
                helper.returnCurrentYearTwoDigit()
        );
        return code;
    }

    public double getTotalAmountPaid(int credit_id) {
        double amount_paid = creditPaymentRepository.summationAmountPaid(credit_id);
        return amount_paid;
    }

    public void onTableClicked(
            int credit_id,
            JLabel creditID,
            JLabel batchCode,
            JComboBox customer,
            JTextField totalAmount
    ) {

        Credit credit = creditRepository.find(credit_id);
        Customer customerInfo = customerRepository.find(credit.getCustomer_id());

        creditID.setText(String.valueOf(credit.getId()));
        batchCode.setText(credit.getCode());
        customer.setSelectedItem(customerInfo.getFullname() + " | " + customerInfo.getPhone_number());
        totalAmount.setText(helper.priceToString(credit.getTotal_amount()));
    }

    private String statusOfCredit(boolean status) {
        return status ? "Closed" : "Opened";
    }

    public void changeStatus(
            int credit_id,
            int status,
            JTable table,
            int selectedRow
    ) {
        creditRepository.updateStatus(credit_id, status);
        this.populateAfterUpdating(table, selectedRow, credit_id);
    }

    public Credit getSingleCredit(int credit_id) {
        Credit credit = creditRepository.find(credit_id);
        return credit;
    }

}
