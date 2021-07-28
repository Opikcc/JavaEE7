
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.Conversation;
import javax.inject.Named;
import javax.inject.Inject;


@Named(value = "myHelloBean")
@RequestScoped
public class ConversationalBean {
    @Inject
    Conversation conversation;
    
    public void finishInteractions() {
        conversation.end();
    }
    
}
