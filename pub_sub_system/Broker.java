import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Broker {
    private UUID brokerId;
    private final ConcurrentHashMap<String, Topic> topicHashMap;
    private final ExecutorService consumerExecutor;
    private final ExecutorService publisherExecutor;
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 20;
    private static final int QUEUE_POOL_SIZE = 20;

    public Broker() {
        brokerId = UUID.randomUUID();
        topicHashMap = new ConcurrentHashMap<>();
        topicHashMap.put("music", new Topic("music"));
        topicHashMap.put("activity", new Topic("activity"));
        topicHashMap.put("stock_price", new Topic("stock_price"));
        topicHashMap.put("books", new Topic("books"));
        topicHashMap.put("trump_tariffs", new Topic("trump_tariffs"));
        topicHashMap.put("insta_popular_user_followers", new Topic("insta_hot_user_likes"));
        topicHashMap.put("bank_transfer", new Topic("bank_transfer"));

        consumerExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(QUEUE_POOL_SIZE));
        publisherExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(QUEUE_POOL_SIZE));
    }

    public void publishAndConsume() {
        Publisher publisher = new Publisher(topicHashMap.get("insta_popular_user_followers").getQueue());
        Consumer consumer = new Consumer(topicHashMap.get("insta_popular_user_followers").getQueue());
        for (int i = 0; i < CORE_POOL_SIZE; i++) {
            Thread thread = new Thread(publisher);
            Thread consumerThread = new Thread(consumer);
            publisherExecutor.submit(thread);
            consumerExecutor.submit(consumerThread);
        }
        publisherExecutor.shutdown();
        consumerExecutor.shutdown();
        try{
            publisherExecutor.awaitTermination(5L, TimeUnit.SECONDS);
            consumerExecutor.awaitTermination(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Program Interrupted without task completion");
        }
        System.out.println("END OF PROGRAM");
    }
}
