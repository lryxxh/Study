package io;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class BufferOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.putInt(264);
		ByteBuffer ttt = ByteBuffer.wrap(new byte[]{0,0,1,45});
		System.out.println(ttt.getInt());
//		for(byte tt : buffer.array()) {
//			System.out.println(tt);
//		}
	}

}
