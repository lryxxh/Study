package net;

import java.io.IOException;
import java.io.InputStream;


/**
 * ��̬��ȡ�����������Ĺ�����
 * @author ������
 *
 */
public class EvtRecvBufferTool {
	
	//������
	private InputStream inputStream=null;
	//��ǰ�Ѿ���ȡ�����ֽ���buffer�е��α�λ��
	private int offset=0;
	//��ǰbuffer��ʵ�ʳ���
	private int realBufferLength=0;
	//buffer����󳤶�
	private int maxBufferLength=0;
	
	private byte[] bufferData=null;

	//ʵ��Ҫ��ȡ���ֽڳ���
	private int realDataLength = 0;

	//�Ѿ���ȡ���ֽڳ���
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
	 * �ӻ���������ж�ȡ���ݵ�Ԫ
	 * @param dataUnit
	 */
	public void read(DataUnit dataUnit) throws IOException{
		//		System.out.println("start");
		int dataLength=dataUnit.getDataLength();
		//		System.out.println("��Ҫ��ȡ�����ݵ�Ԫ���ȣ�"+dataLength);
		
		
		//��Ҫ��ȡ�ĳ��ȴ��ڵ�ǰ��󻺳�������ʱ��������󻺳�������
		if(dataLength>maxBufferLength){
			byte[] newByte=new byte[dataLength];
			maxBufferLength=dataLength;
			int lastLength=realBufferLength-offset;//��ǰ�����л�û�ж�ȡ���ֽڳ���
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
	 * �ӻ���������ж�ȡ���ݵ�Ԫ
	 * @param dataUnit
	 */
	public void readMMI(DataUnit dataUnit,String fileName) throws IOException{
		int dataLength=dataUnit.getDataLength();

		//��Ҫ��ȡ�ĳ��ȴ��ڵ�ǰ��󻺳�������ʱ��������󻺳�������
		if(dataLength>maxBufferLength){
			byte[] newByte=new byte[dataLength];
			maxBufferLength=dataLength;
			int lastLength=realBufferLength-offset;//��ǰ�����л�û�ж�ȡ���ֽڳ���
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
			System.out.println("����û�����ݣ���ȴ�......");
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int lastLength=realBufferLength-offset;//��ǰ�����л�û�ж�ȡ���ֽڳ���
				System.out.println("��ǰ������ʣ����ֽڳ��ȣ�"+lastLength);
		if(lastLength>=dataLength){
						System.out.println("����Ҫ����");
			return;
		}
		//modify by caizhao for �澯�¼���ʧ����
		int needLength=dataLength-lastLength;//����Ҫ��ȡ���ֽڳ���
				System.out.println("����Ҫ����"+needLength+"�ֽ�");
		byte[] tempByte=new byte[needLength];
		int realReadLength=0;
		// ���� start
//		InputStream in = inputStream;
//		while(in.read() != -1){
//			System.out.print(in.read());
//			System.out.print(",");
//		}
		System.out.println("EvtRecvBufferTool---��ǰ�ڴ���ʣ��ɶ�ȡ�ֽ���:"+inputStream.available());
		// ���� end
		realReadLength = inputStream.read(tempByte,0,needLength);
					System.out.println("ʵ�ʶ�ȡ�ˣ�"+realReadLength+"�ֽ�");
		if(realReadLength!=needLength){
							System.out.println("û����ȫ��ȡ============"+inputStream.available());
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
//				System.out.println("�α�λ������");
//				System.out.println("ʵ��buffer����Ϊ��"+realBufferLength);
		readFromInputStream(dataLength);
	}
	
	private void readFromInputStreamMMI(int dataLength) throws IOException{
		int lastLength=realBufferLength-offset;//��ǰ�����л�û�ж�ȡ���ֽڳ���
		//		System.out.println("��ǰ������ʣ����ֽڳ��ȣ�"+lastLength);
		// if(lastLength>=dataLength || realDataLength - readDataLength ==0){
		if(lastLength>=dataLength){
			//			System.out.println("����Ҫ����");
			return;
		}
		int needLength=maxBufferLength-lastLength;//����Ҫ��ȡ���ֽڳ���
		if(realDataLength - readDataLength < needLength ){
			needLength = realDataLength - readDataLength;
		}
		//		System.out.println("��Ҫ����"+needLength+"�ֽ�");
		byte[] tempByte=new byte[needLength];
		int realReadLength=0;
		realReadLength = inputStream.read(tempByte,0,needLength);
		readDataLength += realReadLength;
		//			System.out.println("ʵ�ʶ�ȡ�ˣ�"+realReadLength+"�ֽ�");
		if(realReadLength!=needLength){
			//				System.out.println("û����ȫ��ȡ");
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
		//		System.out.println("�α�λ������");
		//		System.out.println("ʵ��buffer����Ϊ��"+realBufferLength);
		readFromInputStreamMMI(dataLength);
	}

	// 2012.11.08 zhangshikun �жϵ�ǰ���������Ƿ����
	public boolean isReadOverFromInputStream() {
		boolean isReadOver = false;

		if (realDataLength - readDataLength == 0 && realBufferLength - offset == 0) {
			isReadOver = true;
		}

		return isReadOver;
	}
}
