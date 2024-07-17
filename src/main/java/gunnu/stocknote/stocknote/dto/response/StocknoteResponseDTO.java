package gunnu.stocknote.stocknote.dto.response;

import gunnu.stocknote.stocknote.entity.Stocknote;

public record StocknoteResponseDTO(

    Long stocknoteId,
    String title,
    String contents,
    String image,
    Long userId,
    Long stockId

) {

    public static StocknoteResponseDTO from(Stocknote stocknote) {
        return new StocknoteResponseDTO(
            stocknote.getStocknoteId(),
            stocknote.getTitle(),
            stocknote.getContents(),
            stocknote.getImage(),
            stocknote.getUserId(),
            stocknote.getStockId()
        );
    }
}
