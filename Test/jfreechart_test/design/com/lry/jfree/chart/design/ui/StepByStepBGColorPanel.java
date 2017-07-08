package design.com.lry.jfree.chart.design.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import design.com.lry.jfree.chart.design.factory.FinalStaticFactory;
import design.com.lry.jfree.chart.design.model.MapTypeComboxModel;
import design.com.lry.jfree.chart.design.propertybean.BackgroundPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.PropertyBean;

@SuppressWarnings("unchecked")
public class StepByStepBGColorPanel extends PropertyPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4890795518816055536L;
	
	/**
	 * 颜色1.
	 */
	private JComboBox colorStartComBox = null;

	/**
	 * 颜色2.
	 */
	private JComboBox colorEndComBox;
	
	/**
	 * 从左到右.
	 */
	private JRadioButton leftToRightRadioButton = null;
	
	/**
	 * 上右到左.
	 */
	private JRadioButton rightToLeftRadioButton = null;
	
	/**
	 * 从上到下.
	 */
	private JRadioButton topToBottomRadioButton = null;
	
	/**
	 * 从下到上.
	 */
	private JRadioButton bottomToTopRadioButton = null;
	
	/**
	 * 集中.
	 */
	private JRadioButton colorFocusRadioButton = null;
	
	/**
	 * 辐射.
	 */
	private JRadioButton colorRadiateRadioButton = null;
	
	ButtonGroup group = null;
	
	/**
	 * 开始颜色模型.
	 */
	MapTypeComboxModel startModel = null;
	
	/**
	 * 结束颜色模型.
	 */
	MapTypeComboxModel endModel = null;
	
	private List<Color> colorList = null;
	BackgroundPropertyBean bean = null;
	
	/**
	 * 背景预览面版.
	 */
	private BackgroundPreViewPanel backgroundPreViewPanel = null;
	
	/**
	 * 构造方法.
	 */
	public StepByStepBGColorPanel() {
		this(new BackgroundPropertyBean());
	}
	
	/**
	 * 构造方法.
	 */
	public StepByStepBGColorPanel(BackgroundPropertyBean bean) {
		this.bean = bean;
		this.initComponents();
		this.setValues();
	}
	
	public void setValues() {
		Paint startPaint = bean.getStep_by_step_color1();
		Paint endPaint = bean.getStep_by_step_color2();
		colorStartComBox.setSelectedIndex(colorList.indexOf(startPaint));
		colorEndComBox.setSelectedIndex(colorList.indexOf(endPaint));
		setGroupButtonSelected(bean.getStep_by_step_type());
	}
	
	private void setGroupButtonSelected(int type) {
		switch (type) {
		case 0:
			leftToRightRadioButton.setSelected(true);
			break;
		case 1:
			rightToLeftRadioButton.setSelected(true);
			break;
		case 2:
			topToBottomRadioButton.setSelected(true);
			break;
		case 3:
			bottomToTopRadioButton.setSelected(true);
			break;
		case 4:
			colorRadiateRadioButton.setSelected(true);
			break;
		case 5:
			colorFocusRadioButton.setSelected(true);
			break;
		}
	}

	/**
	 * 初始化界面
	 */
	public void initComponents() {
		init();
		this.setLayout(new BorderLayout());
		add(getColorSelectPanel(), BorderLayout.NORTH);
		add(getColorPaintViewPanel(), BorderLayout.CENTER);
	}

	private void init() {
		leftToRightRadioButton = new JRadioButton(FinalStaticFactory.LEFT_TO_RIGHT_LABEL);
		rightToLeftRadioButton = new JRadioButton(FinalStaticFactory.RIGHT_TO_LEFT_LABEL);
		topToBottomRadioButton = new JRadioButton(FinalStaticFactory.TOP_TO_BOTTOM_LABEL);
		bottomToTopRadioButton = new JRadioButton(FinalStaticFactory.BOTTOM_TO_UP_LABEL);
		colorFocusRadioButton = new JRadioButton(FinalStaticFactory.COLOR_FOCUS_LABEL);
		colorRadiateRadioButton = new JRadioButton(FinalStaticFactory.COLOR_RADIATE_LABEL);
		group = new ButtonGroup();
		startModel = new MapTypeComboxModel(FinalStaticFactory.getColorModelData());
		endModel = new MapTypeComboxModel(FinalStaticFactory.getColorModelData());
		colorList = FinalStaticFactory.getColorList();
	}

	private Component getColorPaintViewPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.add(getColorPaintTypePanel());
		panel.add(getBackgroundPreViewPanel());
		return panel;
	}

	/**
	 * 得到渐进颜色类型面板.
	 * @return
	 */
	private Component getColorPaintTypePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 3));
		panel.add(leftToRightRadioButton);
		panel.add(topToBottomRadioButton);
		panel.add(colorRadiateRadioButton);
		panel.add(rightToLeftRadioButton);
		panel.add(bottomToTopRadioButton);
		panel.add(colorFocusRadioButton);
		group.add(leftToRightRadioButton);
		group.add(topToBottomRadioButton);
		group.add(colorRadiateRadioButton);
		group.add(rightToLeftRadioButton);
		group.add(bottomToTopRadioButton);
		group.add(colorFocusRadioButton);
		leftToRightRadioButton.setSelected(true);
		addActionListener();
		return panel;
	}
	
	private void addActionListener() {
		leftToRightRadioButton.addActionListener(this);
		topToBottomRadioButton.addActionListener(this);
		colorRadiateRadioButton.addActionListener(this);
		rightToLeftRadioButton.addActionListener(this);
		colorFocusRadioButton.addActionListener(this);
		bottomToTopRadioButton.addActionListener(this);
		leftToRightRadioButton.setActionCommand("0");
		rightToLeftRadioButton.setActionCommand("1");
		topToBottomRadioButton.setActionCommand("2");
		bottomToTopRadioButton.setActionCommand("3");
		colorRadiateRadioButton.setActionCommand("4");
		colorFocusRadioButton.setActionCommand("5");
	}

	/**
	 * 得到颜色选择面板.
	 * @return
	 */
	private Component getColorSelectPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(getColorSelect1Panel());
		panel.add(getColorSelect2Panel());
		return panel;
	}
	
	/**
	 * 背景预览面板.
	 * @return
	 */
	private Component getBackgroundPreViewPanel() {
		if (null == backgroundPreViewPanel) {
			backgroundPreViewPanel = new BackgroundPreViewPanel(bean);
		}
		return backgroundPreViewPanel;
	}
	
	/**
	 * 得到起始颜色面板.
	 * @return
	 */
	private Component getColorSelect1Panel() {
		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel colorStartLabel = new JLabel(FinalStaticFactory.COLOR_START_LABEL);
		panel.setLayout(layout);
		constraints.weightx = 0.0;
		layout.setConstraints(colorStartLabel, constraints);
		panel.add(colorStartLabel);
		
		Component colorStartCombox = getColorStartCombox();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(0, 10, 0, 50);
		layout.setConstraints(colorStartCombox, constraints);
		panel.add(colorStartCombox);
		return panel;
	}

	/**
	 * 得到起始颜色组合框.
	 * @return
	 */
	private Component getColorStartCombox() {
		if(colorStartComBox == null) {
			colorStartComBox = new JComboBox();
			colorStartComBox.setModel(startModel);
			colorStartComBox.setRenderer(new ColorListCellRenderer());
		}
		return colorStartComBox;
	}
	
	/**
	 * 得到结束颜色面板.
	 * @return
	 */
	private Component getColorSelect2Panel() {
		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel colorEndLabel = new JLabel(FinalStaticFactory.COLOR_END_LABEL);
		panel.setLayout(layout);
		constraints.weightx = 0.0;
		layout.setConstraints(colorEndLabel, constraints);
		panel.add(colorEndLabel);
		
		Component colorEndCombox = getColorEndCombox();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(0, 10, 0, 50);
		layout.setConstraints(colorEndCombox, constraints);
		panel.add(colorEndCombox);
		return panel;
	}
	
	/**
	 * 得到结束颜色组合框.
	 * @return
	 */
	private Component getColorEndCombox() {
		if(colorEndComBox == null) {
			colorEndComBox = new JComboBox();
			colorEndComBox.setModel(endModel);
			colorEndComBox.setRenderer(new ColorListCellRenderer());
		}
		return colorEndComBox;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600,300);
		MapTypeComboxModel model = new MapTypeComboxModel(FinalStaticFactory.getColorModelData());
		JComboBox checkBox = new JComboBox(model);
		checkBox.setRenderer(new ColorListCellRenderer());
		frame.getContentPane().add(checkBox);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		HashMap<String, Color> startColorMap = (HashMap<String, Color>) colorStartComBox.getSelectedItem();
		HashMap<String, Color> endColorMap = (HashMap<String, Color>) colorEndComBox.getSelectedItem();
		Color startColor = startColorMap.values().iterator().next();
		Color endColor = endColorMap.values().iterator().next();
		if (e.getSource() instanceof JRadioButton) {
			JRadioButton eventRadioButton = (JRadioButton) e.getSource();
			String actionCommand = eventRadioButton.getActionCommand();
			int type = Integer.parseInt(actionCommand);
			bean.setStep_by_step_type(type);
			bean.setStep_by_step_color1(startColor);
			bean.setStep_by_step_color2(endColor);
			backgroundPreViewPanel.updateUI();
		}
	}
	
	@Override
	public PropertyBean getPropertyBean() {
		return this.bean;
	}
	
}
