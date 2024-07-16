package gunnu.stocknote.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SignupRequestDTO(

    @NotBlank(message = "닉네임은 필수 입력 값 입니다")
    String nickname,

    @NotBlank(message = "비밀번호는 필수 입력 값 입니다")
    String password
) {

}
