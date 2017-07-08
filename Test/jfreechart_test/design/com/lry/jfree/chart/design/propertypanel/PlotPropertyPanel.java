package design.com.lry.jfree.chart.design.propertypanel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import design.com.lry.jfree.chart.design.factory.FinalStaticFactory;
import design.com.lry.jfree.chart.design.propertybean.PlotPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.PropertyBean;
import design.com.lry.jfree.chart.design.ui.CurveInfoPanel;
import design.com.lry.jfree.chart.design.ui.PropertyPanel;

public class PlotPropertyPanel extends PropertyPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8780352808152728162L;
	
	private PlotPropertyBean bean = new PlotPropertyBean();
	private GridLinePropertyPanel gridLinePropertyPanel = null;
	private BackgroundPropertyPanel backgroundPropertyPanel = null;
	public PlotPropertyPanel() {
		this(new PlotPropertyBean());
	}
	
	public PlotPropertyPanel(PlotPropertyBean bean) {
		this.bean = bean;
		this.initComponents();
		this.setValues();
	}
	
	@Override
	public void initComponents() {
		this.setLayout(new BorderLayout());
		this.add(getPlotPropertyTabbedPane());
	}

	private JTabbedPane getPlotPropertyTabbedPane() {
		JTabbedPane plotPropertyTabbedPane = new JTabbedPane();
		plotPropertyTabbedPane.addTab(FinalStaticFactory.BACKGROUND_LABEL, getBackgroundPropertyPanel());
		plotPropertyTabbedPane.addTab(FinalStaticFactory.GRID_LINE_LABEL, getGridPropertyPanel());
		plotPropertyTabbedPane.addTab(FinalStaticFactory.CURVE_LABEL, getCurveSetPanel());
		return plotPropertyTabbedPane;
	}

	private Component getGridPropertyPanel() {
		if(null == gridLinePropertyPanel) {
			gridLinePropertyPanel = new GridLinePropertyPanel(bean);
		}
		return gridLinePropertyPanel;
	}

	private JPanel getBackgroundPropertyPanel() {
		if (null == backgroundPropertyPanel) {
			backgroundPropertyPanel= new BackgroundPropertyPanel(bean.getBgPropertyBean());
		}
		return backgroundPropertyPanel;
	}

	private JPanel getCurveSetPanel() {
		return new CurveInfoPanel();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new PlotPropertyPanel());
		frame.setVisible(true);
	}

	@Override
	public PropertyBean getPropertyBean() {
		return this.bean;
	}

}
