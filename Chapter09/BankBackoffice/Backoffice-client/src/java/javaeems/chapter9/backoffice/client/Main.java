package javaeems.chapter9.backoffice.client;

import javax.swing.*;
import javaeems.chapter9.backoffice.PaymentRecorder;
import java.util.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main extends JFrame {
    JTextArea jta = new JTextArea();
    PaymentRecorder recorder;
    
    
    public Main() {
        try {
            recorder = (PaymentRecorder) InitialContext.doLookup("java:app/BankBackoffice-ejb/payment-recorder");
        } catch (NamingException ne) {
            System.out.println("Could not connect to payment recorder bean, exiting. " + ne.getMessage());
            return;
        }
        JScrollPane jsp = new JScrollPane(jta);
        this.getContentPane().add(jsp);
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    List<String> l = recorder.getPayments();
                    updateUI(l);
                    setTitle("Backoffice at " + new Date());
                    try {sleep(5000);} catch (InterruptedException r) {}
                
                }
            }
 
        };
        t.start();
    }
    
    public static void main(String[] args) {
        Main m = new Main();
        m.setBounds(20,20, 450, 300);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    void updateUI(List<String> l) {
        this.jta.setText("");
        for (String next : l) {
            jta.append(next + "\n");
        }
    }
    
}
