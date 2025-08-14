package parcial.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parcial.model.Cryptocoin;
import parcial.model.Transaction;
import parcial.model.User;
import parcial.model.type.TransactionType;

import java.util.Stack;

public class ProcessTransactionService {

    private static final Logger logger = LogManager.getLogger(ProcessTransactionService.class);

    public static final double USD_TO_COP = 4000.0;

    public static void processBuyTransaction(User user, Cryptocoin coin, int quantity , TransactionType transactionType) {

        logger.info("User {} balance before buy: {} COP", user.getName(), user.getBalance());

        double priceUSD = Double.parseDouble(coin.getPrice_usd());
        double totalCostUSD = priceUSD * quantity;
        double totalCostCOP = totalCostUSD * USD_TO_COP;

        if (user.getBalance() < totalCostCOP) {
            logger.warn("{} tried to buy {} units of {} at ${} USD but has insufficient balance. Needed: {} COP, Available: {} COP", user.getName(), quantity, coin.getName(), priceUSD, totalCostCOP, user.getBalance());
            System.out.println(user.getName() + " tried to buy " + quantity + " units of " + coin.getName() + " at $" + priceUSD + " USD but has insufficient balance. Needed: " + totalCostCOP + " COP, Available: " + user.getBalance() + " COP");
            return;
        }

        user.setBalance(user.getBalance() - totalCostCOP);

        for (int i = 0; i < quantity; i++) {
            user.getWallet().addItem(new Cryptocoin(
                    coin.getId(),
                    coin.getSymbol(),
                    coin.getName(),
                    coin.getPrice_usd()
            ));
        }

        Transaction tx = new Transaction(user.getName(), coin.getName(), Double.parseDouble(coin.getPrice_usd()), transactionType);
        user.addTransaction(tx);

    }

    public static void processSellTransaction(User user, Cryptocoin coin, int quantity , TransactionType transactionType){

        logger.info("User {} balance before buy: {} COP", user.getName(), user.getBalance());

        int ownedCount = 0;

        for (Cryptocoin c : user.getWallet().getItems()) {
            if (c.getName().equals(coin.getName())) {
                ownedCount++;
            }
        }

        if (ownedCount < quantity) {
            logger.warn("{} tried to sell {} units of {} at ${} USD but has insufficient coins. Owned: {}, Trying to sell: {}", user.getName(), quantity, coin.getName(), coin.getPrice_usd(), ownedCount, quantity);
            System.out.println(user.getName() + " tried to sell " + quantity + " units of " + coin.getName() + " at $" + coin.getPrice_usd() + " but has insufficient coins. Owned: " + ownedCount + ", Trying to sell: " + quantity);
            return;
        }

        int removed = 0;

        Stack<Cryptocoin> tempStack = new Stack<>();
        while (!user.getWallet().isEmpty()) {
            Cryptocoin current = user.getWallet().removeItem();
            if (current.getName().equals(coin.getName()) && removed < quantity) {
                removed++;
            } else {
                tempStack.push(current);
            }
        }

        while (!tempStack.isEmpty()) {
            user.getWallet().addItem(tempStack.pop());
        }

        Transaction tx = new Transaction(user.getName(), coin.getName(), Double.parseDouble(coin.getPrice_usd()), transactionType);
        user.addTransaction(tx);

    }

}
