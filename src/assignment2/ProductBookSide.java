package assignment2;

import assignment1.Price;
import assignment3.DataValidationException;
import assignment3.UserManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ProductBookSide {
    private final BookSide side;

    private final HashMap<Price, ArrayList<Tradable>> bookEntries;

    public ProductBookSide(BookSide side) throws InvalidBookSide {
        DataValidator.checkSide(side);
        this.side = side;

        this.bookEntries = new HashMap<Price, ArrayList<Tradable>>();
    }

    public TradableDTO generateDTO(Tradable o) {
        return new TradableDTO(o.getUser(), o.getProduct(), o.getPrice(), o.getSide(),
                o.getId(), o.getOriginalVolume(), o.getRemainingVolume(),
                o.getCancelledVolume(), o.getFilledVolume());
    }

    public TradableDTO add(Tradable o) {
        Price price = o.getPrice();

        if (!bookEntries.containsKey(price)) {
            bookEntries.put(price, new ArrayList<Tradable>());
        }

        bookEntries.get(price).add(o);

        return generateDTO(o);
    }

    public TradableDTO cancel(String tradableId) throws DataValidationException {
        Tradable found = null;
        Price key = null;
        ArrayList<Tradable> tradablesCopy = null;
        boolean emptyList = false;

        // Iterate through every Tradable in every ArrayList of every Price
        for (Price p : bookEntries.keySet()) {
            ArrayList<Tradable> tradables = bookEntries.get(p);
            int len = tradables.size();

            for (int i = 0; i < len; i++) {
                if (tradables.get(i).getId().equals(tradableId)) {
                    // Modifications made to copy of the ArrayList to avoid modifying while looping
                    tradablesCopy = tradables;

                    Tradable tCopy = tradablesCopy.get(i);
                    tradablesCopy.remove(tCopy);

                    found = tCopy;
                    key = p;

                    tCopy.setCancelledVolume(tCopy.getCancelledVolume() + tCopy.getRemainingVolume());
                    tCopy.setRemainingVolume(0);

                    System.out.println("CANCEL: " + side + ": " + tradableId +
                            " Cxl Qty: " + tCopy.getCancelledVolume());

                    // Save flag to remove Price entry after loopnig if ArrayList becomes empty
                    if (tradablesCopy.isEmpty()) {
                        emptyList = true;
                    }

                    break;
                }
            }

            if (found != null) {
                break;
            }
        }

        // Replace old ArrayList with ArrayList copy with modifications
        if (key != null && tradablesCopy != null) {
            bookEntries.replace(key, tradablesCopy);
        }

        if (emptyList) {
            bookEntries.remove(key);
        }

        if (found != null) {
            TradableDTO dto = generateDTO(found);
            UserManager.getInstance().addToUser(found.getUser(), dto);
            return dto;
        }

        return null;
    }

    public TradableDTO removeQuotesForUser(String userName) throws DataValidationException {
        for (Price p : bookEntries.keySet()) {
            ArrayList<Tradable> tradables = bookEntries.get(p);

            for (Tradable tradable : tradables) {
                if (tradable.getUser().equals(userName)) {
                    // Cancel handles case where ArrayList becomes empty after removing quote
                    return cancel(tradable.getId());
                }
            }
        }

        return null;
    }

    public Price topOfBookPrice() {
        if (sortHashPrices().isEmpty()) {
            return null;
        }

        return sortHashPrices().get(0);
    }

    public int topOfBookVolume() {
        Price p = topOfBookPrice();
        ArrayList<Tradable> tradables = bookEntries.get(p);
        int volume = 0;

        if (tradables == null) {
            return 0;
        }

        for (Tradable tradable : tradables) {
            volume += tradable.getRemainingVolume();
        }

        return volume;
    }

    public void tradeOut(Price price, int vol) throws DataValidationException {
        int remainingVol = vol;
        ArrayList<Tradable> orders = bookEntries.get(price);

        while (remainingVol > 0) {
            if (orders.isEmpty()) {
                break;
            }

            Tradable o = orders.get(0);

            if (o.getRemainingVolume() <= remainingVol) {
                int oVol = o.getRemainingVolume();

                o = orders.remove(0);
                o.setFilledVolume(o.getFilledVolume() + oVol);
                remainingVol -= oVol;
                o.setRemainingVolume(0);

                System.out.println("\tFULL FILL: (" + side + " " + oVol + ") " + o);
            }
            else {
                o.setFilledVolume(o.getFilledVolume() + remainingVol);
                o.setRemainingVolume(o.getRemainingVolume() - remainingVol);
                remainingVol = 0;

                System.out.println("\tPARTIAL FILL: (" + side + " " + o.getFilledVolume() + ") " + o);
            }

            UserManager.getInstance().addToUser(o.getUser(), generateDTO(o));
        }

        if (orders.isEmpty()) {
            bookEntries.remove(price);
        }
    }

    private ArrayList<Price> sortHashPrices() {
        ArrayList<Price> prices = new ArrayList<Price>(bookEntries.keySet());
        Collections.sort(prices);

        if (side.equals(BookSide.BUY)) {
            Collections.reverse(prices);
        }

        return prices;
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();

        if (side.equals(BookSide.BUY)) {
            msg.append("Side: BUY\n");
        }
        else {
            msg.append("Side: SELL\n");
        }

        for (Price p : sortHashPrices()) {
            msg.append("\tPrice: " + p + "\n");
            ArrayList<Tradable> tradables = bookEntries.get(p);

            for (Tradable t : tradables) {
                msg.append("\t\t" + t + "\n");
            }
        }

        return msg.toString();
    }
}
