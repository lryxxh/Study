/**
 * Focus_Test.java
 * Created by liurenyong at 2013-11-13
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * 
 * @author liurenyong 2013-11-13
 */
public class Focus_Test {

    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane();
        final JTable table = new JTable(10, 4);
        table.addFocusListener(new FocusListener() {
            
            @Override
            public void focusLost(FocusEvent e) {
                System.err.println("focusLost"  + e.getOppositeComponent());                
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                System.err.println("focusGained" + e.getOppositeComponent());
            }
        });
        table.setInputVerifier(new InputVerifier() {
            
            @Override
            public boolean verify(JComponent input) {
                System.out.println("..............oooooooooooooo");
                return false;
            }
        });
        scrollPane.setViewportView(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        JPanel panel = new JPanel();
       final JButton button = new JButton("Ωπµ„≤‚ ‘");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showOptionDialog(null, "ddd", "dd", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
//                button.setFocusable(false);
                System.out.println("----------------------");
                if (option == JOptionPane.OK_OPTION){
                    table.requestFocus();
//                    button.setFocusable(true);
                }
            }
        });
        panel.add(new JTextField(20));
        panel.add(button);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.getContentPane().add(new JTextField(), BorderLayout.NORTH);
        frame.setVisible(true);
    }

}
