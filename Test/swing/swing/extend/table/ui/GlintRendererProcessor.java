package swing.extend.table.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;

import swing.extend.table.model.column.ColumnIdentifier;

/**
 * …¡À∏‰÷»æ¥¶¿Ì∆˜.
 * @author HMI-Lenovo
 *
 */
public class GlintRendererProcessor extends RendererProcessor{

	//…¡À∏—’…´øÿ÷∆
	boolean flag = false;
	
	@Override
	public boolean isFilterProcessor(JTable table, Object value, int row,
			int column) {
		boolean flag = false;
		//≤‚ ‘
		if(0 == column) {
			flag = true;
		}
		return flag;
	}

	@Override
	public void process(Component component, ColumnIdentifier identifier,
			JTable table, Object value, int row, int column) {
		if(flag) {
			component.setBackground(Color.RED);
		} else {
			component.setBackground(Color.GREEN);
		}
	}

}
