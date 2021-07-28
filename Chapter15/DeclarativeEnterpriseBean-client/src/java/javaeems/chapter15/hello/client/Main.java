package javaeems.chapter15.hello.client;

import javaeems.chapter15.hello.HelloBean;
import javax.ejb.EJB;
import javax.swing.*;
import java.awt.event.*;
import javax.ejb.EJBAccessException;

public class Main extends JFrame {

    @EJB
    static HelloBean helloBean;
    private static final long serialVersionUID = 1L;
    JTextField toYouTf = new JTextField(15);
    JTextField changeGreetingTf = new JTextField(15);
    JTextField resultTf = new JTextField(30);

    public static void main(String[] args) throws Exception {
        Main m = new Main();
    }

    public Main() {
        super.setTitle("May I Say Hello ?");
        this.setBounds(100, 100, 450, 200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton sayHelloB = new JButton("Just Say Hello");
        JButton sayHelloToYouB = new JButton("Say Hello to ");
        JButton changeGreetingB = new JButton("Change Greeting to");

        sayHelloB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sayHello();
            }
        });

        sayHelloToYouB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sayHelloTo();
            }
        });

        changeGreetingB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changeGreeting();
            }
        });

        toYouTf.setText("the great outdoors");
        changeGreetingTf.setText("Greetings to you,");

        JPanel p = new JPanel();
        JPanel pp = new JPanel();
        pp.add(sayHelloB);
        p.add(pp);
        pp = new JPanel();
        pp.add(sayHelloToYouB);
        pp.add(toYouTf);
        p.add(pp);
        pp = new JPanel();

        pp.add(changeGreetingB);
        pp.add(changeGreetingTf);
        p.add(pp);

        pp = new JPanel();
        pp.add(new JLabel("Result:"));
        pp.add(resultTf);
        p.add(pp);
        this.getContentPane().add(p);
        this.setVisible(true);

    }

    public void sayHello() {
        try {
            String result = helloBean.sayHello();
            this.resultTf.setText(result);
        } catch (EJBAccessException ejbae) {
            JOptionPane.showMessageDialog(this, "You do not have permission say hello !");
        }
    }

    public void sayHelloTo() {
        try {
            String result = helloBean.sayHelloTo(toYouTf.getText());
            this.resultTf.setText(result);
        } catch (EJBAccessException ejbae) {
            JOptionPane.showMessageDialog(this, "You do not have permission say hello to a specific name !");
        }
    }

    public void changeGreeting() {
        try {
            helloBean.setGreeting(changeGreetingTf.getText());
        } catch (EJBAccessException ejbae) {
            JOptionPane.showMessageDialog(this, "You do not have permission to change the greeting !");
        }
    }

    public static void doIt() {
        System.out.println(helloBean);
        System.out.println(helloBean.sayHelloTo("injection"));
    }

}
