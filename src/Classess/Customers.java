package Classess;

public class Customers {

    private int id;
    private String fullname;
    private String contact;
    private String hometown;
    private String type;

    public Customers() {
    }

    public Customers(String fullname, String contact, String hometown, String type) {
        this.fullname = fullname;
        this.contact = contact;
        this.hometown = hometown;
        this.type = type;
    }

    public Customers(int id, String fullname, String contact, String hometown, String type) {
        this.id = id;
        this.fullname = fullname;
        this.contact = contact;
        this.hometown = hometown;
        this.type = type;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
