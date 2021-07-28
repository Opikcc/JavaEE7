package javaeems.chapter9.hello;

import javax.ejb.Remote;

@Remote
public interface HelloBean {
    public String getMessageFor(String caller);
}
