import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable {
    private final UUID consumerId;
    private final LinkedBlockingQueue<Message> queue;
    private int messageConsumed = 0;
    private static final Integer MAX_MESSAGE_TO_CONSUME = 100;

    public Consumer(LinkedBlockingQueue<Message> queue) {
        this.consumerId = UUID.randomUUID();
        this.queue = queue;
    }

    @Override
    public void run() {
        consume();
    }

    public synchronized void consume() {
        while(queue.isEmpty() && messageConsumed < MAX_MESSAGE_TO_CONSUME) {
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        while(messageConsumed < MAX_MESSAGE_TO_CONSUME) {
            Message msg = queue.poll();
            messageConsumed++;
            System.out.println(String.format("Consumed by %s, ts:%d msg: %s", this.consumerId.toString(), msg.getTimestamp().getEpochSecond(), msg.getText()));
            notifyAll();
        }
    }
}
