package ModelDTO;

/**
 *
 * @author nyark
 */
public class BudgetDTO {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String TOTAL_AMOUNT = "total_amount";
    private static final String AMOUNT_USED = "amount_used";
    private static final String AMOUNT_LEFT = "amount_left";
    private static final String STATUS = "status";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
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

    public static String getAMOUNT_USED() {
        return AMOUNT_USED;
    }

    public static String getAMOUNT_LEFT() {
        return AMOUNT_LEFT;
    }

    public static String getSTATUS() {
        return STATUS;
    }

    public static String getSTART_DATE() {
        return START_DATE;
    }

    public static String getEND_DATE() {
        return END_DATE;
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

}
