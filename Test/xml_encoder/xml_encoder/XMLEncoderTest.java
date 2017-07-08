package xml_encoder;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.Statement;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;



public class XMLEncoderTest extends TestBean{

	public static void main(String[] args) {
		SubTestBean bean = new SubTestBean();
		bean.addTest("ew");
		bean.notest();
		TestBean testBean = new TestBean();
		testBean.setAddress("���");
		testBean.setAge(24);
		testBean.setName("����");
		bean.addTest(0,testBean);
		bean.setAddress("�ӱ�ʡ�е���");
		bean.setAge(23);
		bean.setName("����");
		bean.setgAddress("�ӱ�ʡ");
		bean.setgAge(22);
		bean.setgName("����");
		bean.testMethod("����");
		try {
			java.beans.XMLEncoder encoder = new XMLEncoder(new FileOutputStream(new File("C:/Users/LRY/Desktop/1.xml")));
			encoder.writeObject(bean);
			encoder.setPersistenceDelegate(SubTestBean.class, new PersistenceDelegate() {
				
				@Override
				protected Expression instantiate(Object oldInstance, Encoder out) {
					out.writeStatement(new Statement(oldInstance, "customMethod", new Object[]{}));
					return null;
				}
			});
			encoder.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

