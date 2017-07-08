package swing.multipletableheader2;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class MultipleTableHeader_Test extends JFrame {

	public MultipleTableHeader_Test() {
		setSize(800, 600);
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void init() {
		final HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		map.put(1, new ArrayList<String>());
		map.put(2, new ArrayList<String>());
		map.put(3, new ArrayList<String>());
		map.get(1).add("A");
		map.get(1).add("B");
		map.get(2).add("C");
		map.get(2).add("D");
		map.get(3).add("E");
		
		final JTable table = new JTable(new Object[][]{{1,2,4,5,6,1,2,4,5,6},{1,2,4,5,6,1,2,4,5,6},{1,2,4,5,6,1,2,4,5,6},{1,2,4,5,6,1,2,4,5,6},{1,2,4,5,6,1,2,4,5,6}}, new Object[]{"A","B","C","D","E","A","B","C","D","E"});
//		table.getTableHeader().setDefaultRenderer(new TableTableHeaderRenderer());
		table.setTableHeader(getHeader(table));
//		{
//			@Override
//			protected JTableHeader createDefaultTableHeader() {
//				return getHeader(this);
//			}
//		}
		;
	
//		table.setTableHeader(header);
//		table.getTableHeader().setVisible(true);
//		table.getTableHeader().setDefaultRenderer(new TableCellRenderer() {
//			
//			@Override
//			public Component getTableCellRendererComponent(JTable arg0, Object arg1,
//					boolean arg2, boolean arg3, int arg4, int arg5) {
//				JTable table = new JTable(new Object[][]{{"A"},{"B"}},new Object[]{""});
//				return table;
//			}
//		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
	}
	
	public JTableHeader  getHeader(JTable table) {
		final JTableHeader header = new JTableHeader(table.getColumnModel()){
				
				
				public Rectangle getHeaderRect(int column) {
					System.err.println(column);
					Rectangle rectangle1 = super.getHeaderRect(2 * column);
					Rectangle rectangle2 = super.getHeaderRect(2 * column +1);
					System.out.println(rectangle1 +"  " + rectangle2);
					return new Rectangle(rectangle1.x, rectangle1.y, rectangle1.width + rectangle2.width, rectangle2.height);
//					return super.getHeaderRect(column);
				}
			};
			return header;
	}

	public static void main(String[] args) {
		new MultipleTableHeader_Test();
	}
	
//	class TableTableHeaderRenderer extends DefaultTableCellRenderer {
//		@Override
//		public Component getTableCellRendererComponent(JTable table,
//				Object value, boolean isSelected, boolean hasFocus, int row,
//				int column) {
//			JTable table2 = new JTable(new Object[][]{{2,2,3}},new Object[]{"A","B","C"});
////			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
////					row, column);
//			table2.getTableHeader().setVisible(true);
//			return table2.getTableHeader();
//		}
//	}

}
