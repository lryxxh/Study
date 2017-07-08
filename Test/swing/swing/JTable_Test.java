package swing;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultRowSorter;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;


public class JTable_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame= new JFrame();
		Object[][] data = new Object[][]{{"°¡","B","A"},{"Æ¤Å«ÒÛ","B","A"},{"ÊÇ","B","A"},{"ÖÐµÄ","B","A"}};
		Object[] columns = new Object[]{"C","B","A"};
		JTable table = new JTable(data, columns);
		table.setAutoCreateRowSorter(true);
		DefaultRowSorter sorter = (DefaultRowSorter) table.getRowSorter();
		List sortKeys = new ArrayList();
		SortKey sortKey1 = new SortKey(0, SortOrder.DESCENDING);
//		sortKeys.add(sortKey1);
		sorter.setSortKeys(sortKeys);
		frame.getContentPane().add(new JScrollPane(table));
		frame.setSize(600,400);
		frame.setVisible(true);
		
	}

}
