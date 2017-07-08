package lang;
import java.awt.AWTEvent;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 
 */

/**
 * @author HMI-Lenovo
 *
 */
public class BitOper_Test {
	
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(959447040);
		byte[] bytes = buffer.array();
		
		for(byte temp : bytes) {
			System.out.print(temp + " ");
		}
		buffer = ByteBuffer.wrap(new byte[]{57, 48, 0, 0});
		buffer.order(ByteOrder.BIG_ENDIAN);
		System.out.println(buffer.getInt());
		
//		System.err.println("==="+Integer.toBinaryString(1));
//
//		System.err.println("----"+Integer.toBinaryString(-1));
//		System.out.println(Integer.toBinaryString(0xff));
//
//		System.out.println(-128 & 0xFF);
//		int mask = 4104;
//		int a = 0;
//		a = 1 << a;
//		System.out.println(9 & a);
//		System.out.println(Integer.toBinaryString(mask));
//		System.err.println(Integer.toBinaryString(251658570));
//		long motionMask = mask & AWTEvent.MOUSE_MOTION_EVENT_MASK;
//		long wheelMask = mask & AWTEvent.MOUSE_WHEEL_EVENT_MASK;
//		long mouseMask = mask & AWTEvent.MOUSE_EVENT_MASK;
//		System.out.println(motionMask + " " + mouseMask + " " + wheelMask);
//		boolean value = motionMask != 0 || mouseMask != 0 || wheelMask != 0;
//		System.out.println(value);
	}
}
