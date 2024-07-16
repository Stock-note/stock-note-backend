package gunnu.stocknote.user.service;

import gunnu.stocknote.common.TokenProvider;
import gunnu.stocknote.exception.user.ExistUsernameException;
import gunnu.stocknote.exception.user.NotMatchPasswordException;
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
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO signup(final String username, final String password) {
        checkUsernameExists(username);

        User savedUser = userRepository.save(new User(
            username,
            passwordEncoder.encode(password),
            USER_ROLE)
        );

        return UserResponseDTO.from(savedUser);
    }

    public String login(final String username, final String password) {
        User user = userRepository.findByUsername(username)
            .filter(u -> u.getPassword().equals(passwordEncoder.encode(password)))
            .orElseThrow(NotMatchPasswordException::new);

        return tokenProvider.createAccessToken(
            user.getUserId(),
            user.getUsername(),
            user.getUserRole());
    }

    private void checkUsernameExists(final String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new ExistUsernameException();
        });
    }
}
