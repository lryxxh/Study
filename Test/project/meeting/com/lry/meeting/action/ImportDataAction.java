/**
 * 
 */
package meeting.com.lry.meeting.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


/**
 * �������빦��.
 * @author liurenyong
 *
 */
public class ImportDataAction extends AbstractAction{

	/**����id*/
	private static final long serialVersionUID = 5047362420097181055L;
	
	/**
	 * 
	 */
	public ImportDataAction() {
		super(ActionTools.importActionName, ActionTools.importIcon);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
