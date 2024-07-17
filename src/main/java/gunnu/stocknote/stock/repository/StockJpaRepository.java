package gunnu.stocknote.stock.repository;

import gunnu.stocknote.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockJpaRepository extends JpaRepository<Stock, Long> {

}
