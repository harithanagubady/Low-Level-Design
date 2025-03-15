import com.low_level_design.pub_sub.model.Broker;
import com.low_level_design.pub_sub.model.Consumer;
import com.low_level_design.pub_sub.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsumerTest {
    @Mock
    private Broker broker;
    @InjectMocks
    private Consumer consumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        consumer = new Consumer(1, broker);
    }

    @Test
    void testConsumerReceivesMessage() {
        Message message = new Message(1, "Hello", System.currentTimeMillis());
        consumer.consume(1, message);
        verify(broker, times(1)).acknowledgeMessage(1, consumer, message);
    }
}