package swing.extend.table.model.sorter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultRowSorter;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import swing.extend.table.model.AbstractExtendTableModelManager;

/**
 * @author HMI-Lenovo
 * @version 1.0
 * @created 21-一月-2014 10:47:51
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public class RowSortFilterManager {
	
	//表格排序接口
	private TableRowSorter rowSorter = null;
	
	//用于存储派讯的键
	private List<RowSorter.SortKey> sortKeyList = new ArrayList<RowSorter.SortKey>();
	
	//用于存储过滤的过滤器集合
	private CompositeRowFilter compositeRowFilter = new CompositeRowFilter();

	public RowSortFilterManager(){

	}
	
	/**
	 * 得到表格排序器,创建初始过滤器。
	 * @return
	 */
	public TableRowSorter setRowSorter(TableModel tableModel) {
		getRowSorter().setModel(tableModel);
		return rowSorter;
	}

	/**
	 * 
	 * @param sortKey
	 */
	public void registerSortKey(SortKey sortKey){
		getRowSorter().getSortKeys().add(sortKey);
	}

	/**
	 * 如果rowSorter尚未被初始化，得到一个默认的rowSorter。
	 * @return
	 */
	private TableRowSorter getRowSorter() {
		if(null == rowSorter) {
			rowSorter = new TableRowSorter<TableModel>();
		}
		return rowSorter;
	}

	/**
	 * 注册过滤器。
	 * @param rowFilter
	 */
	public void registerRowFilter(RowFilter rowFilter) {
		compositeRowFilter.addRowFilter(rowFilter);
	}
	
	/**
	 * 移除 过滤器。
	 * @param rowFilter
	 */
	public void unregisterRowFilter(RowFilter rowFilter) {
		compositeRowFilter.removeRowFilter(rowFilter);
	}
	
	/**
	 * 设置过滤器.(先清楚原过滤器)
	 * @param rowFilter
	 */
	public void setRowFilters(CompositeRowFilter rowFilter, boolean isClear) {
		if(isClear) {
			clearRowFilters();
		}
		getRowSorter().setRowFilter(rowFilter);
	}
	
	/**
	 * 清楚所有的过滤器。
	 */
	public void clearRowFilters() {
		getRowSorter().setRowFilter(null);
		compositeRowFilter.clear();
	}
	
	/**
	 * 清楚所有的排序键。
	 */
	public void clearSortKey() {
		if(null != getRowSorter().getSortKeys()) {
			getRowSorter().getSortKeys().clear();
		}
	}
	
	/**
	 * 过滤表格数据。
	 * @param tableModel
	 */
	public void filter() {
		getRowSorter().setRowFilter(compositeRowFilter);
	}
	
	/**
	 * 按照给定的排序集合排序。
	 * @param data
	 */
	public void sort(){
		getRowSorter().getSortKeys().addAll(sortKeyList);
	}

	/**设置排序.
	 * @param sortKeys
	 * @param isClear
	 */
	public void setRowSortKeys(List<SortKey> sortKeys, boolean isClear) {
		if(isClear) {
			clearSortKey();
		}
		getRowSorter().getSortKeys().addAll(sortKeys);
	}

}