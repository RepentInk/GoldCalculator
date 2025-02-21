package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Helpers.TableActions;
import ModelDTO.CreditDTO;
import Models.Budget;
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
    BudgetController budgetController = new BudgetController();
    AnonymousRepository anonymousRepository = new AnonymousRepository();
    ReportController reportController = new ReportController();

    public void populateTable(JTable table, String createdDate) {
        if (createdDate.isEmpty()) {
            createdDate = helper.returnDate();
        }

        List<Credit> credits = creditRepository.list(createdDate);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Credit credit : credits) {

            object = new Object[]{
                credit.getId(),
                credit.getCode(),
                credit.getCustomer(),
                credit.getBudget(),
                helper.priceToString(credit.getAmount()),
                helper.priceToString(this.getCreditPayments(credit.getId())),
                helper.priceToString(credit.getAmount() - this.getCreditPayments(credit.getId())),
                credit.getUser(),
                credit.getCreated_time(),
                credit.getCreated_date(),
                TableActions.View.toString(),
                TableActions.History.toString()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    public void saveUpdate(
            JLabel creditID,
            JLabel batchCode,
            JComboBox customer,
            JComboBox budgetSelected,
            JTextField totalBudgetBefore,
            JTextField totalBudgetAfter,
            JTextField totalAmount,
            JTextField previousAmount,
            JTable table,
            int selectedRow,
            javax.swing.JDialog dialog
    ) {

        int customer_id = 0;
        int credit_id = 0;
        String code = batchCode.getText();
        String selected_customer = customer.getSelectedItem().toString();
        double budget_before = totalBudgetBefore.getText().isEmpty() ? 0 : helper.parseAmountWithComma(totalBudgetBefore.getText());
        double budget_after = totalBudgetAfter.getText().isEmpty() ? 0 : helper.parseAmountWithComma(totalBudgetAfter.getText());
        double total_amount = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(totalAmount.getText());
        double previous_amount = previousAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(previousAmount.getText());
        String created_date = helper.returnDate();
        String raw_date = helper.returnDate();
        String created_time = helper.returnTime();

        Budget budget = budgetController.getSingleBudget(budgetSelected.getSelectedItem().toString());

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
                    budget.getId(),
                    total_amount,
                    previous_amount,
                    budget_before,
                    budget_after,
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
                    budget.getId(),
                    total_amount,
                    previous_amount,
                    budget_before,
                    budget_after,
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

        object = new Object[]{
            credit.getId(),
            credit.getCode(),
            credit.getCustomer(),
            credit.getBudget(),
            helper.priceToString(credit.getAmount()),
            helper.priceToString(this.getCreditPayments(credit.getId())),
            helper.priceToString(credit.getAmount() - this.getCreditPayments(credit.getId())),
            credit.getUser(),
            credit.getCreated_time(),
            credit.getCreated_date(),
            TableActions.View.toString(),
            TableActions.History.toString()
        };

        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int credit_id) {

        Credit credit = creditRepository.find(credit_id);

        table.setValueAt(credit.getId(), selectedRow, 0);
        table.setValueAt(credit.getCode(), selectedRow, 1);
        table.setValueAt(credit.getCustomer(), selectedRow, 2);
        table.setValueAt(credit.getBudget(), selectedRow, 3);
        table.setValueAt(helper.priceToString(credit.getAmount()), selectedRow, 4);
        table.setValueAt(helper.priceToString(this.getCreditPayments(credit.getId())), selectedRow, 5);
        table.setValueAt(helper.priceToString(credit.getAmount() - this.getCreditPayments(credit.getId())), selectedRow, 6);
        table.setValueAt(credit.getUser(), selectedRow, 7);
        table.setValueAt(credit.getCreated_time(), selectedRow, 8);
        table.setValueAt(credit.getCreated_date(), selectedRow, 9);
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

        double amount_paid = creditPaymentRepository.summationOfAmountPaid(customerInfo.getId());
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

    private double getCreditPayments(int credit_id) {
        double totalAmount = creditPaymentRepository.summationAmountPaid(credit_id);
        return totalAmount;
    }

    public void onTableClicked(
            int credit_id,
            JLabel creditID,
            JLabel batchCode,
            JComboBox customer,
            JComboBox budgetSelected,
            JTextField totalBudgetBefore,
            JTextField totalBudgetAfter,
            JTextField totalAmount,
            JTextField previousAmount,
            JTextField amountPaid
    ) {

        Credit credit = creditRepository.find(credit_id);
        Customer customerInfo = customerRepository.find(credit.getCustomer_id());
        Budget budget = budgetController.getSingleBudgetWithID(credit.getBudget_id());

        creditID.setText(String.valueOf(credit.getId()));
        batchCode.setText(credit.getCode());
        customer.setSelectedItem(customerInfo.getFullname() + " | " + customerInfo.getPhone_number());
        budgetSelected.setSelectedItem(budget.getId() + " | " + budget.getName());
        totalBudgetBefore.setText(helper.priceToString(credit.getBudget_before()));
        totalBudgetAfter.setText(helper.priceToString(credit.getBudget_after()));
        totalAmount.setText(helper.priceToString(credit.getAmount()));
        previousAmount.setText(helper.priceToString(credit.getPrevious_balance()));
        amountPaid.setText(helper.priceToString(credit.getAmount() - credit.getPrevious_balance()));
    }

}
