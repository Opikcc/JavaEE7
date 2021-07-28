package javaeems.chapter9.bank;

import javax.ejb.Stateful;
import javax.annotation.PostConstruct;
import javax.interceptor.Interceptors;

@Stateful

public class CheckingImpl implements Checking {
    private double balance;
    private String accountNumber;
    
    @PostConstruct
    private void init() {
        this.balance = 400;
        this.accountNumber = "8729-356-XXXXX";
    }

    @Override
    public double getBalance() {
        return this.balance;
    }
    
    @Override
    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    @Override
    @Interceptors(AuditInterceptor.class)
    public void doDeduct(double amount) throws PaymentException {
        if (amount > this.balance) {
            throw new PaymentException("Not enough cash in the account to deduct " + amount);
        } else {
            balance = balance - amount;
        }
    }
    
}
