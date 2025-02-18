package ModelDTO;

/**
 *
 * @author nyark
 */
public class CreditDTO {

    private static final String ID = "id";
    private static final String CODE = "code";
    private static final String CUSTOMER_ID = "customer_id";
    private static final String BUDGET_ID = "budget_id";
    private static final String AMOUNT = "amount";
    private static final String PREVIOUS_AMOUNT = "previous_balance";
    private static final String BUDGET_BEFORE = "budget_before";
    private static final String BUDGET_AFTER = "budget_after";
    private static final String USER_ID = "user_id";
    private static final String CREATED_DATE = "created_date";
    private static final String CREATED_TIME = "created_time";
    private static final String RAW_DATE = "raw_date";

    private static final String CREDIT_DB = "credits";
    private static final String CUSTOMER = "customer";
    private static final String BUDGET = "budget";
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

    public static String getBUDGET_ID() {
        return BUDGET_ID;
    }

    public static String getAMOUNT() {
        return AMOUNT;
    }

    public static String getPREVIOUS_AMOUNT() {
        return PREVIOUS_AMOUNT;
    }

    public static String getBUDGET_BEFORE() {
        return BUDGET_BEFORE;
    }

    public static String getBUDGET_AFTER() {
        return BUDGET_AFTER;
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

    public static String getBUDGET() {
        return BUDGET;
    }

    public static String getUSER() {
        return USER;
    }

}
