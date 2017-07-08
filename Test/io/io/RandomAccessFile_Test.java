/**
 * RandomAccessFile_Test.java
 * Created by liurenyong at 2013-8-16
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author liurenyong 2013-8-16
 */
public class RandomAccessFile_Test {
	

	public static void main(String[] args) throws IOException, InterruptedException {
		class ExtendObject extends Object {
			 final int a =0;
			 
			 int[] array = null;
			 InnerObject oo = null;
			 Object obj = null;
			 class InnerObject {
				 
			 }
		}
		List list = new ArrayList();
		for(int i =0;i < 10000;i++) {
				list.add(new ExtendObject());
				Thread.sleep(1000);
		}
		/*
		StringBuffer string = new StringBuffer();
		string.append("import java.util.ArrayList;\r");
		string.append("import java.util.HashMap;\r");
		string.append("import java.util.Collections;\r");
		RandomAccessFile file = new RandomAccessFile(
				"C:/Users/HMI-Lenovo/Desktop/log4j.properties", "rw");
		// MappedByteBuffer是特殊的直接缓冲器.必须指定文件的开始位置和文件大小.
		MappedByteBuffer out = file.getChannel().map(
				FileChannel.MapMode.READ_WRITE, 1, string.length());
		// for(int i=0; i<string.length(); i++){
		ByteBuffer buffer = ByteBuffer.allocate(string.length());
		buffer.put(string.toString().getBytes());
		out.put(buffer);
		// out.put(string.toString().getBytes());
		// }

		out.force();
		file.getChannel().close();
		System.out.println("Finish writing.");
		// for(int i = length/2; i<(length/2 + 6); i++){
		// System.out.println((char)out.get(i));
		// }
		// file.getChannel().force(true);
		file.close();
	*/}

	/**
	 * 
	 * @param args
	 * @author liurenyong 2013-8-16
	 */
	// public static void main(String[] args) {
	// File file = new File("C:/Users/HMI-Lenovo/Desktop/log4j.properties");
	// try {
	// RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
	// StringBuffer string = new StringBuffer();
	// string.append("import java.util.ArrayList;\r");
	// string.append("import java.util.HashMap;\r");
	// string.append("import java.util.Collections;\r");
	// long old_length = randomAccessFile.length();
	// System.out.println(string.length() + " =========== oldLength " +
	// old_length + "  fileLength " +file.length());
	// randomAccessFile.seek(0);
	// // randomAccessFile.setLength(file.length() + string.length());
	// randomAccessFile.writeBytes(string.toString());
	// randomAccessFile.close();
	// System.out.println(file.length());
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// // String fileName = "C:/Users/liurenyong/Desktop/log4j.properties";
	// // long skip = 50;
	// // String str = "hello World ";
	// // beiju(skip, str, fileName);
	//
	// }

	public static void beiju(long skip, String str, String fileName) {
		try {
			RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
			if (skip < 0 || skip > raf.length()) {
				System.out.println("跳过字节数无效");
				return;
			}
			byte[] b = str.getBytes();
			raf.setLength(raf.length() + b.length);
			for (long i = raf.length() - 1; i > b.length + skip - 1; i--) {
				raf.seek(i - b.length);
				byte temp = raf.readByte();
				raf.seek(i);
				raf.writeByte(temp);
			}
			raf.seek(skip);
			raf.write(b);
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
