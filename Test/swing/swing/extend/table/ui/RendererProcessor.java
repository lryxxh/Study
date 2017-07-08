package swing.extend.table.ui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import swing.extend.table.model.column.ColumnIdentifier;

public abstract class RendererProcessor {
	
	//过滤后的列数据，当前RendererProcessor要处理的列
	protected List<ColumnIdentifier> filterIdentifiers = new ArrayList<ColumnIdentifier>();
	
	/**
	 * 判断当前单元格是否需要渲染，子类需要复写这个方法.
	 * @param table
	 * @param value
	 * @param row
	 * @param column
	 * @return
	 */
	public abstract boolean isFilterProcessor(JTable table, Object value, int row, int column);
	
	/**
	 * 是否需要处理.
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
	 * 处理单元格渲染.首先调用
	 * @param component 渲染组件
	 * @param identifier	表格列数据
	 * @param table		表格
	 * @param value		当前值
	 * @param row		当前行
	 * @param column	当前列
	 */
	public abstract void process(Component component, ColumnIdentifier identifier, JTable table, Object value, int row, int column);
	
}
