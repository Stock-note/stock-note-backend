package gunnu.stocknote.user.service;

import gunnu.stocknote.exception.user.NicknameExistException;
import gunnu.stocknote.user.dto.response.UserResponseDTO;
import gunnu.stocknote.user.entity.User;
import gunnu.stocknote.user.entity.UserRole;
import gunnu.stocknote.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final UserRole USER_ROLE = UserRole.USER;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO signup(final String nickname, final String password) {
        checkNicknameExists(nickname);
        String encodedPassword = passwordEncoder.encode(password);

        User savedUser = userRepository.save(new User(
            nickname,
            encodedPassword,
            USER_ROLE));

        return UserResponseDTO.from(savedUser);
    }

    private void checkNicknameExists(final String nickname) {
        userRepository.findByNickname(nickname).ifPresent(user -> {
            throw new NicknameExistException();
        });
    }
}
