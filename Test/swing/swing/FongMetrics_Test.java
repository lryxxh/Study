package swing;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.UIManager;

public class FongMetrics_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		System.out.println(UIManager.getDefaults().values());
		Font font = UIManager.getFont("Table.font");
		System.out.println(font);
		
		FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
//		FontMetrics fontMetrics = frame.getGraphics().getFontMetrics();
		int width = fontMetrics.stringWidth("HelloWorld!");
		System.out.println(width);

	}

}
