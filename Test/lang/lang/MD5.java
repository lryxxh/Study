package lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;

public class MD5 {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	// 将字节数组转换为十六进制字符串
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	// 将字节转换为十六进制字符
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			// MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法
			MessageDigest md = MessageDigest.getInstance("MD5");
//			resultString = byteArrayToHexString(temp);
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes("UTF-8")));
			
		} catch (Exception ex) {
		}
		return resultString;
	}
	
	public static void main(String[] args) {
		File file = new File("link_conf.CIME");
		System.out.println("length " + file.length());
		byte[] arrays = new byte[(int) file.length()];
		FileInputStream fis = null;
		try {
			 fis = new FileInputStream("link_conf.CIME");
			fis.read(arrays);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(byte bytesss : arrays) {
			System.out.print(bytesss + " ");
		}
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(file));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		char[] chars = new char[5000000]; 
		int leng = 0;
		try {
			leng = reader.read(chars);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		chars= Arrays.copyOfRange(chars, 0, leng);
		byte[] ss = new byte[chars.length * 2]; 
		int index = 0;
		byte tempCharByte =0;
		ByteBuffer buffer = ByteBuffer.allocate(2);
		for(char tempChar : chars) {
			buffer.putChar(tempChar);
			buffer.array();
		}
		
		String stringwww = new String(chars);
		try {
			System.err.println("====="+stringwww.getBytes().length);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		System.out.println("old +   :" + new String(Arrays.copyOfRange(arrays, 0, 16)));
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
//			String tt = MD5.MD5Encode(new String(Arrays.copyOfRange(arrays, 16, arrays.length)));
			byte[] tt = md.digest(Arrays.copyOfRange(arrays, 16, arrays.length));
			System.out.println("md5 == " + new String(tt));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String string = MD5.MD5Encode("abcdefghijklmnopqrstuvwxyz");
		System.err.println(string);
	}
	
}