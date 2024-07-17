package gunnu.stocknote.stocknote.repository;

import gunnu.stocknote.stocknote.entity.Stocknote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocknoteJpaRepository extends JpaRepository<Stocknote, Long> {

}
