package swing.extend.table.model.data;

import java.util.List;

import swing.extend.table.model.column.ColumnIdentifier;
import swing.extend.table.model.parmeter.Parameter;

/**
 * @author HMI-Lenovo
 * @version 1.0
 * @created 21-һ��-2014 10:47:49
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractRequestDataManager {

	protected Parameter parameter;

	public AbstractRequestDataManager(Parameter parameter){
		this.parameter = parameter;
	}

	/**
	 * ȡ���ӿ�.
	 * @param identifiers
	 * @param parameter
	 */
	public abstract List<List> requestData(List<ColumnIdentifier> identifiers);

	/**
	 * ˢ�½ӿ�.
	 * @return
	 */
	public abstract List<List> refreshData();

}