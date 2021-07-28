package javaeems.chapter15.hello.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;

public class MyLoginWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private String name;
    private String password;

    boolean waiting = false;
    boolean canceled = false;
    JTextField usernameF = new JTextField(10);
    JTextField passwordF = new JPasswordField(10);

    JButton submitB = new JButton("Submit");
    JButton cancelB = new JButton("Cancel");

    public static MyLoginWindow gatherCredential(String title) {
        MyLoginWindow mlw = new MyLoginWindow();
        mlw.getCredential(title);
        return mlw;
    }

    private MyLoginWindow() {
    }

    public String getUsername() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    private void getCredential(String title) {
        this.waiting = true;
        this.setBounds(50, 50, 250, 150);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle(title);

        submitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                name = usernameF.getText();
                password = passwordF.getText();
                waiting = false;
                canceled = false;
                setVisible(false);
                passwordF.setText("");
            }

        });
        cancelB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                canceled = true;
                waiting = false;
                setVisible(false);
                System.exit(-1);
            }
        });

        JPanel p = null;
        p = new JPanel();

        p.add(new JLabel("Username:"));
        p.add(usernameF);
        this.getContentPane().add(p);

        p.add(new JLabel("Password:"));
        p.add(passwordF);
        this.getContentPane().add(p);

        p.add(submitB);
        p.add(cancelB);
        this.getContentPane().add(p);
        this.setVisible(true);
        while (waiting) {
            // wait
        }
    }
}
