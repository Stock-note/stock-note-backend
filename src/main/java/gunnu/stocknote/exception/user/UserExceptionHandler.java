package gunnu.stocknote.exception.user;

import gunnu.stocknote.common.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseDTO<String>> handleExistUsernameException(
        ExistUsernameException ex
    ) {
        return ResponseEntity.badRequest()
            .body(
                ResponseDTO.<String>builder()
                    .statusCode(HttpStatus.CONFLICT.value())
                    .message(ex.getMessage())
                    .build()
            );
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDTO<String>> handleNotMatchPasswordException(
        NotMatchPasswordException ex
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
