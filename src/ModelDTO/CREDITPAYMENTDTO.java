package ModelDTO;

/**
 *
 * @author nyark
 */
public class CREDITPAYMENTDTO {

    private static final String ID = "id";
    private static final String CREDIT_ID = "credit_id";
    private static final String CUSTOMER_ID = "customer_id";
    private static final String PAID = "paid";
    private static final String BALANCE = "balance";
    private static final String USER_ID = "user_id";
    private static final String CREATED_DATE = "created_date";
    private static final String CREATED_TIME = "created_time";
    private static final String RAW_DATE = "raw_date";

    private static final String CREDIT_PAYMENTS_DB = "credit_payments";
    private static final String USER = "user";

    public static String getID() {
        return ID;
    }

    public static String getCREDIT_ID() {
        return CREDIT_ID;
    }

    public static String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public static String getPAID() {
        return PAID;
    }

    public static String getBALANCE() {
        return BALANCE;
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

    public static String getRAW_DATE() {
        return RAW_DATE;
    }

    public static String getCREDIT_PAYMENTS_DB() {
        return CREDIT_PAYMENTS_DB;
    }

    public static String getUSER() {
        return USER;
    }

}
