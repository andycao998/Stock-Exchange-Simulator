package main;

import assignment1.InvalidPriceOperation;
import assignment1.Price;
import assignment1.PriceFactory;
import assignment2.*;
import assignment3.ProductManager;
import assignment3.User;
import assignment3.UserManager;
import assignment4.CurrentMarketPublisher;
import sim.TradingSim;

import static assignment2.BookSide.*;

import java.util.ArrayList;

public class Main {
    /*
    public static void main(String[] args) {
        ArrayList<Price> prices = new ArrayList<Price>();

        // Testing price creation, valid string to int cents conversion, price hash (15 initially)
        Price p1 = PriceFactory.makePrice("98765");
        prices.add(p1);
        Price p2 = PriceFactory.makePrice("000");
        prices.add(p2);
        Price p3 = PriceFactory.makePrice(".22");
        prices.add(p3);
        Price p4 = PriceFactory.makePrice("14.7555");
        prices.add(p4);
        Price p5 = PriceFactory.makePrice("25.79");
        prices.add(p5);
        Price p6 = PriceFactory.makePrice("001.76");
        prices.add(p6);
        Price p7 = PriceFactory.makePrice("4,567.89");
        prices.add(p7);
        Price p8 = PriceFactory.makePrice("$-12.85");
        prices.add(p8);
        Price p9 = PriceFactory.makePrice("$-12");
        prices.add(p9);
        Price p10 = PriceFactory.makePrice("$-.89");
        prices.add(p10);
        Price p11 = PriceFactory.makePrice("$-25.4");
        prices.add(p11);
        Price p12 = PriceFactory.makePrice("$-25.4");
        prices.add(p12);
        Price p13 = PriceFactory.makePrice("$-25.4");
        prices.add(p13);
        Price p14 = PriceFactory.makePrice(-2540);
        prices.add(p14);
        Price p15 = PriceFactory.makePrice(10577);
        prices.add(p15);
        Price p16 = PriceFactory.makePrice("-1234.891234");
        prices.add(p16);
        Price p17 = PriceFactory.makePrice(205214);
        prices.add(p17);
        Price p18 = PriceFactory.makePrice(1);
        prices.add(p18);
        Price p19 = PriceFactory.makePrice(176);
        prices.add(p19);
        Price p20 = PriceFactory.makePrice(10577);
        prices.add(p20);
        //
        //Package Private Price creation
        //Price p16 = Price(10000);
        //

        System.out.println("\n****Displaying Prices****");
        for(Price price : prices) {
            System.out.println(price.toString());
        }
        System.out.println();

        System.out.println("****Negative Tests****");
        System.out.println(p1.isNegative()); // false
        System.out.println(p8.isNegative() + "\n"); // true

        try {
            System.out.println("****Add Tests****");
            System.out.println(p1.add(p3)); // $98,765.22
            System.out.println(p5.add(p14) + "\n"); // $0.39

            System.out.println("****Subtract Tests****");
            System.out.println(p12.subtract(p14)); // $0.00
            System.out.println(p4.subtract(p3)); // $14.53
            System.out.println(p9.subtract(p6) + "\n"); // $-13.76

            System.out.println("****Multiply Tests****");
            System.out.println(p3.multiply(3)); // $0.66
            System.out.println(p9.multiply(4) + "\n"); // $-48.00

            System.out.println("****GreaterEqual Tests****");
            System.out.println(p9.greaterOrEqual(p8)); // true
            System.out.println(p6.greaterOrEqual(p5)); // false
            System.out.println(p11.greaterOrEqual(p12) + "\n"); // true

            System.out.println("****LessEqual Tests****");
            System.out.println(p9.lessOrEqual(p8)); // false
            System.out.println(p6.lessOrEqual(p5)); // true
            System.out.println(p11.lessOrEqual(p12) + "\n"); // true

            System.out.println("****Greater Tests****");
            System.out.println(p9.greaterThan(p8)); // true
            System.out.println(p6.greaterThan(p5)); // false
            System.out.println(p11.greaterThan(p12) + "\n"); // false

            System.out.println("****Less Tests****");
            System.out.println(p9.lessThan(p8)); // false
            System.out.println(p6.lessThan(p5)); // true
            System.out.println(p11.lessThan(p12) + "\n"); // false

            System.out.println("****CompareTo Tests****");
            System.out.println(p9.compareTo(p8)); // positive
            System.out.println(p11.compareTo(p12)); // zero
            System.out.println(p4.compareTo(p1) + "\n"); // negative
        } catch (InvalidPriceOperation e) {
            System.out.println(e.getMessage());
        }

        System.out.println("****Equals Tests****");
        System.out.println(p9.equals(p8)); // false
        System.out.println(p11.equals(p12) + "\n"); // true
    }
    */

    /*
    public static void main(String[] args) throws InvalidStringException, InvalidBookSide {
        // This main content should execute w/o issue - no exceptions should occur.
        ProductBook pb = new ProductBook("TGT");
        try {
            System.out.print("1) Enter BUY order for TGT from EST");
            System.out.println(" - book shows 1 order on the BUY side");
            Order o1 = new Order("EST", "TGT", PriceFactory.makePrice(17720), 50, BUY);
            pb.add(o1);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.println("2) Cancel BUY order for TGT from EST - book should be empty");
            pb.cancel(BUY, o1.getId());
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("3) Enter BUY order for TGT from ANA");
            System.out.println(" - book shows 1 order on the BUY side");
            Order o2 = new Order("ANA", "TGT", PriceFactory.makePrice(17720), 50, BUY);
            pb.add(o2);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("4) Enter Quote for TGT from BOB - book shows 1 order on ");
            System.out.println("the BUY side and 1 quote on the BUY & SELL side");
            Quote qte = new Quote("TGT",
                    PriceFactory.makePrice(17720), 75,
                    PriceFactory.makePrice(17730), 75,
                    "BOB");
            pb.add(qte);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("5) Enter SELL order for TGT from COD");
            System.out.println(" - book shows new SELL order on the SELL side");
            Order o3 = new Order("COD", "TGT", PriceFactory.makePrice(17730), 85, SELL);
            pb.add(o3);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("6) Enter BUY order for TGT from DIG - fully trades against SELL-side");
            System.out.println("quote (partially) from BOB - partial sell-side quote from BOB remains");
            Order o4 = new Order("DIG", "TGT", PriceFactory.makePrice(17730), 60, BUY);
            pb.add(o4);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.println("7) Cancel all open TGT orders & quotes - leaves book empty");
            pb.cancel(BUY, o2.getId());
            pb.cancel(SELL, o3.getId());
            pb.removeQuotesForUser("BOB");
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("8) Enter BUY order for TGT from ANA - ");
            System.out.println("book shows 1 order from ANA on the BUY side");
            Order o5 = new Order("ANA", "TGT", PriceFactory.makePrice(17710), 50, BUY);
            pb.add(o5);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("9) Enter BUY order for TGT from BOB at a lesser price - ");
            System.out.println("book contains 2 BUY orders at 2 prices");
            Order o6 = new Order("BOB", "TGT", PriceFactory.makePrice(17720), 100, BUY);
            pb.add(o6);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("10) Enter BUY order for TGT from COD at an even lesser price");
            System.out.println(" - book contains 3 BUY orders at 3 prices");
            Order o7 = new Order("COD", "TGT", PriceFactory.makePrice(17730), 150, BUY);
            pb.add(o7);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("11) Enter Quote for TGT from DIG at a better BUY side - ");
            System.out.println("book shows quote sides at the top of both BUY & SELL sides");
            qte = new Quote("TGT",
                    PriceFactory.makePrice(17740), 200,
                    PriceFactory.makePrice(17750), 200,
                    "DIG");
            pb.add(qte);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.print("12) Enter large SELL order for TGT from EST to trade out BUY side - ");
            System.out.println("trades with everything on the BUY side, leaving 1 SELL-side quote");
            Order o8 = new Order("EST", "TGT", PriceFactory.makePrice(17710), 500, SELL);
            pb.add(o8);
            System.out.println(pb);
            System.out.print("========================================================");
            System.out.println("========================================================\n");
            System.out.println("13) Cancel TGT quote from DIG - leaves the book empty");
            pb.removeQuotesForUser("DIG");
            System.out.println(pb);


            //QuoteSide qs1 = new QuoteSide("ABC", "DEF", null, 100, BUY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

    /*
    public static void main(String[] args) {

        // This main content should execute w/o issue - no exceptions should occur.
        try {
            System.out.println("\nSetup: Initialize ProductManager and UserManager");
            ProductManager.getInstance().addProduct("WMT");
            ProductManager.getInstance().addProduct("TGT");

            UserManager.getInstance().init(
                    new String[]{"ANA", "BOB", "COD", "DIG", "EST"});

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\nStep A) Build up book sides with Quotes (no trades)");
            ProductManager.getInstance().addQuote(
                    new Quote("TGT",
                            PriceFactory.makePrice(15990), 75,
                            PriceFactory.makePrice(16000), 75,
                            "ANA"));
            ProductManager.getInstance().addQuote(
                    new Quote("TGT",
                            PriceFactory.makePrice(15990), 100,
                            PriceFactory.makePrice(16000), 100,
                            "BOB"));
            System.out.println(ProductManager.getInstance().getProductBook("TGT").toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep B) Enter an Order that trades with the SELL side quotes");
            ProductManager.getInstance().addTradable(
                    new Order("COD", "TGT", PriceFactory.makePrice(16000), 100, BUY));
            System.out.println(ProductManager.getInstance().getProductBook("TGT").toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep C) Change user ANA's Quote");
            ProductManager.getInstance().addQuote(
                    new Quote("TGT",
                            PriceFactory.makePrice(15985), 111,
                            PriceFactory.makePrice(16000), 111,
                            "ANA"));
            System.out.println(ProductManager.getInstance().getProductBook("TGT").toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep D) Display random Tradable for user ANA");

            User u1 = UserManager.getInstance().getUser("ANA");
            boolean value = u1.hasTradableWithRemainingQty();
            System.out.println("User ANA has Tradable with remaining quantity: " + value);
            TradableDTO u1DTO = u1.getTradableWithRemainingQty();
            System.out.println("User ANA's random Tradable with remaining quantity:\n" + u1DTO.toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep E) Enter an Order that trades out the BUY side quotes");
            ProductManager.getInstance().addTradable(
                    new Order("DIG", "TGT", PriceFactory.makePrice(15985), 211, SELL));
            System.out.println(ProductManager.getInstance().getProductBook("TGT").toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep F) Cancel quotes from ANA and BOB");
            ProductManager.getInstance().cancelQuote("TGT", "ANA");
            ProductManager.getInstance().cancelQuote("TGT", "BOB");
            System.out.println(ProductManager.getInstance().getProductBook("TGT").toString());

            User u2 = UserManager.getInstance().getUser("ANA");
            System.out.println("User ANA has Tradable with remaining quantity: " + u2.hasTradableWithRemainingQty());
            User u3 = UserManager.getInstance().getUser("BOB");
            System.out.println("User BOB has Tradable with remaining quantity: " + u3.hasTradableWithRemainingQty());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep G) Print users and their OrderDTO's (user order doesn't matter)");
            System.out.println(UserManager.getInstance().toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep H) Print product books");
            System.out.println(ProductManager.getInstance().toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep I) Enter 3 BUY side orders for WMT");
            ProductManager.getInstance().addTradable(
                    new Order("COD", "WMT", PriceFactory.makePrice(6010), 50, BUY));
            ProductManager.getInstance().addTradable(
                    new Order("DIG", "WMT", PriceFactory.makePrice(6010), 100, BUY));
            ProductManager.getInstance().addTradable(
                    new Order("EST", "WMT", PriceFactory.makePrice(6010), 75, BUY));
            System.out.println(ProductManager.getInstance().getProductBook("WMT").toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep J) Enter Quote from user BOB for WMT that trades out BUY orders");
            ProductManager.getInstance().addQuote(
                    new Quote("WMT",
                            PriceFactory.makePrice(6000), 225,
                            PriceFactory.makePrice(6010), 225,
                            "BOB"));
            ProductManager.getInstance().cancelQuote("WMT", "BOB");
            System.out.println(ProductManager.getInstance().getProductBook("WMT").toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep K) Display all Product Books");
            System.out.println(ProductManager.getInstance().toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep L) Display all Users and their TradableDTO's");
            System.out.println(UserManager.getInstance().toString());

            //comment block start
            if (UserManager.getInstance().getUser("sads") == null) {
                System.out.println("Null user");
            }
            //comment block end

            //comment block start
            System.out.println("=======================================");
            User u99 = UserManager.getInstance().getRandomUser();
            User u100 = UserManager.getInstance().getRandomUser();
            System.out.println(u99);
            System.out.println(u100);
            System.out.println(ProductManager.getInstance().getRandomProduct());
            System.out.println(ProductManager.getInstance().getRandomProduct());
            System.out.println(ProductManager.getInstance().getRandomProduct());
            //comment block end

            //comment block start
            Tradable o = new Order("EST", "WMT", PriceFactory.makePrice(17710), 500, SELL);
            ProductManager.getInstance().addTradable(o);
            System.out.println(ProductManager.getInstance().getProductBook("WMT").toString());
            ProductManager.getInstance().cancel(o.makeTradableDTO());
            System.out.println(ProductManager.getInstance().getProductBook("WMT").toString());
            //comment block end
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    */


    public static void main(String[] args) {

        // This main content should execute w/o issue - no exceptions should occur.
        try {
            System.out.println("\nSetup: Initialize Products");
            ProductManager.getInstance().addProduct("WMT");
            ProductManager.getInstance().addProduct("TGT");

            System.out.println("\nSetup: Initialize Users");
            UserManager.getInstance().init(
                    new String[]{"ANA", "BOB", "COD", "DIG", "EST"});

            User u1 = UserManager.getInstance().getUser("ANA");
            User u2 = UserManager.getInstance().getUser("BOB");
            User u3 = UserManager.getInstance().getUser("COD");
            User u4 = UserManager.getInstance().getUser("DIG");
            User u5 = UserManager.getInstance().getUser("EST");

            CurrentMarketPublisher.getInstance().subscribeCurrentMarket("WMT", u1);
            CurrentMarketPublisher.getInstance().subscribeCurrentMarket("TGT", u1);

            CurrentMarketPublisher.getInstance().subscribeCurrentMarket("WMT", u2);
            CurrentMarketPublisher.getInstance().subscribeCurrentMarket("TGT", u2);

            CurrentMarketPublisher.getInstance().subscribeCurrentMarket("WMT", u3);
            CurrentMarketPublisher.getInstance().subscribeCurrentMarket("TGT", u3);

            CurrentMarketPublisher.getInstance().subscribeCurrentMarket("TGT", u4);

            CurrentMarketPublisher.getInstance().subscribeCurrentMarket("WMT", u5);


            CurrentMarketPublisher.getInstance().unSubscribeCurrentMarket("TGT", u2);

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\nStep A) Build up book sides with Quotes (no trades)");
            ProductManager.getInstance().addQuote(
                    new Quote("TGT",
                            PriceFactory.makePrice(15990), 75,
                            PriceFactory.makePrice(16000), 75,
                            "ANA"));
            ProductManager.getInstance().addQuote(
                    new Quote("TGT",
                            PriceFactory.makePrice(15990), 100,
                            PriceFactory.makePrice(16000), 100,
                            "BOB"));
            System.out.println(ProductManager.getInstance().getProductBook("TGT").toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep B) Enter an Order that trades with the SELL side quotes");
            ProductManager.getInstance().addTradable(
                    new Order("COD", "TGT", PriceFactory.makePrice(16000), 100, BUY));
            System.out.println(ProductManager.getInstance().getProductBook("TGT").toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep C) Change user ANA's Quote");
            ProductManager.getInstance().addQuote(
                    new Quote("TGT",
                            PriceFactory.makePrice(15985), 111,
                            PriceFactory.makePrice(16000), 111,
                            "ANA"));
            System.out.println(ProductManager.getInstance().getProductBook("TGT").toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep D) Display random Tradable for user ANA");

            boolean value = u1.hasTradableWithRemainingQty();
            System.out.println("User ANA has Tradable with remaining quantity: " + value);
            TradableDTO u1DTO = u1.getTradableWithRemainingQty();
            System.out.println("User ANA's random Tradable with remaining quantity:\n" + u1DTO.toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep E) Enter an Order that trades out the BUY side quotes");
            ProductManager.getInstance().addTradable(
                    new Order("DIG", "TGT", PriceFactory.makePrice(15985), 211, SELL));
            System.out.println(ProductManager.getInstance().getProductBook("TGT").toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep F) Add a BUY TGT order from user DIG that does not trade");
            TradableDTO t1 = ProductManager.getInstance().addTradable(
                    new Order("DIG", "TGT", PriceFactory.makePrice(15985), 211, BUY));
            System.out.println(ProductManager.getInstance().getProductBook("TGT").toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep G) Print users and their OrderDTO's (user order doesn't matter)");
            System.out.println(UserManager.getInstance().toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep H) Print product books");
            System.out.println(ProductManager.getInstance().toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep I) Enter 3 BUY side orders and 1 quote for WMT");
            TradableDTO t2 = ProductManager.getInstance().addTradable(
                    new Order("COD", "WMT", PriceFactory.makePrice(6010), 50, BUY));
            TradableDTO t3 = ProductManager.getInstance().addTradable(
                    new Order("DIG", "WMT", PriceFactory.makePrice(6010), 100, BUY));
            TradableDTO t4 = ProductManager.getInstance().addTradable(
                    new Order("EST", "WMT", PriceFactory.makePrice(6010), 75, BUY));
            ProductManager.getInstance().addQuote(
                    new Quote("WMT",
                            PriceFactory.makePrice(6010), 111,
                            PriceFactory.makePrice(6012), 111,
                            "ANA"));
            System.out.println(ProductManager.getInstance().getProductBook("WMT").toString());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep J) Display current market values received by all user");
            System.out.println(u1.getUserId() + ":\n" + u1.getCurrentMarkets());
            System.out.println(u2.getUserId() + ":\n" + u2.getCurrentMarkets());
            System.out.println(u3.getUserId() + ":\n" + u3.getCurrentMarkets());
            System.out.println(u4.getUserId() + ":\n" + u4.getCurrentMarkets());
            System.out.println(u5.getUserId() + ":\n" + u5.getCurrentMarkets());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep K) Cancel ANA's WMT quote");
            ProductManager.getInstance().cancelQuote("WMT", "ANA");

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep L) Display current market values received by all user");
            System.out.println(u1.getUserId() + ":\n" + u1.getCurrentMarkets());
            System.out.println(u2.getUserId() + ":\n" + u2.getCurrentMarkets());
            System.out.println(u3.getUserId() + ":\n" + u3.getCurrentMarkets());
            System.out.println(u4.getUserId() + ":\n" + u4.getCurrentMarkets());
            System.out.println(u5.getUserId() + ":\n" + u5.getCurrentMarkets());

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep M) Cancel any open orders and quotes");
            ProductManager.getInstance().cancelQuote("TGT", "ANA");
            ProductManager.getInstance().cancelQuote("TGT", "BOB");
            ProductManager.getInstance().cancel(t1);
            ProductManager.getInstance().cancel(t2);
            ProductManager.getInstance().cancel(t3);
            ProductManager.getInstance().cancel(t4);

            ////////////////////////////////////////////////////////////////////////////
            System.out.println("\n\nStep N) Display current market values received by all user");
            System.out.println(u1.getUserId() + ":\n" + u1.getCurrentMarkets());
            System.out.println(u2.getUserId() + ":\n" + u2.getCurrentMarkets());
            System.out.println(u3.getUserId() + ":\n" + u3.getCurrentMarkets());
            System.out.println(u4.getUserId() + ":\n" + u4.getCurrentMarkets());
            System.out.println(u5.getUserId() + ":\n" + u5.getCurrentMarkets());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /*
    public static void main(String[] args) {
        try {
            TradingSim.runSim();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
