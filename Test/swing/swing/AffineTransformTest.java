package swing;
import java.awt.Component;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.beans.PropertyEditorSupport;

import javax.swing.JComboBox;

public class AffineTransformTest extends PropertyEditorSupport {
	@Override
	public Component getCustomEditor() {
		JComboBox box = new JComboBox(new String[] { "1", "2", "3" });
		return box;
	}
	
	public static void main(String[] args) {
		AffineTransform transform = new AffineTransform();
		transform.scale(2.0, 2.0);
		transform.translate(100, 100);
		Point point = new Point();
		transform.transform(point,point);
		System.out.println(transform +"  "+ point);
		
		transform.setToIdentity();
		transform.translate(-300, -300);
		transform.scale(2.5, 2.5);
		point = new Point(200,200);
		transform.transform(point, point);
		System.err.println(transform +"  "+ point);
		
		transform.setToIdentity();
		transform.translate(-500, -500);
		transform.scale(3.5, 3.5);
		point = new Point(200,200);
		transform.transform(point, point);
		System.out.println(transform +"  "+ point);
		
		
	}
}
