package gunnu.stocknote.stock.repository;

import gunnu.stocknote.stock.entity.Stock;

public interface StockRepository {

    Stock findStockByName(final String name);
}
