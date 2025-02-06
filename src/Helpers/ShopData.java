package Helpers;

/**
 *
 * @author nyark
 */
public class ShopData {

    private static int id = 0;
    private static String name = "";
    private static String location = "";
    private static String contacts = null;
    private static String email_address = null;
    private static String digital_address = null;
    private static String motto = "";
    private static int yearLimit = 2024;
    private static int getSkinType = 0;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ShopData.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ShopData.name = name;
    }

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        ShopData.location = location;
    }

    public static String getContacts() {
        return contacts;
    }

    public static void setContacts(String contacts) {
        ShopData.contacts = contacts;
    }

    public static String getEmail_address() {
        return email_address;
    }

    public static void setEmail_address(String email_address) {
        ShopData.email_address = email_address;
    }

    public static String getDigital_address() {
        return digital_address;
    }

    public static void setDigital_address(String digital_address) {
        ShopData.digital_address = digital_address;
    }

    public static String getMotto() {
        return motto;
    }

    public static void setMotto(String motto) {
        ShopData.motto = motto;
    }

    public static int getYearLimit() {
        return yearLimit;
    }

    public static void setYearLimit(int yearLimit) {
        ShopData.yearLimit = yearLimit;
    }

    public static int getGetSkinType() {
        return getSkinType;
    }

    public static void setGetSkinType(int getSkinType) {
        ShopData.getSkinType = getSkinType;
    }

}
