package net;

import java.io.IOException;
import java.io.InputStream;


/**
 * 动态读取网络数据流的工具类
 * @author 边晓宇
 *
 */
public class EvtRecvBufferTool {
	
	//输入流
	private InputStream inputStream=null;
	//当前已经读取过的字节在buffer中的游标位置
	private int offset=0;
	//当前buffer的实际长度
	private int realBufferLength=0;
	//buffer的最大长度
	private int maxBufferLength=0;
	
	private byte[] bufferData=null;

	//实际要读取的字节长度
	private int realDataLength = 0;

	//已经读取的字节长度
	private int readDataLength = 0;

	public EvtRecvBufferTool(InputStream inputStream,int bufferLength){
		this.inputStream=inputStream;
		this.maxBufferLength=bufferLength;
		this.bufferData=new byte[bufferLength];
	}

	public EvtRecvBufferTool(InputStream inputStream,int realDataLength,int maxBufferLength){
		this.inputStream = inputStream;
		this.realDataLength = realDataLength;
		this.maxBufferLength = maxBufferLength;
		if(realDataLength < maxBufferLength){
			maxBufferLength = realDataLength;
		}
		this.bufferData=new byte[maxBufferLength];
	}
	
	public EvtRecvBufferTool(InputStream inputStream){
		this(inputStream, 4096);
	}
	
	
	/**
	 * 从缓存管理器中读取数据单元
	 * @param dataUnit
	 */
	public void read(DataUnit dataUnit) throws IOException{
		//		System.out.println("start");
		int dataLength=dataUnit.getDataLength();
		//		System.out.println("需要读取的数据单元长度："+dataLength);
		
		
		//当要读取的长度大于当前最大缓冲区长度时，扩充最大缓冲区长度
		if(dataLength>maxBufferLength){
			byte[] newByte=new byte[dataLength];
			maxBufferLength=dataLength;
			int lastLength=realBufferLength-offset;//当前缓存中还没有读取的字节长度
			for(int i=0;i<lastLength;i++){
				newByte[i]=bufferData[offset+i];
			}
			realBufferLength=lastLength;
			offset=0;
			bufferData=newByte;
		}


		try {
			readFromInputStream(dataLength);
		} catch (StackOverflowError e) {
			throw new IOException();
//			e.printStackTrace();
		}

		byte[] byteData=new byte[dataLength];
		for(int i=0;i<dataLength;i++){
			byteData[i]=bufferData[offset+i];
		}
		ByteTool.read(byteData, dataUnit);
		offset+=dataLength;
		//		System.out.println("end");
	}

	/**
	 * 从缓存管理器中读取数据单元
	 * @param dataUnit
	 */
	public void readMMI(DataUnit dataUnit,String fileName) throws IOException{
		int dataLength=dataUnit.getDataLength();

		//当要读取的长度大于当前最大缓冲区长度时，扩充最大缓冲区长度
		if(dataLength>maxBufferLength){
			byte[] newByte=new byte[dataLength];
			maxBufferLength=dataLength;
			int lastLength=realBufferLength-offset;//当前缓存中还没有读取的字节长度
			for(int i=0;i<lastLength;i++){
				newByte[i]=bufferData[offset+i];
			}
			realBufferLength=lastLength;
			offset=0;
			bufferData=newByte;
		}


		try {
			readFromInputStreamMMI(dataLength);
		} catch (StackOverflowError e) {
			e.printStackTrace();
			throw new IOException();
		}

		if (bufferData.length < offset + dataLength) {
			return;
		}

		byte[] byteData=new byte[dataLength];
		for(int i=0;i<dataLength;i++){
			byteData[i]=bufferData[offset+i];
		}
		ByteTool.read(byteData, dataUnit);
		offset+=dataLength;
	}

	private void readFromInputStream(int dataLength) throws IOException{
		while (inputStream.available()<=0) {
			System.out.println("流中没有数据，需等待......");
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int lastLength=realBufferLength-offset;//当前缓存中还没有读取的字节长度
				System.out.println("当前缓存中剩余的字节长度："+lastLength);
		if(lastLength>=dataLength){
						System.out.println("不需要续读");
			return;
		}
		//modify by caizhao for 告警事件丢失问题
		int needLength=dataLength-lastLength;//还需要读取的字节长度
				System.out.println("还需要续读"+needLength+"字节");
		byte[] tempByte=new byte[needLength];
		int realReadLength=0;
		// 测试 start
//		InputStream in = inputStream;
//		while(in.read() != -1){
//			System.out.print(in.read());
//			System.out.print(",");
//		}
		System.out.println("EvtRecvBufferTool---当前内存中剩余可读取字节数:"+inputStream.available());
		// 测试 end
		realReadLength = inputStream.read(tempByte,0,needLength);
					System.out.println("实际读取了："+realReadLength+"字节");
		if(realReadLength!=needLength){
							System.out.println("没有完全读取============"+inputStream.available());
		}
		for(int i=0;i<lastLength;i++){
			bufferData[i]=bufferData[i+offset];
		}
		for(int i=0;i<realReadLength;i++){
			bufferData[lastLength+i]=tempByte[i];
		}
		offset=0;
		realBufferLength=lastLength+realReadLength;
		lastLength=realBufferLength;
//				System.out.println("游标位置置零");
//				System.out.println("实际buffer长度为："+realBufferLength);
		readFromInputStream(dataLength);
	}
	
	private void readFromInputStreamMMI(int dataLength) throws IOException{
		int lastLength=realBufferLength-offset;//当前缓存中还没有读取的字节长度
		//		System.out.println("当前缓存中剩余的字节长度："+lastLength);
		// if(lastLength>=dataLength || realDataLength - readDataLength ==0){
		if(lastLength>=dataLength){
			//			System.out.println("不需要续读");
			return;
		}
		int needLength=maxBufferLength-lastLength;//还需要读取的字节长度
		if(realDataLength - readDataLength < needLength ){
			needLength = realDataLength - readDataLength;
		}
		//		System.out.println("需要续读"+needLength+"字节");
		byte[] tempByte=new byte[needLength];
		int realReadLength=0;
		realReadLength = inputStream.read(tempByte,0,needLength);
		readDataLength += realReadLength;
		//			System.out.println("实际读取了："+realReadLength+"字节");
		if(realReadLength!=needLength){
			//				System.out.println("没有完全读取");
		}
		for(int i=0;i<lastLength;i++){
			bufferData[i]=bufferData[i+offset];
		}
		for(int i=0;i<realReadLength;i++){
			bufferData[lastLength+i]=tempByte[i];
		}
		offset=0;
		realBufferLength=lastLength+realReadLength;
		lastLength=realBufferLength;
		//		System.out.println("游标位置置零");
		//		System.out.println("实际buffer长度为："+realBufferLength);
		readFromInputStreamMMI(dataLength);
	}

	// 2012.11.08 zhangshikun 判断当前流中数据是否读完
	public boolean isReadOverFromInputStream() {
		boolean isReadOver = false;

		if (realDataLength - readDataLength == 0 && realBufferLength - offset == 0) {
			isReadOver = true;
		}

		return isReadOver;
	}
}
