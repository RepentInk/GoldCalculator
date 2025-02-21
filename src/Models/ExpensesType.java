package Models;

/**
 *
 * @author nyark
 */
public class ExpensesType {

    private int id;
    private String name;
    private String created_date;
    private String created_time;

    public ExpensesType() {
    }

    public ExpensesType(String name, String created_date, String created_time) {
        this.name = name;
        this.created_date = created_date;
        this.created_time = created_time;
    }

    public ExpensesType(int id, String name, String created_date, String created_time) {
        this.id = id;
        this.name = name;
        this.created_date = created_date;
        this.created_time = created_time;
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

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

}
