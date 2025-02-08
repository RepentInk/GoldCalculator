package Helpers;

import ModelDTO.BuyGoldDTO;
import ModelDTO.CustomerDTO;
import ModelDTO.PaymentDTO;
import ModelDTO.UserDTO;
import Models.Payments;
import Models.Receipt;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author nyark
 */
public class Report {

    Connection conn = null;
    HelperFunctions helper = new HelperFunctions();

    public Report() {
        conn = connectDB.ConnecrDb();
    }

    public String receiptData(int payment_id) {
        String query = "SELECT payment.amount_paid AS Amount,payment.balance AS Balance,user.fullname AS User,gold.code AS Code,payment.created_date AS Created,payment.created_time AS Time,"
                + "gold.top AS Top, gold.down AS Down, gold.density AS Density, gold.karat AS Karat, gold.pounds AS Pounds, gold.base_price AS Price,"
                + "gold.total_weight AS Value, gold.total_amount AS Total, customer.fullname AS Customer FROM " + PaymentDTO.getPAYMENT_DB() + " payment "
                + "LEFT JOIN " + UserDTO.getUSERS_DB() + " user ON payment.user_id=user.id "
                + "LEFT JOIN " + BuyGoldDTO.getBUY_GOLD_DB() + " gold ON payment.buy_gold_id=gold.id "
                + "LEFT JOIN " + CustomerDTO.getCUSTOMER_DB() + " customer ON gold.customer_id=customer.id "
                + "WHERE payment.id = '" + payment_id + "'";

        return query;
    }

    public void paymentReceiptPrint(String sql, Receipt receipt) {
        try {
            InputStream url = getClass().getResourceAsStream("/Report_80/Receipt.jrxml");
            JasperDesign reportDesign = JRXmlLoader.load(url);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            reportDesign.setQuery(newQuery);

            HashMap<String, Object> para = new HashMap<>();
            para.put("name", ShopData.getName());
            para.put("location", ShopData.getLocation());
            para.put("contacts", ShopData.getContacts());
            para.put("email", ShopData.getEmail_address());
            para.put("digital", ShopData.getDigital_address());
            para.put("totalAmount", helper.priceToString(receipt.getTotalAmount()));
            para.put("balance", helper.priceToString(receipt.getBalance()));
            para.put("basePrice", helper.priceToString(receipt.getBasePrice()));
            para.put("amountPaid", helper.priceToString(receipt.getAmountPaid()));

            JasperReport report = JasperCompileManager.compileReport(reportDesign);
            JasperPrint print = JasperFillManager.fillReport(report, para, conn);
            JasperViewer jasperReport = new JasperViewer(print, false);
            jasperReport.setSize(600, 600);
            jasperReport.setTitle("Payment Receipt Preview");
            jasperReport.setLocationRelativeTo(null);
            jasperReport.setVisible(true);

        } catch (JRException e) {
            e.getLocalizedMessage();
        }
    }

}
