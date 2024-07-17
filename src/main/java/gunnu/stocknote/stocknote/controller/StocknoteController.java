package gunnu.stocknote.stocknote.controller;

import gunnu.stocknote.common.dto.ResponseDTO;
import gunnu.stocknote.stocknote.dto.request.CreateStocknoteRequestDTO;
import gunnu.stocknote.stocknote.dto.response.StocknoteResponseDTO;
import gunnu.stocknote.stocknote.service.StocknoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stocknotes")
@RequiredArgsConstructor
public class StocknoteController {

    private final StocknoteService stocknoteService;

    @PostMapping
    public ResponseEntity<ResponseDTO<StocknoteResponseDTO>> createStocknote(
        @RequestBody CreateStocknoteRequestDTO requestDTO,
        HttpServletRequest request
    ) {
        StocknoteResponseDTO responseDTO = stocknoteService.createStocknote(
            requestDTO.title(),
            requestDTO.contents(),
            requestDTO.image(),
            requestDTO.stockId(),
            (Long) request.getAttribute("userId")
        );
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResponseDTO.<StocknoteResponseDTO>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("매매일지작성성공")
                .data(responseDTO)
                .build()
            );
    }
}
