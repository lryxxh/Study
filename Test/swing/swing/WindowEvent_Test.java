package swing;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * WindowEvent_Test.java
 * Created by liurenyong at 2013-7-15
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */

/**
 * 
 * @author liurenyong 2013-7-15
 */
public class WindowEvent_Test extends JFrame{

    /**  */
    private static final long serialVersionUID = 452526051503311099L;

    public WindowEvent_Test() {
        this.setSize(800,600);
//        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("------------");
            }
        });
        this.setVisible(true);
    }
    
    public static void A() {
        B();
        B();
        B();
        B();
        B();
        B();
        B();
        
    }
    
    public static void B() {
        B();
    }
    
    public static void C() {
        B();
    }
    
    public static void main(String[] args) {
        new WindowEvent_Test();
    }
}
