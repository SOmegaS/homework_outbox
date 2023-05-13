package hw.outbox.homework_outbox;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "USERS")
@NoArgsConstructor
public class UserDTO {
    @Id
    @GeneratedValue
    Long id;
    String login;
    String password;

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
