/**
 * PropertyChangeListener_Test.java
 * Created by liurenyong at 2013-9-6
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * 
 * @author liurenyong 2013-9-6
 */
public class PropertyChangeListener_Test implements PropertyChangeListener {

	Hashtable<String, Object> changes = new Hashtable<String, Object>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		changes.put(evt.getPropertyName(), evt.getNewValue());
		System.out.println(evt.getPropertyName() + "  " + evt.getOldValue()
				+ " " + evt.getNewValue());
	}

}

class PropertyBean {
	PropertyChangeSupport support = new PropertyChangeSupport(this);

	private String name = "里斯";

	private int age = 24;

	public void setAge(int age) {
		int oldAge = this.age;
		this.age = age;
		support.firePropertyChange("age", oldAge, this.age);
	}

	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		support.firePropertyChange("name", oldName, this.name);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	public static void main(String[] args) {
		PropertyChangeListener_Test test = new PropertyChangeListener_Test();
		PropertyBean bean = new PropertyBean();
		bean.addPropertyChangeListener(test);
		bean.setAge(25);
		bean.setName("张三");
		bean.age = 1;
		bean.name = "1";

		System.out.println("before " + bean.name + "  " + bean.age);

		// try {
		// BeanInfo info = Introspector.getBeanInfo(bean.getClass());
		// PropertyDescriptor[] propertyDescriptors =
		// info.getPropertyDescriptors();
		// for(PropertyDescriptor propertyDescriptor : propertyDescriptors) {
		// if(test.changes.keySet().contains(propertyDescriptor.getName())) {
		// propertyDescriptor.getWriteMethod().invoke(bean,
		// test.changes.get(propertyDescriptor.getName()));
		// }
		// }
		// System.out.println(bean.name + "  " + bean.age);
		// } catch (IntrospectionException e1) {
		// e1.printStackTrace();
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// e.printStackTrace();
		// }

		Iterator<String> properties = test.changes.keySet().iterator();
		String propertyName = "";
		while (properties.hasNext()) {
			try {
				propertyName = properties.next();
				bean.getClass().getDeclaredField(propertyName)
						.set(bean, test.changes.get(propertyName));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(bean.age + "  " + bean.name);
		}
	}
}