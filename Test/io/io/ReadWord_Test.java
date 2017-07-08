/**
 * 
 */
package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author HMI-Lenovo
 *
 */
public class ReadWord_Test {
	
	public static void main(String[] args) {
		BufferedReader reader = null;
		InputStreamReader inputStreamReader = null;
		FileInputStream fileInputStream =  null;
		try {
			inputStreamReader = new InputStreamReader(new FileInputStream("bin/io/1.txt"), "GBK");
			reader = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
