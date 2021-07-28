package javaeems.chapter14.chat.websocket;

import java.util.*;
import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javaeems.chapter14.chat.event.*;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@ApplicationScoped
public class UserList {

    private final List<User> users = new ArrayList<>();
    @Inject
    private Event<ChatEvent> eventSource;

    class UserImpl implements User {

        private String name;

        @Override
        public void setName(String name) {
            this.name = name;
            eventSource.fire(new UserJoinedEvent(this));
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String toString() {
            return "User: " + name;
        }

    }

    @Default
    @Produces
    @Dependent
    private User getUser() {
        User user = new UserImpl();
        this.users.add(user);
        return user;
    }

    public void deleteUser(User user) {
        this.users.remove(user);
        eventSource.fire(new UserLeftEvent(user));
    }

    public List<String> getUsernames() {
        List<String> usernames = new ArrayList<>();
        for (User u : this.users) {
            usernames.add(u.getName());
        }
        return usernames;
    }

    public String validateUsername(String newUsername) {
        if (this.getUsernames().contains(newUsername)) {
            return this.validateUsername(newUsername + "1");
        }
        return newUsername;
    }

}
