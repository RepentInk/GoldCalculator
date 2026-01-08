package ModelDTO;

/**
 *
 * @author nyark
 */
public class PaymentDTO {

    private static final String ID = "id";
    private static final String BUY_GOLD_ID = "buy_gold_id";
    private static final String AMOUNT_PAID = "amount_paid";
    private static final String BALANCE = "balance";
    private static final String USER_ID = "user_id";
    private static final String CREATED_DATE = "created_date";
    private static final String CREATED_TIME = "created_time";
    private static final String RAW_DATE = "raw_date";
    private static final String TOTAL_AMOUNT = "total_amount";

    private static final String USER = "user";
    private static final String BUY_GOLD = "buy_gold";
    private static final String CUSTOMER = "customer";
    private static final String TOTAL_GOLD_PAYMENT = "total_payment";

    private static final String PAYMENT_DB = "payments";

    public static String getID() {
        return ID;
    }

    public static String getBUY_GOLD_ID() {
        return BUY_GOLD_ID;
    }

    public static String getAMOUNT_PAID() {
        return AMOUNT_PAID;
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

    public static String getTOTAL_AMOUNT() {
        return TOTAL_AMOUNT;
    }

    public static String getPAYMENT_DB() {
        return PAYMENT_DB;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getBUY_GOLD() {
        return BUY_GOLD;
    }

    public static String getCUSTOMER() {
        return CUSTOMER;
    }

    public static String getTOTAL_GOLD_PAYMENT() {
        return TOTAL_GOLD_PAYMENT;
    }

}
