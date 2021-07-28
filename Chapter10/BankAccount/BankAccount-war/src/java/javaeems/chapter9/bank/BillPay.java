package javaeems.chapter9.bank;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface BillPay {

    public List<String> getPayees();

    public String doPay(String accountNumber, String payee, double amount) throws PaymentException;

}
