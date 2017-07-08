package swing;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class JTableSorterTest {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("file");
		JMenu saveMenu = new JMenu("save");
		JMenuItem item = new JMenuItem("sss");
		JMenuItem item2 = new JMenuItem("sss2");
		item.setEnabled(false);
		saveMenu.add(item);saveMenu.add(item2);
		fileMenu.add(saveMenu);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		
	
		Object[][] data = new Object[][] {
				{ "adat1", "hdat2", "tdata3", 11, 12, 30, 121 },
				{ "dsat1", "gdat2", "ydata3", 11, 12, 11, 22 },
				{ "a", "dfdat2", "udata3", 11, 13, 34, 343 },
				{ "ËµÊÇ", "sadat2", "idata3", 11, 13, 22, 234 },
				{ "°¡°¡", "2dat2", "odata3", 11, 14, 54, 542 },
				{ "°®°®°®µÄ", "edat2", "odata3", 12, 14, 32, 311 },
				{ "ydat1", "wdat2", "ldata3", 12, 12, 43, 131 },
				{ "udat1", "sdat2", "mdata3", 12, 54, 65, 131 } };
		Object[] column = new Object[] { "column1", "column2", "column3",
				"column4", "column5", "column6", "column7" };
		JTable table = new JTable(data, column);
		JScrollPane pane = new JScrollPane(table);
		final TableRowSorter sorter = new TableRowSorter(table.getModel());
		table.setRowSorter(sorter);
		sorter.sort();
		sorter.setMaxSortKeys(column.length);
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<RowSorter.SortKey> list = sorter.getSortKeys();
				System.out.println();
				for (RowSorter.SortKey key : list) {
					System.out.println(key.getColumn() + "  "  + key.getSortOrder());
				}
			}
		});
		JTableHeader header = table.getTableHeader();
//		header.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		table.setGridColor(Color.BLACK);
//		table.setRowMargin(5);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		for(int i=0;i<table.getColumnCount();i++) {
			
		}
//		table.setIntercellSpacing(new Dimension(20,5));
//		table.setRowHeight(30);
		frame.getContentPane().add(pane);
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	
//		System.out.println(new Date(1367112677));
	}

}
