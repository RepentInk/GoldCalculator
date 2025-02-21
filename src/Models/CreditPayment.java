package Models;

/**
 *
 * @author nyark
 */
public class CreditPayment {

    private int id;
    private int credit_id;
    private int customer_id;
    private double paid;
    private double balance;
    private boolean paid_from;
    private int user_id;
    private String created_date;
    private String created_time;
    private String raw_date;

    private String user;

    public CreditPayment() {
    }

    public CreditPayment(
            int credit_id,
            int customer_id,
            double paid,
            double balance,
            boolean paid_from,
            int user_id,
            String created_date,
            String created_time,
            String raw_date
    ) {
        this.credit_id = credit_id;
        this.customer_id = customer_id;
        this.paid = paid;
        this.balance = balance;
        this.paid_from = paid_from;
        this.user_id = user_id;
        this.created_date = created_date;
        this.created_time = created_time;
        this.raw_date = raw_date;
    }

    public CreditPayment(
            int id,
            int credit_id,
            int customer_id,
            double paid,
            double balance,
            boolean paid_from,
            int user_id,
            String created_date,
            String created_time,
            String raw_date
    ) {
        this.id = id;
        this.credit_id = credit_id;
        this.customer_id = customer_id;
        this.paid = paid;
        this.balance = balance;
        this.paid_from = paid_from;
        this.user_id = user_id;
        this.created_date = created_date;
        this.created_time = created_time;
        this.raw_date = raw_date;
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

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean getPaid_from() {
        return paid_from;
    }

    public void setPaid_from(boolean paid_from) {
        this.paid_from = paid_from;
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

    public String getRaw_date() {
        return raw_date;
    }

    public void setRaw_date(String raw_date) {
        this.raw_date = raw_date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
