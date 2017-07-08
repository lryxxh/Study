package swing.extend.table.demo.model;

import swing.extend.table.model.parmeter.Parameter;

/**
 * 实时库取数参数bean.
 * @author HMI-Lenovo
 *
 */
public class RealtimeParameter extends Parameter{
	
	//服务定位标识
	private String dbid = "";
	
	//表格名
	private String tableName = "";
	
	/**
	 * 设置服务定位标识.
	 * @param dbid
	 */
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	
	/**
	 * 获取服务定位标识.
	 * @return
	 */
	public String getDbid() {
		return dbid;
	}
	
	/**
	 * 设置表名.
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * 获取表名.
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}
	
}
