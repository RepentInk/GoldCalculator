package ModelDTO;

/**
 *
 * @author nyark
 */
public class PricingDTO {

    private static final String ID = "id";
    private static final String CURRENT_PRICE = "current_price";
    private static final String OLD_PRICE = "old_price";
    private static final String TOP_DIVIDE_VALUE = "top_divide_value";
    private static final String DENSITY_MINUS_VALUE = "density_minus_value";
    private static final String DENSITY_MULTIPLY_VALUE = "density_multiply_value";
    private static final String KARAT_DIVIDE_VALUE = "karat_divide_value";
    private static final String CREATED_DATE = "created_date";

    private static final String PRICING_DB = "pricings";

    public static String getID() {
        return ID;
    }

    public static String getCURRENT_PRICE() {
        return CURRENT_PRICE;
    }

    public static String getOLD_PRICE() {
        return OLD_PRICE;
    }

    public static String getTOP_DIVIDE_VALUE() {
        return TOP_DIVIDE_VALUE;
    }

    public static String getDENSITY_MINUS_VALUE() {
        return DENSITY_MINUS_VALUE;
    }

    public static String getDENSITY_MULTIPLY_VALUE() {
        return DENSITY_MULTIPLY_VALUE;
    }

    public static String getKARAT_DIVIDE_VALUE() {
        return KARAT_DIVIDE_VALUE;
    }

    public static String getCREATED_DATE() {
        return CREATED_DATE;
    }

    public static String getPRICING_DB() {
        return PRICING_DB;
    }

}
