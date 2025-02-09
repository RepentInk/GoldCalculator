package ModelDTO;

/**
 *
 * @author nyark
 */
public class RegistrationDTO {

    private static final String ID = "id";
    private static final String CODE = "code";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String STATUS = "status";

    private static final String REGISTARTION_DB = "registration_db";

    public static String getID() {
        return ID;
    }

    public static String getCODE() {
        return CODE;
    }

    public static String getSTART_DATE() {
        return START_DATE;
    }

    public static String getEND_DATE() {
        return END_DATE;
    }

    public static String getSTATUS() {
        return STATUS;
    }

    public static String getREGISTARTION_DB() {
        return REGISTARTION_DB;
    }

}
