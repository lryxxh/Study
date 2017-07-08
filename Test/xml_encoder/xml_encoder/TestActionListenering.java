package xml_encoder;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class TestActionListenering extends AbstractAction{
	public TestActionListenering(String str) {
		super(str);
	}
	
	public TestActionListenering() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("HelloWorld!");
	}
	
}
