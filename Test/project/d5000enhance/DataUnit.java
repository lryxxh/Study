package d5000enhance;


import java.io.Serializable;

/**
 * ���ݵ�Ԫ ��ȷ��ʶ�������ͣ����ݳ���
 * 
 * @author ������
 * 
 */
public class DataUnit implements Cloneable, Comparable<DataUnit>, Serializable {

	DataUnitInterface dataUnitInterface ;
	
	// ���췽��

	/**
	 * ����һ���ַ���ʵ�� ��ֵ�����ͣ����Ⱦ�׼ȷ
	 */
	public DataUnit(String stringData) {
	}

	/**
	 * ����һ������ʵ�� ��ֵ�����ͣ����Ⱦ�׼ȷ
	 * 
	 * @param type
	 * @param longData
	 */
	public DataUnit(short type, long longData) {
	}

	/**
	 * ����һ��ʵ��ʵ�� ��ֵ�����ͣ����Ⱦ�׼ȷ
	 * 
	 * @param type
	 * @param longData
	 */
	public DataUnit(short type, double doubleData) {
	}

	/**
	 * ����һ��byteArrayʵ�� ��ֵ�����ͣ����Ⱦ�׼ȷ
	 */
	public DataUnit(byte[] byteData) {
	}

	// ��д�ķ���

	public String toString() {
		return dataUnitInterface.toString();
	}

	public Object clone() {
		return (Object)(dataUnitInterface.clone());
	}

	// bean����

	public int getDataLength() {
		return dataUnitInterface.getDataLength();
	}

	/**
	 * �������ݳ���
	 */
	public void setDataLength(int dataLength) {
		dataUnitInterface.setDataLength(dataLength);
	}

	/**
	 * ����������UCHAR��DataUnit,�ڵ���getLongDataʱ��Ҫת����byte����Ӧ��ת��Ϊshort
	 */
	public long getLongData() {
		return dataUnitInterface.getLongData();
	}

	/**
	 * ����������������DataUnit ��ֵ׼ȷ�����Ⱥ��������Ͳ�׼ȷ
	 * 
	 * @param longData
	 */
	public void setLongData(long longData) {
		dataUnitInterface.setLongData(longData);
	}

	public short getDataType() {
		return dataUnitInterface.getDataType();
	}

	public double getDoubleData() {
		return dataUnitInterface.getDoubleData();
	}

	/**
	 * ��ֵ׼ȷ�����Ⱥ��������Ͳ�׼ȷ
	 * 
	 * @param doubleData
	 */
	public void setDoubleData(double doubleData) {
		dataUnitInterface.setDoubleData(doubleData);
	}

	public String getStringData() {
		return dataUnitInterface.getStringData();
	}

	/**
	 * ���ݣ����ͣ����Ⱦ�׼ȷ
	 * 
	 * @param stringData
	 */
	public void setStringData(String stringData) {
		dataUnitInterface.setStringData(stringData);
	}

	public byte[] getByteData() {
		return  dataUnitInterface.getByteData();
	}

	public void setByteData(byte[] byteData) {
		dataUnitInterface.setByteData(byteData);
	}

	public byte getType() {
		return dataUnitInterface.getType();
	}

	public void setType(byte type) {
		dataUnitInterface.setType(type);
	}

	/**
	 * ������������֮���Զ��������ݳ���
	 * 
	 * @param dataType
	 */
	public void setDataType(short dataType) {
		dataUnitInterface.setDataType();
	}

	public Object getData() {
		return dataUnitInterface.getData();
	}

	public String getDetail() {
		return dataUnitInterface.getDetail();
	}

	public int compareTo(DataUnitInterface dataUnitInterface) {
		return dataUnitInterface.compareTo(dataUnitInterface);
	}

	public boolean equals(Object obj) {
		return true;
	}

	@Override
	public int compareTo(DataUnit o) {
		return 0;
	}
}
