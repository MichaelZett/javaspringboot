package de.zettsystems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.STRICT_STUBS;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = STRICT_STUBS)
class BrokerTest {

    @Mock
    private StockService stockService;

    @InjectMocks
    private Broker testee;

    @Captor
    private ArgumentCaptor<Integer> quantityCaptor;

    @Captor
    private ArgumentCaptor<Stock> stockCaptor;


    @Test
    void shouldBuyStocks() {
        when(stockService.getPrice(any(Stock.class))).thenReturn(100.0D);

        testee.buyBestStock(1_000_000);

        verify(stockService).getPrice(any(Stock.class));
        verify(stockService).buy(any(Stock.class), eq(10_000));
        verify(stockService).buy(any(Stock.class), quantityCaptor.capture());

        assertThat(quantityCaptor.getValue()).isEqualTo(10_000);

        verify(stockService).buy(stockCaptor.capture(), eq(10_000));

        final Stock value = stockCaptor.getValue();
        assertThat(value.getName()).isEqualTo("SAP");
    }
}