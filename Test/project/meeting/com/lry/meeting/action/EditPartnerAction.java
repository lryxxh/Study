/**
 * 
 */
package meeting.com.lry.meeting.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


/**
 * 编辑与会人员action.
 * @author 
 *
 */
public class EditPartnerAction extends AbstractAction{

	/**序列id*/
	private static final long serialVersionUID = 6443398007375219143L;
	
	/**
	 * 
	 */
	public EditPartnerAction() {
		super(ActionTools.editActionName, ActionTools.editIcon);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
