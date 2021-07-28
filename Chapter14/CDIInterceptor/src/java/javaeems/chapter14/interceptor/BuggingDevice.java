package javaeems.chapter14.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.*;

public class BuggingDevice {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        Object result = null;
        Bed bed = (Bed) context.getTarget();

        String parameterString = Arrays.asList(context.getParameters()).toString();
        System.out.println("BedInterceptor: " + parameterString
                + " is about to try " + bed + "...");
        try {
            result = context.proceed();
        } catch (Exception e) {
            System.out.println("BedInterceptor: ....which raised " + e);
            throw e;
        }
        System.out.println("BedInterceptor: ....and has found it to be " + result);
        return result;
    }

}
