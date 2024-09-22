package assignment4;

import assignment1.InvalidPriceOperation;
import assignment1.Price;
import assignment2.DataValidator;

public class CurrentMarketSide {
    private final Price price;
    private final int volume;

    public CurrentMarketSide(Price price, int volume) throws InvalidPriceOperation {
        DataValidator.checkPrice(price);
        this.price = price;

        this.volume = volume;
    }

    public Price getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return price.toString() + "x" + volume;
    }
}
