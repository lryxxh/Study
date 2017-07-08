package design.com.lry.jfree.chart.design.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CurveInfoPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1158424848094541127L;
	
	private JTable curveInfoTable = null;

	public CurveInfoPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		this.add(getCurveScrollPane());
	}

	private Component getCurveScrollPane() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(getCurveInfoTable());
		return scrollPane;
	}

	private JTable getCurveInfoTable() {
		if (null == curveInfoTable) {
			curveInfoTable = new JTable();
		}
		return curveInfoTable;
	}

}
