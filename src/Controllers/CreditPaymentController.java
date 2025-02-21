package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Models.Credit;
import Models.CreditPayment;
import Repository.CreditPaymentRepository;
import Repository.CreditRepository;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nyark
 */
public class CreditPaymentController {

    CreditPaymentRepository creditPaymentRepository = new CreditPaymentRepository();
    CreditRepository creditRepository = new CreditRepository();
    HelperFunctions helper = new HelperFunctions();

    public void populateTable(JTable table, int crediter_id) {
        List<CreditPayment> creditPayments = creditPaymentRepository.list(String.valueOf(crediter_id));

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (CreditPayment creditPayment : creditPayments) {
            object = new Object[]{
                creditPayment.getId(),
                creditPayment.getCreated_date(),
                creditPayment.getCreated_time(),
                helper.priceToString(creditPayment.getPaid()),
                helper.priceToString(creditPayment.getBalance()),
                creditPayment.getUser()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    public void viewDetails(
            int credit_id,
            JLabel lblCrediterID,
            JLabel lblCustomerID,
            JTextField amountLeft
    ) {
        Credit credit = creditRepository.find(credit_id);
        lblCrediterID.setText(String.valueOf(credit.getId()));
        lblCustomerID.setText(String.valueOf(credit.getCustomer_id()));
        double amountPaid = creditPaymentRepository.summationAmountPaid(credit.getId());
        amountLeft.setText(helper.priceToString(credit.getAmount() - amountPaid));
    }

    public void saveUpdate(
            JLabel lblCrediterID,
            JLabel lblCustomerID,
            JTextField amountPaying,
            JTextField balanceAmount,
            JTable table
    ) {

        int credit_id = Integer.parseInt(lblCrediterID.getText());
        int customer_id = Integer.parseInt(lblCustomerID.getText());
        double amount_left = amountPaying.getText().isEmpty() ? 0 : helper.parseAmountWithComma(amountPaying.getText());
        double balance = balanceAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(balanceAmount.getText());
        String created_date = helper.returnDate();
        String raw_date = helper.returnDate();
        String created_time = helper.returnTime();

        CreditPayment creditPayment = new CreditPayment(
                credit_id,
                customer_id,
                amount_left,
                balance,
                false,
                Authuser.getId(),
                created_date,
                created_time,
                raw_date
        );

        int last_insert_id = creditPaymentRepository.save(creditPayment);

        this.populateAfterSaving(table, last_insert_id);

    }

    private void populateAfterSaving(JTable table, int credit_payment_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;

        CreditPayment creditPayment = creditPaymentRepository.find(credit_payment_id);

        object = new Object[]{
            creditPayment.getId(),
            creditPayment.getCreated_date(),
            creditPayment.getCreated_time(),
            helper.priceToString(creditPayment.getPaid()),
            helper.priceToString(creditPayment.getBalance()),
            creditPayment.getUser()
        };
        tmodel.insertRow(0, object);
    }

    public void saveCreditPayment(int customer_id, double totalAmount, double previousBalance) {
        Credit credit = creditRepository.findCustomerLastCredit(customer_id);
        String created_date = helper.returnDate();
        String raw_date = helper.returnDate();
        String created_time = helper.returnTime();

        double balance = 0;
        double temporalAmount = previousBalance;
        if (previousBalance > totalAmount) {
            balance = previousBalance - totalAmount;
            temporalAmount = totalAmount;
        }

        CreditPayment creditPayment = new CreditPayment(
                credit.getId(),
                customer_id,
                temporalAmount,
                balance,
                true,
                Authuser.getId(),
                created_date,
                created_time,
                raw_date
        );

        creditPaymentRepository.save(creditPayment);
    }

}
