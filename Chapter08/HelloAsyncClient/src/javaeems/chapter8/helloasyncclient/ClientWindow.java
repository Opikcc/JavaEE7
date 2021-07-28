
package javaeems.chapter8.helloasyncclient;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.ws.rs.core.Response;

public class ClientWindow extends JFrame {
    JTextArea jta = new JTextArea();
    JTextField jtf = new JTextField(15);
    HelloClient helloClient = new HelloClient();
    SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm:ss a");
    
    public static void main(String[] args) {
        ClientWindow cw = new ClientWindow(); 
    }
    
    public ClientWindow() {
        this.setTitle(this.helloClient.getUriAsString());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(30,30,450,300);
        this.jtf.setText("dear reader");
        JButton sayHelloB = new JButton("Say Hello");
        sayHelloB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sayHello();
            }            
        });
        JButton clearB = new JButton("Clear");
        clearB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jta.setText("");
            }            
        });
        
        this.getContentPane().setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.add(this.jtf);
        inputPanel.add(sayHelloB);
        inputPanel.add(clearB);
        this.getContentPane().add(inputPanel, BorderLayout.NORTH);
        JScrollPane jsp = new JScrollPane(this.jta);
        this.getContentPane().add(jsp, BorderLayout.CENTER);        
        this.setVisible(true);
    }
    
    public void sayHello() {
        final Future<Response> futureResponse = this.helloClient.sayHelloFuture(this.jtf.getText());
        this.jta.append(dateFormatter.format(new Date()) + " " + "sent request." + "\n");
        Thread pollingThread = new Thread() {
            @Override
            public void run() {
                pollForResponse(futureResponse);
            }

        };
        pollingThread.start();
    }
    
   void pollForResponse(Future<Response> futureResponse) {
       while(!futureResponse.isDone()) {
            jta.append(dateFormatter.format(new Date()) + " " + "waiting..." + "\n");
            try {Thread.sleep(1000);} catch (InterruptedException ie) {}
        }
        String s = "";
        try {
            Response r = futureResponse.get();
            s = r.readEntity(String.class);
            jta.append(dateFormatter.format(new Date()) + " " + s + "\n");
        } catch (ExecutionException | InterruptedException e) {
            jta.append(e.getMessage() + "\n");
        }     
   }
    
}
