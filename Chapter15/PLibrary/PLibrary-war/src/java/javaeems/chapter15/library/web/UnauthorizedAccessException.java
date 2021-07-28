package javaeems.chapter15.library.web;

public class UnauthorizedAccessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String ejbClassname;
    private String operation;

    public UnauthorizedAccessException() {
    }

    public UnauthorizedAccessException(String msg, String ejbClassname, String operation) {
        super(msg);
        this.ejbClassname = ejbClassname;
        this.operation = operation;
    }

    public String getEjbClassname() {
        return this.ejbClassname;
    }

    public String getOperation() {
        return this.operation;
    }
}
