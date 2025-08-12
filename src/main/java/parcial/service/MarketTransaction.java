package parcial.service;

public interface MarketTransaction {

    void Buy(String symbol, double amount);

    void Sell(String symbol, double amount);

}
