package gunnu.stocknote.stocknote.dto.request;

public record CreateStocknoteRequestDTO(
    String title,
    String contents,
    String image,
    Long stockId
) {

}
