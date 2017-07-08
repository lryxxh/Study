package swing.extend.table.model.column;

/**
 * 列信息.
 * @author HMI-Lenovo
 *
 */
@SuppressWarnings("rawtypes") 
public class ColumnIdentifier {
	
	//数据库中列名（英文名）
	private String en_Name = "";
	
	//描述（中文名）
	private String ch_Name = "";
	
	//列的数据类型
	private Class columnType = null;
	
	/**
	 * 设置英文名.
	 * @param en_Name
	 */
	public void setEn_Name(String en_Name) {
		this.en_Name = en_Name;
	}
	
	/**
	 * 设置中文名.
	 * @param ch_Name
	 */
	public void setCh_Name(String ch_Name) {
		this.ch_Name = ch_Name;
	}
	
	/**
	 * 得到英文名.
	 * @return
	 */
	public String getEn_Name() {
		return en_Name;
	}
	
	/**
	 * 得到中文名.
	 * @return
	 */
	public String getCh_Name() {
		return ch_Name;
	}
	
	/**
	 * 
	 * @param columnType
	 */
	public void setClazz(Class columnType) {
		this.columnType = columnType;
	}
	
	/**
	 * @return
	 */
	public Class getClazz() {
		return columnType;
	}
	
	@Override
	public String toString() {
		String value = en_Name;
		if (ch_Name!= null && !"".equals(ch_Name)) {
			value = ch_Name;
		}
		return value;
	}

}
