package parcial.model;

import java.io.Serializable;

public class Cryptocoin implements Serializable {

    private String id;
    private String symbol;
    private String name;
    private String price_usd;

    public Cryptocoin(String id, String symbol, String name, String price_usd) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.price_usd = price_usd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    @Override
    public String toString() {
        return "Crypto coin{" +
                "id='" + id + '\'' +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", price_usd='" + price_usd + '\'' +
                '}';
    }

}

