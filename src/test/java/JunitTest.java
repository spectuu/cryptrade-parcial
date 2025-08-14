import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parcial.model.Cryptocoin;
import parcial.model.User;
import parcial.model.type.TransactionType;
import parcial.service.MarketService;
import parcial.service.ProcessTransactionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JunitTest {

    private User user;
    private Cryptocoin bitcoin;

    @BeforeEach
    void setUp() {
        user = new User("Test User", 10_000_000);
        bitcoin = new Cryptocoin("1", "BTC", "Bitcoin", "1000");
    }

    @Test
    void testBuyTransactionSuccess() {
        ProcessTransactionService.processBuyTransaction(user, bitcoin, 2, TransactionType.BUY);
        assertEquals(10_000_000 - (2 * 1000 * ProcessTransactionService.USD_TO_COP), user.getBalance());
        assertEquals(2, user.getWallet().size());
        assertEquals(1, user.getTransactionHistory().size());
        assertEquals(TransactionType.BUY, user.getTransactionHistory().peek().getType());
    }

    @Test
    void testBuyTransactionInsufficientFunds() {
        user.setBalance(1_000);
        ProcessTransactionService.processBuyTransaction(user, bitcoin, 2, TransactionType.BUY);
        assertEquals(1_000, user.getBalance());
        assertEquals(0, user.getWallet().size());
        assertTrue(user.getTransactionHistory().isEmpty());
    }

    @Test
    void testSellTransactionInsufficientCoins() {

        ProcessTransactionService.processSellTransaction(user, bitcoin, 2, TransactionType.SELL);
        assertEquals(0, user.getWallet().size());
        assertTrue(user.getTransactionHistory().isEmpty());
    }

    @Test
    void testSellTransactionSuccess() {
        ProcessTransactionService.processBuyTransaction(user, bitcoin, 2, TransactionType.BUY);
        ProcessTransactionService.processSellTransaction(user, bitcoin, 1, TransactionType.SELL);
        assertEquals(6000000.0 - (1 * 1000 * ProcessTransactionService.USD_TO_COP), user.getBalance());
        assertEquals(1, user.getWallet().size());
        assertEquals(2, user.getTransactionHistory().size());
        assertEquals(TransactionType.SELL, user.getTransactionHistory().peek().getType());
    }


}
