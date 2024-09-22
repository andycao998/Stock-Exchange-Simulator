package assignment2;

import assignment1.InvalidPriceOperation;
import assignment1.Price;
import assignment1.PriceFactory;
import assignment3.DataValidationException;
import assignment3.UserManager;
import assignment4.CurrentMarketTracker;

public class ProductBook {
    private final String product;

    private final ProductBookSide buySide;
    private final ProductBookSide sellSide;

    // Abstract DataValidator class with static method to check symbol prior to setting
    public ProductBook(String product) throws InvalidStringException, InvalidBookSide {
        DataValidator.checkProduct(product);
        this.product = product;

        buySide = new ProductBookSide(BookSide.BUY);
        sellSide = new ProductBookSide(BookSide.SELL);
    }

    public TradableDTO add(Tradable t) throws DataValidationException, InvalidPriceOperation {
        System.out.println("ADD: " + t.getSide() + ": " + t);

        TradableDTO dto = null;

        if (t.getSide().equals(BookSide.BUY)) {
            dto = buySide.add(t);
        }
        else {
            dto = sellSide.add(t);
        }

        tryTrade();
        updateMarket();
        return dto;
    }

    public TradableDTO[] add(Quote qte) throws DataValidationException {
        System.out.println("ADD: BUY: " + qte.getQuoteSide(BookSide.BUY));
        System.out.println("ADD: SELL: " + qte.getQuoteSide(BookSide.SELL));

        TradableDTO buyDto = buySide.add(qte.getQuoteSide(BookSide.BUY));
        TradableDTO sellDto = sellSide.add(qte.getQuoteSide(BookSide.SELL));

        tryTrade();
        TradableDTO[] dtos = {buyDto, sellDto};
        return dtos;
    }

    public TradableDTO cancel(BookSide side, String orderId)
            throws DataValidationException, InvalidPriceOperation {
        TradableDTO dto = null;

        if (side.equals(BookSide.BUY)) {
            dto = buySide.cancel(orderId);
        }
        else {
            dto = sellSide.cancel(orderId);
        }

        updateMarket();
        return dto;
    }

    public void tryTrade() throws DataValidationException {
        Price topBuy = buySide.topOfBookPrice();
        Price topSell = sellSide.topOfBookPrice();

        while (topBuy != null && topSell != null && topBuy.compareTo(topSell) >= 0) {
            int volToTrade = Math.min(buySide.topOfBookVolume(), sellSide.topOfBookVolume());
            sellSide.tradeOut(topSell, volToTrade);
            buySide.tradeOut(topBuy, volToTrade);

            topBuy = buySide.topOfBookPrice();
            topSell = sellSide.topOfBookPrice();
        }
    }

    public TradableDTO[] removeQuotesForUser(String userName)
            throws DataValidationException, InvalidPriceOperation {
        TradableDTO buyDto = buySide.removeQuotesForUser(userName);
        TradableDTO sellDto = sellSide.removeQuotesForUser(userName);

        UserManager.getInstance().getUser(userName).addTradable(buyDto);
        UserManager.getInstance().getUser(userName).addTradable(sellDto);

        TradableDTO[] dtos = {buyDto, sellDto};
        updateMarket();
        return dtos;
    }

    private void updateMarket() throws InvalidPriceOperation, DataValidationException {
        Price topBuyPrice = buySide.topOfBookPrice();
        int topBuyVol = buySide.topOfBookVolume();
        Price topSellPrice = sellSide.topOfBookPrice();
        int topSellVol = sellSide.topOfBookVolume();

        CurrentMarketTracker.getInstance().updateMarket(product, topBuyPrice, topBuyVol,
                topSellPrice, topSellVol);
    }

    @Override
    public String toString() {
        String msg = "--------------------------------------------\n" +
                "Product Book: " + product + "\n" + buySide + sellSide +
                "--------------------------------------------";
        return msg;
    }
}
