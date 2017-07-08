package design.com.lry.jfree.chart.design.propertypanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import design.com.lry.jfree.chart.design.factory.FinalStaticFactory;
import design.com.lry.jfree.chart.design.propertybean.JFreeChartPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.PropertyBean;
import design.com.lry.jfree.chart.design.ui.PropertyPanel;

public class JFreeChartPropertyPanel extends PropertyPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2867007841186469754L;
	
	JFreeChartPropertyBean bean = null;

	private BackgroundPropertyPanel backGroundPanel = null;

	/**
	 * Ë¢ÐÂÖÜÆÚ.
	 */
	private JTextField refreshCycleTextField;

	private JTextField bgWidthTextField;

	private JTextField bgHeightTextField;
	
	public JFreeChartPropertyPanel() {
		this(new JFreeChartPropertyBean());
	}
	
	public JFreeChartPropertyPanel(JFreeChartPropertyBean bean) {
		this.bean = bean;
		initComponents();
		setValues();
	}
	
	@Override
	public void setValues() {
		
	}

	@Override
	public void initComponents() {
		init();
		setLayout(new BorderLayout());
		add(getPropertyTabbedPane());
	}

	private void init() {
	}

	private Component getPropertyTabbedPane() {
		JTabbedPane propertyTabbedPane = new JTabbedPane();
		propertyTabbedPane.add(FinalStaticFactory.COMMON_LABEL, getCommonPanel());
		propertyTabbedPane.add(FinalStaticFactory.COMMON_LABEL, getBackGroundPanel());
		return propertyTabbedPane;
	}

	private Component getCommonPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel refreshPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		refreshPanel.add(new JLabel(FinalStaticFactory.REFRESH_CYCLE_LABEL));
		refreshPanel.add(getRefreshCycleTextField());
		refreshPanel.add(new JLabel(FinalStaticFactory.SECOND_LABEL));
		JPanel bgWidthHeightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bgWidthHeightPanel.add(new JLabel(FinalStaticFactory.BACKGROUND_WIDTH_LABEL));
		bgWidthHeightPanel.add(getBGWidthTextField());
		bgWidthHeightPanel.add(new JLabel(FinalStaticFactory.BACKGROUND_HEIGHT_LABEL));
		bgWidthHeightPanel.add(getBGHeightTextField());
		panel.add(refreshPanel, BorderLayout.NORTH);
		panel.add(bgWidthHeightPanel, BorderLayout.CENTER);
		return panel;
	}
	
	private Component getBGHeightTextField() {
		if(null == bgHeightTextField) {
			bgHeightTextField = new JTextField(10);
		}
		return bgHeightTextField;
	}

	private Component getBGWidthTextField() {
		if(null == bgWidthTextField) {
			bgWidthTextField = new JTextField(10);
		}
		return bgWidthTextField;
	}

	private Component getRefreshCycleTextField() {
		if(null == refreshCycleTextField) {
			refreshCycleTextField = new JTextField(10);
		}
		return refreshCycleTextField;
	}

	private Component getBackGroundPanel() {
		if (null == backGroundPanel) {
			backGroundPanel  = new BackgroundPropertyPanel(bean.getBackgroundPropertyBean());
		}
		return backGroundPanel;
	}

	@Override
	public PropertyBean getPropertyBean() {
		return bean;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new JFreeChartPropertyPanel());
		frame.setVisible(true);
	}

}
