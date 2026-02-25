package Screen;

import Components.AddButton;
import Controllers.ExpensesController;
import Dialogs.ExpensesForm;
import Helpers.ActionsColumns;
import Helpers.HelperFunctions;
import Helpers.ModelType;
import Main.Dashboard;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nyark
 */
public class ExpensesScreen extends javax.swing.JPanel {

    HelperFunctions helper = new HelperFunctions();
    ExpensesController expensesController = new ExpensesController();
    Vector searchTableVector;

    /**
     * Creates new form ExpensesScreen
     */
    public ExpensesScreen() {
        initComponents();

        this.populateData("", helper.returnDate());
        dateCurrentDate.setDate(helper.convertChooserDate(helper.returnDate()));
        this.onDateChooserAction();

        lblEndDate.setVisible(false);
        startDate.setVisible(false);
        lblStartDate.setVisible(false);

        this.sumUpTotalAmount();
    }

    private void addForm() {
        ExpensesForm expensesForm = new ExpensesForm(new Dashboard(), true);
        expensesForm.setVisible(true);

        this.sumUpTotalAmount();
    }

    private void populateData(String startDate, String endDate) {
        expensesController.populateData(expensesTable, startDate, endDate);
        helper.TableColor(expensesTable);

        new AddButton().addBtnItemsTable(expensesTable, ActionsColumns.tableActionColumn(ModelType.Expenses));
        searchTableVector = (Vector) ((DefaultTableModel) expensesTable.getModel()).getDataVector().clone();

        this.countRow();
    }

    private void searchTable(String searchItem) {
        helper.searchItem(expensesTable, searchItem, searchTableVector);
        this.countRow();
        this.sumUpTotalAmount();
    }

    public void onTableClicked() {
        int[] columns = ActionsColumns.tableActionColumn(ModelType.Expenses);
        String tableID = expensesTable.getModel().getValueAt(expensesTable.getSelectedRow(), 0).toString();
        int table_id = Integer.parseInt(tableID);

        if (expensesTable.getSelectedColumn() == columns[0]) {
            ExpensesForm expensesForm = new ExpensesForm(new Dashboard(), true);
            expensesForm.viewDetails(table_id, expensesTable.getSelectedRow());
            expensesForm.setVisible(true);
        } else if (expensesTable.getSelectedColumn() == columns[1]) {
            JLabel label = new JLabel("Are you sure you want to delete this record.");
            label.setFont(new Font("serif", Font.BOLD, 16));
            int ask = JOptionPane.showConfirmDialog(null, label, "DELETE CONFIRMATION", JOptionPane.OK_OPTION);
            if (ask == 0) {
                expensesController.deleteItem(expensesTable, tableID, expensesTable.getSelectedRow());
            }
        }

        this.countRow();
    }

    private void refresh() {
        this.populateData("", helper.returnDate());
        this.sumUpTotalAmount();
    }

    private void countRow() {
        expensesRowCount.setText(String.valueOf(expensesTable.getRowCount()));
    }

    private void onDateChooserAction() {
        dateCurrentDate.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            String startDateValue = ((JTextField) startDate.getDateEditor().getUiComponent()).getText().toLowerCase();
            String endDate = ((JTextField) dateCurrentDate.getDateEditor().getUiComponent()).getText().toLowerCase();
            if (endDate.equals("")) {
                return;
            }
            this.populateData(startDateValue, endDate);
            this.sumUpTotalAmount();
        });

        startDate.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            String startDateValue = ((JTextField) startDate.getDateEditor().getUiComponent()).getText().toLowerCase();
            String endDate = ((JTextField) dateCurrentDate.getDateEditor().getUiComponent()).getText().toLowerCase();
            if (startDateValue.endsWith("") && endDate.equals("")) {
                return;
            }
            this.populateData(startDateValue, endDate);
            this.sumUpTotalAmount();
        });
    }

    private void hideOrShowFields() {
        if (filterCheckBox.isSelected()) {
            lblEndDate.setVisible(true);
            startDate.setVisible(true);
            lblStartDate.setVisible(true);
        } else {
            lblEndDate.setVisible(false);
            startDate.setVisible(false);
            lblStartDate.setVisible(false);

            startDate.setCalendar(null);
        }
    }

    private void sumUpTotalAmount() {
        double total = helper.summationOfTableColumnReturnDouble(expensesTable, 2);

        txtTotalExpenses.setText(helper.priceToString(total));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel30 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btn_addExpenses = new javax.swing.JButton();
        txtTotalExpenses = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        lbl_SearchIcon = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        dateCurrentDate = new com.toedter.calendar.JDateChooser();
        filterCheckBox = new javax.swing.JCheckBox();
        lblStartDate = new javax.swing.JLabel();
        startDate = new com.toedter.calendar.JDateChooser();
        lblEndDate = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        expensesTable = new javax.swing.JTable();
        jPanel52 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        expensesRowCount = new javax.swing.JLabel();

        jPanel30.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/expenses.png"))); // NOI18N
        jLabel5.setText("Expenses");

        btn_addExpenses.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_addExpenses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btn_addExpenses.setText("Add");
        btn_addExpenses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addExpensesActionPerformed(evt);
            }
        });

        txtTotalExpenses.setEditable(false);
        txtTotalExpenses.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtTotalExpenses.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalExpenses.setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Total GH₵:");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_addExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btn_addExpenses, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
            .addComponent(txtTotalExpenses)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtSearch.setToolTipText("Enter to search");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lbl_SearchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ref.png"))); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        dateCurrentDate.setDateFormatString("yyyy-MM-dd");
        dateCurrentDate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        filterCheckBox.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        filterCheckBox.setText("Filter between dates");
        filterCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterCheckBoxActionPerformed(evt);
            }
        });

        lblStartDate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblStartDate.setText("Start Date:");

        startDate.setDateFormatString("yyyy-MM-dd");
        startDate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lblEndDate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEndDate.setText("Current Date:");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_SearchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEndDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateCurrentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblStartDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbl_SearchIcon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateCurrentDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(filterCheckBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        expensesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Expense Type", "Amount", "Paid To", "Created By", "Time", "Date", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        expensesTable.getTableHeader().setReorderingAllowed(false);
        expensesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                expensesTableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(expensesTable);
        if (expensesTable.getColumnModel().getColumnCount() > 0) {
            expensesTable.getColumnModel().getColumn(0).setMinWidth(0);
            expensesTable.getColumnModel().getColumn(0).setMaxWidth(0);
            expensesTable.getColumnModel().getColumn(7).setMinWidth(80);
            expensesTable.getColumnModel().getColumn(7).setMaxWidth(80);
            expensesTable.getColumnModel().getColumn(8).setMinWidth(70);
            expensesTable.getColumnModel().getColumn(8).setPreferredWidth(70);
            expensesTable.getColumnModel().getColumn(8).setMaxWidth(70);
        }

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Row Count :");

        expensesRowCount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        expensesRowCount.setText("1000");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(expensesRowCount)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
            .addComponent(expensesRowCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane7)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addExpensesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addExpensesActionPerformed
        this.addForm();
    }//GEN-LAST:event_btn_addExpensesActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        this.searchTable(txtSearch.getText());
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        this.refresh();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void expensesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expensesTableMouseClicked
        this.onTableClicked();
    }//GEN-LAST:event_expensesTableMouseClicked

    private void filterCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterCheckBoxActionPerformed
        this.hideOrShowFields();
    }//GEN-LAST:event_filterCheckBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btn_addExpenses;
    private com.toedter.calendar.JDateChooser dateCurrentDate;
    public static javax.swing.JLabel expensesRowCount;
    public static javax.swing.JTable expensesTable;
    private javax.swing.JCheckBox filterCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lbl_SearchIcon;
    private com.toedter.calendar.JDateChooser startDate;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotalExpenses;
    // End of variables declaration//GEN-END:variables
}
