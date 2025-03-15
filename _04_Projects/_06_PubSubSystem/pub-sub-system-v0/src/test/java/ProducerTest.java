import com.low_level_design.pub_sub.model.Broker;
import com.low_level_design.pub_sub.model.Message;
import com.low_level_design.pub_sub.model.Producer;
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
public class ProducerTest {

    @Mock private Broker brokerMock;

    @InjectMocks private Producer producer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producer = new Producer(1, brokerMock);
    }

    @Test
    void testProducerPublishesMessage() {
        Message message = new Message(1, "Hello World!");
        producer.publish(1, message);
        verify(brokerMock, times(1)).publish(1, message);
    }
}
