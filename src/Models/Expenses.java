package Models;

/**
 *
 * @author nyark
 */
public class Expenses {

    private int id;
    private int expenses_type_id;
    private double amount;
    private String paid_to;
    private int user_id;
    private String created_time;
    private String created_date;
    private String raw_date;

    private String expense_type;
    private String user;

    public Expenses() {
    }

    public Expenses(
            int expenses_type_id,
            double amount,
            String paid_to,
            int user_id,
            String created_time,
            String created_date,
            String raw_date
    ) {
        this.expenses_type_id = expenses_type_id;
        this.amount = amount;
        this.paid_to = paid_to;
        this.user_id = user_id;
        this.created_time = created_time;
        this.created_date = created_date;
        this.raw_date = raw_date;
    }

    public Expenses(
            int id,
            int expenses_type_id,
            double amount,
            String paid_to,
            int user_id,
            String created_time,
            String created_date,
            String raw_date
    ) {
        this.id = id;
        this.expenses_type_id = expenses_type_id;
        this.amount = amount;
        this.paid_to = paid_to;
        this.user_id = user_id;
        this.created_time = created_time;
        this.created_date = created_date;
        this.raw_date = raw_date;
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

    public String getRaw_date() {
        return raw_date;
    }

    public void setRaw_date(String raw_date) {
        this.raw_date = raw_date;
    }

}
