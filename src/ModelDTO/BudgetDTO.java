package ModelDTO;

/**
 *
 * @author nyark
 */
public class BudgetDTO {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String TOTAL_AMOUNT = "total_amount";
    private static final String SOURCE_OF_FUND = "source_of_fund";
    private static final String STATUS = "status";
    private static final String CREATED_DATE = "created_date";
    private static final String CREATED_TIME = "created_time";
    private static final String RAW_DATE = "raw_date";
    private static final String USER_ID = "user_id";

    private static final String USER = "user";

    private static final String BUDGET_DB = "budgets";

    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getTOTAL_AMOUNT() {
        return TOTAL_AMOUNT;
    }

    public static String getSTATUS() {
        return STATUS;
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

    public static String getUSER_ID() {
        return USER_ID;
    }

    public static String getBUDGET_DB() {
        return BUDGET_DB;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getSOURCE_OF_FUND() {
        return SOURCE_OF_FUND;
    }
}
