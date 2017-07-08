package d5000enhance;


import java.io.Serializable;

/**
 * 数据单元 明确标识数据类型，数据长度
 * 
 * @author 边晓宇
 * 
 */
public class DataUnit implements Cloneable, Comparable<DataUnit>, Serializable {

	DataUnitInterface dataUnitInterface ;
	
	// 构造方法

	/**
	 * 创建一个字符型实例 数值，类型，长度均准确
	 */
	public DataUnit(String stringData) {
	}

	/**
	 * 创建一个整型实例 数值，类型，长度均准确
	 * 
	 * @param type
	 * @param longData
	 */
	public DataUnit(short type, long longData) {
	}

	/**
	 * 创建一个实型实例 数值，类型，长度均准确
	 * 
	 * @param type
	 * @param longData
	 */
	public DataUnit(short type, double doubleData) {
	}

	/**
	 * 创建一个byteArray实例 数值，类型，长度均准确
	 */
	public DataUnit(byte[] byteData) {
	}

	// 覆写的方法

	public String toString() {
		return dataUnitInterface.toString();
	}

	public Object clone() {
		return (Object)(dataUnitInterface.clone());
	}

	// bean方法

	public int getDataLength() {
		return dataUnitInterface.getDataLength();
	}

	/**
	 * 重设数据长度
	 */
	public void setDataLength(int dataLength) {
		dataUnitInterface.setDataLength(dataLength);
	}

	/**
	 * 数据类型是UCHAR的DataUnit,在调用getLongData时不要转换成byte，而应该转换为short
	 */
	public long getLongData() {
		return dataUnitInterface.getLongData();
	}

	/**
	 * 将整形数据设置入DataUnit 数值准确，长度和数据类型不准确
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
	 * 数值准确，长度和数据类型不准确
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
	 * 数据，类型，长度均准确
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
	 * 设置数据类型之后，自动更新数据长度
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
