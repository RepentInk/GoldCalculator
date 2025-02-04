package Repository;

import Helpers.HelperFunctions;
import Helpers.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author nyark
 */
public class AnonymousRepository {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    HelperFunctions helper = new HelperFunctions();

    public AnonymousRepository() {
        conn = connectDB.ConnecrDb();
    }

    private int lastGenerateID(String tableName) {
        int last_inserted_id = 0;
        try {
            String query = "SELECT MAX(id) FROM " + tableName + " LIMIT 1";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                last_inserted_id = rs.getInt("MAX(id)");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return last_inserted_id;
    }

    public String generateCode(String tableName, String column, String initial) {
        int count = this.lastGenerateID(tableName);

        String generatedCode = helper.generateRandomCode(count, initial);
        while (this.checkCode(generatedCode, tableName, column)) {
            generatedCode = helper.generateRandomCode(count + 1, initial);
        }

        return generatedCode;
    }

    private boolean checkCode(String code, String tableName, String column) {
        boolean productExist = false;
        try {
            String query = "SELECT * FROM " + tableName + " WHERE " + column + "='" + code + "'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                productExist = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        return productExist;
    }
}
