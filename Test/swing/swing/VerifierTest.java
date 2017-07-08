/**
 * InputVer.java
 * Created by liurenyong at 2013-8-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * 
 * @author liurenyong 2013-8-19
 */
public class VerifierTest extends JFrame {
    public VerifierTest() {
        JTextField tf1 = new JTextField("Type \"pass\" here");
        getContentPane().add(tf1, BorderLayout.NORTH);
        tf1.setInputVerifier(new PassVerifier());

        JTextField tf2 = new JTextField("TextField2");
        getContentPane().add(tf2, BorderLayout.SOUTH);

        JButton button = new JButton("dssd");
        getContentPane().add(button);
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
    }

    class PassVerifier extends InputVerifier {
        public boolean verify(JComponent input) {
            JTextField tf = (JTextField) input;
            boolean flag =  "pass".equals(tf.getText());
            if (!flag) {
                tf.requestFocus();
            }
            return flag;
        }
    }

    public static void main(String[] args) {
        Frame f = new VerifierTest();
        f.pack();
        f.setVisible(true);
    }
}
