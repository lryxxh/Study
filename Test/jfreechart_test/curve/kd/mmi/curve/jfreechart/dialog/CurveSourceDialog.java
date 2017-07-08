package curve.kd.mmi.curve.jfreechart.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import org.jfree.chart.CustomChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.custom.axis.CustomDateAxis;
import org.jfree.chart.custom.title.ChangePageTitle;
import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.Title;
import org.jfree.data.general.Series;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.custom.CustomTimeSeries;

import agency.exception.DataFaultException;
import agency.exception.LocatorException;
import agency.exception.ProxyFaultException;
import agency.exception.ServiceConnectionException;
import agency.message.base.DomainManager;
import agency.message.base.KeyID;
import agency.message.midhs.MidhsService;
import context.Context;
import curve.kd.mmi.curve.jfreechart.panel.CurveSourcePanel;
import curve.kd.mmi.curve.jfreechart.refresher.TimeCurveRefresherBean;
import curve.kd.mmi.curvechart.util.CustomUtility;
import pictureeditor.SwingDialog;
import pictureeditor.browser.manager.BrowserBaseFrameManager;
import pictureeditor.browser.view.BrowserBaseFrame;
import support.DataClient;
import uiframework.widgets.keyidsearcher.view.SearchMainPanle;

/**
 * <p>
 * Title: CurveSourceDialog
 * </p>
 * <p>
 * Description: 曲线信息对话框基础类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: kedong
 * </p>
 * 
 * @author mengxin
 * @version 1.0
 * @sign curve_update_116
 * @since 2008/01/08
 */

public class CurveSourceDialog extends JDialog {
	private List curveSourcePanelList = new ArrayList(); // @jve:decl-index=0:
	// private List allCurveList = new ArrayList(); // @jve:decl-index=0:
	private List curveList = new ArrayList(); // @jve:decl-index=0:
	private List calCurveList = new ArrayList(); // @jve:decl-index=0:

	private JPanel mainPanel = null;
	private JPanel contentPanel = null;
	private JPanel confirmPanel = null;
	private JButton okButton = null;
	private JButton cancelButton = null;
	private JButton addButton = null;// yuyaozong20080410
	private JButton deleteButton = null;// liurenyong20110610
	/** 读取数据时使用的DataClient静态方法 */
	private static DataClient dataClient = DataClient.getDataClient();

	private String addedID = null;

	private List<Series> deleteSeries = new ArrayList<Series>(); // 删除测定的ID

	// CurveViewPanel viewPanel = null;
	CustomChartPanel chartPanel = null;
	// List<ChartSeriesAttribute> seriesAttributes = null;
	int seriesIndex = 0;
	int datasetIndex = 0;

	/**
	 * 构造方法
	 * 
	 * @param frame
	 * @param title
	 * @param modal
	 * @param viewer
	 */
	public CurveSourceDialog(Frame frame, String title, boolean modal,
			CustomChartPanel chartPanel) {
		super(frame, title, modal);
		this.chartPanel = chartPanel;
		initialize();
		this.pack();
	}

	/**
	 * 构造方法
	 * 
	 * @param title
	 * @param modal
	 * @param viewer
	 */
	public CurveSourceDialog(String title, boolean modal,
			CustomChartPanel chartPanel) {
		this(null, title, modal, chartPanel);
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setContentPane(getMainPanel());
	}

	/**
	 * This method initializes mainPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(getContentPanel(), BorderLayout.CENTER);
			mainPanel.add(getConfirmPanel(), BorderLayout.SOUTH);
		}
		return mainPanel;
	}

	/**
	 * This method initializes contentPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	protected JPanel getContentPanel() {
		if (contentPanel == null) {
			List list = new ArrayList();
			// 加入曲线
			XYPlot plot = chartPanel.getChart().getXYPlot();
			List<Series> seriesList = new ArrayList<Series>();
			// this.setAllCurveList(seriesAttributes);
			for (int i = 0; i < plot.getDatasetCount(); i++) {
				XYDataset dataset = plot.getDataset(i);
				for (int j = 0; j < dataset.getSeriesCount(); j++) {
					if (dataset instanceof XYSeriesCollection) {
						XYSeries series = ((XYSeriesCollection) dataset)
								.getSeries(j);
						seriesList.add(series);
					} else if (dataset instanceof TimeSeriesCollection) {
						TimeSeries series = ((TimeSeriesCollection) dataset)
								.getSeries(j);
						seriesList.add(series);
					}
				}
			}
			contentPanel = new JPanel();
			contentPanel.setBorder(BorderFactory
					.createEtchedBorder(EtchedBorder.RAISED));
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(seriesList.size());
			gridLayout.setColumns(1);
			gridLayout.setHgap(5);
			gridLayout.setVgap(3);
			contentPanel.setLayout(gridLayout);
			addCurveSourcePanel(seriesList);
		}
		return contentPanel;
	}

	/**
	 * 添加曲线信息面板
	 * 
	 * @param list
	 */
	protected void addCurveSourcePanel(List<Series> list) {
		for (Series series : list) {
			// 时间轴
			if (series instanceof CustomTimeSeries) {
				CustomTimeSeries timeSeries = (CustomTimeSeries) series;
				// curve_bug_11 mengxin 2008/08/25
				CurveSourcePanel curveSourcePanel = new CurveSourcePanel(
						timeSeries, chartPanel);
				this.getContentPanel().add(curveSourcePanel, null);
				this.getCurveSourcePanelList().add(curveSourcePanel);
			} else {
				// 数字轴
			}
		}
		// 注册测点托拽事件 liuxiaocong 2007-03-09
		// PointDrag.registorDropReceiver(curveSourcePanel.getIdField(),
		// curveSourcePanel.getDescrField(), getViewPanel(), i);
	}

	/**
	 * This method initializes confirmPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getConfirmPanel() {
		if (confirmPanel == null) {
			confirmPanel = new JPanel();
			confirmPanel.setLayout(new FlowLayout());
			confirmPanel.setBorder(BorderFactory
					.createEtchedBorder(EtchedBorder.RAISED));
			confirmPanel.add(getOkButton(), null);
			confirmPanel.add(getAddButton(), null); // yuyaozong20080410
			confirmPanel.add(getDeleteButton(), null); // liurenyong20110610
			confirmPanel.add(getCancelButton(), null);
		}
		return confirmPanel;
	}

	protected void setCurveSourcePanelList(List curveSourcePanelList) {
		this.curveSourcePanelList = curveSourcePanelList;
	}

	protected List getCurveSourcePanelList() {
		return curveSourcePanelList;
	}

	// protected void setAllCurveList(List allCurveList) {
	// this.allCurveList = allCurveList;
	// }
	//
	// protected List getAllCurveList() {
	// return allCurveList;
	// }

	protected void setCurveList(List curveList) {
		this.curveList = curveList;
	}

	protected List getCurveList() {
		return curveList;
	}

	protected void setCalCurveList(List calCurveList) {
		this.calCurveList = calCurveList;
	}

	protected List getCalCurveList() {
		return calCurveList;
	}

	/**
	 * This method initializes okButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("确定");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					okButton_ActionPerformed(e);
				}
			});
		}
		return okButton;
	}


	public void okButton_ActionPerformed(ActionEvent e) {
		if (this.getAddedID() != null && this.getAddedID().length() != 0) {
			long addedID = Long.parseLong(this.getAddedID());
			JFreeChart chart = chartPanel.getChart();
			XYPlot plot = chartPanel.getChart().getXYPlot();
			int subTitleSize = chart.getSubtitleCount();
			XYDataset dataset = plot.getDataset(datasetIndex);
			Series series = null;
			long keyID = addedID;
			int period = 60;
			long startTime = Calendar.getInstance().getTimeInMillis();
			long endTime = Calendar.getInstance().getTimeInMillis();
			int mode = 4;
			double length = 1;
			String curveType = "1";
			if (dataset instanceof TimeSeriesCollection) {
				series = ((TimeSeriesCollection) dataset)
						.getSeries(seriesIndex);
				TimeCurveRefresherBean bean = ((CustomTimeSeries) series)
						.getRefresherBean();
				period = bean.getPeriod();
				startTime = bean.getStartTime();
				endTime = bean.getEndTime();
				curveType = bean.getCurveType();
				mode = bean.getMode();
				length = bean.getLength();
			} else if (dataset instanceof XYSeriesCollection) {
				series = ((XYSeriesCollection) dataset).getSeries(seriesIndex);
			}

			int scale = 1;
			if (dataset != null) {
				Vector result = null;
				try {
					result = MidhsService.receiveCurveData(
							DomainManager.LOCAL_DOMAIN, null, curveType, keyID,
							period, startTime, endTime, mode);
				} catch (DataFaultException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ServiceConnectionException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (LocatorException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ProxyFaultException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				ValueAxis domainAxis = plot.getDomainAxis();
				int y_type_index = dataset.getSeriesCount();
				String desc = CustomUtility.getIDDescr(DomainManager.LOCAL_DOMAIN,
						String.valueOf(addedID));
				int count = seriescount + 1;
				int c1 = 125 + count * 5;
				int c2 = count * 20;
				int c3 = count * 15;
				if (c1 > 255) {
					c1 = 255;
				}
				if (c2 > 255) {
					c2 = 255;
				}
				if (c2 > 255) {
					c2 = 255;
				}
				Color c = null;
				if (count % 2 != 0) {
					c = new Color(c1, c2, c3);
				} else {
					c = new Color(c2, c1, c3);
				}
				if (domainAxis instanceof CustomDateAxis) {
					desc = "".equals(desc) ? "Curve" + y_type_index : desc;
					CustomTimeSeries dateseries = new CustomTimeSeries(desc);
					((TimeSeriesCollection) dataset).addSeries(dateseries);
					XYItemRenderer renderer = plot.getRenderer(datasetIndex);
					renderer.setSeriesStroke(y_type_index,
							renderer.getSeriesStroke(y_type_index - 1));
					renderer.setSeriesPaint(y_type_index, Color.BLUE);
					TimeCurveRefresherBean bean = new TimeCurveRefresherBean(
							DomainManager.LOCAL_DOMAIN, Context.REALTIME,
							curveType, keyID, period, startTime, endTime, mode);
					bean.setLength(length);
					dateseries.setTimePeriodClass(((CustomTimeSeries) series)
							.getTimePeriodClass());
					dateseries.setRefresherBean(bean);
					// dateseries.refresherObservableStart();
					Class periodClass = ((CustomTimeSeries) series)
							.getTimePeriodClass();
					((CustomTimeSeries) dateseries)
							.setInitStartXValue(startTime);
					((CustomDateAxis) domainAxis).addObserver(dateseries
							.getObserver());
					List<Title> list = chart.getSubtitles();
					for (int i = 0; i < subTitleSize; i++) {
						Title subTitle = list.get(i);
						if (subTitle instanceof ChangePageTitle) {
							((ChangePageTitle) subTitle).addObserver(dateseries
									.getObserver());
						}
					}
					// for (int i = 2; i < result.size(); i++) {
					// MidhsData curveData = (MidhsData) result.get(i);
					// if (Debug.DEBUG_RECEIVE_MIDHS_CURVE) {
					// System.err
					// .println(curveData.getValidFlag() + "  "
					// + new Date(curveData.getXValue()) + "  "
					// + curveData.getYValue() + "  "
					// + curveData.getQuality());
					// }
					// int isNull = curveData.getValidFlag();
					// if (isNull == 0) {
					// float yValue = curveData.getYValue();
					// long xValue = curveData.getXValue();
					// RegularTimePeriod timePeriod =
					// RegularTimePeriod.createInstance(periodClass, new
					// Date(xValue),TimeZone.getDefault());
					// dateseries.add(timePeriod, yValue);
					// dateseries.fireSeriesChanged();
					// }
					// }

				} else if (domainAxis instanceof NumberAxis) {
					XYSeries xyseries = new XYSeries(desc);
					scale = 0;
					((XYSeriesCollection) dataset)
							.addSeries((XYSeries) xyseries);
				}

			}
			// 删除测点曲线
		} else if (getDeleteSeries() != null && getDeleteSeries().size() > 0) {
			JFreeChart chart = chartPanel.getChart();
			XYPlot plot = chart.getXYPlot();
			int datasetCount = plot.getDatasetCount();
			for (int i = datasetCount - 1; i >= 0; i--) {
				XYDataset dataset = plot.getDataset(i);
				int seriesCount = dataset.getSeriesCount();
				for (int j = seriesCount - 1; j >= 0; j--) {
					// 时间轴
					if (dataset instanceof TimeSeriesCollection) {
						Series series = ((TimeSeriesCollection) dataset)
								.getSeries(j);
						if (getDeleteSeries().contains(series)) {
							((TimeSeriesCollection) dataset)
									.removeSeries((TimeSeries) series);
						}
					} else if (dataset instanceof XYSeriesCollection) {// 数字轴
						Series series = ((XYSeriesCollection) dataset)
								.getSeries(j);
						if (getDeleteSeries().contains(series)) {
							((XYSeriesCollection) dataset)
									.removeSeries((XYSeries) series);
						}
					}
				}
				chart.titleChanged(new TitleChangeEvent(chart.getLegend()));
			}
		} else {
			XYPlot plot = chartPanel.getChart().getXYPlot();
			List curveSourcePanelList = this.getCurveSourcePanelList();
			for (int i = 0; i < curveSourcePanelList.size(); i++) {
				CurveSourcePanel curveSourcePanel = (CurveSourcePanel) curveSourcePanelList
						.get(i);
				
				Date showDate = CustomUtility.convertDateFormatStrToDate("yyyy-MM-dd",
						curveSourcePanel.getCurveDateField().getText());
				Calendar calendarInstance = Calendar.getInstance();;
				calendarInstance.setTime(showDate);
				Series series = curveSourcePanel.getSeries();
				if (series instanceof CustomTimeSeries) {
					TimeCurveRefresherBean bean = ((CustomTimeSeries) series).getRefresherBean();
					long startTime = bean.getStartTime();
					Calendar startCalendar = Calendar.getInstance();
		
					startCalendar.setTimeInMillis(startTime);
					if (startCalendar.get(Calendar.DAY_OF_MONTH) != calendarInstance.get(Calendar.DAY_OF_MONTH)) {
						startCalendar.set(Calendar.DAY_OF_MONTH, calendarInstance.get(Calendar.DAY_OF_MONTH));
						bean.setStartTime(startCalendar.getTimeInMillis());
						bean.setEndTime((long) (startCalendar.getTimeInMillis() + bean.getLength()));
						((CustomTimeSeries) series).setInitStartXValue(startCalendar.getTimeInMillis());
					}
				}
				calendarInstance.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			}
		}
		this.dispose();
		chartPanel.startDataEngine();

	}

	// public List<ChartSeriesAttribute> getSeriesAttributeByKeyID(List<String>
	// keyIDList) {
	// List<ChartSeriesAttribute> seriesAttributeList = new
	// ArrayList<ChartSeriesAttribute>();
	// for (ChartSeriesAttribute attribute : seriesAttributes) {
	// long key = attribute.getKey_ID();
	// int y_type = attribute.getY_Type();
	// int y_type_index = attribute.getY_Type_Index();
	// if (keyIDList.contains(String.valueOf(key))) {
	// seriesAttributeList.add(attribute);
	// continue;
	// }
	// }
	// return seriesAttributeList;
	// }

	/**
	 * This method initializes cancelButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("取消");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancelButton_ActionPerformed(e);
				}
			});
		}
		return cancelButton;
	}

	public void cancelButton_ActionPerformed(ActionEvent e) {
		this.dispose();
		chartPanel.startDataEngine();
	}

	/**
	 * This method initializes addButton yuyaozong20080410 added for benxi
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText("增加测点");
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addButton_ActionPerformed(e);
				}
			});
		}
		return addButton;
	}

	/**
	 * 删除测点
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText("删除测点");
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteButton_ActionPerformed(e);
				}
			});
		}
		return deleteButton;
	}

	public void deleteButton_ActionPerformed(ActionEvent e) {
		for (int i = 0; i < curveSourcePanelList.size(); i++) {
			CurveSourcePanel basePanel = (CurveSourcePanel) curveSourcePanelList
					.get(i);
			if (basePanel.isDelete()) {
				if (curveSourcePanelList.indexOf(basePanel) != -1) {
					this.getDeleteSeries().add(basePanel.getSeries());
				}

			}
		}
		getOkButton().doClick();
	}

	int seriescount = 0;

	public void addButton_ActionPerformed(ActionEvent e) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		XYPlot plot = chartPanel.getChart().getXYPlot();
		boolean isShow = true;
		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i < plot.getDatasetCount(); i++) {
			XYDataset dataset = plot.getDataset(i);
			for (int j = 0; j < dataset.getSeriesCount(); j++) {
				Comparable seriesKey = dataset.getSeriesKey(j);
				JCheckBox modeCheckbox = new JCheckBox(seriesKey.toString());
				modeCheckbox.setActionCommand("  " + i + "  " + j);
				panel.add(modeCheckbox);
				group.add(modeCheckbox);
				seriescount++;
			}
		}
		// JDialog dialog = new JDialog();
		// dialog.add(panel);
		// dialog.setVisible(true);
		int result = JOptionPane.showOptionDialog(null, panel, "模板",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, null, null);
		if (result == JOptionPane.OK_OPTION) {
			String seriesKey = group.getSelection().getActionCommand();
			String[] seriesArgStrings = seriesKey.split("  ");
			if (seriesArgStrings != null && seriesArgStrings.length == 2) {
				datasetIndex = Integer.parseInt(seriesArgStrings[0]);
				seriesIndex = Integer.parseInt(seriesArgStrings[1]);
			}
		} else {
			return;
		}

		SearchMainPanle searchIdPanel = new SearchMainPanle();
		SwingDialog dialog = new SwingDialog(
				(Frame) SwingUtilities.getAncestorOfClass(Frame.class, this),
				"检索器", true);
		dialog.getContentPane().add(searchIdPanel);
		dialog.pack();
		dialog.setAutoResize(false);
		dialog.show();

		KeyID keyid = searchIdPanel.getKeyID();
		this.setAddedID(keyid.getKeyID());
		getOkButton().doClick();
	}

	private int modifyMainPanel(String sysName, String bobName, String appName,
			String tableName, String id, String dscr) {

		return 1;
	}

	public static String getCurrentBrowserContextNodeName() {
		if (getPictureBrowserTopFrame() != null) {
			return getPictureBrowserTopFrame().getBrowser().getContextManager()
					.getCurrentContext().getNodeName();
		} else {
			return dataClient.getCurrentNode();
		}

	}

	// liuyan add to send context id in command 20070423
	public static String getCurrentBrowserContextID() {
		return dataClient.getCurrentContextID();
	}

	/**
	 * Get picture browser top frame, add by Guohui Shen on 2005-11-10
	 * 
	 * @return : picture browser top frame
	 */
	public static BrowserBaseFrame getPictureBrowserTopFrame() {
		return BrowserBaseFrameManager.getInstance().getTopBrowserBaseFrame();
	}

	protected void setAddedID(String id) {
		this.addedID = id;
	}

	protected String getAddedID() {
		return addedID;
	}

	public List getDeleteSeries() {
		return deleteSeries;
	}

	public void setDeleteSeries(List deleteSeries) {
		this.deleteSeries = deleteSeries;
	}

	// public void setViewPanel(CurveViewPanel viewPanel) {
	// this.viewPanel = viewPanel;
	// }
	//
	// public CurveViewPanel getViewPanel() {
	// return this.viewPanel;
	// }

	public static void main(String[] args) {
		String desc = CustomUtility.getIDDescr(DomainManager.LOCAL_DOMAIN,
				String.valueOf(122723132795621538L));
		System.out.println(desc);
	}

} // @jve:decl-index=0:visual-constraint="10,10"
