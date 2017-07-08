package swing;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * JProcessBar_Tets.java
 * Created by liurenyong at 2013-7-29
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */

/**
 * 
 * @author liurenyong 2013-7-29
 */
public class JProcessBar_Tets {
    
    public static void main(String[] args) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        RunThread run = new RunThread(thread);
        run.start();
    }

}
class RunThread extends Thread {
    
    Thread thread = null;
    public RunThread(Thread thread) {
        this.thread = thread;
    }
    
    @Override
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                ShowDialog dialog = new ShowDialog();
                ThreadTest test = new ThreadTest(dialog, thread);
                test.start();
            }
        });
    }
}

class ThreadTest extends Thread{
    ShowDialog dialog = null;
    Thread thread = null;
    public ThreadTest(ShowDialog dialog, Thread thread) {
        this.dialog = dialog;
        this.thread = thread;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        dialog.setVisible(true);
        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(".........");
        dialog.dispose();
    }
}

class ShowDialog extends JDialog {
    
    /**  */
    private static final long serialVersionUID = -757789995889778682L;

    ShowDialog() {
        JLabel lable = new JLabel("is working...");
        lable.setIcon(new ImageIcon(getClass().getResource("/image/working.gif")));
        add(lable);
        setSize(300,400);
    }
}
