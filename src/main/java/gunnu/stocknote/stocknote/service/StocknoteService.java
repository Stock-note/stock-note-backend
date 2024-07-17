package gunnu.stocknote.stocknote.service;

import gunnu.stocknote.stocknote.dto.response.StocknoteResponseDTO;
import gunnu.stocknote.stocknote.entity.Stocknote;
import gunnu.stocknote.stocknote.repository.StocknoteJpaRepository;
import gunnu.stocknote.stocknote.repository.StocknoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StocknoteService {

    private final StocknoteRepository stocknoteRepository;
    private final StocknoteJpaRepository jpaRepository;


    public StocknoteResponseDTO createStocknote(
        final String title,
        final String contents,
        final String image,
        final Long stockId,
        final Long userId
    ) {
        Stocknote stocknote = stocknoteRepository.save(Stocknote.builder()
            .title(title)
            .contents(contents)
            .image(image)
            .stockId(stockId)
            .userId(userId)
            .build());
        return StocknoteResponseDTO.from(stocknote);
    }
}
