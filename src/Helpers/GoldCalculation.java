package Helpers;

/**
 *
 * @author nyark
 */
public class GoldCalculation {

    public double poundsCalculation(double value) {
        return value / PricingData.getTop_divide_value();
    }

    public double densityCalculation(double top, double down) {
        return top / down;
    }

    public double karatCalculation(double density) {
        double result = (((density - PricingData.getDensity_minus_value()) * PricingData.getDensity_multiply_value()) / density);
        return result;
    }

    public double valueCalculation(double karat, double top) {
        double value = (karat / PricingData.getKarat_divide_value()) * top;
        return value;
    }

    public double totalAmountCalculation(double value) {
        return value * PricingData.getCurrent_price();
    }

}
