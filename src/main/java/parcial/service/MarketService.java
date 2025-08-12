package parcial.service;

import parcial.model.Cryptocoin;
import parcial.model.Transaction;
import parcial.model.User;
import parcial.type.TransactionType;

import java.util.*;

public class MarketService implements MarketTransaction {

    Queue<Transaction> marketTransaction = new LinkedList<>();

    static Random rand = new Random();

    public static int RandomNum(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public static ArrayList<Cryptocoin> fluctuarPrecios(ArrayList<Cryptocoin> criptos) {
        ArrayList<Cryptocoin> resultado = new ArrayList<>();
        for (Cryptocoin c : criptos) {
            int cambioSigno = RandomNum(0, 1) == 1 ? 1 : -1;
            double cambio = cambioSigno * (RandomNum(1, 10) / 100.0);
            double precio = Double.parseDouble(c.getPrice_usd());
            double nuevoPrecio = Math.max(1, precio + precio * cambio);
            resultado.add(new Cryptocoin(c.getId(), c.getSymbol(), c.getName(), String.format("%.2f", nuevoPrecio)));
        }
        return resultado;
    }

    public static void simularTurno(int turno, List<Cryptocoin> criptos, ArrayList<User> usuarios, Queue libroOrdenes) {

        System.out.println("\n--- Turno " + turno + " ---");

        for (int i = 0; i < criptos.size(); i++) {
            ArrayList<Cryptocoin> temp = new ArrayList<>();
            temp.add(criptos.get(i));
            criptos.set(i, fluctuarPrecios(temp).get(0));
        }

        System.out.print("Precios: ");
        for (int i = 0; i < criptos.size(); i++) {
            Cryptocoin c = criptos.get(i);
            System.out.print(c.getName() + ": $" + c.getPrice_usd());
            if (i < criptos.size() - 1) System.out.print(", ");
        }

        for (User usuario : usuarios) {
            int opera = RandomNum(0, 1);
            if (opera == 1) {
                Cryptocoin cripto = criptos.get(RandomNum(0, criptos.size() - 1));
                String tipo = RandomNum(0, 1) == 1 ? "compra" : "venta";
                int cantidad = RandomNum(1, 5);


                Transaction transaccion = new Transaction(usuario.getNombre(), cripto.getName(), Double.parseDouble(cripto.getPrice_usd()));
                libroOrdenes.add(transaccion);
                System.out.println(usuario.getNombre() + " quiere " + tipo + " " + cantidad + " " + cripto.getName() + " a $" + cripto.getPrice_usd());

                if(tipo.equals("compra")){
                    usuario.getWallet().addItem(new Cryptocoin(cripto.getId(), cripto.getSymbol(), cripto.getName(), cripto.getPrice_usd()));
                }

                for (Cryptocoin cryptocoin : usuario.getWallet().getItems()){
                    System.out.println(usuario.getNombre() + " tiene en su wallet " + cryptocoin.getName());
                }

            } else {
                System.out.println(usuario.getNombre() + " no opera este turno.");
            }
        }
    }

    @Override
    public void Buy(String symbol, double amount) {
        System.out.println("Compra de " + amount + " unidades de " + symbol + " realizada.");
    }

    @Override
    public void Sell(String symbol, double amount) {
        System.out.println("Venta de " + amount + " unidades de " + symbol + " realizada.");
    }

}
