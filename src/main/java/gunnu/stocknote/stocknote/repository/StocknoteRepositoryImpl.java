package gunnu.stocknote.stocknote.repository;

import gunnu.stocknote.stocknote.entity.Stocknote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StocknoteRepositoryImpl implements StocknoteRepository {

    private final StocknoteJpaRepository jpaRepository;

    @Override
    public Stocknote save(final Stocknote stocknote) {
        return jpaRepository.save(stocknote);
    }
}
