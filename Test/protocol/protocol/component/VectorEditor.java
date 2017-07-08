package protocol.component;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyEditorSupport;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VectorEditor extends PropertyEditorSupport {

	private JComponent panel = null;
	private JPanel centerPanel = null;

	private Vector data = null;

	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#getCustomEditor()
	 */
	@Override
	public Component getCustomEditor() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.add(getRightButtonPanel(),BorderLayout.EAST);
			panel.add(getCenterPanel(), BorderLayout.CENTER);
		}
		return panel;
	}
	
	private JPanel getCenterPanel() {
		if(centerPanel == null) {
			centerPanel = new JPanel();
//			centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
			centerPanel.setBorder(BorderFactory.createEtchedBorder());
		}
		return centerPanel;
	}
	
	private JPanel getRightButtonPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createVerticalStrut(5));
		panel.add(getAddButton());
		panel.add(Box.createVerticalStrut(5));
		panel.add(getDeleteButton());
		return panel;
	}

	private Component getDeleteButton() {
		JButton deleteButton = new JButton("-");
		deleteButton.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		return deleteButton;
	}

	private JButton getAddButton() {
		JButton addbutton = new JButton("+");
		addbutton.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		addbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getCenterPanel().add(addNewItem());
				getCenterPanel().updateUI();
			}
		});
		return addbutton;
	}

	private JTextField addNewItem() {
		JTextField textField = new JTextField(20);
		return textField;
	}

	@Override
	public Object getValue() {
		return data;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		VectorEditor editor = new VectorEditor();
		Component component = editor.getCustomEditor();
		frame.getContentPane().add(component);
		frame.setSize(800,600);
		frame.setVisible(true);
	}
}
