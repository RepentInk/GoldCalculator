package Models;

/**
 *
 * @author nyark
 */
public class Receipt {

    private double totalAmount;
    private double basePrice;
    private double amountPaid;
    private double balance;
    private double credit_balance;

    public Receipt() {
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getCredit_balance() {
        return credit_balance;
    }

    public void setCredit_balance(double credit_balance) {
        this.credit_balance = credit_balance;
    }

}
