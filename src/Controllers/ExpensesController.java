package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Helpers.TableActions;
import Models.Budget;
import Models.Expenses;
import Models.ExpensesType;
import Repository.ExpensesRepository;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nyark
 */
public class ExpensesController {

    ExpensesRepository expensesRepository = new ExpensesRepository();
    BudgetController budgetController = new BudgetController();
    ExpensesTypeController expensesTypeController = new ExpensesTypeController();

    HelperFunctions helper = new HelperFunctions();
    ReportController reportController = new ReportController();

    public void populateData(JTable table, String createdDate) {
        List<Expenses> expenses = expensesRepository.list(createdDate);

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Expenses expense : expenses) {
            object = new Object[]{
                expense.getId(),
                expense.getExpense_type(),
                helper.priceToString(expense.getAmount()),
                expense.getPaid_to(),
                expense.getBudget(),
                helper.priceToString(expense.getBudget_before()),
                helper.priceToString(expense.getBudget_after()),
                expense.getUser(),
                expense.getCreated_time(),
                expense.getCreated_date(),
                TableActions.View.toString()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    public void saveUpdate(
            JLabel expenseID,
            JComboBox expenseType,
            JComboBox budgetSelected,
            JTextField totalAmount,
            JTextField paidTo,
            JTextField budgetBefore,
            JTextField budgetAfter,
            int selectedRow,
            JTable table,
            JDialog dialog
    ) {

        Budget budget = budgetController.getSingleBudget(budgetSelected.getSelectedItem().toString());
        ExpensesType expensesType = expensesTypeController.getSingleExpenseType(expenseType.getSelectedItem().toString());

        int expenses_id = 0;
        double expenses_amount = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(totalAmount.getText());
        double budget_before = budgetBefore.getText().isEmpty() ? 0 : helper.parseAmountWithComma(budgetBefore.getText());
        double budget_after = budgetAfter.getText().isEmpty() ? 0 : helper.parseAmountWithComma(budgetAfter.getText());
        String paid_to = paidTo.getText();
        String created_time = helper.returnTime();
        String created_date = helper.returnDate();

        if (!expenseID.getText().equals("")) {
            expenses_id = Integer.parseInt(expenseID.getText());
        }

        if (expenses_id > 0) {

            Expenses expenses = new Expenses(
                    expenses_id,
                    expensesType.getId(),
                    budget.getId(),
                    expenses_amount,
                    paid_to,
                    budget_before,
                    budget_after,
                    Authuser.getId(),
                    created_time,
                    created_date
            );

            expensesRepository.update(expenses, expenses_id);

            this.populateAfterUpdating(table, selectedRow, expenses_id);

            dialog.setVisible(false);

        } else {

            Expenses expenses = new Expenses(
                    expensesType.getId(),
                    budget.getId(),
                    expenses_amount,
                    paid_to,
                    budget_before,
                    budget_after,
                    Authuser.getId(),
                    created_time,
                    created_date
            );

            int last_insert_id = expensesRepository.save(expenses);

            this.populateAfterSaving(table, last_insert_id);

            dialog.setVisible(false);
        }
    }

    private void populateAfterSaving(JTable table, int expenses_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;

        Expenses expense = expensesRepository.find(expenses_id);

        object = new Object[]{
            expense.getId(),
            expense.getExpense_type(),
            helper.priceToString(expense.getAmount()),
            expense.getPaid_to(),
            expense.getBudget(),
            helper.priceToString(expense.getBudget_before()),
            helper.priceToString(expense.getBudget_after()),
            expense.getUser(),
            expense.getCreated_time(),
            expense.getCreated_date(),
            TableActions.View.toString()
        };
        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int expenses_id) {

        Expenses expense = expensesRepository.find(expenses_id);

        table.setValueAt(expense.getId(), selectedRow, 0);
        table.setValueAt(expense.getExpense_type(), selectedRow, 1);
        table.setValueAt(helper.priceToString(expense.getAmount()), selectedRow, 2);
        table.setValueAt(expense.getPaid_to(), selectedRow, 3);
        table.setValueAt(expense.getBudget(), selectedRow, 4);
        table.setValueAt(helper.priceToString(expense.getBudget_before()), selectedRow, 5);
        table.setValueAt(helper.priceToString(expense.getBudget_after()), selectedRow, 6);
        table.setValueAt(expense.getUser(), selectedRow, 7);
        table.setValueAt(expense.getCreated_time(), selectedRow, 8);
        table.setValueAt(expense.getCreated_date(), selectedRow, 9);
    }

    public void deleteItem(JTable table, String expensesTypeID, int selectedRow) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(expensesTypeID);
        expensesRepository.delete(id);
        tmodel.removeRow(selectedRow);
    }

    public void onTableClicked(
            int expenses_id,
            JLabel espensesID,
            JComboBox expenseTypeSelected,
            JComboBox budgetSelected,
            JTextField totalAmount,
            JTextField paidTo,
            JTextField budgetBefore,
            JTextField budgetAfter
    ) {
        Expenses expense = expensesRepository.find(expenses_id);
        Budget budget = budgetController.getSingleBudgetWithID(expense.getBudget_id());
        ExpensesType expensesType = expensesTypeController.getSingleExpenseTypeID(expense.getExpenses_type_id());

        espensesID.setText(String.valueOf(expense.getId()));
        totalAmount.setText(helper.priceToString(expense.getAmount()));
        budgetBefore.setText(helper.priceToString(expense.getBudget_before()));
        budgetAfter.setText(helper.priceToString(expense.getBudget_after()));
        paidTo.setText(expense.getPaid_to());

        expenseTypeSelected.setSelectedItem(expensesType.getId() + " | " + expensesType.getName());
        budgetSelected.setSelectedItem(budget.getId() + " | " + budget.getName());
    }

    public void setBudgetDetails(
            String budget_selected,
            JTextField amountBeforePayment
    ) {
        Budget budget = budgetController.getSingleBudget(budget_selected);
        double budget_used = reportController.budgetUsedInAll(budget.getId());
        amountBeforePayment.setText(helper.priceToString(budget.getTotal_amount() - budget_used));
    }

}
