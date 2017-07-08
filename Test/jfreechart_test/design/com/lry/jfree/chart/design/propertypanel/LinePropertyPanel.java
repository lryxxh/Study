package design.com.lry.jfree.chart.design.propertypanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import design.com.lry.jfree.chart.design.enums.LineStyleEnum;
import design.com.lry.jfree.chart.design.factory.FinalStaticFactory;
import design.com.lry.jfree.chart.design.model.MapTypeComboxModel;
import design.com.lry.jfree.chart.design.propertybean.LinePropertyBean;
import design.com.lry.jfree.chart.design.propertybean.PropertyBean;
import design.com.lry.jfree.chart.design.ui.ColorListCellRenderer;
import design.com.lry.jfree.chart.design.ui.LineViewPanel;
import design.com.lry.jfree.chart.design.ui.PropertyPanel;

/**
 * ���������.
 * @author LRY
 *
 */
@SuppressWarnings("unchecked")
public class LinePropertyPanel extends PropertyPanel implements ItemListener, ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * �߿���Ͽ�.
	 */
	private JSpinner lineWidthSpinner;

	/**
	 * ��ɫ��Ͽ�.
	 */
	private JComboBox lineColorCombox;

	/**
	 * ������Ͽ�.
	 */
	private JComboBox lineTypeCombox;
	
	/**
	 * ��ɫ��Ͽ�ģ��.
	 */
	private MapTypeComboxModel colorModel = null;
	
	private List<Color> colorList = null; 
	
	
	/**
	 * Ԥ�����
	 */
	private JPanel viewPanel;
	
	LinePropertyBean bean = new LinePropertyBean();

	public LinePropertyPanel() {
		this(new LinePropertyBean());
	}
	
	public LinePropertyPanel(LinePropertyBean bean) {
		this.bean = bean;
		this.initComponents();
		this.setValues();
	}
	
	public void setValues() {
		Paint color = bean.getLineColor();
		int index = colorList.indexOf(color);
		float width = bean.getLineWidth();
		width = width == 0.0f ? 1.0f : width; 
		float[] dash = bean.getStrokeDash();
		HashMap<String, Color> map = new HashMap<String, Color>(); 
		if (index == -1) {
			map.put("undefined", (Color) color);
			lineColorCombox.addItem(map);
		} else {
			map = (HashMap<String, Color>) colorModel.getElementAt(index);
		}
		lineColorCombox.setSelectedItem(map);
		lineWidthSpinner.setValue(Float.valueOf(width).intValue());
		lineTypeCombox.setSelectedItem(LineStyleEnum.getNameByDash(dash));
	}

	/**
	 * ��ʼ�����.
	 */
	public void initComponents() {
		init();
		setLayout(new BorderLayout());
		add(getPropertyPanel(), BorderLayout.NORTH);
		add(getPreViewPanel(), BorderLayout.CENTER);
	}

	private void init() {
		colorModel = new MapTypeComboxModel(FinalStaticFactory.getColorModelData());
		colorList = FinalStaticFactory.getColorList(); 
		
	}

	/**
	 * Ԥ�������.
	 * @return
	 */
	private Component getPreViewPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder(FinalStaticFactory.PREVIEW_LABEL));
		panel.add(getViewPanel());
		return panel;
	}

	/**
	 * Ԥ�����.
	 * @return
	 */
	private Component getViewPanel() {
		if(null == viewPanel) {
			viewPanel = new LineViewPanel(bean);
		}
		return viewPanel;
	}

	/**
	 * �õ��������.
	 * @return
	 */
	private Component getPropertyPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		panel.add(getLineTypePanel());
		panel.add(getLineColorPanel());
		panel.add(getLineWidthPanel());
		return panel;
	}

	/**
	 * �õ��߿����.
	 * @return
	 */
	private Component getLineWidthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel(FinalStaticFactory.LINE_WIDTH_LABEL));
		panel.add(getLineWidthSpinner());
		return panel;
	}

	/**
	 * �߿�.
	 * @return
	 */
	private Component getLineWidthSpinner() {
		if (null == lineWidthSpinner) {
			lineWidthSpinner = new JSpinner();
			lineWidthSpinner.setModel(new SpinnerNumberModel());
			lineWidthSpinner.setPreferredSize(new Dimension(50, 25));
			lineWidthSpinner.addChangeListener(this);
			lineWidthSpinner.setValue(1);
		}
		return lineWidthSpinner;
	}

	/**
	 * �õ���ɫ���.
	 * @return
	 */
	private Component getLineColorPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel(FinalStaticFactory.LINE_COLOR_LABEL));
		panel.add(getLineColorCombox());
		return panel;
	}

	private Component getLineColorCombox() {
		if (null == lineColorCombox) {
			lineColorCombox = new JComboBox(colorModel);
			lineColorCombox.setRenderer(new ColorListCellRenderer());
			lineColorCombox.setPreferredSize(new Dimension(120, 25));
			lineColorCombox.addItemListener(this);
		}
		return lineColorCombox;
	}

	/**
	 * �õ��������.
	 * @return
	 */
	private Component getLineTypePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel(FinalStaticFactory.LINE_TYPE_LABEL));
		panel.add(getLineTypeCombox());
		return panel;
	}

	private Component getLineTypeCombox() {
		if (null == lineTypeCombox) {
			lineTypeCombox = new JComboBox(FinalStaticFactory.getLineTypeArray());
			lineTypeCombox.addItemListener(this);
		}
		return lineTypeCombox;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new LinePropertyPanel());
		frame.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			if(e.getSource().equals(lineColorCombox)) {
				HashMap<String, Color> colorMap = (HashMap<String, Color>) lineColorCombox.getSelectedItem(); 
				String colorName = colorMap.keySet().iterator().next();
				if (colorName.equals(FinalStaticFactory.USER_DEFINE_COLOR)) {
					Color userDefineColor = JColorChooser.showDialog(this, "ѡ���Զ�����ɫ", Color.BLACK);
					colorMap.put(colorName, userDefineColor);
				} 
				bean.setLineColor(colorMap.get(colorName));
			} else if (e.getSource().equals(lineTypeCombox)) {
				int index = lineTypeCombox.getSelectedIndex();
				float[] dash = LineStyleEnum.getEnumByNo(index).getDash();
				bean.setStrokeDash(dash);
			}
			viewPanel.updateUI();
		} 		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource().equals(lineWidthSpinner)) {
			int width = (Integer) lineWidthSpinner.getValue();
			width = width < 0 ? 0 : width;
			bean.setLineWidth(width);
			updateUI();
		}
	}
	
	@Override
	public PropertyBean getPropertyBean() {
		return this.bean;
	}
}
