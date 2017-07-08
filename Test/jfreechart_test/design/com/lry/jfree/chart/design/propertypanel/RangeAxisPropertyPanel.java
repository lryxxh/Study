package design.com.lry.jfree.chart.design.propertypanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import design.com.lry.jfree.chart.design.factory.FinalStaticFactory;
import design.com.lry.jfree.chart.design.propertybean.PropertyBean;
import design.com.lry.jfree.chart.design.propertybean.RangeAxisPropertyBean;
import design.com.lry.jfree.chart.design.ui.PropertyPanel;

/**
 * y轴属性面板.
 * @author LRY
 *
 */
public class RangeAxisPropertyPanel extends PropertyPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3018610214584970819L;
	
	/**
	 * 固定刻度.
	 */
	private JCheckBox fixGraduateCheckBox = null;
	
	/**
	 * 自适应刻度.
	 */
	private JCheckBox autoGraduateCheckBox = null;
	
	/**
	 * 限值自适应刻度.
	 */
	private JCheckBox limitGraduateCheckBox = null;
	
	/**
	 * 增值自适应刻度.
	 */
	private JCheckBox increaseGraduateCheckBox = null;
	
	/**
	 * 下限.
	 */
	private JTextField minTextField = null;
	
	/**
	 * 上限.
	 */
	private JTextField maxTextField = null;
	
	/**
	 * 主刻度数.
	 */
	private JTextField mainSpaceTextField = null;
	
	/**
	 * 副刻度数.
	 */
	private JTextField subSpaceTextField = null;
	
	/**
	 * 下浮比例.
	 */
	private JTextField downBalanceTextField = null;
	
	/**
	 * 上浮比例.
	 */
	private JTextField upBalanceextField = null;
	
	/**
	 * 小数位数.
	 */
	private JTextField decimalTextField = null;
	
	/**
	 * 增量比例.
	 */
	private JTextField increaseBalanceTextField = null;
	
	/**
	 * y轴属性bean.
	 */
	private RangeAxisPropertyBean bean = null;

	/**
	 * y轴线属性.
	 */
	private LinePropertyPanel rangeAxisLinePropertyPanel = null;
	
	/**
	 * y轴线属性.
	 */
	private FontPropertyPanel rangeAxisFontPropertyPanel = null;
	
	/**
	 * 左侧。
	 */
	private JCheckBox leftCheckBox = null;
	
	/**
	 * 右侧。
	 */
	private JCheckBox rightCheckBox = null;
	
	public RangeAxisPropertyPanel() {
		this(new RangeAxisPropertyBean());
	}
	
	public RangeAxisPropertyPanel(RangeAxisPropertyBean bean) {
		this.bean = bean;
		this.initComponents();
		this.setValues();
	}

	@Override
	public void initComponents() {
		init();
		setLayout(new BorderLayout());
		add(getNorthPanel(), BorderLayout.NORTH);
		add(getCenterPanel(), BorderLayout.CENTER);
		
	}
	
	/**
	 * 多坐标轴情况。
	 * @return
	 */
	private Component getNorthPanel() {
		JPanel panel = new JPanel();
		panel.add(getAxisCountSpinner());
		panel.add(getLocationPanel());
		return null;
	}

	private Component getLocationPanel() {
		JPanel panel = new JPanel();
		panel.add(getLeftLocationCheckbox());
		panel.add(getRightLocationCheckbox());
		return panel;
	}

	private Component getRightLocationCheckbox() {
		if(leftCheckBox == null) {
			leftCheckBox = new JCheckBox(FinalStaticFactory.LEFT_LOCATION_LABEL);
		}
		return leftCheckBox;
	}

	private Component getLeftLocationCheckbox() {
		if(rightCheckBox == null) {
			rightCheckBox = new JCheckBox(FinalStaticFactory.RIGHT_LOCATIOIN_LABEL);
		}
		return rightCheckBox;
	}

	/**
	 * 曲线数量。
	 * @return
	 */
	private Component getAxisCountSpinner() {
		JSpinner spinner = new JSpinner(new SpinnerNumberModel());
		spinner.setValue(1);
		return spinner;
	}

	@Override
	public void setValues() {
		
	}

	private Component getCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		panel.add(getRangePropertyPanel());
		panel.add(getLinePropertyPanel());
		panel.add(getFontPropertyPanel());
		return panel;
	}

	private Component getFontPropertyPanel() {
		if (null == rangeAxisFontPropertyPanel) {
			rangeAxisFontPropertyPanel = new FontPropertyPanel(bean.getFontPropertyBean());
		}
		rangeAxisFontPropertyPanel.setBorder(BorderFactory.createTitledBorder(null, "字体属性", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		return rangeAxisFontPropertyPanel;
	}

	private Component getLinePropertyPanel() {
		if(null == rangeAxisLinePropertyPanel) {
			rangeAxisLinePropertyPanel = new LinePropertyPanel(bean.getLinePropertyBean());
		}
		rangeAxisLinePropertyPanel.setBorder(BorderFactory.createTitledBorder(null, "线属性", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		return rangeAxisLinePropertyPanel;
	}
	

	/**
	 * 得到y轴具体属性面板.
	 * @return
	 */
	private Component getRangePropertyPanel() {
		JPanel panel = new JPanel();
		GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
		gridBagConstraints16.fill = GridBagConstraints.BOTH;
		gridBagConstraints16.gridy = 4;
		gridBagConstraints16.weightx = 1.0;
		gridBagConstraints16.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints16.gridx = 3;
		GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
		gridBagConstraints15.gridx = 2;
		gridBagConstraints15.anchor = GridBagConstraints.WEST;
		gridBagConstraints15.insets = new Insets(5, 10, 5, 5);
		gridBagConstraints15.gridy = 4;
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.fill = GridBagConstraints.BOTH;
		gridBagConstraints14.gridy = 4;
		gridBagConstraints14.weightx = 1.0;
		gridBagConstraints14.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints14.gridx = 1;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.gridx = 0;
		gridBagConstraints13.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints13.anchor = GridBagConstraints.WEST;
		gridBagConstraints13.gridy = 4;
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.fill = GridBagConstraints.BOTH;
		gridBagConstraints12.gridy = 3;
		gridBagConstraints12.weightx = 1.0;
		gridBagConstraints12.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints12.gridx = 3;
		GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
		gridBagConstraints111.gridx = 2;
		gridBagConstraints111.insets = new Insets(5, 10, 5, 5);
		gridBagConstraints111.anchor = GridBagConstraints.WEST;
		gridBagConstraints111.gridy = 3;
		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		gridBagConstraints10.fill = GridBagConstraints.BOTH;
		gridBagConstraints10.gridy = 3;
		gridBagConstraints10.weightx = 1.0;
		gridBagConstraints10.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints10.gridx = 1;
		GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		gridBagConstraints9.gridx = 0;
		gridBagConstraints9.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints9.anchor = GridBagConstraints.WEST;
		gridBagConstraints9.gridy = 3;
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.fill = GridBagConstraints.BOTH;
		gridBagConstraints8.gridy = 2;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints8.gridx = 3;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 2;
		gridBagConstraints7.insets = new Insets(5, 10, 5, 5);
		gridBagConstraints7.anchor = GridBagConstraints.WEST;
		gridBagConstraints7.gridy = 2;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.BOTH;
		gridBagConstraints6.gridy = 2;
		gridBagConstraints6.weightx = 1.0;
		gridBagConstraints6.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.gridx = 0;
		gridBagConstraints5.anchor = GridBagConstraints.WEST;
		gridBagConstraints5.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints5.gridy = 2;
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.fill = GridBagConstraints.BOTH;
		gridBagConstraints4.gridy = 1;
		gridBagConstraints4.weightx = 1.0;
		gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints4.gridx = 3;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.gridx = 2;
		gridBagConstraints31.anchor = GridBagConstraints.WEST;
		gridBagConstraints31.insets = new Insets(5, 10, 5, 5);
		gridBagConstraints31.gridy = 1;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.fill = GridBagConstraints.BOTH;
		gridBagConstraints21.gridy = 1;
		gridBagConstraints21.weightx = 1.0;
		gridBagConstraints21.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints21.gridx = 1;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.anchor = GridBagConstraints.WEST;
		gridBagConstraints11.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints11.fill = GridBagConstraints.NONE;
		gridBagConstraints11.gridy = 1;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 3;
		gridBagConstraints3.insets = new Insets(5, 10, 5, 10);
		gridBagConstraints3.gridy = 0;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 2;
		gridBagConstraints2.insets = new Insets(5, 10, 5, 10);
		gridBagConstraints2.gridy = 0;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.insets = new Insets(5, 10, 5, 10);
		gridBagConstraints1.gridy = 0;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.insets = new Insets(5, 10, 5, 10);
		gridBagConstraints.gridy = 0;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createTitledBorder(null, "坐标属性", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		panel.add(getFixGraduateCheckBox(), gridBagConstraints);
		panel.add(getAutoGraduateCheckBox(), gridBagConstraints1);
		panel.add(getLimitGraduateCheckBox(), gridBagConstraints2);
		panel.add(getIncreaseGraduateCheckBox(), gridBagConstraints3);
		panel.add(new JLabel(FinalStaticFactory.MIN_LABEL), gridBagConstraints11);
		panel.add(getMinTextField(), gridBagConstraints21);
		panel.add(new JLabel(FinalStaticFactory.MAX_LABEL), gridBagConstraints31);
		panel.add(getMaxTextField(), gridBagConstraints4);
		panel.add(new JLabel(FinalStaticFactory.MAIN_SPACE_LABEL), gridBagConstraints5);
		panel.add(getMainSpaceTextField(), gridBagConstraints6);
		panel.add(new JLabel(FinalStaticFactory.SUB_SPACE_LABEL), gridBagConstraints7);
		panel.add(getSubSpaceTextField(), gridBagConstraints8);
		panel.add(new JLabel(FinalStaticFactory.DOWN_BALANCE_LABEL), gridBagConstraints9);
		panel.add(getDownBalanceTextField(), gridBagConstraints10);
		panel.add(new JLabel(FinalStaticFactory.UP_BALANCE_LABEL), gridBagConstraints111);
		panel.add(getUpBalanceextField(), gridBagConstraints12);
		panel.add(new JLabel(FinalStaticFactory.DECIMAL_LABEL), gridBagConstraints13);
		panel.add(getDecimalTextField(), gridBagConstraints14);
		panel.add(new JLabel(FinalStaticFactory.INCREASE_BALANCE_LABEL), gridBagConstraints15);
		panel.add(getIncreaseBalanceTextField(), gridBagConstraints16);
		return panel;
	}

	public JTextField getMinTextField() {
		if(null == minTextField) {
			minTextField = new JTextField();
		}
		minTextField.setPreferredSize(new Dimension(150, 25));
		return minTextField;
	}
	
	public JTextField getMaxTextField() {
		if (null == maxTextField) {
			maxTextField = new JTextField();
		}
		maxTextField.setPreferredSize(new Dimension(150, 25));
		return maxTextField;
	}
	
	public JTextField getMainSpaceTextField() {
		if (null == mainSpaceTextField) {
			mainSpaceTextField = new JTextField();
		}
		mainSpaceTextField.setPreferredSize(new Dimension(150, 25));
		return mainSpaceTextField;
	}
	
	public JTextField getSubSpaceTextField() {
		if (null == subSpaceTextField) {
			subSpaceTextField = new JTextField();
		}
		subSpaceTextField.setPreferredSize(new Dimension(150, 25));
		return subSpaceTextField;
	}
	
	public JTextField getDownBalanceTextField() {
		if (null == downBalanceTextField) {
			downBalanceTextField = new JTextField();
		}
		downBalanceTextField.setPreferredSize(new Dimension(150, 25));
		return downBalanceTextField;
	}
	
	public JTextField getUpBalanceextField() {
		if (null == upBalanceextField) {
			upBalanceextField = new JTextField();
		}
		upBalanceextField.setPreferredSize(new Dimension(150, 25));
		return upBalanceextField;
	}
	
	public JTextField getDecimalTextField() {
		if (null == decimalTextField) {
			decimalTextField = new JTextField();
		}
		decimalTextField.setPreferredSize(new Dimension(150, 25));
		return decimalTextField;
	}
	
	public JTextField getIncreaseBalanceTextField() {
		if (null == increaseBalanceTextField) {
			increaseBalanceTextField = new JTextField();
		}
		increaseBalanceTextField.setPreferredSize(new Dimension(150, 25));
		return increaseBalanceTextField;
	}

	/**
	 * 固定刻度.
	 * @return
	 */
	public JCheckBox getFixGraduateCheckBox() {
		if(null == fixGraduateCheckBox) {
			fixGraduateCheckBox = new JCheckBox(FinalStaticFactory.FIX_GRADUATE_LABEL);
		}
		return fixGraduateCheckBox;
	}
	
	/**
	 * 自适应刻度.
	 * @return
	 */
	public JCheckBox getAutoGraduateCheckBox() {
		if(null == autoGraduateCheckBox) {
			autoGraduateCheckBox = new JCheckBox(FinalStaticFactory.AUTO_GRADUATE_LABEL);
		}
		return autoGraduateCheckBox;
	}
	
	/**
	 * 限值内自适应.
	 * @return
	 */
	public JCheckBox getLimitGraduateCheckBox() {
		if(null == limitGraduateCheckBox) {
			limitGraduateCheckBox = new JCheckBox(FinalStaticFactory.LIMIT_GRADUATE_LABEL);
		}
		return limitGraduateCheckBox;
	}
	
	/**
	 * 增量自适应.
	 * @return
	 */
	public JCheckBox getIncreaseGraduateCheckBox() {
		if(null == increaseGraduateCheckBox) {
			increaseGraduateCheckBox = new JCheckBox(FinalStaticFactory.LIMIT_GRADUATE_LABEL);
		}
		return increaseGraduateCheckBox;
	}

	private void init() {
		this.fixGraduateCheckBox = new JCheckBox(FinalStaticFactory.FIX_GRADUATE_LABEL);
		this.autoGraduateCheckBox = new JCheckBox(FinalStaticFactory.AUTO_GRADUATE_LABEL);
		this.limitGraduateCheckBox = new JCheckBox(FinalStaticFactory.LIMIT_GRADUATE_LABEL);
		this.increaseGraduateCheckBox = new JCheckBox(FinalStaticFactory.INCREASE_GRADUATE_LABEL);
		this.rangeAxisLinePropertyPanel = new LinePropertyPanel(bean.getLinePropertyBean());
		this.rangeAxisFontPropertyPanel = new FontPropertyPanel(bean.getFontPropertyBean());
		this.minTextField = new JTextField();
		this.maxTextField = new JTextField();
		this.mainSpaceTextField = new JTextField();
		this.subSpaceTextField = new JTextField();
		this.upBalanceextField = new JTextField();
		this.downBalanceTextField = new JTextField();
		this.decimalTextField = new JTextField();
		this.increaseBalanceTextField = new JTextField();
		ButtonGroup group = new ButtonGroup();
		group.add(fixGraduateCheckBox);
		group.add(autoGraduateCheckBox);
		group.add(limitGraduateCheckBox);
		group.add(increaseGraduateCheckBox);
	}

	@Override
	public PropertyBean getPropertyBean() {
		return bean;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new RangeAxisPropertyPanel());
		frame.setVisible(true);
	}
	

}
