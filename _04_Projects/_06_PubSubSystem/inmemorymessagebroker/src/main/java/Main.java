import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Topic topic = new Topic("topic1");
        MessageBroker broker = new MessageBroker(topic);

        Consumer c1 = new Consumer("consumer1", topic);
        Consumer c2 = new Consumer ("consumer2", topic);

        topic.subscribe(c1);
        topic.subscribe(c2);

        ExecutorService producers = Executors.newCachedThreadPool();
        producers.submit(new Producer("producer1", broker));
        producers.submit(new Producer("producer2", broker));

        Thread.sleep(100000);
        producers.shutdown();
    }
}
