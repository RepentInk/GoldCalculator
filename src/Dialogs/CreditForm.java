package Dialogs;

import Controllers.BudgetController;
import Controllers.CreditController;
import Controllers.CustomerController;
import Controllers.PaymentController;
import Helpers.HelperFunctions;
import Screen.CreditScreen;
import javax.swing.JOptionPane;

/**
 *
 * @author nyark
 */
public class CreditForm extends javax.swing.JDialog {

    HelperFunctions helper = new HelperFunctions();
    CustomerController customerController = new CustomerController();
    BudgetController budgetController = new BudgetController();
    PaymentController paymentController = new PaymentController();
    CreditController creditController = new CreditController();
    private int selectedRow;

    /**
     * Creates new form CreditForm
     *
     * @param parent
     * @param modal
     */
    public CreditForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(helper.setIcon(helper.iconImagePath()));
        this.populateData();
        lblcreditID.setVisible(false);

        lblBatchCode.setText(creditController.generateCode());
    }

    private void populateData() {
        customerController.populateDropdownData(cmbCustomer, "Select customer");
        budgetController.populateDropdownData(cmdBudget, "Select Budget", helper.returnCurrentYear());
    }

    private void budgetData() {
        if (cmdBudget.getSelectedIndex() == 0) {
            return;
        }

        paymentController.setBudgetDetails(
                cmdBudget.getSelectedItem().toString(),
                txtBudgetBefore
        );
    }

    private void previousBalance() {
        if (cmbCustomer.getSelectedIndex() == 0) {
            return;
        }

        creditController.getCustomerPreviousBalance(cmbCustomer,
                txtPreviousAmount
        );
    }

    private void saveUpdate() {
        if (!this.checkFields()) {
            return;
        }

        creditController.saveUpdate(
                lblcreditID,
                lblBatchCode,
                cmbCustomer,
                cmdBudget,
                txtBudgetBefore,
                txtBudgetAfter,
                txtTotalCreditAmount,
                txtPreviousAmount,
                CreditScreen.creditTable,
                this.selectedRow,
                this
        );

    }

    public void viewDetails(int rowID, int selectedRow) {
        this.selectedRow = selectedRow;
        btnSave.setEnabled(false);

        creditController.onTableClicked(
                rowID,
                lblcreditID,
                lblBatchCode,
                cmbCustomer,
                cmdBudget,
                txtBudgetBefore,
                txtBudgetAfter,
                txtTotalCreditAmount,
                txtPreviousAmount,
                txtAmount
        );
    }

    private boolean checkFields() {
        String message = "";

        if (cmbCustomer.getSelectedIndex() == 0) {
            message = message + "Customer name is required \n";
        }

        if (cmdBudget.getSelectedIndex() == 0) {
            message = message + "Budget to pay from is required \n";
        }

        if (txtAmount.getText().isEmpty()) {
            message = message + "Total amount is required \n";
        }

        if (txtTotalCreditAmount.getText().isEmpty()) {
            message = message + "Total Credit Amount is required \n";
        }

        if (message.length() > 0) {
            JOptionPane.showMessageDialog(this, message, "Form Validation", 0);
        }

        return message.length() <= 0;
    }

    private void calculateTotalCredit() {
        if (txtAmount.getText().isEmpty()) {
            getToolkit().beep();
            return;
        }

        double amount = txtAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(txtAmount.getText());
        double previous_amount = txtPreviousAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(txtPreviousAmount.getText());
        double budget_before_payment = txtBudgetBefore.getText().isEmpty() ? 0 : helper.parseAmountWithComma(txtBudgetBefore.getText());

        double budget_after_payment = 0, total_credit = 0;
        if (amount <= 0) {
            return;
        }

        if (amount > budget_before_payment) {
            JOptionPane.showMessageDialog(
                    null,
                    "Sorry! Amount cannot be more than budget remain amount",
                    "PAYMENT AMOUNT LIMIT",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        budget_after_payment = budget_before_payment - amount;
        total_credit = amount + previous_amount;

        txtTotalCreditAmount.setText(String.valueOf(helper.priceToString(total_credit)));
        txtBudgetAfter.setText(String.valueOf(helper.priceToString(budget_after_payment)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblBatchCode = new javax.swing.JLabel();
        cmbCustomer = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmdBudget = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtPreviousAmount = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtBudgetBefore = new javax.swing.JTextField();
        txtAmount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotalCreditAmount = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtBudgetAfter = new javax.swing.JTextField();
        lblcreditID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ADD CREDIT FORM");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Cutomer");

        lblBatchCode.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        cmbCustomer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCustomerActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Budget");

        cmdBudget.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmdBudget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBudgetActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Previous Balance");

        txtPreviousAmount.setEditable(false);
        txtPreviousAmount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPreviousAmount.setFocusable(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Budget Before");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Amount");

        txtBudgetBefore.setEditable(false);
        txtBudgetBefore.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBudgetBefore.setFocusable(false);

        txtAmount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAmountKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Total Credit Amount");

        txtTotalCreditAmount.setEditable(false);
        txtTotalCreditAmount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTotalCreditAmount.setFocusable(false);

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Budget After");

        txtBudgetAfter.setEditable(false);
        txtBudgetAfter.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBudgetAfter.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblcreditID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBudgetBefore)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdBudget, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCustomer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                    .addComponent(txtPreviousAmount)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTotalCreditAmount, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBudgetAfter))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPreviousAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdBudget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBudgetBefore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalCreditAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBudgetAfter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblcreditID, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBudgetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBudgetActionPerformed
        this.budgetData();
    }//GEN-LAST:event_cmdBudgetActionPerformed

    private void cmbCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCustomerActionPerformed
        this.previousBalance();
    }//GEN-LAST:event_cmbCustomerActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int ask = JOptionPane.showConfirmDialog(null, "Are you sure you want to save this record?, record cannot be deleted", "DELETE RECORDS", JOptionPane.YES_NO_OPTION);
        if (ask == 0) {
            this.saveUpdate();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountKeyReleased
        this.calculateTotalCredit();
    }//GEN-LAST:event_txtAmountKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreditForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            CreditForm dialog = new CreditForm(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbCustomer;
    private javax.swing.JComboBox<String> cmdBudget;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblBatchCode;
    private javax.swing.JLabel lblcreditID;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtBudgetAfter;
    private javax.swing.JTextField txtBudgetBefore;
    private javax.swing.JTextField txtPreviousAmount;
    private javax.swing.JTextField txtTotalCreditAmount;
    // End of variables declaration//GEN-END:variables
}
