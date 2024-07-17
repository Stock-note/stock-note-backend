package gunnu.stocknote.stock.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import gunnu.stocknote.stock.entity.Market;
import gunnu.stocknote.stock.entity.Stock;
import gunnu.stocknote.stock.repository.StockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @InjectMocks
    StockService stockService;

    @Mock
    StockRepository stockRepository;

    private final Long TEST_STOCK_ID = 1L;
    private final String TEST_STOCK_NAME = "삼성전자";
    private final String TEST_STOCK_CODE = "1234";
    private final Market TEST_STOCK_KOSPI = Market.KOSPI;
    private final Stock TEST_STOCK = Stock.builder()
        .stockId(TEST_STOCK_ID)
        .name(TEST_STOCK_NAME)
        .code(TEST_STOCK_CODE)
        .market(TEST_STOCK_KOSPI)
        .build();

    @Nested
    @DisplayName("종목검색")
    class searchStock {

        @Test
        @DisplayName("종목검색_성공")
        void 종목검색_성공() {
            //given
            when(stockRepository.findStockByName(anyString())).thenReturn(TEST_STOCK);

            //when
            stockService.searchStockByName(TEST_STOCK_NAME);
        }
    }
}
