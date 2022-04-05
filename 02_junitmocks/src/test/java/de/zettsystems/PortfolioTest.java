package de.zettsystems;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PortfolioTest {
    public static final double SAP_VALUE = 123.87D;
    public static final int SAP_QUANTITY = 50;
    private static final int TELEKOM_QUANTITY = 23;
    private static final Double TELEKOM_VALUE = 22.99D;

    @Test
    void shouldGetMarkedValue() {
        // Given
        Stock sap = createStock("123", "SAP", SAP_QUANTITY);
        Portfolio testee = new Portfolio();
        testee.setStocks(Arrays.asList(sap));

        StockService mockedService = mock(StockService.class);
        when(mockedService.getPrice(sap)).thenReturn(SAP_VALUE);
        testee.setStockService(mockedService);

        // When
        final double result = testee.getMarketValue();

        // Then
        assertThat(result).isNotZero().isEqualTo(SAP_VALUE * SAP_QUANTITY);
    }

    @Test
    void shouldGetMarkedValueOfMoreStocks() {
        // Given
        Stock sap = createStock("123", "SAP", SAP_QUANTITY);
        Stock telekom = createStock("473", "TELEKOM", TELEKOM_QUANTITY);
        Portfolio testee = new Portfolio();
        testee.setStocks(Arrays.asList(sap, telekom));

        StockService mockedService = mock(StockService.class);
        when(mockedService.getPrice(sap)).thenReturn(SAP_VALUE);
        when(mockedService.getPrice(telekom)).thenReturn(TELEKOM_VALUE);

        testee.setStockService(mockedService);

        // When
        final double result = testee.getMarketValue();

        // Then
        assertThat(result).isNotZero().isEqualTo(SAP_VALUE * SAP_QUANTITY + TELEKOM_VALUE * TELEKOM_QUANTITY);

        verify(mockedService, times(2)).getPrice(any(Stock.class));
    }


    @Test
    void shouldHandleExcpetions() {
        // Given
        Stock sap = createStock("123", "SAP", SAP_QUANTITY);
        Portfolio testee = new Portfolio();
        testee.setStocks(Arrays.asList(sap));

        StockService mockedService = mock(StockService.class);
        doThrow(IllegalStateException.class).when(mockedService).getPrice(sap);
        testee.setStockService(mockedService);

        // When
        final double result = testee.getMarketValue();

        // Then
        assertThat(result).isZero();
    }


    private Stock createStock(String stockId, String sap, int quantity) {
        return new Stock(stockId, sap, quantity);
    }
}