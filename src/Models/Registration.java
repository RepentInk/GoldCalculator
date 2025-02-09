package Models;

/**
 *
 * @author nyark
 */
public class Registration {

    private int id;
    private String code;
    private boolean status;
    private String start_date;
    private String end_date;

    public Registration() {
    }

    public Registration(int id, String code, boolean status, String start_date, String end_date) {
        this.id = id;
        this.code = code;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Registration(String code, boolean status, String start_date, String end_date) {
        this.code = code;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

}
