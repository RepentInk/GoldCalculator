package Models;

/**
 *
 * @author nyark
 */
public class User {

    private int id;
    private String fullname;
    private String phone_number;
    private String username;
    private String password;
    private String created_at;
    private String updated_at;

    public User() {
    }

    public User(int id, String fullname, String phone_number, String username, String password, String created_at, String updated_at) {
        this.id = id;
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.username = username;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public User(String fullname, String phone_number, String username, String password, String created_at, String updated_at) {
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.username = username;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

}
