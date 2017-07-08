/**
 * JOptionPane_Dialog_Test.java
 * Created by liurenyong at 2013-10-28
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * 
 * @author liurenyong 2013-10-28
 */
public class JOptionPane_Dialog_Test {
    
    public static void main(String[] args) {
//        JDialog dialog = new JOptionPane().createDialog(null, "title");
//        dialog.setAlwaysOnTop(true);
//        dialog.setVisible(true);
        JOptionPane option = new JOptionPane("≤‚ ‘", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
        JDialog dialog = new JDialog((JDialog)null, "title");
        dialog.setAlwaysOnTop(true);
        dialog.getContentPane().add(option);
        dialog.pack();
        dialog.setVisible(true);
    }
    
}
