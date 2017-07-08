package lang;


/**
 * @author HMI-Lenovo
 *
 */
public class OxIntegerBinaryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		printIntegerBinary();
//		printOxBinary();
		testBitOperate(1000000, 3);
	}
	
	private static void testBitOperate(int fieldNumber, int type) {
		int value = (fieldNumber << 3) | type;
		System.out.println("init value:"+value + "  " + Integer.toBinaryString(value));
		System.out.println();
		while (true) {
		      if ((value & ~0x7F) == 0) {
		    	  writeRawByte(value);
		        return;
		      } else {
		    	int a = value & 0x7F;
		  		System.out.println(a + "  " + Integer.toBinaryString(0x7F) + "  " + Integer.toBinaryString(a));
		  		a |= 0x80;
		  		System.out.println(a + " " + Integer.toBinaryString(0x80) + "  " + Integer.toBinaryString(a));
		        writeRawByte(a);
		        value >>>= 7;
		      }
		    }
	

	}
	
	/**
	 * @param i
	 */
	private static void writeRawByte(int a) {
		System.out.println(a + " " +Integer.toBinaryString(a));
		System.out.println();
	}

	private static void printOxBinary(){
		System.out.println(0x7F + "  " + Integer.toBinaryString(~0x7F));
	}
	
	/**
	 * 测试16进制整数对应的二进制字符串
	 */
	private static void printIntegerBinary() {
		System.out.println(Integer.toBinaryString(0x00000001));
		System.out.println(Integer.toBinaryString(0x00000002));
		System.out.println(Integer.toBinaryString(0x00000004));
		System.out.println(Integer.toBinaryString(0x00000008));
		System.out.println(Integer.toBinaryString(0x00000010));
		System.out.println(Integer.toBinaryString(0x00000020));
		System.out.println(Integer.toBinaryString(0x00000040));
		System.out.println(Integer.toBinaryString(0x00000080));
		System.out.println(Integer.toBinaryString(0x00000100));
		System.out.println(Integer.toBinaryString(0x00000200));
		System.out.println(Integer.toBinaryString(0x00000400));
		System.out.println(Integer.toBinaryString(0x00000800));
	}

	
}
