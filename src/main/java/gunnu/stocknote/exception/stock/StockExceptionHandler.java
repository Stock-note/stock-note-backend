package gunnu.stocknote.exception.stock;

import gunnu.stocknote.common.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StockExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseDTO<String>> handleNoSuchStockException(
        NoSuchStockException ex
    ) {
        return ResponseEntity.badRequest()
            .body(
                ResponseDTO.<String>builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message(ex.getMessage())
                    .build()
            );
    }
}
