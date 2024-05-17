import java.util.ArrayList;
import java.util.List;

public class MessageService {
    private List<Message> sentMessages = new ArrayList<>();

    public void sendMessage(String receiver, String message) {
        // Logic to send a message to a user would go here, e.g., through an API
        System.out.println("Message sent to " + receiver + ": " + message);
        sentMessages.add(new Message("system", receiver, message));
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }
}
