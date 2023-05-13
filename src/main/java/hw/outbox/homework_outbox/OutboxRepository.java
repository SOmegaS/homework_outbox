package hw.outbox.homework_outbox;


import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

import static org.hibernate.LockOptions.SKIP_LOCKED;

public interface OutboxRepository extends JpaRepository<OutboxDTO, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = SKIP_LOCKED + "")})
    List<OutboxDTO> findAll();
}
