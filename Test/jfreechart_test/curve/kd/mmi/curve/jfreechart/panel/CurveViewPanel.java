package curve.kd.mmi.curve.jfreechart.panel;

/**
 * <p>Title: </p>
 * 
 * <p>Description: </p>
 * 
 * <p>Copyright: Copyright (c) 2004</p>
 * 
 * <p>Company: </p>
 * 
 * @author  yuxinping
 * @version 1.0
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.Series;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.custom.CustomTimeSeries;

import agency.exception.DataFaultException;
import agency.exception.LocatorException;
import agency.exception.ProxyFaultException;
import agency.exception.ServiceConnectionException;
import curve.kd.mmi.curvechart.parse.GCurveParse;
import pictureeditor.filemanager.FileType;
import pictureeditor.util.FileInfo;
import support.DataClient;

public class CurveViewPanel extends JPanel {
	// 画面浏览器浏览曲线图方式 picbrowser_autoscale mengxin 2008/10/27
	private static int AUTO_SCALE = 1; // 自适应浏览
	// zhao add 2005-12-14 for autochangesize curve_tj_59 分辨率自适应
	private float widthScale = 1.0f;

	private float highScale = 1.0f;


	private int viewType = 0;
	
	private JViewport viewport = null;
	JPanel panel = null;
	float panelScale = 1.0f;
	float oldScale = 1.0f;
	int font_size = 14;
	int old_table_height = 14;
	
	public void setPanelScale(float scale) {
		oldScale = panelScale;
		this.panelScale = scale;
	}
	
	public CurveViewPanel() {
	}

	ChartPanel chartPanel = null;
	JSplitPane splitPane = null;
	JPanel bottomPanel = new JPanel();
	double xscale = 1.0f;
	double yscale = 1.0f;
	
	JTable table = null;
	double oldwidth = 400;
	double oldheight = 300;
	public void init(String domain, String fileName, Vector args, int viewType, boolean switchApp) {
		if (null == chartPanel) {
			chartPanel = getJFreeChart(domain, fileName, switchApp);
		}

		JPanel panel = new JPanel();
		
		if (null == table) {
			table = new JTable();
		}
		SeriesMaxMinModel serModelRenderer = new SeriesMaxMinModel(seriesColorMap); 
		table.setModel(serModelRenderer);
		old_table_height = table.getRowHeight();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane);
		if (null == splitPane) {
			splitPane = new JSplitPane();
		}
		oldwidth = getBounds().getWidth();
		oldheight = getBounds().getHeight();
		splitPane.setTopComponent(chartPanel);
		splitPane.setBottomComponent(panel);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(1);
		setTableFormat(serModelRenderer);
		this.setLayout(new BorderLayout());
		this.add(splitPane,BorderLayout.CENTER);
	}
	
	private class SeriesMaxMinModel extends DefaultTableCellRenderer implements TableModel{

		final String[] columnStirngs = {"曲线名称","最大值","最小值"}; 
		Vector<Vector> seriess = new Vector<Vector>();
		public SeriesMaxMinModel(Vector<Vector> seriess) {
			this.seriess = seriess;
		}
		
		public void setModelData(Vector<Vector> data) {
			seriess = data;
		}
	
		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return String.class;
		}
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnStirngs.length;
		}
		@Override
		public String getColumnName(int columnIndex) {
			// TODO Auto-generated method stub
			return columnStirngs[columnIndex];
		}
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return seriess.size();
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			Object object = new Object();
			Vector seriesAndColor = seriess.get(rowIndex);
			Series series = (Series) seriesAndColor.get(0);
			double max = 0;
			double min =0;
			if (series instanceof CustomTimeSeries) {
				max = ((CustomTimeSeries) series).getMaxY();
				min = ((CustomTimeSeries) series).getMinY();
			} else if (series instanceof XYSeries){
				max = ((XYSeries) series).getMaxY();
				min = ((XYSeries) series).getMinY();
			}
			if (columnIndex == 0) {
				object = series.getKey();
			} else if (columnIndex == 1) {
				object = max;
			} else {
				object = min;
			}
			return object;
		}
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
					row, column);
			Font font = component.getFont();
			float size = font_size * panelScale;
			font = font.deriveFont(size);
			component.setFont(font);
			int width = component.getWidth();
			int height = component.getHeight();
			component.setSize(width,height);
			table.getTableHeader().setFont(font);
			Color color = (Color) seriess.get(row).lastElement();
			component.setForeground(color);
			return component;
		}
	}
	
	
	public Vector seriesColorMap = null;
	private GCurveParse g_CurveParse = null;
	/**
	 * 获取JFreeChart
	 * @param domain
	 * @param fileName
	 * @param switchApp
	 * @return
	 */
	private ChartPanel getJFreeChart(String domain, String fileName, boolean switchApp) {
		FileInfo fileInfo = new FileInfo(fileName);
		String storeType = fileInfo.getFileType().getStoreType();
		byte[] byteFile = null;
		try {
			if (domain == null || domain.length() == 0) {
				byteFile = DataClient.getByteFile(fileInfo.getAbsolutePath());
			} else {
				byteFile = DataClient.getByteFile(domain, fileInfo.getAbsolutePath());
			}
		} catch (DataFaultException e) {
			e.printStackTrace();
		} catch (ServiceConnectionException e) {
			e.printStackTrace();
		} catch (LocatorException e) {
			e.printStackTrace();
		} catch (ProxyFaultException e) {
			e.printStackTrace();
		}
				
		if (storeType.equals(FileType.STORE_TYPE_G)) {
			try {
				SAXBuilder builder = new SAXBuilder();
				Document document = builder.build(new ByteArrayInputStream(byteFile));
				GCurveParse gCurveParser = new GCurveParse();
				gCurveParser.setDocument(document);
				gCurveParser.setSwitchApp(switchApp);//modify by liurenyong. 增加曲线根据应用自动切换
				gCurveParser.parse();
				g_CurveParse = gCurveParser;
				chartPanel = gCurveParser.getJfreeChart(this);
				seriesColorMap = getSeriesTableModel(chartPanel);
//				Globals.setEditor(null);
//				Globals.setDesignFrame(null);
//				Globals.setDesignTime(false);
				return chartPanel;
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	private Vector getSeriesTableModel(ChartPanel chartPanel2) {
		Vector seriesColorVector = new Vector();
		XYPlot plot = chartPanel2.getChart().getXYPlot();
		int count = plot.getDatasetCount();
		for (int i = 0; i < count; i++) {
			XYDataset dataset = plot.getDataset(i);
			int seriesCount = dataset.getSeriesCount();
			for (int j = 0; j < seriesCount; j++) {
				if (dataset instanceof TimeSeriesCollection) {
					TimeSeries series = ((TimeSeriesCollection) dataset).getSeries(j);
					Vector data = new Vector();
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>" + series.getMaxY() +"   " + series.getMinY());
					data.add(series);
					data.add(plot.getRenderer(i).getSeriesPaint(j));
					seriesColorVector.add(data);
				}
			}
		}
		return seriesColorVector;
	}
	

//
//	/**
//	 * 
//	 * @param scale
//	 * @deprecated
//	 */
//	public void setScale(float scale) {
//		_viewer.setScale(scale);
//	}
//
//	// zhao add 2005-12-14 for autochangesize curve_tj_59 分辨率自适应
//	public void setWidthHighScale(float widthScale, float highScale) {
//		this.widthScale = widthScale;
//		this.highScale = highScale;
//		_viewer.setWidthScale(widthScale);
//		_viewer.setHighScale(highScale);
//	}

	// zhao add 2005-12-14 for autochangesize curve_tj_59 分辨率自适应
	public float getWidthScale() {
		return widthScale;
	}

	// zhao add 2005-12-14 for autochangesize curve_tj_59 分辨率自适应
	public float getHighScale() {
		return highScale;
	}

//	public void setAffineTransform(AffineTransform at) {
//		_viewer.setAffineTransform(at);
//	}

	public void setZoomScale(float zoomscale) {
		if (panelScale >= 1) {
//			setSpliePaneSize();
			setFreeChartFont();
			int width = (int) (this.getWidth() * 0.2);
			int height = (int)(this.getHeight() * 0.2);
			if (table != null) {
				int rowHeight = (int) (old_table_height * panelScale);
				table.setRowHeight(rowHeight);
				float size = 14 * panelScale;
				Font font = table.getFont();
				font = font.deriveFont(size);
				table.setFont(font);
				
			}
		}
//		_viewer.setZoomScale(zoomscale);
	}

	public void setImageSize(int w, int h) {
//		_viewer.setImageSize(w, h);
	}

	
//	public void setSpliePaneSize() {
//		if (null != splitPane) {
////			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
////			splitPane.setSize(this.getSize());
////			chartPanel.setSize(this.getSize().width, (int) (this.getSize().height * 0.2));
//			splitPane.setResizeWeight(0.8);
//			splitPane.setContinuousLayout(true);
////			splitPane.updateUI();
//		}
//	}

	public void setFreeChartFont() {
		if (null != chartPanel && null != chartPanel.getChart()) {
			XYPlot xyPlot = chartPanel.getChart().getXYPlot();
			int domainCount = xyPlot.getDomainAxisCount();
			int rangeCount = xyPlot.getRangeAxisCount();
//			ValueAxis[] valueAxiss = new ValueAxis[domainCount + rangeCount];
			for (int i=0; i<domainCount; i++) {
				ValueAxis valueAxis = xyPlot.getDomainAxis(i);
				Font font = valueAxis.getTickLabelFont();
				float size =(float)( 14 * panelScale);
				font = font.deriveFont(size);
				System.out.println("==========" + 14  +" " + panelScale);
				valueAxis.setTickLabelFont(font);
				System.out.println("fontSize"+valueAxis.getLabelFont().getSize());
			}
			for (int i=0; i< rangeCount; i++) {
				ValueAxis valueAxis = xyPlot.getRangeAxis(i);
				Font font = valueAxis.getTickLabelFont();
				float size =(float)( 14  * panelScale);
				font = font.deriveFont(size);
				valueAxis.setTickLabelFont(font);
			}
		}
		
		
	}
	
	private class TableRenderer_Custom extends DefaultTableCellRenderer {
		
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			// TODO Auto-generated method stub
			Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
					row, column);
			Font font = component.getFont();
			float size = font_size * panelScale;
			font = font.deriveFont(size);
			component.setFont(font);
			int width = component.getWidth();
			int height = component.getHeight();
			component.setSize(width,height);
			table.getTableHeader().setFont(font);
			return component;
		}
		
	}
	
	
	public void setTableFormat(SeriesMaxMinModel renderer) {
		if (table != null) {
			for (int i=0;i<table.getColumnCount();i++) {
				TableColumn column = table.getColumnModel().getColumn(i);
				column.setCellRenderer(renderer);
				int columnWidth = (int) (column.getWidth()/oldScale * panelScale);
				column.setWidth(columnWidth);
			}
		}
	}
	

	/**
	 * 设置画面浏览器浏览曲线图方式
	 * 
	 * @param type
	 * 
	 * @sign picbrowser_autoscale
	 * @author mengxin
	 * @since 2008/10/27
	 */
	public void setViewType(int type) {
		this.viewType = type;
	}

	/**
	 * 得到画面浏览器浏览曲线图方式
	 * 
	 * @return viewType
	 * 
	 * @sign picbrowser_autoscale
	 * @author mengxin
	 * @since 2008/10/27
	 */
	private int getViewScaleType() {
		return this.viewType;
	}

	/**
	 * 为CurveViewPanel设置画面浏览器视口
	 * 
	 * @param viewport
	 * 
	 * @sign curve_update_134
	 * @author mengxin
	 * @since 2008/09/24
	 */
	public void setBrowserViewport(JViewport viewport) {
		this.viewport = viewport;
	}

	/**
	 * 得到CurveViewPanel的画面浏览器视口
	 * 
	 * @sign curve_update_134
	 * @author mengxin
	 * @since 2008/09/24
	 */
	public JViewport getBrowserViewport() {
		return this.viewport;
	}
	
	public ChartPanel getChartPanel() {
		return chartPanel; 
	}
	
	public void restoreScreenScale() {
		
	}
}
