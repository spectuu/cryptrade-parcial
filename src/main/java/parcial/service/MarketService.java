package parcial.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parcial.interfaces.MarketTransaction;
import parcial.model.Cryptocoin;
import parcial.model.Transaction;
import parcial.model.User;
import parcial.model.type.TransactionType;

import java.util.*;

public class MarketService implements MarketTransaction {

    private static final Logger logger = LogManager.getLogger(MarketService.class);

    public static List<Cryptocoin> fluctuatePrices(List<Cryptocoin> cryptos) {
        List<Cryptocoin> result = new ArrayList<>();
        for (Cryptocoin c : cryptos) {
            int sign = getRandomInt(0, 1) == 1 ? 1 : -1;
            double change = sign * (getRandomInt(1, 10) / 100.0);
            double currentPrice = Double.parseDouble(c.getPrice_usd());
            double newPrice = Math.max(1, currentPrice + currentPrice * change);
            result.add(new Cryptocoin(
                    c.getId(),
                    c.getSymbol(),
                    c.getName(),
                    String.format("%.2f", newPrice)
            ));
        }
        return result;
    }

    public void simulateTurn(int turn, List<Cryptocoin> cryptos, List<User> users, Queue<Transaction> orderBook) {

        logger.info("--- Turn {} ---", turn);
        System.out.println("\n--- Turn " + turn + " ---");


        for (int i = 0; i < cryptos.size(); i++) {
            List<Cryptocoin> temp = new ArrayList<>();
            temp.add(cryptos.get(i));
            cryptos.set(i, fluctuatePrices(temp).getFirst());
        }

        System.out.print("Prices: ");
        for (int i = 0; i < cryptos.size(); i++) {
            Cryptocoin c = cryptos.get(i);
            System.out.print(c.getName() + ": $" + c.getPrice_usd());
            if (i < cryptos.size() - 1) System.out.print(", ");
        }

        for (User user : users) {

            boolean willTrade = getRandomInt(0, 1) == 1;

            if (willTrade) {
                Cryptocoin chosenCrypto = cryptos.get(getRandomInt(0, cryptos.size() - 1));
                boolean isBuy = getRandomInt(0, 1) == 1;
                int quantity = getRandomInt(1, 5);

                Transaction transaction = new Transaction(
                        user.getName(),
                        chosenCrypto.getName(),
                        Double.parseDouble(chosenCrypto.getPrice_usd())
                        , isBuy ? TransactionType.BUY : TransactionType.SELL
                );

                orderBook.add(transaction);

                if (isBuy) {
                    buy(user, chosenCrypto, quantity, TransactionType.BUY);
                } else {
                    sell(user, chosenCrypto, quantity, TransactionType.SELL);
                }

                for (Cryptocoin coin : user.getWallet().getItems()) {
                    logger.info("{} has {} in wallet.", user.getName(), coin.getName());
                    System.out.println(user.getName() + " has " + coin.getName() + " in wallet.");
                }

            } else {
                logger.info("{} decides not to trade this turn.", user.getName());
                System.out.println(user.getName() + " decides not to trade this turn.");
            }

        }
    }

    @Override
    public void buy(User user, Cryptocoin coin, int quantity, TransactionType transactionType) {

        ProcessTransactionService.processBuyTransaction(user, coin, quantity, transactionType);

        logger.info("{} buys {} units of {} at ${} new balance: {} in COP", user.getName(), quantity, coin.getName(), coin.getPrice_usd(), user.getBalance());
        System.out.println(user.getName() + " buys " + quantity + " units of " + coin.getName() + " at $" + coin.getPrice_usd() + "new balance: " + user.getBalance() + " COP");

    }

    @Override
    public void sell(User user, Cryptocoin coin, int quantity, TransactionType transactionType) {

        ProcessTransactionService.processSellTransaction(user, coin, quantity, transactionType);

        logger.info("{} sold {} units of {} at ${}", user.getName(), quantity, coin.getName(), coin.getPrice_usd());
        System.out.println(user.getName() + " sold " + quantity + " units of " + coin.getName() + " at $" + coin.getPrice_usd());

    }

    private static final Random rand = new Random();

    public static int getRandomInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

}


