package parcial.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Stack;

public class UserReport {

    private static final Logger logger = LogManager.getLogger(UserReport.class);

    private String id;
    private String name;
    private double finalBalanceCOP;
    private double finalBalanceUSD;
    private double portfolioValueCOP;
    private Wallet<Cryptocoin> portfolio;
    private Stack<Transaction> transactions;

    public UserReport(String id, String name, double finalBalanceCOP, double finalBalanceUSD, double portfolioValueCOP, Wallet<Cryptocoin> portfolio, Stack<Transaction> transactions) {

        if ( id == null || id.isEmpty() || name == null || name.isEmpty()
                || portfolio == null
                || transactions == null
                || finalBalanceCOP < 0
                || finalBalanceUSD < 0
                || portfolioValueCOP < 0) {
            logger .error("Invalid user report data: id={}, name={}, finalBalanceCOP={}, finalBalanceUSD={}, portfolioValueCOP={} portfolio={}, transactions={}", id, name, finalBalanceCOP, finalBalanceUSD, portfolioValueCOP, portfolio, transactions);
            throw new IllegalArgumentException("All fields must be provided and non-empty");
        }

        if (portfolio.getItems() == null) {
            logger.error("Portfolio cannot be null: {}", portfolio);
            throw new IllegalArgumentException("Portfolio cannot be empty");
        }

        try {

            this.id = id;
            this.name = name;
            this.finalBalanceCOP = finalBalanceCOP;
            this.finalBalanceUSD = finalBalanceUSD;
            this.portfolioValueCOP = portfolioValueCOP;
            this.portfolio = portfolio;
            this.transactions = transactions;

        } catch (Exception e){
            logger.error("Error creating UserReport: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid data provided: " + e.getMessage());
        }

    }

    public String getId() { return id; }

    public String getName() { return name; }

    public double getFinalBalanceCOP() { return finalBalanceCOP; }

    public double getPortfolioValueCOP() { return finalBalanceUSD; }

    public double getFinalBalanceUSD() { return finalBalanceUSD; }

    public double getPortfolioValueUSD() { return portfolioValueCOP; }

    public Wallet<Cryptocoin> getPortfolio() { return portfolio; }

    public Stack<Transaction> getTransactions() { return transactions; }

}
