package org.jfree.chart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.Printable;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.custom.axis.CustomDateAxis;
import org.jfree.chart.custom.entity.BeforePageEntity;
import org.jfree.chart.custom.entity.ForwadPageEntity;
import org.jfree.chart.custom.panel.CustomCrosshairOverlay;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.LegendItemEntity;
import org.jfree.chart.entity.PlotEntity;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.event.OverlayChangeListener;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.ResourceBundleWrapper;
import org.jfree.data.Range;
import org.jfree.data.general.Series;
import org.jfree.data.time.DateRange;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.TimeSeriesItem;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.custom.CustomTimeSeries;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;

import agency.exception.DataFaultException;
import agency.exception.LocatorException;
import agency.exception.ProxyFaultException;
import agency.exception.ServiceConnectionException;
import agency.message.base.DataUnit;
import agency.message.base.DomainManager;
import agency.message.base.MenuTool;
import agency.message.midhs.MidhsData;
import agency.message.midhs.MidhsService;
import agency.message.perm.PermStaticFinals;
import curve.kd.mmi.curve.jfreechart.dialog.CurveSourceDialog;
import curve.kd.mmi.curve.jfreechart.panel.CurveListPanel;
import curve.kd.mmi.curve.jfreechart.panel.CurveViewPanel;
import curve.kd.mmi.curve.jfreechart.refresher.RefreshObservable;
import curve.kd.mmi.curve.jfreechart.refresher.TimeCurveRefresherBean;
import login.LoginCtrl;
import login.MMIUser;
import support.DataClient;
import support.custominfo.PrivInfoSet;
import uiframework.uiDialogs.uiPlainDialog.UIPlainDialog;

/**
 * A Swing GUI component for displaying a {@link JFreeChart} object.
 * <P>
 * The panel registers with the chart to receive notification of changes to any
 * component of the chart. The chart is redrawn automatically whenever this
 * notification is received.
 */
public class CustomChartPanel extends ChartPanel implements
		ChartChangeListener, ChartProgressListener, ChartMouseListener,
		ActionListener, MouseListener, MouseMotionListener,
		OverlayChangeListener, Printable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6742590320920524783L;

	CustomCrosshairOverlay overlay = new CustomCrosshairOverlay();
	boolean start = false;
	Point lastPoint = new Point();
	GeneralPath shape = new GeneralPath();
	Map<Integer, Point> mouseMap = new TreeMap<Integer, Point>();

	Vector<Point> mouseMoMap = new Vector<Point>();

	Vector<Point> mousePoint = new Vector<Point>();

	/** View the series data. **/
	private final static String SERIES_DRAG_MODIFY_COMMAND = "SERIES_DRAG_MODIFY";

	/** View the series data. **/
	private final static String SERIES_POINT_MODIFY_COMMAND = "SERIES_POINT_MODIFY";

	/** View the series data. **/
	private final static String SERIES_INFO = "SERIES_INFO";

	/** View the series data. **/
	private final static String SERIES_RESCORY_COMMAND = "SERIES_RESCORY";

	/** View the series data. **/
	private final static String SERIES_SAVE_COMMAND = "SERIES_SAVE";

	/** View the series data. **/
	private final static String SERIES_DATA_COMMAND = "VIEW_DATA";

	/** View the series data. **/
	private final static String SERIES_PERIOD_SET_COMMAND = "SERIES_PERIOD_SET";

	protected static ResourceBundle localizationResources2 = ResourceBundleWrapper
			.getBundle("LocalizationBundle");

	Crosshair domainCrosshair = new Crosshair();
	private int seriesindex = 0;
	private int datasetindex = 0;
	boolean isMove = false;
//	CurveViewPanel viewPanel = new CurveViewPanel();
	int i = 0;
	private boolean modifyFlag = false;
	private boolean pointModifyFlag = false;
//	private boolean refresh = false;
	Vector<MidhsData> modifyDatas = new Vector<MidhsData>();
	CurveViewPanel viewPanel = new CurveViewPanel();
	List<Component> menuList = new ArrayList<Component>();
	/**
	 * Constructs a panel that displays the specified chart.
	 * 
	 * @param chart
	 *            the chart.
	 */
	public CustomChartPanel(JFreeChart chart, CurveViewPanel viewPanel) {

		this(chart, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_MINIMUM_DRAW_WIDTH,
				DEFAULT_MINIMUM_DRAW_HEIGHT, DEFAULT_MAXIMUM_DRAW_WIDTH,
				DEFAULT_MAXIMUM_DRAW_HEIGHT, DEFAULT_BUFFER_USED, true, // properties
				true, // save
				true, // print
				true, // zoom
				true, // tooltips
				viewPanel);

	}

	/**
	 * Constructs a panel containing a chart. The <code>useBuffer</code> flag
	 * controls whether or not an offscreen <code>BufferedImage</code> is
	 * maintained for the chart. If the buffer is used, more memory is consumed,
	 * but panel repaints will be a lot quicker in cases where the chart itself
	 * hasn't changed (for example, when another frame is moved to reveal the
	 * panel). WARNING: If you set the <code>useBuffer</code> flag to false,
	 * note that the mouse zooming rectangle will (in that case) be drawn using
	 * XOR, and there is a SEVERE performance problem with that on JRE6 on
	 * Windows.
	 * 
	 * @param chart
	 *            the chart.
	 * @param useBuffer
	 *            a flag controlling whether or not an off-screen buffer is used
	 *            (read the warning above before setting this to
	 *            <code>false</code>).
	 */
	public CustomChartPanel(JFreeChart chart, boolean useBuffer, CurveViewPanel viewPanel) {

		this(chart, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_MINIMUM_DRAW_WIDTH,
				DEFAULT_MINIMUM_DRAW_HEIGHT, DEFAULT_MAXIMUM_DRAW_WIDTH,
				DEFAULT_MAXIMUM_DRAW_HEIGHT, useBuffer, true, // properties
				true, // save
				true, // print
				true, // zoom
				true, // tooltips
				viewPanel);

	}

	/**
	 * Constructs a JFreeChart panel.
	 * 
	 * @param chart
	 *            the chart.
	 * @param properties
	 *            a flag indicating whether or not the chart property editor
	 *            should be available via the popup menu.
	 * @param save
	 *            a flag indicating whether or not save options should be
	 *            available via the popup menu.
	 * @param print
	 *            a flag indicating whether or not the print option should be
	 *            available via the popup menu.
	 * @param zoom
	 *            a flag indicating whether or not zoom options should be added
	 *            to the popup menu.
	 * @param tooltips
	 *            a flag indicating whether or not tooltips should be enabled
	 *            for the chart.
	 */
	public CustomChartPanel(JFreeChart chart, boolean properties, boolean save,
			boolean print, boolean zoom, boolean tooltips, CurveViewPanel viewPanel) {

		this(chart, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_MINIMUM_DRAW_WIDTH,
				DEFAULT_MINIMUM_DRAW_HEIGHT, DEFAULT_MAXIMUM_DRAW_WIDTH,
				DEFAULT_MAXIMUM_DRAW_HEIGHT, DEFAULT_BUFFER_USED, properties,
				save, print, zoom, tooltips, viewPanel);

	}

	/**
	 * Constructs a JFreeChart panel.
	 * 
	 * @param chart
	 *            the chart.
	 * @param width
	 *            the preferred width of the panel.
	 * @param height
	 *            the preferred height of the panel.
	 * @param minimumDrawWidth
	 *            the minimum drawing width.
	 * @param minimumDrawHeight
	 *            the minimum drawing height.
	 * @param maximumDrawWidth
	 *            the maximum drawing width.
	 * @param maximumDrawHeight
	 *            the maximum drawing height.
	 * @param useBuffer
	 *            a flag that indicates whether to use the off-screen buffer to
	 *            improve performance (at the expense of memory).
	 * @param properties
	 *            a flag indicating whether or not the chart property editor
	 *            should be available via the popup menu.
	 * @param save
	 *            a flag indicating whether or not save options should be
	 *            available via the popup menu.
	 * @param print
	 *            a flag indicating whether or not the print option should be
	 *            available via the popup menu.
	 * @param zoom
	 *            a flag indicating whether or not zoom options should be added
	 *            to the popup menu.
	 * @param tooltips
	 *            a flag indicating whether or not tooltips should be enabled
	 *            for the chart.
	 */
	public CustomChartPanel(JFreeChart chart, int width, int height,
			int minimumDrawWidth, int minimumDrawHeight, int maximumDrawWidth,
			int maximumDrawHeight, boolean useBuffer, boolean properties,
			boolean save, boolean print, boolean zoom, boolean tooltips, CurveViewPanel viewPanel) {

		this(chart, width, height, minimumDrawWidth, minimumDrawHeight,
				maximumDrawWidth, maximumDrawHeight, useBuffer, properties,
				true, save, print, zoom, tooltips, viewPanel);
	}

	/**
	 * Constructs a JFreeChart panel.
	 * 
	 * @param chart
	 *            the chart.
	 * @param width
	 *            the preferred width of the panel.
	 * @param height
	 *            the preferred height of the panel.
	 * @param minimumDrawWidth
	 *            the minimum drawing width.
	 * @param minimumDrawHeight
	 *            the minimum drawing height.
	 * @param maximumDrawWidth
	 *            the maximum drawing width.
	 * @param maximumDrawHeight
	 *            the maximum drawing height.
	 * @param useBuffer
	 *            a flag that indicates whether to use the off-screen buffer to
	 *            improve performance (at the expense of memory).
	 * @param properties
	 *            a flag indicating whether or not the chart property editor
	 *            should be available via the popup menu.
	 * @param copy
	 *            a flag indicating whether or not a copy option should be
	 *            available via the popup menu.
	 * @param save
	 *            a flag indicating whether or not save options should be
	 *            available via the popup menu.
	 * @param print
	 *            a flag indicating whether or not the print option should be
	 *            available via the popup menu.
	 * @param zoom
	 *            a flag indicating whether or not zoom options should be added
	 *            to the popup menu.
	 * @param tooltips
	 *            a flag indicating whether or not tooltips should be enabled
	 *            for the chart.
	 * 
	 * @since 1.0.13
	 */
	public CustomChartPanel(JFreeChart chart, int width, int height,
			int minimumDrawWidth, int minimumDrawHeight, int maximumDrawWidth,
			int maximumDrawHeight, boolean useBuffer, boolean properties,
			boolean copy, boolean save, boolean print, boolean zoom,
			boolean tooltips, CurveViewPanel viewPanel) {
		super(chart, width, height, minimumDrawWidth, minimumDrawHeight,
				maximumDrawWidth, maximumDrawHeight, useBuffer, properties,
				copy, save, print, zoom, tooltips);

		this.setMouseWheelEnabled(true);
		this.addChartMouseListener(this);
		this.viewPanel = viewPanel;
		overlay.addDomainCrosshair(domainCrosshair);
		domainCrosshair.setPaint(Color.RED);
		domainCrosshair.setLabelVisible(true);
		chart.getXYPlot().setDomainCrosshairLockedOnData(true);
		chart.getXYPlot().setDomainCrosshairVisible(false);
		domainCrosshair.setLabelAnchor(RectangleAnchor.BOTTOM_LEFT);
		domainCrosshair.setLabelBackgroundPaint(new Color(255, 255, 0, 100));
		addOverlay(overlay);
	}

	private void addPopupMenu() {
		// TODO Auto-generated method stub
		// boolean separator = false;
		menuList.clear();
		JPopupMenu popupMenu = super.getPopupMenu();
		/** 添加菜单 . **/
		/*
		 * 添加曲线列表数据菜单项.
		 */
		if (true) {
			JMenu seriesDataItem = new JMenu(
					localizationResources2.getString("Series_Data"));
			seriesDataItem.setActionCommand(SERIES_DATA_COMMAND);
			seriesDataItem.addActionListener(this);
			popupMenu.add(seriesDataItem);
			menuList.add(seriesDataItem);
			XYPlot plot = getChart().getXYPlot();
			int datasetSize = plot.getDatasetCount();
			for (int i=0; i< datasetSize; i++) {
				final int datasetIndex = i;
				XYDataset dataset = plot.getDataset(i);
				int seriesSize = dataset.getSeriesCount();
				for (int j = 0; j < seriesSize; j++) {
					final int seriesIndex = j;
					Series series = null;
					TimeCurveRefresherBean bean = null;
					Date beginDate = new Date();
					if (dataset instanceof TimeSeriesCollection) {
						series = ((TimeSeriesCollection) dataset).getSeries(j);
						bean = ((CustomTimeSeries)series).getRefresherBean();
						beginDate = new Date(bean.getStartTime());
					} else if (dataset instanceof XYSeriesCollection){
						series = ((XYSeriesCollection) dataset).getSeries(j);
					}
					final Date seriesDate = beginDate;
					Comparable menuName = series.getKey();
					JMenuItem menuItem = new JMenuItem(menuName.toString());
					seriesDataItem.add(menuItem);
					long tempKeyID = bean.getKeyid();
					final long keyID = bean.getKeyid();
					final String curveType = bean.getCurveType();
					
					
					menuItem.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							XYDataset dataset = getChart().getXYPlot().getDataset(datasetIndex);
							List list = null;
							Date date = new Date();
							if (dataset instanceof TimeSeriesCollection) {
								TimeSeries timeSeries = ((TimeSeriesCollection) dataset)
										.getSeries(seriesIndex);
								list = timeSeries.getItems();
							} else if (dataset instanceof XYSeriesCollection) {
								XYSeries xySeries = ((XYSeriesCollection) dataset)
										.getSeries(seriesIndex);
								list = xySeries.getItems();
							}
							Object[][] dataObjects = new Object[list.size()][];
							
							Vector<Vector<String>> curveListData = new Vector<Vector<String>>(
									list.size());
							for (int i = 0; i < list.size(); i++) {
								Vector data = new Vector();
								Object object = list.get(i);
								dataObjects[i] = new Object[3];
								int quality = 0;
								if (object instanceof TimeSeriesDataItem) {
									date = ((TimeSeriesDataItem) object)
											.getPeriod().getStart();
									dataObjects[i][0] = new SimpleDateFormat(
											"HH:mm:ss")
											.format(((TimeSeriesDataItem) object)
													.getPeriod().getStart());
									dataObjects[i][1] = ((TimeSeriesDataItem) object)
											.getValue();
									// quality =
									// ((TimeSeriesDataItem)object).getQuality();
								} else {
									dataObjects[i][0] = ((XYDataItem) object)
											.getX().toString();
									dataObjects[i][1] = ((XYDataItem) object)
											.getY().toString();
									// quality =
									// ((TimeSeriesItem)object).getQuality();
								}
								ArrayList<String> qualityList = MenuTool
										.getMultiMenuDisplayValue(
												DomainManager.LOCAL_DOMAIN, "遥测状态",
												quality);
								StringBuffer displayQuality = new StringBuffer("");
								for (int j = 0; j < qualityList.size(); j++) {
									displayQuality.append(qualityList.get(j));
									if (j != qualityList.size() - 1) {
										displayQuality.append("/");
									}
								}
								dataObjects[i][2] = displayQuality;
								data.add(dataObjects[i][0].toString());
								data.add(dataObjects[i][1].toString());
								data.add(dataObjects[i][2].toString());
								curveListData.add(data);
							}
							CurveListPanel hisDataPanel = new CurveListPanel(
									curveType,
									String.valueOf(keyID),
									new SimpleDateFormat("yyyy/MM/dd").format(seriesDate),
									"yyyy/MM/dd HH:mm");
							hisDataPanel.displayData(curveListData);
							UIPlainDialog plainDialog = new UIPlainDialog();
							plainDialog.setApplyButtonVisible(true);
							plainDialog.setHelpButtonVisible(false);
							plainDialog.setDecorativePanelStatus(false);
							plainDialog.setTitlePanelStatus(false);
							plainDialog.addUIPlainDialogContent(hisDataPanel);
							plainDialog.setVisible(true);
						}
					});
					
				}
			}
		}
		/*
		 * 添加曲线信息菜单项.
		 */
		if (true) {
			JMenuItem seriesInfoItem = new JMenuItem(
					localizationResources2.getString("Series_Info"));
			seriesInfoItem.setActionCommand(SERIES_INFO);
			seriesInfoItem.addActionListener(this);
			popupMenu.add(seriesInfoItem);
			menuList.add(seriesInfoItem);
		}

		/*
		 * 添加曲线拖动修改菜单项.
		 */
		if (true) {

			JMenu dragItemsMenu = new JMenu(
					localizationResources2.getString("Series_Drag_Modify"));
			dragItemsMenu.setActionCommand(SERIES_DRAG_MODIFY_COMMAND);
			XYPlot plot = getChart().getXYPlot();
			for (int i = 0; i < plot.getDatasetCount(); i++) {
				final int nowI = i;
				XYDataset dataset = plot.getDataset(i);
				for (int j = 0; j < dataset.getSeriesCount(); j++) {
					final int nowJ = j;
					Comparable seriesKey = dataset.getSeriesKey(j);
					JMenuItem seriesModifyItem = new JMenuItem(
							seriesKey.toString());
					seriesModifyItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
//							refresh = false;
							stopDataEngine();
							modifyFlag = true;
							seriesindex = nowJ;
							datasetindex = nowI;
						}
					});
					dragItemsMenu.add(seriesModifyItem);
				}
			}
			popupMenu.add(dragItemsMenu);
			menuList.add(dragItemsMenu);
		}

		/*
		 * 添加曲线单点修改菜单项.
		 */
		if (true) {
			JMenu dragItemsMenu = new JMenu(
					localizationResources2.getString("Series_Point_Modify"));
			dragItemsMenu.setActionCommand(SERIES_POINT_MODIFY_COMMAND);
			XYPlot plot = getChart().getXYPlot();
			for (int i = 0; i < plot.getDatasetCount(); i++) {
				final int nowI = i;
				XYDataset dataset = plot.getDataset(i);
				for (int j = 0; j < dataset.getSeriesCount(); j++) {
					final int nowJ = j;
					Comparable seriesKey = dataset.getSeriesKey(j);
					JMenuItem seriesModifyItem = new JMenuItem(
							seriesKey.toString());
					seriesModifyItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							XYPlot plot = getChart().getXYPlot();
//							refresh = false;
							stopDataEngine();
							seriesindex = nowJ;
							datasetindex = nowI;
							initRange = plot.getRangeAxis().getRange();
//							refresh = false;
							pointModifyFlag = true;
							((XYLineAndShapeRenderer)plot.getRenderer(datasetindex)).setSeriesShapesVisible(
									seriesindex, true);
						}
					});
					dragItemsMenu.add(seriesModifyItem);
				}
			}
			popupMenu.add(dragItemsMenu);
			menuList.add(dragItemsMenu);
			
		}
		
		/**
		 * 步长设置
		 */
//		if (true) {
//			JMenu periodSetItemsMenu = new JMenu(
//					localizationResources2.getString("Series_Period_Set"));
//			periodSetItemsMenu.setActionCommand(SERIES_PERIOD_SET_COMMAND);
//			XYPlot plot = getChart().getXYPlot();
//			for (int i = 0; i < plot.getDatasetCount(); i++) {
//				final int nowI = i;
//				XYDataset dataset = plot.getDataset(i);
//				for (int j = 0; j < dataset.getSeriesCount(); j++) {
//					final int nowJ = j;
//					if (plot.getDomainAxis() instanceof CustomDateAxis) {
//						CustomTimeSeries series = (CustomTimeSeries) ((TimeSeriesCollection)dataset).getSeries(j);
//						Comparable seriesKey = series.getKey();
//						JMenuItem seriesModifyItem = new JMenuItem(
//								seriesKey.toString());
//						seriesModifyItem.addActionListener(new ActionListener() {
//							@Override
//							public void actionPerformed(ActionEvent e) {
////								refresh = false;
//								stopDataEngine();
//								series.getRefresherBean().setPeriod(period)
//								modifyFlag = true;
//								seriesindex = nowJ;
//								datasetindex = nowI;
//							}
//						});
//						periodSetItemsMenu.add(seriesModifyItem);
//					}
//					
//					
//				}
//			}
//			popupMenu.add(periodSetItemsMenu);
//			menuList.add(periodSetItemsMenu);
//		}

		/*
		 * 添加恢复菜单项.
		 */
		if (true) {
			JMenuItem seriesRescoryItem = new JMenuItem(
					localizationResources2.getString("Series_Rescory"));
			seriesRescoryItem.setActionCommand(SERIES_RESCORY_COMMAND);
			seriesRescoryItem.addActionListener(this);
			popupMenu.add(seriesRescoryItem);
			menuList.add(seriesRescoryItem);
		}

		/*
		 * 添加保存菜单项.
		 */
		if (true) {
			JMenuItem seriesSaveItem = new JMenuItem(
					localizationResources2.getString("Series_Save"));
			seriesSaveItem.setActionCommand(SERIES_SAVE_COMMAND);
			seriesSaveItem.addActionListener(this);
			popupMenu.add(seriesSaveItem);
			popupMenu.setEnabled(false);
			menuList.add(seriesSaveItem);
		}

		/** 结束添加菜单 **/
	}
	
	public List<Series> getSeriesList() {
		List<Series> seriesList = new ArrayList<Series>();
		XYPlot plot = getChart().getXYPlot();
		for (int i = 0; i < plot.getDatasetCount(); i++) {
			final int nowI = i;
			XYDataset dataset = plot.getDataset(i);
			for (int j = 0; j < dataset.getSeriesCount(); j++) {
				Series series = null;
				if (dataset instanceof TimeSeriesCollection) {
					series = ((TimeSeriesCollection) dataset).getSeries(j);
				} else if(dataset instanceof XYSeriesCollection){
					series = ((XYSeriesCollection) dataset).getSeries(j);
				}
				seriesList.add(series);
			}
		}
		return seriesList;
	}

//	public CurveViewPanel getCurveViewPanel() {
//		return this.viewPanel;
//	}

	@Override
	public void chartMouseClicked(ChartMouseEvent event) {
		ChartEntity entity = event.getEntity();
		XYPlot plot = getChart().getXYPlot();
		if (entity instanceof LegendItemEntity) {
			XYDataset dataset = (XYDataset) ((LegendItemEntity) entity)
					.getDataset();
			XYItemRenderer renderer = plot.getRendererForDataset(dataset);
			Comparable<String> key = (String) ((LegendItemEntity) entity)
					.getSeriesKey();
			int index = dataset.indexOf(key);
			boolean isShow = renderer.isSeriesVisible(index);
			renderer.setSeriesVisible(index, !isShow, false);
			((XYLineAndShapeRenderer) renderer).setSeriesLinesVisible(index,
					!isShow);
		} else if (entity instanceof PlotEntity) {
			if (SwingUtilities.isLeftMouseButton(event.getTrigger())) {
				isMove = !isMove;
				Point beginPoint = event.getTrigger().getPoint();
				plot.setDomainCrosshairVisible(isMove);
				Point2D beginPoint2D = translateScreenToJava2D(beginPoint);
				if (!isMove) {
					overlay.clearDomainCrosshairs();
				} else {
					domainCrosshair.setValue(beginPoint2D.getX());
					overlay.addDomainCrosshair(domainCrosshair);
				}
			}
		} else if (entity instanceof BeforePageEntity) {
			// 时间轴
			if (plot.getDomainAxis() instanceof CustomDateAxis) {
				CustomDateAxis domainAxis = (CustomDateAxis) plot
						.getDomainAxis();
				DateRange initRange = domainAxis.getInitDateRange();
				double length = initRange.getLength();
				DateRange nowRange = (DateRange) domainAxis.getRange();
				Range range = new DateRange(nowRange.getLowerBound() - length,
						nowRange.getUpperBound() - length);
				domainAxis.setRange(range);
				((BeforePageEntity) entity).notifyObservers();
				// domainAxis.setMinimumDate(((DateRange)range).getLowerDate());
				// domainAxis.setMaximumDate(((DateRange)range).getUpperDate());
			} else {
				// 数字轴

			}
		} else if (entity instanceof ForwadPageEntity) {
			// 时间轴
			if (plot.getDomainAxis() instanceof CustomDateAxis) {
				CustomDateAxis domainAxis = (CustomDateAxis) plot
						.getDomainAxis();
				DateRange initRange = domainAxis.getInitDateRange();
				double length = initRange.getLength();
				DateRange nowRange = (DateRange) domainAxis.getRange();
				Range range = new DateRange(nowRange.getLowerBound() + length,
						nowRange.getUpperBound() + length);
				domainAxis.setRange(range);
				((ForwadPageEntity) entity).notifyObservers();
			} else {
				// 数字轴
			}
		} 
		else if (entity instanceof XYItemEntity) {
			if (pointModifyFlag) {
				xyItemEntity = (XYItemEntity) entity;
//				isModify = !isModify;
//				XYDataset dataset = ((XYItemEntity) entity).getDataset();
//				int seriesIndex = ((XYItemEntity) entity).getSeriesIndex();
//				XYItemRenderer renderer = plot.getRendererForDataset(dataset);
//				((XYLineAndShapeRenderer) renderer).setSeriesShapesVisible(
//						seriesIndex, isModify);
			}
		}
	}

	XYItemEntity xyItemEntity = null;
	boolean isModify = false;

	@Override
	public synchronized void chartMouseMoved(ChartMouseEvent event) {
		// XYPlot xyPlot = getChart().getXYPlot();
		// ChartRenderingInfo chartRenderingInfo = getChartRenderingInfo();
		// Rectangle2D rectangle2D = chartRenderingInfo.getPlotInfo()
		// .getDataArea();
		// ValueAxis valueAxis = xyPlot.getRangeAxis();
		// CustomDateAxis domainAxis = (CustomDateAxis) xyPlot.getDomainAxis();
		// RectangleEdge rangeRectangleEdge = xyPlot.getRangeAxisEdge();
		// RectangleEdge domainRectangleEdge = xyPlot.getDomainAxisEdge();
		if (isMove) {
			Point beginPoint = event.getTrigger().getPoint();
			Point2D beginPoint2D = translateScreenToJava2D(beginPoint);
			domainCrosshair.setValue(beginPoint2D.getX());
		}
	}

	Range initRange = null;

	public void mouseDragged(MouseEvent e) {
		if (modifyFlag) {
			Rectangle2D rectangle2d = this.getChartRenderingInfo()
					.getPlotInfo().getDataArea().getBounds2D();
			if (rectangle2d.getMinX() > e.getPoint().getX()
					|| rectangle2d.getMaxX() < e.getPoint().getX() || rectangle2d.getMinY() > e.getPoint().getY() || rectangle2d.getMaxY() < e.getPoint().getY()) {
				return;
			}
			start = true;
			if (start) {
				Point point = e.getPoint();
				if (!mouseMap.containsKey(point.x)) {
					mouseMoMap.add(point);
					paintCurve(point);
				} else {
					Point point2 = mouseMap.get(point.x);
					mouseMap.remove(point2);
					mouseMap.put(point.x, point);
					int index = mouseMoMap.indexOf(point2);
					mouseMoMap.remove(point2);
					mouseMoMap.insertElementAt(point, index);
					paintCurve(point);
				}
			}
		} else if (pointModifyFlag) {//单点修改
			if (xyItemEntity != null) {
				XYPlot xyPlot = getChart().getXYPlot();
				ChartRenderingInfo chartRenderingInfo = getChartRenderingInfo();
				Rectangle2D rectangle2D = chartRenderingInfo.getPlotInfo()
						.getDataArea();
				ValueAxis valueAxis = xyPlot.getRangeAxis();

				RectangleEdge rangeRectangleEdge = xyPlot.getRangeAxisEdge();
				Point beginPoint = e.getPoint();
				Point2D beginPoint2D = translateScreenToJava2D(beginPoint);
				int item = xyItemEntity.getItem();
				XYDataset dataset = ((XYItemEntity) xyItemEntity).getDataset();
				int seriesIndex = ((XYItemEntity) xyItemEntity)
						.getSeriesIndex();
				double value = valueAxis.java2DToValue(beginPoint2D.getY(),
						rectangle2D, rangeRectangleEdge);
				Range range = valueAxis.getRange();
				if (value >= initRange.getLowerBound()
						&& value <= initRange.getUpperBound()) {
					valueAxis.setAutoRange(false);
					valueAxis.setRange(initRange);
				} else {
					if (value > initRange.getUpperBound()) {
						range = new Range(range.getLowerBound(), getRangeValue(
								valueAxis, value));
					} else if (value < initRange.getLowerBound()) {
						range = new Range(getRangeValue(valueAxis, value),
								range.getUpperBound());
					}
					valueAxis.setRange(range);
				}
				if (dataset instanceof TimeSeriesCollection) {
					TimeSeriesDataItem dataItem = ((TimeSeriesCollection) dataset)
							.getSeries(seriesIndex).getDataItem(item);
					dataItem.setValue(value);
					((TimeSeriesCollection) dataset).getSeries(seriesIndex)
							.addOrUpdate(dataItem);
				}
			}
		} else if (!pointModifyFlag && !modifyFlag) {
			super.mouseDragged(e);
		}

	}

	public double getRangeValue(ValueAxis valueAxis, double value) {
		double labelLength = 1;
		Range range = valueAxis.getRange();
		if (valueAxis instanceof NumberAxis) {
			labelLength = ((NumberAxis) valueAxis).getTickUnit().getSize();
		}
		if (value < range.getLowerBound()) {
			labelLength = -labelLength;
		} else if (value < initRange.getUpperBound()
				&& value - labelLength > range.getLowerBound()) {
			return value - labelLength;
		}
		return labelLength + value;
	}

	public int getIndexByXValue(double value, int datasetindex, int seriesindex) {
		int beginIndex = 0;
		XYPlot plot = this.getChart().getXYPlot();
		XYDataset dataset = plot.getDataset(datasetindex);
		Series series = null;
		int begin = 0;
		int end = 0;
		boolean isTimeSeries = true;
		if (dataset instanceof XYSeriesCollection) {
			isTimeSeries = false;
		}
		ValueAxis domainAxis = plot.getDomainAxis();
		Range domainRange = domainAxis.getRange();

		int midIndex = 0;
		double lowerBoundValue = domainRange.getLowerBound();
		double upperBoundValue = domainRange.getUpperBound();
		double midBoundValue = (lowerBoundValue + upperBoundValue) / 2;
		if (isTimeSeries) {
			end = ((TimeSeriesCollection) dataset).getSeries(seriesindex)
					.getItemCount() - 1;
			midIndex = (begin + end) / 2;
			series = ((TimeSeriesCollection) dataset).getSeries(seriesindex);
			lowerBoundValue = ((TimeSeries) series).getDataItem(begin)
					.getPeriod().getFirstMillisecond();
			upperBoundValue = ((TimeSeries) series).getDataItem(end)
					.getPeriod().getFirstMillisecond();
			midBoundValue = ((TimeSeries) series).getDataItem(midIndex)
					.getPeriod().getFirstMillisecond();
		} else {
			end = ((XYSeriesCollection) dataset).getSeries(0).getItemCount() - 1;
			midIndex = (begin + end) / 2;
			series = ((XYSeriesCollection) dataset).getSeries(seriesindex);
			lowerBoundValue = ((XYSeries) series).getDataItem(begin)
					.getXValue();
			upperBoundValue = ((XYSeries) series).getDataItem(end).getXValue();
			midBoundValue = ((XYSeries) series).getDataItem(midIndex)
					.getXValue();
		}
		if (lowerBoundValue > value) {
			return -1;
		} 
		if (upperBoundValue < value) {
			return -1;
		}
		while (true) {
			if (end - begin > 1) {
				if (value < midBoundValue) {
					end = midIndex;
					midIndex = (begin + end) / 2;
					if (isTimeSeries) {
						lowerBoundValue = ((TimeSeries) series)
								.getDataItem(begin).getPeriod()
								.getFirstMillisecond();
						upperBoundValue = ((TimeSeries) series)
								.getDataItem(end).getPeriod()
								.getFirstMillisecond();
						midBoundValue = ((TimeSeries) series)
								.getDataItem(midIndex).getPeriod()
								.getFirstMillisecond();
					} else {
						lowerBoundValue = ((XYSeries) series)
								.getDataItem(begin).getXValue();
						upperBoundValue = ((XYSeries) series).getDataItem(end)
								.getXValue();
						midBoundValue = ((XYSeries) series).getDataItem(
								midIndex).getXValue();
					}
				} else if (value > midBoundValue) {
					begin = midIndex;
					midIndex = (begin + end) / 2;
					if (isTimeSeries) {
						lowerBoundValue = ((TimeSeries) series)
								.getDataItem(begin).getPeriod()
								.getFirstMillisecond();
						upperBoundValue = ((TimeSeries) series)
								.getDataItem(end).getPeriod()
								.getFirstMillisecond();
						midBoundValue = ((TimeSeries) series)
								.getDataItem(midIndex).getPeriod()
								.getFirstMillisecond();
					} else {
						lowerBoundValue = ((XYSeries) series)
								.getDataItem(begin).getXValue();
						upperBoundValue = ((XYSeries) series).getDataItem(end)
								.getXValue();
						midBoundValue = ((XYSeries) series).getDataItem(
								midIndex).getXValue();
					}
				} else {
					beginIndex = midIndex;
					break;
				}
			} else {
				beginIndex = (Math.abs(upperBoundValue - value) < Math
						.abs(lowerBoundValue - value)) ? end : begin;
				break;
			}
		}
		return beginIndex;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (SERIES_DRAG_MODIFY_COMMAND.equals(command)) {
			System.out.println("拖动修改");
			MMIUser user = LoginCtrl.getInstance().getLoginMMIUser();
			int funcID = PrivInfoSet.getPrivInfo(PermStaticFinals.SAMPLE_MODIFY).getFuncID();
		
			Vector<DataUnit> result = null;
			try {
				result = DataClient.hasGivenFunc(user.getUserName(), null, null, "", DataClient.getLocalHostName(), null,
						false, funcID);
			} catch (DataFaultException e1) {
				e1.printStackTrace();
			} catch (ServiceConnectionException e1) {
				e1.printStackTrace();
			} catch (LocatorException e1) {
				e1.printStackTrace();
			} catch (ProxyFaultException e1) {
				e1.printStackTrace();
			}

			if (result != null && result.size() > 0) 
			{
				
				int returnCode = (int) result.get(0).getLongData();
				if (returnCode == PermStaticFinals.P_PERMIT) 
				{
//					refresh = false;
					stopDataEngine();
					modifyFlag = true;
				} else {
					String message = PermStaticFinals.getCodeString(returnCode);
					JOptionPane.showMessageDialog(null, message, "提示",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} else if (SERIES_POINT_MODIFY_COMMAND.equals(command)) {
			System.out.println("单点修改");
			MMIUser user = LoginCtrl.getInstance().getLoginMMIUser();
			int funcID = PrivInfoSet.getPrivInfo(PermStaticFinals.SAMPLE_MODIFY).getFuncID();
		
			Vector<DataUnit> result = null;
			try {
				result = DataClient.hasGivenFunc(user.getUserName(), null, null, "", DataClient.getLocalHostName(), null,
						false, funcID);
			} catch (DataFaultException e1) {
				e1.printStackTrace();
			} catch (ServiceConnectionException e1) {
				e1.printStackTrace();
			} catch (LocatorException e1) {
				e1.printStackTrace();
			} catch (ProxyFaultException e1) {
				e1.printStackTrace();
			}

			if (result != null && result.size() > 0) 
			{
				int returnCode = (int) result.get(0).getLongData();
				if (returnCode == PermStaticFinals.P_PERMIT) 
				{
					stopDataEngine();
					initRange = this.getChart().getXYPlot().getRangeAxis().getRange();
//					refresh = false;
					pointModifyFlag = true;
				} else {
					String message = PermStaticFinals.getCodeString(returnCode);
					JOptionPane.showMessageDialog(null, message, "提示",
						JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (SERIES_INFO.equals(command)) {
			stopDataEngine();
			dataSourceMenuItemPerformed(event);
		} else if (SERIES_RESCORY_COMMAND.equals(command)) {
			System.out.println("恢复");
			if (mouseMoMap.size() > 1) {
				lastPoint = null;
				mouseMoMap.clear();
				mouseMap.clear();
			}
			updateData.clear();
//			refresh = true;
			modifyFlag = false;
			pointModifyFlag = false;
			((XYLineAndShapeRenderer)getChart().getXYPlot().getRenderer(datasetindex)).setSeriesShapesVisible(
					seriesindex, false);
			startDataEngine();
			updateUI();
		} else if (SERIES_SAVE_COMMAND.equals(command)) {
			saveToDB(updateData);
//			refresh = true;
			updateData.clear();
			modifyFlag = false;
			pointModifyFlag = false;
			startDataEngine();
			((XYLineAndShapeRenderer)getChart().getXYPlot().getRenderer(datasetindex)).setSeriesShapesVisible(
					seriesindex, false);
			if (mouseMoMap.size() > 1) {
				lastPoint = null;
				mouseMoMap.clear();
				mouseMap.clear();
			}
			updateUI();
		} else {
			super.actionPerformed(event);
		}

	}

	protected void dataSourceMenuItemPerformed(ActionEvent e) {
		// 一幅曲线画面中只能有一种RecordSet类型的曲线,取得曲线RecordSet,根据RecordSet判断曲线数据源对话框类型
		JDialog curveSourceDialog = new CurveSourceDialog(
				(JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this),
				"曲线数据源", false, this);
		curveSourceDialog.setLocationRelativeTo(null);
		curveSourceDialog.show();
	}

	private void saveToDB(Vector datasVector) {
		// TODO Auto-generated method stub
		modifyDatas.clear();
		for (Object object : datasVector) {
			MidhsData data = new MidhsData();
			int quality = 0;
			long xValue = Calendar.getInstance().getTimeInMillis();
			float yValue = 0.0f;
			if (object instanceof TimeSeriesItem) {
				quality = ((TimeSeriesItem) object).getQuality();
				xValue = ((TimeSeriesItem) object).getPeriod()
						.getFirstMillisecond();
				yValue = ((TimeSeriesItem) object).getValue().floatValue();
			}
			data.setQuality(quality);
			data.setValidFlag(0);
			data.setXValue(xValue);
			data.setYValue(yValue);
			modifyDatas.add(data);
		}
		try {
			XYPlot plot = getChart().getXYPlot();
			XYDataset dataset = plot.getDataset(datasetindex);
			if (dataset instanceof TimeSeriesCollection) {
				CustomTimeSeries timeSeries = (CustomTimeSeries) ((TimeSeriesCollection) dataset)
						.getSeries(seriesindex);
				TimeCurveRefresherBean bean = (TimeCurveRefresherBean) timeSeries.getRefresherBean();
				String type = bean.getCurveType();
				long keyID = bean.getKeyid();
				MidhsService.updateCurveData(DomainManager.LOCAL_DOMAIN, null,
						type, keyID, modifyDatas);
			} else {
				//数字轴
			}

		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DataFaultException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ServiceConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LocatorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProxyFaultException e1) {
			e1.printStackTrace();
			return;
		}
	}

	Vector updateData = new Vector();

	public Vector updateChart() {
		XYPlot xyPlot = this.getChart().getXYPlot();
		xyPlot.getRangeAxis().setAutoRange(false);
		ChartRenderingInfo chartRenderingInfo = this.getChartRenderingInfo();
		Rectangle2D rectangle2D = chartRenderingInfo.getPlotInfo()
				.getDataArea();
		ValueAxis valueAxis = xyPlot.getRangeAxis();
		ValueAxis domainAxis = xyPlot.getDomainAxis();
		RectangleEdge rangeRectangleEdge = xyPlot.getRangeAxisEdge();
		RectangleEdge domainRectangleEdge = xyPlot.getDomainAxisEdge();
		XYDataset dataset = xyPlot.getDataset(datasetindex);
		for (Point point : mouseMoMap) {
			System.out.println("updateChart" + point);
		}
		Point point = mouseMoMap.get(0);
		double firstValue = domainAxis.java2DToValue(point.getX(), rectangle2D,
				domainRectangleEdge);
		if (domainAxis instanceof CustomDateAxis) {
			Point2D point2d = translateScreenToJava2D(point);
			firstValue = ((CustomDateAxis) domainAxis).java2DToValueCustom(
					point2d.getX(), rectangle2D, domainRectangleEdge,
					seriesindex);
		}
		int indexBegin = getIndexByXValue(firstValue, datasetindex, seriesindex);
		double x = firstValue;
		Series series = null;
		boolean isTimeSeries = true;
		double maxXValue = 0.0;
		if (dataset instanceof XYSeriesCollection) {
			XYSeriesCollection tsc = (XYSeriesCollection) xyPlot
					.getDataset(datasetindex);
			series = tsc.getSeries(seriesindex);
			x = ((XYSeries) series).getDataItem(indexBegin).getXValue();
			isTimeSeries = false;
		} else {
			TimeSeriesCollection tsc = (TimeSeriesCollection) dataset;
			series = tsc.getSeries(seriesindex);
			for (int i=0; i < series.getItemCount(); i ++) {
				System.out.println("index = " + i +"  " + ((TimeSeries)series).getValue(i));
			}
			int size = series.getItemCount();
			maxXValue = ((TimeSeries) series).getTimePeriod(size - 1)
					.getFirstMillisecond();
			x = ((TimeSeries) series).getDataItem(indexBegin).getPeriod()
					.getFirstMillisecond();
		}
		for (int i = 0, j = i + 1; i < mouseMoMap.size()
				&& j < mouseMoMap.size(); i++, j++) {

			Point beginPoint = mouseMoMap.get(i);
			Point endPoint = mouseMoMap.get(j);

			Point2D beginPoint2D = this.translateScreenToJava2D(beginPoint);
			Point2D endPoint2D = this.translateScreenToJava2D(endPoint);

			double beginXValue = domainAxis.java2DToValue(beginPoint2D.getX(),
					rectangle2D, domainRectangleEdge);
			double beginValue = valueAxis.java2DToValue(beginPoint2D.getY(),
					rectangle2D, rangeRectangleEdge);

			double endXValue = domainAxis.java2DToValue(endPoint2D.getX(),
					rectangle2D, domainRectangleEdge);
			double endValue = valueAxis.java2DToValue(endPoint2D.getY(),
					rectangle2D, rangeRectangleEdge);
			if (domainAxis instanceof CustomDateAxis) {
				beginXValue = ((CustomDateAxis) domainAxis)
						.java2DToValueCustom(beginPoint2D.getX(), rectangle2D,
								domainRectangleEdge, seriesindex);
				endXValue = ((CustomDateAxis) domainAxis).java2DToValueCustom(
						endPoint2D.getX(), rectangle2D, domainRectangleEdge,
						seriesindex);
			}

			if (endXValue > maxXValue) {
				endXValue = maxXValue;
			}
			double jieju = (endXValue * beginValue - beginXValue
					* endValue)
					/ (endXValue - beginXValue);

			double xielv = (endValue - beginValue)
					/ (endXValue - beginXValue);
			System.out.println( i + " i " + new Date((long) beginXValue) +"  " + beginValue);
			System.out.println( j + " i " + new Date((long) endXValue) +"  " + endValue);
			System.out.println("xielv " + xielv);
			System.out.println("jieju " + jieju);
			System.out.println();
			System.out.println();
			System.out.println();
			while (x < endXValue) {
				double newYValue = beginValue;
				if (beginXValue != endXValue) {
					newYValue = xielv * x + jieju;
				}
				if (!isTimeSeries) {
					((XYSeries) series).addOrUpdate(x, newYValue);
					XYDataItem item = (XYDataItem) ((XYSeries) series)
							.getDataItem(indexBegin);
					item.setY(newYValue);
					updateData.add(item);
					x = ((XYSeries) series).getDataItem(indexBegin++)
							.getXValue();
				} else {
					TimeSeriesDataItem item = (TimeSeriesDataItem) ((TimeSeries) series)
							.getDataItem(indexBegin);
					item.setValue(newYValue);
					updateData.add(item);
					((TimeSeries) series).update(indexBegin, newYValue);
					x = ((TimeSeries) series).getDataItem(indexBegin++)
							.getPeriod().getFirstMillisecond();
				}
			}

		}
		xyPlot.getRangeAxis().setAutoRange(true);
		return updateData;
	}

	public void mouseReleased(MouseEvent e) {
		start = false;
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (mouseMoMap.size() > 1) {
				if (modifyFlag) {
					lastPoint = null;
					updateChart();
					mouseMoMap.clear();
					mouseMap.clear();
					shape.reset();
				} 
			}if (pointModifyFlag) {
					if (xyItemEntity != null) {
						XYPlot plot = getChart().getXYPlot();
						XYDataset dataset = plot.getDataset(datasetindex);
						if (dataset instanceof TimeSeriesCollection) {
							TimeSeries series = ((TimeSeriesCollection) dataset).getSeries(seriesindex);
							TimeSeriesDataItem item = series.getDataItem(xyItemEntity.getItem());
							updateData.add(item);
						}
				}
			}
		} else {
			super.mouseReleased(e);
		}
	}

	private void paintCurve(Point newPoint) {
		if (mouseMoMap.size() > 2) {
			Graphics graphics = this.getGraphics();
			Graphics2D g2 = (Graphics2D) graphics;
			Point prePoint = null;
			g2.setPaint(getChart().getXYPlot().getRenderer(datasetindex)
					.getSeriesPaint(seriesindex));
			for (int i = 0; i < mouseMoMap.size(); i++) {
				Point nowPoint = mouseMoMap.get(i);
				if (i == 0) {
					prePoint = nowPoint;
				}
				g2.drawLine(prePoint.x, prePoint.y, nowPoint.x, nowPoint.y);
				prePoint = nowPoint;
			}
		}
		lastPoint = newPoint;
		if (!mouseMoMap.contains(newPoint)) {
			mouseMoMap.add(newPoint);
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		super.mouseClicked(event);
	}
	
	public void startDataEngine() {
		XYPlot plot = getChart().getXYPlot();
		ValueAxis domainAxis = plot.getDomainAxis();
		if (domainAxis instanceof CustomDateAxis) {
			List<Observer> observers = ((CustomDateAxis) domainAxis).getObservers();
			for (Observer observer : observers) {
				if (observer instanceof RefreshObservable) {
					((RefreshObservable) observer).start_refresh();
				}
			}
		}
	}
	
	public void stopDataEngine() {
		XYPlot plot = getChart().getXYPlot();
		ValueAxis domainAxis = plot.getDomainAxis();
		if (domainAxis instanceof CustomDateAxis) {
			List<Observer> observers = ((CustomDateAxis) domainAxis).getObservers();
			for (Observer observer : observers) {
				if (observer instanceof RefreshObservable) {
					((RefreshObservable) observer).stop_refresh();
				}
			}
		}
	}
	
	public void interruptDataEngine() {
		XYPlot plot = getChart().getXYPlot();
		ValueAxis domainAxis = plot.getDomainAxis();
		if (domainAxis instanceof CustomDateAxis) {
			List<Observer> observers = ((CustomDateAxis) domainAxis).getObservers();
			for (Observer observer : observers) {
				if (observer instanceof RefreshObservable) {
					((RefreshObservable) observer).interrupt_refresh();
				}
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			XYPlot plot = getChart().getXYPlot();
			if (plot.getDomainAxis() instanceof CustomDateAxis) {
				JPopupMenu popupMenu = super.getPopupMenu();
				if (menuList.size() > 0) {
					for (int i=0; i< menuList.size(); i++) {
						popupMenu.remove(menuList.get(i));
					}
				}
				addPopupMenu();
				
			}
		}
		super.mousePressed(e);
	};
	
}
