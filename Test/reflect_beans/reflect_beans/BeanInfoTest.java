package reflect_beans;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;

import com.kedong.hmi.service.OodbServAgent;

public class BeanInfoTest {

	public static void main(String[] args) {
		// int a = Font.BOLD;
		// int b = Font.ITALIC;
		//
		// System.out.println(Font.BOLD | Font.ITALIC);
		//
		// System.out.println(a + "  " + b);
		// boolean isBold = false;
		// boolean isItalic = true;
		//
		// int x = b & 0x0;
		// int y = a & 0xFFFFFF;
		// System.out.println(x + " " + y + "  :" + (x | y));

		// System.out.println(Long.MAX_VALUE);
//		PropertyEditorManager manager = new PropertyEditorManager();
//		manager.registerEditor(Vector.class, TT.class);
//		PropertyEditor editor = manager.findEditor(Vector.class);
//		System.out.println(editor.getCustomEditor());
		Class<OodbServAgent> clazz = OodbServAgent.class;
		try {
			BeanInfo beaninfo = Introspector.getBeanInfo(clazz);
			MethodDescriptor methodDescriptors[]= beaninfo.getMethodDescriptors();
			for(MethodDescriptor descriptor : methodDescriptors) {
				System.out.println(descriptor.getMethod().getAnnotations());
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}

	

}
