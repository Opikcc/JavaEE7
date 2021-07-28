
package javaeems.chapter9.bank;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.interceptor.Interceptor;
import java.util.*;

@Interceptor
public class AuditInterceptor {
    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        Object result = null;
        String ebClassname = context.getTarget().getClass().getSimpleName();
        String methodName = context.getTarget().getClass().getSimpleName();
        String parameterString = Arrays.asList(context.getParameters()).toString();
        System.out.println("AuditInterceptor: The call to " + 
                                ebClassname + 
                                "." + methodName + 
                                parameterString + "...");
        try {
            result = context.proceed();
        } catch (Exception e) {
            System.out.println("AuditInterceptor: ....which raised " + e);
            throw e;
        }
        System.out.println("AuditInterceptor: ....has returned " + result);
        return result;
    }
    
    
}
