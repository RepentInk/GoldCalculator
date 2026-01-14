package ModelDTO;

/**
 *
 * @author nyark
 */
public class ProfitLossDTO {

    private static final String ID = "id";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String TOTAL_BUDGET = "total_budget";
    private static final String BUDGET_LEFT = "budget_left";
    private static final String TOTAL_EXPENSES = "total_expenses";
    private static final String AMOUNT_FROM_GOLD = "amount_from_gold";
    private static final String PROFIT_LOSS = "profit_loss";
    private static final String CREATED_DATE = "created_date";
    private static final String CREATED_TIME = "created_time";
    private static final String RAW_DATE = "raw_date";

    private static final String PROFIT_LOSS_DB = "profit_loss_db";

    public static String getID() {
        return ID;
    }

    public static String getSTART_DATE() {
        return START_DATE;
    }

    public static String getEND_DATE() {
        return END_DATE;
    }

    public static String getTOTAL_BUDGET() {
        return TOTAL_BUDGET;
    }

    public static String getBUDGET_LEFT() {
        return BUDGET_LEFT;
    }

    public static String getTOTAL_EXPENSES() {
        return TOTAL_EXPENSES;
    }

    public static String getAMOUNT_FROM_GOLD() {
        return AMOUNT_FROM_GOLD;
    }

    public static String getPROFIT_LOSS() {
        return PROFIT_LOSS;
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

    public static String getPROFIT_LOSS_DB() {
        return PROFIT_LOSS_DB;
    }

}
