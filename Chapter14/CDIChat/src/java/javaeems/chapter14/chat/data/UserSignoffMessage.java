package javaeems.chapter14.chat.data;

public class UserSignoffMessage extends BasicMessage {

    public UserSignoffMessage(String username) {
        super(SIGNOFF_REQUEST, username);
    }

    public String getUsername() {
        return super.getData();
    }

}
