package swing.extend.table.demo.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import swing.extend.table.model.column.ColumnIdentifier;
import swing.extend.table.model.parmeter.Parameter;
import swing.extend.table.model.process.DataProcessor;

/**
 * һ���������͵����ݴ���.
 * @author HMI-Lenovo
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class DateProcessor extends DataProcessor{
	
	//Ҫת�������ڸ�ʽ��format
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * ���캯��
	 * @param identifiers
	 * @param parameter
	 */
	public DateProcessor(List<ColumnIdentifier> identifiers, Parameter parameter) {
		super(identifiers, parameter);
	}

	/**
	 * �ж�identifier���Ƿ���Ҫ����.
	 */
	protected boolean isDataProcessType(ColumnIdentifier identifier) {
		boolean flag = false;
		if(identifier.getClazz().getName().equals(Date.class.getName())) {
			flag = true;
		}
		return flag;
	}

	@Override
	public void process(
			List<ColumnIdentifier> identifiers,	List<List> data, Map<ColumnIdentifier, List> processDataMap) {
		int index = 0;
		long time= System.currentTimeMillis();
		Object origData = null;
		for(ColumnIdentifier identifier : super.filterIdentifiers) {
			index = identifiers.indexOf(identifier);
			List<Object> filterList = processDataMap.get(identifier);
			if(filterList == null) {
				filterList = new ArrayList<Object>();
				processDataMap.put(identifier, filterList);
			}
			for(List<Object> rowData : data) {
				origData = rowData.get(index);
				filterList.add(origData);
				rowData.set(index, format.format(new Date(Long.valueOf((String)origData + "000"))));
//				filterList.add(format.format(new Date(Long.valueOf((String)origData))));
			}
		}
		System.out.println((System.currentTimeMillis() - time));
	}

}
