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
    PaymentsRepository paymentsRepository = new PaymentsRepository();
    HelperFunctions helper = new HelperFunctions();

    public void populateTable(JTable table, String startDate, String endDate) {
        if (endDate.equals("")) {
            endDate = helper.returnDate();
        }

        List<Payments> payments;
        if (!startDate.isEmpty() && !endDate.isEmpty()) {
            payments = paymentsRepository.findPaymentBetweenDates(startDate, endDate);
        } else {
            payments = paymentsRepository.list(endDate);
        }

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Payments payment : payments) {
            object = new Object[]{
                payment.getId(),
                payment.getBuy_gold(),
                payment.getCustomer(),
                helper.priceToString(payment.getTotal_amount()),
                helper.priceToString(payment.getAmount_paid()),
                helper.priceToString(payment.getBalance()),
                payment.getUser(),
                payment.getCreated_time(),
                payment.getCreated_date(),
                TableActions.View.toString(),
                TableActions.Print.toString(),
                TableActions.Delete.toString()
            };

            defaultTableModel.addRow(object);
        }
    }

    public void saveUpdate(
            JLabel paymentID,
            JComboBox buyGoldSelected,
            JTextField amountPaying,
            JTextField balance,
            JTable table,
            int selectedRow,
            javax.swing.JDialog dialog
    ) {

        BuyGold buyGold = buyGoldController.getSingleData(buyGoldSelected.getSelectedItem().toString());

        int payment_id = 0;
        double amount_paying = amountPaying.getText().isEmpty() ? 0 : helper.parseAmountWithComma(amountPaying.getText());
        double balance_remain = balance.getText().isEmpty() ? 0 : helper.parseAmountWithComma(balance.getText());
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
                    amount_paying,
                    balance_remain,
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
                    amount_paying,
                    balance_remain,
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

    public void populateDropDownData(JComboBox cmdGoldPurchase, String createdDate) {
        if (createdDate.equals("")) {
            createdDate = helper.returnDate();
        }

        buyGoldController.populateDropdownData(cmdGoldPurchase, "Select Purchase", createdDate);
    }

    public void plopulateBaseOnSearch(JComboBox cmdGoldPurchase, String startDate, String endDate) {
        buyGoldController.populatePurchaseBetweenDates(cmdGoldPurchase, "Select Purchase", startDate, endDate);
    }

    public void setPurchaseDetails(
            String purchase,
            JTextField totalAmount,
            JTextField amountPaid,
            JTextField amountRemains
    ) {
        BuyGold buyGold = buyGoldController.getSingleData(purchase);
        double total_payment = paymentsRepository.summationOfPurchasePayment(buyGold.getId());
        double amount_remains = buyGold.getTotal_amount() - total_payment;

        if (amount_remains < 0) {
            amount_remains = -(amount_remains);
        }

        totalAmount.setText(helper.priceToString(buyGold.getTotal_amount()));
        amountPaid.setText(helper.priceToString(total_payment));
        amountRemains.setText(helper.priceToString(amount_remains));
    }

    private void populateAfterSaving(JTable table, int payment_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;

        Payments payment = paymentsRepository.find(payment_id);

        object = new Object[]{
            payment.getId(),
            payment.getBuy_gold(),
            payment.getCustomer(),
            helper.priceToString(payment.getTotal_amount()),
            helper.priceToString(payment.getAmount_paid()),
            helper.priceToString(payment.getBalance()),
            payment.getUser(),
            payment.getCreated_time(),
            payment.getCreated_date(),
            TableActions.View.toString(),
            TableActions.Print.toString(),
            TableActions.Delete.toString()
        };

        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int payment_id) {

        Payments payment = paymentsRepository.find(payment_id);

        table.setValueAt(payment.getId(), selectedRow, 0);
        table.setValueAt(payment.getBuy_gold(), selectedRow, 1);
        table.setValueAt(payment.getCustomer(), selectedRow, 2);
        table.setValueAt(helper.priceToString(payment.getTotal_amount()), selectedRow, 3);
        table.setValueAt(helper.priceToString(payment.getAmount_paid()), selectedRow, 4);
        table.setValueAt(helper.priceToString(payment.getBalance()), selectedRow, 5);
        table.setValueAt(payment.getUser(), selectedRow, 6);
        table.setValueAt(payment.getCreated_time(), selectedRow, 7);
        table.setValueAt(payment.getCreated_date(), selectedRow, 8);
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
            JTextField amountPaying,
            JTextField balance,
            int selectedRow
    ) {

        Payments payment = paymentsRepository.find(payment_id);
        BuyGold buyGold = buyGoldController.getSingleDataWithID(payment.getBuy_gold_id());

        double total_payment = paymentsRepository.summationOfPurchasePayment(buyGold.getId());

        paymentID.setText(String.valueOf(payment.getId()));
        buyGoldSelected.addItem(buyGold.getCode() + " | " + buyGold.getCustomer());
        buyGoldSelected.setSelectedIndex(1);

        totalAmount.setText(helper.priceToString(buyGold.getTotal_amount()));
        amountPaid.setText(helper.priceToString(total_payment));
        amountRemaining.setText(helper.priceToString(buyGold.getTotal_amount() - total_payment));
        amountPaying.setText(helper.priceToString(payment.getAmount_paid()));
        balance.setText(helper.priceToString(payment.getBalance()));
    }

    public Receipt getSinglePayment(int payment_id) {
        Receipt receipt = paymentsRepository.receiptData(payment_id);
        return receipt;
    }

    public void populatePaymentHistoryTable(JTable table, int buyGoldId) {
        List<Payments> payments = paymentsRepository.paymentHistory(buyGoldId);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Payments payment : payments) {
            object = new Object[]{
                payment.getId(),
                payment.getCreated_date(),
                helper.priceToString(payment.getAmount_paid()),
                helper.priceToString(payment.getBalance()),
                payment.getUser(),
                payment.getCreated_time()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

}
