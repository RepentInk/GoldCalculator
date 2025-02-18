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

    public double amountCalculation(double price, double pounds, double karat) {
        return (price * pounds * karat) / PricingData.getKarat_divide_value();
    }

    public double basePriceCalculation(double amount, double pounds) {
        return amount / pounds;
    }

}
