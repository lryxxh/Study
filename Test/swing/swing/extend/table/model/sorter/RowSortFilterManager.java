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
 * @created 21-һ��-2014 10:47:51
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public class RowSortFilterManager {
	
	//�������ӿ�
	private TableRowSorter rowSorter = null;
	
	//���ڴ洢��Ѷ�ļ�
	private List<RowSorter.SortKey> sortKeyList = new ArrayList<RowSorter.SortKey>();
	
	//���ڴ洢���˵Ĺ���������
	private CompositeRowFilter compositeRowFilter = new CompositeRowFilter();

	public RowSortFilterManager(){

	}
	
	/**
	 * �õ����������,������ʼ��������
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
	 * ���rowSorter��δ����ʼ�����õ�һ��Ĭ�ϵ�rowSorter��
	 * @return
	 */
	private TableRowSorter getRowSorter() {
		if(null == rowSorter) {
			rowSorter = new TableRowSorter<TableModel>();
		}
		return rowSorter;
	}

	/**
	 * ע���������
	 * @param rowFilter
	 */
	public void registerRowFilter(RowFilter rowFilter) {
		compositeRowFilter.addRowFilter(rowFilter);
	}
	
	/**
	 * �Ƴ� ��������
	 * @param rowFilter
	 */
	public void unregisterRowFilter(RowFilter rowFilter) {
		compositeRowFilter.removeRowFilter(rowFilter);
	}
	
	/**
	 * ���ù�����.(�����ԭ������)
	 * @param rowFilter
	 */
	public void setRowFilters(CompositeRowFilter rowFilter, boolean isClear) {
		if(isClear) {
			clearRowFilters();
		}
		getRowSorter().setRowFilter(rowFilter);
	}
	
	/**
	 * ������еĹ�������
	 */
	public void clearRowFilters() {
		getRowSorter().setRowFilter(null);
		compositeRowFilter.clear();
	}
	
	/**
	 * ������е��������
	 */
	public void clearSortKey() {
		if(null != getRowSorter().getSortKeys()) {
			getRowSorter().getSortKeys().clear();
		}
	}
	
	/**
	 * ���˱�����ݡ�
	 * @param tableModel
	 */
	public void filter() {
		getRowSorter().setRowFilter(compositeRowFilter);
	}
	
	/**
	 * ���ո��������򼯺�����
	 * @param data
	 */
	public void sort(){
		getRowSorter().getSortKeys().addAll(sortKeyList);
	}

	/**��������.
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