package design.com.lry.jfree.chart.design.propertypanel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import design.com.lry.jfree.chart.design.factory.FinalStaticFactory;
import design.com.lry.jfree.chart.design.propertybean.PlotPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.PropertyBean;
import design.com.lry.jfree.chart.design.ui.PropertyPanel;

public class GridLinePropertyPanel extends PropertyPanel implements ItemListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7652604023437497565L;
	private LinePropertyPanel mainGridLinePropertyPanel = null;
	private LinePropertyPanel subGridLinePropertyPanel = null;
	private JCheckBox mainGridHLineCheckBox = null;
	private JCheckBox mainGridVLineCheckBox = null;
	private JCheckBox subGridHLineCheckBox = null;
	private JCheckBox subGridVLineCheckBox = null;
	PlotPropertyBean bean = null;
	public GridLinePropertyPanel() {
		this(new PlotPropertyBean());
	}
	
	public GridLinePropertyPanel(PlotPropertyBean bean) {
		this.bean = bean;
		initComponents();
		setValues();
	}
	
	public void setValues() {
		mainGridHLineCheckBox.setSelected(bean.isH_MainGridLineVisible());
		mainGridVLineCheckBox.setSelected(bean.isV_MainGridLineVisible());
		subGridHLineCheckBox.setSelected(bean.isH_SubGridLineVisible());
		subGridVLineCheckBox.setSelected(bean.isV_SubGridLineVisible());
	}

	public void initComponents() {
		init();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(getMainGridPropertyPanel());
		add(getSubGridPropertyPanel());
	}

	private void init() {
		mainGridHLineCheckBox = new JCheckBox(FinalStaticFactory.MAIN_GRID_LINE_H_LABEL);
		mainGridVLineCheckBox = new JCheckBox(FinalStaticFactory.MAIN_GRID_LINE_V_LABEL);
		subGridHLineCheckBox = new JCheckBox(FinalStaticFactory.SUB_GRID_LINE_H_LABEL);
		subGridVLineCheckBox = new JCheckBox(FinalStaticFactory.SUB_GRID_LINE_V_LABEL);
		mainGridHLineCheckBox.addItemListener(this);
		mainGridVLineCheckBox.addItemListener(this);
		subGridHLineCheckBox.addItemListener(this);
		subGridVLineCheckBox.addItemListener(this);
	}

	private Component getSubGridPropertyPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(getSubGridCheckBoxPanel());
		panel.add(getSubGridLinePropertyPanel());
		return panel;
	}
	
	public LinePropertyPanel getSubGridLinePropertyPanel() {
		if (null == subGridLinePropertyPanel) {
			subGridLinePropertyPanel = new LinePropertyPanel(bean.getSubGridLinePropertyBean());
		}
		return subGridLinePropertyPanel;
	}
	
	private Component getSubGridCheckBoxPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(subGridHLineCheckBox);
		panel.add(subGridVLineCheckBox);
		return panel;
	}

	private Component getMainGridPropertyPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(getMainGridCheckBoxPanel());
		panel.add(getMainGridLinePropertyPanel());
		return panel;
	}
	
	public LinePropertyPanel getMainGridLinePropertyPanel() {
		if (null == mainGridLinePropertyPanel) {
			mainGridLinePropertyPanel = new LinePropertyPanel(bean.getMainGridLinePropertyBean());
		}
		return mainGridLinePropertyPanel;
	}
	
	private Component getMainGridCheckBoxPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(mainGridHLineCheckBox);
		panel.add(mainGridVLineCheckBox);
		return panel;
	}
	
	@Override
	public PropertyBean getPropertyBean() {
		return this.bean;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GridLinePropertyPanel());
		frame.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object object = e.getSource();
		if(mainGridHLineCheckBox.equals(object)) {
			bean.setH_MainGridLineVisible(mainGridHLineCheckBox.isSelected());
		}else if (mainGridVLineCheckBox.equals(object)) {
			bean.setV_MainGridLineVisible(mainGridVLineCheckBox.isSelected());
		} else if (subGridHLineCheckBox.equals(object)) {
			bean.setH_SubGridLineVisible(subGridHLineCheckBox.isSelected());
		} else if (subGridVLineCheckBox.equals(object)) {
			bean.setV_SubGridLineVisible(subGridVLineCheckBox.isSelected());
		}
	}

}
