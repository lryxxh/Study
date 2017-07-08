package lang;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
 class A2 {
	private String name = "";
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}

class A3 extends A2{
	@Override
	public void setName(String name) {
		super.setName(name);
	}
	
	@Override
	public String getName() {
		return super.getName();
	}
}

class A4 extends A2 {
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}
}
public class JavaBeanPropertyChange {
	PropertyChangeSupport support = new PropertyChangeSupport(new A2());
	
	private String text = "123";

	public String getText() {
		return text;
	}

	public void setText(String text) throws IntrospectionException {
		support.firePropertyChange("text", this.text , text);
		this.text = text;
		BeanInfo info = Introspector.getBeanInfo(A2.class);
		info.getPropertyDescriptors();
	}

	public JavaBeanPropertyChange() throws IntrospectionException {
		support.addPropertyChangeListener(new Test222());
		setText("3333");
		setText("4444");
	}
	
	public static void main(String[] args) throws IntrospectionException {
		new JavaBeanPropertyChange();
	}


}

class Test222 implements PropertyChangeListener{

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt.getPropagationId() +" " + evt.getPropertyName() +" " + evt.getNewValue() +" " + evt.getOldValue() +" "+ evt.getSource());
	}
	
}