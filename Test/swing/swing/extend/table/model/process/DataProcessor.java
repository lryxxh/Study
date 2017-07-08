package swing.extend.table.model.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import swing.extend.table.model.column.ColumnIdentifier;
import swing.extend.table.model.parmeter.Parameter;

/**
 * ���ݴ���ӿڣ�ÿ��ʵ�ִ���һ�����ͣ���ֻ�ܴ���һ������.
 * @author HMI-Lenovo
 * @version 1.0
 * @created 21-һ��-2014 10:47:50
 */
@SuppressWarnings("rawtypes")
public abstract class DataProcessor {
	
	protected List<ColumnIdentifier> filterIdentifiers = new ArrayList<ColumnIdentifier>();
	
	/**
	 * �б�ʶ���Լ��ϡ�
	 * @param identifiers ������
	 * @param  parameter  ȡ�������������ڴ������ݹ������õ�
	 */
	public DataProcessor(List<ColumnIdentifier> identifiers, Parameter parameter) {
		filterColumnIdentifierByType(identifiers);
	}
	
	/**
	 * �ж�ColumnIdentifier�Ƿ���Ҫ�����DataProcess�д���
	 * @return
	 */
	protected abstract boolean isDataProcessType(ColumnIdentifier identifier);
	
	/**
	 * ����DataProcessҪ�������������������ColumnIdentifier��ÿһ��DataProcessֻ����һ������
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
	 * �������ݣ����������ݴ洢��map�У�����key��Ӧ������Ϣbean��ֵ��Ӧ�еĴ���������.
	 * @param identifiers	����Ϣ����.
	 * @param data	��������
	 * @param processDataMap ���Լ��������Ϣ
	 */
	public abstract void process(List<ColumnIdentifier> identifiers, List<List> data, Map<ColumnIdentifier, List> processDataMap);

}