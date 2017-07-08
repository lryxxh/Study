/**
 * Binary_Test.java
 * Created by liurenyong at 2013-11-14
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;


/**
 * 
 * @author liurenyong 2013-11-14
 */
public class Binary_Test {

	static int test() {
		int x = 0;
		int y = 0;
		try{
			x = 5;
			y = 6;
			return x;
		}finally {
			x++;
			x = 7;
		}
	}
	public static void main(String[] args) {
		System.out.println(test());
		System.out.println(Integer.toBinaryString(1));
		System.out.println(Integer.toBinaryString(-1));
//		boolean flag = true;
//		boolean temp = true;
//		int a,b,c,d=2;
//		a = 1;
//		System.out.println(b=(a++)+a);
//		System.out.println(c=d);
//		a=b=c=d;
//		System.out.println();
//		System.out.println((flag = false) && (temp = false));
//		System.out.println("flag:" + flag);
//		System.out.println("temp:" + temp);

	}

}
