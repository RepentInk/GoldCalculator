package Controllers;

import Helpers.HelperFunctions;
import Models.Pricing;
import Repository.PricingRepository;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author nyark
 */
public class PricingController {

    PricingRepository pricingRepository = new PricingRepository();
    HelperFunctions helper = new HelperFunctions();

    public void populateData(
            JLabel pricingID,
            JTextField currentPrice,
            JTextField oldPrice,
            JTextField topDivide,
            JTextField densityMinus,
            JTextField densityMultiply,
            JTextField karatDivide
    ) {

        Pricing pricing = pricingRepository.find(0);

        pricingID.setText(String.valueOf(pricing.getId()));
        currentPrice.setText(String.valueOf(helper.priceToString(pricing.getCurrent_price())));
        oldPrice.setText(String.valueOf(helper.priceToString(pricing.getOld_price())));
        topDivide.setText(String.valueOf(pricing.getTop_divide_value()));
        densityMinus.setText(String.valueOf(pricing.getDensity_minus_value()));
        densityMultiply.setText(String.valueOf(pricing.getDensity_multiply_value()));
        karatDivide.setText(String.valueOf(pricing.getKarat_divide_value()));
    }

    public void saveUpdate(
            JLabel pricingID,
            JTextField currentPrice,
            JTextField oldPrice,
            JTextField topDivide,
            JTextField densityMinus,
            JTextField densityMultiply,
            JTextField karatDivide
    ) {

        int pricing_id = 0;
        double current_price = currentPrice.getText().isEmpty() ? 0 : helper.parseAmountWithComma(currentPrice.getText());
        double old_price = oldPrice.getText().isEmpty() ? 0 : helper.parseAmountWithComma(oldPrice.getText());
        double top_divide = topDivide.getText().isEmpty() ? 0 : Double.parseDouble(topDivide.getText());
        double density_minus = densityMinus.getText().isEmpty() ? 0 : Double.parseDouble(densityMinus.getText());
        double density_multiply = densityMultiply.getText().isEmpty() ? 0 : Double.parseDouble(densityMultiply.getText());
        double karat_divide = karatDivide.getText().isEmpty() ? 0 : Double.parseDouble(karatDivide.getText());
        String created_date = helper.returnDate();

        if (!pricingID.getText().equals("")) {
            pricing_id = Integer.parseInt(pricingID.getText());
        }

        if (pricing_id > 0) {

            Pricing pricing = new Pricing(
                    pricing_id,
                    current_price,
                    old_price,
                    top_divide,
                    density_minus,
                    density_multiply,
                    karat_divide,
                    created_date
            );

            pricingRepository.update(pricing, pricing_id);

        } else {

            Pricing pricing = new Pricing(
                    current_price,
                    old_price,
                    top_divide,
                    density_minus,
                    density_multiply,
                    karat_divide,
                    created_date
            );

            pricingRepository.save(pricing);

        }

        JOptionPane.showMessageDialog(null, "Pricing information updated successfully");

    }

}
