package assignment2;

import assignment1.InvalidPriceOperation;
import assignment1.Price;

public class Quote {
    private final String user;
    private final String product;

    private final QuoteSide buySide;
    private final QuoteSide sellSide;

    // Abstract DataValidator class with static methods to check user and symbol prior to setting
    public Quote(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume, String userName)
            throws InvalidStringException, InvalidPriceOperation, InvalidBookSide {
        DataValidator.checkUser(userName);
        this.user = userName;

        DataValidator.checkProduct(symbol);
        this.product = symbol;

        buySide = new QuoteSide(userName, symbol, buyPrice, buyVolume, BookSide.BUY);
        sellSide = new QuoteSide(userName, symbol, sellPrice, sellVolume, BookSide.SELL);
    }

    public QuoteSide getQuoteSide(BookSide sideIn) {
        if (sideIn.equals(BookSide.BUY)) {
            return buySide;
        }
        else {
            return sellSide;
        }
    }

    public String getUser() {
        return user;
    }

    public String getSymbol() {
        return product;
    }
}
