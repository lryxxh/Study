package net;

import java.awt.Dimension;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

/**
 * 二进制解码与编码工具
 * @author 边晓宇
 *
 */
public class ByteTool {

	
	/**
	 * 将一个存放DataUnit对象的列表转换为byte数组
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
				//float型转成字节
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
	 * 将一个存放DataUnit对象的列表转换为byte数组
	 * @param message
	 * @return
	 */
	public static byte[] toByteForNariGIS(Vector dataUnits){//小端模式
		double left = Double.parseDouble(dataUnits.get(0).toString());
		double bottom = Double.parseDouble(dataUnits.get(1).toString());
		double right = Double.parseDouble(dataUnits.get(2).toString());
		double top = Double.parseDouble(dataUnits.get(3).toString());
		
		int    renderFlag = Integer.parseInt(dataUnits.get(4).toString());      // 渲染标志（第0位标记是否为透明）
	    int    formatFlag = Integer.parseInt(dataUnits.get(5).toString());      // 文件格式（0-PNG, 1-JPEG）
	    short  width = Short.parseShort(dataUnits.get(6).toString());     // 图像宽
	    short  height = Short.parseShort(dataUnits.get(7).toString());     // 图像高
	    short  layerCount = Short.parseShort(dataUnits.get(8).toString());      // 请求图层数目
	    short  namesBufferLen = Short.parseShort(dataUnits.get(9).toString());  // 图层名称数组Buffer长度
	    String layerNamesBuffer =dataUnits.get(11).toString();// 图层名称数组Buffer
	    serialize(left, bottom, right, top, renderFlag, formatFlag, new Dimension(width, height), layerCount, namesBufferLen, layerNamesBuffer);
		return sendData;
	}
	
	static byte [] sendData;
	 /**
	  * 反序列化，将请求信息根据协议转化成数据流
	  * @param mapName 打开地图的名字
	  * @param imageSize 图像大小
	  * @param left 地图范围左边界
	  * @param bottom 地图范围下边界
	  * @param right 地图范围右边界
	  * @param top 地图范围上边界
	  */
	 public static byte[] serialize(double left, double bottom, double right, double top, int renderFlag, int formatFlag, Dimension imageSize, short layerCount, short namesBufferLen,String name){
		// 一下是数据序列化的字节顺序，具体请参照详细的协议
		// int + 256*count + double + double + double + double + 32 + double +
		// short + short
		// count layername 左下X 左下Y 右上X 右上Y format transParent width height
		// 约定：所有的字符串都采用UTF-8编码格式
		// 地图名称
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
		  //请求数据大小
		  int totalLength = 8*4 + 4*2 + 2*4 + + 8 + bytes.length + 1;
//		  int totalLength = 8*4 + 4*2 + 2*4 + + 8 + bytes.length ;
		  sendData = new byte[totalLength];
		  int offset = 0;
		  offset = dataSetDouble(left,offset);//左
		  offset = dataSetDouble(bottom,offset);//下
		  offset = dataSetDouble(right,offset);//右
		  offset = dataSetDouble(top,offset);//上
		  
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
	  * 将int值序列化为数据流，小端模式
	  */
	 public static int dataSetInt(int val,int offset){
	  for(int n=0;n<4;n++){
	   sendData[3-n+offset]=(byte)(val>>(4-1-n)*8);
	  }
	  return offset+4;
	 }
	 /**
	  * 将short值序列化为数据流，小端模式
	  */
	 public static int dataSetShort(short val,int offset){
	  for(int n=0;n<2;n++){
	   sendData[1-n+offset]=(byte)(val>>(2-1-n)*8);
	  }
	  return offset+2;
	 }
	 /**
	  * 将double值序列化为数据流，小端模式
	  */
	 public static int dataSetDouble(double val,int offset){
	  long valLong = Double.doubleToLongBits(val);
	  for(int n=0;n<8;n++){
	   sendData[7-n+offset]=(byte)(valLong>>(8-1-n)*8);
	  }
	  return offset+8;
	 }
	 /**
	  * 将String值序列化为数据流，UTF-8字符编码格式
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
	 * 将original字节数组从第offset位开始被part数组替换
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
	 * 将两个字节数组按照先后顺序组合
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
	 * 将二维byte数组合并为一个byte数组
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
	 * 计算二维byte数组的总长度
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
	 * 计算给定的DataUnit列表中所有记录的长度总和
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
	 * 计算给定的DataUnit列表中所有记录的长度总和
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
	 * 从一个数据流的头部将一个给定的DataUnit赋值
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
	
	
	// 字节转成UCHAR型,由于Java不支持UCHAR类型，所以这里转换成short类型
	public static short byte2Uchar(byte[] b, int off) {
		int temp = b[0];
		if(b[0] < 0){
			temp = temp + 256;
		}
		return (short)temp;
	}
	
	// 字节转成short型
	public static short byte2Short(byte[] b, int off) {
		return (short) (((b[off + 1] & 0xFF) << 0) + ((b[off + 0] & 0xFF) << 8));
	}
	
	// 字节转成int型
	public static int byte2Int(byte[] b, int off) {
		return ((b[off + 3] & 0xFF) << 0) + ((b[off + 2] & 0xFF) << 8)
				+ ((b[off + 1] & 0xFF) << 16) + ((b[off + 0] & 0xFF) << 24);
	}
	
	// 字节转成long型
	public static long byte2Long(byte[] b, int off) {
		return ((b[off + 7] & 0xFFL) << 0) + ((b[off + 6] & 0xFFL) << 8)
				+ ((b[off + 5] & 0xFFL) << 16) + ((b[off + 4] & 0xFFL) << 24)
				+ ((b[off + 3] & 0xFFL) << 32) + ((b[off + 2] & 0xFFL) << 40)
				+ ((b[off + 1] & 0xFFL) << 48) + ((b[off + 0] & 0xFFL) << 56);
	}
	
	
	// 字节转成float型
	public static float byte2Float(byte[] b, int off) {
		int i = ((b[off + 3] & 0xFF) << 0) + ((b[off + 2] & 0xFF) << 8)
				+ ((b[off + 1] & 0xFF) << 16) + ((b[off + 0] & 0xFF) << 24);
		return Float.intBitsToFloat(i);
	}



	// 字节转成double型
	public static double byte2Double(byte[] b, int off) {
		long j = ((b[off + 7] & 0xFFL) << 0) + ((b[off + 6] & 0xFFL) << 8)
				+ ((b[off + 5] & 0xFFL) << 16) + ((b[off + 4] & 0xFFL) << 24)
				+ ((b[off + 3] & 0xFFL) << 32) + ((b[off + 2] & 0xFFL) << 40)
				+ ((b[off + 1] & 0xFFL) << 48) + ((b[off + 0] & 0xFFL) << 56);
		return Double.longBitsToDouble(j);
	}
	
	
	// 字节转成char型
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
            b[i] = new Long(temp & 0xff).byteValue();// 将最低位保存在最低位 
            temp = temp >> 8; // 向右移8位 
        } 
        return b; 
    } 
	
}
