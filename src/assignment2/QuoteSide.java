package assignment2;

import assignment1.InvalidPriceOperation;
import assignment1.Price;

public class QuoteSide implements Tradable {
    private final String user;
    private final String product;

    private final Price price;
    private final BookSide side;
    private final String id;

    private final int originalVolume;
    private int remainingVolume;
    private int cancelledVolume;
    private int filledVolume;

    // Abstract DataValidator class with static methods to check user, symbol, price, and side prior to setting
    public QuoteSide(String user, String product, Price price, int originalVolume, BookSide side)
            throws InvalidStringException, InvalidPriceOperation, InvalidBookSide {
        DataValidator.checkUser(user);
        this.user = user;

        DataValidator.checkProduct(product);
        this.product = product;

        DataValidator.checkPrice(price);
        this.price = price;

        DataValidator.checkSide(side);
        this.side = side;

        id = this.user + this.product + this.price + System.nanoTime();

        this.originalVolume = originalVolume;
        remainingVolume = this.originalVolume;
        cancelledVolume = 0;
        filledVolume = 0;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getProduct() {
        return product;
    }

    @Override
    public Price getPrice() {
        return price;
    }

    @Override
    public BookSide getSide() {
        // Pass by reference of BookSide: create copy to return instead
        BookSide copy;
        if (side.equals(BookSide.BUY)) {
            copy = BookSide.BUY;
        }
        else {
            copy = BookSide.SELL;
        }

        return copy;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getOriginalVolume() {
        return originalVolume;
    }

    @Override
    public int getRemainingVolume() {
        return remainingVolume;
    }

    @Override
    public void setRemainingVolume(int newVol) {
        remainingVolume = newVol;
    }

    @Override
    public int getCancelledVolume() {
        return cancelledVolume;
    }

    @Override
    public void setCancelledVolume(int newVol) {
        cancelledVolume = newVol;
    }

    @Override
    public int getFilledVolume() {
        return filledVolume;
    }

    @Override
    public void setFilledVolume(int newVol) {
        filledVolume = newVol;
    }

    @Override
    public TradableDTO makeTradableDTO() {
        // Pass by reference of BookSide and Price (immutable)
        return new TradableDTO(user, product, price, getSide(), id,
                originalVolume, remainingVolume, cancelledVolume, filledVolume);
    }

    @Override
    public String toString() {
        return product + " quote from " + user + ": " + price + ", Orig Vol: " + originalVolume
                + ", Rem Vol: " + remainingVolume + ", Fill Vol: " + filledVolume + ", CXL Vol: "
                + cancelledVolume + ", ID: " + id;
    }
}
