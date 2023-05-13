package hw.outbox.homework_outbox;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDTO, Long> {
    Optional<UserDTO> findByLogin(String login);
}
