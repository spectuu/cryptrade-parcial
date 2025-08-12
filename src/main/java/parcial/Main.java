package parcial;

import parcial.model.Cryptocoin;
import parcial.model.User;
import parcial.service.HttpService;
import parcial.service.MarketService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        List<Cryptocoin> criptos = HttpService.get("https://api.coinlore.net/api/tickers/");

        ArrayList<User> usuarios = new ArrayList<>();
        usuarios.add(new User("0"));
        usuarios.add(new User("Luis"));
        usuarios.add(new User("Maria"));
        usuarios.add(new User("Juan"));

        Queue libroOrdenes = new LinkedList();

        int TURNOS = 5;
        for (int t = 1; t <= TURNOS; t++) {
            MarketService.simularTurno(t, criptos, usuarios, libroOrdenes);
        }

        System.out.println("\nLibro de órdenes:");
        while (!libroOrdenes.isEmpty()) {
            System.out.println(libroOrdenes.poll());
        }

    }

}