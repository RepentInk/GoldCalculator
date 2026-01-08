package Models;

/**
 *
 * @author nyark
 */
public class Payments {

    private int id;
    private int buy_gold_id;
    private double amount_paid;
    private double balance;
    private double total_amount;
    private int user_id;
    private String created_date;
    private String created_time;
    private String raw_date;

    private String user;
    private String buy_gold;
    private String customer;

    public Payments() {
    }

    public Payments(
            int buy_gold_id,
            double amount_paid,
            double balance,
            int user_id,
            String created_date,
            String created_time,
            String raw_date
    ) {
        this.buy_gold_id = buy_gold_id;
        this.amount_paid = amount_paid;
        this.balance = balance;
        this.user_id = user_id;
        this.created_date = created_date;
        this.created_time = created_time;
        this.raw_date = raw_date;
    }

    public Payments(
            int id,
            int buy_gold_id,
            double amount_paid,
            double balance,
            int user_id,
            String created_date,
            String created_time,
            String raw_date
    ) {
        this.id = id;
        this.buy_gold_id = buy_gold_id;
        this.amount_paid = amount_paid;
        this.balance = balance;
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

    public int getBuy_gold_id() {
        return buy_gold_id;
    }

    public void setBuy_gold_id(int buy_gold_id) {
        this.buy_gold_id = buy_gold_id;
    }

    public double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    public String getBuy_gold() {
        return buy_gold;
    }

    public void setBuy_gold(String buy_gold) {
        this.buy_gold = buy_gold;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getRaw_date() {
        return raw_date;
    }

    public void setRaw_date(String raw_date) {
        this.raw_date = raw_date;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

}
