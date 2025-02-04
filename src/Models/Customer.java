package Models;

/**
 *
 * @author nyark
 */
public class Customer {

    private int id;
    private String fullname;
    private String phone_number;
    private String place_of_work;
    private String emergency_number;
    private String created_at;
    private String updated_at;

    public Customer() {
    }

    public Customer(
            String fullname,
            String phone_number,
            String place_of_work,
            String emergency_number,
            String created_at,
            String updated_at
    ) {
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.place_of_work = place_of_work;
        this.emergency_number = emergency_number;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Customer(
            int id,
            String fullname,
            String phone_number,
            String place_of_work,
            String emergency_number,
            String created_at,
            String updated_at
    ) {
        this.id = id;
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.place_of_work = place_of_work;
        this.emergency_number = emergency_number;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getPlace_of_work() {
        return place_of_work;
    }

    public void setPlace_of_work(String place_of_work) {
        this.place_of_work = place_of_work;
    }

    public String getEmergency_number() {
        return emergency_number;
    }

    public void setEmergency_number(String emergency_number) {
        this.emergency_number = emergency_number;
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
