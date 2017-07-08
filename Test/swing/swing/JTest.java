package swing;


import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3849316907321160120L;

	public JTest() {
		init();
	}

	private void init() {
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Object[][] data = new Object[][] { { "A", "C", "D", "E", "E" },
				{ "A", "C", "D", "E", "E" }, { "A", "C", "D", "E", "E" },
				{ "A", "C", "D", "E", "E" }, { "A", "C", "D", "E", "E" },
				{ "A", "C", "D", "E", "E" }, { "A", "C", "D", "E", "E" },
				{ "A", "C", "D", "E", "E" }, { "A", "C", "D", "E", "E" },
				{ "A", "C", "D", "E", "E" }, { "A", "C", "D", "E", "E" },
				{ "A", "C", "D", "E", "E" }, { "A", "C", "D", "E", "E" },
				{ "A", "C", "D", "E", "E" }, { "A", "C", "D", "E", "E" },
				{ "A", "C", "D", "E", "E" }, { "A", "C", "D", "E", "E" },
				{ "A", "C", "D", "E", "E" }, { "A", "C", "D", "E", "E" },
				{ "A", "C", "D", "E", "E" }, { "A", "C", "D", "E", "E" },
				{ "A", "C", "D", "E", "E" } };
		Object[] column = new Object[] { "Column1", "Column2", "Column3",
				"Column4", "Column5" };
		JTable table = new JTable(data, column);
		table.setPreferredSize(new Dimension(500, 280));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		this.add(scrollPane);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new JTest();
	}

}
