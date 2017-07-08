/**
 * CombineFixTable.java
 * Created by liurenyong at 2013-8-15
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing.table.combinefix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * �̶���ϱ��.
 * 
 * @author liurenyong 2013-8-15
 */
public class CombineFixTable extends JPanel {

    /** ����ID */
    private static final long serialVersionUID = 6908398397873175782L;

    /** ������岼�� */
    private ScrollPaneLayout layout = null;

    /** ������� */
    private JScrollPane fixMobileScrollPane = null;

    /** �̶��ı�� */
    private JTable fixTable = null;

    /** �ƶ�ʽ�ı�� */
    private JTable mobileTable = null;

    /** �̶����ı�ͷ���� */
    private Vector<Object> fixColumns = null;

    /** �̶��������� ���� */
    private Vector<Vector<Object>> fixDatas = null;

    /** �ƶ����ı�ͷ���� */
    private Vector<Object> mobileColumns = null;

    /** �ƶ��������ݼ��� */
    private Vector<Vector<Object>> mobileDatas = null;

    /** �������������� */
    private TableRowSorter<TableModel> tableRowSorter = null;

    /** �����������Model */
    private DefaultTableModel rowSorterModel = null;

    /** �������������� */
    private Vector<Object> totalColumns = null;

    /** ���������������� */
    private Vector<Vector<Object>> totalDatas = null;

    /** �̶���Model */
    private TableColumnModel fixColumnModel = null;

    /** �ƶ���Model */
    private TableColumnModel mobileColumnModel = null;
    
    private JTable totalTable = null;

    /**
     * 
     * @author liurenyong 2013-8-15
     */
    public CombineFixTable(Vector<Object> fixColumns, Vector<Vector<Object>> fixDatas, Vector<Object> mobileColumns,
            Vector<Vector<Object>> mobileDatasDatas) {
        this.fixColumns = fixColumns;
        this.fixDatas = fixDatas;
        this.mobileColumns = mobileColumns;
        this.mobileDatas = mobileDatasDatas;
        init();
    }

    /**
     * ��ʼ�����.
     * 
     * @author liurenyong 2013-8-15
     */
    private void init() {
        initData();
        initComponent();
        initTableRowSorter();
        initListener();
        initScrollPane();
        fixColumn(5);
        add(fixMobileScrollPane, BorderLayout.CENTER);
    }

    /**
     * 
     * @author liurenyong 2013-8-15
     */
    private void initData() {
        totalColumns = new Vector<Object>();
        totalDatas = new Vector<Vector<Object>>();
        for (int i = 0; i < mobileDatas.size(); i++) {
            totalDatas.add(new Vector<Object>());
        }
        initTotalDatas();
    }

    /**
     * ��ʼ��������.
     * 
     * @author liurenyong 2013-8-15
     */
    private void initListener() {
        CombineFixTableListener fixListener = new CombineFixTableListener(mobileTable);
        CombineFixTableListener mobileListener = new CombineFixTableListener(fixTable);
        fixTable.getSelectionModel().addListSelectionListener(fixListener);
        mobileTable.getSelectionModel().addListSelectionListener(mobileListener);
    }

    /**
     * ��ʼ�����model�Լ�RowSorter.
     * 
     * @author liurenyong 2013-8-15
     */
    private void initTableRowSorter() {
        initRowSorter();
        initFixTable();
        initMobileTable();
    }

    /**
     * ���ù�������Corner.
     * 
     * @author liurenyong 2013-8-15
     */
    private void initScrollPane() {
        setScrollPaneLayout();
        fixMobileScrollPane.setViewportView(mobileTable);
        fixMobileScrollPane.setRowHeaderView(fixTable);
        fixMobileScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixTable.getTableHeader());
        fixMobileScrollPane.setBackground(Color.green);
    }

    /**
     * 
     * @author liurenyong 2013-8-15
     */
    private void initRowSorter() {
        tableRowSorter = new TableRowSorter<TableModel>();
        tableRowSorter.setModel(rowSorterModel);

        fixTable.setRowSorter(tableRowSorter);
        mobileTable.setRowSorter(tableRowSorter);
        
    }

    /**
     * ��ʼ��������ģ��.
     * 
     * @author liurenyong 2013-8-15
     */
    private void initTotalDatas() {
        rowSorterModel = new DefaultTableModel();
        totalColumns.addAll(fixColumns);
        totalColumns.addAll(mobileColumns);

        addDataToTotalDatas(fixDatas);
        addDataToTotalDatas(mobileDatas);

        rowSorterModel.setDataVector(totalDatas, totalColumns);
    }

    /**
     * ��������ӵ����е����ݼ�����.
     * 
     * @author liurenyong 2013-8-15
     */
    private void addDataToTotalDatas(Vector<Vector<Object>> datas) {
        for (int i = 0; i < datas.size(); i++) {
            Vector<Object> rowDatas = totalDatas.get(i);
            if (null == rowDatas) {
                rowDatas = new Vector<Object>();
            }
            totalDatas.get(i).addAll(datas.get(i));
        }
    }

    /**
     * ��ʼ���ƶ�������.
     * 
     * @author liurenyong 2013-8-15
     */
    private void initMobileTable() {
        DefaultTableModel mobileTableModel = new DefaultTableModel(totalDatas, totalColumns);
        mobileTable.setModel(mobileTableModel);
        mobileTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        mobileColumnModel = mobileTable.getColumnModel();
        setMobileTablePreference();
    }

    /**
     * �����ƶ�����ƫ���趨.
     * 
     * @author liurenyong 2013-8-15
     */
    private void setMobileTablePreference() {
        setHidenColumnPreferredSize();
        setVisibleColumnPreferredSize();
    }

    /**
     * ���ÿɼ��е�Ĭ�Ͽ��.
     * @author liurenyong 2013-8-15
     */
    private void setVisibleColumnPreferredSize() {
        TableColumn tableColumn = null;
        for (int i = fixColumns.size(); i < totalColumns.size(); i++) {
            tableColumn = mobileTable.getColumnModel().getColumn(i);
            tableColumn.setPreferredWidth(100);
        }
    }

    /**
     * �����ƶ���������е��п�.
     * 
     * @author liurenyong 2013-8-15
     */
    private void setHidenColumnPreferredSize() {
        for (int i = 0; i < fixColumns.size(); i++) {
            hidenMobileColumn(i);
        }
    }

    /**
     * 
     * @param i
     * @author liurenyong 2013-8-16
     */
    private void hidenMobileColumn(int i) {
        TableColumn tableColumn = mobileTable.getColumnModel().getColumn(i);
        tableColumn.setMaxWidth(0);
        tableColumn.setMinWidth(0);
        tableColumn.setResizable(false);
        tableColumn.setPreferredWidth(0);
    }

    /**
     * ���ù̶��е��п�.
     * 
     * @param column
     *            ָ������
     * @param width
     *            �п�
     * @author liurenyong 2013-8-15
     */
    public void setFixColumnWidthPreferredSize(int column, int width) {
        fixColumnModel.getColumn(column).setPreferredWidth(width);
        adjustScrollPane();
    }

    /**
     * ����ScrollPane�Ĵ�С.
     * @author liurenyong 2013-8-16
     */
    private void adjustScrollPane() {
        int width = 0;
        int height = 0;
        int rowMargin = fixTable.getRowMargin();
        int rowCount = fixTable.getRowCount();
        for(int i = 0; i < fixColumns.size(); i ++) {
            width += fixColumnModel.getColumn(i).getPreferredWidth();
            width += rowMargin;
        }
        int totalHeight = rowCount * height + rowCount * rowMargin;
        fixMobileScrollPane.setMinimumSize(new Dimension(width, totalHeight));
    }

    /**
     * ���ÿ��ƶ��е��п�.
     * 
     * @param column
     *            ָ������
     * @param width
     *            �п�
     * @author liurenyong 2013-8-15
     */
    public void setMobileColumnWidthPreferredSize(int column, int width) {
        column = mobileTable.convertColumnIndexToModel(column);
        mobileColumnModel.getColumn(column + fixColumns.size()).setPreferredWidth(width);
    }

    /**
     * ��ʼ���̶�������.
     * 
     * @author liurenyong 2013-8-15
     */
    private void initFixTable() {
        DefaultTableModel fixTableModel = new DefaultTableModel(fixDatas, fixColumns);
        fixTable.setModel(fixTableModel);
        
        fixColumnModel = fixTable.getColumnModel();
        setFixTablePreference();
        
        totalTable.setModel(new DefaultTableModel(totalDatas, totalColumns));
    }
    
    public void setFixTableBackground(Color color) {
        fixTable.setBackground(color);
    }
    
    public void setfixColumn() {
    	Vector<Vector> data = new Vector<Vector>();
    }
    
    /**
     * 
     * @param column
     * @param cellRenderer
     * @author liurenyong 2013-8-15
     */
    public void setFixTableCellRenderer(int column, TableCellRenderer cellRenderer) {
        fixColumnModel.getColumn(column).setCellRenderer(cellRenderer);
    }

    /**
     * ���ù̶�����ƫ���趨.
     * 
     * @author liurenyong 2013-8-15
     */
    private void setFixTablePreference() {
        fixTable.getTableHeader().setReorderingAllowed(false);
        fixTable.getTableHeader().setResizingAllowed(true);
        fixTable.setBackground(Color.lightGray);
        
        setFixTableColumnWidth();
    }

    /**
     * ���ù̶��е�Ĭ�Ͽ��.
     * @author liurenyong 2013-8-16
     */
    private void setFixTableColumnWidth() {
        for (int i = 0; i < fixColumns.size(); i++) {
            setFixColumnWidthPreferredSize(i, 100);
        }
    }

    /**
     * ��ʼ�����.
     * 
     * @author liurenyong 2013-8-15
     */
    private void initComponent() {
        fixMobileScrollPane = new JScrollPane();
        fixTable = new JTable(20, 5);
        mobileTable = new JTable(20, 10);
        totalTable = new JTable(20,15);
        fixTable.setName("FixTable");
        mobileTable.setName("MobileTable");
        setLayout(new BorderLayout());
    }

    /**
     * ���ù�����岼��.
     * 
     * @author liurenyong 2013-8-15
     */
    private void setScrollPaneLayout() {
        fixMobileScrollPane.setLayout(getDefaultLayout());
    }

    /**
     * �õ��������Ĭ�ϲ���.
     * 
     * @return
     * @author liurenyong 2013-8-15
     */
    private LayoutManager getDefaultLayout() {
        if (null == layout) {
            layout = createDefaultLayout();
        }
        return layout;
    }

    /**
     * ����Ĭ�ϲ���.
     * 
     * @return
     * @author liurenyong 2013-8-15
     */
    private ScrollPaneLayout createDefaultLayout() {
        if (null == layout) {
            layout = new CombineFixTableLayout();
        }
        return layout;
    }
    
    /**
     * �̶��ƶ����ֵ�ĳһ��.
     * @param column    ��
     * @author liurenyong 2013-8-16
     */
    public void fixColumn(int column) {
        removeMobileTableColumn(column);
        TableColumn tableColumn = mobileColumnModel.getColumn(column);
        mobileColumnModel.removeColumn(tableColumn);
        fixColumnModel.addColumn(tableColumn);
    }

    /**
     * �Ƴ�
     * @param column
     * @author liurenyong 2013-8-16
     */
    private void removeMobileTableColumn(int column) {
        hidenMobileColumn(column);
        
    }

    /**
     * 
     * @author liurenyong 2013-8-15
     */
    public CombineFixTable(Object[] fixColumns, Object[][] fixDatas, Object[] mobileColumns, Object[][] mobileDatasDatas) {
        this(convertToVector(fixColumns), convertToVector(fixDatas), convertToVector(mobileColumns), convertToVector(mobileDatasDatas));
    }

    /**
     * ����ѡ���¼�������.
     * 
     * @author liurenyong 2013-8-15
     */
    private class CombineFixTableListener implements ListSelectionListener {

        JTable otherTable = null;

        protected CombineFixTableListener(JTable otherTable) {
            this.otherTable = otherTable;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel model = (ListSelectionModel) e.getSource();
            otherTable.setSelectionModel(model);
        }

    }

    /**
     * Returns a vector that contains the same objects as the array.
     * 
     * @param anArray
     *            the array to be converted
     * @return the new vector; if <code>anArray</code> is <code>null</code>,
     *         returns <code>null</code>
     */
    protected static Vector<Object> convertToVector(Object[] anArray) {
        if (anArray == null) {
            return null;
        }
        Vector<Object> columnVector = new Vector<Object>(anArray.length);
        for (int i = 0; i < anArray.length; i++) {
            columnVector.addElement(anArray[i]);
        }
        return columnVector;
    }

    /**
     * Returns a vector of vectors that contains the same objects as the array.
     * 
     * @param anArray
     *            the double array to be converted
     * @return the new vector of vectors; if <code>anArray</code> is
     *         <code>null</code>, returns <code>null</code>
     */
    protected static Vector<Vector<Object>> convertToVector(Object[][] anArray) {
        if (anArray == null) {
            return null;
        }
        Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>(anArray.length);
        for (int i = 0; i < anArray.length; i++) {
            dataVector.addElement(convertToVector(anArray[i]));
        }
        return dataVector;
    }

}
