package gunnu.stocknote.stocknote.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import gunnu.stocknote.stocknote.dto.response.StocknoteResponseDTO;
import gunnu.stocknote.stocknote.entity.Stocknote;
import gunnu.stocknote.stocknote.repository.StocknoteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StocknoteServiceTest {

    @InjectMocks
    private StocknoteService stocknoteService;

    @Mock
    private StocknoteRepository stocknoteRepository;

    private final Long TEST_STOCKNOTE_ID = 1L;
    private final String TEST_TITLE = "title";
    private final String TEST_CONTENTS = "contents";
    private final String TEST_IMAGE = "image";
    private final Long TEST_USER_ID = 1L;
    private final Long TEST_STOCK_ID = 1L;

    private final Stocknote TEST_STOCKNOTE = Stocknote.builder()
        .stocknoteId(TEST_STOCKNOTE_ID)
        .title(TEST_TITLE)
        .contents(TEST_CONTENTS)
        .image(TEST_IMAGE)
        .userId(TEST_USER_ID)
        .stockId(TEST_STOCK_ID)
        .build();

    @Test
    @DisplayName("매매일지작성")
    void 매매일지작성() {
        //given
        when(stocknoteRepository.save(any())).thenReturn(TEST_STOCKNOTE);

        //when
        StocknoteResponseDTO responseDTO = stocknoteService.createStocknote(
            TEST_TITLE,
            TEST_CONTENTS,
            TEST_IMAGE,
            TEST_STOCK_ID,
            TEST_USER_ID
        );

        //then
        assertThat(responseDTO.stocknoteId()).isEqualTo(TEST_STOCK_ID);
        assertThat(responseDTO.title()).isEqualTo(TEST_TITLE);
        assertThat(responseDTO.contents()).isEqualTo(TEST_CONTENTS);
        assertThat(responseDTO.image()).isEqualTo(TEST_IMAGE);
        assertThat(responseDTO.userId()).isEqualTo(TEST_USER_ID);
        assertThat(responseDTO.stockId()).isEqualTo(TEST_STOCK_ID);
    }
}
