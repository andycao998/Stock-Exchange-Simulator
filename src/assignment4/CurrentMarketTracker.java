package assignment4;

import assignment1.InvalidPriceOperation;
import assignment1.Price;
import assignment1.PriceFactory;
import assignment3.DataValidationException;

// Singleton
public final class CurrentMarketTracker {
    private static CurrentMarketTracker instance;

    private CurrentMarketTracker() {

    }

    public static CurrentMarketTracker getInstance() {
        if (instance == null) {
            instance = new CurrentMarketTracker();
        }

        return instance;
    }

    public void updateMarket(String symbol, Price buyPrice, int buyVolume, Price sellPrice, int sellVolume)
            throws InvalidPriceOperation {
        Price marketWidth;

        if (buyPrice == null || sellPrice == null) {
            marketWidth = PriceFactory.makePrice(0);

            if (buyPrice == null) {
                buyPrice = PriceFactory.makePrice(0);
                buyVolume = 0;
            }
            if (sellPrice == null) {
                sellPrice = PriceFactory.makePrice(0);
                sellVolume = 0;
            }
        }
        else {
            marketWidth = sellPrice.subtract(buyPrice);
        }

        CurrentMarketSide buySide = new CurrentMarketSide(buyPrice, buyVolume);
        CurrentMarketSide sellSide = new CurrentMarketSide(sellPrice, sellVolume);

        System.out.println("*********** Current Market ***********");
        System.out.println("* " + symbol + "\t" + buySide.toString() + " - " + sellSide.toString() +
                " [" + marketWidth.toString() + "]");
        System.out.println("**************************************");

        CurrentMarketPublisher.getInstance().acceptCurrentMarket(symbol, buySide, sellSide);
    }
}
