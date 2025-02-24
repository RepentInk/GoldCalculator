package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Helpers.TableActions;
import ModelDTO.BuyGoldDTO;
import Models.BuyGold;
import Models.Customer;
import Models.Receipt;
import Repository.AnonymousRepository;
import Repository.BuyGoldRepository;
import Repository.CustomerRepository;
import Repository.PaymentsRepository;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nyark
 */
public class BuyGoldController {

    BuyGoldRepository buyGoldRepository = new BuyGoldRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    AnonymousRepository anonymousRepository = new AnonymousRepository();
    PaymentsRepository paymentsRepository = new PaymentsRepository();
    CreditPaymentController creditPaymentController = new CreditPaymentController();
    HelperFunctions helper = new HelperFunctions();

    public void populateData(JTable table, String createdDate) {
        List<BuyGold> buyGolds = buyGoldRepository.list(createdDate);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (BuyGold buyGold : buyGolds) {
            double amount_paid = this.amountPaid(buyGold.getId());

            object = new Object[]{
                buyGold.getId(),
                buyGold.getCode(),
                buyGold.getCustomer(),
                helper.priceToString(buyGold.getTop()),
                helper.priceToString(buyGold.getDown()),
                helper.priceToString(buyGold.getPounds()),
                helper.priceToString(buyGold.getDensity()),
                helper.priceToString(buyGold.getKarat()),
                helper.priceToString(buyGold.getTotal_weight()),
                helper.priceToString(buyGold.getBase_price()),
                helper.priceToString(buyGold.getTotal_amount()),
                helper.priceToString(amount_paid + buyGold.getCredit_balance()),
                helper.priceToString(buyGold.getTotal_amount() - (amount_paid + buyGold.getCredit_balance())),
                buyGold.getUser(),
                buyGold.getCreated_date(),
                TableActions.View.toString(),
                TableActions.History.toString(),
                TableActions.Print.toString()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    public void saveUpdate(
            JLabel buyGoldID,
            JLabel code,
            JComboBox customer,
            JTextField top,
            JTextField down,
            JTextField pound,
            JTextField density,
            JTextField karat,
            JTextField value,
            JTextField basePrice,
            JTextField totalAmount,
            JTextField creditAmount,
            JTable table,
            int selectedRow,
            javax.swing.JDialog dialog
    ) {

        int buy_gold_id = 0;
        int customer_id = 0;
        String selected_customer = customer.getSelectedItem().toString();
        String batch_code = code.getText();
        double top_value = top.getText().isEmpty() ? 0 : helper.parseAmountWithComma(top.getText());
        double down_value = down.getText().isEmpty() ? 0 : helper.parseAmountWithComma(down.getText());
        double pound_value = pound.getText().isEmpty() ? 0 : helper.parseAmountWithComma(pound.getText());
        double density_value = density.getText().isEmpty() ? 0 : helper.parseAmountWithComma(density.getText());
        double karat_value = karat.getText().isEmpty() ? 0 : helper.parseAmountWithComma(karat.getText());
        double value_value = value.getText().isEmpty() ? 0 : helper.parseAmountWithComma(value.getText());
        double base_price = basePrice.getText().isEmpty() ? 0 : helper.parseAmountWithComma(basePrice.getText());
        double total_amount = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(totalAmount.getText());
        double credit_amount = creditAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(creditAmount.getText());
        String created_date = helper.returnDate();
        String raw_date = helper.returnDate();
        String created_time = helper.returnTime();

        if (!selected_customer.isEmpty()) {
            String fullName = helper.splitWord(selected_customer, 0, "|");
            String phoneNumber = helper.splitWord(selected_customer, 1, "|");
            Customer customerInfo = customerRepository.findByName(fullName.trim(), phoneNumber.trim());
            customer_id = customerInfo.getId();
        }

        if (!buyGoldID.getText().isEmpty()) {
            buy_gold_id = Integer.parseInt(buyGoldID.getText());
        }

        double credit_balance = total_amount;
        if (total_amount > credit_amount) {
            credit_balance = credit_amount;
        }

        if (buy_gold_id > 0) {
            BuyGold buyGold = new BuyGold(
                    buy_gold_id,
                    batch_code,
                    top_value,
                    down_value,
                    density_value,
                    karat_value,
                    pound_value,
                    base_price,
                    value_value,
                    total_amount,
                    credit_balance,
                    created_date,
                    created_time,
                    raw_date,
                    customer_id,
                    Authuser.getId()
            );

            buyGoldRepository.update(buyGold, buy_gold_id);

            this.populateAfterUpdating(table, selectedRow, buy_gold_id);

            dialog.setVisible(false);

        } else {

            BuyGold buyGold = new BuyGold(
                    batch_code,
                    top_value,
                    down_value,
                    density_value,
                    karat_value,
                    pound_value,
                    base_price,
                    value_value,
                    total_amount,
                    credit_balance,
                    created_date,
                    created_time,
                    raw_date,
                    customer_id,
                    Authuser.getId()
            );

            int last_insert_id = buyGoldRepository.save(buyGold);

            this.populateAfterSaving(table, last_insert_id);

            if (credit_amount > 0) {
                creditPaymentController.saveCreditPayment(
                        customer_id,
                        total_amount,
                        credit_amount,
                        last_insert_id
                );
            }

            dialog.setVisible(false);
        }

    }

    private void populateAfterSaving(JTable table, int buy_gold_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;

        BuyGold buyGold = buyGoldRepository.find(buy_gold_id);
        double amount_paid = this.amountPaid(buyGold.getId());

        object = new Object[]{
            buyGold.getId(),
            buyGold.getCode(),
            buyGold.getCustomer(),
            helper.priceToString(buyGold.getTop()),
            helper.priceToString(buyGold.getDown()),
            helper.priceToString(buyGold.getPounds()),
            helper.priceToString(buyGold.getDensity()),
            helper.priceToString(buyGold.getKarat()),
            helper.priceToString(buyGold.getTotal_weight()),
            helper.priceToString(buyGold.getBase_price()),
            helper.priceToString(buyGold.getTotal_amount()),
            helper.priceToString(amount_paid + buyGold.getCredit_balance()),
            helper.priceToString(buyGold.getTotal_amount() - (amount_paid + buyGold.getCredit_balance())),
            buyGold.getUser(),
            buyGold.getCreated_date(),
            TableActions.View.toString(),
            TableActions.History.toString(),
            TableActions.Print.toString()
        };

        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int buy_gold_id) {

        BuyGold buyGold = buyGoldRepository.find(buy_gold_id);
        double amount_paid = this.amountPaid(buyGold.getId());

        table.setValueAt(buyGold.getId(), selectedRow, 0);
        table.setValueAt(buyGold.getCode(), selectedRow, 1);
        table.setValueAt(buyGold.getCustomer(), selectedRow, 2);
        table.setValueAt(helper.priceToString(buyGold.getTop()), selectedRow, 3);
        table.setValueAt(helper.priceToString(buyGold.getDown()), selectedRow, 4);
        table.setValueAt(helper.priceToString(buyGold.getPounds()), selectedRow, 5);
        table.setValueAt(helper.priceToString(buyGold.getDensity()), selectedRow, 6);
        table.setValueAt(helper.priceToString(buyGold.getKarat()), selectedRow, 7);
        table.setValueAt(helper.priceToString(buyGold.getTotal_weight()), selectedRow, 8);
        table.setValueAt(helper.priceToString(buyGold.getBase_price()), selectedRow, 9);
        table.setValueAt(helper.priceToString(buyGold.getTotal_amount()), selectedRow, 10);
        table.setValueAt(helper.priceToString(amount_paid + buyGold.getCredit_balance()), selectedRow, 11);
        table.setValueAt(helper.priceToString(buyGold.getTotal_amount() - (amount_paid + buyGold.getCredit_balance())), selectedRow, 12);
        table.setValueAt(buyGold.getUser(), selectedRow, 13);
        table.setValueAt(buyGold.getCreated_date(), selectedRow, 14);

    }

    public String generateCode() {
        String code = anonymousRepository.generateCode(
                BuyGoldDTO.getBUY_GOLD_DB(),
                BuyGoldDTO.getCODE(),
                helper.returnCurrentYearTwoDigit()
        );
        return code;
    }

    public void deleteItem(JTable table, String buyGoldID, int selectedRow) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(buyGoldID);

        double total = paymentsRepository.summationOfPurchasePayment(id);
        if (total > 0) {
            JOptionPane.showMessageDialog(null, "Sorry! This record cannot be deleted because history of payments exist");
            return;
        }

        buyGoldRepository.delete(id);
        tmodel.removeRow(selectedRow);
    }

    public void onTableClicked(
            int buy_gold_id,
            JLabel buyGoldID,
            JLabel code,
            JComboBox customer,
            JTextField top,
            JTextField down,
            JTextField pound,
            JTextField density,
            JTextField karat,
            JTextField value,
            JTextField basePrice,
            JTextField totalAmount,
            JTextField creditAmount,
            JTextField balancePayable
    ) {
        BuyGold buyGold = buyGoldRepository.find(buy_gold_id);
        Customer customerInfo = customerRepository.find(buyGold.getCustomer_id());

        buyGoldID.setText(String.valueOf(buyGold.getId()));
        code.setText(buyGold.getCode());
        customer.setSelectedItem(customerInfo.getFullname() + " | " + customerInfo.getPhone_number());
        top.setText(helper.priceToString(buyGold.getTop()));
        down.setText(helper.priceToString(buyGold.getDown()));
        pound.setText(helper.priceToString(buyGold.getPounds()));
        density.setText(helper.priceToString(buyGold.getDensity()));
        karat.setText(helper.priceToString(buyGold.getKarat()));
        value.setText(helper.priceToString(buyGold.getTotal_weight()));
        basePrice.setText(helper.priceToString(buyGold.getBase_price()));
        totalAmount.setText(helper.priceToString(buyGold.getTotal_amount()));
        creditAmount.setText(helper.priceToString(buyGold.getCredit_balance()));
        balancePayable.setText(helper.priceToString(buyGold.getTotal_amount() - buyGold.getCredit_balance()));
    }

    public void populateDropdownData(JComboBox comboBox, String title, String createdDate) {
        List<BuyGold> listBuyGolds = buyGoldRepository.list(createdDate);
        comboBox.addItem(title);
        comboBox.setSelectedIndex(0);

        for (BuyGold buyGold : listBuyGolds) {
            comboBox.addItem(buyGold.getCode() + " | " + buyGold.getCustomer());
        }
    }

    public BuyGold getSingleData(String selected_row) {
        String batch_code = helper.splitWord(selected_row, 0, "|");
        BuyGold buyGold = buyGoldRepository.findByCode(batch_code.trim());
        return buyGold;
    }

    public BuyGold getSingleDataWithID(int buy_gold_id) {
        BuyGold buyGold = buyGoldRepository.find(buy_gold_id);
        return buyGold;
    }

    private double amountPaid(int id) {
        double total = paymentsRepository.summationOfPurchasePayment(id);
        return total;
    }

    public Receipt buyGoldData(int buyGoldId) {
        Receipt receiptData = buyGoldRepository.receiptData(buyGoldId);
        return receiptData;
    }
}
