/**
 * 
 */
package meeting.com.lry.meeting.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author liurenyong
 *
 */
public class LabelTextFieldPanel extends JPanel{

	private JLabel propertyLabel = null;
	
	private JTextField propertyField = null;
	
	public LabelTextFieldPanel(String propertyName) {
		this(propertyName, "");
	}
	
	public LabelTextFieldPanel(String propertyName, String propertyValue) {
		propertyLabel = new JLabel(propertyName);
		propertyField = new JTextField(50);
		propertyField.setText(propertyValue);
	}
	
}
