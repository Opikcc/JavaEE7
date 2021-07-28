package javaeems.chapter9.bank;

import javax.ejb.*;

@Remote
public interface Checking {

    public double getBalance();

    public void doDeduct(double amount) throws PaymentException;

    public String getAccountNumber();
}
