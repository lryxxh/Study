package swing.extend.table.model.column;

/**
 * ����Ϣ.
 * @author HMI-Lenovo
 *
 */
@SuppressWarnings("rawtypes") 
public class ColumnIdentifier {
	
	//���ݿ���������Ӣ������
	private String en_Name = "";
	
	//��������������
	private String ch_Name = "";
	
	//�е���������
	private Class columnType = null;
	
	/**
	 * ����Ӣ����.
	 * @param en_Name
	 */
	public void setEn_Name(String en_Name) {
		this.en_Name = en_Name;
	}
	
	/**
	 * ����������.
	 * @param ch_Name
	 */
	public void setCh_Name(String ch_Name) {
		this.ch_Name = ch_Name;
	}
	
	/**
	 * �õ�Ӣ����.
	 * @return
	 */
	public String getEn_Name() {
		return en_Name;
	}
	
	/**
	 * �õ�������.
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
