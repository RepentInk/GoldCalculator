package ModelDTO;

/**
 *
 * @author nyark
 */
public class BudgetAdditionDTO {

    private static final String ID = "id";
    private static final String BUDGET_ID = "budget_id";
    private static final String SOURCE = "source";
    private static final String AMOUNT = "amount";
    private static final String USER_ID = "user_id";
    private static final String CREATED_TIME = "created_time";
    private static final String CREATED_DATE = "created_date";

    private static final String BUDGET_ADDITION_DB = "budget_additions";
    private static final String USER = "user";

    public static String getID() {
        return ID;
    }

    public static String getBUDGET_ID() {
        return BUDGET_ID;
    }

    public static String getSOURCE() {
        return SOURCE;
    }

    public static String getAMOUNT() {
        return AMOUNT;
    }

    public static String getCREATED_DATE() {
        return CREATED_DATE;
    }

    public static String getCREATED_TIME() {
        return CREATED_TIME;
    }

    public static String getUSER_ID() {
        return USER_ID;
    }

    public static String getBUDGET_ADDITION_DB() {
        return BUDGET_ADDITION_DB;
    }

    public static String getUSER() {
        return USER;
    }

}
