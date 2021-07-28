package javaeems.chapter14.chat.event;

import javaeems.chapter14.chat.websocket.User;
import javax.inject.*;

public class UserLeftEvent extends ChatEvent {

    @Inject
    public UserLeftEvent(User user) {
        super(user);
    }
}
