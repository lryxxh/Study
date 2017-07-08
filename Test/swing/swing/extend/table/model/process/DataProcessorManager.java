package swing.extend.table.model.process;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import swing.extend.table.model.AbstractExtendTableModelManager;
import swing.extend.table.model.column.ColumnIdentifier;


/**
 * @author HMI-Lenovo
 * @version 1.0
 * @created 21-һ��-2014 10:47:45
 */
@SuppressWarnings({"rawtypes", "unused"})
public class DataProcessorManager {

	//�洢����Ϣ��Ӧ�Ĵ���������
	private Map<ColumnIdentifier,List> processDataMap = new HashMap<ColumnIdentifier, List>();
	
	//�洢���е����ݴ�����
	private List<DataProcessor> processorList = new ArrayList<DataProcessor>();

	public DataProcessorManager(){

	}

	/**
	 * ��������.
	 * @param columnIdentifiers
	 * @param data
	 */
	public void process(List<ColumnIdentifier> columnIdentifiers, List<List> data){
		for(DataProcessor processor : processorList) {
			processor.process(columnIdentifiers, data, processDataMap);
		}
	}

	/**
	 * ע�����ݴ���.
	 * @param dataProcessor
	 */
	public void registerProcess(DataProcessor dataProcessor){
		if(null != dataProcessor) {
			processorList.add(dataProcessor);
		}
	}

}