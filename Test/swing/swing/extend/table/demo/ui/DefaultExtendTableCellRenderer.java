package swing.extend.table.demo.ui;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import swing.extend.table.ui.AbstractRendererManager;
import swing.extend.table.ui.ExtendTableCellRenderer;

/**
 * 默认的扩展表格渲染器.
 * @author HMI-Lenovo
 *
 */
public class DefaultExtendTableCellRenderer extends DefaultTableCellRenderer implements ExtendTableCellRenderer{
	
	//序列id
	private static final long serialVersionUID = -7288929041904105695L;
	
	private AbstractRendererManager manager = null;
	
	public void setRendererManager(AbstractRendererManager rendererManager) {
		this.manager = rendererManager;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		rendererProcessor(table, value, row, column);
		return component;
	}

	@Override
	public void rendererProcessor(JTable table, Object value, int row, int column) {
		manager.processRenderer(this, table, value, row, column);
	}

}
