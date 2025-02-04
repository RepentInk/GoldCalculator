package Components;

import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Repent
 */
public class AddButton {

    // link button to tempTable
    public void addBtnTempTable(JTable table, int column[]) {
        TempTableButton temBtn = new TempTableButton();
        for (int i = 0; i < column.length; i++) {
            table.getColumnModel().getColumn(column[i]).setCellRenderer(new TempTableButton());
            table.getColumnModel().getColumn(column[i]).setCellEditor(temBtn.new TableButtonEditor(new JTextField()));
        }
    }

    // set items table button
    public void addBtnItemsTable(JTable table, int column[]) {
        ItemsTableButton temBtn = new ItemsTableButton();
        for (int i = 0; i < column.length; i++) {
            table.getColumnModel().getColumn(column[i]).setCellRenderer(new ItemsTableButton());
            table.getColumnModel().getColumn(column[i]).setCellEditor(temBtn.new TableButtonEditor(new JTextField()));
        }
    }

    // set items table button
    public void addBtnPaymentTable(JTable table, int column[]) {
        ItemsTableButton temBtn = new ItemsTableButton();
        for (int i = 0; i < column.length; i++) {
            table.getColumnModel().getColumn(column[i]).setCellRenderer(new ItemsTableButton());
            table.getColumnModel().getColumn(column[i]).setCellEditor(temBtn.new TableButtonEditor(new JTextField()));
        }
    }
}
