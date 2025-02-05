package Models;

public class BuyGold {

    private int id;
    private String code;
    private double top;
    private double down;
    private double density;
    private double karat;
    private double pounds;
    private double base_price;
    private double total_weight;
    private double total_amount;
    private String created_date;
    private String created_time;
    private String raw_date;

    private int customer_id;
    private int user_id;

    private String user;
    private String customer;

    public BuyGold() {
    }

    public BuyGold(
            String code,
            double top,
            double down,
            double density,
            double karat,
            double pounds,
            double base_price,
            double total_weight,
            double total_amount,
            String created_date,
            String created_time,
            String raw_date,
            int customer_id,
            int user_id
    ) {
        this.code = code;
        this.top = top;
        this.down = down;
        this.density = density;
        this.karat = karat;
        this.pounds = pounds;
        this.base_price = base_price;
        this.total_weight = total_weight;
        this.total_amount = total_amount;
        this.created_date = created_date;
        this.created_time = created_time;
        this.raw_date = raw_date;
        this.customer_id = customer_id;
        this.user_id = user_id;
    }

    public BuyGold(
            int id,
            String code,
            double top,
            double down,
            double density,
            double karat,
            double pounds,
            double base_price,
            double total_weight,
            double total_amount,
            String created_date,
            String created_time,
            String raw_date,
            int customer_id,
            int user_id
    ) {
        this.id = id;
        this.code = code;
        this.top = top;
        this.down = down;
        this.density = density;
        this.karat = karat;
        this.pounds = pounds;
        this.base_price = base_price;
        this.total_weight = total_weight;
        this.total_amount = total_amount;
        this.created_date = created_date;
        this.created_time = created_time;
        this.raw_date = raw_date;
        this.customer_id = customer_id;
        this.user_id = user_id;
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

    public double getPounds() {
        return pounds;
    }

    public void setPounds(double pounds) {
        this.pounds = pounds;
    }

    public double getBase_price() {
        return base_price;
    }

    public void setBase_price(double base_price) {
        this.base_price = base_price;
    }

    public double getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(double total_weight) {
        this.total_weight = total_weight;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

}
