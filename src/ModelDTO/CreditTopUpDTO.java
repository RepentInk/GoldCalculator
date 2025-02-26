package ModelDTO;

/**
 *
 * @author nyark
 */
public class CreditTopUpDTO {

    private static final String ID = "id";
    private static final String CREDIT_ID = "credit_id";
    private static final String AMOUNT_PAID = "amount_paid";
    private static final String TOTAL_AMOUNT = "total_amount";
    private static final String USER_ID = "user_id";
    private static final String CREATED_DATE = "created_date";
    private static final String CREATED_TIME = "created_time";

    private static final String CREDIT_TOPUP_DB = "credit_topup";
    private static final String USER = "user";

    public static String getID() {
        return ID;
    }

    public static String getCREDIT_ID() {
        return CREDIT_ID;
    }

    public static String getAMOUNT_PAID() {
        return AMOUNT_PAID;
    }

    public static String getTOTAL_AMOUNT() {
        return TOTAL_AMOUNT;
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

    public static String getCREDIT_TOPUP_DB() {
        return CREDIT_TOPUP_DB;
    }

    public static String getUSER() {
        return USER;
    }

}
