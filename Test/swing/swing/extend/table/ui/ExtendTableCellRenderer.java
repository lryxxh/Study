package swing.extend.table.ui;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * 扩展的表格渲染器接口.renderer中不进行数据的处理，所有数据处理在Model中进行，例如引用数据，格式化等。
 * @author HMI-Lenovo
 *
 */
public interface ExtendTableCellRenderer extends TableCellRenderer{
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column);
	
	/**
	 * 单元格渲染处理.
	 * @param table	  表格
	 * @param value	  当前单元格的值
	 * @param row	  当前行号
	 * @param column 当前列号
	 */
	public void rendererProcessor(JTable table, Object value, int row, int column);

}
