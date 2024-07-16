package gunnu.stocknote.user.controller;

import gunnu.stocknote.common.dto.ResponseDTO;
import gunnu.stocknote.user.dto.request.SignupRequestDTO;
import gunnu.stocknote.user.dto.response.UserResponseDTO;
import gunnu.stocknote.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> signup(
        @Valid @RequestBody SignupRequestDTO requestDTO
    ) {
        UserResponseDTO responseDTO = userService.signup(
            requestDTO.nickname(),
            requestDTO.password());

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDTO.<UserResponseDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("회원가입 성공")
                .data(responseDTO)
                .build());
    }
}
