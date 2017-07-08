package swing.extend.table.ui;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * ��չ�ı����Ⱦ���ӿ�.renderer�в��������ݵĴ����������ݴ�����Model�н��У������������ݣ���ʽ���ȡ�
 * @author HMI-Lenovo
 *
 */
public interface ExtendTableCellRenderer extends TableCellRenderer{
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column);
	
	/**
	 * ��Ԫ����Ⱦ����.
	 * @param table	  ���
	 * @param value	  ��ǰ��Ԫ���ֵ
	 * @param row	  ��ǰ�к�
	 * @param column ��ǰ�к�
	 */
	public void rendererProcessor(JTable table, Object value, int row, int column);

}
