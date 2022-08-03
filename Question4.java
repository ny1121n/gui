import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

 
public class Question4 {
    public static void main(String[] argv){
        ShiftCipher myApp = new ShiftCipher();
        myApp.setVisible(true);
    }
}

class ShiftCipher extends JFrame{
    private JTextField shift;
    private JTextField message;
    private JTextField encrypted;
    private JTextField decrypted;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;

    private JButton set;
    private JButton encrypt;
    private JButton reset;
    private JButton decrypt;

    public ShiftCipher() {
        createUI();
    }

    public void createUI() {
        this.setTitle("Shift Ciper"); //title
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shift = new JTextField();
        message = new JTextField();
        encrypted = new JTextField();
        encrypted.setEditable(false);
        decrypted = new JTextField();
        decrypted.setEditable(false);

        label1 = new JLabel("Shift");
        label2 = new JLabel("Messagge");
        label3 = new JLabel("Encrypted");
        label4 = new JLabel("Decrypted");
        
        set = new JButton("Set");
        encrypt = new JButton("Ecrypt");
        decrypt = new JButton("Decrypt");
        reset = new JButton("Reset");

        this.setLayout(new GridLayout(0, 3));
        this.add(label1);
        this.add(shift);
        this.add(set);
        this.add(label2);
        this.add(message);
        this.add(encrypt);
        this.add(label3);
        this.add(encrypted);
        this.add(decrypt);
        this.add(label4);
        this.add(decrypted);
        this.add(reset);

        this.pack();
        addHandlers();
    }

    private void addHandlers() {
        ActionListener enc = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                encrypting();
            }
        };
        ActionListener dec = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decrypting();
            }
        };
        ActionListener setter = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setting();
            }
        };
        ActionListener resetter = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetting();
            }
        };
        encrypt.addActionListener(enc);
        decrypt.addActionListener(dec);
        set.addActionListener(setter);
        reset.addActionListener(resetter);
    }

    public void encrypting() {
        StringBuilder encryption = new StringBuilder();
        char[] chArray = new char[message.getText().length()];
        for (int i = 0; i < chArray.length; i++) {
            chArray[i] = message.getText().charAt(i);
        }
        for (char c : chArray) {
            if (c != ' ') {
                int pos = c - 'a';
                int newPos = (pos + Integer.parseInt(shift.getText())) % 26;
                char char2 = (char) ('a' + newPos);
                encryption.append(char2);
            } 
            else {
                encryption.append(c);
            }
        }
        encrypted.setText(encryption.toString());
    }

    public void decrypting() {
        StringBuilder decyprtion= new StringBuilder();
        char[] chArray = new char[message.getText().length()];
        for (int i = 0; i < chArray.length; i++) {
            chArray[i] = message.getText().charAt(i);
        }
        for (char c : chArray) {
            if (c != ' ') {
                int pos = c - 'a';
                int newPos = (pos + (26 - (Integer.parseInt(shift.getText())%26))) % 26;
                char char2 = (char)('a' + newPos);
                decyprtion.append(char2);
            } 
            else {
                decyprtion.append(c);
            }
        }
        decrypted.setText(decyprtion.toString());
    }

    public void setting() {
        if (!(Integer.parseInt(shift.getText()) <= 25 && Integer.parseInt(shift.getText()) >= 1)) {
            popUp("Shift value must be 1  to 25", "error");
        }
    }

    public void resetting() {
        shift.setText("");
        message.setText("");
        encrypted.setText("");
        decrypted.setText("");
    }

    public void popUp(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
