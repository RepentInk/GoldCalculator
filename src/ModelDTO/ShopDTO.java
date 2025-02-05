package ModelDTO;

/**
 *
 * @author nyark
 */
public class ShopDTO {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LOCATION = "location";
    private static final String CONTACTS = "contacts";
    private static final String EMAIL_ADDRESS = "email_address";
    private static final String DIGITAL_ADDRESS = "digital_address";
    private static final String MOTTO = "motto";

    private static final String SHOP_DB = "shops";

    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getLOCATION() {
        return LOCATION;
    }

    public static String getCONTACTS() {
        return CONTACTS;
    }

    public static String getEMAIL_ADDRESS() {
        return EMAIL_ADDRESS;
    }

    public static String getDIGITAL_ADDRESS() {
        return DIGITAL_ADDRESS;
    }

    public static String getMOTTO() {
        return MOTTO;
    }

    public static String getSHOP_DB() {
        return SHOP_DB;
    }

}
