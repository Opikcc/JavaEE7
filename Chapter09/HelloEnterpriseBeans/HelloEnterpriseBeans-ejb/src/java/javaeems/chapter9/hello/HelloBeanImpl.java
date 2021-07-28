
package javaeems.chapter9.hello;

import javax.ejb.Stateful;

@Stateful
public class HelloBeanImpl implements HelloBean {

    @Override
    public String getMessageFor(String caller) {
        return "hello to you, " + caller + " !";
    }
}
