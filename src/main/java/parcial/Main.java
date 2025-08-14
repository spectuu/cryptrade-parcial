package parcial;

import parcial.model.Cryptocoin;
import parcial.model.Transaction;
import parcial.model.User;
import parcial.service.HttpService;
import parcial.service.MarketService;
import parcial.service.ReportService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        MarketService marketService = new MarketService();

        List<Cryptocoin> cryptos = HttpService.get("https://api.coinlore.net/api/tickers/");

        if (cryptos == null || cryptos.isEmpty()) {
            System.out.println("Cant fetch cryptocurrencies.");
            return;
        }

        List<User> users = new ArrayList<>();
        users.add(new User("Santiago", 100000));
        users.add(new User("Luis", 50000));
        users.add(new User("Maria", 75000));
        users.add(new User("Juan", 200000));

        Queue<Transaction> orderBook = new LinkedList<>(); // se usa la queue para el libro de órdenes porque se necesita un orden FIFO (First In, First Out)

        final int TOTAL_TURNS = 5;

        for (int turn = 1; turn <= TOTAL_TURNS; turn++) {
            marketService.simulateTurn(turn, cryptos, users, orderBook);
        }

        ReportService.generateFinalReport(users, cryptos);

    }

}