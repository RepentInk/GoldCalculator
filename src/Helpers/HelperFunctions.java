package Helpers;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HelperFunctions {

    public HelperFunctions() {
    }

    public String iconImagePath() {
        return "/Images/goldink.png";
    }

    // Method that set the icon
    public Image setIcon(String img) {
        Image i = null;
        try {
            i = ImageIO.read(getClass().getResource(img));
        } catch (IOException e) {
        }
        return i;
    }

    // Method to replace hyphens 
    public String replaceContentAppostraphe(String content) {
        return content.trim().replace("'", "");
    }

    public String splitWord(String word, int index, String separator) {
        if (!word.contains(separator)) {
            return word;
        }

        String[] splitWord = word.split(Pattern.quote(separator));

        return splitWord[index].trim();
    }

    // Generate random code
    public String generateRandomCode(int id, String abbreviation) {
        String code = null;

        if (id == 0) {
            id++;
        }

        if (id < 10) {
            code = "0000" + Integer.toString(id);
        } else if (id >= 10 && id < 100) {
            code = "000" + Integer.toString(id);
        } else if (id >= 100 && id < 1000) {
            code = "00" + Integer.toString(id);
        } else if (id >= 1000 && id < 10000) {
            code = "0" + Integer.toString(id);
        } else if (id >= 10000) {
            code = Integer.toString(id);
        }

        return abbreviation + code;
    }

    public void StartDate(JLabel date) {
        Timer t = new Timer(500, (ActionEvent e) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM EE d, yyy");
            date.setText(sdf.format(new java.util.Date()));
        });

        t.start();
    }

    // starting time
    public void StartTime(JLabel time) {
        Timer t = new Timer(500, (ActionEvent e) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
            time.setText(sdf.format(new java.util.Date()));
        });
        t.start();
    }

    // return current date
    public String returnDate() {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyy-MM-dd");
        String date = sdf2.format(new java.util.Date()).trim();
        return date;
    }

    // return current time
    public String returnTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
        String time = sdf.format(new java.util.Date());
        return time;
    }

    // return current date
    public String localDateFormat() {
        SimpleDateFormat sdf2 = new SimpleDateFormat("MMM EE d, yyyy");
        String date = sdf2.format(new java.util.Date()).trim();
        return date;
    }

    // return current year
    public String returnCurrentYear() {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyy");
        String date = sdf2.format(new java.util.Date()).trim();
        return date;
    }

    // return current year
    public String returnCurrentYearTwoDigit() {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yy");
        String date = sdf2.format(new java.util.Date()).trim();
        return date;
    }

    // return current date
    public String returnCurrentMonth() {
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        String date = sdf2.format(new java.util.Date()).trim();
        return date;
    }

    public String returnCurrentDay() {
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
        String date = sdf2.format(new java.util.Date()).trim();
        return date;
    }

    // return sql date format
    public java.sql.Date sqlDateFormat(String mydate) {
        java.sql.Date currentDate = null;
        try {

            DateFormat format = new SimpleDateFormat("MMM EE d, yyyy", Locale.getDefault());
            Date date = format.parse(mydate);
            currentDate = new java.sql.Date(date.getTime());

        } catch (ParseException ex) {
            Logger.getLogger(HelperFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return currentDate;
    }

    // parse date
    public String parseDate(String current) {
        String data = null;
        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyy-MM-d");
            Date date = sdf2.parse(current);
            DateFormat format = new SimpleDateFormat("MMM EE d, yyyy");
            data = format.format(date);

        } catch (ParseException ex) {
            Logger.getLogger(HelperFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    // parse date
    public String parseDateMonth(String current) {
        String data = null;
        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
            Date date = sdf2.parse(current);
            DateFormat format = new SimpleDateFormat("MMMM");
            data = format.format(date);

        } catch (ParseException ex) {
            Logger.getLogger(HelperFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    // Searching for item in table method starts here
    public void searchTable(String SearchString, JTable table) {
        Vector originalTableModel = (Vector) ((DefaultTableModel) table.getModel()).getDataVector().clone();

        DefaultTableModel currtableModel = (DefaultTableModel) table.getModel();
        currtableModel.setRowCount(0);
        for (Object rows : originalTableModel) {
            Vector rowVector = (Vector) rows;

            for (Object column : rowVector) {
                if (column.toString().toLowerCase().contains(SearchString.toLowerCase())) {
                    currtableModel.addRow(rowVector);
                    break;
                }
            }

        }
    }

    // searching items 
    public void searchItem(JTable itemTable, String SearchString, Vector itemVector) {

        DefaultTableModel currtableModel = (DefaultTableModel) itemTable.getModel();
        currtableModel.setRowCount(0);

        for (Object rows : itemVector) {
            Vector rowData = (Vector) rows;

            for (Object column : rowData) {
                if (column != null && column.toString().toLowerCase().contains(SearchString.toLowerCase())) {
                    currtableModel.addRow(rowData);
                    break;
                }
            }
        }
    }

    // function to show hidden password
    public void showPassword(JCheckBox checkBox, JPasswordField passwordField) {
        if (checkBox.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('*');
        }
    }

    // provide colors to table
    public void TableColor(JTable table) {
        Color[] equelThree = {Color.BLACK, Color.BLACK, Color.RED};
        Color[] equelFour = {Color.BLACK, Color.BLACK, Color.BLUE, Color.RED};
        Color[] equelFive = {Color.BLACK, Color.BLACK, Color.BLUE, Color.BLUE, Color.RED};
        Color[] equelSix = {Color.BLACK, Color.BLACK, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED};
        Color[] equelSeven = {Color.BLACK, Color.BLACK, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED};
        Color[] equelEight = {Color.BLACK, Color.BLACK, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED};
        Color[] equelNine = {Color.BLACK, Color.BLACK, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED};
        Color[] equelTen = {Color.BLACK, Color.BLACK, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED};
        Color[] equelEleven = {Color.BLACK, Color.BLACK, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED};
        Color[] colorTwelve = {Color.BLACK, Color.BLACK, Color.BLACK, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.GRAY, Color.RED, Color.BLUE, Color.BLUE, Color.RED};
        Color[] colorThirteen = {Color.BLACK, Color.BLACK, Color.BLACK, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.GRAY, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.RED};
        Color[] colorFourteen = {Color.BLACK, Color.BLACK, Color.BLACK, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.GRAY, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.DARK_GRAY};
        Color[] colorFithteen = {Color.BLACK, Color.BLACK, Color.BLACK, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.GRAY, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.DARK_GRAY, Color.RED};
        Color[] colorSixteen = {Color.BLACK, Color.BLACK, Color.BLACK, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.GRAY, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.DARK_GRAY, Color.RED, Color.darkGray};
        Color[] colorSeventeen = {Color.BLACK, Color.BLACK, Color.BLACK, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.GRAY, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.DARK_GRAY, Color.RED, Color.darkGray, Color.RED};
        Color[] colorEighteen = {Color.BLACK, Color.BLACK, Color.BLACK, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.GRAY, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.DARK_GRAY, Color.RED, Color.darkGray, Color.RED, Color.RED};

        for (int i = 0; i < table.getColumnCount(); i++) {

            Color col = null;

            switch (table.getColumnCount()) {
                case 3:
                    col = equelThree[i];
                    break;
                case 4:
                    col = equelFour[i];
                    break;
                case 5:
                    col = equelFive[i];
                    break;
                case 6:
                    col = equelSix[i];
                    break;
                case 7:
                    col = equelSeven[i];
                    break;
                case 8:
                    col = equelEight[i];
                    break;
                case 9:
                    col = equelNine[i];
                    break;
                case 10:
                    col = equelTen[i];
                    break;
                case 11:
                    col = equelEleven[i];
                    break;
                case 12:
                    col = colorTwelve[i];
                    break;
                case 13:
                    col = colorThirteen[i];
                    break;
                case 14:
                    col = colorFourteen[i];
                    break;
                case 15:
                    col = colorFithteen[i];
                    break;
                case 16:
                    col = colorSixteen[i];
                    break;
                case 17:
                    col = colorSeventeen[i];
                    break;
                default:
                    col = colorEighteen[i];
                    break;
            }

            table.getColumnModel().getColumn(i).setCellRenderer(new CustomRenderer(col, i));
        }

        table.getTableHeader().setDefaultRenderer(new HeaderColor());
    }

    public static class CustomRenderer extends DefaultTableCellRenderer {

        private final Color color;
        private int i = 0;

        public CustomRenderer(Color color, int i) {
            this.color = color;
            this.i = i;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == this.i) {
                cellComponent.setForeground(this.color);
                cellComponent.setFont(new java.awt.Font("Tahoma", 1, 12));
            }
            return cellComponent;
        }

    }

    public static class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cellComponent.setBackground(Color.YELLOW);
            cellComponent.setForeground(Color.BLACK);
            cellComponent.setFont(new java.awt.Font("Tahoma", 1, 15));
            return cellComponent;
        }

    }

    // sum up table column integer
    public int summationTableColumnReturnInteger(JTable table, int col) {
        int rowcount = table.getRowCount();
        int sum = 0;
        for (int i = 0; i < rowcount; i++) {
            if (!"".equals(table.getValueAt(i, col).toString())) {
                sum = sum + Integer.parseInt(table.getValueAt(i, col).toString());
            }
        }

        return sum;
    }

    // summation of table column and return double
    public double summationOfTableColumnReturnDouble(JTable table, int col) {
        int rowcount = table.getRowCount();
        double sum = 0;
        for (int i = 0; i < rowcount; i++) {
            if (table.getValueAt(i, col) != null && !(table.getValueAt(i, col)).equals("")) {
                sum = sum + parseAmountWithComma(table.getValueAt(i, col).toString());
            }
        }

        return sum;
    }

    // random receipt id
    public String generateRandom() {
        String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            stringBuilder.append(text.charAt(random.nextInt(text.length())));
        }

        return stringBuilder.toString();
    }

    // adding month
    public String addMonth(int i) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM EE dd, yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, i);
        String result = dateFormat.format(cal.getTime());

        return result;
    }

    // confirming password
    public boolean confirmPassword(JPasswordField password, JPasswordField confirm_password) {
        return password.getText().trim().equalsIgnoreCase(confirm_password.getText().trim());
    }

    // generate activation code
    public String generateActivationCode(String name) {
        String code;
        String word = name.replaceAll("\\s+", "");
        if (word.length() > 3) {
            code = reverseWordInMyString(word.substring(0, 3) + "S9-AS87D-SZ" + reverseWordInMyString(word.substring(0, 3)) + "-P080H-" + reverseWordInMyString(word.substring(0, 3)) + "SX");
        } else {
            code = reverseWordInMyString(word + "S9-AS87D-SZ" + reverseWordInMyString(word.substring(0, 3)) + "-P080H-" + reverseWordInMyString(word) + "SX");
        }

        return code.toUpperCase();
    }

    // public function get date difference
    public long dateDifference(String endDate) {
        DateFormat format = new SimpleDateFormat("MMMM EE dd, yyyy", Locale.ENGLISH);
        long day = 0;
        try {
            Date currentdate = format.parse(localDateFormat());
            Date enddate = format.parse(endDate);

            long diff = Math.abs(currentdate.getTime() - enddate.getTime());
            day = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            if (currentdate.getTime() > enddate.getTime()) {
                day = Long.parseLong("-") + day;
            }

        } catch (ParseException e) {
            e.getLocalizedMessage();
        }

        return day;
    }

    public long daysBetweenDates(String startDate, String endDate) {
        if (startDate.isEmpty() || endDate.isEmpty()) {
            return 0;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate firstDate = LocalDate.parse(startDate, formatter);
        final LocalDate secondDate = LocalDate.parse(endDate, formatter);
        final long days = ChronoUnit.DAYS.between(firstDate, secondDate);

        return days;
    }

    public String reverseWordInMyString(String str) {
        String[] words = str.split(" ");
        String reversedString = "";

        for (String word : words) {
            String reverseWord = "";
            for (int j = word.length() - 1; j >= 0; j--) {
                reverseWord = reverseWord + word.charAt(j);
            }
            reversedString = reversedString + reverseWord;
        }

        return reversedString;
    }

    public boolean compareDate(String endDate) {
        DateFormat format = new SimpleDateFormat("MMMM EE dd, yyyy", Locale.ENGLISH);
        boolean result = false;
        try {
            Date currentdate = format.parse(localDateFormat());
            Date enddate = format.parse(endDate);

            result = currentdate.after(enddate);

        } catch (ParseException e) {
            e.getLocalizedMessage();
        }

        return result;
    }

    public String splitIntoLine(String input, int maxLineLength) {
        char NEWLINE = '\n';
        String SPACE_SEPARATOR = " ";
        String SPLIT_REGEXP = "\\s+";

        String[] tokens = input.split(SPLIT_REGEXP);
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        for (int i = 0; i < tokens.length; i++) {
            String word = tokens[i];

            if (lineLen + (SPACE_SEPARATOR + word).length() > maxLineLength) {
                if (i > 0) {
                    output.append(NEWLINE);
                }
                lineLen = 0;
            }
            if (i < tokens.length - 1 && (lineLen + (word + SPACE_SEPARATOR).length() + tokens[i + 1].length()
                    <= maxLineLength)) {
                word += SPACE_SEPARATOR;
            }
            output.append(word);
            lineLen += word.length();
        }

        return output.toString();
    }

    public String multiLine(String longString, String splitter, int maxLength) {
        return Arrays.stream(longString.split(splitter))
                .collect(
                        ArrayList<String>::new,
                        (l, s) -> {
                            Function<ArrayList<String>, Integer> id = list -> list.size() - 1;
                            if (l.isEmpty() || (l.get(id.apply(l)).length() != 0 && l.get(id.apply(l)).length() + s.length() >= maxLength)) {
                                l.add("");
                            }
                            l.set(id.apply(l), l.get(id.apply(l)) + (l.get(id.apply(l)).length() == 0 ? "" : splitter) + s);
                        },
                        (l1, l2) -> l1.addAll(l2))
                .stream().reduce((s1, s2) -> s1 + "\n" + s2).get();
    }

    private String formatAmountWithDecimal(double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###,###,##0.00");
        return formatter.format(price);
    }

    private String formatAmountWithoutDecimal(double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###,###,##0.00");
        return formatter.format(price);
    }

    public String priceToString(double price) {
        String toShow = formatAmountWithDecimal(price);
        if (toShow.indexOf(".") > 0) {
            return formatAmountWithDecimal(price);
        } else {
            return formatAmountWithoutDecimal(price);
        }
    }

    public String priceToStringWithoutRoundUp(double price) {
        DecimalFormat df = new DecimalFormat("###,###,###,###,###.##");
        df.setRoundingMode(RoundingMode.DOWN);
        String result = df.format(price);
        return result;
    }

    public String priceRoundUpWhole(double price) {
        DecimalFormat df = new DecimalFormat("###,###,###,###,###.00");
        df.setRoundingMode(RoundingMode.DOWN);
        String result = df.format(price);
        String value = result;
        
        if(result.contains(".")) {
            value = splitWord(result, 0, ".");
        }
        
        return value + ".00";
    }

    public BigDecimal parse(final String amount, final Locale locale) throws ParseException {
        final NumberFormat format = NumberFormat.getNumberInstance(locale);
        if (format instanceof DecimalFormat) {
            ((DecimalFormat) format).setParseBigDecimal(true);
        }
        return (BigDecimal) format.parse(amount.replaceAll("[^\\d.,]", ""));
    }

    public double applyDiscount(double amount, double percent) {
        double total = amount - (amount * (percent / 100));
        return total;
    }

    public double getDiscountAmount(double amount, double quan, double percent) {
        double total = (amount * quan) * (percent / 100);
        return total;
    }

    // JDateChooser function 
    public java.sql.Date convertDateChooserDateToSQLDate(JDateChooser choose) {
        java.sql.Date currentDate = null;
        try {
            String myDate = ((JTextField) choose.getDateEditor().getUiComponent()).getText().toLowerCase().trim();
            DateFormat format = new SimpleDateFormat("MMM EE d, yyyy", Locale.getDefault());
            Date date = format.parse(myDate);
            currentDate = new java.sql.Date(date.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(HelperFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return currentDate;
    }

    public Date convertSQLDateToDate(Date sqlDate) {
        java.util.Date datePaid = null;
        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("MMM EE d, yyyy");
            String created_at = sdf2.format(sqlDate);
            datePaid = new SimpleDateFormat("MMM EE d, yyyy").parse(created_at);
        } catch (ParseException ex) {
            Logger.getLogger(HelperFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datePaid;
    }

    public Date convertChooserDate(String date) {
        Date converted_date = null;

        if (date == null) {
            return null;
        }
        try {
            converted_date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(HelperFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return converted_date;
    }

    public double parseAmountWithComma(String amount) {
        String parse_amount = amount.replaceAll(",", "");
        return Double.parseDouble(parse_amount);
    }

    public ArrayList<String> getYearList(int year) {
        ArrayList<String> yearList = new ArrayList<>(year);
        int endYear = Calendar.getInstance().get(Calendar.YEAR);
        int yearBetween = endYear - year;
        for (int i = 0; i <= yearBetween; i++) {
            yearList.add(Integer.toString(endYear - i));
        }

        return yearList;
    }

}
