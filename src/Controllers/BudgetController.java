package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Helpers.TableActions;
import Models.Budget;
import Repository.BudgetRepository;
import Repository.PaymentsRepository;
import com.toedter.calendar.JDateChooser;
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
    PaymentsRepository paymentsRepository = new PaymentsRepository();

    public void populateTable(JTable table, String year) {
        if (year.equals("")) {
            year = helper.returnCurrentYear();
        }
        List<Budget> budgets = budgetRepository.list(year);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Budget budget : budgets) {
            double total_budget_used = this.budgetUsed(budget.getId());

            object = new Object[]{
                budget.getId(),
                budget.getName(),
                budget.getStart_date(),
                budget.getEnd_date(),
                helper.priceToString(budget.getTotal_amount()),
                helper.priceToString(total_budget_used),
                helper.priceToString(budget.getTotal_amount() - total_budget_used),
                this.budgetStatus(budget.isStatus()),
                budget.getUser(),
                budget.getCreated_date(),
                budget.getCreated_time(),
                TableActions.View.toString(),
                TableActions.Delete.toString()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    public void saveUpdate(
            JLabel budgetID,
            JTextField name,
            JDateChooser startDate,
            JDateChooser endDate,
            JTextField totalAmount,
            JTextField amountUsed,
            JTextField amountLeft,
            JTable table,
            int selectedRow,
            javax.swing.JDialog dialog
    ) {

        int budget_id = 0;
        String budget_name = name.getText().trim();
        String start_date = ((JTextField) startDate.getDateEditor().getUiComponent()).getText().toLowerCase();
        String end_date = ((JTextField) endDate.getDateEditor().getUiComponent()).getText().toLowerCase();
        double total_amount = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(totalAmount.getText());
        double amount_used = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(amountUsed.getText());
        double amount_left = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(amountLeft.getText());
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
                    amount_used,
                    amount_left,
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
                    amount_used,
                    amount_left,
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
            budget.getStart_date(),
            budget.getEnd_date(),
            helper.priceToString(budget.getTotal_amount()),
            helper.priceToString(total_budget_used),
            helper.priceToString(budget.getTotal_amount() - total_budget_used),
            this.budgetStatus(budget.isStatus()),
            budget.getUser(),
            budget.getCreated_date(),
            budget.getCreated_time(),
            TableActions.View.toString(),
            TableActions.Delete.toString()
        };

        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int budget_id) {

        Budget budget = budgetRepository.find(budget_id);
        double total_budget_used = this.budgetUsed(budget.getId());

        table.setValueAt(budget.getId(), selectedRow, 0);
        table.setValueAt(budget.getName(), selectedRow, 1);
        table.setValueAt(budget.getStart_date(), selectedRow, 2);
        table.setValueAt(budget.getEnd_date(), selectedRow, 3);
        table.setValueAt(helper.priceToString(budget.getTotal_amount()), selectedRow, 4);
        table.setValueAt(helper.priceToString(total_budget_used), selectedRow, 5);
        table.setValueAt(helper.priceToString(budget.getTotal_amount() - total_budget_used), selectedRow, 6);
        table.setValueAt(this.budgetStatus(budget.isStatus()), selectedRow, 7);
    }

    private String budgetStatus(boolean status) {
        return status ? "Closed" : "Open";
    }

    private double budgetUsed(int id) {
        return paymentsRepository.summationOfBudget(id);
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
            JDateChooser startDate,
            JDateChooser endDate,
            JTextField totalAmount,
            JTextField amountUsed,
            JTextField amountLeft
    ) {
        Budget budget = budgetRepository.find(budget_id);

        budgetID.setText(String.valueOf(budget.getId()));
        name.setText(budget.getName());
        if (!budget.getStart_date().isEmpty()) {
            startDate.setDate(helper.convertChooserDate(budget.getStart_date()));
        }

        if (!budget.getEnd_date().isEmpty()) {
            endDate.setDate(helper.convertChooserDate(budget.getEnd_date()));
        }

        double total_budget_used = this.budgetUsed(budget.getId());

        totalAmount.setText(helper.priceToString(budget.getTotal_amount()));
        amountUsed.setText(helper.priceToString(total_budget_used));
        amountLeft.setText(helper.priceToString(budget.getTotal_amount() - total_budget_used));
    }

    public void populateDropdownData(JComboBox comboBox, String title, String year) {
        List<Budget> listBudgets = budgetRepository.list(year);
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

}
