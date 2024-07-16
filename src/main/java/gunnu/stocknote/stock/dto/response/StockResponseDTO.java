package gunnu.stocknote.stock.dto.response;

import gunnu.stocknote.stock.entity.Market;
import gunnu.stocknote.stock.entity.Stock;

public record StockResponseDTO(
    Long stockId,
    String name,
    Long code,
    Market market,
    Long price
) {

    public static StockResponseDTO from(final Stock stock) {
        return new StockResponseDTO(
            stock.getStockId(),
            stock.getName(),
            stock.getCode(),
            stock.getMarket(),
            stock.getPrice()
        );
    }
}
