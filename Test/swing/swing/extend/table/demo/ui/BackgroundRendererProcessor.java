package swing.extend.table.demo.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;

import swing.extend.table.model.column.ColumnIdentifier;
import swing.extend.table.ui.RendererProcessor;

/**
 * 组件文本对齐方式处理.
 * @author HMI-Lenovo
 *
 */
public class BackgroundRendererProcessor extends RendererProcessor{

	@Override
	public boolean isFilterProcessor(JTable table, Object value, int row,
			int column) {
		return true;
	}

	@Override
	public void process(Component component, ColumnIdentifier identifier,
			JTable table, Object value, int row, int column) {
		component.setBackground(Color.BLUE);

		if(column == 0) {
			component.setBackground(Color.GRAY);
		}
		
		if(column == 1) {
			component.setBackground(Color.RED);
		}
		
		if(column == 2) {
			component.setBackground(Color.GREEN);
		} 
		if (column == 4) {
			component.setBackground(Color.YELLOW);
		}
		
	}

}
