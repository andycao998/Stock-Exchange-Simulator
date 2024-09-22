package assignment3;

import assignment1.InvalidPriceOperation;
import assignment2.*;

import java.util.ArrayList;
import java.util.HashMap;

// Singleton
public final class ProductManager {
    private static ProductManager instance;

    private HashMap<String, ProductBook> productBooks;

    private ProductManager() {
        productBooks = new HashMap<String, ProductBook>();
    }

    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }

        return instance;
    }

    public void addProduct(String symbol) throws InvalidStringException, InvalidBookSide {
        DataValidator.checkProduct(symbol);

        ProductBook pb = new ProductBook(symbol);
        productBooks.put(symbol, pb);
    }

    public ProductBook getProductBook(String symbol) throws DataValidationException {
        if (productBooks.get(symbol) == null) {
            throw new DataValidationException("Provided symbol-product does not exist in product books");
        }

        return productBooks.get(symbol);
    }

    // Places each product in hashmap into array, which can be accessed with a random index
    public String getRandomProduct() throws DataValidationException {
        if (productBooks.isEmpty()) {
            throw new DataValidationException("No products exist");
        }

        String[] symbolList = new String[productBooks.size()];
        int index = 0;
        for (String symbol : productBooks.keySet()) {
            symbolList[index] = symbol;
            index += 1;
        }

        int rand = (int) (Math.random() * (symbolList.length));
        return symbolList[rand];
    }

    public TradableDTO addTradable(Tradable o)
            throws DataValidationException, InvalidStringException, InvalidBookSide, InvalidPriceOperation {
        if (o == null) {
            throw new DataValidationException("Provided Tradable is null");
        }

        String symbol = o.getProduct();
        ProductBook pb = productBooks.get(symbol);

        // Add to ProductManager hashmap if product was not found (guarantees a ProductBook exists)
        if (pb == null) {
            addProduct(symbol);
        }
        // Add Tradable to matching ProductBook
        pb = productBooks.get(symbol);
        pb.add(o);

        TradableDTO dto = o.makeTradableDTO();
        UserManager.getInstance().addToUser(o.getUser(), dto);

        return dto;
    }

    public TradableDTO[] addQuote(Quote q)
            throws DataValidationException, InvalidStringException, InvalidBookSide, InvalidPriceOperation {
        if (q == null) {
            throw new DataValidationException("Provided quote is null");
        }

        String symbol = q.getSymbol();
        ProductBook pb = productBooks.get(symbol);

        // Call removeQuotesForUser only if a ProductBook of the quote's symbol exists
        if (pb != null) {
            pb.removeQuotesForUser(q.getUser());
        }

        QuoteSide buySide = q.getQuoteSide(BookSide.BUY);
        QuoteSide sellSide = q.getQuoteSide(BookSide.SELL);
        addTradable(buySide);
        addTradable(sellSide);

        TradableDTO[] dtos = {buySide.makeTradableDTO(), sellSide.makeTradableDTO()};
        return dtos;
    }

    public TradableDTO cancel(TradableDTO o) throws DataValidationException, InvalidPriceOperation {
        if (o == null) {
            throw new DataValidationException("Provided TradableDTO for cancellation is null");
        }

        ProductBook pb = productBooks.get(o.product);

        if (pb == null) {
            System.out.println("Cancel failed");
            return null;
        }

        return pb.cancel(o.side, o.id);
    }

    public TradableDTO[] cancelQuote(String symbol, String user)
            throws DataValidationException, InvalidPriceOperation {
        if (symbol == null) {
            throw new DataValidationException("Provided symbol is null");
        }
        if (user == null) {
            throw new DataValidationException("Provided user is null");
        }
        if (productBooks.get(symbol) == null) {
            throw new DataValidationException("Provided symbol does not exist in hashmap");
        }

        ProductBook pb = productBooks.get(symbol);
        return pb.removeQuotesForUser(user);
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();

        for (String id : productBooks.keySet()) {
            msg.append(productBooks.get(id) + "\n");
        }

        return msg.toString();
    }

    public ArrayList<String> getProductList() {
        return new ArrayList<>(productBooks.keySet());
    }

}
