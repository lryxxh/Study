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
 * 扩展表格数据模型管理类.
 * @author HMI-Lenovo
 * @version 1.0
 * @created 21-一月-2014 10:47:43
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class AbstractExtendTableModelManager {

	//存储列信息的集合
	protected List<ColumnIdentifier> identifiers = null;
	
	//表格
	protected JTable table = null;
	
	//刷新管理类
	protected RefreshManager refreshManager = null;
	
	//数据处理管理类
	protected DataProcessorManager dataProcessorManager = null;
	
	//数据请求管理类
	protected AbstractRequestDataManager requestDataManager = null;
	
	//表格排序筛选管理类
	protected RowSortFilterManager rowSortFilterManager = null;
	
	//表格数据模型
	protected ExtendTableModel tableModel = null;
	
	//是否排序
	private boolean isSort = false;
	
	//取数参数
	protected Parameter parameter; 

	/**
	 * 默认构造器.
	 * @param table	 	要设置的表格
	 * @param identifiers 表格的列头数据
	 * @param parameter	取数的参数bean
	 * @param isSort	是否排序
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
	 * 初始化表格数据模型，并设置排序过滤器。只通过构造器调用一次。
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
	 * 获取数据并处理数据。
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
	 * 根据表格数据以及列数据创建表格模型,表格模需要时ExtendTableModel类型。将表格模型设置到tableModel中。
	 * @param data
	 * @param identifiers
	 */
	protected abstract void buildExtendTableModel(List<List> data, List<ColumnIdentifier> identifiers);

	/**
	 * 设置表格排序过滤器.
	 */
	private final void setTableRowSorter(){
		if (null != table) {
			//如果需要排序，则根据initRowSortFilter设置的初始排序方式进行排序
			if(isSort) {
				table.setRowSorter(rowSortFilterManager.setRowSorter(tableModel));
				rowSortFilterManager.filter();
				rowSortFilterManager.sort();
			} else {
				//如果不需要排序，则清除所有的排序
				table.setAutoCreateRowSorter(false);
				rowSortFilterManager.clearSortKey();
			}
		}
	}

	/**
	 * 设置表格数据模型。
	 */
	private final void setExtendTableModel() throws Exception{
		if (null == tableModel) {
			buildExtendTableModel(handleData(), identifiers);
			if(null == tableModel) {
				throw new Exception("buildExtendTableModel方法没有设置tableModel的值");
			}
			
			if(null == table) {
				throw new Exception("构造方法中需要传递JTable的值");
			}
			table.setModel(tableModel);
		}
	}

	/**
	 * 初始化所有的manager。
	 */
	protected void initManager(){
		initRequestDataManager();
		initDataProcessManager();
		initRowSortFilterManager();
		initRefreshManager();
	}

	/**
	 * 初始请求数据的manager,设置requestDataManager的值.
	 */
	protected abstract void initRequestDataManager();
	
	/**
	 * 初始刷新manager，需要的话可以复习该方法。
	 */
	protected void initRefreshManager(){
		refreshManager = new RefreshManager(this);
		initRefresher();
	}
	

	/**
	 * 如果当前有manager不满足需要，扩展该manager，在这个方法中直接设置manager的值。
	 */
	protected void initDataProcessManager() {
		dataProcessorManager = new DataProcessorManager();
		initDataProcessor();
	}
	
	/**
	 * 如果当前有manager不满足需要，扩展该manager，在这个方法中直接设置manager的值。
	 */
	protected void initRowSortFilterManager() {
		rowSortFilterManager = new RowSortFilterManager();
		initRowSortFilter();
	}
	
	/**
	 * 在这个方法中初始化dataProcessorManager的属性，比如向manager注册dataprocess...
	 */
	protected void initDataProcessor(){};
	
	/**
	 * 在这个方法中初始化rowSortFilterManager的属性，比如初始化的排序方式，初始过滤器。
	 */
	protected void initRowSortFilter(){};
	

	/**
	 * 在这个方法中初始化refreshManager的属性，比如刷新周期，是否闪烁 ...
	 */
	protected void initRefresher(){};
	
	/**
	 * 设置新的过滤器，清除原过滤器.
	 * @param rowFilter
	 */
	public void setRowFilter(CompositeRowFilter rowFilter) {
		setRowFilter(rowFilter, true);
	}
	
	/**
	 * 给表格添加过滤器，
	 * @param rowFilter 过滤器
	 * @param isClear	是否清除原过滤器
	 */
	public void setRowFilter(CompositeRowFilter rowFilter, boolean isClear) {
		rowSortFilterManager.setRowFilters(rowFilter, isClear);
	}
	
	/**
	 * 设置新的排序器.(将清空原排序器)
	 * @param sortKeys
	 */
	public void setRowSortKeys(List<RowSorter.SortKey> sortKeys) {
		setRowSortKeys(sortKeys, true);
	}
	
	/**
	 * @param sortKey 新的排序器
	 * @param isClear 是否清楚原排序器
	 */
	public void setRowSortKeys(List<RowSorter.SortKey> sortKeys, boolean isClear){
		rowSortFilterManager.setRowSortKeys(sortKeys, isClear);
	}
	
	/**
	 * 清楚过滤器.
	 */
	public void clearFilter() {
		rowSortFilterManager.clearRowFilters();
	}
	
	/**
	 * 清楚排序器.
	 */
	public void clearSortKey() {
		rowSortFilterManager.clearSortKey();
	}

	/**
	 * 刷新数据。如果已经排序，则会按照已经存在的排序方式进行排序，如果存在过滤器，则同时对数据进行刷新。
	 */
	public void refreshData() {
		tableModel.refreshDataVector(refreshHandleData());
	}
	
}