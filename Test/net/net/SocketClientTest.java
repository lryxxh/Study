package net;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;


public class SocketClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Socket socket = new Socket("127.0.0.1", 50000);
//			socket.setSoTimeout(2000);
//			Thread.sleep(5000);
			//socket.close();
			readDataBufferTool(socket.getInputStream(), 16);
			readDataBufferTool(socket.getInputStream(), 300);
//			readDataEvtRecvBufferTool(socket.getInputStream(), 16);
			System.out.println();
			
				System.out.println("" + new Date().toLocaleString());
				Thread.sleep(500);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static Object readDataEvtRecvBufferTool(InputStream inputStream,int messageLength) throws IOException {
		EvtRecvBufferTool bufferTool=new EvtRecvBufferTool(inputStream,16);

		DataUnit dataUnit=new DataUnit(StaticFinals.INT,-1);
		bufferTool.read(dataUnit);
		System.out.println("1> "+dataUnit.getLongData());
		
		dataUnit=new DataUnit(StaticFinals.SHORT,-1);
		bufferTool.read(dataUnit);
		System.out.println("2> "+dataUnit.getLongData());

		
		dataUnit=new DataUnit(StaticFinals.SHORT,-1);
		bufferTool.read(dataUnit);
		System.out.println("3> "+dataUnit.getLongData());
		
		dataUnit=new DataUnit(StaticFinals.SHORT,-1);
		bufferTool.read(dataUnit);
		System.out.println("4> "+dataUnit.getLongData());
		
		dataUnit=new DataUnit(StaticFinals.SHORT,-1);
		bufferTool.read(dataUnit);
		System.out.println(" 5>" + dataUnit.getLongData());
		
		dataUnit=new DataUnit(StaticFinals.SHORT,-1);
		bufferTool.read(dataUnit);
		System.out.println(" 6>" + dataUnit.getLongData());
		
		dataUnit=new DataUnit(StaticFinals.SHORT,-1);
		bufferTool.read(dataUnit);
		System.out.println(" 7>" +  dataUnit.getLongData());
		
		return null;
	}
	
	public static Object readDataBufferTool(InputStream inputStream,int messageLength) throws IOException {
		BufferTool bufferTool=new BufferTool(inputStream, messageLength);

		DataUnit dataUnit=new DataUnit(StaticFinals.INT,-1);
		bufferTool.read(dataUnit);
		System.out.println("1> "+dataUnit.getLongData());
		
		dataUnit=new DataUnit(StaticFinals.SHORT,-1);
		bufferTool.read(dataUnit);
		System.out.println("2> "+dataUnit.getLongData());

		
		dataUnit=new DataUnit(StaticFinals.SHORT,-1);
		bufferTool.read(dataUnit);
		System.out.println("3> "+dataUnit.getLongData());
		
		dataUnit=new DataUnit(StaticFinals.SHORT,-1);
		bufferTool.read(dataUnit);
		System.out.println("4> "+dataUnit.getLongData());
		
		dataUnit=new DataUnit(StaticFinals.SHORT,-1);
		bufferTool.read(dataUnit);
		System.out.println(" 5>" + dataUnit.getLongData());
		
		dataUnit=new DataUnit(StaticFinals.SHORT,-1);
		bufferTool.read(dataUnit);
		System.out.println(" 6>" + dataUnit.getLongData());
		
		dataUnit=new DataUnit(StaticFinals.SHORT,-1);
		bufferTool.read(dataUnit);
		System.out.println(" 7>" +  dataUnit.getLongData());
		
		return null;
	}

}
