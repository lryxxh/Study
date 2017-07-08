package swing;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JFrame_Test extends JFrame{
	
	HashMap<String, Integer> columnIndex = new HashMap();
	public JFrame_Test() {
		initComponents();
		setSize(800,600);
		setVisible(true);
	}

	private void initComponents() {
		Container contaner = getRootPane();
		contaner.setLayout(new BorderLayout());
		contaner.add(getJScrollPane());
		JButton button = new JButton("±£´æ");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String columnName = "";
				for(int i = 0 ; i < table.getColumnCount();i++) {
					columnName = table.getColumnName(i);
					columnIndex.put(columnName, i);
				}
				System.out.println(columnIndex);
			}
		});
		JButton button2 = new JButton("ÖØÖÃ");
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		JPanel panel = new JPanel();
		panel.add(button);
		panel.add(button2);
		contaner.add(panel, BorderLayout.SOUTH);
	}

	private JScrollPane getJScrollPane() {
		JScrollPane pane = new JScrollPane();
		pane.setViewportView(getJTable());
		return pane;
		
	}

	private JTable getJTable() {
		Object[] column = {"A","B","C","D","E","F"};
		Object[][] data = new Object[10][];
		for(int i = 0; i < data.length;i++) {
			data[i] = new Object[column.length];
			for(int j = 0; j < column.length;j++) {
				data[i][j] = (int)(Math.random() * 100);
			}
		}
		table = new JTable(data, column);
		return table;
	}
	JTable table = null;
	public static void main(String[] args) {
		new JFrame_Test();
	}

}
