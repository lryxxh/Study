/**
 * 
 */
package meeting.com.lry.meeting.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


/**
 * @author ǩ��ȷ��action
 *
 */
public class SignatureAction extends AbstractAction{

	/**����id*/
	private static final long serialVersionUID = 1941948077172338581L;

	/**
	 * 
	 */
	public SignatureAction() {
		super(ActionTools.signatureActionName, ActionTools.signatureIcon);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
