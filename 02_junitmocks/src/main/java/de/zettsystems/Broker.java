package de.zettsystems;

public class Broker {
    private StockService stockService;

    public Broker(StockService stockService) {
        this.stockService = stockService;
    }

    public void buyBestStock(int value) {
        Stock sap = new Stock("123", "SAP", 1);
        final double price = stockService.getPrice(sap);
        final int quantity = (int) (value / price);
        stockService.buy(sap, quantity);
    }
}
