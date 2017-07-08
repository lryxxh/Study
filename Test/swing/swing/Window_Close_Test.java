/**
 * Window_Close_Test.java
 * Created by liurenyong at 2013-9-11
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * @author liurenyong 2013-9-11
 */
public class Window_Close_Test extends JFrame{

    /**
     * 
     * @author liurenyong 2013-9-11
     */
    public Window_Close_Test() {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            /* (non-Javadoc)
             * @see java.lang.Thread#run()
             */
            @Override
            public void run() {
                System.out.println(Window_Close_Test.this.hashCode());
                System.out.println("shutdown");
            }
        });
        setSize(800, 600);
        JButton button = new JButton("≤‚ ‘");
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        getContentPane().add(button);
        setVisible(true);
//        this.addComponentListener(new ComponentListener() {
//            
//            @Override
//            public void componentShown(ComponentEvent e) {
//                   System.out.println("componentShown...............");
//            }
//            
//            @Override
//            public void componentResized(ComponentEvent e) {
//                
//            }
//            
//            @Override
//            public void componentMoved(ComponentEvent e) {
//                
//            }
//            
//            @Override
//            public void componentHidden(ComponentEvent e) {
//                System.out.println("componentHidden...............");
//            }
//        });
//        
//        this.addWindowStateListener(new WindowStateListener() {
//            
//            @Override
//            public void windowStateChanged(WindowEvent e) {
//                System.out.println("windowStateChanged...............");
//            }
//        });
//        
//        this.addWindowListener(new WindowListener() {
//            
//            @Override
//            public void windowOpened(WindowEvent e) {
//                System.out.println("windowOpened...............");
//                
//            }
//            
//            @Override
//            public void windowIconified(WindowEvent e) {
//                System.out.println("windowIconified...............");
//                
//            }
//            
//            @Override
//            public void windowDeiconified(WindowEvent e) {
//                System.out.println("windowDeiconified...............");
//                
//            }
//            
//            @Override
//            public void windowDeactivated(WindowEvent e) {
//                System.out.println("windowDeactivated..............." + Window_Close_Test.this.isDisplayable());
//            }
//            
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.out.println("windowClosing...............");
//                
//            }
//            
//            @Override
//            public void windowClosed(WindowEvent e) {
//                System.out.println("windowClosed...............");  
//            }
//            
//            @Override
//            public void windowActivated(WindowEvent e) {
//                System.out.println("windowActivated...............");  
//                
//            }
//        });
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JFrame#processWindowEvent(java.awt.event.WindowEvent)
     */
    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        System.out.println(".....................processWindowEvent" + e);
    }
    
    /* (non-Javadoc)
     * @see java.awt.Window#processWindowStateEvent(java.awt.event.WindowEvent)
     */
    @Override
    protected void processWindowStateEvent(WindowEvent e) {
        super.processWindowStateEvent(e);
        System.out.println(".....................processWindowStateEvent");
    }
    
    public static void main(String[] args) {
        new Window_Close_Test();
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("lllll");
            }
        };
        thread.setDaemon(true);
        thread.start();
    }
}
