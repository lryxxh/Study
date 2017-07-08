/**
 * JTableSorter_Test.java
 * Created by liurenyong at 2013-8-14
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing.table;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import swing.table.combinefix.CombineFixTableLayout;

/**
 * 
 * @author liurenyong 2013-8-14
 */
public class JTableSorter_Test extends JFrame {

    /**  */
    private static final long serialVersionUID = 3945571109105196987L;

    private String[] fixColumns = null;

    private String[][] fixDatas = null;

    private String[] moveColumns = null;

    private String[][] moveDatas = null;

    JTable fixTable = null;

    JTable moveTable = null;

    TableRowSorter rowSorter = null;

    /**
     * 
     * @author liurenyong 2013-8-14
     */
    public JTableSorter_Test() {
        init();
    }

    /**
     * 
     * @author liurenyong 2013-8-14
     */
    private void init() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initData();
        this.initComponent();
        this.setVisible(true);
    }

    /**
     * 
     * @author liurenyong 2013-8-14
     */
    private void initComponent() {
        final JScrollPane tableScrollPane = new JScrollPane();
//        tableScrollPane.setLayout(new CustomScrollPaneLayout());
        tableScrollPane.setLayout(new CombineFixTableLayout());
      
        Object[] totalColumns = new Object[fixColumns.length + moveColumns.length];
        Object[][] totalDatas = new Object[fixDatas.length][totalColumns.length];
        System.arraycopy(fixColumns, 0, totalColumns, 0, fixColumns.length);
        System.arraycopy(moveColumns, 0, totalColumns, fixColumns.length, moveColumns.length);

        for (int i = 0; i < fixDatas.length; i++) {
            totalDatas[i] = new Object[totalColumns.length];
            for (int j = 0; j < fixColumns.length; j++) {
                totalDatas[i][j] = fixDatas[i][j];
            }
        }

        for (int i = 0; i < moveDatas.length; i++) {
            for (int j = 0; j < moveColumns.length; j++) {
                totalDatas[i][fixColumns.length + j] = moveDatas[i][j];
            }
        }
        fixTable = new JTable(fixDatas, fixColumns);
        moveTable = new JTable(totalDatas, totalColumns);
        CustomModel model = new CustomModel(totalDatas, totalColumns);
        final TableRowSorter rowSorter = new TableRowSorter(model);
        fixTable.setRowSorter(rowSorter);
        moveTable.setRowSorter(rowSorter);
//        MoveModel moveModel = new MoveModel(model);
//        FixModel fixModel = new FixModel(model);
//
//        fixTable.setModel(fixModel);
//        moveTable.setModel(moveModel);
        tableScrollPane.setRowHeaderView(fixTable);
        tableScrollPane.setViewportView(moveTable);
        setMoveTableRenderer(moveTable);
        
        fixTable.getTableHeader().setVisible(true);
        moveTable.getTableHeader().setVisible(true);
        tableScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixTable.getTableHeader());
        getContentPane().add(tableScrollPane);
        setSelectSameRow();
        fixTable.setName("FixTable");
        moveTable.setName("MoveTable");
        
//        moveTable.getRowSorter().addRowSorterListener(fixTable);
//        fixTable.getRowSorter().addRowSorterListener(moveTable);
//        final JTableHeader fixHeader = fixTable.getTableHeader();
//        final JTableHeader moveHeader = moveTable.getTableHeader();
        
//        MouseListener[] fixMouseListeners = fixHeader.getMouseListeners();
//        MouseListener[] moveMouseListeners = moveHeader.getMouseListeners();
//
//        for(MouseListener listener : fixMouseListeners) {
//            fixHeader.removeMouseListener(listener);
//        }
//        
//        for(MouseListener listener : moveMouseListeners) {
//            moveHeader.removeMouseListener(listener);
//        }
//        
//        fixHeader.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//               int column = fixHeader.columnAtPoint(e.getPoint());
//               sorter.toggleSortOrder(column);
//            }
//        });
//        
//        moveHeader.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int column = moveHeader.columnAtPoint(e.getPoint());
//                sorter.toggleSortOrder(column + 5);
//            }
//        });
        
        moveTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = moveTable.columnAtPoint(e.getPoint());
            }
        });
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
        for(int i = 0; i < fixColumns.length; i++) {
            moveTable.getColumnModel().getColumn(i).setMaxWidth(0);
            moveTable.getColumnModel().getColumn(i).setMinWidth(0);
            moveTable.getColumnModel().getColumn(i).setPreferredWidth(0); 
        }
        for (int i = fixColumns.length; i < columnCount; i++) {
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
        CustomListSelectionListener moveTablelistener = new CustomListSelectionListener(fixTable);
        fixTable.getTableHeader().setReorderingAllowed(false);
        moveTable.getTableHeader().setReorderingAllowed(false);
        // fixTable.setAutoCreateRowSorter(true);
        // moveTable.setAutoCreateRowSorter(true);
        fixTable.setBackground(new Color(189, 240, 240));
        fixTable.getSelectionModel().addListSelectionListener(fixTableListener);
        moveTable.getSelectionModel().addListSelectionListener(moveTablelistener);
    }

    private class CustomListSelectionListener implements ListSelectionListener {
        JTable otherTable = null;

        public CustomListSelectionListener(JTable table) {
            this.otherTable = table;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel model = (ListSelectionModel) e.getSource();
            otherTable.setSelectionModel(model);
        }
    }
    
    public static void main(String[] args) {
        new JTableSorter_Test();
    }

    class FixModel extends AbstractTableModel {
        CustomModel model = null;
        int offset = 5;

        public FixModel(CustomModel model) {
            this.model = model;
        }

        @Override
        public String getColumnName(int column) {
            return model.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            return model.getRowCount();
        }

        @Override
        public int getColumnCount() {
            return offset;
        }

        @Override
        public Object getValueAt(int row, int column) {
            return model.getValueAt(row, column);
        }
    }

    class MoveModel extends AbstractTableModel {
        CustomModel model = null;
        int offset = 5;

        public MoveModel(CustomModel model) {
            this.model = model;
        }

        @Override
        public String getColumnName(int column) {
            return model.getColumnName(column + offset);
        }

        @Override
        public int getRowCount() {
            return model.getRowCount();
        }

        @Override
        public int getColumnCount() {
            return model.getColumnCount() - offset;
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.table.DefaultTableModel#getValueAt(int, int)
         */
        @Override
        public Object getValueAt(int row, int column) {
            return model.getValueAt(row, column + offset);
        }
    }

    class CustomModel extends AbstractTableModel {
        Object[] columns = null;
        Object[][] datas = null;

        /**
         * 
         * @author liurenyong 2013-8-14
         */
        public CustomModel(Object[][] datas, Object[] columns) {
            this.datas = datas;
            this.columns = columns;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column].toString();
        }

        @Override
        public int getRowCount() {
            return datas.length;
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return datas[rowIndex][columnIndex];
        }

    }

}
