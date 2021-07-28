package javaeems.chapter5.libraryclient;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;


public class AddBookDialog extends JDialog {
    JTextField titleField = new JTextField();
    JTextField authorField = new JTextField();
    JComboBox genresChooser = new JComboBox();
    boolean isOK = false;
    
    AddBookDialog(List<String> genres, JFrame parent) {
        super(parent, "Add new book", Dialog.ModalityType.APPLICATION_MODAL);
        JPanel jp = new JPanel();
        jp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (String next : genres ) {
            this.genresChooser.addItem(next);
        }
        this.setBounds(40, 40, 250, 180);
        
        
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        jp.add(new JLabel("Title:"));
        jp.add(titleField);
        jp.add(new JLabel("Author:"));
        jp.add(authorField);
        
        jp.add(genresChooser);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        jp.add(buttonPanel);
        final JDialog myWindow = this;
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if ("".equals(titleField.getText())) {
                    JOptionPane.showMessageDialog(myWindow, "Please provide a valid author and title !");
                    
                } else {
                    isOK = true;
                    setVisible(false);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                isOK = false;
                setVisible(false);
            }
        });
        this.getContentPane().add(jp);
        
    }
}

