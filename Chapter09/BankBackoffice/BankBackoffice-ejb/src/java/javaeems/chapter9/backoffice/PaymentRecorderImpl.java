

package javaeems.chapter9.backoffice;

import javax.ejb.Singleton;
import java.util.List;
import java.util.ArrayList;


@Singleton(name="payment-recorder")
public class PaymentRecorderImpl implements PaymentRecorder {
    List<String> payments = new ArrayList<>();

    @Override
    public void recordPayment(String payment) {
        this.payments.add(payment);
    }
    
    @Override
    public List<String> getPayments() {
        return this.payments;
    }
}
