/**
 * JTable_RowHeader_Test.java
 * Created by liurenyong at 2013-8-13
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing.table;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;


/**
 * 
 * @author liurenyong 2013-8-13
 */
public class JTable_RowHeader_Test extends JFrame {

    /**  */
    private static final long serialVersionUID = 1682215484708936322L;

    private String[] fixColumns = null;

    private String[][] fixDatas = null;

    private String[] moveColumns = null;

    private String[][] moveDatas = null;

    JTable fixTable = null;

    JTable moveTable = null;

    Color fixColor = new Color(189, 240, 240);

    /**
     * 
     * @author liurenyong 2013-8-13
     */
    public JTable_RowHeader_Test() {
        init();
    }

    /**
     * 
     * @author liurenyong 2013-8-13
     */
    private void init() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initData();
        initComponent();
        this.setVisible(true);
    }

    /**
     * 
     * @author liurenyong 2013-8-13
     */
    private void initData() {
        int fixColumn = 5;
        int moveColumn = 10;
        int fixRow = 20;
        int moveRow = 20;
        fixColumns = new String[fixColumn];
        moveColumns = new String[moveColumn];
        fixDatas = new String[fixRow][fixColumn];
        moveDatas = new String[moveRow][moveColumn];

        setTableColumn(fixColumns, "Fix Column ", fixColumn);
        setTableColumn(moveColumns, "Move Column ", moveColumn);

        setTableData(fixDatas, "Fix", fixRow, fixColumn);
        setTableData(moveDatas, "Move", moveRow, moveColumn);

    }

    /**
     * 
     * @author liurenyong 2013-8-13
     */
    private void setMoveTableRenderer(JTable moveTable) {
        moveTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int columnCount = moveTable.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            moveTable.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
    }

    private void setTableColumn(String[] columns, String columnName, int column) {
        for (int i = 0; i < column; i++) {
            columns[i] = columnName + (i + 1);
        }
    }

    private void setTableData(String[][] datas, String columnName, int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                datas[i][j] = columnName + "_data_" + Math.random() * 100;
            }
        }
    }

    private void setSelectSameRow() {
        CustomListSelectionListener fixTableListener = new CustomListSelectionListener(moveTable);
        CustomListSelectionListener moveTablelistener =  new CustomListSelectionListener(fixTable);
        fixTable.getTableHeader().setReorderingAllowed(false);
        moveTable.getTableHeader().setReorderingAllowed(false);
        fixTable.setBackground(new Color(189, 240, 240));
        fixTable.getTableHeader().setBackground(fixColor);
        fixTable.setAutoCreateRowSorter(true);
        moveTable.setAutoCreateRowSorter(true);
        fixTable.getSelectionModel().addListSelectionListener(fixTableListener);
        moveTable.getSelectionModel().addListSelectionListener(moveTablelistener);
        fixTable.getRowSorter().addRowSorterListener(fixTableListener);
        moveTable.getRowSorter().addRowSorterListener(moveTablelistener);
    }

    private class CustomListSelectionListener implements ListSelectionListener, RowSorterListener {
        JTable otherTable = null;

        public CustomListSelectionListener(JTable table) {
            this.otherTable = table;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel model = (ListSelectionModel) e.getSource();
            otherTable.setSelectionModel(model);
        }

        @Override
        public void sorterChanged(RowSorterEvent e) {
        }
    }

    /**
     * 
     * @author liurenyong 2013-8-13
     */
    private void initComponent() {
        final JScrollPane tableScrollPane = new JScrollPane();
        tableScrollPane.setLayout(new CustomScrollPaneLayout());
        // tableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        fixTable = new JTable(fixDatas, fixColumns);
        moveTable = new JTable(moveDatas, moveColumns);
        tableScrollPane.setRowHeaderView(fixTable);
        tableScrollPane.setViewportView(moveTable);
        setMoveTableRenderer(moveTable);
        tableScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixTable.getTableHeader());
        
//        final JLayeredPane layeredPane = new JLayeredPane();
//        layeredPane.setOpaque(true);
//        layeredPane.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
//        layeredPane.setLayout(new BorderLayout());
////        layeredPane.setLayout(null);
//        
//        Object[] columns = new Object[fixColumns.length + moveColumns.length];
//        System.arraycopy(fixColumns, 0, columns, 0, fixColumns.length);
//        System.arraycopy(moveColumns, 0, columns, fixColumns.length, moveColumns.length);
//        
//        Object[][] datas = new Object[30][];
//        for(int i = 0; i< 30; i++) {
//            datas[i] = new Object[columns.length];
//            for(int j = 0; j < columns.length; j++) {
//                datas[i][j] = "test" + Math.random() * 100; 
//            }
//        }
//        JTable totalTable = new JTable(datas, columns);
//        totalTable.setBackground(Color.GREEN);
//        final JScrollPane ttPane = new JScrollPane(totalTable);
//        layeredPane.add(ttPane , 1);
//        layeredPane.add(tableScrollPane, 0);
//        final JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//        panel.add(layeredPane);
        getContentPane().add(tableScrollPane);
        setSelectSameRow();
    }

    public static void main(String[] args) {
        new JTable_RowHeader_Test();
    }
}

