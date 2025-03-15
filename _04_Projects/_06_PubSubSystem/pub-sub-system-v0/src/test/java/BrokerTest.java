import com.low_level_design.pub_sub.model.Broker;
import com.low_level_design.pub_sub.model.Consumer;
import com.low_level_design.pub_sub.model.Message;
import com.low_level_design.pub_sub.model.Producer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class BrokerTest {

    @InjectMocks
    private Broker broker;
    private Producer producer;
    private Consumer consumer;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        broker = new Broker();
        producer = new Producer(1, broker);
        consumer = new Consumer(1, broker);
        broker.createTopic(1, 10000);
        broker.subscribe(1, consumer);
    }

    @Test
    void testMessagePublishing() {
        Message message = new Message(1, "Hello", System.currentTimeMillis());
        producer.publish(1, message);
        assertFalse(broker.getTopic(1).getMessages().isEmpty());
    }

    @Test
    void testMessageAcknowledgement() {
        Message message = new Message(1, "Hello", System.currentTimeMillis());
        producer.publish(1, message);
        broker.acknowledgeMessage(1, consumer, message);
        assertTrue(broker.getTopic(1).getAcks().containsKey(message));
    }

    @Test
    void testMessageExpiration() throws InterruptedException {
        Message message = new Message(1, "Hello", System.currentTimeMillis());
        producer.publish(1, message);
        Thread.sleep(10000);
        broker.getTopic(1).removeExpiredMessages();
        assertTrue(broker.getTopic(1).getMessages().isEmpty());
    }
}