package Models;

/**
 *
 * @author nyark
 */
public class BudgetAddition {

    private int id;
    private int budget_id;
    private String source;
    private double amount;
    private String created_date;
    private String created_time;
    private int user_id;

    private String user;

    public BudgetAddition() {
    }

    public BudgetAddition(int budget_id, String source, double amount, String created_date, String created_time, int user_id) {
        this.budget_id = budget_id;
        this.source = source;
        this.amount = amount;
        this.created_date = created_date;
        this.created_time = created_time;
        this.user_id = user_id;
    }

    public BudgetAddition(int id, int budget_id, String source, double amount, String created_date, String created_time, int user_id) {
        this.id = id;
        this.budget_id = budget_id;
        this.source = source;
        this.amount = amount;
        this.created_date = created_date;
        this.created_time = created_time;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBudget_id() {
        return budget_id;
    }

    public void setBudget_id(int budget_id) {
        this.budget_id = budget_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
