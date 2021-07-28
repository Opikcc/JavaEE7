package javaeems.chapter10.primes.client;

import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.Future;
import javax.ejb.EJB;
import javaeems.chapter10.primes.PrimeCalculatorRemote;


public class PrimesWindow extends JFrame {
    @EJB
    static PrimeCalculatorRemote calculatorBean;
    JComboBox jcb = new JComboBox();
    JComboBox timeoutCb = new JComboBox();
    JProgressBar jpb = new JProgressBar();
    JButton calculateButton = new JButton("Calculate");
    
    public PrimesWindow() {
        this.setTitle("Find the largest prime under a limit");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jcb.addItem(10000);
        jcb.addItem(50000);
        jcb.addItem(100000);
        jcb.addItem(200000);
        timeoutCb.addItem(1);
        timeoutCb.addItem(2);
        timeoutCb.addItem(3);
        timeoutCb.addItem(4);
        timeoutCb.addItem(5);
        timeoutCb.addItem(10);
        timeoutCb.addItem(20);
        this.jpb.setMaximum(25);
        this.jpb.setStringPainted(true);
        
        JPanel p = new JPanel();
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));
        p.add(new JLabel("Upper bound:"));
        p.add(jcb);
        mPanel.add(p);
        
        p = new JPanel();
        p.add(new JLabel("Timeout (s):"));
        p.add(timeoutCb);
        mPanel.add(p);
        
        p = new JPanel();
        p.add(calculateButton);        
        mPanel.add(p);
        mPanel.add(jpb);
        
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                doCalculate();
            }
        });
        
        jcb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                choiceChanged();
            }
        });

        this.getContentPane().add(mPanel);        
    }
    
    public void doCalculate() {
        jpb.setString("");
        final int max = (int) jcb.getSelectedItem();
        Thread t = new Thread() {
            public void run() {
                setTitle("Calculation has started");
                Future<Long> calculationFuture = calculatorBean.calculateMaxPrimeBelow((long) max);
                int loopCountMax = 25;
                int pause = (((int) timeoutCb.getSelectedItem())*1000) / 25;
                int loopCount = 0;
                while (!calculationFuture.isDone()) {
                    jpb.setValue(loopCount);
                    try {sleep(pause);} catch (InterruptedException e) {}
                    loopCount++;
                    if (loopCount > loopCountMax) {
                        calculationFuture.cancel(true);
                        setTitle("Cancelled: it's taking too long !");
                        jpb.setString("Timed out");
                        setProgressMin();
                        return;
                    }
                }
                Long prime = (long) 0;
                try {prime = calculationFuture.get();} catch (Exception e) {}
                jpb.setString("Completed under " + ((1000 * loopCount * ((int) timeoutCb.getSelectedItem())) / 25) + "ms");
                setTitle("Complete, largest prime is: " + prime); 
            }
            
            
            
            
            
        };
        t.start();
    }
    
    public void setProgressMin() {
        
        this.jpb.setValue(0);
    }
    
    public void setProgressMax() {
        this.jpb.setValue(25);
    }
    
    public void choiceChanged() {
        
    }
  
    
    
    public static void main(String[] args) {
        PrimesWindow pw = new PrimesWindow();
        pw.setBounds(40, 40, 350, 180);
        pw.setVisible(true);
    }
}
