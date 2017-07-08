package curve.kd.mmi.curvechart.parse;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observer;

import javax.swing.JFrame;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.CustomChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.custom.axis.CustomDateAxis;
import org.jfree.chart.custom.axis.DomainTimeGenerator;
import org.jfree.chart.custom.axis.RunProperties;
import org.jfree.chart.custom.plot.CustomXYPlot;
import org.jfree.chart.custom.renderer.CustomXYStepShapeRenderer;
import org.jfree.chart.custom.title.ChangePageTitle;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.Series;
import org.jfree.data.time.DateRange;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Month;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.custom.CustomTimeSeries;
import org.jfree.data.xy.custom.CustomXYSeries;

import agency.Debug;
import agency.exception.DataFaultException;
import agency.exception.LocatorException;
import agency.exception.ProxyFaultException;
import agency.exception.ServiceConnectionException;
import agency.message.base.DomainManager;
import agency.message.midhs.MidhsCurveStaticFinals;
import context.Context;
import curve.kd.mmi.curve.jfreechart.enums.TimeFormatEnum;
import curve.kd.mmi.curve.jfreechart.panel.CurveViewPanel;
import curve.kd.mmi.curve.jfreechart.refresher.CurveRefresherBean;
import curve.kd.mmi.curve.jfreechart.refresher.NumberCurveRefresherBean;
import curve.kd.mmi.curve.jfreechart.refresher.RefreshObservable;
import curve.kd.mmi.curve.jfreechart.refresher.TimeCurveRefresherBean;
import curve.kd.mmi.curvechart.graphics.glanguage.GCurveConvertFromStr;
import curve.kd.mmi.curvechart.util.CustomUtility;
import pictureeditor.svg.tools.ParserTool;
import support.DataClient;

/**
 * 曲线模板G文件解析器
 * 
 * @author mengxin
 * @since 2010/02/23
 */
public class GCurveParse {

	private Document documentToParse = null;

	private Color drawAreaColor = Color.white;

	private int fillPatternValue = GCurveConvertFromStr.CHART_BACKGROUND_FILL_SINGLE;// 默认单色

	private int drawAreaPosX = 90;
	private int drawAreaPosY = 60;
	private int drawAreaWidth = 896;
	private int drawAreaHeight = 512;
	int runPace = 1;
	boolean isRun = false;

	boolean switchApp = false;

	RegularTimePeriod timePeriod = null;
	int domainStart = 0;
	int domainEnd = 1;
	int calendarField = Calendar.SECOND;
	public int refreshPeriod = 60;

	XYPlot plot = new CustomXYPlot();
	JFreeChart chart = new JFreeChart(plot);
	Shape shape = new Area(new Rectangle2D.Double(-4.0D, -4.0D, 8.0D, 8.0D));
	List<Observer> observers = new ArrayList<Observer>();

	private RunProperties runProperties;

	public GCurveParse() {
	}

	public String getUTF8String(String string) {
		String str = "";
		try {
			str = new String(string.getBytes(), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(string + "  " + str);
		return str;
	}

	public void setDocument(Document document) {
		documentToParse = document;
	}

	/**
	 * 解析G文件
	 */
	public void parse() {
		Element gElement = documentToParse.getRootElement();
		int count = ParserTool.getSubElementCount(gElement);
		for (int i = 0; i < count; i++) {
			long time = System.currentTimeMillis();
			Element nextElement = ParserTool.getElement(gElement, i);
			if (nextElement.getName().equals(GCurveConvertFromStr.CURVE)) {
				parseCurve(nextElement);
			} else if (nextElement.getName().equals(GCurveConvertFromStr.GRID)) {
				parseGrid(nextElement);
			} else if (nextElement.getName()
					.equals(GCurveConvertFromStr.X_AXIS)) {
				parseXAxis(nextElement);
			} else if (nextElement.getName()
					.equals(GCurveConvertFromStr.Y_AXIS)) {
				parseYAxis(nextElement);
			} else if (nextElement.getName()
					.equals(GCurveConvertFromStr.ENTITY)) {
				parseEntity(nextElement);
			} else if (nextElement.getName().equals(
					GCurveConvertFromStr.DYNAMIC)) {
				parseDynamic(nextElement);
			} else if (nextElement.getName()
					.equals(GCurveConvertFromStr.LEGEND)) {
				parseLegend(nextElement);
			} else if (nextElement.getName().equals(
					GCurveConvertFromStr.TURNOVER_FIELD)) {
				parseTurnOverField(nextElement);
			}
			System.out.println(nextElement.getName() + " parse time "
					+ (System.currentTimeMillis() - time));
		}

		for (Observer observer : observers) {
			if (observer instanceof RefreshObservable) {
				((RefreshObservable) observer).start();
			}
		}

		 if (plot.getDomainAxis() instanceof CustomDateAxis) {
			 CustomDateAxis domainAxis = ((CustomDateAxis) plot.getDomainAxis());
			 domainAxis.addObserver();
			 domainAxis.startRunWithProperty(runProperties);
		 }

	}

	public ChartPanel getJfreeChart(CurveViewPanel viewPanel) {
		return new CustomChartPanel(chart, viewPanel);
	}

	/**
	 * 解析曲线全局属性
	 * 
	 * @param curveElement
	 */
	private void parseCurve(Element curveElement) {

		String value = null;

		// 背景填充模式
		value = null;
		value = curveElement
				.getAttributeValue(GCurveConvertFromStr.CHART_BACKGROUND_FILLPATTERN);
		if (value != null && value.length() != 0) {
			fillPatternValue = Integer.parseInt(value);
		}

		// 背景图片路径
		String picUrl = curveElement
				.getAttributeValue(GCurveConvertFromStr.CHART_BACKGROUND_PIC);
		if (null != picUrl && !"".equals(picUrl)) {
			byte[] imageBytes = null;
			try {
				imageBytes = DataClient.getByteFile(picUrl);
				Image image = Toolkit.getDefaultToolkit().createImage(
						imageBytes);
				if (null != image) {
					chart.setBackgroundImage(image);
				}
			} catch (DataFaultException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProxyFaultException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 是否显示标题
		int title_show = GCurveConvertFromStr.BOOLEAN_FALSE;// 默认不显示
		value = null;
		value = curveElement.getAttributeValue(GCurveConvertFromStr.TITLE_SHOW);
		if (value != null && value.length() != 0) {
			title_show = Integer.parseInt(value);
		}

		boolean isTitleShow = false;
		if (title_show == GCurveConvertFromStr.BOOLEAN_TRUE) {
			isTitleShow = true;
		}

		// 标题内容
		String title_content = curveElement
				.getAttributeValue(GCurveConvertFromStr.TITLE_CONTENT);
		if (title_content == null) {
			title_content = curveElement.getAttributeValue("title");
		}

		// 字体名称
		String fontFamily = curveElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_FONT_FAMILY);
		if (fontFamily == null || fontFamily.length() == 0) {
			fontFamily = GCurveConvertFromStr.DEFAULT_FONT_FAMILY;
		}

		// 字体类型
		int fontWeight = GCurveConvertFromStr.DEFAULT_FONT_WEIGHT;
		value = null;
		value = curveElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_FONT_WEIGHT);
		if (value != null && value.length() != 0) {
			fontWeight = Integer.parseInt(value);
		}

		// 字体大小
		int fontSize = GCurveConvertFromStr.DEFAULT_FONT_SIZE;
		value = null;
		value = curveElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_FONT_SIZE);
		if (value != null && value.length() != 0) {
			fontSize = Integer.parseInt(value);
		}

		// 字体颜色
		int fc = GCurveConvertFromStr.DEFAULT_FONT_COLOR;
		value = null;
		value = curveElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_FONT_COLOR);
		if (value != null && value.length() != 0) {
			fc = Integer.parseInt(value);
		}
		Color fontColor = new Color(fc);

		// 是否显示标题以及标题内同
		if (title_content == null) {
			title_content = "";
		}
		TextTitle title = new TextTitle(title_content);
		title.setVisible(isTitleShow);
		title.setFont(new Font(fontFamily, fontWeight, fontSize));
		title.setPaint(fontColor);
		// freeChart.setTitle(title);

		// 刷新周期
		int refreshPeriod = GCurveConvertFromStr.REFRESH_DEFAULT_PERIOD;// 默认60秒
		value = null;
		value = curveElement
				.getAttributeValue(GCurveConvertFromStr.REFRESH_INTERVAL);
		if (value != null && value.length() != 0) {
			refreshPeriod = Integer.parseInt(value);
		}

		// 多轴类型
		int multiAxisType = GCurveConvertFromStr.MULTI_AXIS_TYPE_SINGLE;// 默认单轴
		value = null;
		value = curveElement
				.getAttributeValue(GCurveConvertFromStr.MULTI_AXIS_TYPE);
		if (value != null && value.length() != 0) {
			multiAxisType = Integer.parseInt(value);
		}
		// multipleXType = multiAxisType;

		// 背景颜色
		int bgc = GCurveConvertFromStr.COLOR_WITHE; // 默认-1
		value = null;
		value = curveElement
				.getAttributeValue(GCurveConvertFromStr.CHART_BACKGROUND_COLOR);
		if (value != null && value.length() != 0) {
			bgc = Integer.parseInt(value);
		}

		Color backgroundColor = new Color(bgc);

		// 网格背景颜色
		int cgc = GCurveConvertFromStr.COLOR_WITHE;// 默认-1
		value = null;
		value = curveElement
				.getAttributeValue(GCurveConvertFromStr.GRID_BACKGROUND_COLOR);
		if (value != null && value.length() != 0) {
			cgc = Integer.parseInt(value);
		}
		drawAreaColor = new Color(cgc);
		plot.setBackgroundPaint(drawAreaColor);

		// 设置填充模式
		if (fillPatternValue == GCurveConvertFromStr.CHART_BACKGROUND_FILL_SINGLE) {
			chart.setBackgroundPaint(backgroundColor);// 设置背景单色
		} else if (fillPatternValue == GCurveConvertFromStr.CHART_BACKGROUND_FILL_TRANSPARENT) {
			chart.setBackgroundPaint(Color.black);
			chart.setBorderStroke(new BasicStroke(0));
		} else {
			chart.setBackgroundPaint(Color.black);
			chart.setBorderStroke(new BasicStroke(0));
		}

		// 设置刷新周期
		this.refreshPeriod = refreshPeriod;
		// plot.setBackgroundPaint(drawAreaColor);
		// chart.setBackgroundPaint(backgroundColor);
		TextTitle textTitle = new TextTitle();
		textTitle.setFont(new Font(fontFamily, fontWeight, fontSize));
		textTitle.setText(title_content);
		chart.setTitle(textTitle);
	}

	/**
	 * 解析曲线网格属性
	 * 
	 * @param gridElement
	 */
	private void parseGrid(Element gridElement) {
		String value = null;
		// 左上角X坐标
		value = gridElement.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_X);
		if (value != null && value.length() != 0) {
			drawAreaPosX = Integer.parseInt(value) + 10;
		}

		// 左上角Y坐标
		value = null;
		value = gridElement.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_Y);
		if (value != null && value.length() != 0) {
			drawAreaPosY = Integer.parseInt(value) + 6;
		}

		// 宽度
		value = null;
		value = gridElement
				.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_WIDTH);
		if (value != null && value.length() != 0) {
			this.drawAreaWidth = Integer.parseInt(value) - 30;
		}

		// 高度
		value = null;
		value = gridElement
				.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_HEIGHT);
		if (value != null && value.length() != 0) {
			this.drawAreaHeight = Integer.parseInt(value) - 10;
		}

		// 主网格颜色
		int main_grid_c = GCurveConvertFromStr.COLOR_BLACK;// 默认黑色
		value = null;
		value = gridElement
				.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_COLOR);
		if (value != null && value.length() != 0) {
			main_grid_c = Integer.parseInt(value);
		}

		// 主网格线宽
		int mainGridLineWidth = GCurveConvertFromStr.LINE_DEFAULT_WIDTH;// 默认1
		value = null;
		value = gridElement
				.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_LINE_WIDTH);
		if (value != null && value.length() != 0) {
			mainGridLineWidth = Integer.parseInt(value);
		}

		// 主网格线型
		int mainGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DASH; // 默认虚线
		value = null;
		value = gridElement
				.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_LINE_STYLE);
		if (value != null && value.length() != 0) {
			mainGridLineStyle = Integer.parseInt(value);
		}

		// 是否显示X方向主网格
		int main_grid_x_show = GCurveConvertFromStr.BOOLEAN_TRUE;
		value = null;
		value = gridElement
				.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_X_SHOW);
		if (value != null && value.length() != 0) {
			main_grid_x_show = Integer.parseInt(value);
		}
		boolean isMainGridXShow = false;
		if (main_grid_x_show == GCurveConvertFromStr.BOOLEAN_TRUE) {
			isMainGridXShow = true;
		}

		// 是否显示Y方向主网格
		int main_grid_y_show = GCurveConvertFromStr.BOOLEAN_TRUE;
		value = null;
		value = gridElement
				.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_Y_SHOW);
		if (value != null && value.length() != 0) {
			main_grid_y_show = Integer.parseInt(value);
		}
		boolean isMainGridYShow = false;
		if (main_grid_y_show == GCurveConvertFromStr.BOOLEAN_TRUE) {
			isMainGridYShow = true;
		}

		// 副网格颜色
		int sub_grid_c = GCurveConvertFromStr.COLOR_BLACK;
		value = null;
		value = gridElement
				.getAttributeValue(GCurveConvertFromStr.SUB_GRID_COLOR);
		if (value != null && value.length() != 0) {
			sub_grid_c = Integer.parseInt(value);
		}
		Color subGridColor = new Color(sub_grid_c);

		// 副网格线宽
		int subGridLineWidth = GCurveConvertFromStr.LINE_DEFAULT_WIDTH;
		value = null;
		value = gridElement
				.getAttributeValue(GCurveConvertFromStr.SUB_GRID_LINE_WIDTH);
		if (value != null && value.length() != 0) {
			subGridLineWidth = Integer.parseInt(value);
		}

		// 副网格线型
		int subGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DASH;
		value = null;
		value = gridElement
				.getAttributeValue(GCurveConvertFromStr.SUB_GRID_LINE_STYLE);
		if (value != null && value.length() != 0) {
			subGridLineStyle = Integer.parseInt(value);
		}

		// 是否显示X方向副网格
		int sub_grid_x_show = GCurveConvertFromStr.BOOLEAN_FALSE;
		value = null;
		value = gridElement
				.getAttributeValue(GCurveConvertFromStr.SUB_GRID_X_SHOW);
		if (value != null && value.length() != 0) {
			sub_grid_x_show = Integer.parseInt(value);
		}
		boolean isSubGridXShow = false;
		if (sub_grid_x_show == GCurveConvertFromStr.BOOLEAN_TRUE) {
			isSubGridXShow = true;
		}

		// 是否显示Y方向副网格
		int sub_grid_y_show = GCurveConvertFromStr.BOOLEAN_FALSE;
		value = null;
		value = gridElement
				.getAttributeValue(GCurveConvertFromStr.SUB_GRID_Y_SHOW);
		if (value != null && value.length() != 0) {
			sub_grid_y_show = Integer.parseInt(value);
		}
		boolean isSubGridYShow = false;
		if (sub_grid_y_show == GCurveConvertFromStr.BOOLEAN_TRUE) {
			isSubGridYShow = true;
		}

		float[] main_dash = getStrokeDash(mainGridLineStyle);
		Stroke mainGridLineStroke = new BasicStroke(mainGridLineWidth,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, main_dash, 0);
		plot.setDomainGridlineStroke(mainGridLineStroke);
		plot.setRangeGridlineStroke(mainGridLineStroke);
		plot.setDomainGridlinePaint(new Color(main_grid_c));
		plot.setRangeGridlinePaint(new Color(main_grid_c));
		plot.setDomainGridlinesVisible(isMainGridXShow);
		plot.setRangeGridlinesVisible(isMainGridYShow);

		/**
		 * 设置副网格属性.
		 */
		float[] sub_dash = getStrokeDash(subGridLineStyle);
		Stroke subGridLineStroke = new BasicStroke(subGridLineWidth,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, sub_dash, 0);
		plot.setDomainMinorGridlineStroke(subGridLineStroke);
		plot.setRangeMinorGridlineStroke(subGridLineStroke);
		plot.setDomainMinorGridlinePaint(new Color(sub_grid_c));
		plot.setRangeMinorGridlinePaint(new Color(sub_grid_c));
		plot.setDomainMinorGridlinesVisible(isSubGridXShow);
		plot.setRangeMinorGridlinesVisible(isSubGridYShow);

	}

	/**
	 * 解析曲线X轴属性
	 * 
	 * @param xAxisElement
	 */
	private void parseXAxis(Element xAxisElement) {
		ValueAxis axis = null;

		String value = null;
		// 坐标轴类型
		int scaleType = GCurveConvertFromStr.AXIS_X_SCALE_TYPE_TIME;
		value = xAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_SCALE_TYPE);
		if (value != null && value.length() != 0) {
			scaleType = Integer.parseInt(value);
		}

		// 标尺是否显示
		int show = GCurveConvertFromStr.BOOLEAN_TRUE;
		value = null;
		value = xAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_SHOW);
		if (value != null && value.length() != 0) {
			show = Integer.parseInt(value);
		}
		boolean axisXShow = false;
		if (show == GCurveConvertFromStr.BOOLEAN_TRUE) {
			axisXShow = true;
		}

		// 主间距
		int mainSpace = GCurveConvertFromStr.MAIN_SPACE_DEFAULT;
		value = null;
		value = xAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_MAIN_SPACE);
		if (value != null && value.length() != 0) {
			mainSpace = Integer.parseInt(value);
		}
		if (mainSpace == 0) {
			mainSpace = GCurveConvertFromStr.MAIN_SPACE_DEFAULT;
		}

		// 次间距
		int subSpace = GCurveConvertFromStr.SUB_SPACE_DEFAULT;
		value = null;
		value = xAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_SUB_SPACE);
		if (value != null && value.length() != 0) {
			subSpace = Integer.parseInt(value);
		}
		if (subSpace == 0) {
			subSpace = GCurveConvertFromStr.SUB_SPACE_DEFAULT;
		}

		// 步长单位
		int runUnit = GCurveConvertFromStr.RUN_UNIT;
		value = null;
		value = xAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_RUN_UNIT);
		if (value != null && value.length() != 0) {
			runUnit = Integer.parseInt(value);
		}

		// 字体名称
		String fontFamily = xAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_FONT_FAMILY);
		if (fontFamily == null || fontFamily.length() == 0) {
			fontFamily = GCurveConvertFromStr.DEFAULT_FONT_FAMILY;
		}

		// 字体类型
		int fontWeight = GCurveConvertFromStr.DEFAULT_FONT_WEIGHT;
		value = null;
		value = xAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_FONT_WEIGHT);
		if (value != null && value.length() != 0) {
			fontWeight = Integer.parseInt(value);
		}

		// 字体大小
		int fontSize = GCurveConvertFromStr.DEFAULT_FONT_SIZE;
		value = null;
		value = xAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_FONT_SIZE);
		if (value != null && value.length() != 0) {
			fontSize = Integer.parseInt(value);
		}

		// 字体颜色
		int fc = GCurveConvertFromStr.DEFAULT_FONT_COLOR;
		value = null;
		value = xAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_X_FONT_COLOR);
		if (value != null && value.length() != 0) {
			fc = Integer.parseInt(value);
		}
		Color fontColor = new Color(fc);

		// 时间轴
		if (scaleType == GCurveConvertFromStr.AXIS_X_SCALE_TYPE_TIME) {
			axis = new CustomDateAxis();
			// 标尺格式
			int timeFormat = 4;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_FORMAT);
			if (value != null && value.length() != 0) {
				timeFormat = Integer.parseInt(value);
			}

			// 开始时刻
			int start = GCurveConvertFromStr.DEFAULT_TIME_START;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_START);
			if (value != null && value.length() != 0) {
				start = Integer.parseInt(value);
			}

			// 结束时刻
			int end = GCurveConvertFromStr.DEFAULT_TIME_END;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_END);
			if (value != null && value.length() != 0) {
				end = Integer.parseInt(value);
			}

			// 时间跨度
			int span = GCurveConvertFromStr.DEFAULT_TIME_SPAN;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_SPAN);
			if (value != null && value.length() != 0) {
				span = Integer.parseInt(value);
			}

			// 是否绝对时间
			int abs_time = GCurveConvertFromStr.BOOLEAN_TRUE;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_IS_ABS);
			if (value != null && value.length() != 0) {
				abs_time = Integer.parseInt(value);
			}
			boolean isAbs = false;
			if (abs_time == GCurveConvertFromStr.BOOLEAN_TRUE) {
				isAbs = true;
			}

			// 刻度时间是否取整
			int int_time = GCurveConvertFromStr.BOOLEAN_FALSE;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_IS_INT);
			if (value != null && value.length() != 0) {
				int_time = Integer.parseInt(value);
			}
			boolean isInt = false;
			if (int_time == GCurveConvertFromStr.BOOLEAN_TRUE) {
				isInt = true;
			}

			// 取整时间值
			int intTimeValue = GCurveConvertFromStr.DEFAULT_TIME_INT_VALUE;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_INT_TIME_VALUE);
			if (value != null && value.length() != 0) {
				intTimeValue = Integer.parseInt(value);
			}

			// 是否跑动
			int run = GCurveConvertFromStr.BOOLEAN_FALSE;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_RUN);
			if (value != null && value.length() != 0) {
				run = Integer.parseInt(value);
			}
			boolean isRun = false;
			if (run == GCurveConvertFromStr.BOOLEAN_TRUE) {
				isRun = true;
			}
			// 跑动步长
			int runPace = 1;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_RUN_PACE);
			if (value != null && value.length() != 0) {
				runPace = Integer.parseInt(value);
			}

			// 时间间隔单位
			int unit = GCurveConvertFromStr.AXIS_X_TIME_UNIT_HOUR;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_UNIT);
			if (value != null && value.length() != 0) {
				unit = Integer.parseInt(value);
			}

			// pace
			value = null;
			int pace = 7200;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_PACE);
			this.isRun = isRun;
			this.runPace = runPace;

			DateTickUnitType type = DateTickUnitType.HOUR;
			int calendar_Filed = Calendar.SECOND;
			if (unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_SECOND) {
				timePeriod = new Second();
				pace = span / mainSpace;
				type = DateTickUnitType.SECOND;
			} else if (unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_MINUTE) {
				timePeriod = new Minute();
				pace = span / mainSpace * 60;
				type = DateTickUnitType.MINUTE;
				calendar_Filed = Calendar.MINUTE;
			} else if (unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_HOUR) {
				timePeriod = new Hour();
				type = DateTickUnitType.HOUR;
				calendar_Filed = Calendar.HOUR_OF_DAY;
				pace = span / mainSpace * 60 * 60;
			} else if (unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_DAY) {
				timePeriod = new Day();
				type = DateTickUnitType.DAY;
				calendar_Filed = Calendar.DAY_OF_MONTH;
				pace = span / mainSpace * 60 * 60 * 24;
			} else if (unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_WEEK) {
				timePeriod = new Week();
				type = DateTickUnitType.DAY;
				calendar_Filed = Calendar.DAY_OF_MONTH;
				pace = span / mainSpace * 60 * 60 * 24 * 7;
			} else if (unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_MONTH) {
				timePeriod = new Month();
				type = DateTickUnitType.MONTH;
				calendar_Filed = Calendar.MONTH;
				pace = span / mainSpace * 60 * 60 * 24 * 30;
			} else if (unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_QUARTER) {
				timePeriod = new Quarter();
				type = DateTickUnitType.MONTH;
				calendar_Filed = Calendar.MONTH;
				pace = span / mainSpace * 60 * 60 * 24 * 30 * 4;
			} else if (unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_YEAR) {
				timePeriod = new Year();
				type = DateTickUnitType.YEAR;
				calendar_Filed = Calendar.YEAR;
				pace = span / mainSpace * 60 * 60 * 24 * 365;
			}
			// timePeriod = new Second();
			// type = DateTickUnitType.SECOND;
			// calendar_Filed = Calendar.SECOND;
			TimeFormatEnum formatEnum = TimeFormatEnum.geTimeFormatEmnu(timeFormat);
			DateFormat formatter = new SimpleDateFormat(formatEnum.getFormatterString());
			this.calendarField = calendar_Filed;

			this.domainStart = start;
			this.domainEnd = end;
			runProperties = new RunProperties();
			runProperties.setCalendarField(calendar_Filed);
			runProperties.setDomainEnd(domainEnd);
			runProperties.setDomainStart(domainStart);
			runProperties.setInt(isInt);
			runProperties.setIntTimeValue(intTimeValue);
			runProperties.setRun(isRun);
			runProperties.setRunPace(runPace);
			Calendar[] calendars = DomainTimeGenerator
					.getDomainDate(runProperties);

			TickUnit dateTickUnit = getNumberTickUnit(domainEnd, domainStart,
					mainSpace, type, formatter);
			// TickUnit dateTickUnit = getTimeTickUnit(type,pace, formatter);
			// axis.setAutoTickUnitSelection(false);
			axis.setAutoRange(true);
			((CustomDateAxis) axis).setTickUnit((DateTickUnit) dateTickUnit);
			((CustomDateAxis)axis).setDateFormatOverride(formatter);
			DateRange datRange = new DateRange(calendars[0].getTime(),
					calendars[1].getTime());
			((CustomDateAxis) axis).setInitDateRange(datRange);
			axis.setRange(datRange);

		}
		// 数字轴
		else if (scaleType == GCurveConvertFromStr.AXIS_X_SCALE_TYPE_NUM) {
			axis = new NumberAxis();
			// 最大值
			float max = GCurveConvertFromStr.DEFAULT_MAX_VALUE;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_NUMBER_MAX);
			if (value != null && value.length() != 0) {
				max = Float.parseFloat(value);
			}

			// 最小值
			float min = GCurveConvertFromStr.DEFAULT_MIN_VALUE;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_NUMBER_MIN);
			if (value != null && value.length() != 0) {
				min = Float.parseFloat(value);
			}

			// 小数有效位数
			int decimal = GCurveConvertFromStr.DEFAULT_DECIMAL_VALUE;
			value = null;
			value = xAxisElement
					.getAttributeValue(GCurveConvertFromStr.AXIS_X_NUMBER_DECIMAL);
			if (value != null && value.length() != 0) {
				decimal = Integer.parseInt(value);
			}

			/**
			 * 设置属性.
			 */
			axis.setRange(min, max);
			DecimalFormat decimalFormat = new DecimalFormat();
			decimalFormat.setMaximumFractionDigits(decimal);
			decimalFormat.setMinimumFractionDigits(decimal);
			TickUnit unit = getNumberTickUnit(max, min, mainSpace, null, null);
			((NumberAxis) axis).setTickUnit((NumberTickUnit) unit);
			((NumberAxis) axis).setNumberFormatOverride(decimalFormat);
		}

		else if (scaleType == GCurveConvertFromStr.AXIS_X_SCALE_TYPE_RTDB) {

		}

		axis.setVisible(axisXShow);
		axis.setMinorTickCount(subSpace);
		axis.setMinorTickMarksVisible(true);
		axis.setTickMarksVisible(true);
		axis.setTickLabelFont(new Font(fontFamily, fontWeight, fontSize));
		axis.setAxisLineVisible(true);
		axis.setTickLabelPaint(new Color(fc));
		axis.setAxisLinePaint(new Color(fc));
		axis.setLabel("");
		plot.setDomainAxis(axis);
	}

	/**
	 * 设置数字轴标签间隔.
	 * 
	 * @param max
	 * @param min
	 * @param mainspace
	 * @param type
	 * @param formatter
	 * @return
	 */
	public TickUnit getNumberTickUnit(double max, double min, int mainspace,
			DateTickUnitType type, DateFormat formatter) {
		TickUnit unit = null;
		if (type == null) {
			unit = new NumberTickUnit((max - min) / mainspace);
		} else {
			int size = (int) ((max - min) / mainspace);
			unit = new DateTickUnit(type, size, formatter);
		}
		return unit;
	}

	/**
	 * 根据pace设置时间标签间隔.
	 * 
	 * @param max
	 * @param min
	 * @param mainspace
	 * @param type
	 * @param formatter
	 * @return
	 */
	public TickUnit getTimeTickUnit(int pace, DateFormat formatter) {
		TickUnit unit = new DateTickUnit(DateTickUnitType.HOUR, 2, formatter);
		return unit;
	}

	/**
	 * 解析曲线Y轴属性
	 * 
	 * @param yAxisElement
	 */
	private void parseYAxis(Element yAxisElement) {
		NumberAxis rangeAxis = new NumberAxis();
		String value = null;

		// 左坐标是否自适应
		int auto_scale = GCurveConvertFromStr.BOOLEAN_FALSE;
		value = yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_AUTO_SCALE);
		if (value != null && value.length() != 0) {
			auto_scale = Integer.parseInt(value);
		}
		boolean isAutoScale = false;
		if (auto_scale == GCurveConvertFromStr.BOOLEAN_TRUE) {
			isAutoScale = true;
		}

		// 标尺是否显示
		int axis_y_show = GCurveConvertFromStr.BOOLEAN_TRUE;
		value = null;
		value = yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_SHOW);
		if (value != null && value.length() != 0) {
			axis_y_show = Integer.parseInt(value);
		}
		boolean axisYShow = false;
		if (axis_y_show == GCurveConvertFromStr.BOOLEAN_TRUE) {
			axisYShow = true;
		}

		int mainSpace = Integer.parseInt(yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_MAIN_SPACE));
		if (mainSpace == 0) {
			mainSpace = 1;
		}
		// 次间距
		int subSpace = Integer.parseInt(yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_SUB_SPACE));
		if (subSpace == 0) {
			subSpace = 1;
		}
		// 最大值
		float max = Float.parseFloat(yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_NUMBER_MAX));
		// 最小值
		float min = Float.parseFloat(yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_NUMBER_MIN));
		// 小数有效位数
		int decimal = Integer.parseInt(yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_NUMBER_DECIMAL));

		// 最大值上浮比例

		// 主间距
		float maxRate = 0.0f;
		String axis_y_max_rate = yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_MAX_RATE);
		if (axis_y_max_rate != null && axis_y_max_rate.length() != 0) {
			maxRate = Float.parseFloat(axis_y_max_rate);
		}
		// 最小值下浮比例
		float minRate = 0.0f;
		String axis_y_min_rate = yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_MIN_RATE);
		if (axis_y_min_rate != null && axis_y_min_rate.length() != 0) {
			minRate = Float.parseFloat(axis_y_min_rate);
		}

		// 字体名称
		String fontFamily = yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_FONT_FAMILY);
		// 字体类型
		int fontWeight = Integer.parseInt(yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_FONT_WEIGHT));
		// 字体大小
		int fontSize = Integer.parseInt(yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_FONT_SIZE));
		// 字体颜色
		int fc = Integer.parseInt(yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_FONT_COLOR));
		Color fontColor = new Color(fc);

		// 曲线数目
		value = null;
		int cnum = 1;
		value = yAxisElement
				.getAttributeValue(GCurveConvertFromStr.AXIS_Y_CNUM);
		if (value != null && value.length() != 0) {
			cnum = Integer.parseInt(value);
		}

		// 纵坐标多坐标定义
		value = null;
		int multiAxisType = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
		value = yAxisElement
				.getAttributeValue(GCurveConvertFromStr.MULTI_AXIS_DEF);
		if (value != null && value.length() != 0) {
			multiAxisType = Integer.parseInt(value);
		}
		// if (multiAxisType == GCurveConvertFromStr.MULTI_AXIS_SINGLE) {
		// multiAxisType = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
		// }
		// multipleYType = multiAxisType;

		int x = 0;
		int y = this.drawAreaPosY + this.drawAreaHeight;
		if (multiAxisType == GCurveConvertFromStr.MULTI_AXIS_SINGLE
				|| multiAxisType == GCurveConvertFromStr.MULTI_AXIS_LEFT_1) {
			x = this.drawAreaPosX - 4;
		} else if (multiAxisType == GCurveConvertFromStr.MULTI_AXIS_LEFT_2) {
			x = this.drawAreaPosX - 24;
		} else if (multiAxisType == GCurveConvertFromStr.MULTI_AXIS_RIGHT_1) {
			x = this.drawAreaPosX + this.drawAreaWidth + 15;
		} else if (multiAxisType == GCurveConvertFromStr.MULTI_AXIS_RIGHT_2) {
			x = this.drawAreaPosX + this.drawAreaWidth + 35;
		}

		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(decimal);
		decimalFormat.setMinimumFractionDigits(decimal);
		rangeAxis.setNumberFormatOverride(decimalFormat);
		rangeAxis.setVisible(axis_y_show == 1 ? true : false);
		rangeAxis.setMinorTickCount(subSpace);
		rangeAxis.setMinorTickMarksVisible(true);
		rangeAxis.setTickMarksVisible(true);
		rangeAxis.setRange(min, max);
		rangeAxis.setAutoRange(isAutoScale);
		rangeAxis.setAxisLineVisible(true);
		rangeAxis.setTickLabelsVisible(true);

		rangeAxis.setTickLabelFont(new Font(fontFamily, fontWeight, fontSize));
		rangeAxis.setTickLabelPaint(new Color(fc));
		rangeAxis.setAxisLinePaint(new Color(fc));
		rangeAxis.setLabel("");

		XYDataset dataset = null;
		int index = 0;
//		XYItemRenderer renderer = new CustomXYLineShapeRenderer();
		XYItemRenderer renderer = new CustomXYStepShapeRenderer();
//		XYItemRenderer renderer = new XYStepRenderer();
		((CustomXYStepShapeRenderer) renderer).setBaseShapesVisible(false);
		((CustomXYStepShapeRenderer) renderer).setLegendLine(shape);
		((CustomXYStepShapeRenderer) renderer).setBaseShapesFilled(true);

		if (plot.getDomainAxis() instanceof NumberAxis) {
			dataset = new XYSeriesCollection();
		} else {
			dataset = new TimeSeriesCollection();
		}
		if (multiAxisType == 0) {
			plot.setRangeAxis(rangeAxis);
			plot.setDataset(dataset);
			plot.setRenderer(renderer);
		} else {
			plot.setRangeAxis(multiAxisType - 1, rangeAxis);
			plot.setDataset(index, dataset);
			plot.mapDatasetToRangeAxis(index, multiAxisType - 1);
			plot.setRenderer(index, renderer);
			if (multiAxisType % 2 == 0) {
				plot.setRangeAxisLocation(multiAxisType - 1,
						AxisLocation.BOTTOM_OR_RIGHT);
			} else {
				plot.setRangeAxisLocation(multiAxisType - 1,
						AxisLocation.BOTTOM_OR_LEFT);
			}
		}

	}

	/**
	 * 解析曲线实体属性
	 * 
	 * @param entityElement
	 */
	private void parseEntity(Element entityElement) {
		String value = null;
		// 曲线名称
		value = entityElement
				.getAttributeValue(GCurveConvertFromStr.ENTITY_TITLE);
		String title = "";
		if (value != null && value.length() != 0) {
			title = value;
		}
		// 自动设置曲线标题
		value = entityElement
				.getAttributeValue(GCurveConvertFromStr.ENTITY_AUTO);
		boolean autoTitle = false;
		if (value != null && value.length() != 0) {
			if (value.toString().equals("1")) {
				autoTitle = true;
			}
		}

		// 所属纵坐标
		value = null;
		int y_type = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
		value = entityElement
				.getAttributeValue(GCurveConvertFromStr.ENTITY_AXIS_Y_TYPE);
		if (value != null && value.length() != 0) {
			y_type = Integer.parseInt(value);
		}
		// if (y_type == GCurveConvertFromStr.MULTI_AXIS_SINGLE) {
		// y_type = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
		// }

		// 是否显示图例
		value = null;
		int is_legend_show = GCurveConvertFromStr.BOOLEAN_TRUE;
		value = entityElement
				.getAttributeValue(GCurveConvertFromStr.ENTITY_LEGEND_SHOW);
		if (value != null && value.length() != 0) {
			is_legend_show = Integer.parseInt(value);
		}
		boolean isLegendShow = true;
		if (is_legend_show == GCurveConvertFromStr.BOOLEAN_FALSE) {
			isLegendShow = false;
		}

		// 曲线颜色
		value = null;
		int lc = -65536;
		value = entityElement
				.getAttributeValue(GCurveConvertFromStr.ENTITY_LINE_COLOR);
		if (value != null && value.length() != 0) {
			lc = Integer.parseInt(value);
		}
		Color lineColor = new Color(lc);

		// 曲线线宽
		value = null;
		int lineWidth = 1;
		value = entityElement
				.getAttributeValue(GCurveConvertFromStr.ENTITY_LINE_WIDTH);
		if (value != null && value.length() != 0) {
			lineWidth = Integer.parseInt(value);
		}

		// 曲线线型
		value = null;
		int lineStyle = GCurveConvertFromStr.LINE_STYLE_SOLID;
		value = entityElement
				.getAttributeValue(GCurveConvertFromStr.ENTITY_LINE_STYLE);
		if (value != null && value.length() != 0) {
			lineStyle = Integer.parseInt(value);
		}

		// 曲线画法
		value = null;
		int ds = GCurveConvertFromStr.ENTITY_DRAW_STRATEGY_LINE;
		value = entityElement
				.getAttributeValue(GCurveConvertFromStr.ENTITY_DRAW_STRATEGY);
		if (value != null && value.length() != 0) {
			ds = Integer.parseInt(value);
		}
		// 曲线序号
		value = null;
		int index = 0;
		value = entityElement
				.getAttributeValue(GCurveConvertFromStr.ENTITY_INDEX);
		if (value != null && value.length() != 0) {
			index = Integer.parseInt(value);
		}
		// if (!y_typeIndexMap.containsKey(y_type)) {
		// y_typeIndexMap.put(y_type, 0);
		// }else {
		// int size = y_typeIndexMap.get(y_type);
		// y_typeIndexMap.put(y_type, size+1);
		// }

		// 曲线线型
		value = null;
		int y_type_index = 0;
		value = entityElement.getAttributeValue("y_type_index");
		if (value != null && value.length() != 0) {
			y_type_index = Integer.parseInt(value);
		}

		XYDataset dataset = plot.getDataset(y_type);
		Series series = null;
		XYItemRenderer renderer = plot.getRendererForDataset(dataset);
		float[] dash = getStrokeDash(ds);
		BasicStroke stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND, 0, dash, 0);
		if (plot.getDomainAxis() instanceof NumberAxis) {
			series = new CustomXYSeries(title);
			((XYSeriesCollection) dataset).addSeries((XYSeries) series);
		} else if (plot.getDomainAxis() instanceof CustomDateAxis) {
			series = new CustomTimeSeries(title);
			((TimeSeriesCollection) dataset).addSeries((TimeSeries) series);
		}
		renderer.setSeriesStroke(y_type_index, stroke);
		renderer.setSeriesPaint(y_type_index, new Color(lc));

	}

	/**
	 * 解析曲线动态属性
	 * 
	 * @param dynamicElement
	 */
	private void parseDynamic(Element dynamicElement) {
		String value = null;
		// 曲线动态类型
		value = dynamicElement
				.getAttributeValue(GCurveConvertFromStr.DYNAMIC_DATASOURCE_TYPE);
		String type = MidhsCurveStaticFinals.DAYLOAD;
		if (value != null && value.length() != 0) {
			type = value;
		}

		// 应用号
		value = null;
		value = dynamicElement
				.getAttributeValue(GCurveConvertFromStr.DYNAMIC_APP);
		int appID = 10000;
		if (value != null && value.length() != 0) {
			appID = Integer.parseInt(value);
		}

		// 设备关键字
		String keyid = dynamicElement
				.getAttributeValue(GCurveConvertFromStr.DYNAMIC_KEYID);
		long keyID = -1;
		if (keyid != null && keyid.length() != 0) {
			keyID = Long.parseLong(keyid);
		}

		// 所属纵坐标
		value = null;
		int y_type = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
		value = dynamicElement
				.getAttributeValue(GCurveConvertFromStr.ENTITY_AXIS_Y_TYPE);
		if (value != null && value.length() != 0) {
			y_type = Integer.parseInt(value);
		}
		// if (y_type == GCurveConvertFromStr.MULTI_AXIS_SINGLE) {
		// y_type = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
		// }

		// 曲线序号
		value = null;
		value = dynamicElement
				.getAttributeValue(GCurveConvertFromStr.DYNAMIC_INDEX);
		int index = 1;
		if (value != null && value.length() != 0) {
			index = Integer.parseInt(value);
		}

		// 曲线精度.
		value = null;
		value = dynamicElement
				.getAttributeValue(GCurveConvertFromStr.DYNAMIC_DECIMAL);
		int decimal = GCurveConvertFromStr.DEFAULT_DECIMAL_VALUE;
		if (value != null && value.length() != 0) {
			decimal = Integer.parseInt(value);
		}

		Date startDate = null;
		Date endDate = null;
		// XY曲线
		if (type.equals(GCurveConvertFromStr.DYNAMIC_DATASOURCE_TYPE_XY)) {
			int xPace = Integer.parseInt(dynamicElement
					.getAttributeValue(GCurveConvertFromStr.DYNAMIC_X_PACE));
			int xDirection = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_X_DIRECTION));
			int yPace = Integer.parseInt(dynamicElement
					.getAttributeValue(GCurveConvertFromStr.DYNAMIC_Y_PACE));
			int yDirection = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_Y_DIRECTION));
			int point = Integer.parseInt(dynamicElement
					.getAttributeValue(GCurveConvertFromStr.DYNAMIC_POINT));
			int pointMode = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_POINT_MODE));
			String xkeyID = dynamicElement
					.getAttributeValue(GCurveConvertFromStr.DYNAMIC_X_KEY_ID);
			long xkeyIDL = -1;
			if (xkeyID != null && xkeyID.length() != 0) {
				xkeyIDL = Long.parseLong(xkeyID);
			}
			// String contextName = dynamicElement
			// .getAttributeValue(GCurveConvertFromStr.DYNAMIC_CONTEXT_NAME);
			XYDataset dataset = plot.getDataset(y_type - 1);
			value = null;
			int y_type_index = 0;
			value = dynamicElement.getAttributeValue("y_type_index");
			if (value != null && value.length() != 0) {
				y_type_index = Integer.parseInt(value);
			}
			Series series = null;
			CurveRefresherBean bean = new NumberCurveRefresherBean(xkeyIDL,
					keyID, point,xDirection,
					xPace, yDirection, yPace, pointMode, appID, decimal);
			if (dataset instanceof XYSeriesCollection) {
				series = ((XYSeriesCollection) dataset).getSeries(y_type_index);
				((CustomXYSeries) series).setRefresherBean(bean);
				observers.add(((CustomXYSeries) series).getObserver());
			}

		} else {
			// 开始
			int startYear = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_YEAR));
			int startMonth = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_MONTH));
			int startDay = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_DAY));
			int startHour = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_HOUR));
			int startMinute = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_MINUTE));
			int startSecond = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_SECOND));
			int startWeek = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_WEEK));
			int startWeekDay = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_WEEK_DAY));
			// 结束
			int endYear = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_YEAR));
			int endMonth = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_MONTH));
			int endDay = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_DAY));
			int endHour = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_HOUR));
			int endMinute = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_MINUTE));
			int endSecond = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_SECOND));
			int endWeek = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_WEEK));
			int endWeekDay = Integer
					.parseInt(dynamicElement
							.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_WEEK_DAY));

			// 是否绝对时间
			boolean isAbsTime = false;
			String absTime = dynamicElement
					.getAttributeValue(GCurveConvertFromStr.DYNAMIC_ABSTIME);
			if (absTime != null && absTime.length() != 0) {
				int is_abs_time = Integer.parseInt(absTime);
				if (is_abs_time == 1) {
					isAbsTime = true;
				}
			}

			// 相对时间,距离当前时间间隔
			int distant = 1;
			value = dynamicElement
					.getAttributeValue(GCurveConvertFromStr.DYNAMIC_DISTANT);
			if (value != null && value.length() != 0) {
				distant = Integer.valueOf(value);
			}

			// 相对时间,时间间隔单位
			int distantUnit = 2;
			value = dynamicElement
					.getAttributeValue(GCurveConvertFromStr.DYNAMIC_DISTANT_UNIT);
			if (value != null && value.length() != 0) {
				distantUnit = Integer.valueOf(value);
			}

			// 相对时间的时间跨度
			int len = 1;
			value = dynamicElement
					.getAttributeValue(GCurveConvertFromStr.DYNAMIC_LEN);
			if (value != null && value.length() != 0) {
				len = Integer.valueOf(value);
			}

			// 相对时间的时间跨度单位
			int len_unit = 2;
			value = dynamicElement
					.getAttributeValue(GCurveConvertFromStr.DYNAMIC_LEN_UNIT);
			if (value != null && value.length() != 0) {
				len_unit = Integer.valueOf(value);
			}

			// 取值方式
			int mode = 0;
			value = dynamicElement
					.getAttributeValue(GCurveConvertFromStr.DYNAMIC_MODE);
			if (value != null && value.length() != 0) {
				mode = Integer.valueOf(value);
			}

			// 采样周期
			int period = 1;
			value = dynamicElement
					.getAttributeValue(GCurveConvertFromStr.DYNAMIC_PERIOD);
			if (value != null && value.length() != 0) {
				period = Integer.valueOf(value);
			}

			// 采样周期单位
			value = dynamicElement
					.getAttributeValue(GCurveConvertFromStr.DYNAMIC_QUERY_UNIT);
			int query_unit = GCurveConvertFromStr.AXIS_X_TIME_UNIT_SECOND;
			if (value != null && value.length() != 0) {
				query_unit = Integer.parseInt(value);
			}

			if (query_unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_MINUTE) {
				period = period * 60;
			} else if (query_unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_HOUR) {
				period = period * 3600;
			} else if (query_unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_DAY) {
				period = period * 86400;
			} else if (query_unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_WEEK) {

			} else if (query_unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_MONTH) {

			} else if (query_unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_QUARTER) {

			} else if (query_unit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_YEAR) {

			}

			query_unit = 0;

			if (true) {
				startDate = CustomUtility.convertTime(startYear, startMonth,
						startDay, startHour, startMinute, startSecond,
						startWeek, startWeekDay);
				endDate = CustomUtility.convertTime(endYear, endMonth, endDay,
						endHour, endMinute, endSecond, endWeek, endWeekDay);
			} else {
				// 数据源未指定时间
				// time = timeGenerator.convertGivenRelativeDate(new Date(),
				// startTime, endTime, distant, distantUnit);
				// try {
				// startDate = simpleDateFormat.parse(time[0]);
				// endDate = simpleDateFormat.parse(time[1]);
				// } catch (ParseException e1) {
				// newStart = new Date();
				// newFinish = new Date();
				// e1.printStackTrace();
				// }
			}
			startDate = CustomUtility.convertTime(startYear, startMonth, startDay,
					startHour, startMinute, startSecond, startWeek,
					startWeekDay);
			endDate = CustomUtility.convertTime(endYear, endMonth, endDay, endHour,
					endMinute, endSecond, endWeek, endWeekDay);
			// DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
			// startDate = getDomainDate()[0].getTime();
			// endDate = getDomainDate()[1].getTime();
			if (type.equals("30") || type.equals("31")) {
				timePeriod = new Second();
			} else if (type.equals("1")) {
				timePeriod = new Minute();
			}
			// Calendar start = Calendar.getInstance();
			// start.setTime(startDate);
			// Calendar startCalendar = Calendar.getInstance();
			// startCalendar.set(Calendar.DAY_OF_MONTH,
			// start.get(Calendar.DAY_OF_MONTH));
			//
			// Calendar end = Calendar.getInstance();
			// end.setTime(endDate);
			// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
			// endDate);
			// Calendar endCalendar = Calendar.getInstance();
			// endCalendar.setTime(endDate)
			// Calendar[] calendars = DomainTimeGenerator.getDomainDate(
			// runProperties, startCalendar, endCalendar);
			// startDate = calendars[0].getTime();
			// endDate = calendars[1].getTime();
			// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
			// endDate);
			// long endtime = convertEndTime(type, endDate.getTime());
			CurveRefresherBean bean = new TimeCurveRefresherBean(
					DomainManager.LOCAL_DOMAIN, Context.REALTIME, type,
					Long.parseLong(keyid), period, startDate.getTime(),
					endDate.getTime(), mode);
			bean.setRefreshTime(refreshPeriod);
			// 时间轴
			if (plot.getDomainAxis() instanceof CustomDateAxis) {
				DateRange range = ((CustomDateAxis) plot.getDomainAxis())
						.getInitDateRange();
				double length = range.getLength();
				((TimeCurveRefresherBean) bean).setLength(length);
			}
			XYDataset dataset = plot.getDataset(y_type - 1);
			value = null;
			int y_type_index = 0;
			value = dynamicElement.getAttributeValue("y_type_index");
			if (value != null && value.length() != 0) {
				y_type_index = Integer.parseInt(value);
			}
			Series series = null;
			if (dataset instanceof TimeSeriesCollection) {
				series = ((TimeSeriesCollection) dataset)
						.getSeries(y_type_index);
				((CustomTimeSeries) series).setTimePeriodClass(timePeriod
						.getClass());
				((CustomTimeSeries) series).setRefresherBean(bean);
				((CustomTimeSeries) series).setInitStartXValue(startDate
						.getTime());
				observers.add(((CustomTimeSeries) series).getObserver());
			} 
			//时间轴用于处理曲线跑动情况.
			((CustomDateAxis) plot.getDomainAxis()).setObservers(observers);

		}

	}

	/**
	 * 解析曲线图例
	 * 
	 * @param legendElement
	 */
	private void parseLegend(Element legendElement) {
		// LegendTitle legendTitle = null;
		// 字体名称
		String fontFamily = legendElement
				.getAttributeValue(GCurveConvertFromStr.LEGEND_FONT_FAMILY);
		if (fontFamily == null || fontFamily.length() == 0) {
			fontFamily = "SimSun";
		}
		// 字体类型
		int fontWeight = Integer.parseInt(legendElement
				.getAttributeValue(GCurveConvertFromStr.LEGEND_FONT_WEIGHT));
		// 字体大小
		int fontSize = Integer.parseInt(legendElement
				.getAttributeValue(GCurveConvertFromStr.LEGEND_FONT_SIZE));
		// 字体颜色
		String font_color = legendElement
				.getAttributeValue(GCurveConvertFromStr.LEGEND_FONT_COLOR);
		int fc = 0;
		if (font_color != null && font_color.length() != 0) {
			fc = Integer.parseInt(font_color);
		}
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font(fontFamily, fontWeight, fontSize));
		// legendTitle.setItemPaint(new Color(fontColor));
		legendTitle.setItemPaint(Color.BLACK);
		// if (legendPosition == 0) {
		// legendTitle.setPosition(RectangleEdge.BOTTOM);
		// } else if (legendPosition == 1) {
		// legendTitle.setPosition(RectangleEdge.LEFT);
		// } else if (legendPosition == 2) {
		// legendTitle.setPosition(RectangleEdge.RIGHT);
		// } else if (legendPosition == 3) {
		// legendTitle.setPosition(RectangleEdge.TOP);
		// }
		// legendTitle.setVisible(isShow);

	}

	/**
	 * 解析曲线翻页域
	 * 
	 * @param turnOverFieldElement
	 */
	private void parseTurnOverField(Element turnOverFieldElement) {
		String value = null;
		// 左上角X坐标
		int x = 0;
		value = turnOverFieldElement
				.getAttributeValue(GCurveConvertFromStr.TURNOVER_FIELD_X);
		if (value != null && value.length() != 0) {
			x = Integer.parseInt(value);
		}
		// 左上角Y坐标
		value = null;
		int y = 0;
		value = turnOverFieldElement
				.getAttributeValue(GCurveConvertFromStr.TURNOVER_FIELD_Y);
		if (value != null && value.length() != 0) {
			y = Integer.parseInt(value);
		}
		// 宽度
		value = null;
		int w = 30;
		value = turnOverFieldElement
				.getAttributeValue(GCurveConvertFromStr.TURNOVER_FIELD_WIDTH);
		if (value != null && value.length() != 0) {
			w = Integer.parseInt(value);
		}
		// 高度
		value = null;
		int h = 20;
		value = turnOverFieldElement
				.getAttributeValue(GCurveConvertFromStr.TURNOVER_FIELD_HEIGHT);
		if (value != null && value.length() != 0) {
			h = Integer.parseInt(value);
		}
		// 颜色
		value = null;
		value = turnOverFieldElement
				.getAttributeValue(GCurveConvertFromStr.TURNOVER_FIELD_COLOR);
		int color = Color.red.getRGB();
		if (value != null && value.length() != 0) {
			color = Integer.parseInt(value);
		}
		Color turnOverFieldColor = new Color(color);
		// 类型
		value = null;
		int type = GCurveConvertFromStr.TURNOVER_FIELD_TYPE_PRE;
		value = turnOverFieldElement
				.getAttributeValue(GCurveConvertFromStr.TURNOVER_FIELD_TYPE);
		if (value != null && value.length() != 0) {
			type = Integer.parseInt(value);
		}
		// TurnOverField turnOverField = null;
		if (type == GCurveConvertFromStr.TURNOVER_FIELD_TYPE_PRE) {
			// turnOverField = new PreTurnOverField(new Point(x, y),
			// turnOverFieldColor);
		} else {
			// turnOverField = new NextTurnOverField(new Point(x, y),
			// turnOverFieldColor);
		}
		// chart.addFig(turnOverField);

		// List<Observer>
		ChangePageTitle changePageTitle = new ChangePageTitle("Test", observers);
		changePageTitle.setBackgroundPaint(new Color(color));
		chart.addSubtitle(changePageTitle);
	}

	public void setSwitchApp(boolean switchApp2) {
		this.switchApp = switchApp2;
	}

	public float[] getStrokeDash(int lineStyle) {
		float[] dash = null;
		if (lineStyle == GCurveConvertFromStr.LINE_STYLE_NULL
				|| lineStyle == GCurveConvertFromStr.LINE_STYLE_SOLID) {
			dash = null;
		} else if (lineStyle == GCurveConvertFromStr.LINE_STYLE_DASH) {
			dash = new float[] { 4.0f, 2.0f };
		} else if (lineStyle == GCurveConvertFromStr.LINE_STYLE_DOT) {
			dash = new float[] { 1.0f, 2.0f };
		} else if (lineStyle == GCurveConvertFromStr.LINE_STYLE_DASH_DOT) {
			dash = new float[] { 4.0f, 2.0f, 1.0f, 2.0f };
		} else if (lineStyle == GCurveConvertFromStr.LINE_STYLE_DASH_DOT_DOT) {
			dash = new float[] { 4.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f };
		}
		return dash;
	}

	public static void main(String args[]) {
	    Debug.DEBUG_SEND_MIDHS_CURVE = true;
		long time = System.currentTimeMillis();
		byte[] byteFile = null;
//		try {
//			FileInfo fileInfo = new FileInfo(
//					"SG.国调－调度运行－系统目录_52001445.sys.curve.g");
////			FileInfo fileInfo = new FileInfo(
////					"numbercurve_52000000.sys.curve.g");
//
//			byteFile = DataClient.getByteFile(fileInfo.getAbsolutePath());
//		} catch (DataFaultException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (ServiceConnectionException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (LocatorException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (ProxyFaultException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		File file = new File(
				"C:/Users/HMI-Lenovo/Desktop/SG.国调－调度运行－系统目录_52001445.sys.curve.g");
		try {
			FileInputStream fis = new FileInputStream(file);
			byteFile = new byte[fis.available()];
			fis.read(byteFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		File file = new File(
//				"C:/Users/LRY/Desktop/SG.国调－调度运行－系统目录_52001445.sys.curve.g");
//		try {
//			FileInputStream fis = new FileInputStream(file);
//			byteFile = new byte[fis.available()];
//			fis.read(byteFile);
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		try {
			System.out.println("get byte time"
					+ (System.currentTimeMillis() - time));
			time = System.currentTimeMillis();
			document = builder.build(new ByteArrayInputStream(byteFile));
			final GCurveParse curveParse = new GCurveParse();
			curveParse.setDocument(document);
			curveParse.parse();
			System.out.println("parse time"
					+ (System.currentTimeMillis() - time));
			// time = System.currentTimeMillis();
			// ChartPanel chartPanel = curveParse.getChartPanel(null);
			// JFrame frame = new JFrame();
			// frame.getContentPane().add(chartPanel);
			// frame.setSize(new Dimension(800,600));
			// frame.setVisible(true);
			// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			CustomChartPanel panel = new CustomChartPanel(curveParse.chart,
					new CurveViewPanel());
//			System.out.println("<><>" + curveParse.chart.getPlot() + "    "
//					+ ((TimeSeriesCollection)curveParse.chart.getXYPlot().getDataset())+ "   " + curveParse.plot);
			JFrame frame2 = new JFrame();
			frame2.getContentPane().add(panel);
			frame2.setSize(new Dimension(800, 600));
			frame2.setVisible(true);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.out.println("show time"
					+ (System.currentTimeMillis() - time));

			//
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
