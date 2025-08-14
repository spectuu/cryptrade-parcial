package parcial.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

public class Cryptocoin implements Serializable {

    private static final Logger logger = LogManager.getLogger(Cryptocoin.class);

    private String id;
    private String symbol;
    private String name;
    private String price_usd;

    public Cryptocoin(String id, String symbol, String name, String price_usd) {

        if( id == null || id.isEmpty() || symbol == null || symbol.isEmpty() || name == null || name.isEmpty() || price_usd == null || price_usd.isEmpty() ) {
            logger.error("Invalid cryptocoin data: id={}, symbol={}, name={}, price_usd={}", id, symbol, name, price_usd);
            throw new IllegalArgumentException("All fields must be provided and non-empty");
        }

        try {

            this.id = id;
            this.symbol = symbol;
            this.name = name;
            this.price_usd = price_usd;

        } catch (Exception e){
            logger.error("Error creating Cryptocoin: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid data provided: " + e.getMessage());
        }


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

