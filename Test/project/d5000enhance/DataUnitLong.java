package d5000enhance;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import agency.message.base.StaticFinals;

/**
 * 数据单元 明确标识数据类型，数据长度
 * 
 * @author 边晓宇
 * 
 */
public class DataUnitLong implements DataUnitInterface {

	private static final long serialVersionUID = 991010029339333L;

	private long data = -1;

	/**
	 * 创建一个整型实例 数值，类型，长度均准确
	 * 
	 * @param type
	 * @param longData
	 */
	public DataUnitLong(long data) {
		this.data = data;
	}

	public String toString() {
		return String.valueOf(data);
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	// bean方法

	public int getDataLength() {
		return StaticFinals.LONG_LENGTH;
	}

	public void read(InputStream is, ByteBuffer buffer) throws IOException {
		is.read(buffer.array());
		this.data = buffer.get();
		buffer.rewind();
	}

	@Override
	public void setDataLength(int dataLength) {
	}

	@Override
	public long getLongData() {
		return (long) data;
	}

	@Override
	public void setLongData(long longData) {
		this.data = longData;
	}

	@Override
	public short getDataType() {
		return StaticFinals.INT;
	}

	@Override
	public double getDoubleData() {
		return data;
	}

	@Override
	public void setDoubleData(double doubleData) {
		this.data = (long) doubleData;
	}

	@Override
	public String getStringData() {
		return "";
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
		return StaticFinals.TYPE_INTEGER;
	}

	@Override
	public void setType(byte type) {
	}

	@Override
	public void setDataType() {
	}

	@Override
	public String getDetail() {
		StringBuilder builder = new StringBuilder();
		builder.append("dataType:").append(StaticFinals.dataTypeToString(getDataType())).append("--length:").append(getDataLength()).append("--data:").append(data);
		return builder.toString();
	}

	@Override
	public int compareTo(DataUnitInterface dataUnitInterface) {
		int flag = 1;
		if (dataUnitInterface.getClass().getName().equals(getClass().getName())) {
			flag = (int) (this.data - ((DataUnitLong)dataUnitInterface).data);
		}
		return flag;
	}

	@Override
	public Object getData() {
		return data;
	}

}
