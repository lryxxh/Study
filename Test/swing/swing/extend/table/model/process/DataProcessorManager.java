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
 * @created 21-一月-2014 10:47:45
 */
@SuppressWarnings({"rawtypes", "unused"})
public class DataProcessorManager {

	//存储列信息对应的处理后的数据
	private Map<ColumnIdentifier,List> processDataMap = new HashMap<ColumnIdentifier, List>();
	
	//存储所有的数据处理器
	private List<DataProcessor> processorList = new ArrayList<DataProcessor>();

	public DataProcessorManager(){

	}

	/**
	 * 处理数据.
	 * @param columnIdentifiers
	 * @param data
	 */
	public void process(List<ColumnIdentifier> columnIdentifiers, List<List> data){
		for(DataProcessor processor : processorList) {
			processor.process(columnIdentifiers, data, processDataMap);
		}
	}

	/**
	 * 注册数据处理.
	 * @param dataProcessor
	 */
	public void registerProcess(DataProcessor dataProcessor){
		if(null != dataProcessor) {
			processorList.add(dataProcessor);
		}
	}

}