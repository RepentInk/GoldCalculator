package Models;

/**
 *
 * @author nyark
 */
public class Expenses {

    private int id;
    private int expenses_type_id;
    private int budget_id;
    private double amount;
    private String paid_to;
    private double budget_before;
    private double budget_after;
    private int user_id;
    private String created_time;
    private String created_date;

    private String expense_type;
    private String user;
    private String budget;

    public Expenses() {
    }

    public Expenses(
            int expenses_type_id,
            int budget_id,
            double amount,
            String paid_to,
            double budget_before,
            double budget_after,
            int user_id,
            String created_time,
            String created_date
    ) {
        this.expenses_type_id = expenses_type_id;
        this.budget_id = budget_id;
        this.amount = amount;
        this.paid_to = paid_to;
        this.budget_before = budget_before;
        this.budget_after = budget_after;
        this.user_id = user_id;
        this.created_time = created_time;
        this.created_date = created_date;
    }

    public Expenses(
            int id,
            int expenses_type_id,
            int budget_id,
            double amount,
            String paid_to,
            double budget_before,
            double budget_after,
            int user_id,
            String created_time,
            String created_date
    ) {
        this.id = id;
        this.expenses_type_id = expenses_type_id;
        this.budget_id = budget_id;
        this.amount = amount;
        this.paid_to = paid_to;
        this.budget_before = budget_before;
        this.budget_after = budget_after;
        this.user_id = user_id;
        this.created_time = created_time;
        this.created_date = created_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExpenses_type_id() {
        return expenses_type_id;
    }

    public void setExpenses_type_id(int expenses_type_id) {
        this.expenses_type_id = expenses_type_id;
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

    public String getPaid_to() {
        return paid_to;
    }

    public void setPaid_to(String paid_to) {
        this.paid_to = paid_to;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getExpense_type() {
        return expense_type;
    }

    public void setExpense_type(String expense_type) {
        this.expense_type = expense_type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
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

}
