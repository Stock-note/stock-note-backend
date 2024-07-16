package gunnu.stocknote.stock.service;

import gunnu.stocknote.exception.stock.NoSuchStockException;
import gunnu.stocknote.stock.dto.response.StockResponseDTO;
import gunnu.stocknote.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public StockResponseDTO searchStockByName(final String name) {
        return StockResponseDTO.from(stockRepository.findStockByName(name)
            .orElseThrow(NoSuchStockException::new));
    }
}
