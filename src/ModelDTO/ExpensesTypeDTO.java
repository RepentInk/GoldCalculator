package ModelDTO;

/**
 *
 * @author nyark
 */
public class ExpensesTypeDTO {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CREATED_DATE = "created_date";
    private static final String CREATED_TIME = "created_time";

    private static final String EXPENSE_TYPE_DB = "expense_types";

    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getCREATED_DATE() {
        return CREATED_DATE;
    }

    public static String getCREATED_TIME() {
        return CREATED_TIME;
    }

    public static String getEXPENSE_TYPE_DB() {
        return EXPENSE_TYPE_DB;
    }

}
