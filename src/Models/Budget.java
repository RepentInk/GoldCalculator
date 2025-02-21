package Models;

/**
 *
 * @author nyark
 */
public class Budget {

    private int id;
    private String name;
    private double total_amount;
    private double amount_forward;
    private boolean status;
    private String start_date;
    private String end_date;
    private String created_date;
    private String created_time;
    private String raw_date;
    private int user_id;

    private String user;

    public Budget() {
    }

    public Budget(
            String name,
            double total_amount,
            double amount_forward,
            boolean status,
            String start_date,
            String end_date,
            String created_date,
            String created_time,
            String raw_date,
            int user_id
    ) {
        this.name = name;
        this.total_amount = total_amount;
        this.amount_forward = amount_forward;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
        this.created_date = created_date;
        this.created_time = created_time;
        this.raw_date = raw_date;
        this.user_id = user_id;
    }

    public Budget(
            int id,
            String name,
            double total_amount,
            double amount_forward,
            boolean status,
            String start_date,
            String end_date,
            String created_date,
            String created_time,
            String raw_date,
            int user_id
    ) {
        this.id = id;
        this.name = name;
        this.total_amount = total_amount;
        this.amount_forward = amount_forward;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
        this.created_date = created_date;
        this.created_time = created_time;
        this.raw_date = raw_date;
        this.user_id = user_id;
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

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public double getAmount_forward() {
        return amount_forward;
    }

    public void setAmount_forward(double amount_forward) {
        this.amount_forward = amount_forward;
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

    public String getRaw_date() {
        return raw_date;
    }

    public void setRaw_date(String raw_date) {
        this.raw_date = raw_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
