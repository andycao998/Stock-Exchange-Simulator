package assignment2;

import assignment1.Price;

public interface Tradable {
    String getUser();

    String getProduct();

    Price getPrice();

    BookSide getSide();

    String getId();

    int getOriginalVolume();

    int getRemainingVolume();

    void setRemainingVolume(int newVol);

    int getCancelledVolume();

    void setCancelledVolume(int newVol);

    int getFilledVolume();

    void setFilledVolume(int newVol);

    TradableDTO makeTradableDTO();
}
