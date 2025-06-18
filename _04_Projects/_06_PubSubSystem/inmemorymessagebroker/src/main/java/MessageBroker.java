public class MessageBroker {

    private final Topic topic;

    public MessageBroker(Topic topic) {
        this.topic = topic;
    }

    public void publish(Message message) {
        topic.publish(message);
    }
}
