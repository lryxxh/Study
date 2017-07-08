package net;

import java.awt.Dimension;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

/**
 * �����ƽ�������빤��
 * @author ������
 *
 */
public class ByteTool {

	
	/**
	 * ��һ�����DataUnit������б�ת��Ϊbyte����
	 * @param message
	 * @return
	 */
	public static byte[] toByte(Vector dataUnits){
		byte[][] bytes=new byte[dataUnits.size()][];
		for(int i=0;i<dataUnits.size();i++){
			DataUnit unit=(DataUnit)dataUnits.get(i);
			int dataLength=unit.getDataLength();
			long longData=-1;
			double doubleData=-1;
			String data=null;

			if(unit.getType()==StaticFinals.TYPE_STRING){
				data=unit.getStringData();
				byte[] tempByte = new byte[dataLength];
				byte[] cc = data.getBytes();
				int length=cc.length<dataLength?cc.length:dataLength;
				for (int n = 0; n < length; n++) {
					tempByte[n] = cc[n];
				}	
				bytes[i]=tempByte;
			}
			else if(unit.getType()==StaticFinals.TYPE_INTEGER){
				longData=unit.getLongData();
				byte[] tempByte=new byte[dataLength];
				for(int n=0;n<dataLength;n++){
					tempByte[n]=(byte)(longData>>(dataLength-1-n)*8);
				}
				bytes[i]=tempByte;
			}
			else if(unit.getType()==StaticFinals.TYPE_FLOAT){
				//float��ת���ֽ�
				doubleData=unit.getDoubleData();
				long bits=0;
				if(unit.getDataType()==StaticFinals.FLOAT){
					bits =Float.floatToIntBits((float)doubleData);
				}
				else if(unit.getDataType()==StaticFinals.DOUBLE){
					bits =Double.doubleToLongBits(doubleData);
				}
				byte[] tempByte=new byte[dataLength];
				for(int n=0;n<dataLength;n++){
					tempByte[n]=(byte)(bits>>(dataLength-1-n)*8);
				}
				bytes[i]=tempByte;
			}
			else if(unit.getType() == StaticFinals.TYPE_BYTEARRAY){
				bytes[i] = unit.getByteData();
			}
		}
		return toByte(bytes);
	}
	/**
	 * ��һ�����DataUnit������б�ת��Ϊbyte����
	 * @param message
	 * @return
	 */
	public static byte[] toByteForNariGIS(Vector dataUnits){//С��ģʽ
		double left = Double.parseDouble(dataUnits.get(0).toString());
		double bottom = Double.parseDouble(dataUnits.get(1).toString());
		double right = Double.parseDouble(dataUnits.get(2).toString());
		double top = Double.parseDouble(dataUnits.get(3).toString());
		
		int    renderFlag = Integer.parseInt(dataUnits.get(4).toString());      // ��Ⱦ��־����0λ����Ƿ�Ϊ͸����
	    int    formatFlag = Integer.parseInt(dataUnits.get(5).toString());      // �ļ���ʽ��0-PNG, 1-JPEG��
	    short  width = Short.parseShort(dataUnits.get(6).toString());     // ͼ���
	    short  height = Short.parseShort(dataUnits.get(7).toString());     // ͼ���
	    short  layerCount = Short.parseShort(dataUnits.get(8).toString());      // ����ͼ����Ŀ
	    short  namesBufferLen = Short.parseShort(dataUnits.get(9).toString());  // ͼ����������Buffer����
	    String layerNamesBuffer =dataUnits.get(11).toString();// ͼ����������Buffer
	    serialize(left, bottom, right, top, renderFlag, formatFlag, new Dimension(width, height), layerCount, namesBufferLen, layerNamesBuffer);
		return sendData;
	}
	
	static byte [] sendData;
	 /**
	  * �����л�����������Ϣ����Э��ת����������
	  * @param mapName �򿪵�ͼ������
	  * @param imageSize ͼ���С
	  * @param left ��ͼ��Χ��߽�
	  * @param bottom ��ͼ��Χ�±߽�
	  * @param right ��ͼ��Χ�ұ߽�
	  * @param top ��ͼ��Χ�ϱ߽�
	  */
	 public static byte[] serialize(double left, double bottom, double right, double top, int renderFlag, int formatFlag, Dimension imageSize, short layerCount, short namesBufferLen,String name){
		// һ�����������л����ֽ�˳�򣬾����������ϸ��Э��
		// int + 256*count + double + double + double + double + 32 + double +
		// short + short
		// count layername ����X ����Y ����X ����Y format transParent width height
		// Լ�������е��ַ���������UTF-8�����ʽ
		// ��ͼ����
		// ArrayList<String> layerNames = new ArrayList<String>();
		// layerNames.add(mapName);
		// int layerCount = layerNames.size();
		//String name = new String("ShanXinWX");
	
		  byte[] bytes = null;
		  try {
		   bytes = name.getBytes("UTF-8");
		  } catch (UnsupportedEncodingException e) {
		   e.printStackTrace();
		  }
		  //�������ݴ�С
		  int totalLength = 8*4 + 4*2 + 2*4 + + 8 + bytes.length + 1;
//		  int totalLength = 8*4 + 4*2 + 2*4 + + 8 + bytes.length ;
		  sendData = new byte[totalLength];
		  int offset = 0;
		  offset = dataSetDouble(left,offset);//��
		  offset = dataSetDouble(bottom,offset);//��
		  offset = dataSetDouble(right,offset);//��
		  offset = dataSetDouble(top,offset);//��
		  
		  offset = dataSetInt(renderFlag,offset);
		  offset = dataSetInt(formatFlag,offset);
		 
		  offset = dataSetShort((short)imageSize.width,offset);
		  offset = dataSetShort((short)imageSize.height,offset);
		  
		  offset = dataSetShort((short)layerCount,offset);
		  offset = dataSetShort(namesBufferLen, offset);
//		  offset = dataSetShort((short)(bytes.length),offset);
		  offset += 8;
		  offset = dataSetLayerName(name, offset);
//		  System.arraycopy(bytes, 0, sendData, offset, bytes.length);
		  return sendData;
	 }
	 /**
	  * ��intֵ���л�Ϊ��������С��ģʽ
	  */
	 public static int dataSetInt(int val,int offset){
	  for(int n=0;n<4;n++){
	   sendData[3-n+offset]=(byte)(val>>(4-1-n)*8);
	  }
	  return offset+4;
	 }
	 /**
	  * ��shortֵ���л�Ϊ��������С��ģʽ
	  */
	 public static int dataSetShort(short val,int offset){
	  for(int n=0;n<2;n++){
	   sendData[1-n+offset]=(byte)(val>>(2-1-n)*8);
	  }
	  return offset+2;
	 }
	 /**
	  * ��doubleֵ���л�Ϊ��������С��ģʽ
	  */
	 public static int dataSetDouble(double val,int offset){
	  long valLong = Double.doubleToLongBits(val);
	  for(int n=0;n<8;n++){
	   sendData[7-n+offset]=(byte)(valLong>>(8-1-n)*8);
	  }
	  return offset+8;
	 }
	 /**
	  * ��Stringֵ���л�Ϊ��������UTF-8�ַ������ʽ
	  */
	 public static int dataSetLayerName(String val,int offset){
	  byte[] strBuffer = null;
	  try {
	   strBuffer = val.getBytes("UTF-8");
	  } catch (UnsupportedEncodingException e) {
	   e.printStackTrace();
	  }
	  int length = strBuffer.length;
	  if(length > 256){
	   length = 256;
	  }
	  System.arraycopy(strBuffer, 0, sendData, offset, length);
	  return offset+256;
	 }
	
	
	
	/**
	 * ��original�ֽ�����ӵ�offsetλ��ʼ��part�����滻
	 * @param original
	 * @param part
	 * @param offset
	 * @return
	 */
	public static byte[] replace(byte[] original,byte[] part,int offset){
		byte[] result=original;
		for(int i=0;i<part.length;i++){
			original[offset+i]=part[i];
		}
		return result;
	}
	
	/**
	 * �������ֽ����鰴���Ⱥ�˳�����
	 * @param first
	 * @param second
	 * @return
	 */
	public static byte[] combine(byte[] first,byte[] second){
		int firstLength=first.length;
		int secondLength=second.length;
		byte[] result=new byte[firstLength+secondLength];
		for(int i=0;i<firstLength;i++){
			result[i]=first[i];
		}
		for(int i=0;i<secondLength;i++){
			result[firstLength+i]=second[i];
		}
		return result;
	}
	
	/**
	 * ����άbyte����ϲ�Ϊһ��byte����
	 * @param bytes
	 * @return
	 */
	private static byte[] toByte(byte[][] bytes){
		byte[] allData=new byte[getLengthOfBytes(bytes)];
		int offset=0;
		for(int i=0;i<bytes.length;i++){
			byte[] byteData=bytes[i];
			for(int j=0;j<byteData.length;j++){
				allData[offset+j]=byteData[j];
			}
			offset=offset+byteData.length;
		}
		return allData;
	}
	
	/**
	 * �����άbyte������ܳ���
	 * @param dataUnits
	 * @return
	 */
	private static int getLengthOfBytes(byte[][] bytes){
		int length=0;
		for(int i=0;i<bytes.length;i++){
			byte[] byteData=bytes[i];
			length+=byteData.length;
		}
		return length;
	}
	
	
	/**
	 * ���������DataUnit�б������м�¼�ĳ����ܺ�
	 * @param dataUnits
	 * @return
	 */
	public static int getLength(Vector dataUnits){
		int length=0;
		for(int i=0;i<dataUnits.size();i++){
			DataUnit unit=(DataUnit)dataUnits.get(i);
			length+=unit.getDataLength();
		}
		return length;
	}
	/**
	 * ���������DataUnit�б������м�¼�ĳ����ܺ�
	 * @param dataUnits
	 * @return
	 */
	public static int getLengthForNariGis(Vector dataUnits){
		int length=0;
		for(int i=0;i<dataUnits.size();i++){
			Object data = dataUnits.get(i);
			if(data instanceof Short){
				length += 2;
			} else if (data instanceof Integer){
				length += 4;
			} else if (data instanceof Double){
				length += 8;
			} else if (data instanceof String){
				length += data.toString().length();
			} 
		}
		return length;
	}
	
	/**
	 * ��һ����������ͷ����һ��������DataUnit��ֵ
	 * @param inputStream
	 * @param dataUnit
	 */
	public  static void read(byte[] byteData, DataUnit dataUnit) {
		short dataType=dataUnit.getDataType();
		
		switch (dataType) {
		case StaticFinals.INT:
			dataUnit.setLongData(byte2Int(byteData, 0));
			break;
		case StaticFinals.STRING:
			for(int i = 0;i < byteData.length;i++) {
				if(byteData[i] == 0) {
					for(int j = i+1;j<byteData.length;j++) {
						byteData[j] = 0;
					}
					break;
				}
			}
			dataUnit.setStringData(new String(byteData).trim());
			break;
		case StaticFinals.SHORT:
			dataUnit.setLongData(byte2Short(byteData, 0));
			break;
		case StaticFinals.LONG:
			dataUnit.setLongData(byte2Long(byteData, 0));
			break;
		case StaticFinals.FLOAT:
			dataUnit.setDoubleData(byte2Float(byteData, 0));
			break;
		case StaticFinals.UCHAR:
			dataUnit.setLongData(byte2Uchar(byteData, 0));
			break;
		case StaticFinals.TIME:
			dataUnit.setLongData(byte2Long(byteData, 0));
			break;	
		case StaticFinals.DOUBLE:
			dataUnit.setDoubleData(byte2Double(byteData, 0));
			break;
		case StaticFinals.BYTEARRAY:
			dataUnit.setByteData(byteData);
			break;
		default:
			break;
		}
	}
	
	
	// �ֽ�ת��UCHAR��,����Java��֧��UCHAR���ͣ���������ת����short����
	public static short byte2Uchar(byte[] b, int off) {
		int temp = b[0];
		if(b[0] < 0){
			temp = temp + 256;
		}
		return (short)temp;
	}
	
	// �ֽ�ת��short��
	public static short byte2Short(byte[] b, int off) {
		return (short) (((b[off + 1] & 0xFF) << 0) + ((b[off + 0] & 0xFF) << 8));
	}
	
	// �ֽ�ת��int��
	public static int byte2Int(byte[] b, int off) {
		return ((b[off + 3] & 0xFF) << 0) + ((b[off + 2] & 0xFF) << 8)
				+ ((b[off + 1] & 0xFF) << 16) + ((b[off + 0] & 0xFF) << 24);
	}
	
	// �ֽ�ת��long��
	public static long byte2Long(byte[] b, int off) {
		return ((b[off + 7] & 0xFFL) << 0) + ((b[off + 6] & 0xFFL) << 8)
				+ ((b[off + 5] & 0xFFL) << 16) + ((b[off + 4] & 0xFFL) << 24)
				+ ((b[off + 3] & 0xFFL) << 32) + ((b[off + 2] & 0xFFL) << 40)
				+ ((b[off + 1] & 0xFFL) << 48) + ((b[off + 0] & 0xFFL) << 56);
	}
	
	
	// �ֽ�ת��float��
	public static float byte2Float(byte[] b, int off) {
		int i = ((b[off + 3] & 0xFF) << 0) + ((b[off + 2] & 0xFF) << 8)
				+ ((b[off + 1] & 0xFF) << 16) + ((b[off + 0] & 0xFF) << 24);
		return Float.intBitsToFloat(i);
	}



	// �ֽ�ת��double��
	public static double byte2Double(byte[] b, int off) {
		long j = ((b[off + 7] & 0xFFL) << 0) + ((b[off + 6] & 0xFFL) << 8)
				+ ((b[off + 5] & 0xFFL) << 16) + ((b[off + 4] & 0xFFL) << 24)
				+ ((b[off + 3] & 0xFFL) << 32) + ((b[off + 2] & 0xFFL) << 40)
				+ ((b[off + 1] & 0xFFL) << 48) + ((b[off + 0] & 0xFFL) << 56);
		return Double.longBitsToDouble(j);
	}
	
	
	// �ֽ�ת��char��
	public static char byte2Char(byte[] b, int off) {
		return (char) (((b[off + 1] & 0xFF) << 0) + ((b[off + 0] & 0xFF) << 8));
	}
	
	public static void main(String [] arg){
		byte [] b = longToByte(1234);
		for(int i = 0; i < b.length; i++){
			System.out.println(b[i]);
		}
		System.out.println(byte2Long(b, b.length));
	}
	public static byte[] longToByte(long number) { 
        long temp = number; 
        byte[] b = new byte[8]; 
        for (int i = 0; i < b.length; i++) { 
            b[i] = new Long(temp & 0xff).byteValue();// �����λ���������λ 
            temp = temp >> 8; // ������8λ 
        } 
        return b; 
    } 
	
}
