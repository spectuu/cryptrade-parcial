package parcial.model;

public class WalletItemReport {

    private String name;
    private int quantity;

    public WalletItemReport(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public static WalletItemReport[] buildPortfolioWithQuantities(Cryptocoin[] items) {

        String[] names = new String[items.length];
        int[] quantity = new int[items.length];
        int count = 0;

        for (Cryptocoin item : items) {

            String name = item.getName();
            boolean found = false;

            for (int j = 0; j < count; j++) {
                if (names[j].equals(name)) {
                    quantity[j]++;
                    found = true;
                    break;
                }
            }

            if (!found) {
                names[count] = name;
                quantity[count] = 1;
                count++;
            }
        }

        WalletItemReport[] result = new WalletItemReport[count];
        for (int i = 0; i < count; i++) {
            result[i] = new WalletItemReport(names[i], quantity[i]);
        }

        return result;
    }

}




