/**
 * TableCellRenderer_Test.java
 * Created by liurenyong at 2013-8-28
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 
 * @author liurenyong 2013-8-28
 */
public class TableCellRenderer_Test {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        
        JTable table = new JTable();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static class CustomCellRenderer extends DefaultTableCellRenderer {
        
        Timer time = null;
        
        /**
         * 
         * @author liurenyong 2013-8-28
         */
        public CustomCellRenderer() {
            time = new Timer(500, new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                }
            });
        }
        
        /* (non-Javadoc)
         * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

}
