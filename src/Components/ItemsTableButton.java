package Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Repent
 */
public class ItemsTableButton extends JButton implements TableCellRenderer {

    public ItemsTableButton() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }

    public class TableButtonEditor extends DefaultCellEditor {

        protected JButton btn;
        private String lbl;

        public TableButtonEditor(JTextField textField) {
            super(textField);

            btn = new JButton();

            btn.addActionListener((ActionEvent e) -> {
                fireEditingStopped();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            lbl = (value == null) ? "Add" : value.toString();
            btn.setText(lbl);
            btn.setForeground(Color.CYAN);
            btn.setFont(new Font("Serif", Font.BOLD, 12));

            return btn;
        }

        @Override
        public Object getCellEditorValue() {
            return lbl;
        }

        @Override
        public boolean stopCellEditing() {
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped(); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
