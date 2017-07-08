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
	 * 新建菜单.
	 * @return
	 */
	private JMenu getFileMenu() {
		JMenu fileMenu = new JMenu("文件");
		fileMenu.setMnemonic('F');
		
		//新建菜单
		JMenuItem createNewItem = new JMenuItem("新建");
		createNewItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		createNewItem.setMnemonic('N');
		createNewItem.addActionListener(this);
		createNewItem.setIcon(new ImageIcon(getClass().getResource("/images/New.gif")));
		fileMenu.add(createNewItem);
		
		//打开菜单
		JMenu openMenu = new JMenu("打开");
		openMenu.setIcon(new ImageIcon(getClass().getResource("/images/Open.gif")));
		
		//本地打开
		JMenuItem localOpenItem = new JMenuItem("本地");
		localOpenItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		localOpenItem.setMnemonic('O');
		localOpenItem.addActionListener(this);
		openMenu.add(localOpenItem);
		
		//远程打开
		JMenuItem remoteOpenItem = new JMenuItem("远程");
		remoteOpenItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_O, ActionEvent.ALT_MASK));
		remoteOpenItem.setMnemonic('O');
		remoteOpenItem.addActionListener(this);
		openMenu.add(remoteOpenItem);
		fileMenu.add(openMenu);
		
		//保存菜单
		JMenu saveMenu = new JMenu("保存");
		saveMenu.setIcon(new ImageIcon(getClass().getResource("/images/Save.gif")));
		
		//本地保存
		JMenuItem saveLocalItem = new JMenuItem("本地");
		saveLocalItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveLocalItem.setMnemonic('S');
		saveLocalItem.addActionListener(this);
		saveMenu.add(saveLocalItem);
		
		//远程保存
		JMenuItem remoteSaveItem = new JMenuItem("远程");
		remoteSaveItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.ALT_MASK));
		remoteSaveItem.setMnemonic('S');
		remoteSaveItem.addActionListener(this);
		saveMenu.add(remoteSaveItem);
		fileMenu.add(saveMenu);
		
		//另存为菜单
		JMenu saveAsMenu = new JMenu("另存为");
		saveAsMenu.setIcon(new ImageIcon(getClass().getResource("/images/Save.gif")));
		
		//本地另存为
		JMenuItem saveAsLocalItem = new JMenuItem("本地");
		saveAsLocalItem.addActionListener(this);
		saveAsLocalItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		saveAsLocalItem.setMnemonic('A');
		saveAsMenu.add(saveAsLocalItem);
		
		//远程另存为
		JMenuItem remoteAsSaveItem = new JMenuItem("远程");
		remoteAsSaveItem.addActionListener(this);
		saveAsMenu.add(remoteAsSaveItem);
		remoteAsSaveItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, ActionEvent.ALT_MASK));
		remoteAsSaveItem.setMnemonic('A');
		fileMenu.add(saveAsMenu);
		
		fileMenu.add(new JSeparator());

		JMenuItem quitItem = new JMenuItem("退出");
		quitItem.addActionListener(this);
		fileMenu.add(quitItem);
		return fileMenu;
	}

	/**
	 * 得到编辑菜单.
	 * @return
	 */
	private JMenu getEditMenu() {
		JMenu editMenu = new JMenu("编辑");
		JMenu insertMenu = new JMenu("插入");
		
		//直线菜单
		JMenuItem lineItem = new JMenuItem("线");
		lineItem.setIcon(new ImageIcon(getClass().getResource("/images/Line.gif")));
		lineItem.addActionListener(this);
		editMenu.add(lineItem);
		
		editMenu.add(new JSeparator());
		
		//文本域菜单
		JMenuItem textFieldItem = new JMenuItem("文本域");
		textFieldItem.setIcon(new ImageIcon(getClass().getResource("/images/TextField.gif")));
		textFieldItem.addActionListener(this);
		insertMenu.add(textFieldItem);
		
		//数据域菜单
		JMenuItem dataFieldItem = new JMenuItem("数据域");
		dataFieldItem.setIcon(new ImageIcon(getClass().getResource("/images/DataField.gif")));
		dataFieldItem.addActionListener(this);
		insertMenu.add(dataFieldItem);
				
		//焦点域菜单
		JMenuItem focusFieldItem = new JMenuItem("焦点域");
		focusFieldItem.setIcon(new ImageIcon(getClass().getResource("/images/FocusField.gif")));
		focusFieldItem.addActionListener(this);
		insertMenu.add(focusFieldItem);
				
		//图例菜单
		JMenuItem legendItem = new JMenuItem("图例");
		legendItem.setIcon(new ImageIcon(getClass().getResource("/images/ActionState.gif")));
		legendItem.addActionListener(this);
		insertMenu.add(legendItem);
				
		//测点描述
		JMenuItem keyidDescItem = new JMenuItem("测点描述");
		keyidDescItem.setIcon(new ImageIcon(getClass().getResource("/images/ActionState.gif")));
		keyidDescItem.addActionListener(this);
		insertMenu.add(keyidDescItem);
		
		editMenu.add(insertMenu);
		
		//增加曲线菜单
		JMenuItem addCurveItem = new JMenuItem("增加曲线");
		addCurveItem.setIcon(new ImageIcon(getClass().getResource("/images/Curve.gif")));
		addCurveItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		addCurveItem.setMnemonic('I');
		editMenu.add(addCurveItem);
		
		editMenu.add(new JSeparator());
		
		//删除菜单
		JMenuItem deleteItem = new JMenuItem("删除");
		deleteItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_DELETE, 0));
		deleteItem.setMnemonic('D');
		editMenu.add(deleteItem);
		
		return editMenu;
	}
	
	/**
	 * "视图"菜单
	 * 
	 * @return
	 */
	private JMenu getViewMenu() {
		JMenu viewMenu = new JMenu("视图");
		// 工具栏菜单
		JCheckBoxMenuItem toolBarItem = new JCheckBoxMenuItem("工具条", true);
		toolBarItem.setMnemonic('T');
		toolBarItem.addActionListener(this);
		viewMenu.add(toolBarItem);

		// 状态栏菜单
		JCheckBoxMenuItem statusBarItem = new JCheckBoxMenuItem("状态条", true);
		statusBarItem.setMnemonic('S');
		statusBarItem.addActionListener(this);
		viewMenu.add(statusBarItem);
		
		// 图元列表菜单
		JCheckBoxMenuItem entityListItem = new JCheckBoxMenuItem("图元列表",
				false);
		entityListItem.setMnemonic('F');
		entityListItem.addActionListener(this);
		viewMenu.add(entityListItem);
		
		viewMenu.addSeparator();
		
		// 显示属性菜单
		JMenuItem showPropertyItem = new JMenuItem("显示属性");
		showPropertyItem.setMnemonic('P');
		viewMenu.add(showPropertyItem);
		return viewMenu;
	}
	
	/**
	 * "帮助"菜单
	 * 
	 * @return
	 */
	private JMenu getHelpMenu() {
		JMenu helpMenu = new JMenu("帮助");
		helpMenu.setMnemonic('H');
		// 关于菜单
		JMenuItem aboutItem = new JMenuItem("关于");
		aboutItem.addActionListener(this);
		helpMenu.add(aboutItem);
		return helpMenu;
	}
	
	/**
	 * 主界面.
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
	 * 得到jfreechart.
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
		TextFieldTitle title1 = new TextFieldTitle(new Rectangle2D.Double(5,5,40,25),"积分电量");
		TextFieldTitle title2 = new TextFieldTitle(new Rectangle2D.Double(80,15,40,25),"最大值");
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
