package Forms;

import Queries.Connect;
import Queries.CustomerQuery;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CustomerForm extends java.awt.Dialog {

    Connect mets = new Connect();
    CustomerQuery cusQ = new CustomerQuery();

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public CustomerForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        conn = Connect.ConnecrDb();
        setIconImage(mets.myImage("/Images/coin.png"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txt_fullname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_hometown = new javax.swing.JTextField();
        txt_contact = new javax.swing.JFormattedTextField();
        save_customer = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cmd_type = new javax.swing.JComboBox<>();

        setFont(new java.awt.Font("DialogInput", 0, 12)); // NOI18N
        setMinimumSize(new java.awt.Dimension(200, 150));
        setResizable(false);
        setTitle("REGISTERING CUSTOMER FORM");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(248, 203, 117));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CUSTOMER REGISTER FORM");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 51));
        jLabel2.setText("Enter Fullname");

        txt_fullname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_fullname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fullnameKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 0, 51));
        jLabel3.setText("Enter Contact");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 0, 51));
        jLabel4.setText("Enter Location");

        txt_hometown.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_hometown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_hometownKeyPressed(evt);
            }
        });

        try {
            txt_contact.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_contact.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_contact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_contactKeyPressed(evt);
            }
        });

        save_customer.setBackground(new java.awt.Color(248, 203, 117));
        save_customer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        save_customer.setToolTipText("Click to save");
        save_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_customerActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 0, 51));
        jLabel5.setText("Type");

        cmd_type.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmd_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type", "BB", "BS" }));
        cmd_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_typeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(save_customer))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_fullname)
                            .addComponent(txt_hometown)
                            .addComponent(txt_contact)
                            .addComponent(cmd_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmd_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_hometown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(save_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void save_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_customerActionPerformed
        cusQ.saveCustomer(txt_fullname, txt_contact, txt_hometown, cmd_type);
        cusQ.setCustomerToCombo(HomeForm.cmd_customer, "Select Customer");
    }//GEN-LAST:event_save_customerActionPerformed

    private void txt_fullnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fullnameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txt_contact.requestFocusInWindow();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            cmd_type.requestFocusInWindow();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_contact.requestFocusInWindow();
        }
    }//GEN-LAST:event_txt_fullnameKeyPressed

    private void cmd_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_typeActionPerformed
        txt_fullname.requestFocusInWindow();
    }//GEN-LAST:event_cmd_typeActionPerformed

    private void txt_contactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contactKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txt_hometown.requestFocusInWindow();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txt_fullname.requestFocusInWindow();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_hometown.requestFocusInWindow();
        }
    }//GEN-LAST:event_txt_contactKeyPressed

    private void txt_hometownKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hometownKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            save_customer.requestFocusInWindow();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txt_contact.requestFocusInWindow();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            save_customer.requestFocusInWindow();
        }
    }//GEN-LAST:event_txt_hometownKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CustomerForm dialog = new CustomerForm(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmd_type;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton save_customer;
    private javax.swing.JFormattedTextField txt_contact;
    private javax.swing.JTextField txt_fullname;
    private javax.swing.JTextField txt_hometown;
    // End of variables declaration//GEN-END:variables
}