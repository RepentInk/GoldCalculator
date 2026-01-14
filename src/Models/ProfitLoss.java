package Models;

/**
 *
 * @author nyark
 */
public class ProfitLoss {

    private int id;
    private String start_date;
    private String end_date;
    private double total_budget;
    private double budget_left;
    private double total_expenses;
    private double amount_from_gold;
    private double profit_loss;
    private String created_time;
    private String created_date;
    private String raw_date;

    public ProfitLoss() {
    }

    public ProfitLoss(
            double total_budget,
            double budget_left,
            double total_expenses,
            double amount_from_gold,
            double profit_loss,
            String start_date,
            String end_date,
            String created_time,
            String created_date,
            String raw_date
    ) {
        this.total_budget = total_budget;
        this.budget_left = budget_left;
        this.total_expenses = total_expenses;
        this.amount_from_gold = amount_from_gold;
        this.profit_loss = profit_loss;
        this.start_date = start_date;
        this.end_date = end_date;
        this.created_time = created_time;
        this.created_date = created_date;
        this.raw_date = raw_date;
    }

    public ProfitLoss(
            int id,
            double total_budget,
            double budget_left,
            double total_expenses,
            double amount_from_gold,
            double profit_loss,
            String start_date,
            String end_date,
            String created_time,
            String created_date,
            String raw_date
    ) {
        this.id = id;
        this.total_budget = total_budget;
        this.budget_left = budget_left;
        this.total_expenses = total_expenses;
        this.amount_from_gold = amount_from_gold;
        this.profit_loss = profit_loss;
        this.start_date = start_date;
        this.end_date = end_date;
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

    public double getTotal_budget() {
        return total_budget;
    }

    public void setTotal_budget(double total_budget) {
        this.total_budget = total_budget;
    }

    public double getBudget_left() {
        return budget_left;
    }

    public void setBudget_left(double budget_left) {
        this.budget_left = budget_left;
    }

    public double getTotal_expenses() {
        return total_expenses;
    }

    public void setTotal_expenses(double total_expenses) {
        this.total_expenses = total_expenses;
    }

    public double getAmount_from_gold() {
        return amount_from_gold;
    }

    public void setAmount_from_gold(double amount_from_gold) {
        this.amount_from_gold = amount_from_gold;
    }

    public double getProfit_loss() {
        return profit_loss;
    }

    public void setProfit_loss(double profit_loss) {
        this.profit_loss = profit_loss;
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

    public String getRaw_date() {
        return raw_date;
    }

    public void setRaw_date(String raw_date) {
        this.raw_date = raw_date;
    }

}
