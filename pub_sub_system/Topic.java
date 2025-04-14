import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class Topic {
    private UUID topicId;
    private String topicName;
    private LinkedBlockingQueue<Message> queue;
    private static final Integer QUEUE_SIZE = 10000;

    public Topic(String topicName) {
        this.topicId = UUID.randomUUID();
        this.topicName = topicName;
        queue = new LinkedBlockingQueue<>(QUEUE_SIZE);
    }

    public LinkedBlockingQueue<Message> getQueue(){
        return queue;
    }
}
