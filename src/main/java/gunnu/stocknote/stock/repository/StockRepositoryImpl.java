package gunnu.stocknote.stock.repository;

import gunnu.stocknote.stock.entity.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StockRepositoryImpl implements StockRepository {

    private final StockJpaRepository stockJpaRepository;
    private final StockQueryRepository stockQueryRepository;
    private final StockMapperRepository stockMapperRepository;


    @Override
    public Stock findStockByName(final String name) {
        return stockMapperRepository.findStockByName(name);
    }
}
