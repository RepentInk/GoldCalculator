package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Helpers.TableActions;
import Models.Budget;
import Repository.BudgetRepository;
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
public class BudgetController {

    BudgetRepository budgetRepository = new BudgetRepository();
    HelperFunctions helper = new HelperFunctions();

    public void populateTable(JTable table, String startDate, String endDate) {
        if (endDate.equals("")) {
            endDate = helper.returnDate();
        }

        List<Budget> budgets;
        if (!startDate.isEmpty() && !endDate.isEmpty()) {
            budgets = budgetRepository.findBudgetBetweenDates(startDate, endDate);
        } else {
            budgets = budgetRepository.list(endDate);
        }

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Budget budget : budgets) {
            object = new Object[]{
                budget.getId(),
                budget.getName(),
                budget.getSource_of_fund(),
                helper.priceToString(budget.getTotal_amount()),
                budget.getUser(),
                budget.getCreated_time(),
                budget.getCreated_date(),
                TableActions.View.toString(),
                TableActions.Delete.toString()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    private String statusOfBudget(boolean status) {
        return status ? "Closed" : "Opened";
    }

    public void saveUpdate(
            JLabel budgetID,
            JTextField name,
            JTextField totalAmount,
            JTextField sourceOfFund,
            JTable table,
            int selectedRow,
            javax.swing.JDialog dialog
    ) {

        int budget_id = 0;
        String budget_name = name.getText().trim();
        String source_of_fund = sourceOfFund.getText().trim();
        double total_amount = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(totalAmount.getText());
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
                    source_of_fund,
                    false,
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
                    source_of_fund,
                    false,
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

        object = new Object[]{
            budget.getId(),
            budget.getName(),
            budget.getSource_of_fund(),
            helper.priceToString(budget.getTotal_amount()),
            budget.getUser(),
            budget.getCreated_time(),
            budget.getCreated_date(),
            TableActions.View.toString(),
            TableActions.Delete.toString(),};

        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int budget_id) {
        Budget budget = budgetRepository.find(budget_id);

        table.setValueAt(budget.getId(), selectedRow, 0);
        table.setValueAt(budget.getName(), selectedRow, 1);
        table.setValueAt(budget.getSource_of_fund(), selectedRow, 2);
        table.setValueAt(helper.priceToString(budget.getTotal_amount()), selectedRow, 3);
        table.setValueAt(budget.getUser(), selectedRow, 4);
        table.setValueAt(budget.getCreated_time(), selectedRow, 5);
        table.setValueAt(budget.getCreated_date(), selectedRow, 6);
        table.setValueAt(this.statusOfBudget(budget.isStatus()), selectedRow, 7);
    }

    public void deleteItem(JTable table, String budgetID, int selectedRow) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(budgetID);

        budgetRepository.delete(id);
        tmodel.removeRow(selectedRow);
    }

    public void onTableClick(
            int budget_id,
            JLabel budgetID,
            JTextField name,
            JTextField totalAmount,
            JTextField sourceOfFund
    ) {
        Budget budget = budgetRepository.find(budget_id);

        budgetID.setText(String.valueOf(budget.getId()));
        name.setText(budget.getName());

        totalAmount.setText(helper.priceToString(budget.getTotal_amount()));
        sourceOfFund.setText(budget.getSource_of_fund());
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

    public void changeStatus(
            int budget_id,
            int status,
            JTable table,
            int selectedRow
    ) {
        budgetRepository.updateStatus(budget_id, status);
        this.populateAfterUpdating(table, selectedRow, budget_id);
    }

}
