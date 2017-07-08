package swing.extend.table.model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.RowSorter;

import swing.extend.table.model.column.ColumnIdentifier;
import swing.extend.table.model.data.AbstractRequestDataManager;
import swing.extend.table.model.parmeter.Parameter;
import swing.extend.table.model.process.DataProcessorManager;
import swing.extend.table.model.sorter.CompositeRowFilter;
import swing.extend.table.model.sorter.RowSortFilterManager;

/**
 * ��չ�������ģ�͹�����.
 * @author HMI-Lenovo
 * @version 1.0
 * @created 21-һ��-2014 10:47:43
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class AbstractExtendTableModelManager {

	//�洢����Ϣ�ļ���
	protected List<ColumnIdentifier> identifiers = null;
	
	//���
	protected JTable table = null;
	
	//ˢ�¹�����
	protected RefreshManager refreshManager = null;
	
	//���ݴ��������
	protected DataProcessorManager dataProcessorManager = null;
	
	//�������������
	protected AbstractRequestDataManager requestDataManager = null;
	
	//�������ɸѡ������
	protected RowSortFilterManager rowSortFilterManager = null;
	
	//�������ģ��
	protected ExtendTableModel tableModel = null;
	
	//�Ƿ�����
	private boolean isSort = false;
	
	//ȡ������
	protected Parameter parameter; 

	/**
	 * Ĭ�Ϲ�����.
	 * @param table	 	Ҫ���õı��
	 * @param identifiers ������ͷ����
	 * @param parameter	ȡ���Ĳ���bean
	 * @param isSort	�Ƿ�����
	 */
	public AbstractExtendTableModelManager(JTable table, List<ColumnIdentifier> identifiers, Parameter parameter, boolean isSort){
		this.table = table;
		this.identifiers = identifiers;
		this.isSort = isSort;
		this.parameter = parameter;
		initManager();
		buildExtendTable();
	}

	/**
	 * ��ʼ���������ģ�ͣ������������������ֻͨ������������һ�Ρ�
	 */
	private void buildExtendTable(){
		try {
			setExtendTableModel();
			setTableRowSorter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���ݲ��������ݡ�
	 * @return
	 */
	private final List<List> handleData() {
		List<List> data = requestDataManager.requestData(identifiers); 
		dataProcessorManager.process(identifiers, data);
		return data;
	}
	
	private final List<List> refreshHandleData() {
		List<List> data = requestDataManager.refreshData(); 
		dataProcessorManager.process(identifiers, data);
		return data;
	}

	/**
	 * ���ݱ�������Լ������ݴ������ģ��,���ģ��ҪʱExtendTableModel���͡������ģ�����õ�tableModel�С�
	 * @param data
	 * @param identifiers
	 */
	protected abstract void buildExtendTableModel(List<List> data, List<ColumnIdentifier> identifiers);

	/**
	 * ���ñ�����������.
	 */
	private final void setTableRowSorter(){
		if (null != table) {
			//�����Ҫ���������initRowSortFilter���õĳ�ʼ����ʽ��������
			if(isSort) {
				table.setRowSorter(rowSortFilterManager.setRowSorter(tableModel));
				rowSortFilterManager.filter();
				rowSortFilterManager.sort();
			} else {
				//�������Ҫ������������е�����
				table.setAutoCreateRowSorter(false);
				rowSortFilterManager.clearSortKey();
			}
		}
	}

	/**
	 * ���ñ������ģ�͡�
	 */
	private final void setExtendTableModel() throws Exception{
		if (null == tableModel) {
			buildExtendTableModel(handleData(), identifiers);
			if(null == tableModel) {
				throw new Exception("buildExtendTableModel����û������tableModel��ֵ");
			}
			
			if(null == table) {
				throw new Exception("���췽������Ҫ����JTable��ֵ");
			}
			table.setModel(tableModel);
		}
	}

	/**
	 * ��ʼ�����е�manager��
	 */
	protected void initManager(){
		initRequestDataManager();
		initDataProcessManager();
		initRowSortFilterManager();
		initRefreshManager();
	}

	/**
	 * ��ʼ�������ݵ�manager,����requestDataManager��ֵ.
	 */
	protected abstract void initRequestDataManager();
	
	/**
	 * ��ʼˢ��manager����Ҫ�Ļ����Ը�ϰ�÷�����
	 */
	protected void initRefreshManager(){
		refreshManager = new RefreshManager(this);
		initRefresher();
	}
	

	/**
	 * �����ǰ��manager��������Ҫ����չ��manager�������������ֱ������manager��ֵ��
	 */
	protected void initDataProcessManager() {
		dataProcessorManager = new DataProcessorManager();
		initDataProcessor();
	}
	
	/**
	 * �����ǰ��manager��������Ҫ����չ��manager�������������ֱ������manager��ֵ��
	 */
	protected void initRowSortFilterManager() {
		rowSortFilterManager = new RowSortFilterManager();
		initRowSortFilter();
	}
	
	/**
	 * ����������г�ʼ��dataProcessorManager�����ԣ�������managerע��dataprocess...
	 */
	protected void initDataProcessor(){};
	
	/**
	 * ����������г�ʼ��rowSortFilterManager�����ԣ������ʼ��������ʽ����ʼ��������
	 */
	protected void initRowSortFilter(){};
	

	/**
	 * ����������г�ʼ��refreshManager�����ԣ�����ˢ�����ڣ��Ƿ���˸ ...
	 */
	protected void initRefresher(){};
	
	/**
	 * �����µĹ����������ԭ������.
	 * @param rowFilter
	 */
	public void setRowFilter(CompositeRowFilter rowFilter) {
		setRowFilter(rowFilter, true);
	}
	
	/**
	 * �������ӹ�������
	 * @param rowFilter ������
	 * @param isClear	�Ƿ����ԭ������
	 */
	public void setRowFilter(CompositeRowFilter rowFilter, boolean isClear) {
		rowSortFilterManager.setRowFilters(rowFilter, isClear);
	}
	
	/**
	 * �����µ�������.(�����ԭ������)
	 * @param sortKeys
	 */
	public void setRowSortKeys(List<RowSorter.SortKey> sortKeys) {
		setRowSortKeys(sortKeys, true);
	}
	
	/**
	 * @param sortKey �µ�������
	 * @param isClear �Ƿ����ԭ������
	 */
	public void setRowSortKeys(List<RowSorter.SortKey> sortKeys, boolean isClear){
		rowSortFilterManager.setRowSortKeys(sortKeys, isClear);
	}
	
	/**
	 * ���������.
	 */
	public void clearFilter() {
		rowSortFilterManager.clearRowFilters();
	}
	
	/**
	 * ���������.
	 */
	public void clearSortKey() {
		rowSortFilterManager.clearSortKey();
	}

	/**
	 * ˢ�����ݡ�����Ѿ�������ᰴ���Ѿ����ڵ�����ʽ��������������ڹ���������ͬʱ�����ݽ���ˢ�¡�
	 */
	public void refreshData() {
		tableModel.refreshDataVector(refreshHandleData());
	}
	
}