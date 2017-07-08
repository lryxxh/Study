/**
 * JTextPane_Color_Test.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * 
 * @author liurenyong 2013-12-24
 */
public class JTextPane_Color_Test extends JFrame {

    private volatile int count = 0;

    /**
     * 
     * @author liurenyong 2013-12-24
     */
    public JTextPane_Color_Test() {
        Init();
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    /** 
     * 
     */
    private void Init() {
        final JScrollPane scrollPane = new JScrollPane();
        final JTextArea textPane = new JTextArea(new DefaultStyledDocument());
        JButton addButton = new JButton("所得税的爽肤水");
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        for (int i = 0; i < 10000; i++) {
//                            try {
//                                Thread.sleep(0,10);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            SwingUtilities.invokeLater(new Runnable() {
//                                public void run() {
                                    final SimpleAttributeSet aSet = new SimpleAttributeSet();
                                    count++;
                                    Color color = Color.BLACK;
                                    if (count % 2 == 0) {
                                        color = Color.BLUE;
                                    } else if (count % 3 == 0) {
                                        color = Color.RED;
                                    }
                                    StyleConstants.setForeground(aSet, color);
                               
                                    final int start = textPane.getDocument().getLength();
                                    final String str = "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" + count + "\n";
                                    // textPane.replaceSelection("sssssssssssssssss"
                                    // + count + "\n");
                                    // SwingUtilities.invokeLater(new
                                    // Runnable() {
                                    // public void run() {

                                    try {
                                        textPane.append(str);
                                        System.out.println("--------------------------" + count);
                                    } catch (Exception e1) {
                                        e1.printStackTrace();
                                    }
                                    ((DefaultStyledDocument)textPane.getDocument()).setCharacterAttributes(start, str.length(),aSet, true);
                                    // }
                                    // });
                                    // repaint();
                                    // System.out.println(scrollPane.getVerticalScrollBar().getMaximum());
//                                    try {
//                                        SwingUtilities.invokeAndWait(new Runnable() {
//                                            
//                                            @Override
//                                            public void run() {
//                                                scrollPane.getVerticalScrollBar().updateUI();                                            
//                                            }
//                                        });
//                                    } catch (InterruptedException e) {
//                                        // TODO Auto-generated catch block
//                                        e.printStackTrace();
//                                    } catch (InvocationTargetException e) {
//                                        // TODO Auto-generated catch block
//                                        e.printStackTrace();
//                                    }
//                                     scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
                                    // scrollPane.getVerticalScrollBar().repaint();
//                                }
//                            });
                            
                        }

                    };
                }.start();

            }
        });

        JButton showButton = new JButton("所得税的爽肤水");

        showButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Element root = textPane.getDocument().getDefaultRootElement();
                for (int i = 0; i < root.getElementCount(); i++) {
                    System.out.println(root.getElement(i).getName());
                }
            }
        });
        scrollPane.setViewportView(textPane);
        getContentPane().add(scrollPane);
        getContentPane().add(addButton, BorderLayout.SOUTH);
        getContentPane().add(showButton, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new JTextPane_Color_Test();
    }

}
