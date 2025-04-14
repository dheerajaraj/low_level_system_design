public class PubSubApp {

    public static void main(String[] args) {
        Broker broker = new Broker();
        broker.publishAndConsume();
    }
}


/**
 * Functional Requirements
 * 1. The Pub-Sub system should allow publishers to publish messages to specific topics.
 * 2. Subscribers should be able to subscribe to topics of interest and receive messages published to those topics.
 * 3. The system should support multiple publishers and subscribers.
 * 4. Messages should be delivered to all subscribers of a topic in real-time.
 * 5. The system should handle concurrent access and ensure thread safety.
 * 6. The Pub-Sub system should be scalable and efficient in terms of message delivery.
 *
 * Non Functional Requirements
 * Messages in a partition should be in order
 *
 * Entities
 * Message
 * Topics
 * Subsribers
 * Publishers
 * Broker
 *
 */