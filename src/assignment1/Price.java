package assignment1;

import java.util.Objects;

public class Price implements Comparable<Price> {
    private final int value;

    // Package private: prevents Main from creating Price objects
    Price(int value) {
        // Final: no setter necessary
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isNegative() {
        if (value < 0) {
            return true;
        }

        return false;
    }

    public Price add(Price p) throws InvalidPriceOperation {
        if (p == null) {
            throw new InvalidPriceOperation("Invalid price for addition");
        }

        return PriceFactory.makePrice(value + p.getValue());
    }

    public Price subtract(Price p) throws InvalidPriceOperation {
        if (p == null) {
            throw new InvalidPriceOperation("Invalid price for subtraction");
        }

        return PriceFactory.makePrice(value - p.getValue());
    }

    public Price multiply(int n) throws InvalidPriceOperation {
        if (n <= 0) {
            throw new InvalidPriceOperation("Invalid quantity for multiplication");
        }

        return PriceFactory.makePrice(value * n);
    }

    public boolean greaterOrEqual(Price p) throws InvalidPriceOperation {
        if (p == null) {
            throw new InvalidPriceOperation("Invalid price supplied ( >= )");
        }

        return value >= p.getValue();
    }

    public boolean lessOrEqual(Price p) throws InvalidPriceOperation {
        if (p == null) {
            throw new InvalidPriceOperation("Invalid price supplied ( <= )");
        }

        return value <= p.getValue();
    }

    public boolean greaterThan(Price p) throws InvalidPriceOperation {
        if (p == null) {
            throw new InvalidPriceOperation("Invalid price supplied ( > )");
        }

        return value > p.getValue();
    }

    public boolean lessThan(Price p) throws InvalidPriceOperation {
        if (p == null) {
            throw new InvalidPriceOperation("Invalid price supplied ( < )");
        }

        return value < p.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return value == price.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public int compareTo(Price p) {
        if (p == null) {
            // Can't use custom exception class for this method
            throw new NullPointerException("Invalid price object");
        }

        return value - p.getValue();
    }

    @Override
    public String toString() {
        double num = this.value / 100.0;
        return String.format("$%,.2f", num); // Comma grouping and 2 decimal place specifiers

        /*
        StringBuilder dollarFormat = new StringBuilder("$");
        int dollars = value / 100;
        int cents = Math.abs(value % 100);
        dollarFormat.append(dollars);
        dollarFormat.append(".");
        dollarFormat.append(cents);

        if (dollarFormat.substring(dollarFormat.length() - 2, dollarFormat.length()).equals(".0")) {
            dollarFormat.append(0);
        }

        return dollarFormat.toString();

        */
    }
}
