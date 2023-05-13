package hw.outbox.homework_outbox;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "OUTBOX")
@NoArgsConstructor
public class OutboxDTO implements Serializable {
    @Id
    @GeneratedValue
    Long id;
    String login;
    public OutboxDTO(String login) {
        this.login = login;
    }
}
