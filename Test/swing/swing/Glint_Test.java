/**
 * Glint_Test.java
 * Created by liurenyong at 2013-9-3
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * 
 * @author liurenyong 2013-9-3
 */
public class Glint_Test extends JFrame {

    /**
     * 
     * @author liurenyong 2013-9-3
     */
    public Glint_Test() {
    	JMenuBar bar = new JMenuBar();
    	bar.add(new JMenu("file"));
    	setJMenuBar(bar);
    	
    	JToolBar toolBar = new JToolBar();
    	final JButton button = new JButton("Test");
    	button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(button.isFocusable());
			}
		});
    	button.setFocusable(false);
    	toolBar.add(button);
    	add(toolBar, BorderLayout.NORTH);
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setVisible(true);
        
        System.out.println(toolBar.getParent());
    }

    /**
     * 
     * @author liurenyong 2013-9-3
     */
    private void init() {
        Object[][] data = new Object[][] {
                { "12發aaa", Double.valueOf(Math.random() * 100), "dewresdfs", Double.valueOf(Math.random() * 200), "33322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dwrqsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "3daaa", Double.valueOf(Math.random() * 100), "2dsdfSsdfs", Double.valueOf(Math.random() * 200), "13222" },
                { "aaa", Double.valueOf(Math.random() * 100), "dssfasdfs", Double.valueOf(Math.random() * 200), "322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dasdfassdfs", Double.valueOf(Math.random() * 200), "322" },
                { "2saaa", Double.valueOf(Math.random() * 100), "afdawdsdfs", Double.valueOf(Math.random() * 200), "34322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dasfdasdfs", Double.valueOf(Math.random() * 200), "322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "22saaa", Double.valueOf(Math.random() * 100), "sdfdsdfs", Double.valueOf(Math.random() * 200), "5322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "16adsfaa", Double.valueOf(Math.random() * 100), "2rdsdfs", Double.valueOf(Math.random() * 200), "6322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "98aaa", Double.valueOf(Math.random() * 100), "5q2dsdfs", Double.valueOf(Math.random() * 200), "qa3222" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "4309aaa", Double.valueOf(Math.random() * 100), "67dsdfs", Double.valueOf(Math.random() * 200), "e322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "3t43aaa", Double.valueOf(Math.random() * 100), "2dsdfs", Double.valueOf(Math.random() * 200), "3s22" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "g4323aaa", Double.valueOf(Math.random() * 100), "1dsdfs", Double.valueOf(Math.random() * 200), "se322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "2a43aa", Double.valueOf(Math.random() * 100), "3dsdfs", Double.valueOf(Math.random() * 200), "3w22" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "gg5aaa", Double.valueOf(Math.random() * 100), "21dsdfs", Double.valueOf(Math.random() * 200), "e3d22" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "aaa", Double.valueOf(Math.random() * 100), "dsdfs", Double.valueOf(Math.random() * 200), "322" },
                { "773aaa", Double.valueOf(Math.random() * 100), "35dsdfs", Double.valueOf(Math.random() * 200), "3s22" } };
        Object columns[] = new Object[] { "Column1", "Column2", "Column3", "Column4", "Column5" };

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1 || columnIndex == 3) {
                    return Double.class;
                } else {
                    return String.class;
                }
            }
        };
        model.setDataVector(data, columns);
        JTable table = new JTable(model);
        table.setEnabled(false);
        table.setRowSorter(new TableRowSorter<TableModel>(model));
        List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new SortKey(1, SortOrder.ASCENDING));
//        sortKeys.add(new SortKey(2, SortOrder.DESCENDING));
        ((TableRowSorter<TableModel>)table.getRowSorter()).setSortKeys(sortKeys);

        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(204, 204, 255));
                List<SortKey> list = (List<SortKey>) table.getRowSorter().getSortKeys();
                // 标记表明当前列被排序
                boolean flag = false;
                for (SortKey sortKey : list) {
                    if (sortKey.getColumn() == column) {
                        Icon icon = null;
                        if (sortKey.getSortOrder() == SortOrder.ASCENDING) {
                            icon = new ImageIcon(Glint_Test.class.getResource("Cut16.gif"));
                        } else {
                            icon = new ImageIcon(Glint_Test.class.getResource("Delete16_1.gif"));
                        }
                        label.setHorizontalTextPosition(SwingConstants.LEFT);
                        label.setIcon(icon);
                        flag = true;

                        break;
                    }
                }
                if (!flag) {
                    label.setIcon(null);
                }
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });
        // table.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // table.setColumnSelectionAllowed(true);
        ((TableRowSorter) table.getRowSorter()).setMaxSortKeys(table.getColumnCount());
        JScrollPane scrollPane = new JScrollPane(table);

        table.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer());
        getContentPane().add(scrollPane);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
    }

    private class CustomRenderer extends DefaultTableCellRenderer {

        volatile JTable table = null;

        public CustomRenderer() {
            new RefreshThread().start();
        }

//        /**
//         * @return the table
//         */
//        public synchronized JTable getTable() {
//            return table;
//        }
//
//        /**
//         * @param table
//         *            the table to set
//         */
//        public synchronized void setTable(JTable table) {
//            this.table = table;
//        }

        int a = 0;
        
        public synchronized void addA() {
            a++;
        }
        
        public synchronized int getA() {
            return a;
        }
        
        

        private class RefreshThread extends Thread {

            @Override
            public void run() {
                while (true) {
                    if (table != null) {
                        Rectangle firstRowRectangle = table.getCellRect(0, 0, true);
                        Rectangle endRowRectangle = table.getCellRect(table.getRowCount() - 1, 0, true);
                        int width = endRowRectangle.width;
                        int height = endRowRectangle.y + endRowRectangle.height;
                        Rectangle refreshRectangle = new Rectangle(firstRowRectangle.x, firstRowRectangle.y, width, height);
                        addA();
                        refreshData();
//                        table.repaint(refreshRectangle);
//                        table.repaint();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        
        public void refreshData() {
        	for(int i = 0; i < table.getRowCount(); i++) {
        		table.setValueAt((double)(int)(100 * Math.random()), i, 1);
        	}
        	((AbstractTableModel)table.getModel()).fireTableDataChanged();
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (this.table == null) {
//                setTable(table);
                this.table = table;
            }
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//            System.out.println(component.hashCode());
         
            if (getA() % 2 != 0) {
            	if(((Double)(table.getValueAt(row, 1))) > 40) {
            		component.setBackground(Color.red);
            	} else if(((Double)(table.getValueAt(row, 1))) > 60){
            		component.setBackground(Color.green);
            	} else {
            		component.setBackground(null);
            	}
            } else {
            	if(((Double)(table.getValueAt(row, 1))) > 40) {
            		 component.setBackground(Color.yellow);
            	} else if(((Double)(table.getValueAt(row, 1))) > 60){
            		component.setBackground(Color.BLUE);
            	} else {
            		component.setBackground(null);
            	}
            }
            component.setEnabled(false);
            return component;
        }
    }

    public static void main(String[] args) {
        new Glint_Test();
    }
}
