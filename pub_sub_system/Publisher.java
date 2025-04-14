import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class Publisher implements Runnable {
    private final UUID id;
    private final LinkedBlockingQueue<Message> queue;
    private static final Integer MAX_MESSAGE_TO_PUBLISH = 100;
    private int messagePublished = 0;

    public Publisher(LinkedBlockingQueue<Message> queue) {
        this.id = UUID.randomUUID();
        this.queue = queue;
    }

    @Override
    public void run() {
        publish();
    }

    public synchronized void publish() {
        while (queue.remainingCapacity() == 0 && messagePublished < MAX_MESSAGE_TO_PUBLISH) {
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        while(messagePublished < MAX_MESSAGE_TO_PUBLISH) {
            Message message = new Message(String.valueOf(messagePublished));
            this.queue.offer(message);
            messagePublished++;
            notifyAll();
        }
    }
}
