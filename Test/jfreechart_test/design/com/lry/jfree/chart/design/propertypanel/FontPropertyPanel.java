package design.com.lry.jfree.chart.design.propertypanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import design.com.lry.jfree.chart.design.factory.FinalStaticFactory;
import design.com.lry.jfree.chart.design.model.MapTypeComboxModel;
import design.com.lry.jfree.chart.design.propertybean.FontPropertyBean;
import design.com.lry.jfree.chart.design.propertybean.PropertyBean;
import design.com.lry.jfree.chart.design.ui.ColorListCellRenderer;
import design.com.lry.jfree.chart.design.ui.FontPreViewPanel;
import design.com.lry.jfree.chart.design.ui.PropertyPanel;

@SuppressWarnings({"rawtypes", "unused"})
public class FontPropertyPanel extends PropertyPanel implements ItemListener, ChangeListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -168621871991339137L;
	
	/**
	 * ��������bean.
	 */
	FontPropertyBean bean = new FontPropertyBean();
	
	/**
	 * ����������Ͽ�.
	 */
	private JComboBox fontListComBox = null;
	
	/**
	 * ������ɫ��Ͽ�.
	 */
	private JComboBox fontColorComboBox = null;
	
	/**
	 * �����С.
	 */
	private JSpinner fontSizeSpinner = null;
	
	/**
	 * ������帴ѡ��.
	 */
	private JCheckBox boldFontCheckBox = null;
	
	/**
	 * ����б�帴ѡ��.
	 */
	private JCheckBox italicFontCheckBox = null;
	
	/**
	 * ������ɫģ��.
	 */
	private MapTypeComboxModel fontColorModel = null; 
	
	/**
	 * ��ɫ����.
	 */
	private ArrayList<Color> colorList = new ArrayList<Color>(); 
	
	/**
	 * ��ɫ����map.
	 */
	private HashMap<String, Color> colorMap = new HashMap<String, Color>(); 
	
	/**
	 * ������ɫģ������.
	 */
	private List<HashMap> colorModelData = null; 
	
	/**
	 * ������������.
	 */
	private String[] fontNames = null;
	
	private JPanel fontPreViewPanel = null;
	
	public FontPropertyPanel(){
		this(new FontPropertyBean());
	}

	public FontPropertyPanel(FontPropertyBean bean) {
		this.bean = bean;
		initComponents();
		setValues();
	}

	@Override
	public void initComponents() {
		init();
		this.setLayout(new BorderLayout());
		this.add(getFontPropertyPanel(), BorderLayout.CENTER);
//		this.add(getFontPreViewPanel(), BorderLayout.SOUTH);
	}
	
	private Component getFontPreViewPanel() {
		if (null == fontPreViewPanel) {
			fontPreViewPanel = new FontPreViewPanel(bean);
		}
		return fontPreViewPanel;
	}

	private Component getFontPropertyPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		JPanel nameColorPanel = new JPanel();
		nameColorPanel.setLayout(new GridLayout(1, 2));
		nameColorPanel.add(getFontNamePanel());
		nameColorPanel.add(getFontColorPanel());
		JPanel fontSizeStylePanel = new JPanel();
		fontSizeStylePanel.setLayout(new GridLayout(1, 2));
		fontSizeStylePanel.add(getFontSizePanel());
		fontSizeStylePanel.add(getFontStylePanel());
		panel.add(nameColorPanel);
		panel.add(fontSizeStylePanel);
		panel.add(getFontPreViewPanel());
		return panel;
	}

	/**
	 * �õ�������ʽ���.
	 * @return
	 */
	private Component getFontStylePanel() {
		JPanel panel = new JPanel();
		JLabel fontStyleLabel = new JLabel(FinalStaticFactory.SIZE_LABEL);
		panel.add(fontStyleLabel);
		panel.add(getBoldFontCheckBox());
		panel.add(getItalicFontCheckBox());
		return panel;
	}

	private Component getBoldFontCheckBox() {
		if(null == boldFontCheckBox) {
			boldFontCheckBox = new JCheckBox(FinalStaticFactory.FONT_BOLD_STYLE_LABEL);
		}
		return boldFontCheckBox;
	}
	
	private Component getItalicFontCheckBox() {
		if(null == italicFontCheckBox) {
			italicFontCheckBox = new JCheckBox(FinalStaticFactory.FONT_ITALIC_STYLE_LABEL);
		}
		return italicFontCheckBox;
	}

	/**
	 * �õ������С���.
	 * @return
	 */
	private Component getFontSizePanel() {
		JPanel panel = new JPanel();
		JLabel fontSizeLabel = new JLabel(FinalStaticFactory.SIZE_LABEL);
		panel.add(fontSizeLabel);
		panel.add(getFontSizeSpinner());
		return panel;
	}

	/**
	 * �õ������СSpinner.
	 * @return
	 */
	private Component getFontSizeSpinner() {
		if (null == fontSizeSpinner) {
			fontSizeSpinner = new JSpinner(new SpinnerNumberModel());
		}
		fontSizeSpinner.setValue(12);
		fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
		return fontSizeSpinner;
	}

	/**
	 * �õ�������ɫ���.
	 * @return
	 */
	private Component getFontColorPanel() {
		JPanel panel = new JPanel();
		JLabel fontColorLabel = new JLabel(FinalStaticFactory.COLOR_LABEL);
		panel.add(fontColorLabel);
		panel.add(getFontColorCombox());
		return panel;
	}

	/**
	 * �õ�������ɫ��Ͽ�.
	 * @return
	 */
	private Component getFontColorCombox() {
		if(null == fontColorComboBox) {
			fontColorComboBox = new JComboBox(fontColorModel);
			fontColorComboBox.setRenderer(new ColorListCellRenderer());
		}
		fontColorComboBox.setPreferredSize(new Dimension(120, 25));
		return fontColorComboBox;
	}

	/**
	 * �õ������������.
	 * @return
	 */
	private Component getFontNamePanel() {
		JPanel panel = new JPanel();
		JLabel fontNameLabel = new JLabel(FinalStaticFactory.NAME_LABEL);
		panel.add(fontNameLabel);
		panel.add(getFontNameCombox());
		return panel;
	}

	/**
	 * �õ�����������Ͽ�.
	 * @return
	 */
	private Component getFontNameCombox() {
		if (null == fontListComBox) {
			fontListComBox = new JComboBox(fontNames);
		}
		fontListComBox.setPreferredSize(new Dimension(150, 25));
		return fontListComBox;
	}

	/**
	 * ��ʼ����Ա����.
	 */
	private void init() {
		fontNames = FinalStaticFactory.getFontNameList();
		colorList = FinalStaticFactory.getColorList();
		colorMap = FinalStaticFactory.getColorHashMap();
		colorModelData = FinalStaticFactory.getColorModelData();
		fontColorModel = new MapTypeComboxModel(colorModelData);
		fontListComBox = new JComboBox(fontNames);
		fontColorComboBox = new JComboBox(fontColorModel);
		fontColorComboBox.setRenderer(new ColorListCellRenderer());
		fontSizeSpinner = new JSpinner(new SpinnerNumberModel());
		fontSizeSpinner.setValue(12);
		boldFontCheckBox = new JCheckBox(FinalStaticFactory.FONT_BOLD_STYLE_LABEL);
		italicFontCheckBox = new JCheckBox(FinalStaticFactory.FONT_ITALIC_STYLE_LABEL);
		addListener();
	}

	private void addListener() {
		fontColorComboBox.addItemListener(this);
		fontListComBox.addItemListener(this);
		fontSizeSpinner.addChangeListener(this);
		boldFontCheckBox.addItemListener(this);
		italicFontCheckBox.addItemListener(this);
	}

	@Override
	public void setValues() {
		super.setValues();
		fontListComBox.setSelectedItem(bean.getFontName());
		fontColorComboBox.setSelectedIndex(colorList.indexOf(bean.getFontColor()));
		fontSizeSpinner.setValue(((Float)bean.getFontSize()).intValue());
		boldFontCheckBox.setSelected(bean.getFont().isBold());
		italicFontCheckBox.setSelected(bean.getFont().isItalic());
	}

	@Override
	public PropertyBean getPropertyBean() {
		return bean;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new FontPropertyPanel());
		frame.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Object object = e.getSource();
		if (object.equals(fontSizeSpinner)) {
			int fontSize = (Integer) fontSizeSpinner.getValue();
			fontSize = fontSize <= 0 ? 1 : fontSize;
			bean.setFontSize(fontSize);
		}
		updateUI();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object object = e.getSource();
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if(object.equals(fontListComBox)) {
				bean.setFontName((String) fontListComBox.getSelectedItem());
			} else if (object.equals(fontColorComboBox)) {
				HashMap<String, Color> colorMap = (HashMap<String, Color>) fontColorComboBox.getSelectedItem(); 
				String colorName = colorMap.keySet().iterator().next();
				if (colorName.equals(FinalStaticFactory.USER_DEFINE_COLOR)) {
					Color userDefineColor = JColorChooser.showDialog(this, "ѡ���Զ�����ɫ", Color.BLACK);
					colorMap.put(colorName, userDefineColor);
				} 
				bean.setFontColor(colorMap.get(colorName));
			} else if (object.equals(boldFontCheckBox) || object.equals(italicFontCheckBox)) {
				int boldStyle = boldFontCheckBox.isSelected() ? Font.BOLD : Font.PLAIN;
				int italicStyle = italicFontCheckBox.isSelected() ? Font.ITALIC : Font.PLAIN;
				bean.setFontStyle(boldStyle | italicStyle);
			}
		}
		updateUI();
	}
}
