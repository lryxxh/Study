package design.com.lry.jfree.chart.design.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.entity.ChartEntity;

import design.com.lry.jfree.chart.design.factory.EntityParseFactory;
import design.com.lry.jfree.chart.design.factory.FinalStaticFactory;
import design.com.lry.jfree.chart.design.factory.PropertyPanelFactory;

public class PropertyDialog extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4557953437246349379L;
	
	/**
	 * 确定按钮.
	 */
	private JButton okButton = new JButton(FinalStaticFactory.OK_BUTTON_LABEL);
	
	/**
	 * 取消按钮.
	 */
	private JButton cancelButton = new JButton(FinalStaticFactory.CANCEL_BUTTON_LABEL);
	PropertyPanel propertyPanel = null;
	ChartEntity entity = null;
	ChartPanel chartPanel = null;
	@SuppressWarnings("static-access")
	public PropertyDialog(ChartEntity entity, ChartPanel chartPanel) {
		this.entity = entity;
		this.chartPanel = chartPanel;
		propertyPanel = new PropertyPanelFactory().getPropertyPanel(entity, chartPanel);
		add(propertyPanel, BorderLayout.CENTER);
		add(getButtonPanel(), BorderLayout.SOUTH);
		this.setSize(850,650);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private Component getButtonPanel() {
		JPanel buttonPanel = new JPanel();
		((FlowLayout)buttonPanel.getLayout()).setHgap(25);
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		addButtonListener();
		return buttonPanel;
	}

	private void addButtonListener() {
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}

	public static void main(String[] args) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(okButton)) {
			EntityParseFactory.setBeanToEntity(propertyPanel.getPropertyBean(), entity, chartPanel);
		}
		this.dispose();
	}

}
