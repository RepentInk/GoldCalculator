package Dialogs;

import Controllers.PaymentController;
import Helpers.HelperFunctions;
import Screen.PaymentsScreen;
import javax.swing.JOptionPane;

/**
 *
 * @author nyark
 */
public class PaymentsForm extends javax.swing.JDialog {

    PaymentController paymentController = new PaymentController();
    HelperFunctions helper = new HelperFunctions();
    private int selectedRow;

    /**
     * Creates new form PaymentsForm
     *
     * @param parent
     * @param modal
     */
    public PaymentsForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(helper.setIcon(helper.iconImagePath()));
        lblPaymentID.setVisible(false);
    }

    public void populateData(String createdDate) {
        paymentController.populateDropDownData(cmdGoldPurchase, cmbBudget, createdDate);
        lblSelectedYear.setText(createdDate);
        btnSave.setEnabled(true);
    }

    private void purchasePayments() {
        if (cmdGoldPurchase.getSelectedIndex() == 0) {
            return;
        }

        paymentController.setPurchaseDetails(
                cmdGoldPurchase.getSelectedItem().toString(),
                txtPurchaseAmount,
                txtTotalAmountPaid,
                txtRemainAmount
        );
    }

    private void budgetData() {
        if (cmbBudget.getSelectedIndex() == 0) {
            return;
        }

        paymentController.setBudgetDetails(
                cmbBudget.getSelectedItem().toString(),
                txtAmountBeforePayment
        );
    }

    private void saveData() {
        if (!this.checkFields()) {
            return;
        }

        paymentController.saveUpdate(
                lblPaymentID,
                cmdGoldPurchase,
                txtTotalAmountPaying,
                txtBalance,
                cmbBudget,
                txtAmountBeforePayment,
                txtAmountAfterPayment,
                PaymentsScreen.paymentsTable,
                this.selectedRow,
                this
        );
    }

    private boolean checkFields() {
        String message = "";

        if (cmdGoldPurchase.getSelectedIndex() == 0) {
            message = message + "Purchase name is required \n";
        }

        if (cmbBudget.getSelectedIndex() == 0) {
            message = message + "Select budget to pay from \n";
        }

        if (txtTotalAmountPaying.getText().isEmpty()) {
            message = message + "Amount paying is required \n";
        }

        if (txtBalance.getText().isEmpty()) {
            message = message + "Balance is required \n";
        }

        if (txtAmountBeforePayment.getText().isEmpty()) {
            message = message + "Budget amount before payment is required \n";
        }

        if (txtAmountAfterPayment.getText().isEmpty()) {
            message = message + "Budget amount after payment value is required \n";
        }

        if (message.length() > 0) {
            JOptionPane.showMessageDialog(this, message, "Form Validation", 0);
        }

        return message.length() <= 0;
    }

    private void calculatePaymentBalance() {
        if (txtTotalAmountPaying.getText().isEmpty()) {
            getToolkit().beep();
            return;
        }

        double amount_paying = txtTotalAmountPaying.getText().isEmpty() ? 0 : Double.parseDouble(txtTotalAmountPaying.getText());
        double amount_remain = txtRemainAmount.getText().isEmpty() ? 0 : helper.parseAmountWithComma(txtRemainAmount.getText());
        double budget_before_payment = txtAmountBeforePayment.getText().isEmpty() ? 0 : helper.parseAmountWithComma(txtAmountBeforePayment.getText());

        double budget_after_payment = 0, balance = 0;
        if (amount_paying <= 0) {
            return;
        }

        if (amount_paying > budget_before_payment) {
            JOptionPane.showMessageDialog(
                    null,
                    "Sorry! Amount paying cannot be more than budget remain amount",
                    "PAYMENT AMOUNT LIMIT",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        if (amount_paying > amount_remain) {
            JOptionPane.showMessageDialog(
                    null,
                    "Sorry! Amount paying cannot be more than purchase amount remaining",
                    "PAYMENT AMOUNT LIMIT",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        budget_after_payment = budget_before_payment - amount_paying;
        balance = amount_remain - amount_paying;

        txtAmountAfterPayment.setText(String.valueOf(helper.priceToString(budget_after_payment)));
        txtBalance.setText(String.valueOf(helper.priceToString(balance)));
    }

    public void viewDetails(int rowID, int selectedRow, String createdDate) {
        this.populateData(createdDate);

        paymentController.onTableClicked(
                rowID,
                lblPaymentID,
                cmdGoldPurchase,
                txtPurchaseAmount,
                txtTotalAmountPaid,
                txtRemainAmount,
                cmbBudget,
                txtAmountBeforePayment,
                txtTotalAmountPaying,
                txtBalance,
                txtAmountAfterPayment,
                selectedRow
        );

        btnSave.setEnabled(false);
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
        cmdGoldPurchase = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPurchaseAmount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbBudget = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtAmountBeforePayment = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTotalAmountPaying = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtBalance = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAmountAfterPayment = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        lblSelectedYear = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTotalAmountPaid = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtRemainAmount = new javax.swing.JTextField();
        lblPaymentID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PAYMENTS FORM");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Purchases");

        cmdGoldPurchase.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmdGoldPurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGoldPurchaseActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Purchase Amount");

        txtPurchaseAmount.setEditable(false);
        txtPurchaseAmount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPurchaseAmount.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Budget");

        cmbBudget.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbBudget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBudgetActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Budget Amount Before Payment");

        txtAmountBeforePayment.setEditable(false);
        txtAmountBeforePayment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtAmountBeforePayment.setFocusable(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Amount Paying");

        txtTotalAmountPaying.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTotalAmountPaying.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalAmountPayingKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Balance");

        txtBalance.setEditable(false);
        txtBalance.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBalance.setFocusable(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Budget Amount After Payment");

        txtAmountAfterPayment.setEditable(false);
        txtAmountAfterPayment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtAmountAfterPayment.setFocusable(false);

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblSelectedYear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSelectedYear.setForeground(new java.awt.Color(255, 0, 0));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Amount Paid");

        txtTotalAmountPaid.setEditable(false);
        txtTotalAmountPaid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTotalAmountPaid.setFocusable(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Remaining Amount");

        txtRemainAmount.setEditable(false);
        txtRemainAmount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtRemainAmount.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdGoldPurchase, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPurchaseAmount)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbBudget, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                    .addComponent(txtAmountBeforePayment)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAmountAfterPayment)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSelectedYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalAmountPaying))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBalance, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalAmountPaid))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRemainAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblPaymentID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblSelectedYear, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(cmdGoldPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPurchaseAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalAmountPaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRemainAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbBudget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAmountBeforePayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalAmountPaying, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAmountAfterPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPaymentID, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdGoldPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGoldPurchaseActionPerformed
        this.purchasePayments();
    }//GEN-LAST:event_cmdGoldPurchaseActionPerformed

    private void cmbBudgetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBudgetActionPerformed
        this.budgetData();
    }//GEN-LAST:event_cmbBudgetActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int ask = JOptionPane.showConfirmDialog(null, "Are you sure you want to save this record?, record cannot be deleted", "DELETE RECORDS", JOptionPane.YES_NO_OPTION);
        if (ask == 0) {
            this.saveData();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtTotalAmountPayingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalAmountPayingKeyReleased
        this.calculatePaymentBalance();
    }//GEN-LAST:event_txtTotalAmountPayingKeyReleased

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
            java.util.logging.Logger.getLogger(PaymentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            PaymentsForm dialog = new PaymentsForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cmbBudget;
    private javax.swing.JComboBox<String> cmdGoldPurchase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblPaymentID;
    private javax.swing.JLabel lblSelectedYear;
    private javax.swing.JTextField txtAmountAfterPayment;
    private javax.swing.JTextField txtAmountBeforePayment;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtPurchaseAmount;
    private javax.swing.JTextField txtRemainAmount;
    private javax.swing.JTextField txtTotalAmountPaid;
    private javax.swing.JTextField txtTotalAmountPaying;
    // End of variables declaration//GEN-END:variables
}
