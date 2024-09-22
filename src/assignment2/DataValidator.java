package assignment2;

import assignment1.InvalidPriceOperation;
import assignment1.Price;

public abstract class DataValidator {
    public static void checkUser(String code) throws InvalidStringException {
        code = code.toLowerCase();

        if (code.length() != 3) {
            throw new InvalidStringException("Invalid code length");
        }

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < 3; i++) {
            String c = code.substring(i, i + 1);

            if (!alphabet.contains(c)) {
                throw new InvalidStringException("Invalid code characters");
            }
        }
    }

    public static void checkProduct(String symbol) throws InvalidStringException {
        symbol = symbol.toLowerCase();

        if (symbol.isEmpty() || symbol.length() > 5) {
            throw new InvalidStringException("Invalid symbol length");
        }

        String stockSymbols = "abcdefghijklmnopqrstuvwxyz0123456789.";
        int length = symbol.length();

        for (int i = 0; i < length; i++) {
            String c = symbol.substring(i, i + 1);

            if (!stockSymbols.contains(c)) {
                throw new InvalidStringException("Invalid symbol characters");
            }
        }
    }

    public static void checkPrice(Price p) throws InvalidPriceOperation {
        if (p == null) {
            throw new InvalidPriceOperation("Invalid price supplied");
        }
    }

    public static void checkSide(BookSide side) throws InvalidBookSide {
        if (side == null) {
            throw new InvalidBookSide("Invalid side");
        }
    }
}
