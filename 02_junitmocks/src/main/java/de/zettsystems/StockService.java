package de.zettsystems;

public interface StockService {
    double getPrice(Stock stock);

    void buy(Stock stock, int quantity);
}
