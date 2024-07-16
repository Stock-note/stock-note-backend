package gunnu.stocknote.user.dto.response;

import gunnu.stocknote.user.entity.User;

public record UserResponseDTO(
    Long userId,
    String nickname,
    String profile
) {

    public static UserResponseDTO from(final User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getNickname(),
            user.getProfile()
        );
    }
}
