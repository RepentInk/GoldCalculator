package Models;

/**
 *
 * @author nyark
 */
public class Shop {

    private int id;
    private String name;
    private String location;
    private String contacts;
    private String email_address;
    private String digital_address;
    private String motto;

    public Shop() {
    }

    public Shop(String name, String location, String contacts, String email_address, String digital_address, String motto) {
        this.name = name;
        this.location = location;
        this.contacts = contacts;
        this.email_address = email_address;
        this.digital_address = digital_address;
        this.motto = motto;
    }

    public Shop(int id, String name, String location, String contacts, String email_address, String digital_address, String motto) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.contacts = contacts;
        this.email_address = email_address;
        this.digital_address = digital_address;
        this.motto = motto;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getDigital_address() {
        return digital_address;
    }

    public void setDigital_address(String digital_address) {
        this.digital_address = digital_address;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

}
