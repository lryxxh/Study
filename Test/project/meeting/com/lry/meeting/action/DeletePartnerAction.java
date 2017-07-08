/**
 * 
 */
package meeting.com.lry.meeting.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


/**
 * 删除与会人员action
 * @author liurenyong
 *
 */
public class DeletePartnerAction extends AbstractAction{

	/**序列id*/
	private static final long serialVersionUID = -2216931912080621490L;

	/**
	 * 
	 */
	public DeletePartnerAction() {
		super(ActionTools.deleteActionName, ActionTools.deleteIcon);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
