package swing.extend.table.model.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import swing.extend.table.model.column.ColumnIdentifier;
import swing.extend.table.model.parmeter.Parameter;

/**
 * 数据处理接口，每种实现处理一种类型，且只能处理一种类型.
 * @author HMI-Lenovo
 * @version 1.0
 * @created 21-一月-2014 10:47:50
 */
@SuppressWarnings("rawtypes")
public abstract class DataProcessor {
	
	protected List<ColumnIdentifier> filterIdentifiers = new ArrayList<ColumnIdentifier>();
	
	/**
	 * 列标识属性集合。
	 * @param identifiers 列属性
	 * @param  parameter  取数参数，可能在处理数据过程中用到
	 */
	public DataProcessor(List<ColumnIdentifier> identifiers, Parameter parameter) {
		filterColumnIdentifierByType(identifiers);
	}
	
	/**
	 * 判断ColumnIdentifier是否需要在这个DataProcess中处理。
	 * @return
	 */
	protected abstract boolean isDataProcessType(ColumnIdentifier identifier);
	
	/**
	 * 根据DataProcess要处理的数据类型来分类ColumnIdentifier，每一种DataProcess只处理一种类型
	 */
	private void filterColumnIdentifierByType(List<ColumnIdentifier> identifiers) {
		if (null != identifiers) {
			for (ColumnIdentifier identifier : identifiers) {
				if(isDataProcessType(identifier)) {
					filterIdentifiers.add(identifier);
				}
			}
		}
	}


	/**
	 * 处理数据，处理后的数据存储在map中，其中key对应的列信息bean，值对应列的处理后的数据.
	 * @param identifiers	列信息集合.
	 * @param data	所有数据
	 * @param processDataMap 列以及处理后信息
	 */
	public abstract void process(List<ColumnIdentifier> identifiers, List<List> data, Map<ColumnIdentifier, List> processDataMap);

}