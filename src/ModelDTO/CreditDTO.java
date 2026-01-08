package ModelDTO;

/**
 *
 * @author nyark
 */
public class CreditDTO {

    private static final String ID = "id";
    private static final String CODE = "code";
    private static final String CUSTOMER_ID = "customer_id";
    private static final String TOTAL_AMOUNT = "total_amount";
    private static final String USER_ID = "user_id";
    private static final String CREATED_DATE = "created_date";
    private static final String CREATED_TIME = "created_time";
    private static final String RAW_DATE = "raw_date";
    private static final String STATUS = "status";

    private static final String CREDIT_DB = "credits";
    private static final String CUSTOMER = "customer";
    private static final String TOTAL_CREDIT_PAYMENT = "total_payment";
    private static final String USER = "user";

    public static String getID() {
        return ID;
    }

    public static String getCODE() {
        return CODE;
    }

    public static String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public static String getUSER_ID() {
        return USER_ID;
    }

    public static String getCREATED_DATE() {
        return CREATED_DATE;
    }

    public static String getCREATED_TIME() {
        return CREATED_TIME;
    }

    public static String getCUSTOMER() {
        return CUSTOMER;
    }

    public static String getRAW_DATE() {
        return RAW_DATE;
    }

    public static String getCREDIT_DB() {
        return CREDIT_DB;
    }

    public static String getCustomer() {
        return CUSTOMER;
    }

    public static String getTOTAL_AMOUNT() {
        return TOTAL_AMOUNT;
    }

    public static String getTOTAL_CREDIT_PAYMENT() {
        return TOTAL_CREDIT_PAYMENT;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getSTATUS() {
        return STATUS;
    }

}
