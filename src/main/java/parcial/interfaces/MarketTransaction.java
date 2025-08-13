package parcial.interfaces;

import parcial.model.Cryptocoin;
import parcial.model.User;

public interface MarketTransaction {

    void buy(User user, Cryptocoin coin, int quantity);

    void sell(User user, Cryptocoin coin, int quantity);

}
