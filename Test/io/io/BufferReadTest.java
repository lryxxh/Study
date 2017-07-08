package io;
import java.io.IOException;


public class BufferReadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec("javaws -uninstall");
			Runtime.getRuntime().exec("javaws -import -silent http://192.168.200.231/picbrowser.jnlp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("123");
//		String a = "abc";
//		String b = "abc";
//		String c = new String("abc");
//		String d = new String("abc");
//		System.out.println((a == b) + " " +( c == d) );
//		HashMap<Integer, String> map = new HashMap<Integer, String>(); 
//		map.put(1, a);
//		map.put(2, b);
//		System.out.println(map.get(1) == map.get(2));
		
//		ByteBuffer buffer = ByteBuffer.allocate(25);
//		System.out.println(buffer);
//		buffer.putInt(24);//4
//		System.out.println(buffer);
//		buffer.putDouble(123.4);//8
//		System.out.println(buffer);
//		buffer.putLong(23l);//8
//		System.out.println(buffer);
//		buffer.put((byte)1);//1
//		System.out.println(buffer);
//		buffer.putFloat(123.4f);//4
//		System.out.println(buffer);
//		buffer.flip();
//		System.out.println(buffer.getInt());
//		System.out.println(buffer.getDouble());
//		System.out.println(buffer.getLong());
//		System.out.println(buffer.get());
//		System.out.println(buffer.getFloat());
		
		
	}

}
