package Helpers;

/**
 *
 * @author nyark
 */
public class Authuser {

    private static int id = 0;
    private static String fullname = "";
    private static String phone_number = "";
    private static String username = null;
    private static String user_type = null;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Authuser.id = id;
    }

    public static String getFullname() {
        return fullname;
    }

    public static void setFullname(String fullname) {
        Authuser.fullname = fullname;
    }

    public static String getPhone_number() {
        return phone_number;
    }

    public static void setPhone_number(String phone_number) {
        Authuser.phone_number = phone_number;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Authuser.username = username;
    }

    public static String getUser_type() {
        return user_type;
    }

    public static void setUser_type(String user_type) {
        Authuser.user_type = user_type;
    }

}
