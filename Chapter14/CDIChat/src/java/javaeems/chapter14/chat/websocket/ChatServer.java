package javaeems.chapter14.chat.websocket;

import javaeems.chapter14.chat.data.*;
import java.io.IOException;
import java.util.*;
import javax.websocket.*;
import javax.websocket.server.*;
import javax.inject.Inject;

@ServerEndpoint(value = "/chat-server",
        subprotocols = {"chat"},
        decoders = {ChatDecoder.class},
        encoders = {ChatEncoder.class})
public class ChatServer {

    private Session session;
    @Inject
    private Transcript transcript;
    @Inject
    UserList userList;
    @Inject
    User currentUser;

    @OnOpen
    public void startChatChannel(Session session) {
        this.session = session;
    }

    @OnMessage
    public void handleChatMessage(ChatMessage message) {
        switch (message.getType()) {
            case NewUserMessage.USERNAME_MESSAGE:
                this.processNewUser((NewUserMessage) message);
                break;
            case ChatMessage.CHAT_DATA_MESSAGE:
                this.processChatUpdate((ChatUpdateMessage) message);
                break;
            case ChatMessage.SIGNOFF_REQUEST:
                this.processSignoffRequest((UserSignoffMessage) message);
        }
    }

    @OnError
    public void myError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }

    @OnClose
    public void endChatChannel() {
        if (this.currentUser.getName() != null) {
            this.addMessage(" just left...without even signing out !");
            this.broadcastUserListUpdate();
        }
    }

    void processNewUser(NewUserMessage message) {
        String newUsername = this.userList.validateUsername(message.getUsername());
        currentUser.setName(newUsername);
        NewUserMessage uMessage = new NewUserMessage(newUsername);
        try {
            session.getBasicRemote().sendObject(uMessage);
        } catch (IOException | EncodeException ioe) {
            System.out.println("Error signing " + message.getUsername() + " into chat : " + ioe.getMessage());
        }
        this.broadcastUserListUpdate();
        this.addMessage(" just joined.");
    }

    void processChatUpdate(ChatUpdateMessage message) {
        this.addMessage(message.getMessage());
    }

    void processSignoffRequest(UserSignoffMessage drm) {
        this.addMessage(" just left.");
        this.userList.deleteUser(this.currentUser);
        try {
            this.broadcastUserListUpdate();
            this.session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "User logged off"));
        } catch (IOException e) {
            System.out.println("Error removing user");
        }
    }

    private List<String> getUserList() {
        return userList.getUsernames();
    }

    private void broadcastUserListUpdate() {
        UserListUpdateMessage ulum = new UserListUpdateMessage(this.getUserList());
        for (Session nextSession : session.getOpenSessions()) {
            try {
                nextSession.getBasicRemote().sendObject(ulum);
            } catch (IOException | EncodeException ex) {
                System.out.println("Error updating a client : " + ex.getMessage());
            }
        }
    }

    private void broadcastTranscriptUpdate() {
        for (Session nextSession : session.getOpenSessions()) {
            ChatUpdateMessage cdm = new ChatUpdateMessage(this.transcript.getLastUsername(), this.transcript.getLastMessage());
            try {
                nextSession.getBasicRemote().sendObject(cdm);
            } catch (IOException | EncodeException ex) {
                System.out.println("Error updating a client : " + ex.getMessage());
            }
        }
    }

    private void addMessage(String message) {
        this.transcript.addEntry(this.currentUser.getName(), message);
        this.broadcastTranscriptUpdate();
    }

}
