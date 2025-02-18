package Dialogs;

import Controllers.BuyGoldController;
import Controllers.CustomerController;
import Helpers.GoldCalculation;
import Helpers.HelperFunctions;
import Screen.BuyGoldScreen;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author nyark
 */
public class BuyGoldForm extends javax.swing.JDialog {

    CustomerController customerController = new CustomerController();
    GoldCalculation goldCalculation = new GoldCalculation();
    HelperFunctions helper = new HelperFunctions();
    BuyGoldController buyGoldController = new BuyGoldController();
    private int selectedRow;

    /**
     * Creates new form BuyGoldForm
     *
     * @param parent
     * @param modal
     */
    public BuyGoldForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(helper.setIcon(helper.iconImagePath()));
        this.populateCustomers();
        lblBuyGoldID.setVisible(false);

        lblBuyGoldCode.setText(buyGoldController.generateCode());
    }

    private void populateCustomers() {
        customerController.populateDropdownData(cmbCustomer, "Select customer");
    }

    private void calculatePounds() {
        if (txtTop.getText().isEmpty()) {
            getToolkit().beep();
            this.clearFields();
            return;
        }

        double top = txtTop.getText().isEmpty() ? 0 : Double.parseDouble(txtTop.getText());
        double poundsValue = 0;
        if (top > 0) {
            poundsValue = goldCalculation.poundsCalculation(top);
        }

        txtPounds.setText(String.valueOf(helper.priceToStringWithoutRoundUp(poundsValue)));
    }

    private void calculateGoldWeight() {
        if (txtTop.getText().isEmpty() || txtDown.getText().isEmpty()) {
            getToolkit().beep();
            return;
        }

        double top = txtTop.getText().isEmpty() ? 0 : Double.parseDouble(txtTop.getText());
        double down = txtDown.getText().isEmpty() ? 0 : Double.parseDouble(txtDown.getText());

        double density = 0, karat = 0;
        if (top > 0 && down > 0) {
            density = goldCalculation.densityCalculation(top, down);
            karat = goldCalculation.karatCalculation(density);
        }

        txtDensity.setText(String.valueOf(helper.priceToStringWithoutRoundUp(density)));
        txtKarat.setText(String.valueOf(helper.priceToStringWithoutRoundUp(karat)));
    }

    private void calculateTotalAmount() {
        if (txtPounds.getText().isEmpty() && txtKarat.getText().isEmpty() && txtBasePrice.getText().isEmpty()) {
            getToolkit().beep();
            return;
        }

        double pounds = txtPounds.getText().isEmpty() ? 0 : Double.parseDouble(txtPounds.getText());
        double karat = txtKarat.getText().isEmpty() ? 0 : Double.parseDouble(txtKarat.getText());
        double price = txtBasePrice.getText().isEmpty() ? 0 : Double.parseDouble(txtBasePrice.getText());

        double amount = 0, basePrice = 0;
        if (pounds > 0 && karat > 0) {
            amount = goldCalculation.amountCalculation(price, pounds, karat);
            basePrice = goldCalculation.basePriceCalculation(amount, pounds);
        }

        txtTotalAmount.setText(String.valueOf(helper.priceToStringWithoutRoundUp(amount)));
        txtValue.setText(String.valueOf(helper.priceToStringWithoutRoundUp(basePrice)));
    }

    private void clearFields() {
        txtDown.setText("");
        txtPounds.setText(String.valueOf(helper.priceToString(0)));
        txtDensity.setText(String.valueOf(helper.priceToString(0)));
        txtKarat.setText(String.valueOf(helper.priceToString(0)));
        txtValue.setText(String.valueOf(helper.priceToString(0)));
        txtTotalAmount.setText(String.valueOf(helper.priceToString(0)));
    }

    private void saveData() {
        if (!this.checkFields()) {
            return;
        }

        buyGoldController.saveUpdate(
                lblBuyGoldID,
                lblBuyGoldCode,
                cmbCustomer,
                txtTop,
                txtDown,
                txtPounds,
                txtDensity,
                txtKarat,
                txtValue,
                txtBasePrice,
                txtTotalAmount,
                BuyGoldScreen.buyGoldTable,
                this.selectedRow,
                this
        );

    }

    public void viewDetails(int rowId, int selectedRow) {
        btnSave.setEnabled(false);
        
        buyGoldController.onTableClicked(
                rowId,
                lblBuyGoldID,
                lblBuyGoldCode,
                cmbCustomer,
                txtTop,
                txtDown,
                txtPounds,
                txtDensity,
                txtKarat,
                txtValue,
                txtBasePrice,
                txtTotalAmount
        );
    }

    private boolean checkFields() {
        String message = "";

        if (cmbCustomer.getSelectedIndex() == 0) {
            message = message + "Customer name is required \n";
        }

        if (txtTop.getText().isEmpty()) {
            message = message + "Top value is required \n";
        }

        if (txtDown.getText().isEmpty()) {
            message = message + "Down value is required \n";
        }

        if (txtPounds.getText().isEmpty()) {
            message = message + "Pounds value is required \n";
        }

        if (txtDensity.getText().isEmpty()) {
            message = message + "Density value is required \n";
        }

        if (txtKarat.getText().isEmpty()) {
            message = message + "Karat value is required \n";
        }

        if (txtValue.getText().isEmpty()) {
            message = message + "Value is required \n";
        }

        if (txtTotalAmount.getText().isEmpty()) {
            message = message + "Total amount is required \n";
        }

        if (message.length() > 0) {
            JOptionPane.showMessageDialog(this, message, "Form Validation", 0);
        }

        return message.length() <= 0;
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
        cmbCustomer = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtTop = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDown = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPounds = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDensity = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtKarat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtValue = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtBasePrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        lblBuyGoldID = new javax.swing.JLabel();
        lblBuyGoldCode = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GOLD WEIGHT CALCULATION FORM");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Customer");

        cmbCustomer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Top:");

        txtTop.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTopKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTopKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Down:");

        txtDown.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDownKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDownKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Pounds:");

        txtPounds.setEditable(false);
        txtPounds.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPounds.setFocusable(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Density:");

        txtDensity.setEditable(false);
        txtDensity.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDensity.setFocusable(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Karat:");

        txtKarat.setEditable(false);
        txtKarat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtKarat.setFocusable(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Base Price:");

        txtValue.setEditable(false);
        txtValue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtValue.setFocusable(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Price");

        txtBasePrice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtBasePrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBasePriceKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Amount");

        txtTotalAmount.setEditable(false);
        txtTotalAmount.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTotalAmount.setForeground(new java.awt.Color(0, 0, 204));
        txtTotalAmount.setFocusable(false);

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblBuyGoldCode.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCustomer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTop, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDown, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPounds, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDensity, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKarat, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBasePrice)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalAmount)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblBuyGoldID, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBuyGoldCode, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBuyGoldCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPounds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDensity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtKarat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBasePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblBuyGoldID, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTopKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTopKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_ENTER))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTopKeyTyped

    private void txtDownKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDownKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_ENTER))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtDownKeyTyped

    private void txtTopKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTopKeyReleased
        this.calculatePounds();
    }//GEN-LAST:event_txtTopKeyReleased

    private void txtDownKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDownKeyReleased
        this.calculateGoldWeight();
    }//GEN-LAST:event_txtDownKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        this.saveData();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtBasePriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBasePriceKeyReleased
        this.calculateTotalAmount();
    }//GEN-LAST:event_txtBasePriceKeyReleased

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
            java.util.logging.Logger.getLogger(BuyGoldForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuyGoldForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuyGoldForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuyGoldForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            BuyGoldForm dialog = new BuyGoldForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblBuyGoldCode;
    private javax.swing.JLabel lblBuyGoldID;
    private javax.swing.JTextField txtBasePrice;
    private javax.swing.JTextField txtDensity;
    private javax.swing.JTextField txtDown;
    private javax.swing.JTextField txtKarat;
    private javax.swing.JTextField txtPounds;
    private javax.swing.JTextField txtTop;
    private javax.swing.JTextField txtTotalAmount;
    private javax.swing.JTextField txtValue;
    // End of variables declaration//GEN-END:variables
}
