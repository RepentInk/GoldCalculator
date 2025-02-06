package Controllers;

import Helpers.Authuser;
import Helpers.PricingData;
import Helpers.ShopData;
import Models.Pricing;
import Models.Shop;
import Models.User;
import Repository.PricingRepository;
import Repository.ShopRepository;

/**
 *
 * @author nyark
 */
public class SetStaticDataController {
    
    PricingRepository pricingRepository = new PricingRepository();
    ShopRepository shopRepository = new ShopRepository();
    
    public void setAuthUser(User user) {
        Authuser.setId(user.getId());
        Authuser.setFullname(user.getFullname());
        Authuser.setPhone_number(user.getPhone_number());
        Authuser.setUsername(user.getUsername());
        Authuser.setUser_type(user.getUser_type());
    }
    
    public void setPricing() {
        Pricing pricing = pricingRepository.find(0);
        PricingData.setId(pricing.getId());
        PricingData.setCurrent_price(pricing.getCurrent_price());
        PricingData.setOld_price(pricing.getOld_price());
        PricingData.setTop_divide_value(pricing.getTop_divide_value());
        PricingData.setDensity_minus_value(pricing.getDensity_minus_value());
        PricingData.setDensity_multiply_value(pricing.getDensity_multiply_value());
        PricingData.setKarat_divide_value(pricing.getKarat_divide_value());
    }
    
    public void setShopDetail() {
        Shop shop = shopRepository.find(0);
        ShopData.setId(shop.getId());
        ShopData.setName(shop.getName());
        ShopData.setLocation(shop.getLocation());
        ShopData.setContacts(shop.getContacts());
        ShopData.setEmail_address(shop.getEmail_address());
        ShopData.setDigital_address(shop.getDigital_address());
        ShopData.setMotto(shop.getMotto());
        ShopData.setGetSkinType(shop.getSkin_type());
    }
    
}
