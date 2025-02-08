package Screen;

import Components.AddButton;
import Controllers.BuyGoldController;
import Dialogs.BuyGoldForm;
import Helpers.ActionsColumns;
import Helpers.HelperFunctions;
import Helpers.ModelType;
import Helpers.ShopData;
import Main.Dashboard;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nyark
 */
public class BuyGoldScreen extends javax.swing.JPanel {
    
    Vector searchTableVector;
    HelperFunctions helper = new HelperFunctions();
    BuyGoldController buyGoldController = new BuyGoldController();

    /**
     * Creates new form DailyBudgetScreen
     */
    public BuyGoldScreen() {
        initComponents();
        
        this.populateYears();
        this.populateData(helper.returnCurrentYear());
        cmbYears.setSelectedItem(helper.returnCurrentYear());
    }
    
    private void addForm() {
        BuyGoldForm buyGoldForm = new BuyGoldForm(new Dashboard(), true);
        buyGoldForm.setVisible(true);
    }
    
    private void populateYears() {
        ArrayList<String> years = helper.getYearList(ShopData.getYearLimit());
        
        cmbYears.addItem("Years");
        
        for (int year = 0; year < years.size(); year++) {
            cmbYears.addItem(years.get(year));
        }
    }
    
    private void populateData(String year) {
        buyGoldController.populateData(buyGoldTable, year);
        helper.TableColor(buyGoldTable);
        
        new AddButton().addBtnItemsTable(buyGoldTable, ActionsColumns.tableActionColumn(ModelType.BuyGold));
        searchTableVector = (Vector) ((DefaultTableModel) buyGoldTable.getModel()).getDataVector().clone();
        
        this.countRow();
    }
    
    private void searchTable(String searchItem) {
        helper.searchItem(buyGoldTable, searchItem, searchTableVector);
        this.countRow();
    }
    
    public void onTableClicked() {
        int[] columns = ActionsColumns.tableActionColumn(ModelType.BuyGold);
        String tableID = buyGoldTable.getModel().getValueAt(buyGoldTable.getSelectedRow(), 0).toString();
        
        if (buyGoldTable.getSelectedColumn() == columns[0]) {
            int table_id = Integer.parseInt(tableID);
            BuyGoldForm buyGoldForm = new BuyGoldForm(new Dashboard(), true);
            buyGoldForm.viewDetails(table_id, buyGoldTable.getSelectedRow());
            buyGoldForm.setVisible(true);
        } else if (buyGoldTable.getSelectedColumn() == columns[1]) {
            int ask = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this record?", "DELETE RECORDS", JOptionPane.YES_NO_OPTION);
            if (ask == 0) {
                buyGoldController.deleteItem(buyGoldTable, tableID, buyGoldTable.getSelectedRow());
            }
        }
        
        this.countRow();
    }
    
    private void refresh() {
        this.populateData(helper.returnCurrentYear());
    }
    
    private void countRow() {
        buyGoldRowCount.setText(String.valueOf(buyGoldTable.getRowCount()));
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
        jPanel37 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        lbl_SearchIcon1 = new javax.swing.JLabel();
        btnRefresh1 = new javax.swing.JButton();
        cmbYears = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        buyGoldTable = new javax.swing.JTable();
        jPanel52 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        buyGoldRowCount = new javax.swing.JLabel();

        setToolTipText("");

        jPanel30.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/users.png"))); // NOI18N
        jLabel5.setText("Buy Gold");

        btn_addUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_addUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btn_addUser.setText("Add");
        btn_addUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 518, Short.MAX_VALUE)
                .addComponent(btn_addUser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btn_addUser, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
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

        cmbYears.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cmbYears.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbYearsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_SearchIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbYears, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbl_SearchIcon1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbYears))
                .addGap(0, 3, Short.MAX_VALUE))
        );

        buyGoldTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Code", "Customer", "Top", "Down", "Pounds", "Density", "Karat", "Value", "Base Price", "Total Amount", "Created By", "Date", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        buyGoldTable.getTableHeader().setReorderingAllowed(false);
        buyGoldTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buyGoldTableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(buyGoldTable);
        if (buyGoldTable.getColumnModel().getColumnCount() > 0) {
            buyGoldTable.getColumnModel().getColumn(0).setMinWidth(0);
            buyGoldTable.getColumnModel().getColumn(0).setMaxWidth(0);
            buyGoldTable.getColumnModel().getColumn(1).setMinWidth(60);
            buyGoldTable.getColumnModel().getColumn(1).setMaxWidth(60);
            buyGoldTable.getColumnModel().getColumn(3).setMinWidth(60);
            buyGoldTable.getColumnModel().getColumn(3).setMaxWidth(60);
            buyGoldTable.getColumnModel().getColumn(4).setMinWidth(60);
            buyGoldTable.getColumnModel().getColumn(4).setMaxWidth(60);
            buyGoldTable.getColumnModel().getColumn(12).setMinWidth(90);
            buyGoldTable.getColumnModel().getColumn(12).setMaxWidth(90);
            buyGoldTable.getColumnModel().getColumn(13).setMinWidth(60);
            buyGoldTable.getColumnModel().getColumn(13).setMaxWidth(60);
            buyGoldTable.getColumnModel().getColumn(14).setMinWidth(60);
            buyGoldTable.getColumnModel().getColumn(14).setMaxWidth(60);
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
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
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

    private void buyGoldTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buyGoldTableMouseClicked
        this.onTableClicked();
    }//GEN-LAST:event_buyGoldTableMouseClicked

    private void cmbYearsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbYearsActionPerformed
        if (cmbYears.getSelectedIndex() <= 0) {
            return;
        }
        
        this.populateData(cmbYears.getSelectedItem().toString());
    }//GEN-LAST:event_cmbYearsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btn_addUser;
    public static javax.swing.JLabel buyGoldRowCount;
    public static javax.swing.JTable buyGoldTable;
    private javax.swing.JComboBox<String> cmbYears;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbl_SearchIcon1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
