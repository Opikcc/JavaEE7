package javaeems.chapter15.hello;

import javax.ejb.Stateful;
import java.io.Serializable;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.DeclareRoles;

@Stateful
@PermitAll
@DeclareRoles({"registered-user", "administrator", "guest"})
public class HelloBeanImpl implements HelloBean, Serializable {

    private static final long serialVersionUID = 1L;
    private String greeting = "Hello";

    @Override
    public String sayHello() {
        return greeting;
    }

    @RolesAllowed({"administrator", "registered-user"})
    @Override
    public String sayHelloTo(String name) {
        return greeting + " " + name + " !";
    }

    @RolesAllowed("administrator")
    @Override
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
