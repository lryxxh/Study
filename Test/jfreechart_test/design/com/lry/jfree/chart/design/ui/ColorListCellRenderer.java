package design.com.lry.jfree.chart.design.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class ColorListCellRenderer extends JLabel implements ListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -646426167540804989L;
	private Color color = null;

	public ColorListCellRenderer() {
		setOpaque(true);
	}

	@SuppressWarnings("rawtypes")
	public Component getListCellRendererComponent(JList listbox, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		if (UIManager.getLookAndFeel().getName().equals("CDE/Motif")) {
			if (index == -1)
				setOpaque(false);
			else
				setOpaque(true);
		} else
			setOpaque(true);
		if (value == null) {
		} else if (isSelected) {
			setBackground(UIManager.getColor("ComboBox.selectionBackground"));
			setForeground(UIManager.getColor("ComboBox.selectionForeground"));
		} else {
			setBackground(UIManager.getColor("ComboBox.background"));
			setForeground(UIManager.getColor("ComboBox.foreground"));
		}
		Map map = (Map) value;
		Color color = (Color) map.values().iterator().next();
		String key = (String) map.keySet().iterator().next();
		this.setText(key);
		this.setHorizontalAlignment(SwingConstants.LEFT);
		this.color = color;
		return this;
	}

	private String getEmptyString() {
		StringBuffer buffer = new StringBuffer("    ");
		FontMetrics metrics = getFontMetrics(getFont());
		int width = metrics.stringWidth(" ");
		int emptySize = getHeight() / width + 1;
		for(int i = 0; i < emptySize; i++) {
			buffer.append(" ");
		}
		return buffer.toString();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		setText(getEmptyString() + getText());
		super.paint(g);
		g.setColor(this.color);
		g.fillRect(2, 2, this.getHeight() - 4, this.getHeight() - 4);
	}

}