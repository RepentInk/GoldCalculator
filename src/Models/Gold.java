package Models;

public class Gold {

    private int id;
    private int customer_id;
    private int user_id;

    private double top;
    private double down;
    private double density;
    private double karat;
    private double base;
    private double value;
    private double money;

    private String created_at;

    public Gold() {
    }

    public Gold(int customer_id, int user_id, double top, double down, double density, double karat, double base, double value, double money, String created_at) {
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.top = top;
        this.down = down;
        this.density = density;
        this.karat = karat;
        this.base = base;
        this.value = value;
        this.money = money;
        this.created_at = created_at;
    }

    public Gold(int id, int customer_id, int user_id, double top, double down, double density, double karat, double base, double value, double money, String created_at) {
        this.id = id;
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.top = top;
        this.down = down;
        this.density = density;
        this.karat = karat;
        this.base = base;
        this.value = value;
        this.money = money;
        this.created_at = created_at;
    }

    public Gold(double top, double down, double density, double karat, double base, double value, double money) {
        this.top = top;
        this.down = down;
        this.density = density;
        this.karat = karat;
        this.base = base;
        this.value = value;
        this.money = money;
    }

    public Gold(int id, double top, double down, double density, double karat, double base, double value, double money) {
        this.id = id;
        this.top = top;
        this.down = down;
        this.density = density;
        this.karat = karat;
        this.base = base;
        this.value = value;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getTop() {
        return top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public double getDown() {
        return down;
    }

    public void setDown(double down) {
        this.down = down;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getKarat() {
        return karat;
    }

    public void setKarat(double karat) {
        this.karat = karat;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    
}
