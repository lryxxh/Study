/**
 * JSPinner_Test.java
 * Created by liurenyong at 2013-10-28
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * 
 * @author liurenyong 2013-10-28
 */
public class JSPinner_Test {

    /** 
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800,600);
        JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(0,0,10,1));
//        ((DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
        frame.getContentPane().add(spinner);
        frame.setVisible(true);
    }

}
