package gunnu.stocknote.stocknote.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateStocknoteRequestDTO(
    @NotBlank(message = "필수 입력 값 입니다")
    String title,

    String contents,
    String image,
    @NotNull
    Long stockId
) {

}
