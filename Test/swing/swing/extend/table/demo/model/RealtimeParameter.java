package swing.extend.table.demo.model;

import swing.extend.table.model.parmeter.Parameter;

/**
 * ʵʱ��ȡ������bean.
 * @author HMI-Lenovo
 *
 */
public class RealtimeParameter extends Parameter{
	
	//����λ��ʶ
	private String dbid = "";
	
	//�����
	private String tableName = "";
	
	/**
	 * ���÷���λ��ʶ.
	 * @param dbid
	 */
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	
	/**
	 * ��ȡ����λ��ʶ.
	 * @return
	 */
	public String getDbid() {
		return dbid;
	}
	
	/**
	 * ���ñ���.
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * ��ȡ����.
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}
	
}
