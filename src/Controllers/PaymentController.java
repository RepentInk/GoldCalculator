package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Helpers.TableActions;
import Models.Budget;
import Models.BuyGold;
import Models.Payments;
import Models.Receipt;
import Repository.PaymentsRepository;
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
public class PaymentController {

    BuyGoldController buyGoldController = new BuyGoldController();
    BudgetController budgetController = new BudgetController();
    HelperFunctions helper = new HelperFunctions();
    PaymentsRepository paymentsRepository = new PaymentsRepository();

    public void populateTable(JTable table, String year) {
        if (year.equals("")) {
            year = helper.returnCurrentYear();
        }
        List<Payments> payments = paymentsRepository.list(year);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Payments payment : payments) {
            object = new Object[]{
                payment.getId(),
                payment.getBuy_gold(),
                payment.getCustomer(),
                payment.getBudget(),
                helper.priceToString(payment.getAmount_paid()),
                helper.priceToString(payment.getBalance()),
                helper.priceToString(payment.getBudget_before_payment()),
                helper.priceToString(payment.getBudget_after_payment()),
                payment.getUser(),
                payment.getCreated_time(),
                payment.getCreated_date(),
                TableActions.View.toString(),
                TableActions.Print.toString()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    public void saveUpdate(
            JLabel paymentID,
            JComboBox buyGoldSelected,
            JTextField amountPaying,
            JTextField balance,
            JComboBox budgetSelected,
            JTextField budgetBeforePayment,
            JTextField budgetAfterPayment,
            JTable table,
            int selectedRow,
            javax.swing.JDialog dialog
    ) {

        Budget budget = budgetController.getSingleBudget(budgetSelected.getSelectedItem().toString());
        BuyGold buyGold = buyGoldController.getSingleData(buyGoldSelected.getSelectedItem().toString());

        int payment_id = 0;
        double amount_paying = amountPaying.getText().isEmpty() ? 0 : helper.parseAmountWithComma(amountPaying.getText());
        double balance_remain = balance.getText().isEmpty() ? 0 : helper.parseAmountWithComma(balance.getText());
        double budget_before_payement = budgetBeforePayment.getText().isEmpty() ? 0 : helper.parseAmountWithComma(budgetBeforePayment.getText());
        double budget_after_payment = budgetAfterPayment.getText().isEmpty() ? 0 : helper.parseAmountWithComma(budgetAfterPayment.getText());
        String created_date = helper.returnDate();
        String raw_date = helper.returnDate();
        String created_time = helper.returnTime();

        if (!paymentID.getText().isEmpty()) {
            payment_id = Integer.parseInt(paymentID.getText());
        }

        if (payment_id > 0) {

            Payments payments = new Payments(
                    payment_id,
                    buyGold.getId(),
                    budget.getId(),
                    amount_paying,
                    balance_remain,
                    budget_before_payement,
                    budget_after_payment,
                    Authuser.getId(),
                    created_date,
                    created_time,
                    raw_date
            );

            paymentsRepository.update(payments, payment_id);

            this.populateAfterUpdating(table, selectedRow, payment_id);

            dialog.setVisible(false);

        } else {

            Payments payments = new Payments(
                    buyGold.getId(),
                    budget.getId(),
                    amount_paying,
                    balance_remain,
                    budget_before_payement,
                    budget_after_payment,
                    Authuser.getId(),
                    created_date,
                    created_time,
                    raw_date
            );

            int last_insert_id = paymentsRepository.save(payments);

            this.populateAfterSaving(table, last_insert_id);

            dialog.setVisible(false);

        }
    }

    public void populateDropDownData(JComboBox cmdGoldPurchase, JComboBox cmbBudget, String year) {
        if (year.equals("")) {
            year = helper.returnCurrentYear();
        }

        buyGoldController.populateDropdownData(cmdGoldPurchase, "Select Purchase", year);
        budgetController.populateDropdownData(cmbBudget, "Select Budget", year);
    }

    public void setPurchaseDetails(
            String purchase,
            JTextField totalAmount,
            JTextField amountPaid,
            JTextField amountRemains
    ) {
        BuyGold buyGold = buyGoldController.getSingleData(purchase);
        double total_payment = paymentsRepository.summationOfPurchasePayment(buyGold.getId());
        totalAmount.setText(helper.priceToString(buyGold.getTotal_amount()));
        amountPaid.setText(helper.priceToString(total_payment));
        amountRemains.setText(helper.priceToString(buyGold.getTotal_amount() - total_payment));
    }

    public void setBudgetDetails(
            String budget_selected,
            JTextField amountBeforePayment
    ) {
        Budget budget = budgetController.getSingleBudget(budget_selected);
        double total_budget_used = paymentsRepository.summationOfBudget(budget.getId());
        amountBeforePayment.setText(helper.priceToString(budget.getTotal_amount() - total_budget_used));
    }

    private void populateAfterSaving(JTable table, int payment_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;

        Payments payment = paymentsRepository.find(payment_id);

        object = new Object[]{
            payment.getId(),
            payment.getBuy_gold(),
            payment.getCustomer(),
            payment.getBudget(),
            helper.priceToString(payment.getAmount_paid()),
            helper.priceToString(payment.getBalance()),
            helper.priceToString(payment.getBudget_before_payment()),
            helper.priceToString(payment.getBudget_after_payment()),
            payment.getUser(),
            payment.getCreated_time(),
            payment.getCreated_date(),
            TableActions.View.toString(),
            TableActions.Print.toString()
        };

        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int payment_id) {

        Payments payment = paymentsRepository.find(payment_id);

        table.setValueAt(payment.getId(), selectedRow, 0);
        table.setValueAt(payment.getBuy_gold(), selectedRow, 1);
        table.setValueAt(payment.getCustomer(), selectedRow, 2);
        table.setValueAt(payment.getBudget(), selectedRow, 3);
        table.setValueAt(helper.priceToString(payment.getAmount_paid()), selectedRow, 4);
        table.setValueAt(helper.priceToString(payment.getBalance()), selectedRow, 5);
        table.setValueAt(helper.priceToString(payment.getBudget_before_payment()), selectedRow, 6);
        table.setValueAt(helper.priceToString(payment.getBudget_after_payment()), selectedRow, 7);
        table.setValueAt(payment.getUser(), selectedRow, 8);
        table.setValueAt(payment.getCreated_time(), selectedRow, 9);
        table.setValueAt(payment.getCreated_date(), selectedRow, 10);
    }

    public void deleteItem(JTable table, String rowID, int selectedRow) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(rowID);
        paymentsRepository.delete(id);
        tmodel.removeRow(selectedRow);
    }

    public void onTableClicked(
            int payment_id,
            JLabel paymentID,
            JComboBox buyGoldSelected,
            JTextField totalAmount,
            JTextField amountPaid,
            JTextField amountRemaining,
            JComboBox budgetSelected,
            JTextField budgetBeforePayment,
            JTextField amountPaying,
            JTextField balance,
            JTextField budgetAfterPayment,
            int selectedRow
    ) {

        Payments payment = paymentsRepository.find(payment_id);
        Budget budget = budgetController.getSingleBudgetWithID(payment.getBudget_id());
        double total_budget_used = paymentsRepository.summationOfBudget(budget.getId());

        BuyGold buyGold = buyGoldController.getSingleDataWithID(payment.getBuy_gold_id());
        double total_payment = paymentsRepository.summationOfPurchasePayment(buyGold.getId());

        paymentID.setText(String.valueOf(payment.getId()));
        buyGoldSelected.setSelectedItem(buyGold.getCode() + " | " + buyGold.getCustomer());
        budgetSelected.setSelectedItem(budget.getId() + " | " + budget.getName());

        totalAmount.setText(helper.priceToString(buyGold.getTotal_amount()));
        amountPaid.setText(helper.priceToString(total_payment));
        amountRemaining.setText(helper.priceToString(buyGold.getTotal_amount() - total_payment));

        budgetBeforePayment.setText(helper.priceToString(payment.getBudget_before_payment()));
        budgetAfterPayment.setText(helper.priceToString(payment.getBudget_after_payment()));

        amountPaying.setText(helper.priceToString(payment.getAmount_paid()));
        balance.setText(helper.priceToString(payment.getBalance()));
    }

    public Receipt getSinglePayment(int payment_id) {
        Receipt receipt = paymentsRepository.receiptData(payment_id);
        return receipt;
    }

}
