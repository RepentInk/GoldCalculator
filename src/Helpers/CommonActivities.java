package Helpers;

import Components.AddButton;
import Dialogs.UserForm;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author nyark
 */
public class CommonActivities {

    HelperFunctions helper = new HelperFunctions();

    public CommonActivities() {
    }

    public void generalModalActivaties(JTable table, ModelType modelsTypes, String searchDate) {
        switch (modelsTypes.toString()) {
            case "ProductGroup":
                // productGroupController.populateTable(table)
                break;
            default:
                throw new AssertionError();
        }

        helper.TableColor(table);
        new AddButton().addBtnItemsTable(table, ActionsColumns.tableActionColumn(modelsTypes));
    }

    public void openModalDialogs(JFrame frame, ModelType modelsTypes, String tableID, int selectedRow) {
        switch (modelsTypes.toString()) {
            case "Users":
                UserForm usersForm = new UserForm(frame, true);
                if (tableID != null) {
                    // usersForm.viewAccount(tableID, selectedRow);
                }
                usersForm.setVisible(true);
                break;
            default:
                throw new AssertionError();
        }
    }

    public void tableClickAction(JFrame frame, JTable table, ModelType modelsTypes, JLabel rowCount) {
        int[] actionColumns = ActionsColumns.tableActionColumn(modelsTypes);
        String tableID = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();

        if (table.getSelectedColumn() == actionColumns[0]) {
            openModalDialogs(frame, modelsTypes, tableID, table.getSelectedRow());
        } else if (table.getSelectedColumn() == actionColumns[1]) {
            int ask = JOptionPane.showConfirmDialog(null, this.alertMessage(), "DELETE RECORDS", JOptionPane.YES_NO_OPTION);
            if (ask == 0) {
                this.deleteTableActions(table, tableID, modelsTypes);
            }
        } else if (table.getSelectedColumn() == actionColumns[2]) {

        }
    }

    private String alertMessage() {
        return "Do you want to remove record?";
    }

    private void deleteTableActions(JTable table, String tableID, ModelType modelsTypes) {
        switch (modelsTypes.toString()) {
            case "Users":
                // accountController.deleteItem(table, tableID, table.getSelectedRow());
                break;
            default:
                throw new AssertionError();
        }
    }
}
