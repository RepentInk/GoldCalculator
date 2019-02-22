package Forms;

import Queries.Connect;
import Queries.CustomerQuery;
import Queries.GoldQuery;
import Queries.PriceQuery;
import Queries.UserQuery;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PrintForm extends java.awt.Dialog {

    Connect mets = new Connect();
    PriceQuery priceQ = new PriceQuery();
    CustomerQuery cusQ = new CustomerQuery();
    GoldQuery goldQ = new GoldQuery();
    UserQuery useQ = new UserQuery();

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    DecimalFormat df2 = new DecimalFormat("####.##");

    public PrintForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        conn = Connect.ConnecrDb();
        setIconImage(mets.myImage("/Images/coin.png"));

        setPageAssessment();
    }

    public void setPageAssessment() {

        URL url = Forms.PrintForm.class.getProtectionDomain().getCodeSource().getLocation();

        Double top = Double.parseDouble(HomeForm.txt_top.getText().toString());
        Double down = Double.parseDouble(HomeForm.txt_down.getText().toString());
        Double density = Double.parseDouble(HomeForm.txt_density.getText().toString());
        Double karat = Double.parseDouble(HomeForm.txt_karat.getText().toString());
        Double base = Double.parseDouble(HomeForm.cmd_base.getSelectedItem().toString());
        Double money = Double.parseDouble(HomeForm.lbl_totalAmount.getText().toString());
        Double pounds = Double.parseDouble(HomeForm.txt_tot_top.getText().toString());

        String customer = HomeForm.cmd_customer.getSelectedItem().toString();
        String user = useQ.returnName(Integer.parseInt(HomeForm.user_id.getText().toString()));

        DateFormat dateFormat = new SimpleDateFormat("MMMM EE dd, yyyy h:m:s a");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());

        try {
            // extract directory from code source url
            String root = (new File(url.toURI())).getParentFile().getPath();
            File doc = new File(root, "PageIndex.html");

            // create htm file contents for testing
            FileWriter writer = new FileWriter(doc);
            writer.write("<h2 style='margin-bottom:-150px;margin-top:-50px;font-size:11px;margin-left:2px'>" + "BEST BRAIN GOLD BUYING AGENT" + "</h2>"
                    + "<h4 style='margin-bottom:-150px;margin-top:-50px;margin-left:10px'>" + " Obaapa Junction Opposite Sephem Oil </h4>"
                    + "<h4 style='margin-bottom:-150px;margin-top:-50px;margin-left:50px'>" + " Asankrangwa</h4>"
                    + "<h4 style='letter-spacing:5pxmargin-bottom:-150px;margin-top:-50px;'>" + " 0244795859/0200124065/0322496150/0545550000</h4>"
                    + "<table><tbody style='font-size:8px;'>"
                    + "<tr style='border-top: 1px dotted black;margin-bottom:-150px;margin-top:-50px;'>"
                    + "<td style='text-align:left;'><b>Date : </b>" + date + "</td>"
                    + "</tr>"
                    + "<tr style='margin-bottom:-150px;margin-top:-50px;'>"
                    + "<td style='text-align:left;'><b>Buyer Name :</b> " + mets.capitalizer(user) + "</td>"
                    + "</tr>"
                    + "<tr style='margin-bottom:-150px;margin-top:-50px;'>"
                    + "<td style='text-align:left;'><b>Customer Name :</b> " + mets.capitalizer(customer) + "</td>"
                    + "</tr>"
                    + "</tbody></table>"
                    + "</b>");

            String val = "";

            val += "<table style='font-size:11px; border-bottom: 1px solid black'>"
                    + "<thead style='border-bottom: 1px dotted black; border-top: 1px dotted black'>"
                    + "<tr>"
                    + "<th style='text-align: left; width:130px'>Sub Titles</th>"
                    + "<th >" + " " + "</th>"
                    + "<th style='text-align: left;'>Values</th>"
                    + "</tr><hr>"
                    + "</thead><tbody>";

            val += "<tr style='border-top: 1px dotted black; margin-bottom:-150px;margin-top:-50px;'>"
                    + "<td style='text-align: left; '><b>" + "Top " + "</b></td>"
                    + "<td >" + " " + "</td>"
                    + "<td style='text-align: left;'>" + top + "</td>";

            val += "<tr style='margin-bottom:-150px;margin-top:-50px;'>"
                    + "<td style='text-align: left; '><b>" + "Pounds " + "</b></td>"
                    + "<td >" + " " + "</td>"
                    + "<td style='text-align: left;'>" + pounds + "</td>";

            val += "<tr style='margin-bottom:-150px;margin-top:-50px;'>"
                    + "<td style='text-align: left; '><b>" + "Down " + "</b></td>"
                    + "<td >" + " " + "</td>"
                    + "<td style='text-align: left;'>" + down + "</td>";

            val += "<tr style='margin-bottom:-150px;margin-top:-50px;'>"
                    + "<td style='text-align: left; '><b>" + "Density " + "</b></td>"
                    + "<td >" + " " + "</td>"
                    + "<td style='text-align: left;'>" + density + "</td>";

            val += "<tr style='margin-bottom:-150px;margin-top:-50px;'>"
                    + "<td style='text-align: left; '><b>" + "Karat " + "</b></td>"
                    + "<td >" + " " + "</td>"
                    + "<td style='text-align: left;'>" + karat + "</td>";

            val += "<tr style='margin-bottom:-150px;margin-top:-50px;'>"
                    + "<td style='text-align: left; '><b>" + "Base Price " + "</b></td>"
                    + "<td >" + " " + "</td>"
                    + "<td style='text-align: left;'>" + base + "</td>";

            val += "<tr style='border-top: 1px dotted black;margin-bottom:-150px;margin-top:-50px;'>"
                    + "<td style='text-align: left; '><b>" + "Amount Paid" + "</b></td>"
                    + "<td >" + " " + "</td>"
                    + "<td style='text-align: left;'><b>" + money + "</b></td>";

            val += "</tbody></table>"
                    + "<i style='text-align:left; font-size:8px;'>Contacts Developer : 0500383888/0544474706</i>";

            writer.write(val);
            writer.close();
            // open it in the editor
            print_editor_pane.setPage(doc.toURI().toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printReport() {
        try {
            int rows = 600;
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            PageFormat pageFormat = printerJob.defaultPage();
            Paper paper = new Paper();
            paper.setSize(600, (double) (paper.getHeight() - rows * 50));
            paper.setImageableArea(paper.getWidth() + rows * 5, 120, paper.getWidth() - rows * 2, paper.getHeight() - rows * 5);
            pageFormat.setPaper(paper);
            pageFormat.setOrientation(PageFormat.PORTRAIT);
            printerJob.setPrintable(print_editor_pane.getPrintable(null, null), pageFormat);
            printerJob.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        print_editor_pane = new javax.swing.JEditorPane();
        jButton1 = new javax.swing.JButton();

        setResizable(false);
        setTitle("PRINT RECEIPT FOR CUSTOMER");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(248, 203, 117));

        print_editor_pane.setEditable(false);
        jScrollPane1.setViewportView(print_editor_pane);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        jButton1.setToolTipText("Click to print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        printReport();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PrintForm dialog = new PrintForm(new java.awt.Frame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JEditorPane print_editor_pane;
    // End of variables declaration//GEN-END:variables
}
