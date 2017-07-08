/**
 * 
 */
package meeting.com.lry.meeting.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


/**
 * 添加与会人员action
 * @author liurenyong
 *
 */
public class AddPartnerAction extends AbstractAction{

	/**序列id*/
	private static final long serialVersionUID = -6933063475874801649L;
	
	/**
	 * 
	 */
	public AddPartnerAction() {
		super(ActionTools.addActionName, ActionTools.addIcon);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
