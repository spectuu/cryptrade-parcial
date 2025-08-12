package parcial.model;

import parcial.type.TransactionType;

import java.util.Date;

public class Transaction {

    private String user;
    private String crypto;
    private double price;

    public Transaction(String user, String crypto, double price) {
        this.user = user;
        this.crypto = crypto;
        this.price = price;
    }

    @Override
    public String toString() {
        return user + " quiere operar " + price + " a $" + String.format("%.2f", price);
    }

}
