package swing.table;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * JTable_Test.java
 * Created by liurenyong at 2013-7-26
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */

/**
 * 
 * @author liurenyong 2013-7-26
 */
public class JTable_Test extends JFrame {

    /**  */
    private static final long serialVersionUID = -4526797233422716729L;

    /**
     * 
     * @author liurenyong 2013-7-26
     */
    public JTable_Test() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        init();
        this.setVisible(true);
    }

    /**
     * 
     * @author liurenyong 2013-7-26
     */
    @SuppressWarnings("unused")
    private void init() {
        JSplitPane splitPane = new JSplitPane();
        JScrollPane leftScrollPane = new JScrollPane();
        JScrollPane rightScrollPane = new JScrollPane();
        JTree tree = new JTree();
        Object[][] data = new Object[10][15];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                data[i][j] = Math.random() * 1000;
            }
        }
        
        for(int k = 0; k < 15; k++) {
            
        }
        Object[] columns = new Object[] { "Column1", "Column2", "Column3", "Column4", "Column5", "Column6", "Column7", "Column8", "Column9",
                "Column10" };
        JTable table = new JTable();
        table.setModel(new DefaultTableModel(data, columns));
        rightScrollPane.setViewportView(table);
        leftScrollPane.setViewportView(new JTree());
//        getContentPane().add(new JScrollPane(table));
//        leftScrollPane.setVisible(false);
        splitPane.setDividerSize(5);
        splitPane.setLeftComponent(leftScrollPane);
        splitPane.setRightComponent(rightScrollPane);
        getContentPane().add(splitPane);
        
//        table.setAutoCreateRowSorter(true);
//        ((TableRowSorter)table.getRowSorter()).setRowFilter(new RowFilter() {
//
//            @Override
//            public boolean include(Entry entry) {
//                System.out.println(entry.getIdentifier() +"...." + entry.getValueCount() + "  ");
//                return false;
//            }
//            
//        });
//        System.err.println(table.getColumnName(column));
    }
    
    public static void main(String[] args) {
        new JTable_Test();
    }
}
