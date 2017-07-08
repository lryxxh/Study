/**
 * GifImage_TEst.java
 * Created by ¡ı» ”¬ at 2013-7-2
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author ¡ı» ”¬ 2013-7-2
 */
@SuppressWarnings("unused")
public class GifImage_TEst {
    
    public static void main(String[] args) {
      
        final Image image = Toolkit.getDefaultToolkit().getImage("D:/Work/eclipse/D5000_WorkSpace2/Test2/src/image/01_µ»¥˝.gif");
        final JFrame frame = new JFrame(){
            /* (non-Javadoc)
             * @see java.awt.Window#paint(java.awt.Graphics)
             */
            @Override
            public void paint(Graphics g) {
                System.out.println(".............");
                super.paint(g);
            }
        };
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final TextDraw text = new TextDraw();
        final ImagePanel panel = new ImagePanel();
//        panel.setBorder(BorderFactory.createMatteBorder(50, 50, 50, 50, new ImageIcon(image)));
//        JPanel panel3 = new JPanel(){
//            /**  */
//            private static final long serialVersionUID = 4935285290248401440L;
//
//            @Override
//            public void paint(Graphics g) {
//                super.paint(g);
//                text.paint(g);
//            }
//        };
//        panel3.setLayout(null);
//        panel3.add(panel);
//        panel.updateUI();
        JPanel panel2 = new JPanel(){
            /* (non-Javadoc)
             * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
             */
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(image));
        panel2.add(label);
        panel2.setBounds(100,100,400,400);
        frame.getContentPane().add(panel2, BorderLayout.CENTER);
        JButton button = new JButton("---------------");
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.repaint();
            }
        });
        frame.getContentPane().add(button, BorderLayout.SOUTH);
        frame.setVisible(true);
        
    }

}

class TextDraw {
    public void paint(Graphics g) {
        g.drawString("HelloWorld",50,50);
        System.out.println("................");
    }
}

class ImagePanel extends JPanel {
    /**  */
    private static final long serialVersionUID = 8830304207968375879L;
    final Image image = Toolkit.getDefaultToolkit().getImage("D:/Work/eclipse/D5000_WorkSpace2/Test2/src/image/01_µ»¥˝.gif");

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("------------");
        g.drawImage(image, 220, 220, null);
    }
}
