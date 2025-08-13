package parcial.model;

public class User {

    private String name;

    private final Wallet<Cryptocoin> wallet;

    public User(String name) {

        this.name = name;
        this.wallet = new Wallet<>();

    }

    public String getName() { return name; }

    public Wallet<Cryptocoin> getWallet() { return wallet; }

}
