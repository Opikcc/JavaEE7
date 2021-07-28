package javaeems.chapter4.hello.beans;

import javax.inject.Named;
import javax.enterprise.context.*;

@Named(value = "myHelloBean")
@RequestScoped
public class HelloBean {
    private String name = "dear reader";
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    
}
