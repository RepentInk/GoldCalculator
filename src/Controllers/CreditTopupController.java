package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Models.Budget;
import Models.Credit;
import Models.CreditTopup;
import Repository.BudgetRepository;
import Repository.CreditRepository;
import Repository.CreditTopupRepository;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nyark
 */
public class CreditTopupController {
    
    CreditTopupRepository creditTopupRepository = new CreditTopupRepository();
    CreditRepository creditRepository = new CreditRepository();
    BudgetRepository budgetRepository = new BudgetRepository();
    ReportController reportController = new ReportController();
    HelperFunctions helper = new HelperFunctions();
    
    public void populateTable(JTable table, int crediter_id) {
        List<CreditTopup> creditTopups = creditTopupRepository.list(String.valueOf(crediter_id));
        
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;
        
        for (CreditTopup creditTopup : creditTopups) {
            object = new Object[]{
                creditTopup.getId(),
                creditTopup.getCreated_date(),
                creditTopup.getCreated_time(),
                helper.priceToString(creditTopup.getAmount_paid()),
                creditTopup.getUser()
            };
            
            defaultTableModel.addRow(object);
        }
        
        defaultTableModel.fireTableDataChanged();
    }
    
    public void saveUpdate(
            JLabel lblCrediterID,
            JTextField amountPaid,
            JTextField totalAmount,
            JTable table
    ) {
        
        int credit_id = Integer.parseInt(lblCrediterID.getText());
        double amount_paid = amountPaid.getText().isEmpty() ? 0 : helper.parseAmountWithComma(amountPaid.getText());
        double total_amount = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(totalAmount.getText());
        String created_date = helper.returnDate();
        String created_time = helper.returnTime();
        
        CreditTopup creditTopup = new CreditTopup(
                credit_id,
                amount_paid,
                total_amount,
                Authuser.getId(),
                created_date,
                created_time
        );
        
        int last_insert_id = creditTopupRepository.save(creditTopup);
        
        creditRepository.updateCreditAmount(credit_id, amount_paid);
        
        this.populateAfterSaving(table, last_insert_id);
        
    }
    
    private void populateAfterSaving(JTable table, int credit_topup_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;
        
        CreditTopup creditTopup = creditTopupRepository.find(credit_topup_id);
        
        object = new Object[]{
            creditTopup.getId(),
            creditTopup.getCreated_date(),
            creditTopup.getCreated_time(),
            helper.priceToString(creditTopup.getAmount_paid()),
            creditTopup.getUser()
        };
        tmodel.insertRow(0, object);
    }
    
    public void setCreditAmount(int credit_id, JTextField totalAmount, JTextField budgetBalance) {
        Credit credit = creditRepository.find(credit_id);
        Budget budget = budgetRepository.todayBudget(helper.returnDate());
        double budget_used = reportController.budgetUsedAll(budget.getId());
        
        totalAmount.setText(helper.priceToString(credit.getAmount()));
        budgetBalance.setText(helper.priceToString(budget.getTotal_amount() - budget_used));
    }
    
}
