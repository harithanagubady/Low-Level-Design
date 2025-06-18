import java.util.Random;
import java.util.UUID;

public class Producer implements Runnable {

    private final String id;
    private final MessageBroker broker;
    private final Random rnd = new Random();

    public Producer(String id, MessageBroker broker) {
        this.id = id;
        this.broker = broker;
    }

    public void send (String payload) {
        broker.publish(new Message(UUID.randomUUID().toString(), payload, System.currentTimeMillis()));
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                String payload = "Data-" + id + "-" + i;
                send(payload);
                Thread.sleep(rnd.nextInt(1000));
            }
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
        }
    }
}
