/**
 * BUtton_Test.java
 * Created by liurenyong at 2013-8-22
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * @author liurenyong 2013-8-22
 */
public class Button_Test extends JFrame{

    Button_Test() {
      
        final JButton butt = new JButton();
        butt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		super.mouseClicked(e);
        		System.out.println("------ " );
        	}
		});
//        Action action = new AbstractAction("TTTT"){
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//              
//                SwingUtilities.invokeLater(new Runnable() {
//                    
//                    @Override
//                    public void run() {
//                        boolean hiden = butt.getHideActionText();
//                        System.out.println("dddd" + hiden);
//                        butt.setHideActionText(!hiden);
//                    }
//                });
//            }
//            
//        };
//        butt.setAction(action);
        getContentPane().add(butt);
        setSize(400,300);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Button_Test();
    }
}
