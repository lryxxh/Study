package jmx;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import javax.management.*;

public class SimpleDynamic implements DynamicMBean {
	/*
	 * 私有变量
	 */
	private String state = "initial state";
	private int nbChanges = 0;
	private int nbResets = 0;
	private String dClassName = this.getClass().getName();
	private String dDescription = "Simple implementation of a dynamic MBean.";
	private MBeanAttributeInfo[] dAttributes = new MBeanAttributeInfo[2];
	private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
	private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[1];
	private MBeanInfo dMBeanInfo = null;

	public SimpleDynamic() {
		// 建立辅助信息
		buildDynamicMBeanInfo();
	}

	public Object getAttribute(String attribute_name) throws AttributeNotFoundException, MBeanException, ReflectionException {
		// 检查属性是否为空
		if (attribute_name == null) {
			throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke a getter of " + dClassName + " with null attribute name");
		}
		// 检查已知属性,调用已知方法
		if (attribute_name.equals("State")) {
			return getState();
		}
		if (attribute_name.equals("NbChanges")) {
			return getNbChanges();
		}
		// 如果属性不可识别,抛出异常
		throw (new AttributeNotFoundException("Cannot find " + attribute_name + " attribute in " + dClassName));
	}

	public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
		if (attribute == null) {
			throw new RuntimeOperationsException(new IllegalArgumentException("Attribute cannot be null"), "Cannot invoke a setter of " + dClassName + " with null attribute");
		}
		String name = attribute.getName();
		Object value = attribute.getValue();
		if (name == null) {
			throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke the setter of " + dClassName + " with null attribute name");
		}
		if (name.equals("State")) {
			if (value == null) {
				try {
					setState(null);
				} catch (Exception e) {
					throw (new InvalidAttributeValueException("Cannot set attribute " + name + " to null"));
				}
			} else {
				try {
					if ((Class.forName("java.lang.String")).isAssignableFrom(value.getClass())) {
						setState((String) value);
					} else {
						throw (new InvalidAttributeValueException("Cannot set attribute " + name + " to a " + value.getClass().getName() + " object, String expected"));
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		// 由于"NbChanges" 属性是只读的,所以抛出异常
		else if (name.equals("NbChanges")) {
			throw (new AttributeNotFoundException("Cannot set attribute " + name + " because it is read-only"));
		} else {
			throw (new AttributeNotFoundException("Attribute " + name + " not found in " + this.getClass().getName()));
		}
	}

	public AttributeList getAttributes(String[] attributeNames) {
		if (attributeNames == null) {
			throw new RuntimeOperationsException(new IllegalArgumentException("attributeNames[] cannot be null"), "Cannot invoke a getter of " + dClassName);
		}
		AttributeList resultList = new AttributeList();
		if (attributeNames.length == 0)
			return resultList;
		for (int i = 0; i < attributeNames.length; i++) {
			try {
				Object value = getAttribute((String) attributeNames[i]);
				resultList.add(new Attribute(attributeNames[i], value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (resultList);
	}

	public AttributeList setAttributes(AttributeList attributes) {
		if (attributes == null) {
			throw new RuntimeOperationsException(new IllegalArgumentException("AttributeList attributes cannot be null"), "Cannot invoke a setter of " + dClassName);
		}
		AttributeList resultList = new AttributeList();
		if (attributes.isEmpty())
			return resultList;
		for (Iterator i = attributes.iterator(); i.hasNext();) {
			Attribute attr = (Attribute) i.next();
			try {
				setAttribute(attr);
				String name = attr.getName();
				Object value = getAttribute(name);
				resultList.add(new Attribute(name, value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (resultList);
	}

	/**
	 * 设置操作
	 */
	public Object invoke(String operationName, Object params[], String signature[]) throws MBeanException, ReflectionException {
		// 检查方法名是否为空
		if (operationName == null) {
			throw new RuntimeOperationsException(new IllegalArgumentException("Operation name cannot be null"), "Cannot invoke a null operation in " + dClassName);
		}
		if (operationName.equals("reset")) {
			reset();
			return null;
		} else {
			throw new ReflectionException(new NoSuchMethodException(operationName), "Cannot find the operation " + operationName + " in " + dClassName);
		}
	}

	public MBeanInfo getMBeanInfo() {
		return (dMBeanInfo);
	}

	/*
	 * ----------------------------------------------------- 下面是公共方法
	 * -----------------------------------------------------
	 */
	public String getState() {
		return state;
	}

	public void setState(String s) {
		state = s;
		nbChanges++;
	}

	public Integer getNbChanges() {
		return new Integer(nbChanges);
	}

	public void reset() {
		state = "initial state";
		nbChanges = 0;
		nbResets++;
	}

	public Integer getNbResets() {
		return new Integer(nbResets);
	}

	/**
	 * 构造辅助信息,这里用了很多辅助类,具体看规范
	 */
	private void buildDynamicMBeanInfo() {
		dAttributes[0] = new MBeanAttributeInfo("State", "java.lang.String", "State: state string.", true, true, false);
		dAttributes[1] = new MBeanAttributeInfo("NbChanges", "java.lang.Integer", "NbChanges: number of times the State string has been changed.", true, false, false);
		Constructor[] constructors = this.getClass().getConstructors();
		dConstructors[0] = new MBeanConstructorInfo("SimpleDynamic(): Constructs a SimpleDynamic object", constructors[0]);
		MBeanParameterInfo[] params = null;
		dOperations[0] = new MBeanOperationInfo("reset", "reset(): reset State and NbChanges attributes to their initial values", params, "void", MBeanOperationInfo.ACTION);
		dMBeanInfo = new MBeanInfo(dClassName, dDescription, dAttributes, dConstructors, dOperations, new MBeanNotificationInfo[0]);
	}
}