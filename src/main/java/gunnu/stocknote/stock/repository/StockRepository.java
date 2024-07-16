package gunnu.stocknote.stock.repository;

import gunnu.stocknote.stock.entity.Stock;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findStockByName(final String name);
}
