package design.com.lry.jfree.chart.design;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import design.com.lry.jfree.chart.design.manager.EntityMouseManager;
import design.com.lry.jfree.chart.design.title.TextFieldTitle;

public class JFreeChartDesignFrame extends JFrame implements ActionListener, MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3826099799277360270L;

	public JFreeChartDesignFrame() {
		init();
	}
	
	private ChartPanel chartPanel = null;
	private JFreeChart chart = null;
	
	private void init() {
		Dimension frameSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets insetSize = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		int width = (int)frameSize.getWidth() - insetSize.left - insetSize.right;
		int height = (int)frameSize.getHeight() - insetSize.top - insetSize.bottom;
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(getMainPanel());
		addMenu();
		setVisible(true);
	}

	private void addMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(getFileMenu());
		menuBar.add(getEditMenu());
		menuBar.add(getViewMenu());
		menuBar.add(getHelpMenu());
		setJMenuBar(menuBar);
	}
	
	/**
	 * �½��˵�.
	 * @return
	 */
	private JMenu getFileMenu() {
		JMenu fileMenu = new JMenu("�ļ�");
		fileMenu.setMnemonic('F');
		
		//�½��˵�
		JMenuItem createNewItem = new JMenuItem("�½�");
		createNewItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		createNewItem.setMnemonic('N');
		createNewItem.addActionListener(this);
		createNewItem.setIcon(new ImageIcon(getClass().getResource("/images/New.gif")));
		fileMenu.add(createNewItem);
		
		//�򿪲˵�
		JMenu openMenu = new JMenu("��");
		openMenu.setIcon(new ImageIcon(getClass().getResource("/images/Open.gif")));
		
		//���ش�
		JMenuItem localOpenItem = new JMenuItem("����");
		localOpenItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		localOpenItem.setMnemonic('O');
		localOpenItem.addActionListener(this);
		openMenu.add(localOpenItem);
		
		//Զ�̴�
		JMenuItem remoteOpenItem = new JMenuItem("Զ��");
		remoteOpenItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_O, ActionEvent.ALT_MASK));
		remoteOpenItem.setMnemonic('O');
		remoteOpenItem.addActionListener(this);
		openMenu.add(remoteOpenItem);
		fileMenu.add(openMenu);
		
		//����˵�
		JMenu saveMenu = new JMenu("����");
		saveMenu.setIcon(new ImageIcon(getClass().getResource("/images/Save.gif")));
		
		//���ر���
		JMenuItem saveLocalItem = new JMenuItem("����");
		saveLocalItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveLocalItem.setMnemonic('S');
		saveLocalItem.addActionListener(this);
		saveMenu.add(saveLocalItem);
		
		//Զ�̱���
		JMenuItem remoteSaveItem = new JMenuItem("Զ��");
		remoteSaveItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.ALT_MASK));
		remoteSaveItem.setMnemonic('S');
		remoteSaveItem.addActionListener(this);
		saveMenu.add(remoteSaveItem);
		fileMenu.add(saveMenu);
		
		//���Ϊ�˵�
		JMenu saveAsMenu = new JMenu("���Ϊ");
		saveAsMenu.setIcon(new ImageIcon(getClass().getResource("/images/Save.gif")));
		
		//�������Ϊ
		JMenuItem saveAsLocalItem = new JMenuItem("����");
		saveAsLocalItem.addActionListener(this);
		saveAsLocalItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		saveAsLocalItem.setMnemonic('A');
		saveAsMenu.add(saveAsLocalItem);
		
		//Զ�����Ϊ
		JMenuItem remoteAsSaveItem = new JMenuItem("Զ��");
		remoteAsSaveItem.addActionListener(this);
		saveAsMenu.add(remoteAsSaveItem);
		remoteAsSaveItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, ActionEvent.ALT_MASK));
		remoteAsSaveItem.setMnemonic('A');
		fileMenu.add(saveAsMenu);
		
		fileMenu.add(new JSeparator());

		JMenuItem quitItem = new JMenuItem("�˳�");
		quitItem.addActionListener(this);
		fileMenu.add(quitItem);
		return fileMenu;
	}

	/**
	 * �õ��༭�˵�.
	 * @return
	 */
	private JMenu getEditMenu() {
		JMenu editMenu = new JMenu("�༭");
		JMenu insertMenu = new JMenu("����");
		
		//ֱ�߲˵�
		JMenuItem lineItem = new JMenuItem("��");
		lineItem.setIcon(new ImageIcon(getClass().getResource("/images/Line.gif")));
		lineItem.addActionListener(this);
		editMenu.add(lineItem);
		
		editMenu.add(new JSeparator());
		
		//�ı���˵�
		JMenuItem textFieldItem = new JMenuItem("�ı���");
		textFieldItem.setIcon(new ImageIcon(getClass().getResource("/images/TextField.gif")));
		textFieldItem.addActionListener(this);
		insertMenu.add(textFieldItem);
		
		//������˵�
		JMenuItem dataFieldItem = new JMenuItem("������");
		dataFieldItem.setIcon(new ImageIcon(getClass().getResource("/images/DataField.gif")));
		dataFieldItem.addActionListener(this);
		insertMenu.add(dataFieldItem);
				
		//������˵�
		JMenuItem focusFieldItem = new JMenuItem("������");
		focusFieldItem.setIcon(new ImageIcon(getClass().getResource("/images/FocusField.gif")));
		focusFieldItem.addActionListener(this);
		insertMenu.add(focusFieldItem);
				
		//ͼ���˵�
		JMenuItem legendItem = new JMenuItem("ͼ��");
		legendItem.setIcon(new ImageIcon(getClass().getResource("/images/ActionState.gif")));
		legendItem.addActionListener(this);
		insertMenu.add(legendItem);
				
		//�������
		JMenuItem keyidDescItem = new JMenuItem("�������");
		keyidDescItem.setIcon(new ImageIcon(getClass().getResource("/images/ActionState.gif")));
		keyidDescItem.addActionListener(this);
		insertMenu.add(keyidDescItem);
		
		editMenu.add(insertMenu);
		
		//�������߲˵�
		JMenuItem addCurveItem = new JMenuItem("��������");
		addCurveItem.setIcon(new ImageIcon(getClass().getResource("/images/Curve.gif")));
		addCurveItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		addCurveItem.setMnemonic('I');
		editMenu.add(addCurveItem);
		
		editMenu.add(new JSeparator());
		
		//ɾ���˵�
		JMenuItem deleteItem = new JMenuItem("ɾ��");
		deleteItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_DELETE, 0));
		deleteItem.setMnemonic('D');
		editMenu.add(deleteItem);
		
		return editMenu;
	}
	
	/**
	 * "��ͼ"�˵�
	 * 
	 * @return
	 */
	private JMenu getViewMenu() {
		JMenu viewMenu = new JMenu("��ͼ");
		// �������˵�
		JCheckBoxMenuItem toolBarItem = new JCheckBoxMenuItem("������", true);
		toolBarItem.setMnemonic('T');
		toolBarItem.addActionListener(this);
		viewMenu.add(toolBarItem);

		// ״̬���˵�
		JCheckBoxMenuItem statusBarItem = new JCheckBoxMenuItem("״̬��", true);
		statusBarItem.setMnemonic('S');
		statusBarItem.addActionListener(this);
		viewMenu.add(statusBarItem);
		
		// ͼԪ�б�˵�
		JCheckBoxMenuItem entityListItem = new JCheckBoxMenuItem("ͼԪ�б�",
				false);
		entityListItem.setMnemonic('F');
		entityListItem.addActionListener(this);
		viewMenu.add(entityListItem);
		
		viewMenu.addSeparator();
		
		// ��ʾ���Բ˵�
		JMenuItem showPropertyItem = new JMenuItem("��ʾ����");
		showPropertyItem.setMnemonic('P');
		viewMenu.add(showPropertyItem);
		return viewMenu;
	}
	
	/**
	 * "����"�˵�
	 * 
	 * @return
	 */
	private JMenu getHelpMenu() {
		JMenu helpMenu = new JMenu("����");
		helpMenu.setMnemonic('H');
		// ���ڲ˵�
		JMenuItem aboutItem = new JMenuItem("����");
		aboutItem.addActionListener(this);
		helpMenu.add(aboutItem);
		return helpMenu;
	}
	
	/**
	 * ������.
	 * @return
	 */
	private JPanel getMainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		chartPanel = new ChartPanel(getJFreeChart());
		chartPanel.setBounds(0, 0, 800, 600);
		for(MouseListener listener : chartPanel.getMouseListeners()) {
			chartPanel.removeMouseListener(listener);
		}
		for(MouseMotionListener listener : chartPanel.getMouseMotionListeners()) {
			chartPanel.removeMouseMotionListener(listener);
		}
		chartPanel.addMouseListener(this);
		chartPanel.addMouseMotionListener(this);
		panel.add(chartPanel);
//		panel.setBackground(Color.GREEN);
		return panel;
	}
	
	/**
	 * �õ�jfreechart.
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		if(null == chart) {
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			XYSeries series = new XYSeries("");
			series.add(1, 1);
			series.add(2, 2);
			series.add(3, 3);
			series.add(4, 4);
			series.add(5, 5);
			series.add(6, 6);
			dataset.addSeries(series);
//			chart = ChartFactory.createTimeSeriesChart("", "x", "y", null, true, false, false);
			chart = ChartFactory.createXYLineChart("", "", "", null, PlotOrientation.VERTICAL,true, false, false);
			chart.addSubtitle(new TextTitle(" "));
			chart.addSubtitle(new TextTitle(" "));
		}
		chart.setBackgroundPaint(Color.green);
//		chart.getXYPlot().setBackgroundPaint(Color.YELLOW);
		chart.getXYPlot().setOutlineVisible(false);
		TextFieldTitle title1 = new TextFieldTitle(new Rectangle2D.Double(5,5,40,25),"���ֵ���");
		TextFieldTitle title2 = new TextFieldTitle(new Rectangle2D.Double(80,15,40,25),"���ֵ");
		title1.setPaint(Color.YELLOW);
		title2.setPaint(Color.WHITE);
		ValueAxis axis2 = new NumberAxis("e");
		ValueAxis axis3 = new NumberAxis("dd");
		chart.getXYPlot().setDomainAxis(1, axis2);
		chart.getXYPlot().setDomainAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT);
		chart.getXYPlot().setDomainAxis(2, axis3);
		chart.getXYPlot().setDomainAxisLocation(2, AxisLocation.BOTTOM_OR_LEFT);
//		chart.getXYPlot().getDomainAxis().setLabel(label);
//		chart.getXYPlot().setBackgroundAlpha(0);
		chart.addSubtitle(title1);
		chart.addSubtitle(title2);
		chart.getXYPlot().setDomainZeroBaselinePaint(Color.RED);
		return chart;
	}

	public static void main(String[] args) {
		JFreeChartDesignFrame frame = new JFreeChartDesignFrame();
		System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
		System.out.println(Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration()));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	boolean ismove = false;
	ChartEntity entity = null;
	boolean isResized = false;
	@Override
	public void mouseClicked(MouseEvent e) {
		EntityMouseManager.mouseClicked(entity, e, chartPanel);
		chartPanel.chartChanged(new ChartChangeEvent(chart));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		entity = chartPanel.getEntityForPoint(e.getX(), e.getY());
		EntityMouseManager.mousePressed(entity, e, chartPanel);
		chartPanel.chartChanged(new ChartChangeEvent(chart));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		EntityMouseManager.mouseReleased(entity, e, chartPanel);
		chartPanel.chartChanged(new ChartChangeEvent(chart));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	Point point = new Point();
	@Override
	public void mouseDragged(MouseEvent e) {
		EntityMouseManager.mouseDragged(entity, e, chartPanel);
		chartPanel.chartChanged(new ChartChangeEvent(chart));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
