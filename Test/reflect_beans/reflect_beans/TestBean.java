package reflect_beans;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestBean extends AbstractBean {
	private String a , b;
	public TestBean(String a, String b) {
		this.a = a;
		this.b = b;
	}

	public static void main(String[] args) {
		try {
			Class clazz = Class.forName("reflect_beans.TestBean");
			Constructor<AbstractBean> constructor = clazz.getConstructor(String.class, String.class);
			AbstractBean bean = constructor.newInstance("a", "b");
			bean.say("123", "345");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
}
