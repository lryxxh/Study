package net;

import java.io.Serializable;


/**
 * ���ݵ�Ԫ ��ȷ��ʶ�������ͣ����ݳ���
 * 
 * @author ������
 * 
 */
public class DataUnit implements Cloneable, Comparable<DataUnit>, Serializable {

	private static final long serialVersionUID = 991010029339333L;
	// ������
	private int dataLength = -1;// ���ݳ���
	private short dataType = -1;// ��������
	private byte type = -1;// ���ͣ�ʵ�ͣ��ַ���,������������

	// ������
	private String stringData = null;
	private long longData = -1;
	private double doubleData = -1;
	private byte[] byteData = null;

	private Object data;

	// ���췽��

	/**
	 * ����һ���ַ���ʵ�� ��ֵ�����ͣ����Ⱦ�׼ȷ
	 */
	public DataUnit(String stringData) {
		setDataType(StaticFinals.STRING);
		this.stringData = stringData;
		this.data = stringData;
		this.setDataLength(stringData.getBytes().length);
	}

	/**
	 * ����һ������ʵ�� ��ֵ�����ͣ����Ⱦ�׼ȷ
	 * 
	 * @param type
	 * @param longData
	 */
	public DataUnit(short type, long longData) {
		setDataType(type);
		this.longData = longData;
		this.data = longData;
	}

	/**
	 * ����һ��ʵ��ʵ�� ��ֵ�����ͣ����Ⱦ�׼ȷ
	 * 
	 * @param type
	 * @param longData
	 */
	public DataUnit(short type, double doubleData) {
		setDataType(type);
		this.doubleData = doubleData;
		this.data = doubleData;
	}

	/**
	 * ����һ��byteArrayʵ�� ��ֵ�����ͣ����Ⱦ�׼ȷ
	 */
	public DataUnit(byte[] byteData) {
		setDataType(StaticFinals.BYTEARRAY);
		this.byteData = byteData;
		this.data = byteData;
		this.setDataLength((int) byteData.length);
	}

	// ��д�ķ���

	public String toString() {
		String string = "";
		if (type == StaticFinals.TYPE_INTEGER) {
			if (dataType == StaticFinals.INT) {
				string += (int) getLongData();
			} else if (dataType == StaticFinals.SHORT) {
				string += (short) getLongData();
			} else if (dataType == StaticFinals.LONG) {
				string += (long) getLongData();
			} else if (dataType == StaticFinals.UCHAR) {
				string += (long) getLongData();
			} else if (dataType == StaticFinals.TIME) {
				string += (long) getLongData();
			}
		} else if (type == StaticFinals.TYPE_FLOAT) {
			if (dataType == StaticFinals.FLOAT) {
				string += (float) getDoubleData();
			} else if (dataType == StaticFinals.DOUBLE) {
				string += (double) getDoubleData();
			}
		} else if (type == StaticFinals.TYPE_STRING) {
			string += getStringData();
		}
		return string;
	}

	public Object clone() {
		DataUnit dataUnit = null;
		try {
			dataUnit = (DataUnit) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return dataUnit;
	}

	// bean����

	public int getDataLength() {
		return dataLength;
	}

	/**
	 * �������ݳ���
	 */
	public void setDataLength(int dataLength) {
		if (dataType == StaticFinals.STRING || dataType == StaticFinals.BYTEARRAY) {
			this.dataLength = dataLength;
		}
	}

	/**
	 * ����������UCHAR��DataUnit,�ڵ���getLongDataʱ��Ҫת����byte����Ӧ��ת��Ϊshort
	 */
	public long getLongData() {
		return longData;
	}

	/**
	 * ����������������DataUnit ��ֵ׼ȷ�����Ⱥ��������Ͳ�׼ȷ
	 * 
	 * @param longData
	 */
	public void setLongData(long longData) {
		this.longData = longData;
		setType(StaticFinals.TYPE_INTEGER);
	}

	public short getDataType() {
		return dataType;
	}

	public double getDoubleData() {
		return doubleData;
	}

	/**
	 * ��ֵ׼ȷ�����Ⱥ��������Ͳ�׼ȷ
	 * 
	 * @param doubleData
	 */
	public void setDoubleData(double doubleData) {
		this.doubleData = doubleData;
		setType(StaticFinals.TYPE_FLOAT);
	}

	public String getStringData() {
		return stringData;
	}

	/**
	 * ���ݣ����ͣ����Ⱦ�׼ȷ
	 * 
	 * @param stringData
	 */
	public void setStringData(String stringData) {
		this.stringData = stringData;
		setType(StaticFinals.TYPE_STRING);
		this.setDataLength((short) stringData.getBytes().length);

	}

	public byte[] getByteData() {
		return byteData;
	}

	public void setByteData(byte[] byteData) {
		this.byteData = byteData;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	/**
	 * ������������֮���Զ��������ݳ���
	 * 
	 * @param dataType
	 */
	public void setDataType(short dataType) {
		this.dataType = dataType;
		switch (dataType) {
		case StaticFinals.INT:
			dataLength = StaticFinals.INT_LENGTH;
			type = StaticFinals.TYPE_INTEGER;
			break;
		case StaticFinals.UCHAR:
			dataLength = StaticFinals.BYTE_LENGTH;
			type = StaticFinals.TYPE_INTEGER;
			break;
		case StaticFinals.SHORT:
			dataLength = StaticFinals.SHORT_LENGTH;
			type = StaticFinals.TYPE_INTEGER;
			break;
		case StaticFinals.STRING:
			// �ַ������Ͳ�����
			type = StaticFinals.TYPE_STRING;
			break;
		case StaticFinals.LONG:
			dataLength = StaticFinals.LONG_LENGTH;
			type = StaticFinals.TYPE_INTEGER;
			break;
		case StaticFinals.FLOAT:
			dataLength = StaticFinals.FLOAT_LENGTH;
			type = StaticFinals.TYPE_FLOAT;
			break;
		case StaticFinals.DOUBLE:
			dataLength = StaticFinals.DOUBLE_LENGTH;
			type = StaticFinals.TYPE_FLOAT;
			break;
		case StaticFinals.TIME:
			dataLength = StaticFinals.TIME_LENGTH;
			type = StaticFinals.TYPE_INTEGER;
			break;
		case StaticFinals.BYTEARRAY:
			type = StaticFinals.TYPE_BYTEARRAY;
			break;
		default:
			break;
		}
	}

	public Object getData() {
		Object data = null;
		if (type == StaticFinals.TYPE_INTEGER) {
			if (dataType == StaticFinals.INT) {
				data = (int) getLongData();
			} else if (dataType == StaticFinals.SHORT) {
				data = (short) getLongData();
			} else if (dataType == StaticFinals.LONG) {
				data = (long) getLongData();
			} else if (dataType == StaticFinals.UCHAR) {
				data = (long) getLongData();
			}
		} else if (type == StaticFinals.TYPE_FLOAT) {
			if (dataType == StaticFinals.FLOAT) {
				data = (float) getDoubleData();
			} else if (dataType == StaticFinals.DOUBLE) {
				data = (double) getLongData();
			}
		} else if (type == StaticFinals.TYPE_STRING) {
			data = getStringData();
		} else if (type == StaticFinals.TYPE_BYTEARRAY) {
			data = getStringData();
		}
		return data;
	}

	public String getDetail() {
		String string = "dataType:" + StaticFinals.dataTypeToString(dataType) + "--length:" + dataLength + "--data:";
		if (type == StaticFinals.TYPE_INTEGER) {
			if (dataType == StaticFinals.INT) {
				string += (int) getLongData();
			} else if (dataType == StaticFinals.SHORT) {
				string += (short) getLongData();
			} else if (dataType == StaticFinals.LONG) {
				string += (long) getLongData();
			} else if (dataType == StaticFinals.UCHAR) {
				string += (long) getLongData();
			}
		} else if (type == StaticFinals.TYPE_FLOAT) {
			if (dataType == StaticFinals.FLOAT) {
				string += (float) getDoubleData();
			} else if (dataType == StaticFinals.DOUBLE) {
				string += (double) getLongData();
			}
		} else if (type == StaticFinals.TYPE_STRING) {
			// string+=getStringData();
		} else if (type == StaticFinals.TYPE_BYTEARRAY) {
			// string+=getStringData();
		}
		return string;
	}

	public int compareTo(DataUnit dataUnit) {
		if (type == dataUnit.getType() && dataType == dataUnit.getDataType()) {
			switch (type) {
			case StaticFinals.TYPE_INTEGER:
				if (longData < dataUnit.getLongData()) {
					return -1;
				} else if (longData > dataUnit.getLongData()) {
					return 1;
				} else {
					return 0;
				}
			case StaticFinals.TYPE_FLOAT:
				if (doubleData < dataUnit.getDoubleData()) {
					return -1;
				} else if (doubleData > dataUnit.getDoubleData()) {
					return 1;
				} else {
					return 0;
				}
			case StaticFinals.TYPE_STRING:
				// yuyue ���stringData.equals("")ʱ����0������ start
				// if(stringData != null && !stringData.equals("") &&
				// dataUnit.getStringData() != null &&
				// !dataUnit.getStringData().equals("")){ //add by jiangcong
				// 20110905
				// String first = PinYinTool.getPinYin(stringData)[0];
				// String second =
				// PinYinTool.getPinYin(dataUnit.getStringData())[0];
				// return first.compareTo(second);
				// }
				if (stringData != null && dataUnit.getStringData() != null) {
					String first = stringData;
					String second = dataUnit.getStringData();
					first = hasNullStr(first);
					second = hasNullStr(second);
					return first.compareTo(second);
				}
				// yuyue ���stringData.equals("")ʱ����0������ end
			default:
				// yuyue �������0������ start
				// // 2013.01.16 juguanghui �������Ϊ�� ���� start
				// return 0;
				// // 2013.01.16 juguanghui �������Ϊ�� ���� end
				if (stringData != null && dataUnit.getStringData() != null) {
					String first = stringData;
					String second = dataUnit.getStringData();
					first = hasNullStr(first);
					second = hasNullStr(second);
					return first.compareTo(second);
				} else {
					return toString().compareTo(dataUnit.toString());
				}
				// yuyue �������0������ start
			}
		} else {
			// yuyue �������0������ start
			// // 2013.01.16 juguanghui �������Ϊ�� ���� start
			// return 0;
			// // 2013.01.16 juguanghui �������Ϊ�� ���� end
			if (stringData != null && dataUnit.getStringData() != null) {
				String first = stringData;
				String second = dataUnit.getStringData();
				first = hasNullStr(first);
				second = hasNullStr(second);
				return first.compareTo(second);
			} else {
				return toString().compareTo(dataUnit.toString());
			}
			// yuyue �������0������ start
		}
	}

	private String hasNullStr(String str) {
		String result = "";
		if (str != null && !str.equals("") && !str.equals("null")) {
			result = str;
		}
		return result;
	}

	public boolean equals(Object obj) {
		DataUnit dataUnit = (DataUnit) obj;
		if (this.getType() == dataUnit.getType()) {
			if (this.getType() == StaticFinals.TYPE_INTEGER) {
				return (this.getLongData() == dataUnit.getLongData());
			} else if (this.getType() == StaticFinals.TYPE_FLOAT) {
				return (this.getDoubleData() == dataUnit.getDoubleData());
			} else if (this.getType() == StaticFinals.TYPE_STRING) {
				return (this.getStringData().equals(dataUnit.getStringData()));
			} else if (this.getType() == StaticFinals.TYPE_BYTEARRAY) {
				if (this.getByteData().length != dataUnit.getByteData().length) {
					return false;
				} else {
					for (int i = 0; i < this.getByteData().length; i++) {
						if (this.getByteData()[i] != dataUnit.getByteData()[i]) {
							return false;
						}
					}
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}
}
