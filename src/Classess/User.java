package Classess;

public class User {

    private int id;
    private String name;
    private String fullname;
    private String password;
    private String created_at;

    public User() {
    }

    public User(String name, String fullname, String password, String created_at) {
        this.name = name;
        this.fullname = fullname;
        this.password = password;
        this.created_at = created_at;
    }

    public User(int id, String name, String fullname, String password, String created_at) {
        this.id = id;
        this.name = name;
        this.fullname = fullname;
        this.password = password;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

}
