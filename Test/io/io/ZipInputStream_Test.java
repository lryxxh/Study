package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipInputStream_Test {
	
	public static void main(String[] args) {
		try {
			ZipInputStream zis = new ZipInputStream(new FileInputStream(new File("C:/Users/HHD/bin/mmiexec_ok/temp/menueditor/mmiconsole.console.menu.zip")));
			System.out.println();
			ZipEntry entry = null;
			while((entry = zis.getNextEntry())!=null) {
				System.out.println(entry.getName() +"  "  + zis.available());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
