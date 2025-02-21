package Controllers;

import Helpers.HelperFunctions;
import Helpers.TableActions;
import Models.ExpensesType;
import Repository.ExpensesRepository;
import Repository.ExpensesTypeRepository;
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
public class ExpensesTypeController {

    ExpensesTypeRepository expensesTypeRepository = new ExpensesTypeRepository();
    ExpensesRepository expensesRepository = new ExpensesRepository();
    HelperFunctions helper = new HelperFunctions();

    public void populateDropdownData(JComboBox comboBox, String title) {
        List<ExpensesType> expensesTypes = expensesTypeRepository.list();
        comboBox.addItem(title);
        comboBox.setSelectedIndex(0);

        for (ExpensesType expensesType : expensesTypes) {
            comboBox.addItem(expensesType.getId() + " | " + expensesType.getName());
        }
    }

    public void populateData(JTable table) {
        List<ExpensesType> expensesTypes = expensesTypeRepository.list();

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] object;

        for (ExpensesType expensesType : expensesTypes) {
            object = new Object[]{
                expensesType.getId(),
                expensesType.getName(),
                expensesType.getCreated_time(),
                expensesType.getCreated_date(),
                TableActions.View.toString(),
                TableActions.Delete.toString()
            };

            defaultTableModel.addRow(object);
        }

        defaultTableModel.fireTableDataChanged();
    }

    public void saveUpdate(
            JLabel expenseTypeID,
            JTextField name,
            JTable table,
            int selectedRow,
            javax.swing.JDialog dialog
    ) {

        int expenses_type_id = 0;
        String expense_type = name.getText().trim();
        String created_time = helper.returnTime();
        String created_date = helper.returnDate();

        if (!expenseTypeID.getText().equals("")) {
            expenses_type_id = Integer.parseInt(expenseTypeID.getText());
        }

        if (expenses_type_id > 0) {

            ExpensesType expensesType = new ExpensesType(
                    expenses_type_id,
                    expense_type,
                    created_date,
                    created_time
            );

            expensesTypeRepository.update(expensesType, expenses_type_id);

            this.populateAfterUpdating(table, selectedRow, expenses_type_id);

            dialog.setVisible(false);

        } else {

            ExpensesType expensesType = new ExpensesType(
                    expense_type,
                    created_date,
                    created_time
            );

            int last_insert_id = expensesTypeRepository.save(expensesType);

            this.populateAfterSaving(table, last_insert_id);

            dialog.setVisible(false);
        }

    }

    private void populateAfterSaving(JTable table, int expenses_type_id) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        Object[] object;

        ExpensesType expensesType = expensesTypeRepository.find(expenses_type_id);

        object = new Object[]{
            expensesType.getId(),
            expensesType.getName(),
            expensesType.getCreated_time(),
            expensesType.getCreated_date(),
            TableActions.View.toString(),
            TableActions.Delete.toString()
        };

        tmodel.insertRow(0, object);
    }

    private void populateAfterUpdating(JTable table, int selectedRow, int expenses_type_id) {

        ExpensesType expensesType = expensesTypeRepository.find(expenses_type_id);

        table.setValueAt(expensesType.getId(), selectedRow, 0);
        table.setValueAt(expensesType.getName(), selectedRow, 1);
        table.setValueAt(expensesType.getCreated_time(), selectedRow, 2);
        table.setValueAt(expensesType.getCreated_date(), selectedRow, 3);
    }

    public void deleteItem(JTable table, String expensesTypeID, int selectedRow) {
        DefaultTableModel tmodel = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(expensesTypeID);

        boolean status = expensesRepository.expenseType(id);
        if (status) {
            JOptionPane.showMessageDialog(null, "Sorry! This record cannot be deleted because expenses type is in use");
            return;
        }
        expensesTypeRepository.delete(id);
        tmodel.removeRow(selectedRow);
    }

    public void onTableClicked(
            int expenseTypeId,
            JLabel expensesTypeID,
            JTextField expenseName
    ) {
        ExpensesType expensesType = expensesTypeRepository.find(expenseTypeId);
        expensesTypeID.setText(String.valueOf(expensesType.getId()));
        expenseName.setText(expensesType.getName());
    }

    public ExpensesType getSingleExpenseType(String selected_expenses_type) {
        String expenseTypeID = helper.splitWord(selected_expenses_type, 0, "|");
        ExpensesType expensesType = expensesTypeRepository.find(Integer.parseInt(expenseTypeID.trim()));
        return expensesType;
    }

    public ExpensesType getSingleExpenseTypeID(int expense_type_id) {
        ExpensesType expensesType = expensesTypeRepository.find(expense_type_id);
        return expensesType;
    }

}
