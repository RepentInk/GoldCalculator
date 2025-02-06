package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Helpers.TableActions;
import ModelDTO.BuyGoldDTO;
import Models.BuyGold;
import Models.Customer;
import Repository.AnonymousRepository;
import Repository.BuyGoldRepository;
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
public class BuyGoldController {

    BuyGoldRepository buyGoldRepository = new BuyGoldRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    AnonymousRepository anonymousRepository = new AnonymousRepository();
    HelperFunctions helper = new HelperFunctions();

    public void populateData(JTable table, String year) {
        List<BuyGold> buyGolds = buyGoldRepository.list(year);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (BuyGold buyGold : buyGolds) {
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
                buyGold.getUser(),
                buyGold.getCreated_date(),
                TableActions.View.toString(),
                TableActions.Delete.toString()
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
                    created_date,
                    created_time,
                    raw_date,
                    customer_id,
                    Authuser.getId()
            );

            int last_insert_id = buyGoldRepository.save(buyGold);

            this.populateAfterSaving(table, last_insert_id);

            dialog.setVisible(false);
        }
    }

    private void populateAfterSaving(JTable table, int buy_gold_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;

        BuyGold buyGold = buyGoldRepository.find(buy_gold_id);

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
            buyGold.getUser(),
            buyGold.getCreated_date(),
            TableActions.View.toString(),
            TableActions.Delete.toString()
        };
        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int buy_gold_id) {

        BuyGold buyGold = buyGoldRepository.find(buy_gold_id);

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
        table.setValueAt(buyGold.getUser(), selectedRow, 11);
        table.setValueAt(buyGold.getCreated_date(), selectedRow, 12);

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
            JTextField totalAmount
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
    }

}
