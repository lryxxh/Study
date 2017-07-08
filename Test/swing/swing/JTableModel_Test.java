/**
 * JTableModel_Test.java
 * Created by liurenyong at 2014-1-2
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import sun.swing.DefaultLookup;

import com.l2fprod.common.swing.renderer.DefaultCellRenderer;

/**
 * 
 * @author liurenyong 2014-1-2
 */
public class JTableModel_Test {
	public static void main(String[] args) {
		final String[] columns = new String[10];

		final Object[][] data = new Object[100][10];
		for (int i = 0; i < data.length; i++) {
			data[i] = new Object[10];
			for (int j = 0; j < 10; j++) {
				data[i][j] = (50 * Math.random());
				if (j == 0) {
					data[i][j] = i + "";
				}
				if (i == 0) {
					columns[j] = "Column" + j;
				}
			}
		}
		JFrame frame = new JFrame();
		final JScrollPane scrollPane = new JScrollPane();

		TableCellRenderer renderer = new DefaultCellRenderer() {
			public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

				Color fg = null;
				Color bg = null;

				JTable.DropLocation dropLocation = table.getDropLocation();
				if (dropLocation != null && !dropLocation.isInsertRow() && !dropLocation.isInsertColumn() && dropLocation.getRow() == row && dropLocation.getColumn() == column) {

					fg = DefaultLookup.getColor(this, ui, "Table.dropCellForeground");
					bg = DefaultLookup.getColor(this, ui, "Table.dropCellBackground");

					isSelected = true;
				}

				if (isSelected) {
					super.setForeground(fg == null ? table.getSelectionForeground() : fg);
					super.setBackground(bg == null ? table.getSelectionBackground() : bg);
				} else {
					Color background = table.getBackground();
					if (background == null || background instanceof javax.swing.plaf.UIResource) {
						Color alternateColor = DefaultLookup.getColor(this, ui, "Table.alternateRowColor");
						if (alternateColor != null && row % 2 == 0)
							background = alternateColor;
					}
					super.setForeground(table.getForeground());
					super.setBackground(background);
				}

				setFont(table.getFont());

				if (hasFocus) {
					Border border = null;
					if (isSelected) {
						border = DefaultLookup.getBorder(this, ui, "Table.focusSelectedCellHighlightBorder");
					}
					if (border == null) {
						border = DefaultLookup.getBorder(this, ui, "Table.focusCellHighlightBorder");
					}
					setBorder(border);

					if (!isSelected && table.isCellEditable(row, column)) {
						Color col;
						col = DefaultLookup.getColor(this, ui, "Table.focusCellForeground");
						if (col != null) {
							super.setForeground(col);
						}
						col = DefaultLookup.getColor(this, ui, "Table.focusCellBackground");
						if (col != null) {
							super.setBackground(col);
						}
					}
				} else {
					setBorder(BorderFactory.createEmptyBorder());
				}
				setText(value.toString());
				return this;
			};
		};

		final DefaultTableModel model = new DefaultTableModel() {

//			@Override
//			public Class<?> getColumnClass(int columnIndex) {
//				if (columnIndex == 0) {
//					return Double.class;
//				}
//				return super.getColumnClass(columnIndex);
//			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.table.DefaultTableModel#getColumnName(int)
			 */
			@Override
			public String getColumnName(int column) {
				return columns[column];
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.table.DefaultTableModel#getColumnCount()
			 */
			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return columns.length;
			}
		};
		final JTable table = new JTable();

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				System.out.println("---" + e.getPoint());
			}
		});
		table.setAutoCreateRowSorter(true);
		// table.setShowGrid(false);
		// table.setShowVerticalLines(true);
		table.setModel(model);
		model.setDataVector(data, columns);
//		table.getColumnModel().getColumn(0).setCellRenderer(renderer);
		TableRowSorter rowSorter = (TableRowSorter) table.getRowSorter();
		rowSorter.setComparator(0, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				Long a1 = Long.valueOf(o1.toString());
				Long a2 = Long.valueOf(o2.toString());
				return a1.compareTo(a2);
			};
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.err.println("......................." + e + "  " + e.getSource());
			}
		});

		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).sizeWidthToFit();
		}

		scrollPane.getViewport().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JViewport viewport = (JViewport) e.getSource();
				Rectangle rectangle = viewport.getViewRect();
				System.out.println("rect:" + rectangle);
				System.out.println("top: " + table.rowAtPoint(rectangle.getLocation()));
				System.out.println("bottom: " + table.rowAtPoint(new Point(rectangle.x + rectangle.width, rectangle.y + rectangle.height - 1)));

			}
		});
		

		// table.getColumnModel().getColumn(0).setMaxWidth(0);
		// table.getColumnModel().getColumn(0).setMinWidth(0);
		// table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.addMouseListener(new MouseAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent
			 * )
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("---------" + table.getSelectedColumn());
				System.out.println(table.getSelectedColumnCount());
			}
		});
		JButton button = new JButton("..............");
		button.setToolTipText("<html><font color=red>Button</font></html>");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				JViewport viewport = scrollPane.getViewport();
//				Rectangle rect = table.getCellRect(table.getRowCount(), 0, true);
//				System.err.println("sdfs fdd"+rect);
//				viewport.setViewPosition(rect.getLocation());
				int max = scrollPane.getVerticalScrollBar().getMaximum();
				int min = scrollPane.getVerticalScrollBar().getMinimum();
				scrollPane.getVerticalScrollBar().setValue(max);
			
				
			}
		});
		scrollPane.setViewportView(table);
		JTable table2 = new JTable();
		

		Vector<Vector> indexdata = new Vector<Vector>();
		Vector indexcolumns = new Vector();
		for (int i = 0; i < 100; i++) {
			Vector row  = new Vector();
			for (int j = 0; j < 1; j++) {
				String value = "";
				value = ""+ (int)(50 * Math.random());
				if (j == 0) {
					value = i + "";
				}
				if (i == 0) {
					indexcolumns.add("Column" + j);
				}
				row.add(value);
			}
			indexdata.add(row);
		}
		table2.setModel(new DefaultTableModel(indexdata, indexcolumns));
		table2.getColumnModel().getColumn(0).setMinWidth(50);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.getColumnModel().getColumn(0).setPreferredWidth(50);
		table2.getColumnModel().getColumn(0).setMaxWidth(50);
		table2.setPreferredScrollableViewportSize(new Dimension(50, 0));
		scrollPane.setRowHeaderView(table2);
		JLabel label = new JLabel("ÐòºÅ");
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		scrollPane.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, label);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane().add(button, BorderLayout.SOUTH);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn tableColumn = null;
		for (int i = 0; i < table.getColumnCount(); i++) {
			tableColumn = columnModel.getColumn(i);
			tableColumn.setPreferredWidth(120);
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		button.repaint();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		table.setGridColor(Color.red);
	}
}
