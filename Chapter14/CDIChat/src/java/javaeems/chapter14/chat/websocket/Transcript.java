package javaeems.chapter14.chat.websocket;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Transcript {

    private final List<String> messages = new ArrayList<>();
    private final List<String> usernames = new ArrayList<>();
    private final int maxLines;

    public Transcript() {
        maxLines = 20;
    }

    public String getLastUsername() {
        return usernames.get(usernames.size() - 1);
    }

    public String getLastMessage() {
        return messages.get(messages.size() - 1);
    }

    public void addEntry(String username, String message) {
        if (usernames.size() > maxLines) {
            usernames.remove(0);
            messages.remove(0);
        }
        usernames.add(username);
        messages.add(message);
    }
}
