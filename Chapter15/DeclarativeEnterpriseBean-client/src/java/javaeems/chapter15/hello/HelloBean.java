package javaeems.chapter15.hello;

import javax.ejb.Remote;

@Remote
public interface HelloBean {

    public String sayHello();

    public String sayHelloTo(String name);

    public void setGreeting(String greeting);
}
