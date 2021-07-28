package javaeems.chapter14.chat.event;

import javaeems.chapter14.chat.websocket.User;
import java.util.Date;

public class ChatEvent {

    User user;
    Date timestamp;

    public ChatEvent(User user) {
        this.user = user;
        this.timestamp = new Date();
    }

    public User getUser() {
        return this.user;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }
}
