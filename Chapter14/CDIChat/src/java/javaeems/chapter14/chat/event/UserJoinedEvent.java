package javaeems.chapter14.chat.event;

import javaeems.chapter14.chat.websocket.User;
import javax.inject.Inject;

public class UserJoinedEvent extends ChatEvent {

    @Inject
    public UserJoinedEvent(User user) {
        super(user);
    }
}
