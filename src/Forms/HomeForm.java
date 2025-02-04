package Forms;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class HomeForm extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    DecimalFormat df2 = new DecimalFormat("####.##");

    public HomeForm() {
        initComponents();

        cus_iddd.setVisible(false);
        user_id.setVisible(false);
    }

    public double truncateTo(double unroundedNumber, int decimalPlaces) {
        int truncatedNumberInt = (int) (unroundedNumber * Math.pow(10, decimalPlaces));
        double truncatedNumber = (double) (truncatedNumberInt / Math.pow(10, decimalPlaces));
        return truncatedNumber;
    }

    public double top(double value) {
        double result = truncateTo((value / 7.75), 2);
        return result;
    }

    public double density(double top, double down) {
        double result = truncateTo((top / down), 2);
        return result;
    }

    public double karat(double density) {
        double result = Double.valueOf(df2.format(((density - 10.51) * 52.828) / density));;
        return result;
    }

    public double value(double karat, double top) {
        double value = (karat / 23) * top;
        return value;
    }

    public double money(double value, double base) {
        double money = truncateTo((value * base), 0);
        return money;
    }

    public void setTop() {
        if (txt_top.getText().isEmpty()) {
        } else {
            double topv = Double.parseDouble(txt_top.getText());
            txt_tot_top.setText("" + top(topv));
        }
    }

    public void setDown() {
        if (txt_down.getText().isEmpty() || txt_top.getText().isEmpty()) {
        } else {
            double topv = Double.parseDouble(txt_top.getText());
            double down = Double.parseDouble(txt_down.getText());
            txt_density.setText("" + density(topv, down));
            txt_karat.setText("" + karat(density(topv, down)));
            txt_value.setText("" + value(karat(density(topv, down)), topv));
        }
    }

    public void setPrice() {
        if (txt_down.getText().isEmpty() || txt_top.getText().isEmpty() || cmd_base.getSelectedIndex() <= 0) {
            //JOptionPane.showMessageDialog(null, "Please select base price");
        } else {
            double topv = Double.parseDouble(txt_top.getText());
            double down = Double.parseDouble(txt_down.getText());
            String baseP = cmd_base.getSelectedItem().toString();
            double base = Double.parseDouble(baseP);

            lbl_totalAmount.setText("" + money(value(karat(density(topv, down)), top(topv)), base));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txt_top = new javax.swing.JTextField();
        cmd_customer = new javax.swing.JComboBox<>();
        add_customer = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_tot_top = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_down = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmd_base = new javax.swing.JComboBox<>();
        add_base = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_density = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_karat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_value = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lbl_totalAmount = new javax.swing.JLabel();
        btn_save = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        cus_iddd = new javax.swing.JLabel();
        user_id = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        user = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        customer = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        price = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        exit = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("THE HOME PAGE");
        setBackground(new java.awt.Color(255, 153, 0));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(248, 203, 117));
        jPanel1.setMinimumSize(new java.awt.Dimension(343, 460));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("GOLD CALCULATOR");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 20, 320, 30);

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 58, 340, 10);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Customer");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 80, 70, 20);

        txt_top.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_topFocusLost(evt);
            }
        });
        txt_top.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_topKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_topKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_topKeyTyped(evt);
            }
        });
        jPanel1.add(txt_top);
        txt_top.setBounds(90, 130, 90, 20);

        cmd_customer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmd_customer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmd_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_customerActionPerformed(evt);
            }
        });
        jPanel1.add(cmd_customer);
        cmd_customer.setBounds(90, 80, 140, 21);

        add_customer.setBackground(new java.awt.Color(248, 203, 117));
        add_customer.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        add_customer.setText("Add New");
        add_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_customerActionPerformed(evt);
            }
        });
        jPanel1.add(add_customer);
        add_customer.setBounds(240, 80, 80, 23);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Top");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 130, 70, 20);

        txt_tot_top.setEditable(false);
        txt_tot_top.setBackground(new java.awt.Color(248, 203, 117));
        txt_tot_top.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txt_tot_top.setForeground(new java.awt.Color(255, 0, 0));
        txt_tot_top.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tot_top.setBorder(null);
        jPanel1.add(txt_tot_top);
        txt_tot_top.setBounds(260, 130, 60, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Down");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 170, 70, 20);

        txt_down.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_downKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_downKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_downKeyTyped(evt);
            }
        });
        jPanel1.add(txt_down);
        txt_down.setBounds(90, 170, 90, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Base Price");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 230, 70, 17);

        cmd_base.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmd_base.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_baseActionPerformed(evt);
            }
        });
        jPanel1.add(cmd_base);
        cmd_base.setBounds(90, 230, 90, 20);

        add_base.setBackground(new java.awt.Color(248, 203, 117));
        add_base.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        add_base.setText("Add New");
        add_base.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_baseActionPerformed(evt);
            }
        });
        jPanel1.add(add_base);
        add_base.setBounds(190, 230, 80, 23);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Density");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(190, 170, 60, 20);

        txt_density.setEditable(false);
        txt_density.setBackground(new java.awt.Color(248, 203, 117));
        txt_density.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txt_density.setForeground(new java.awt.Color(255, 0, 0));
        txt_density.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_density.setBorder(null);
        jPanel1.add(txt_density);
        txt_density.setBounds(260, 170, 60, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Karat");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(190, 200, 40, 20);

        txt_karat.setEditable(false);
        txt_karat.setBackground(new java.awt.Color(248, 203, 117));
        txt_karat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_karat.setForeground(new java.awt.Color(255, 0, 51));
        txt_karat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_karat.setBorder(null);
        jPanel1.add(txt_karat);
        txt_karat.setBounds(260, 200, 60, 22);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Value");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 270, 70, 20);

        txt_value.setEditable(false);
        txt_value.setBackground(new java.awt.Color(248, 203, 117));
        txt_value.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txt_value.setForeground(new java.awt.Color(255, 0, 51));
        txt_value.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_value.setBorder(null);
        jPanel1.add(txt_value);
        txt_value.setBounds(90, 270, 230, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setText("Amount to Pay GH₵");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(10, 300, 160, 20);

        lbl_totalAmount.setBackground(new java.awt.Color(255, 0, 51));
        lbl_totalAmount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_totalAmount.setForeground(new java.awt.Color(255, 51, 51));
        lbl_totalAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_totalAmount.setText("0.0");
        jPanel1.add(lbl_totalAmount);
        lbl_totalAmount.setBounds(180, 300, 150, 20);

        btn_save.setBackground(new java.awt.Color(248, 203, 117));
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        jPanel1.add(btn_save);
        btn_save.setBounds(140, 360, 80, 60);

        btn_print.setBackground(new java.awt.Color(248, 203, 117));
        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        jPanel1.add(btn_print);
        btn_print.setBounds(240, 360, 80, 60);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(0, 260, 340, 10);
        jPanel1.add(jSeparator3);
        jSeparator3.setBounds(0, 330, 340, 10);
        jPanel1.add(jSeparator4);
        jSeparator4.setBounds(0, 110, 340, 10);

        cus_iddd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(cus_iddd);
        cus_iddd.setBounds(20, 340, 40, 20);

        user_id.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel1.add(user_id);
        user_id.setBounds(20, 370, 40, 20);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Pounds");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(190, 135, 60, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/home.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 343, 460);

        jMenuBar1.setBackground(new java.awt.Color(248, 203, 117));

        jMenu1.setText("File");

        user.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user1.png"))); // NOI18N
        user.setText("Add User");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jMenu1.add(user);
        jMenu1.add(jSeparator5);

        customer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        customer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cus.png"))); // NOI18N
        customer.setText("Add Customer");
        customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerActionPerformed(evt);
            }
        });
        jMenu1.add(customer);
        jMenu1.add(jSeparator6);

        price.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        price.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cash.png"))); // NOI18N
        price.setText("Add Base Price");
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        jMenu1.add(price);
        jMenu1.add(jSeparator7);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/view.png"))); // NOI18N
        jMenuItem1.setText("View Customer");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator8);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/view.png"))); // NOI18N
        jMenuItem2.setText("View Users");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator9);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/view.png"))); // NOI18N
        jMenuItem3.setText("View Records");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator10);

        exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.png"))); // NOI18N
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jMenu1.add(exit);
        jMenu1.add(jSeparator11);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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

    private void add_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_customerActionPerformed

    }//GEN-LAST:event_add_customerActionPerformed

    private void txt_topKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_topKeyTyped

    }//GEN-LAST:event_txt_topKeyTyped

    private void txt_downKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_downKeyTyped

    }//GEN-LAST:event_txt_downKeyTyped

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed

    }//GEN-LAST:event_userActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerActionPerformed

    }//GEN-LAST:event_customerActionPerformed

    private void add_baseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_baseActionPerformed

    }//GEN-LAST:event_add_baseActionPerformed

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed

    }//GEN-LAST:event_priceActionPerformed

    private void txt_topFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_topFocusLost
        //setTop();
    }//GEN-LAST:event_txt_topFocusLost

    private void txt_topKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_topKeyReleased
        setTop();
        setDown();
        setPrice();
    }//GEN-LAST:event_txt_topKeyReleased

    private void txt_downKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_downKeyReleased
        setDown();
        setPrice();
    }//GEN-LAST:event_txt_downKeyReleased

    private void cmd_baseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_baseActionPerformed
        if (cmd_base.getSelectedIndex() < 0) {
            //JOptionPane.showMessageDialog(null, "Please select base price");
        } else {
            setPrice();
        }
    }//GEN-LAST:event_cmd_baseActionPerformed

    private void cmd_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_customerActionPerformed

    }//GEN-LAST:event_cmd_customerActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed

    }//GEN-LAST:event_btn_saveActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void txt_topKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_topKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            txt_down.requestFocusInWindow();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            cmd_customer.requestFocusInWindow();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_down.requestFocusInWindow();
        }
    }//GEN-LAST:event_txt_topKeyPressed

    private void txt_downKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_downKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            cmd_base.requestFocusInWindow();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txt_top.requestFocusInWindow();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmd_base.requestFocusInWindow();
        }
    }//GEN-LAST:event_txt_downKeyPressed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed

    }//GEN-LAST:event_btn_printActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
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
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_base;
    private javax.swing.JButton add_customer;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_save;
    public static javax.swing.JComboBox<String> cmd_base;
    public static javax.swing.JComboBox<String> cmd_customer;
    private javax.swing.JLabel cus_iddd;
    private javax.swing.JMenuItem customer;
    private javax.swing.JMenuItem exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    public static javax.swing.JLabel lbl_totalAmount;
    private javax.swing.JMenuItem price;
    public static javax.swing.JTextField txt_density;
    public static javax.swing.JTextField txt_down;
    public static javax.swing.JTextField txt_karat;
    public static javax.swing.JTextField txt_top;
    public static javax.swing.JTextField txt_tot_top;
    private javax.swing.JTextField txt_value;
    private javax.swing.JMenuItem user;
    public static javax.swing.JLabel user_id;
    // End of variables declaration//GEN-END:variables
}
