package ModelDTO;

/**
 *
 * @author nyark
 */
public class ExpensesDTO {

    private static final String ID = "id";
    private static final String EXPENSE_TYPE_ID = "expenses_type_id";
    private static final String BUDGET_ID = "budget_id";
    private static final String AMOUNT = "amount";
    private static final String PAID_TO = "paid_to";
    private static final String USER_ID = "user_id";
    private static final String BUDGET_BEFORE = "budget_before";
    private static final String BUDGET_AFTER = "budget_after";
    private static final String CREATED_TIME = "created_time";
    private static final String CREATED_DATE = "created_date";

    private static final String EXPENSES_DB = "expenses";

    private static final String EXPENSE_TYPE = "expense_type";
    private static final String USER = "user";
    private static final String BUDGET = "budget";

    public static String getID() {
        return ID;
    }

    public static String getEXPENSE_TYPE_ID() {
        return EXPENSE_TYPE_ID;
    }

    public static String getBUDGET_ID() {
        return BUDGET_ID;
    }

    public static String getAMOUNT() {
        return AMOUNT;
    }

    public static String getPAID_TO() {
        return PAID_TO;
    }

    public static String getUSER_ID() {
        return USER_ID;
    }

    public static String getBUDGET_BEFORE() {
        return BUDGET_BEFORE;
    }

    public static String getCREATED_TIME() {
        return CREATED_TIME;
    }

    public static String getCREATED_DATE() {
        return CREATED_DATE;
    }

    public static String getEXPENSES_DB() {
        return EXPENSES_DB;
    }

    public static String getEXPENSE_TYPE() {
        return EXPENSE_TYPE;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getBUDGET() {
        return BUDGET;
    }

    public static String getBUDGET_AFTER() {
        return BUDGET_AFTER;
    }

}
