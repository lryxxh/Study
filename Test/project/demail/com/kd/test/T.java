package demail.com.kd.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import demail.com.kd.dmail.tool.DateUtils;

public class T {
	public static void main(String[] args) {
		String d1 = DateUtils.getDate("day");
		String d2 = DateUtils.getDate("week");
		System.out.println(d1 + "  "+d2);
		System.out.println(d2.compareTo(d1));
		// try {
		// FileOutputStream out = new FileOutputStream("DMail_2012-5-11.eh");
		// out.write("123".getBytes());
		// for(int i = 0;i < 100;i++){
		// System.out.println(new java.util.Date().toLocaleString());
		// Thread.sleep(500);
		// }
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
