package Models;

public class Pricing {

    private int id;
    private double current_price;
    private double old_price;
    private double top_divide_value;
    private double density_minus_value;
    private double density_multiply_value;
    private double karat_divide_value;
    private String created_date;

    public Pricing() {
    }

    public Pricing(
            double current_price,
            double old_price,
            double top_divide_value,
            double density_minus_value,
            double density_multiply_value,
            double karat_divide_value,
            String created_date
    ) {
        this.current_price = current_price;
        this.old_price = old_price;
        this.top_divide_value = top_divide_value;
        this.density_minus_value = density_minus_value;
        this.density_multiply_value = density_multiply_value;
        this.karat_divide_value = karat_divide_value;
        this.created_date = created_date;
    }

    public Pricing(
            int id,
            double current_price,
            double old_price,
            double top_divide_value,
            double density_minus_value,
            double density_multiply_value,
            double karat_divide_value,
            String created_date
    ) {
        this.id = id;
        this.current_price = current_price;
        this.old_price = old_price;
        this.top_divide_value = top_divide_value;
        this.density_minus_value = density_minus_value;
        this.density_multiply_value = density_multiply_value;
        this.karat_divide_value = karat_divide_value;
        this.created_date = created_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(double current_price) {
        this.current_price = current_price;
    }

    public double getOld_price() {
        return old_price;
    }

    public void setOld_price(double old_price) {
        this.old_price = old_price;
    }

    public double getTop_divide_value() {
        return top_divide_value;
    }

    public void setTop_divide_value(double top_divide_value) {
        this.top_divide_value = top_divide_value;
    }

    public double getDensity_minus_value() {
        return density_minus_value;
    }

    public void setDensity_minus_value(double density_minus_value) {
        this.density_minus_value = density_minus_value;
    }

    public double getDensity_multiply_value() {
        return density_multiply_value;
    }

    public void setDensity_multiply_value(double density_multiply_value) {
        this.density_multiply_value = density_multiply_value;
    }

    public double getKarat_divide_value() {
        return karat_divide_value;
    }

    public void setKarat_divide_value(double karat_divide_value) {
        this.karat_divide_value = karat_divide_value;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

}
