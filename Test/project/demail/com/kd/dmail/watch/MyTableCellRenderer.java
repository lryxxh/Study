package demail.com.kd.dmail.watch;

import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import demail.com.kd.dmail.consts.DMailConstants;

/**
 * 
 * @author xuzhiqi
 */
class MyTableCellRenderer implements TableCellRenderer {
	Map<String, String> list = null;

	public MyTableCellRenderer(HashMap<String, String> list) {
		this.list = list;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// if (row == 1) {
		String image = list.get(row);
		if (image == null)
			image = "red.png";
		ImageIcon imageicon = new ImageIcon(DMailConstants.imageFilePath
				+ image);
		imageicon = new ImageIcon(imageicon.getImage().getScaledInstance(20,
				20, Image.SCALE_DEFAULT));
		// icon = icon.getImage().get
		JLabel label = new JLabel(imageicon);
		label.setOpaque(false);
		return label;
		// } else {
		// ImageIcon imageicon = new ImageIcon(DMailConstants.imageFilePath
		// + "green.png");
		// imageicon = new ImageIcon(imageicon.getImage().getScaledInstance(
		// 20, 20, Image.SCALE_DEFAULT));
		// // icon = icon.getImage().get
		// JLabel label = new JLabel(imageicon);
		// label.setOpaque(false);
		// return label;
		// }
		// return null;
	}
}