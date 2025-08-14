package parcial.model;

import parcial.model.type.TransactionType;

public class Transaction {

    private String user;
    private String crypto;
    private double price;
    private final TransactionType type;

    public Transaction(String user, String crypto, double price, TransactionType type) {
        this.user = user;
        this.crypto = crypto;
        this.price = price;
        this.type = type;
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

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return user + type.toString().toLowerCase() + " " + crypto + " at $" + price + " USD";
    }

}
