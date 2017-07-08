/**
 * 
 */
package meeting.com.lry.meeting.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


/**
 * 批量导出功能action.
 * @author liurenyong
 *
 */
public class ExportDataAction extends AbstractAction{

	/**序列id*/
	private static final long serialVersionUID = -5489219300579262859L;

	/**
	 * 
	 */
	public ExportDataAction() {
		super(ActionTools.exportActionName, ActionTools.exportIcon);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
