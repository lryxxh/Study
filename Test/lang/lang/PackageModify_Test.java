/**
 * 
 */
package lang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HMI-Lenovo
 * 
 */
public class PackageModify_Test {

	public static void main(String[] args) {
		File file = new File("E:/Work/eclipse_test/WorkSpace/设计模式/src/");
		modifyPackageName(file);
		System.out.println();
	}

	private static String getPackage(String fileName) {
		String packageName = fileName.split("src")[1].replace("\\", ".");

		packageName = packageName.substring(1);
		System.out.println(packageName);

		return "package " + packageName + ";";
	}

	private static void modifyPackageName(File file) {
		if (!file.isDirectory() && file.getName().endsWith(".java")) {
			try {
				List<String> lines = new ArrayList<String>();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(new FileInputStream(file)));
				String line = reader.readLine();// 第一行读包名
				String packageName = getPackage(file.getParent());
				while ((line = reader.readLine()) != null) {
					lines.add(line);
				}
				lines.add(0, packageName);
				reader.close();
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(file)));
				for (String newline : lines) {
					writer.write(newline);
					writer.newLine();
				}
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (file.isDirectory()) {
			for (File tempFile : file.listFiles()) {
				modifyPackageName(tempFile);
			}
		}
	}

}
