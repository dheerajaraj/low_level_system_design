import java.time.Instant;
import java.util.UUID;

public class Message {
    private UUID messageId;
    private String text;
    private Instant timestamp;

    public Message(String text) {
        this.messageId = UUID.randomUUID();
        this.text = text;
        this.timestamp = Instant.now();
    }

    public UUID getMessageId() {
        return messageId;
    }

    public String getText() {
        return text;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
