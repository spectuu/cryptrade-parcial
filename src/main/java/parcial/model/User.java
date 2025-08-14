package parcial.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Stack;
import java.util.UUID;

public class User {

    private static final Logger logger = LogManager.getLogger(User.class);

    private final UUID id;
    private final String name;
    private double balance; // Balance in COP

    private final Wallet<Cryptocoin> wallet;
    private Stack<Transaction> transactionHistory;

    public User(){
        this.id = UUID.randomUUID();
        this.name = "Default User";
        this.balance = 0;
        this.wallet = new Wallet<>();
        this.transactionHistory = new Stack<>();
    }

    public User(String name, int balance) {

        if (name == null || name.isEmpty() || balance < 0) {
            logger.error("Invalid user data: name={}, balance={}", name, balance);
            throw new IllegalArgumentException("Name must be provided and balance must be non-negative");
        }

        try {

            this.id = UUID.randomUUID();
            this.name = name;
            this.wallet = new Wallet<>();
            this.transactionHistory = new Stack<>();
            this.balance = balance;

        } catch (Exception e) {
            logger.error("Error creating user: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid data provided: " + e.getMessage());
        }

    }

    public UUID getId() { return id; }

    public String getName() { return name; }

    public double getBalance() { return balance; }

    public Wallet<Cryptocoin> getWallet() { return wallet; }

    public Stack<Transaction> getTransactionHistory() { return transactionHistory; }

    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            logger.error("Transaction cannot be null");
            throw new IllegalArgumentException("Transaction cannot be null");
        }

        transactionHistory.push(transaction);
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            logger.error("Balance cannot be negative: {}", balance);
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

}
