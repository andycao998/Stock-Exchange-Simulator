package assignment1;

import java.util.HashMap;

public abstract class PriceFactory {
    // Flyweight design pattern
    private static HashMap<Integer, Price> priceHash = new HashMap<Integer, Price>();
    private static int count = 0;

    public static void inPriceHash(int value) {
        if (!priceHash.containsKey(value)) {
            Price p = new Price(value);
            priceHash.put(value, p);
            count += 1;
            //System.out.println("Price created: " + p.getValue() + ", Count: " + count);
        }
    }

    public static Price makePrice(int value) {
        inPriceHash(value);
        return priceHash.get(value);
    }

    public static Price makePrice(String stringValueIn) {
        String stringPrice = stringValueIn.replace("$", "");
        stringPrice = stringPrice.replace(",", "");

        double value = Double.parseDouble(stringPrice);
        // Convert to int and truncate (take ceiling for negatives and floor for positives)
        if (value < 0) {
            value = Math.ceil(value * 100);
        }
        else {
            value = Math.floor(value * 100);
        }

        inPriceHash((int) value);
        return priceHash.get((int) value);
    }
}
