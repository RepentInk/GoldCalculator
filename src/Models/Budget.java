package Models;

/**
 *
 * @author nyark
 */
public class Budget {

    private int id;
    private String name;
    private double total_amount;
    private String source_of_fund;
    private boolean status;
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
            String source_of_fund,
            boolean status,
            String created_date,
            String created_time,
            String raw_date,
            int user_id
    ) {
        this.name = name;
        this.total_amount = total_amount;
        this.source_of_fund = source_of_fund;
        this.status = status;
        this.created_date = created_date;
        this.created_time = created_time;
        this.raw_date = raw_date;
        this.user_id = user_id;
    }

    public Budget(
            int id,
            String name,
            double total_amount,
            String source_of_fund,
            boolean status,
            String created_date,
            String created_time,
            String raw_date,
            int user_id
    ) {
        this.id = id;
        this.name = name;
        this.total_amount = total_amount;
        this.source_of_fund = source_of_fund;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSource_of_fund() {
        return source_of_fund;
    }

    public void setSource_of_fund(String source_of_fund) {
        this.source_of_fund = source_of_fund;
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
