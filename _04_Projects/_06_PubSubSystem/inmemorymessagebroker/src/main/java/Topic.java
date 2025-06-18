import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Topic {

    private String topicName;
    private Message message;
    private final List<Consumer> consumers;

    public Topic (String topicName) {
        this.topicName = topicName;
        this.consumers = new ArrayList<>();
    }

    public void publish(Message message) {
        this.message = message;
        notifyConsumers();
    }

    public void subscribe(Consumer consumer) {
        this.consumers.add(consumer);
    }

    private void notifyConsumers() {
        for (Consumer consumer : consumers) {
            consumer.consume();
        }
    }
}
