/**
 * 
 */
package swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
		propertyField = new JTextField(20);
		propertyField.setText(propertyValue);
		initComponent();
	}

	/**
	 * 
	 */
	private void initComponent() {
//		this.setPreferredSize(new Dimension(300, 35));
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		setBackground(Color.yellow);
		setLayout(gridbag);
		
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridbag.setConstraints(propertyLabel, gridBagConstraints);
		propertyLabel.setBackground(Color.red);
		propertyLabel.setPreferredSize(new Dimension(80, 30));
		propertyLabel.setOpaque(true);
		propertyLabel.getInsets(new Insets(0, 0, 0, 50));
		propertyLabel.setHorizontalAlignment(SwingUtilities.RIGHT);
		add(propertyLabel);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 1;
		gridbag.setConstraints(propertyField, gridBagConstraints);
		propertyField.setBackground(Color.GREEN);
		add(propertyField);
		
		
	}
	
}
