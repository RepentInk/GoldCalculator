package Controllers;

import Models.Shop;
import Repository.ShopRepository;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author nyark
 */
public class ShopController {

    ShopRepository shopRepository = new ShopRepository();

    public void populateData(
            JLabel shopID,
            JTextField name,
            JTextField location,
            JTextField contacts,
            JTextField emailAddress,
            JTextField digitalAddress,
            JTextField motto,
            JRadioButton mcWin,
            JRadioButton aluminium,
            JRadioButton texture
    ) {

        Shop shop = shopRepository.find(0);
        shopID.setText(String.valueOf(shop.getId()));
        name.setText(shop.getName());
        location.setText(shop.getLocation());
        contacts.setText(shop.getContacts());
        emailAddress.setText(shop.getEmail_address());
        digitalAddress.setText(shop.getDigital_address());
        motto.setText(shop.getMotto());

        switch (shop.getSkin_type()) {
            case 2:
                mcWin.setSelected(true);
                break;
            case 1:
                aluminium.setSelected(true);
                break;
            default:
                texture.setSelected(true);
                break;
        }
    }

    public void saveUpdate(
            JLabel shopID,
            JTextField name,
            JTextField location,
            JTextField contacts,
            JTextField emailAddress,
            JTextField digitalAddress,
            JTextField motto,
            int skinType
    ) {

        int shop_id = 0;
        String shop_name = name.getText().trim();
        String shop_location = location.getText().trim();
        String shop_contacts = contacts.getText().trim();
        String email_address = emailAddress.getText().trim();
        String digital_address = digitalAddress.getText().trim();
        String shop_motto = motto.getText().trim();

        if (!shopID.getText().isEmpty()) {
            shop_id = Integer.parseInt(shopID.getText());
        }

        if (shop_id > 0) {

            Shop shop = new Shop(
                    shop_id,
                    shop_name,
                    shop_location,
                    shop_contacts,
                    email_address,
                    digital_address,
                    shop_motto,
                    skinType
            );

            shopRepository.update(shop, shop_id);

        } else {

            Shop shop = new Shop(
                    shop_name,
                    shop_location,
                    shop_contacts,
                    email_address,
                    digital_address,
                    shop_motto,
                    skinType
            );

            shopRepository.save(shop);
        }
    }
}
