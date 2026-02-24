package Screen;

import Components.AddButton;
import Controllers.PaymentController;
import Dialogs.PaymentsForm;
import Helpers.ActionsColumns;
import Helpers.HelperFunctions;
import Helpers.ModelType;
import Helpers.Report;
import Main.Dashboard;
import Models.Receipt;
import java.beans.PropertyChangeEvent;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nyark
 */
public class PaymentsScreen extends javax.swing.JPanel {

    Vector searchTableVector;
    HelperFunctions helper = new HelperFunctions();
    PaymentController paymentController = new PaymentController();
    Report report = new Report();

    /**
     * Creates new form DailyBudgetScreen
     */
    public PaymentsScreen() {
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
        String currentDate = ((JTextField) dateCurrentDate.getDateEditor().getUiComponent()).getText().toLowerCase();
        PaymentsForm paymentsForm = new PaymentsForm(new Dashboard(), true);
        paymentsForm.populateData(currentDate);
        paymentsForm.setVisible(true);

        this.sumUpTotalAmount();
    }

    private void populateData(String startDate, String endDate) {
        paymentController.populateTable(paymentsTable, startDate, endDate);
        helper.TableColor(paymentsTable);

        new AddButton().addBtnItemsTable(paymentsTable, ActionsColumns.tableActionColumn(ModelType.Payments));
        searchTableVector = (Vector) ((DefaultTableModel) paymentsTable.getModel()).getDataVector().clone();
        this.countRow();
    }

    private void searchTable(String searchItem) {
        helper.searchItem(paymentsTable, searchItem, searchTableVector);
        this.countRow();
    }

    public void onTableClicked() {
        int[] columns = ActionsColumns.tableActionColumn(ModelType.Payments);
        String tableID = paymentsTable.getModel().getValueAt(paymentsTable.getSelectedRow(), 0).toString();
        int table_id = Integer.parseInt(tableID);
        String currentDate = ((JTextField) dateCurrentDate.getDateEditor().getUiComponent()).getText().toLowerCase();

        if (paymentsTable.getSelectedColumn() == columns[0]) {
            PaymentsForm paymentsForm = new PaymentsForm(new Dashboard(), true);
            paymentsForm.viewDetails(table_id, paymentsTable.getSelectedRow(), currentDate);
            paymentsForm.setVisible(true);
        } else if (paymentsTable.getSelectedColumn() == columns[1]) {
            String sql = report.receiptData(table_id);
            Receipt receipt = paymentController.getSinglePayment(table_id);
            report.paymentReceiptPrint(sql, receipt);
        } else if (paymentsTable.getSelectedColumn() == columns[2]) {
            int ask = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this record?", "DELETE RECORDS", JOptionPane.YES_NO_OPTION);
            if (ask == 0) {
                paymentController.deleteItem(paymentsTable, tableID, paymentsTable.getSelectedRow());
            }
        }

        this.countRow();
        this.sumUpTotalAmount();
    }

    private void refresh() {
        this.populateData("", helper.returnDate());
        this.sumUpTotalAmount();
    }

    private void countRow() {
        buyGoldRowCount.setText(String.valueOf(paymentsTable.getRowCount()));
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
        double total = helper.summationOfTableColumnReturnDouble(paymentsTable, 4);

        txtTotalAmountPaid.setText(helper.priceToString(total));
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
        btn_addUser = new javax.swing.JButton();
        txtTotalAmountPaid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        lbl_SearchIcon1 = new javax.swing.JLabel();
        btnRefresh1 = new javax.swing.JButton();
        dateCurrentDate = new com.toedter.calendar.JDateChooser();
        filterCheckBox = new javax.swing.JCheckBox();
        lblStartDate = new javax.swing.JLabel();
        startDate = new com.toedter.calendar.JDateChooser();
        lblEndDate = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        paymentsTable = new javax.swing.JTable();
        jPanel52 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        buyGoldRowCount = new javax.swing.JLabel();

        setToolTipText("");

        jPanel30.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/payment.png"))); // NOI18N
        jLabel5.setText("Payments");

        btn_addUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_addUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btn_addUser.setText("Add");
        btn_addUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addUserActionPerformed(evt);
            }
        });

        txtTotalAmountPaid.setEditable(false);
        txtTotalAmountPaid.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtTotalAmountPaid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalAmountPaid.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTotalAmountPaid.setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Amount Paid Total GH₵:");

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
                .addComponent(txtTotalAmountPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_addUser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btn_addUser, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
            .addComponent(txtTotalAmountPaid)
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

        lbl_SearchIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N

        btnRefresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ref.png"))); // NOI18N
        btnRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh1ActionPerformed(evt);
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
                .addComponent(lbl_SearchIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(dateCurrentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_SearchIcon1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateCurrentDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        paymentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Code", "Customer", "Total Amount", "Amount Paid", "Balance", "Created By", "Time", "Date", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        paymentsTable.getTableHeader().setReorderingAllowed(false);
        paymentsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentsTableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(paymentsTable);
        if (paymentsTable.getColumnModel().getColumnCount() > 0) {
            paymentsTable.getColumnModel().getColumn(0).setMinWidth(0);
            paymentsTable.getColumnModel().getColumn(0).setMaxWidth(0);
            paymentsTable.getColumnModel().getColumn(1).setMinWidth(70);
            paymentsTable.getColumnModel().getColumn(1).setMaxWidth(70);
            paymentsTable.getColumnModel().getColumn(7).setMinWidth(90);
            paymentsTable.getColumnModel().getColumn(7).setMaxWidth(90);
            paymentsTable.getColumnModel().getColumn(8).setMinWidth(90);
            paymentsTable.getColumnModel().getColumn(8).setMaxWidth(90);
            paymentsTable.getColumnModel().getColumn(9).setMinWidth(60);
            paymentsTable.getColumnModel().getColumn(9).setMaxWidth(60);
            paymentsTable.getColumnModel().getColumn(10).setMinWidth(60);
            paymentsTable.getColumnModel().getColumn(10).setMaxWidth(60);
            paymentsTable.getColumnModel().getColumn(11).setMinWidth(70);
            paymentsTable.getColumnModel().getColumn(11).setPreferredWidth(70);
            paymentsTable.getColumnModel().getColumn(11).setMaxWidth(70);
        }

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Row Count :");

        buyGoldRowCount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buyGoldRowCount.setText("1000");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buyGoldRowCount)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
            .addComponent(buyGoldRowCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addUserActionPerformed
        this.addForm();
    }//GEN-LAST:event_btn_addUserActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        this.searchTable(txtSearch.getText());
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        this.refresh();
    }//GEN-LAST:event_btnRefresh1ActionPerformed

    private void paymentsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentsTableMouseClicked
        this.onTableClicked();
    }//GEN-LAST:event_paymentsTableMouseClicked

    private void filterCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterCheckBoxActionPerformed
        this.hideOrShowFields();
    }//GEN-LAST:event_filterCheckBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btn_addUser;
    public static javax.swing.JLabel buyGoldRowCount;
    private com.toedter.calendar.JDateChooser dateCurrentDate;
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
    private javax.swing.JLabel lbl_SearchIcon1;
    public static javax.swing.JTable paymentsTable;
    private com.toedter.calendar.JDateChooser startDate;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotalAmountPaid;
    // End of variables declaration//GEN-END:variables
}
