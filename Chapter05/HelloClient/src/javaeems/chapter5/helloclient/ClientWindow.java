
package javaeems.chapter5.helloclient;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientWindow extends JFrame {
    JTextArea jta = new JTextArea();
    JTextField jtf = new JTextField(15);
    HelloClient helloClient = new HelloClient();
    
    public static void main(String[] args) {
        ClientWindow cw = new ClientWindow(); 
    }
    
    public ClientWindow() {
        this.setTitle(this.helloClient.getUriAsString());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(30,30,400,300);
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
        String s = this.helloClient.sayHello(this.jtf.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
        this.jta.append(sdf.format(new Date()) + " " + s + "\n");
    }
    
    
    
}
