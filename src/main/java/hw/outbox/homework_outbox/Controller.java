package hw.outbox.homework_outbox;

import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@EnableScheduling
public class Controller {
    private final UserRepository userRepository;
    private final OutboxRepository outboxRepository;
    private final RabbitTemplate rabbitmq;

    @Autowired
    public Controller(UserRepository userRepository, OutboxRepository outboxRepository, RabbitTemplate rabbitmq) {
        this.userRepository = userRepository;
        this.outboxRepository = outboxRepository;
        this.rabbitmq = rabbitmq;
    }

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void processOutbox() {
        List<OutboxDTO> outboxRows =  outboxRepository.findAll();
        for (OutboxDTO outbox : outboxRows) {
            rabbitmq.convertAndSend(BrokerConfiguration.TOPIC_NAME, outbox);
        }
        outboxRepository.deleteAll(outboxRows);
    }

    @PostMapping("/registerUser")
    @Transactional
    public boolean registerUser(@RequestBody UserDTO user) {
        if (user.login == null || user.password == null) {
            return false;
        }
        userRepository.save(user);
        outboxRepository.save(new OutboxDTO(user.login));
        return true;
    }

    @GetMapping("/getUser")
    @Nullable
    public UserDTO getUser(String login) {
        Optional<UserDTO> user = userRepository.findByLogin(login);
        return user.orElse(null);
    }
}
