package Dialogs;

import Controllers.BudgetController;
import Helpers.HelperFunctions;
import Screen.DailyBudgetScreen;
import java.awt.event.KeyEvent;
import java.time.Month;
import javax.swing.JOptionPane;

/**
 *
 * @author nyark
 */
public class BudgetForm extends javax.swing.JDialog {

    BudgetController budgetController = new BudgetController();
    HelperFunctions helper = new HelperFunctions();
    private int selectedRow;

    /**
     * Creates new form BudgetForm
     *
     * @param parent
     * @param modal
     */
    public BudgetForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(helper.setIcon(helper.iconImagePath()));
        lblBudgetID.setVisible(false);

        txtBudgetName.setText(Month.of(Integer.parseInt(helper.returnCurrentMonth())) + " " + helper.returnCurrentDay());
        this.setPreviousBalance();
    }

    private void setPreviousBalance() {
        budgetController.calculateAmountForward(txtBalanceCarryForward);
    }

    private void saveData() {
        if (!this.checkFields()) {
            return;
        }

        budgetController.saveUpdate(
                lblBudgetID,
                txtBudgetName,
                txtTotalAmount,
                txtBalanceCarryForward,
                DailyBudgetScreen.dailyBudgetTable,
                this.selectedRow,
                this
        );

        this.clearFields();
    }

    private boolean checkFields() {
        String message = "";

        if (txtBudgetName.getText().isEmpty()) {
            message = message + "Budget name is required \n";
        }

        if (txtTotalAmount.getText().isEmpty()) {
            message = message + "Total amount is required \n";
        }

        if (message.length() > 0) {
            JOptionPane.showMessageDialog(this, message, "Form Validation", 0);
        }

        return message.length() <= 0;
    }

    public void viewDetails(int budgetID, int selectedRow) {
        this.selectedRow = selectedRow;
        btnSave.setEnabled(false);

        budgetController.onTableClick(
                budgetID,
                lblBudgetID,
                txtBudgetName,
                txtTotalAmount,
                txtAmountUsed,
                txtAmountLeft,
                txtBalanceCarryForward,
                txtTodayBudget
        );

    }

    private void clearFields() {
        lblBudgetID.setText("");
        txtBudgetName.setText("");
        txtTotalAmount.setText("");
        txtAmountUsed.setText("0");
        txtAmountLeft.setText("0");
    }

    private void calculateTotalBudgetToday() {
        if (txtTodayBudget.getText().isEmpty()) {
            getToolkit().beep();
            return;
        }

        double today_budget = txtTodayBudget.getText().isEmpty() ? 0 : Double.parseDouble(txtTodayBudget.getText());
        double budget_forward = txtBalanceCarryForward.getText().isEmpty() ? 0 : helper.parseAmountWithComma(txtBalanceCarryForward.getText());

        double total_budget = 0;
        if (today_budget <= 0) {
            return;
        }

        total_budget = today_budget + budget_forward;
        txtTotalAmount.setText(String.valueOf(helper.priceToString(total_budget)));
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
        txtBudgetName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAmountUsed = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtAmountLeft = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        lblBudgetID = new javax.swing.JLabel();
        txtBalanceCarryForward = new javax.swing.JTextField();
        txtTodayBudget = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BUDGET FORM");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Budget Name");

        txtBudgetName.setEditable(false);
        txtBudgetName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBudgetName.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Balance Carry Forward");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Today Budget");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Total Amount");

        txtTotalAmount.setEditable(false);
        txtTotalAmount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTotalAmount.setFocusable(false);
        txtTotalAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalAmountKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Amount Used");

        txtAmountUsed.setEditable(false);
        txtAmountUsed.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtAmountUsed.setText("0");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Amount Left");

        txtAmountLeft.setEditable(false);
        txtAmountLeft.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtAmountLeft.setText("0");

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        txtBalanceCarryForward.setEditable(false);
        txtBalanceCarryForward.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBalanceCarryForward.setEnabled(false);

        txtTodayBudget.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTodayBudget.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTodayBudgetKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTodayBudgetKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBudgetName)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalAmount)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAmountUsed)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAmountLeft)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBudgetID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBalanceCarryForward)
                    .addComponent(txtTodayBudget))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBudgetName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBalanceCarryForward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTodayBudget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAmountUsed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAmountLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(lblBudgetID, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalAmountKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_ENTER))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTotalAmountKeyTyped

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int ask = JOptionPane.showConfirmDialog(null, "Are you sure you want to save this record?, record cannot be deleted", "DELETE RECORDS", JOptionPane.YES_NO_OPTION);
        if (ask == 0) {
            this.saveData();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtTodayBudgetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTodayBudgetKeyReleased
        this.calculateTotalBudgetToday();
    }//GEN-LAST:event_txtTodayBudgetKeyReleased

    private void txtTodayBudgetKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTodayBudgetKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_ENTER))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTodayBudgetKeyTyped

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
            java.util.logging.Logger.getLogger(BudgetForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BudgetForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BudgetForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BudgetForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            BudgetForm dialog = new BudgetForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblBudgetID;
    private javax.swing.JTextField txtAmountLeft;
    private javax.swing.JTextField txtAmountUsed;
    private javax.swing.JTextField txtBalanceCarryForward;
    private javax.swing.JTextField txtBudgetName;
    private javax.swing.JTextField txtTodayBudget;
    private javax.swing.JTextField txtTotalAmount;
    // End of variables declaration//GEN-END:variables
}
