package Controllers;

import Helpers.Authuser;
import Helpers.HelperFunctions;
import Helpers.TableActions;
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
    ExpensesTypeController expensesTypeController = new ExpensesTypeController();
    HelperFunctions helper = new HelperFunctions();

    public void populateData(JTable table, String startDate, String endDate) {
        if (endDate.equals("")) {
            endDate = helper.returnDate();
        }

        List<Expenses> expenses;
        if (!startDate.isEmpty() && !endDate.isEmpty()) {
            expenses = expensesRepository.findExpensesBetweenDates(startDate, endDate);
        } else {
            expenses = expensesRepository.list(endDate);
        }

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (Expenses expense : expenses) {
            object = new Object[]{
                expense.getId(),
                expense.getExpense_type(),
                helper.priceToString(expense.getAmount()),
                expense.getPaid_to(),
                expense.getUser(),
                expense.getCreated_time(),
                expense.getCreated_date(),
                TableActions.View.toString(),
                TableActions.Delete.toString()
            };

            defaultTableModel.addRow(object);
        }
    }

    public void saveUpdate(
            JLabel expenseID,
            JComboBox expenseType,
            JTextField totalAmount,
            JTextField paidTo,
            int selectedRow,
            JTable table,
            JDialog dialog
    ) {
        ExpensesType expensesType = expensesTypeController.getSingleExpenseType(expenseType.getSelectedItem().toString());

        int expenses_id = 0;
        double expenses_amount = totalAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(totalAmount.getText());
        String paid_to = paidTo.getText();
        String created_time = helper.returnTime();
        String created_date = helper.returnDate();
        String raw_date = helper.returnDate();

        if (!expenseID.getText().equals("")) {
            expenses_id = Integer.parseInt(expenseID.getText());
        }

        if (expenses_id > 0) {

            Expenses expenses = new Expenses(
                    expenses_id,
                    expensesType.getId(),
                    expenses_amount,
                    paid_to,
                    Authuser.getId(),
                    created_time,
                    created_date,
                    raw_date
            );

            expensesRepository.update(expenses, expenses_id);

            this.populateAfterUpdating(table, selectedRow, expenses_id);

            dialog.setVisible(false);

        } else {

            Expenses expenses = new Expenses(
                    expensesType.getId(),
                    expenses_amount,
                    paid_to,
                    Authuser.getId(),
                    created_time,
                    created_date,
                    raw_date
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
            expense.getUser(),
            expense.getCreated_time(),
            expense.getCreated_date(),
            TableActions.View.toString(),
            TableActions.Delete.toString()
        };
        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int expenses_id) {

        Expenses expense = expensesRepository.find(expenses_id);

        table.setValueAt(expense.getId(), selectedRow, 0);
        table.setValueAt(expense.getExpense_type(), selectedRow, 1);
        table.setValueAt(helper.priceToString(expense.getAmount()), selectedRow, 2);
        table.setValueAt(expense.getPaid_to(), selectedRow, 3);
        table.setValueAt(expense.getUser(), selectedRow, 4);
        table.setValueAt(expense.getCreated_time(), selectedRow, 5);
        table.setValueAt(expense.getCreated_date(), selectedRow, 6);
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
            JTextField totalAmount,
            JTextField paidTo
    ) {
        Expenses expense = expensesRepository.find(expenses_id);
        ExpensesType expensesType = expensesTypeController.getSingleExpenseTypeID(expense.getExpenses_type_id());

        espensesID.setText(String.valueOf(expense.getId()));
        totalAmount.setText(helper.priceToString(expense.getAmount()));
        paidTo.setText(expense.getPaid_to());

        expenseTypeSelected.setSelectedItem(expensesType.getId() + " | " + expensesType.getName());
    }

}
