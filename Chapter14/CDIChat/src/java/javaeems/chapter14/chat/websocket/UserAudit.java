package javaeems.chapter14.chat.websocket;

import java.text.SimpleDateFormat;
import javaeems.chapter14.chat.event.*;
import javax.enterprise.event.*;
import java.util.*;

public class UserAudit {

    void userJoined(@Observes UserJoinedEvent e) {
        System.out.println(e.getUser().getName() + " joined the chat at " + this.format(e.getTimestamp()));
    }

    void userLeft(@Observes UserLeftEvent e) {
        System.out.println(e.getUser().getName() + " left the chat at " + this.format(e.getTimestamp()));
    }

    String format(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
        String formatted = sdf.format(d);
        return formatted;
    }

}
