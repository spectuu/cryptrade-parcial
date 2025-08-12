package parcial.model;

public class User {

    private String nombre;

    private final Wallet<Cryptocoin> wallet;

    public User(String nombre) {
        this.nombre = nombre;
        this.wallet = new Wallet<>();
    }

    public String getNombre() { return nombre; }

    public Wallet<Cryptocoin> getWallet() { return wallet; }

}
