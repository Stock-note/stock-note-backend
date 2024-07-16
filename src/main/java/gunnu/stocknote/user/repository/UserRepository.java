package gunnu.stocknote.user.repository;

import gunnu.stocknote.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(final String username);
}
