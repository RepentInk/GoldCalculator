package Models;

/**
 *
 * @author nyark
 */
public class Credit {

    private int id;
    private String code;
    private int customer_id;
    private int budget_id;
    private double amount;
    private double previous_balance;
    private double budget_before;
    private double budget_after;
    private int user_id;
    private String created_date;
    private String created_time;
    private String raw_date;

    private String budget;
    private String customer;
    private String user;

    public Credit() {
    }

    public Credit(
            String code,
            int customer_id,
            int budget_id,
            double amount,
            double previous_balance,
            double budget_before,
            double budget_after,
            int user_id,
            String created_date,
            String created_time,
            String raw_date
    ) {
        this.code = code;
        this.customer_id = customer_id;
        this.budget_id = budget_id;
        this.amount = amount;
        this.previous_balance = previous_balance;
        this.budget_before = budget_before;
        this.budget_after = budget_after;
        this.user_id = user_id;
        this.created_date = created_date;
        this.created_time = created_time;
        this.raw_date = raw_date;
    }

    public Credit(
            int id,
            int customer_id,
            int budget_id,
            double amount,
            double previous_balance,
            double budget_before,
            double budget_after,
            int user_id,
            String created_date,
            String created_time,
            String raw_date
    ) {
        this.id = id;
        this.customer_id = customer_id;
        this.budget_id = budget_id;
        this.amount = amount;
        this.previous_balance = previous_balance;
        this.budget_before = budget_before;
        this.budget_after = budget_after;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getBudget_id() {
        return budget_id;
    }

    public void setBudget_id(int budget_id) {
        this.budget_id = budget_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrevious_balance() {
        return previous_balance;
    }

    public void setPrevious_balance(double previous_balance) {
        this.previous_balance = previous_balance;
    }

    public double getBudget_before() {
        return budget_before;
    }

    public void setBudget_before(double budget_before) {
        this.budget_before = budget_before;
    }

    public double getBudget_after() {
        return budget_after;
    }

    public void setBudget_after(double budget_after) {
        this.budget_after = budget_after;
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

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
