package swing;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureDnd {
	JFrame mainFrame;
	JPanel mainPanel;
	PictureComponent[] pictures;

	public static void main(String[] args) {
		new PictureDnd();
//		try {
//			BeanInfo beanInfo = Introspector.getBeanInfo(tDnd.getClass());
//			System.out.println(beanInfo.getBeanDescriptor());
//			MethodDescriptor[] methodDescriptor = beanInfo.getMethodDescriptors();
//			PropertyDescriptor[] propertyDescriptor = beanInfo.getPropertyDescriptors();
//			for (MethodDescriptor method : methodDescriptor) {
//				System.out.println(method.getDisplayName());
//				System.out.println(Modifier.toString(method.getMethod().getModifiers()));
//				ParameterDescriptor[] params = method.getParameterDescriptors();
//				if (params != null) {
//					for (ParameterDescriptor parameterDescriptor : params) {
//						System.out.println("===="+parameterDescriptor.getDisplayName());
//					}
//				}
//			}
//			
//			for (PropertyDescriptor property : propertyDescriptor) {
//				System.out.println(property.getDisplayName());
//				System.out.println(property.getName());
//				System.out.println(property.getShortDescription());
//				System.out.println(property.getPropertyType());
//			}
////			System.out.println(beanInfo.getPropertyDescriptors());
//		} catch (IntrospectionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void tst(final String a) {
		
	}

	public PictureDnd() {
		mainFrame = new JFrame();
		mainPanel = new JPanel(new GridLayout(2, 2));
		pictures = new PictureComponent[4];
		pictures[0] = new PictureComponent(
				new ImageIcon("close.jpg").getImage());
		pictures[1] = new PictureComponent(
				new ImageIcon("query.jpg").getImage());
		pictures[2] = new PictureComponent(null);
		pictures[3] = new PictureComponent(null);

		mainPanel.add(pictures[0]);
		mainPanel.add(pictures[1]);
		mainPanel.add(pictures[2]);
		mainPanel.add(pictures[3]);

		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(350, 400);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}