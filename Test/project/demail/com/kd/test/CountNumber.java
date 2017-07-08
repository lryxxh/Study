package demail.com.kd.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CountNumber {
	public static void main(String args[]) {
//		 File f = new File("/home/d5000/workspace/DMail1.3.1/src/");
//		File f = new File("/home/d5000/workspace/jgraphx_now_0331/src");// 123128
//		 File f = new File("/home/d5000/workspace/multiCoreMVC/src");//2789||979
//		 File f = new File("/home/d5000/workspace/mvc/src");//3149||
		 File f= new File("/home/d5000/workspace/DMail1.7/src");
		read(f);
		int ss = 0;
		for (String lis : list) {
			System.out.println(lis);
			ss += (Integer.valueOf(lis.split("=")[1]));
		}
		System.out.println("共计" + list.size() + "个类，" + ss + "代码");
	}

	public static ArrayList<String> list = new ArrayList<String>();

	public static void read(File f) {
		// 如果是目录
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (File file : files)
				read(file);
		} else {
			if (f.getName().endsWith(".java")) {
				count(f);
			}
		}
	}

	public static void count(File f) {
		int count = 0;
		InputStream input = null;
		InputStreamReader reader = null;
		BufferedReader br = null;
		try {
			input = new FileInputStream(f);
			reader = new InputStreamReader(input);
			br = new BufferedReader(reader);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.trim().startsWith("*")
						&& !line.trim().startsWith("/"))
					count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list.add(f.getName() + "=" + count);
	}
}
