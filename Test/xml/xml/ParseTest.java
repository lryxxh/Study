package xml;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class ParseTest {
	
	public static void main(String[] args) {
		SAXBuilder builder =new SAXBuilder();
		try {
			Document document = builder.build("dynamicatribute.xml");
			Element element = document.getRootElement();
			Element deviceElement = element.getChild("Device");
			Element subElement = null;
			Element subElement2 = null;
			for(Object subObj : deviceElement.getChildren()) {
				subElement = (Element) subObj;
				for(Object subObj2 : subElement.getChildren()) {
					subElement2 = (Element) subObj2;
					System.out.println(subElement2.getValue() + "  ");
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
