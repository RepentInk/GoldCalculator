package ModelDTO;

/**
 *
 * @author nyark
 */
public class UserDTO {

    private static final String ID = "id";
    private static final String FULL_NAME = "fullname";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String CREATED_AT = "created_at";
    private static final String UPDATED_AT = "updated_at";
    private static final String USER_TYPE = "user_type";

    private static final String USERS_DB = "users";

    public static String getID() {
        return ID;
    }

    public static String getFULL_NAME() {
        return FULL_NAME;
    }

    public static String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getCREATED_AT() {
        return CREATED_AT;
    }

    public static String getUPDATED_AT() {
        return UPDATED_AT;
    }

    public static String getUSERS_DB() {
        return USERS_DB;
    }

    public static String getUSER_TYPE() {
        return USER_TYPE;
    }

}
