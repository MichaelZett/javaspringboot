package de.zettsystems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class PortfolioAnnotationTest {
    public static final double SAP_VALUE = 123.87D;
    public static final int SAP_QUANTITY = 50;
    private static final int TELEKOM_QUANTITY = 23;
    private static final Double TELEKOM_VALUE = 22.99D;

    @Mock
    private StockService mockedService;

    @Spy
    private Stock microsoft;

    @InjectMocks
    private Portfolio testee;

    @Test
    void testSpy() {
        when(microsoft.getQuantity()).thenReturn(17);

        assertThat(microsoft.getName()).isEqualTo("Microsoft");
        assertThat(microsoft.getQuantity()).isEqualTo(17);

        when(microsoft.getName()).thenReturn("Apple");
        assertThat(microsoft.getName()).isEqualTo("Apple");
    }

    @Test
    void shouldGetMarkedValueOfMoreStocks() {
        // Given
        Stock sap = createStock("123", "SAP", SAP_QUANTITY);
        Stock telekom = createStock("473", "TELEKOM", TELEKOM_QUANTITY);
        testee.setStocks(Arrays.asList(sap, telekom));

        doReturn(SAP_VALUE).when(mockedService).getPrice(sap);
        doReturn(TELEKOM_VALUE).when(mockedService).getPrice(telekom);

        // When
        final double result = testee.getMarketValue();

        // Then
        assertThat(result).isNotZero().isEqualTo(SAP_VALUE * SAP_QUANTITY + TELEKOM_VALUE * TELEKOM_QUANTITY);

        verify(mockedService, times(2)).getPrice(any(Stock.class));
    }

    private Stock createStock(String stockId, String sap, int quantity) {
        return new Stock(stockId, sap, quantity);
    }
}
