package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ioMoreTest {
	static List<String> list = new ArrayList<String>();
	static HashMap<String, ArrayList<String>> empty = new HashMap<String, ArrayList<String>>();
	static List<String> iconTypeStirng = new ArrayList<String>();
	static {
		File file = new File("F:/Work/eclipse - Test/workspace/new_mmi_0802/prjs/gparser/src/com/kedong/platform/graph/parser/cimg/");
		File files[] = file.listFiles();
		for(File tempFile : files) {
			if (tempFile.isFile() && tempFile.getName().endsWith(".java")){
				list.add(tempFile.getName().replace(".java",""));
			}
		}
	}
	public static void main(String[] args) throws IOException {
		File file = new File("C:/Users/LRY/Desktop/fac");
		printDontExitsFile(file);
		System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		Iterator item = empty.keySet().iterator();
		while (item.hasNext()) {
			String string = (String) item.next();
			ArrayList<String> type = empty.get(string);
			System.out.println(string + "  " + type);
		}
	}

	private static void printDontExitsFile(File file) throws IOException {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			int i=0;
			for (File subFile : files) {
				i++;
				System.err.println(i + " ======================================================" + file.getName());
				printDontExitsFile(subFile);
			}
		} else {
			if (!file.getName().endsWith(".g")) {
				return;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = "";
			while ((line = reader.readLine())!= null) {
				if (!line.trim().startsWith("<?xml version") && !line.trim().startsWith("</") && !line.trim().startsWith("<Lay")) {
					boolean isExits = false;
					for (String name : list){
						String typeName = line.trim().replace("<", "").split(" ")[0];
						if (typeName.equals(name)) {
							isExits = true;
							break;
						}
					}
					if (!isExits && null != line && !line.trim().equals("")) {
						try {
							String[] splitarrayStrings = line.trim().split(" ");
							if (splitarrayStrings.length >= 1) {
								if (iconTypeStirng.contains(splitarrayStrings[0].substring(1))) {
									continue;
								} else {
									iconTypeStirng.add(splitarrayStrings[0].substring(1));
								}
								ArrayList<String> typeList = empty.get(file.getName());
								if (typeList == null) {
									typeList = new ArrayList<String>();
									typeList.add(splitarrayStrings[0].substring(1).replace(">", ""));
									empty.put(file.getName(), typeList);
								} else {
									typeList.add(splitarrayStrings[0].substring(1).replace(">", ""));
								}
								System.out.println(splitarrayStrings[0].substring(1));
							} 
						} catch (Exception e) {
							e.printStackTrace();
							System.err.println(line +"   " + file.getName());
						}
						
					}
				}
				
			}
		}
	}

}
