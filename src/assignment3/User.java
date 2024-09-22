package assignment3;

import assignment2.*;
import assignment4.CurrentMarketObserver;
import assignment4.CurrentMarketSide;

import java.util.HashMap;

public class User implements CurrentMarketObserver {
    private final String userId;

    private HashMap<String, TradableDTO> tradables;

    private HashMap<String, CurrentMarketSide[]> currentMarkets;

    public User(String userId) throws InvalidStringException {
        //DataValidator.checkUser(userId);

        // userId is final: only set in constructor
        checkUser(userId);
        this.userId = userId;

        tradables = new HashMap<String, TradableDTO>();
        currentMarkets = new HashMap<String, CurrentMarketSide[]>();
    }

    // From DataValidator class to meet assignment requirement (private modifier)
    private void checkUser(String code) throws InvalidStringException {
        code = code.toLowerCase();

        if (code.length() != 3) {
            throw new InvalidStringException("Invalid userId length");
        }

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < 3; i++) {
            String c = code.substring(i, i + 1);

            if (!alphabet.contains(c)) {
                throw new InvalidStringException("Invalid userId characters");
            }
        }
    }

    public String getUserId() {
        return userId;
    }

    public void addTradable(TradableDTO o) {
        if (o == null) {
            return;
        }

        tradables.put(o.id, o);
    }

    // Auxiliary method to find any tradable with remainingQuantity > 0
    private TradableDTO findTradableWithRemainingQty() {
        for (String id : tradables.keySet()) {
            TradableDTO o = tradables.get(id);

            if (o.remainingVolume > 0) {
                return o;
            }
        }

        return null;
    }

    public boolean hasTradableWithRemainingQty() {
        if (findTradableWithRemainingQty() == null) {
            return false;
        }

        return true;
    }

    public TradableDTO getTradableWithRemainingQty() {
        if (!hasTradableWithRemainingQty()) {
            return null;
        }

        return findTradableWithRemainingQty();
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder("User Id: " + userId + "\n");

        for (String id : tradables.keySet()) {
            TradableDTO o = tradables.get(id);

            msg.append("\tProduct: " + o.product + ", Price: " + o.price + ", OriginalVolume: " + o.originalVolume
            + ", CancelledVolume: " + o.cancelledVolume + ", FilledVolume: " + o.filledVolume + ", User: "
            + userId + ", Side: " + o.side + ", Id: " + o.id + "\n");
        }

        return msg.toString();
    }

    @Override
    public void updateCurrentMarket(String symbol, CurrentMarketSide buySide, CurrentMarketSide sellSide) {
        CurrentMarketSide[] cms = {buySide, sellSide};
        currentMarkets.put(symbol, cms);
    }

    public String getCurrentMarkets() {
        StringBuilder msg = new StringBuilder();
        for (String symbol : currentMarkets.keySet()) {
            CurrentMarketSide[] cms = currentMarkets.get(symbol);
            msg.append(symbol + "\t" + cms[0].toString() + " - " + cms[1].toString() + "\n");
        }

        return msg.toString();
    }
}
