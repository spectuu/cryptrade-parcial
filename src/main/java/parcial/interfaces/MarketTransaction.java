package parcial.interfaces;

import parcial.model.Cryptocoin;
import parcial.model.User;
import parcial.model.type.TransactionType;

public interface MarketTransaction {

    void buy(User user, Cryptocoin coin, int quantity, TransactionType transactionType);

    void sell(User user, Cryptocoin coin, int quantity, TransactionType transactionType);

}
