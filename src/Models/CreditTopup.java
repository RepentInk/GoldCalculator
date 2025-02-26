package Models;

/**
 *
 * @author nyark
 */
public class CreditTopup {

    private int id;
    private int credit_id;
    private double amount_paid;
    private double total_amount;
    private int user_id;
    private String created_date;
    private String created_time;

    private String user;

    public CreditTopup() {
    }

    public CreditTopup(int credit_id, double amount_paid, double total_amount, int user_id, String created_date, String created_time) {
        this.credit_id = credit_id;
        this.amount_paid = amount_paid;
        this.total_amount = total_amount;
        this.user_id = user_id;
        this.created_date = created_date;
        this.created_time = created_time;
    }

    public CreditTopup(int id, int credit_id, double amount_paid, double total_amount, int user_id, String created_date, String created_time) {
        this.id = id;
        this.credit_id = credit_id;
        this.amount_paid = amount_paid;
        this.total_amount = total_amount;
        this.user_id = user_id;
        this.created_date = created_date;
        this.created_time = created_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(int credit_id) {
        this.credit_id = credit_id;
    }

    public double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
