package xml_encoder;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class XMLEncoder_Test {

	@Test
	public void testXMLEncoder() {
		XMLTest test = new XMLTest();
		test.put(1);
		test.put(2);
		test.put(3);
		List list2 = new ArrayList();
		list2.add(4);
		test.put(list2);
		XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(new FileOutputStream(new File("C:/Users/HHD/Desktop/Test11111111111.xml")));
			encoder.writeObject(test);
			encoder.flush();
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testXMLDecoder() {
		XMLDecoder decoder;
		try {
			decoder = new XMLDecoder(new FileInputStream(new File("C:/Users/HHD/Desktop/Test11111111111.xml")));
			XMLTest o = (XMLTest) decoder.readObject();
//			System.out.println(o.getList2());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
