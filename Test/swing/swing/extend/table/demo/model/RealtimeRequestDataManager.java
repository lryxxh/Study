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
 * ʵʱ���������ݹ�����
 * @author HMI-Lenovo
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class RealtimeRequestDataManager extends AbstractRequestDataManager{
	
	//�洢������
	private Vector<String> properties = new Vector<String>(); 
	
	//ʵʱ�����
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
	 * ���ýӿ�ȡ��.
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
	 * ��������Ϣ��ȡȡ������Ϣ.
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
