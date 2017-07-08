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
	 * ��䷽ʽ.
	 */
	private JComboBox fillTypeComBox = null;

	/**
	 * ��̬�������.
	 */
	private JPanel dynamicBackGroundPanel = null;
	
	/**
	 * ����.
	 */
	private CardLayout layout = new CardLayout();
	
	/**
	 * ɫ��ѡ������ģ������.
	 */
	private List<HashMap> list = null;
	
	/**
	 * ��ɫ��Ͽ�model.
	 */
	private MapTypeComboxModel model = null;

	/**
	 * ͼƬ·���ı���.
	 */
	private JTextField imagePathTextField = null;
	
	/**
	 * ͼƬѡ��ť.
	 */
	private JButton imageChooseButton = null;
	
	/**
	 * ��ɫ��Ͽ�
	 */
	private JComboBox singleColorCombox;
	
	/**
	 * ���������.
	 */
	private LinePropertyPanel linePropertyPanel = null;
	
	/**
	 * ɫ�ʵ��ַ�����Ӧmap.
	 */
	private HashMap<String, Color> colorMap = new HashMap<String, Color>(); 
	
	/**
	 * ��ɫ����.
	 */
	private ArrayList<Color> colorList = new ArrayList<Color>(); 
	
	/**
	 * ��������JavaBean.
	 */
	private BackgroundPropertyBean bean = null;
	
	/**
	 * ����Ԥ�����.
	 */
	private BackgroundPreViewPanel backgroundPreViewPanel = null;
	
	/**
	 * ����Ԥ�����.
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
	 * ����ֵ.
	 */
	public void setValues() {
		int fillType = bean.getFillType();
		fillTypeComBox.setSelectedIndex(fillType);
		switch (fillType) {
		case 0://��ɫ
		{
			if(!colorMap.containsValue(bean.getBgColor())) {
				HashMap<String, Color> map = new HashMap<String, Color>(); 
				map.put(bean.getBgColor().toString(), (Color) bean.getBgColor());
				singleColorCombox.addItem(map);
			}
			singleColorCombox.setSelectedIndex(colorList.indexOf(bean.getBgColor()));
			break;
		}
		case 1: //����ɫ
			break;
		case 2://ͼƬ
			imagePathTextField.setText("");
			imageBackgroundPreViewPanel.updateUI();
			break;
		case 3://͸��
			break;
		}
	}

	/**
	 * ��ʼ�����.
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
	 * ����Ԥ�����.
	 * @return
	 */
	private Component getBackgroundPreViewPanel() {
		if (null == backgroundPreViewPanel) {
			backgroundPreViewPanel = new BackgroundPreViewPanel(bean);
		}
		return backgroundPreViewPanel;
	}

	/**
	 * �õ��м�����.
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
	 * �м����.
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
	 * ͸��Panel�����.
	 * @return
	 */
	private Component getTransparentPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(getTransparentLinePanel());
		return panel;
	}
	
	/**
	 * ͸�����.
	 * @return
	 */
	private Component getTransparentLinePanel() {
		if (null == linePropertyPanel) {
			linePropertyPanel = new LinePropertyPanel(bean.getLineProperty());
		}
		return linePropertyPanel;
		
	}

	/**
	 * ����ͼƬ���.
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
	 * �õ�����ͼƬ·���ı���.
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
	 * �õ�����ͼƬѡ��ť.
	 * @return
	 */
	private Component getImagePathTextField() {
		if (null == imagePathTextField) {
			imagePathTextField = new JTextField(20);
		}
		return imagePathTextField;
	
	}

	/**
	 * �õ���ɫ�������.
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
	 * ɫ��ѡ���.
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
	 * �õ���䷽ʽpanel.
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
	 * �õ���䷽ʽ��Ͽ�.
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
					Color userDefineColor = JColorChooser.showDialog(this, "ѡ���Զ�����ɫ", Color.BLACK);
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
