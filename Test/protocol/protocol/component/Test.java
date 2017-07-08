package protocol.component;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@SuppressWarnings("unchecked")
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test test = new Test();
		test.parseDocument("DataReceiveMessage.xml");
	}

	/**
	 * 解析文件.
	 * 
	 * @param fileName
	 */
	private void parseDocument(String fileName) {
		Document document = createDocument(fileName);
		Element rootElement = document.getRootElement();
		parseMessageHead(rootElement.element("MessageHead"));
		parseMessageBody(rootElement.element("MessageBody"));
	}

	/**
	 * 创建文档.
	 * 
	 * @param fileName
	 * @return
	 */
	private Document createDocument(String fileName) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(Test.class.getResourceAsStream("DataReceiveMessage.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * 解析消息头.
	 * 
	 * @param rootElement
	 */
	private void parseMessageHead(Element rootElement) {

	}

	/**
	 * 解析消息体.
	 * 
	 * @param rootElement
	 */
	private void parseMessageBody(Element rootElement) {
		List<Element> elements = rootElement.elements();
		for (Element element : elements) {
			System.out.println(element.getName());
			parseElement(element);
		}
	}

	/**
	 * 解析元素.
	 * 
	 * @param element
	 */
	private void parseElement(Element element) {
		String attrName = element.getName();
		if ("Type".equals(attrName)) {
			parseTypeElement(element);
		} else if ("Loop".equals(attrName)) {
			parseLoopElement(element);
		} else if ("If".equals(element)) {
			parseIfElement(element);
		} else if ("Else".equals(element)) {
			parseElseElement(element);
		}
	}

	private void parseTypeElement(Element typeElement) {
		String name = typeElement.attributeValue("name");
		String desc = typeElement.attributeValue("desc");
		String type = typeElement.attributeValue("type");
		String length = typeElement.attributeValue("length");
		System.out.println("name:" + name + " desc:" + desc + " type:" + type + " length:" + length);
	}

	private void parseIfElement(Element ifElement) {
		List<Element> elements = ifElement.elements();
		for (Element element : elements) {
			parseElement(element);
		}
	}

	private void parseElseElement(Element elseElement) {
		List<Element> elements = elseElement.elements();
		for (Element element : elements) {
			parseElement(element);
		}
	}
	
	private void parseLoopElement(Element loopElement) {
		List<Element> elements = loopElement.elements();
		for (Element element : elements) {
			parseElement(element);
		}
	}

}
