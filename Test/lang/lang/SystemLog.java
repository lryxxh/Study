package lang;

import java.nio.charset.Charset;


public class SystemLog {
	
	public static void printMemory(String param) {
		Runtime runtime = Runtime.getRuntime();
		long totalMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		long maxMemory = runtime.maxMemory();
		System.out.println(param + "maxMemory =" + maxMemory + " totalMemory =" + totalMemory + " curMemory =" + (totalMemory - freeMemory));
	}
	
	public static void main(String[] args) {
//	    int a = -16777213;
//	    System.out.println(Integer.toBinaryString(a));
//	    System.out.println((byte)a);
//	    System.out.println((byte)(a>>8));
//	    System.out.println((byte)(a>> 16));
//	    System.out.println((byte)(a>>24));
	    
//	    System.out.println(new Date((long)1.378448143751E12));
	    System.out.println(Charset.defaultCharset());
    }

}