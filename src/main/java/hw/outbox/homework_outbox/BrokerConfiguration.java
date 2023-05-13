package hw.outbox.homework_outbox;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerConfiguration {
    public static final String TOPIC_NAME = "queue";

    @Bean
    public Queue queue() {
        return new Queue(TOPIC_NAME, true);
    }
}