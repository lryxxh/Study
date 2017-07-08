package swing.extend.table.demo.model;

import java.util.List;
import java.util.Vector;

import com.kedong.hmi.service.DataClient;
import com.kedong.hmi.service.common.exception.DataFaultException;
import com.kedong.hmi.service.common.exception.LocatorException;
import com.kedong.hmi.service.common.exception.ProxyException;
import com.kedong.hmi.service.common.exception.ServiceConnectionException;

import swing.extend.table.model.column.ColumnIdentifier;
import swing.extend.table.model.data.AbstractRequestDataManager;
import swing.extend.table.model.parmeter.Parameter;

/**
 * 实时库请求数据管理类
 * @author HMI-Lenovo
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class RealtimeRequestDataManager extends AbstractRequestDataManager{
	
	//存储表属性
	private Vector<String> properties = new Vector<String>(); 
	
	//实时库参数
	private RealtimeParameter parameter = null;
	public RealtimeRequestDataManager(Parameter parameter) {
		super(parameter);
		this.parameter = (RealtimeParameter) parameter;
	}

	@Override
	public List<List> requestData(List<ColumnIdentifier> identifiers) {
		if(properties.isEmpty()) {
			buildColumnNameByIdentifier(identifiers);
		}
		return getData();
	}
	
	/**
	 * 调用接口取数.
	 * @return
	 */
	private List<List> getData() {
		List<List> data = new Vector<List>(); 
		try {
			data = DataClient.RTDBRead(parameter.getDbid(), parameter.getTableName(), properties);
		} catch (DataFaultException e) {
			e.printStackTrace();
		} catch (ServiceConnectionException e) {
			e.printStackTrace();
		} catch (LocatorException e) {
			e.printStackTrace();
		} catch (ProxyException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 根据列信息获取取数域信息.
	 * @param identifiers
	 * @return
	 */
	private Vector<String> buildColumnNameByIdentifier(List<ColumnIdentifier> identifiers) {
		String fieldName = "";
		if(null != identifiers) {
			for(ColumnIdentifier identifier : identifiers) {
				fieldName = identifier.getEn_Name();
				if(null != properties && !properties.contains(fieldName)) {
					properties.add(fieldName);
				}
			}
		}
		return properties;
	}

	@Override
	public List<List> refreshData() {
		return getData();
	}

}
