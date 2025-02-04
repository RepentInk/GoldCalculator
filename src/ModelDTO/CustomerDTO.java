package ModelDTO;

/**
 *
 * @author nyark
 */
public class CustomerDTO {

    private static final String ID = "id";
    private static final String FULL_NAME = "fullname";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String PLACE_OF_WORK = "place_of_work";
    private static final String EMERGENCY_NUMBER = "emergency_number";
    private static final String CREATED_AT = "created_at";
    private static final String UPDATED_AT = "updated_at";

    private static final String CUSTOMER_DB = "customers";

    public static String getID() {
        return ID;
    }

    public static String getFULL_NAME() {
        return FULL_NAME;
    }

    public static String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public static String getPLACE_OF_WORK() {
        return PLACE_OF_WORK;
    }

    public static String getEMERGENCY_NUMBER() {
        return EMERGENCY_NUMBER;
    }

    public static String getCREATED_AT() {
        return CREATED_AT;
    }

    public static String getUPDATED_AT() {
        return UPDATED_AT;
    }

    public static String getCUSTOMER_DB() {
        return CUSTOMER_DB;
    }

}
