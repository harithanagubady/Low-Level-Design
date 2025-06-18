public class Consumer {

    private final String id;
    private final Topic topic;

    public Consumer(String id, Topic topic) {
        this.id = id;
        this.topic = topic;
    }

    public void consume() {
        System.out.println("Consumer " + id + " received message...");
        System.out.println(topic.getMessage());
    }
}
