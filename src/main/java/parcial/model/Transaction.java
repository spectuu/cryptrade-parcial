package parcial.model;

public class Transaction {

    private String user;
    private String crypto;
    private double price;

    public Transaction(String user, String crypto, double price) {
        this.user = user;
        this.crypto = crypto;
        this.price = price;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCrypto() {
        return crypto;
    }

    public void setCrypto(String crypto) {
        this.crypto = crypto;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return user + " quiere operar " + price + " a $" + String.format("%.2f", price);
    }

}
