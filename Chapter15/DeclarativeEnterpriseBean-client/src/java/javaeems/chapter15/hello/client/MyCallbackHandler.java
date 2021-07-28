package javaeems.chapter15.hello.client;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.callback.Callback;
import java.io.*;

public class MyCallbackHandler implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks)
            throws IOException,
            UnsupportedCallbackException {
        MyLoginWindow mlw = MyLoginWindow.gatherCredential("Login to Declarative Security");
        if (mlw.isCanceled()) {
            return;
        }
        for (Callback c : callbacks) {
            if (c instanceof NameCallback) {
                ((NameCallback) c).setName(mlw.getUsername());
            } else if (c instanceof PasswordCallback) {
                ((PasswordCallback) c).setPassword(mlw.getPassword().toCharArray());
            }
        }

    }

}
