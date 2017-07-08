package swing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.DefaultRowSorter;
import javax.swing.table.TableModel;

public class CustomRowSorter extends DefaultRowSorter<TableModel, Integer>{

	
	public static void main(String[] args) {
		PrintStream printStream = null;
		try {
			printStream = new PrintStream(new File("C:/Users/HHD/Desktop/1.txt"));
			System.setOut(printStream);
			String srtString = "12313 ss  ee ";
			String lines[] = srtString.split(" ");
			System.out.println(lines);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
