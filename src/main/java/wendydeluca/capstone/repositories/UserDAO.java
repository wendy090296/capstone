package wendydeluca.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wendydeluca.capstone.entities.User;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

}
