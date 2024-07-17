package gunnu.stocknote.stock.repository;

import gunnu.stocknote.stock.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapperRepository {

    Stock findStockByName(final String name);
}
