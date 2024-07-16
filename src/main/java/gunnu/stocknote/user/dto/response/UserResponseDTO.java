package gunnu.stocknote.user.dto.response;

import gunnu.stocknote.user.entity.User;

public record UserResponseDTO(
    Long userId,
    String username,
    String profile
) {

    public static UserResponseDTO from(final User user) {
        return new UserResponseDTO(
            user.getUserId(),
            user.getUsername(),
            user.getProfile()
        );
    }
}
