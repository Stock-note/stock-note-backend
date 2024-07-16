package gunnu.stocknote.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import gunnu.stocknote.exception.user.ExistUsernameException;
import gunnu.stocknote.user.dto.response.UserResponseDTO;
import gunnu.stocknote.user.entity.User;
import gunnu.stocknote.user.entity.UserRole;
import gunnu.stocknote.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private final Long TEST_USER_ID = 1L;
    private final String TEST_USERNAME = "testUser";
    private final String TEST_PASSWORD = "testPassword";
    private final UserRole TEST_USER_ROLE = UserRole.USER;

    private final User TEST_USER = User.builder()
        .userId(TEST_USER_ID)
        .username(TEST_USERNAME)
        .userRole(TEST_USER_ROLE)
        .build();

    @Nested
    @DisplayName("회원가입")
    class signup {

        @Test
        @DisplayName("회원가입_성공")
        void 회원가입_성공() {
            //given
            when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
            when(passwordEncoder.encode(anyString())).thenReturn(TEST_PASSWORD);
            when(userRepository.save(any(User.class))).thenReturn(TEST_USER);

            //when
            UserResponseDTO responseDTO = userService.signup(
                TEST_USERNAME,
                TEST_PASSWORD
            );

            //then
            assertThat(responseDTO.userId()).isEqualTo(TEST_USER_ID);
            assertThat(responseDTO.username()).isEqualTo(TEST_USERNAME);
            assertThat(responseDTO.username()).isEqualTo(TEST_USERNAME);

        }

        @Test
        @DisplayName("회원가입_실패_중복_아이디")
        void 회원가입_실패_중복아이디() {
            // given
            when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(TEST_USER));

            // when & then
            assertThrows(ExistUsernameException.class, () -> {
                userService.signup(TEST_USERNAME, TEST_PASSWORD);
            });
        }
    }
}
