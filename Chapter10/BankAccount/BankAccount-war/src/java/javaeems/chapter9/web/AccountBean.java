package javaeems.chapter9.web;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.FacesException;
import javax.ejb.EJB;
import javaeems.chapter9.bank.Checking;
import javaeems.chapter9.bank.BillPay;
import javaeems.chapter9.bank.PaymentException;
import java.util.*;

@ManagedBean
@SessionScoped
public class AccountBean implements Serializable {

    private static final long serialVersionUID = 1799716143005270436L;
    @EJB
    private Checking checking;
    @EJB
    private BillPay billPay;
    private String currentPayee;
    private double amount = 15;
    private boolean paymentOK = false;
    private String paymentConfirmation = null;

    public AccountBean() {
    }

    public String getCheckingAccountNumber() {
        return this.checking.getAccountNumber();
    }

    public double getCheckingBalance() {
        return this.checking.getBalance();
    }

    public List<String> getPayees() {
        return this.billPay.getPayees();
    }

    public String getCurrentPayee() {
        if (this.currentPayee == null) {
            this.currentPayee = this.billPay.getPayees().get(0);
        }
        return this.currentPayee;
    }

    public void setCurrentPayee(String payee) {
        this.currentPayee = payee;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void reset() {
        this.paymentOK = false;
        this.paymentConfirmation = null;
    }

    public String getPaymentConfirmation() {
        return this.paymentConfirmation;
    }

    public boolean getPaymentOK() {
        return this.paymentOK;
    }

    public void makePayment() {
        try {
            this.checking.doDeduct(this.amount);
            String confirmation = this.billPay.doPay(this.checking.getAccountNumber(), currentPayee, this.amount);
            this.paymentOK = true;
            this.paymentConfirmation = confirmation;
            this.amount = 15;
            this.currentPayee = null;
        } catch (PaymentException pe) {
            throw new FacesException(pe.getMessage());
        }
    }

}
