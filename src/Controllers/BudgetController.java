package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Helpers.TableActions;
import Models.Budget;
import Repository.BudgetRepository;
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
public class BudgetController {

    BudgetRepository budgetRepository = new BudgetRepository();
    HelperFunctions helper = new HelperFunctions();
    PaymentsRepository paymentsRepository = new PaymentsRepository();

    ReportController reportController = new ReportController();

    public void populateTable(JTable table, String createdDate) {
        if (createdDate.equals("")) {
            createdDate = helper.returnDate();
        }
        List<Budget> budgets = budgetRepository.list(createdDate);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Budget budget : budgets) {
            double total_budget_used = this.budgetUsed(budget.getId());

            object = new Object[]{
                budget.getId(),
                budget.getName(),
                helper.priceToString(budget.getTotal_amount()),
                helper.priceToString(budget.getAmount_forward()),
                helper.priceToString(total_budget_used),
                helper.priceToString(budget.getTotal_amount() - total_budget_used),
                budget.getUser(),
                budget.getCreated_time(),
                budget.getCreated_date(),
                TableActions.View.toString(),
                TableActions.Add.toString()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    public void saveUpdate(
            JLabel budgetID,
            JTextField name,
            JTextField totalAmount,
            JTextField amountForward,
            JTable table,
            int selectedRow,
            javax.swing.JDialog dialog
    ) {

        int budget_id = 0;
        String budget_name = name.getText().trim();
        String start_date = helper.returnDate();
        String end_date = helper.returnDate();
        double total_amount = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(totalAmount.getText());
        double amount_forward = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(amountForward.getText());
        String created_date = helper.returnDate();
        String created_time = helper.returnTime();
        String raw_date = helper.returnDate();

        if (!budgetID.getText().equals("")) {
            budget_id = Integer.parseInt(budgetID.getText());
        }

        if (budget_id > 0) {

            Budget budget = new Budget(
                    budget_id,
                    budget_name,
                    total_amount,
                    amount_forward,
                    false,
                    start_date,
                    end_date,
                    created_date,
                    created_time,
                    raw_date,
                    Authuser.getId()
            );

            budgetRepository.update(budget, budget_id);

            this.populateAfterUpdating(table, selectedRow, budget_id);

            dialog.setVisible(false);

        } else {

            Budget budget = new Budget(
                    budget_name,
                    total_amount,
                    amount_forward,
                    false,
                    start_date,
                    end_date,
                    created_date,
                    created_time,
                    raw_date,
                    Authuser.getId()
            );

            int last_insert_id = budgetRepository.save(budget);

            this.populateAfterSaving(table, last_insert_id);

            dialog.setVisible(false);
        }

    }

    private void populateAfterSaving(JTable table, int budget_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;

        Budget budget = budgetRepository.find(budget_id);
        double total_budget_used = this.budgetUsed(budget.getId());

        object = new Object[]{
            budget.getId(),
            budget.getName(),
            helper.priceToString(budget.getTotal_amount()),
            helper.priceToString(budget.getAmount_forward()),
            helper.priceToString(total_budget_used),
            helper.priceToString(budget.getTotal_amount() - total_budget_used),
            budget.getUser(),
            budget.getCreated_time(),
            budget.getCreated_date(),
            TableActions.View.toString(),
            TableActions.Add.toString()
        };

        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int budget_id) {

        Budget budget = budgetRepository.find(budget_id);
        double total_budget_used = this.budgetUsed(budget.getId());

        table.setValueAt(budget.getId(), selectedRow, 0);
        table.setValueAt(budget.getName(), selectedRow, 1);
        table.setValueAt(helper.priceToString(budget.getTotal_amount()), selectedRow, 2);
        table.setValueAt(helper.priceToString(budget.getAmount_forward()), selectedRow, 3);
        table.setValueAt(helper.priceToString(total_budget_used), selectedRow, 4);
        table.setValueAt(helper.priceToString(budget.getTotal_amount() - total_budget_used), selectedRow, 5);
    }

    private double budgetUsed(int id) {
        return reportController.budgetUsedAll(id);
    }

    public void deleteItem(JTable table, String budgetID, int selectedRow) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(budgetID);

        double total = paymentsRepository.summationOfBudget(id);
        if (total > 0) {
            JOptionPane.showMessageDialog(null, "Sorry! This record cannot be deleted because history of payments exist");
            return;
        }

        budgetRepository.delete(id);
        tmodel.removeRow(selectedRow);
    }

    public void onTableClick(
            int budget_id,
            JLabel budgetID,
            JTextField name,
            JTextField totalAmount,
            JTextField amountUsed,
            JTextField amountLeft,
            JTextField amountForward,
            JTextField txtTodayBudget
    ) {
        Budget budget = budgetRepository.find(budget_id);

        budgetID.setText(String.valueOf(budget.getId()));
        name.setText(budget.getName());
        double total_budget_used = this.budgetUsed(budget.getId());

        totalAmount.setText(helper.priceToString(budget.getTotal_amount()));
        amountUsed.setText(helper.priceToString(total_budget_used));
        amountLeft.setText(helper.priceToString(budget.getTotal_amount() - total_budget_used));
        amountForward.setText(helper.priceToString(budget.getAmount_forward()));
        txtTodayBudget.setText(helper.priceToString(budget.getTotal_amount() - budget.getAmount_forward()));
    }

    public void populateDropdownData(JComboBox comboBox, String title, String createdDate) {
        List<Budget> listBudgets = budgetRepository.list(createdDate);
        comboBox.addItem(title);
        comboBox.setSelectedIndex(0);

        for (Budget budget : listBudgets) {
            comboBox.addItem(budget.getId() + " | " + budget.getName());
        }
    }

    public Budget getSingleBudget(String selected_budget) {
        String budgetID = helper.splitWord(selected_budget, 0, "|");
        Budget budget = budgetRepository.find(Integer.parseInt(budgetID.trim()));
        return budget;
    }

    public Budget getSingleBudgetWithID(int budget_id) {
        Budget budget = budgetRepository.find(budget_id);
        return budget;
    }

    public void calculateAmountForward(JTextField amountForward) {
        Budget budget = budgetRepository.lastBudget();
        double budgetUsed = this.budgetUsed(budget.getId());
        double balance = budget.getTotal_amount() - budgetUsed;

        amountForward.setText(helper.priceToString(balance));
    }

}
