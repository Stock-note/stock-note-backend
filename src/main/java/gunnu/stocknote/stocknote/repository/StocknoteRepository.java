package gunnu.stocknote.stocknote.repository;

import gunnu.stocknote.stocknote.entity.Stocknote;

public interface StocknoteRepository {

    Stocknote save(Stocknote stocknote);
}
