package d5000enhance;

public interface DataUnitInterface extends Cloneable, Comparable<DataUnitInterface>{

	public int getDataLength();

	public void setDataLength(int dataLength);

	public long getLongData();

	public void setLongData(long longData);

	public short getDataType();

	public double getDoubleData();

	public void setDoubleData(double doubleData);

	public String getStringData();

	public void setStringData(String stringData);

	public byte[] getByteData();

	public void setByteData(byte[] byteData);

	public byte getType();

	public void setType(byte type);

	public void setDataType();

	public Object getData();

	public String getDetail();

	public int compareTo(DataUnitInterface dataUnitInterface);
	
	public Object clone();


}
