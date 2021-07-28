package javaeems.chapter9.backoffice;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface PaymentRecorder {
    
    public void recordPayment(String payment);
    
    public List<String> getPayments();
    
}
