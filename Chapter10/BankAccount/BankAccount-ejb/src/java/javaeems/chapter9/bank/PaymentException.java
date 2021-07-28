package javaeems.chapter9.bank;

public class PaymentException extends Exception {

    private static final long serialVersionUID = 3264440871855639283L;

    public PaymentException(String message) {
        super(message);
    }
}
