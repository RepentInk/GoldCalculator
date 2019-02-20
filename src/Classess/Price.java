package Classess;

public class Price {

    private int id;
    private double price;
    private String date;

    public Price() {
    }

    public Price(double price, String date) {
        this.price = price;
        this.date = date;
    }

    public Price(int id, double price, String date) {
        this.id = id;
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
