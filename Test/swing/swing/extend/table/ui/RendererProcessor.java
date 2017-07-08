package swing.extend.table.ui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import swing.extend.table.model.column.ColumnIdentifier;

public abstract class RendererProcessor {
	
	//���˺�������ݣ���ǰRendererProcessorҪ�������
	protected List<ColumnIdentifier> filterIdentifiers = new ArrayList<ColumnIdentifier>();
	
	/**
	 * �жϵ�ǰ��Ԫ���Ƿ���Ҫ��Ⱦ��������Ҫ��д�������.
	 * @param table
	 * @param value
	 * @param row
	 * @param column
	 * @return
	 */
	public abstract boolean isFilterProcessor(JTable table, Object value, int row, int column);
	
	/**
	 * �Ƿ���Ҫ����.
	 * @param identifier
	 * @return
	 */
	public boolean isNeedProcess(ColumnIdentifier identifier) {
		boolean flag = false;
		if(filterIdentifiers.contains(identifier)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * ����Ԫ����Ⱦ.���ȵ���
	 * @param component ��Ⱦ���
	 * @param identifier	���������
	 * @param table		���
	 * @param value		��ǰֵ
	 * @param row		��ǰ��
	 * @param column	��ǰ��
	 */
	public abstract void process(Component component, ColumnIdentifier identifier, JTable table, Object value, int row, int column);
	
}
