package gunnu.stocknote.user.controller;

import gunnu.stocknote.common.dto.ResponseDTO;
import gunnu.stocknote.user.dto.request.LoginRequestDTO;
import gunnu.stocknote.user.dto.request.SignupRequestDTO;
import gunnu.stocknote.user.dto.response.UserResponseDTO;
import gunnu.stocknote.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
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

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> signup(
        @Valid @RequestBody SignupRequestDTO requestDTO
    ) {
        UserResponseDTO responseDTO = userService.signup(
            requestDTO.username(),
            requestDTO.password());

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDTO.<UserResponseDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("회원가입 성공")
                .data(responseDTO)
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<Void>> login(
        HttpServletResponse servletResponse,
        @Valid @RequestBody LoginRequestDTO requestDTO
    ) {
        String token = userService.login(requestDTO.username(), requestDTO.password());
        servletResponse.addHeader(AUTHORIZATION_HEADER, token);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDTO.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .message("로그인 성공")
                .build());
    }
}
