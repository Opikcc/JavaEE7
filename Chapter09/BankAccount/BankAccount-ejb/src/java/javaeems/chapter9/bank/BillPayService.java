package javaeems.chapter9.bank;

import javax.ejb.Singleton;
import java.util.*;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

@Singleton
public class BillPayService implements BillPay {
    @Resource(mappedName = "jms/myConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/myQueue")
    private javax.jms.Queue queue;

    @Override
    public List<String> getPayees() {
        List<String> l = new ArrayList<>();
        l.add("energy");
        l.add("water");
        l.add("mortgage");
        l.add("cable");
        return l;
    }
    
    @Override
    public String doPay(String accountNumber, String payee, double amount) throws PaymentException {
        // process the payment in the credit system
        String confirmation = new Long(System.currentTimeMillis()).toString();
        this.updateBackofficeRecords(accountNumber, payee, amount, confirmation, new Date());
        return confirmation;
    }
    
    
    private void updateBackofficeRecords(String accountNumber, String payee, double amount, String confirmation, Date d) {
        try (Connection connection = connectionFactory.createConnection()){
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            message.setText(accountNumber + ":" + payee + " paid " + amount + " ["+confirmation+"]");
            messageProducer.send(message);

        } catch (JMSException e) {
            System.out.println("Exception occurred: " + e.toString());
        }
        
        
    }
}
