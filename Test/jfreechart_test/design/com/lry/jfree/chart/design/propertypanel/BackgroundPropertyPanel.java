package design.com.lry.jfree.chart.design.propertypanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import design.com.lry.jfree.chart.design.factory.FinalStaticFactory;
import design.com.lry.jfree.chart.design.model.MapTypeComboxModel;
import design.com.lry.jfree.chart.design.propertybean.BackgroundPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.PropertyBean;
import design.com.lry.jfree.chart.design.ui.BackgroundPreViewPanel;
import design.com.lry.jfree.chart.design.ui.ColorListCellRenderer;
import design.com.lry.jfree.chart.design.ui.PropertyPanel;
import design.com.lry.jfree.chart.design.ui.StepByStepBGColorPanel;

@SuppressWarnings({"rawtypes","unchecked"})
public class BackgroundPropertyPanel extends PropertyPanel implements ItemListener, ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7048076536648970225L;
	
	/**
	 * 填充方式.
	 */
	private JComboBox fillTypeComBox = null;

	/**
	 * 动态背景面板.
	 */
	private JPanel dynamicBackGroundPanel = null;
	
	/**
	 * 布局.
	 */
	private CardLayout layout = new CardLayout();
	
	/**
	 * 色彩选择器的模型数据.
	 */
	private List<HashMap> list = null;
	
	/**
	 * 颜色组合框model.
	 */
	private MapTypeComboxModel model = null;

	/**
	 * 图片路径文本框.
	 */
	private JTextField imagePathTextField = null;
	
	/**
	 * 图片选择按钮.
	 */
	private JButton imageChooseButton = null;
	
	/**
	 * 颜色组合框
	 */
	private JComboBox singleColorCombox;
	
	/**
	 * 线属性面板.
	 */
	private LinePropertyPanel linePropertyPanel = null;
	
	/**
	 * 色彩的字符串对应map.
	 */
	private HashMap<String, Color> colorMap = new HashMap<String, Color>(); 
	
	/**
	 * 颜色集合.
	 */
	private ArrayList<Color> colorList = new ArrayList<Color>(); 
	
	/**
	 * 背景属性JavaBean.
	 */
	private BackgroundPropertyBean bean = null;
	
	/**
	 * 背景预览面版.
	 */
	private BackgroundPreViewPanel backgroundPreViewPanel = null;
	
	/**
	 * 背景预览面版.
	 */
	private BackgroundPreViewPanel imageBackgroundPreViewPanel = null;

	private JPanel setpByStepBGColorPanel = null;
	public BackgroundPropertyPanel() {
		this(new BackgroundPropertyBean());
	}
	
	public BackgroundPropertyPanel(BackgroundPropertyBean backgroundBean){
		this.bean = backgroundBean;
		this.initComponents();
		this.setValues();
	}
	
	
	/**
	 * 设置值.
	 */
	public void setValues() {
		int fillType = bean.getFillType();
		fillTypeComBox.setSelectedIndex(fillType);
		switch (fillType) {
		case 0://单色
		{
			if(!colorMap.containsValue(bean.getBgColor())) {
				HashMap<String, Color> map = new HashMap<String, Color>(); 
				map.put(bean.getBgColor().toString(), (Color) bean.getBgColor());
				singleColorCombox.addItem(map);
			}
			singleColorCombox.setSelectedIndex(colorList.indexOf(bean.getBgColor()));
			break;
		}
		case 1: //渐进色
			break;
		case 2://图片
			imagePathTextField.setText("");
			imageBackgroundPreViewPanel.updateUI();
			break;
		case 3://透明
			break;
		}
	}

	/**
	 * 初始化组件.
	 */
	public void initComponents(){
		init();
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		this.add(getFillTypePanel(), BorderLayout.NORTH);
		this.add(getCenterPanel(), BorderLayout.CENTER);
	}

	private void init() {
		list = FinalStaticFactory.getColorModelData();
		model = new MapTypeComboxModel(list);
		colorMap = FinalStaticFactory.getColorHashMap(); 
		colorList = FinalStaticFactory.getColorList(); 
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
	 * 得到中间的面板.
	 * @return
	 */
	private Component getCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JSeparator() , BorderLayout.NORTH);
		panel.add(getContentPanel(), BorderLayout.CENTER);
		return panel;
	}

	/**
	 * 中间面板.
	 * @return
	 */
	private Component getContentPanel() {
		if(null == dynamicBackGroundPanel) {
			dynamicBackGroundPanel = new JPanel();
			dynamicBackGroundPanel.setLayout(layout);
			dynamicBackGroundPanel.getInsets(new Insets(5, 5, 5, 5));
			dynamicBackGroundPanel.add(FinalStaticFactory.FILL_SINGLE_BG_COLOR, getSingleBGColorPanel());
			dynamicBackGroundPanel.add(FinalStaticFactory.FILL_STEP_BY_STEP_BG_COLOR, getStepByStepBGColorPanel());
			dynamicBackGroundPanel.add(FinalStaticFactory.FILL_IMAGE_BG_COLOR, getImageBGPanel());
			dynamicBackGroundPanel.add(FinalStaticFactory.FILL_TRANSPAREND_BG_COLOR, getTransparentPanel());
			dynamicBackGroundPanel.setBorder(BorderFactory.createBevelBorder(1));
		}
		return dynamicBackGroundPanel;
	}
	
	private JPanel getStepByStepBGColorPanel() {
		if (null == setpByStepBGColorPanel) {
			setpByStepBGColorPanel = new StepByStepBGColorPanel(bean);
		}
		return setpByStepBGColorPanel ;
	}

	/**
	 * 透明Panel父面板.
	 * @return
	 */
	private Component getTransparentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(getTransparentLinePanel());
		return panel;
	}
	
	/**
	 * 透明面板.
	 * @return
	 */
	private Component getTransparentLinePanel() {
		if (null == linePropertyPanel) {
			linePropertyPanel = new LinePropertyPanel(bean.getLineProperty());
		}
		return linePropertyPanel;
		
	}

	/**
	 * 背景图片面板.
	 * @return
	 */
	private Component getImageBGPanel() {
		JPanel panel = new JPanel();
		JPanel northPanel = new JPanel();
		panel.setLayout(new BorderLayout());
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		northPanel.add(new JLabel(FinalStaticFactory.IMAGE_LABEL));
		northPanel.add(getImagePathTextField());
		northPanel.add(getImageChooseButton());
		panel.add(northPanel, BorderLayout.NORTH);
		panel.add(getImageBackgroundPreViewPanel(), BorderLayout.CENTER);
		return panel;
	}

	private Component getImageBackgroundPreViewPanel() {
		if (null == imageBackgroundPreViewPanel) {
			imageBackgroundPreViewPanel = new BackgroundPreViewPanel(bean);
		}
		return imageBackgroundPreViewPanel;
	}

	/**
	 * 得到背景图片路径文本框.
	 * @return
	 */
	private Component getImageChooseButton() {
		if (null == imageChooseButton) {
			imageChooseButton = new JButton(FinalStaticFactory.SELECT_LABEL);
			imageChooseButton.addActionListener(this);
		}
		return imageChooseButton;
	}

	/**
	 * 得到背景图片选择按钮.
	 * @return
	 */
	private Component getImagePathTextField() {
		if (null == imagePathTextField) {
			imagePathTextField = new JTextField(20);
		}
		return imagePathTextField;
	
	}

	/**
	 * 得到单色背景面板.
	 * @return
	 */
	private Component getSingleBGColorPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		northPanel.add(new JLabel(FinalStaticFactory.COLOR_LABEL));
		JComboBox colorComboBox = getSingleColorCombox();
		northPanel.add(colorComboBox);
		panel.add(northPanel, BorderLayout.NORTH);
		panel.add(getBackgroundPreViewPanel());		
		return panel;
	}

	/**
	 * 色彩选择框.
	 * @return
	 */
	public JComboBox getSingleColorCombox() {
		if (null == singleColorCombox) {
			singleColorCombox = new JComboBox(model);
			singleColorCombox.setRenderer(new ColorListCellRenderer());
			singleColorCombox.setPreferredSize(new Dimension(120 , 25));
			singleColorCombox.addItemListener(this);
		}
		return singleColorCombox;
	}

	/**
	 * 得到填充方式panel.
	 */
	private Component getFillTypePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(FinalStaticFactory.FILL_TYPE_LABEL);
		panel.add(label);
		panel.add(getFillTypeCombox());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		return panel;
	}

	/**
	 * 得到填充方式组合框.
	 * @return
	 */
	private Component getFillTypeCombox() {
		if (null == fillTypeComBox){
			fillTypeComBox = new JComboBox();
			fillTypeComBox.addItem(FinalStaticFactory.FILL_SINGLE_BG_COLOR);
			fillTypeComBox.addItem(FinalStaticFactory.FILL_STEP_BY_STEP_BG_COLOR);
			fillTypeComBox.addItem(FinalStaticFactory.FILL_IMAGE_BG_COLOR);
			fillTypeComBox.addItem(FinalStaticFactory.FILL_TRANSPAREND_BG_COLOR);
			fillTypeComBox.addItemListener(this);
		}
		return fillTypeComBox;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BackgroundPropertyPanel());
		frame.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			if(e.getSource().equals(fillTypeComBox)) {
				Object itemObject = fillTypeComBox.getSelectedItem(); 
				layout.show(dynamicBackGroundPanel, itemObject.toString());
				bean.setFillType(fillTypeComBox.getSelectedIndex());
			}else if(e.getSource().equals(singleColorCombox)) {
				HashMap<String, Color> colorMap = (HashMap<String, Color>) singleColorCombox.getSelectedItem(); 
				String colorName = colorMap.keySet().iterator().next();
				if (colorName.equals(FinalStaticFactory.USER_DEFINE_COLOR)) {
					Color userDefineColor = JColorChooser.showDialog(this, "选择自定义颜色", Color.BLACK);
					colorMap.put(colorName, userDefineColor);
				} 
				bean.setBgColor(colorMap.get(colorName));
			}
		} 
		 updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(imageChooseButton)) {
			JFileChooser fileChooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & GIF Images", "jpg", "gif", "png");
		    fileChooser.setFileFilter(filter);
		    int returnVal = fileChooser.showOpenDialog(this);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	String path = fileChooser.getSelectedFile().getAbsolutePath();
		    	imagePathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
		    	Image image = Toolkit.getDefaultToolkit().getImage(path);
		    	bean.setBGImage(image);
		    }
		    updateUI();
		}
	}

	@Override
	public PropertyBean getPropertyBean() {
		return this.bean;
	}
	
}
