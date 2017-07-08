package d5000enhance;


import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * 数据单元 明确标识数据类型，数据长度
 * 
 * @author 边晓宇
 * 
 */
public class DataUnitString implements DataUnitInterface {

	private String str = "";
	
	private int length = 1;


	// 构造方法


	/**
	 * 创建一个整型实例 数值，类型，长度均准确
	 * 
	 * @param type
	 * @param longData
	 */
	public DataUnitString(String str) {
		this.str = str;
	}


	// 覆写的方法

	public String toString() {
		return str;
	}

	public Object clone() {
		DataUnitString dataUnit = null;
		try {
			dataUnit = (DataUnitString) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return dataUnit;
	}

	// bean方法

	public int getDataLength() {
		return 8;
	}

	public int compareTo(DataUnitString dataUnit) {
		return getString().compareTo(dataUnit.getString());
	}
	

	public String getString() {
		return str;
	}
	
	public void read(InputStream is) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(length);
		is.read(buffer.array());
		this.str = buffer.asCharBuffer().toString().trim();
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public int getLength() {
		return length;
	}


	@Override
	public void setDataLength(int dataLength) {
	}


	@Override
	public long getLongData() {
		return 0;
	}


	@Override
	public void setLongData(long longData) {
	}


	@Override
	public short getDataType() {
		return 0;
	}


	@Override
	public double getDoubleData() {
		return 0;
	}


	@Override
	public void setDoubleData(double doubleData) {
	}


	@Override
	public String getStringData() {
		return null;
	}


	@Override
	public void setStringData(String stringData) {
	}


	@Override
	public byte[] getByteData() {
		return null;
	}


	@Override
	public void setByteData(byte[] byteData) {
	}


	@Override
	public byte getType() {
		return 0;
	}


	@Override
	public void setType(byte type) {
	}


	@Override
	public void setDataType() {
	}


	@Override
	public Object getData() {
		return null;
	}


	@Override
	public String getDetail() {
		return null;
	}


	@Override
	public int compareTo(DataUnitInterface dataUnitInterface) {
		return 0;
	}
}
