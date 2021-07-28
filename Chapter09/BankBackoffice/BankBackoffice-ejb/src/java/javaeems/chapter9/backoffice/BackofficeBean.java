package javaeems.chapter9.backoffice;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.ejb.EJB;

@MessageDriven(mappedName = "jms/myQueue")
public class BackofficeBean {
    @EJB(lookup="java:module/payment-recorder")
    private PaymentRecorder recorder;

    public BackofficeBean() {
    }

    public void onMessage(Message incoming) {
       try {
            if (incoming instanceof TextMessage) {
                TextMessage message = (TextMessage) incoming;
                this.recorder.recordPayment(message.getText());
            } else {
                System.out.println("Unexpected message type: " + incoming.getClass().getName());
            }
        } catch (JMSException jmse) {
            System.out.println("Exception recording incoming message: " + jmse.getMessage());
        }
    
    }
}
