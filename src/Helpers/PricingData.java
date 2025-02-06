package Helpers;

/**
 *
 * @author nyark
 */
public class PricingData {

    private static int id = 0;
    private static double current_price = 0.0;
    private static double old_price = 0.0;
    private static double top_divide_value = 0.0;
    private static double density_minus_value = 0.0;
    private static double density_multiply_value = 0.0;
    private static double karat_divide_value = 0.0;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        PricingData.id = id;
    }

    public static double getCurrent_price() {
        return current_price;
    }

    public static void setCurrent_price(double current_price) {
        PricingData.current_price = current_price;
    }

    public static double getOld_price() {
        return old_price;
    }

    public static void setOld_price(double old_price) {
        PricingData.old_price = old_price;
    }

    public static double getTop_divide_value() {
        return top_divide_value;
    }

    public static void setTop_divide_value(double top_divide_value) {
        PricingData.top_divide_value = top_divide_value;
    }

    public static double getDensity_minus_value() {
        return density_minus_value;
    }

    public static void setDensity_minus_value(double density_minus_value) {
        PricingData.density_minus_value = density_minus_value;
    }

    public static double getDensity_multiply_value() {
        return density_multiply_value;
    }

    public static void setDensity_multiply_value(double density_multiply_value) {
        PricingData.density_multiply_value = density_multiply_value;
    }

    public static double getKarat_divide_value() {
        return karat_divide_value;
    }

    public static void setKarat_divide_value(double karat_divide_value) {
        PricingData.karat_divide_value = karat_divide_value;
    }

}
