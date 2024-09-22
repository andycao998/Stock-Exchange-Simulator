package assignment4;

import assignment1.InvalidPriceOperation;

import java.util.ArrayList;
import java.util.HashMap;

// Singleton
public final class CurrentMarketPublisher {
    private static CurrentMarketPublisher instance;

    private HashMap<String, ArrayList<CurrentMarketObserver>> filters;

    private CurrentMarketPublisher() {
        filters = new HashMap<String, ArrayList<CurrentMarketObserver>>();
    }

    public static CurrentMarketPublisher getInstance() {
        if (instance == null) {
            instance = new CurrentMarketPublisher();
        }

        return instance;
    }

    private boolean keyInFilters(String symbol) {
        if (filters.get(symbol) == null) {
            return false;
        }

        return true;
    }

    public void subscribeCurrentMarket(String symbol, CurrentMarketObserver cmo) {
        if (!keyInFilters(symbol)) {
            filters.put(symbol, new ArrayList<CurrentMarketObserver>());
        }

        filters.get(symbol).add(cmo);
    }

    public void unSubscribeCurrentMarket(String symbol, CurrentMarketObserver cmo) {
        if (!keyInFilters(symbol)) {
            return;
        }

        filters.get(symbol).remove(cmo);
    }

    public void acceptCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide)
            throws InvalidPriceOperation {
        if (!keyInFilters(symbol)) {
            return;
        }

        ArrayList<CurrentMarketObserver> cmos = filters.get(symbol);

        for (CurrentMarketObserver cmo : cmos) {
            cmo.updateCurrentMarket(symbol, buySide, sellSide);
        }
    }
}
