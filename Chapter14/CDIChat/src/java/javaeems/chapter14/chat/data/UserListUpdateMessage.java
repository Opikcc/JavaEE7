package javaeems.chapter14.chat.data;

import java.util.List;

public class UserListUpdateMessage extends StructuredMessage {

    public UserListUpdateMessage(List usernames) {
        super(ChatMessage.USERLIST_UPDATE, usernames);
    }

    public List getUserList() {
        return super.dataList;
    }

}
