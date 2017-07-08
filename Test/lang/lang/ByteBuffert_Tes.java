/**
 * 
 */
package lang;

import java.nio.ByteBuffer;

/**
 * @author HMI-Lenovo
 *
 */
public class ByteBuffert_Tes {
	
	public static void main(String[] args) {
//		byte[] tt = new byte[]{ -69,-86, -42, -48 };
//		ByteBuffer buffer = ByteBuffer.allocate(4);
//		buffer = buffer.wrap(tt);
//		buffer.order(ByteOrder.LITTLE_ENDIAN);
//		System.out.println(buffer.getInt());
		ByteBuffer buffer = ByteBuffer.allocate(4);
//		buffer = buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.putInt(3);
		for(byte tmp: buffer.array()){ 
			System.out.println(tmp);
		}
	}

}
