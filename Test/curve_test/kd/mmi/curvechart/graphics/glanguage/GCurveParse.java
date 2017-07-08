package kd.mmi.curvechart.graphics.glanguage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Stroke;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import kd.mmi.curvechart.beans.AxisInfo;
import kd.mmi.curvechart.beans.CurveModelBean;
import kd.mmi.curvechart.beans.DrawInformation;
import kd.mmi.curvechart.beans.HisCurveModelBean;
import kd.mmi.curvechart.beans.RTDBCurveModelBean;
import kd.mmi.curvechart.beans.TimeAxisInfo;
import kd.mmi.curvechart.engine.FigCurveEngine;
import kd.mmi.curvechart.figs.Axis;
import kd.mmi.curvechart.figs.FigCurve;
import kd.mmi.curvechart.figs.Legend;
import kd.mmi.curvechart.figs.NumberAxis;
import kd.mmi.curvechart.figs.Series;
import kd.mmi.curvechart.figs.TimeAxis;
import kd.mmi.curvechart.tools.FontTool;
import kd.mmi.curvechart.tools.StrokeTool;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.kedong.hmi.service.file.tool.ParserTool;

/**
 * 曲线模板G文件解析器
 * 
 * @author mengxin
 * @since 2010/02/23
 */
public class GCurveParse {

	// private static GCurveParse instance = null;

	private Document documentToParse = null;

	private FigCurve chart = null;

	private int chartX = 0;
	private int chartY = 0;
	private int chartWidth = 1024;
	private int chartHeight = 640;

	private Color drawAreaColor = Color.white;

	private int fillPatternValue = GCurveConvertFromStr.CHART_BACKGROUND_FILL_SINGLE;// 默认单色
	// 填充

	private DrawInformation info = null;

	// private FigDrawArea drawArea = null;
	private int drawAreaPosX = 90;
	private int drawAreaPosY = 60;
	private int drawAreaWidth = 896;
	private int drawAreaHeight = 512;

	private HashMap<Integer, Series> seriesMap = new HashMap<Integer, Series>();
	//
	// private FigGrid figGrid = null;
	//
	// private FigAxisX axisX = null;
	// private Hashtable<Integer, FigAxisY> axisYTable = new Hashtable<Integer,
	// FigAxisY>();
	//
	// private FigLegend figLegend = null;
	private int legendPosX = drawAreaPosX;
	private int legendPosY = 30;

	// boolean switchApp = false;// modify by liurenyong.曲线是否根据应用自动切换
	// private Hashtable<String, Series> curveTable = new Hashtable<String,
	// Series>();

	public GCurveParse() {

	}

	// public static GCurveParse getInstance() {
	// if (instance == null) {
	// instance = new GCurveParse();
	// }
	// return instance;
	// }

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
			Element nextElement = ParserTool.getElement(gElement, i);
			if (nextElement.getName().equals(GCurveConvertFromStr.CURVE)) {
				parseCurve(nextElement);
			} else if (nextElement.getName().equals(GCurveConvertFromStr.GRID)) {
				parseGrid(nextElement);
			} else if (nextElement.getName().equals(GCurveConvertFromStr.X_AXIS)) {
				parseXAxis(nextElement);
			} else if (nextElement.getName().equals(GCurveConvertFromStr.Y_AXIS)) {
				parseYAxis(nextElement);
			} else if (nextElement.getName().equals(GCurveConvertFromStr.ENTITY)) {
				parseEntity(nextElement);
			} else if (nextElement.getName().equals(GCurveConvertFromStr.DYNAMIC)) {
				parseDynamic(nextElement);
			} else if (nextElement.getName().equals(GCurveConvertFromStr.LEGEND)) {
				parseLegend(nextElement);
			} else if (nextElement.getName().equals(GCurveConvertFromStr.TEXT_FIELD)) {
//				parseTextField(nextElement);
			} else if (nextElement.getName().equals(GCurveConvertFromStr.DATA_FIELD)) {
//				parseDataField(nextElement);
			} else if (nextElement.getName().equals(GCurveConvertFromStr.TURNOVER_FIELD)) {
//				parseTurnOverField(nextElement);
			} else if (nextElement.getName().equals(GCurveConvertFromStr.FOCUS_FIELD)) {
//				parseFocusField(nextElement);
			}
		}
	}
	
	public FigCurve getChart() {
		return chart;
	}

	/**
	 * 解析曲线全局属性
	 * 
	 * @param curveElement
	 */
	private void parseCurve(Element curveElement) {
		String value = null;
		// 左上角X坐标
		value = curveElement.getAttributeValue(GCurveConvertFromStr.CHART_X);
		if (value != null && value.length() != 0) {
			chartX = Integer.parseInt(value);
		}

		// 左上角Y坐标
		value = null;
		value = curveElement.getAttributeValue(GCurveConvertFromStr.CHART_Y);
		if (value != null && value.length() != 0) {
			chartY = Integer.parseInt(value);
		}

		// 宽度
		value = null;
		value = curveElement.getAttributeValue(GCurveConvertFromStr.CHART_WIDTH);
		if (value != null && value.length() != 0) {
			chartWidth = Integer.parseInt(value);
		}

		// 高度
		value = null;
		value = curveElement.getAttributeValue(GCurveConvertFromStr.CHART_HEIGHT);
		if (value != null && value.length() != 0) {
			chartHeight = Integer.parseInt(value);
		}

		// 背景填充模式
		value = null;
		value = curveElement.getAttributeValue(GCurveConvertFromStr.CHART_BACKGROUND_FILLPATTERN);
		if (value != null && value.length() != 0) {
			fillPatternValue = Integer.parseInt(value);
		}

		// 背景图片路径
		String picUrl = curveElement.getAttributeValue(GCurveConvertFromStr.CHART_BACKGROUND_PIC);

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
		String title_content = curveElement.getAttributeValue(GCurveConvertFromStr.TITLE_CONTENT);

		// 刷新周期
		int refreshPeriod = GCurveConvertFromStr.REFRESH_DEFAULT_PERIOD;// 默认60秒
		value = null;
		value = curveElement.getAttributeValue(GCurveConvertFromStr.REFRESH_INTERVAL);
		if (value != null && value.length() != 0) {
			refreshPeriod = Integer.parseInt(value);
		}

		// 多轴类型
		int multiAxisType = GCurveConvertFromStr.MULTI_AXIS_TYPE_SINGLE;// 默认单轴
		value = null;
		value = curveElement.getAttributeValue(GCurveConvertFromStr.MULTI_AXIS_TYPE);
		if (value != null && value.length() != 0) {
			multiAxisType = Integer.parseInt(value);
		}

		// 背景颜色
		int bgc = GCurveConvertFromStr.COLOR_WITHE; // 默认-1
		value = null;
		value = curveElement.getAttributeValue(GCurveConvertFromStr.CHART_BACKGROUND_COLOR);
		if (value != null && value.length() != 0) {
			bgc = Integer.parseInt(value);
		}
		Color backgroundColor = new Color(bgc);

		// 网格背景颜色
		int cgc = GCurveConvertFromStr.COLOR_WITHE;// 默认-1
		value = null;
		value = curveElement.getAttributeValue(GCurveConvertFromStr.GRID_BACKGROUND_COLOR);
		if (value != null && value.length() != 0) {
			cgc = Integer.parseInt(value);
		}
		drawAreaColor = new Color(cgc);

		// 创建曲线Fig对象
		chart = new FigCurve();
		info = chart.getInformation();

		info.setX(chartX);
		info.setY(chartY);
		info.setW(chartWidth);
		info.setH(chartHeight);
		info.setBgc(backgroundColor.getRGB());

		// chart = new FigCurve(chartX, chartY, chartWidth, chartHeight,
		// backgroundColor);

		// 设置填充模式
		// FillPattern fillPattern = null;
		// if (fillPatternValue ==
		// GCurveConvertFromStr.CHART_BACKGROUND_FILL_SINGLE) {
		// fillPattern = new FillSingle(backgroundColor);
		// } else if (fillPatternValue ==
		// GCurveConvertFromStr.CHART_BACKGROUND_FILL_TRANSPARENT) {
		// fillPattern = new FillTransparent(new BasicStroke(0), Color.black);
		// } else {
		// fillPattern = new FillTransparent(new BasicStroke(0), Color.black);
		// }
		// chart.setFillPattern(fillPattern);

		// 设置刷新周期
		// chart.setRefreshPeriod(refreshPeriod);
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
		value = gridElement.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_WIDTH);
		if (value != null && value.length() != 0) {
			this.drawAreaWidth = Integer.parseInt(value) - 30;
		}

		// 高度
		value = null;
		value = gridElement.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_HEIGHT);
		if (value != null && value.length() != 0) {
			this.drawAreaHeight = Integer.parseInt(value) - 10;
		}

		// 主网格颜色
		int main_grid_c = GCurveConvertFromStr.COLOR_BLACK;// 默认黑色
		value = null;
		value = gridElement.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_COLOR);
		if (value != null && value.length() != 0) {
			main_grid_c = Integer.parseInt(value);
		}
		Color mainGridColor = new Color(main_grid_c);

		// 主网格线宽
		int mainGridLineWidth = GCurveConvertFromStr.LINE_DEFAULT_WIDTH;// 默认1
		value = null;
		value = gridElement.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_LINE_WIDTH);
		if (value != null && value.length() != 0) {
			mainGridLineWidth = Integer.parseInt(value);
		}

		// 主网格线型
		int mainGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DASH; // 默认虚线
		value = null;
		value = gridElement.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_LINE_STYLE);
		if (value != null && value.length() != 0) {
			mainGridLineStyle = Integer.parseInt(value);
		}

		// 是否显示X方向主网格
		int main_grid_x_show = GCurveConvertFromStr.BOOLEAN_TRUE;
		value = null;
		value = gridElement.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_X_SHOW);
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
		value = gridElement.getAttributeValue(GCurveConvertFromStr.MAIN_GRID_Y_SHOW);
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
		value = gridElement.getAttributeValue(GCurveConvertFromStr.SUB_GRID_COLOR);
		if (value != null && value.length() != 0) {
			sub_grid_c = Integer.parseInt(value);
		}
		Color subGridColor = new Color(sub_grid_c);

		// 副网格线宽
		int subGridLineWidth = GCurveConvertFromStr.LINE_DEFAULT_WIDTH;
		value = null;
		value = gridElement.getAttributeValue(GCurveConvertFromStr.SUB_GRID_LINE_WIDTH);
		if (value != null && value.length() != 0) {
			subGridLineWidth = Integer.parseInt(value);
		}

		// 副网格线型
		int subGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DASH;
		value = null;
		value = gridElement.getAttributeValue(GCurveConvertFromStr.SUB_GRID_LINE_STYLE);
		if (value != null && value.length() != 0) {
			subGridLineStyle = Integer.parseInt(value);
		}

		// 是否显示X方向副网格
		int sub_grid_x_show = GCurveConvertFromStr.BOOLEAN_FALSE;
		value = null;
		value = gridElement.getAttributeValue(GCurveConvertFromStr.SUB_GRID_X_SHOW);
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
		value = gridElement.getAttributeValue(GCurveConvertFromStr.SUB_GRID_Y_SHOW);
		if (value != null && value.length() != 0) {
			sub_grid_y_show = Integer.parseInt(value);
		}
		boolean isSubGridYShow = false;
		if (sub_grid_y_show == GCurveConvertFromStr.BOOLEAN_TRUE) {
			isSubGridYShow = true;
		}

		info.setMainGridColor(mainGridColor.getRGB());
		info.setMainGridLineStyle(mainGridLineStyle);
		info.setMainGridLineWidth(mainGridLineWidth);
		info.setSubGridColor(subGridColor.getRGB());
		info.setSubGridLineStyle(subGridLineStyle);
		info.setSubGridLineWidth(subGridLineWidth);
		info.setMainGridXShow(isMainGridXShow);
		info.setMainGridYShow(isMainGridYShow);
		info.setSubGridXShow(isSubGridXShow);
		info.setSubGridYShow(isSubGridYShow);
		info.setSubGridYShow(true);
		info.setDraw_Area_x(drawAreaPosX);
		info.setDraw_Area_y(drawAreaPosY);
		info.setDraw_Area_w(drawAreaWidth);
		info.setDraw_Area_h(drawAreaHeight);
		chart.setInformation(info);

		// 设置网格背景填充模式
//		if (fillPatternValue == GCurveConvertFromStr.CHART_BACKGROUND_FILL_SINGLE) {
//			drawArea.setFillPattern(new FillSingle(this.drawAreaColor));
//		} else if (fillPatternValue == GCurveConvertFromStr.CHART_BACKGROUND_FILL_TRANSPARENT) {
//			drawArea.setFillPattern(new FillTransparent(new BasicStroke(0), Color.black));
//		} else {
//			drawArea.setFillPattern(new FillTransparent(new BasicStroke(0), Color.black));
//		}

		// drawArea.setBounds(new
		// Rectangle(this.drawAreaPosX-5,this.drawAreaPosY-5,this.drawAreaWidth+20,this.drawAreaHeight+11));
	}

	/**
	 * 解析曲线X轴属性
	 * 
	 * @param xAxisElement
	 */
	private void parseXAxis(Element xAxisElement) {
		String value = null;
		// 坐标轴类型
		int scaleType = GCurveConvertFromStr.AXIS_X_SCALE_TYPE_TIME;
		value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_SCALE_TYPE);
		if (value != null && value.length() != 0) {
			scaleType = Integer.parseInt(value);
		}

		// 标尺是否显示
		int show = GCurveConvertFromStr.BOOLEAN_TRUE;
		value = null;
		value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_SHOW);
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
		value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_MAIN_SPACE);
		if (value != null && value.length() != 0) {
			mainSpace = Integer.parseInt(value);
		}
		if (mainSpace == 0) {
			mainSpace = GCurveConvertFromStr.MAIN_SPACE_DEFAULT;
		}

		// 次间距
		int subSpace = GCurveConvertFromStr.SUB_SPACE_DEFAULT;
		value = null;
		value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_SUB_SPACE);
		if (value != null && value.length() != 0) {
			subSpace = Integer.parseInt(value);
		}
		if (subSpace == 0) {
			subSpace = GCurveConvertFromStr.SUB_SPACE_DEFAULT;
		}

		// 步长单位
		int runUnit = GCurveConvertFromStr.RUN_UNIT;
		value = null;
		value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_RUN_UNIT);
		if (value != null && value.length() != 0) {
			runUnit = Integer.parseInt(value);
		}

		// 字体名称
		String fontFamily = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_FONT_FAMILY);
		if (fontFamily == null || fontFamily.length() == 0) {
			fontFamily = GCurveConvertFromStr.DEFAULT_FONT_FAMILY;
		}

		// 字体类型
		int fontWeight = GCurveConvertFromStr.DEFAULT_FONT_WEIGHT;
		value = null;
		value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_FONT_WEIGHT);
		if (value != null && value.length() != 0) {
			fontWeight = Integer.parseInt(value);
		}

		// 字体大小
		int fontSize = GCurveConvertFromStr.DEFAULT_FONT_SIZE;
		value = null;
		value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_FONT_SIZE);
		if (value != null && value.length() != 0) {
			fontSize = Integer.parseInt(value);
		}

		// 字体颜色
		int fc = GCurveConvertFromStr.DEFAULT_FONT_COLOR;
		value = null;
		value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_FONT_COLOR);
		if (value != null && value.length() != 0) {
			fc = Integer.parseInt(value);
		}
		Color fontColor = new Color(fc);

		Axis domainAxis = null;
		AxisInfo axisInfo = null;
		// 时间轴
		if (scaleType == GCurveConvertFromStr.AXIS_X_SCALE_TYPE_TIME) {
			// 标尺格式
			int timeFormat = 4;
			value = null;
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_FORMAT);
			if (value != null && value.length() != 0) {
				timeFormat = Integer.parseInt(value);
			}

			// 开始时刻
			int start = GCurveConvertFromStr.DEFAULT_TIME_START;
			value = null;
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_START);
			if (value != null && value.length() != 0) {
				start = Integer.parseInt(value);
			}

			// 结束时刻
			int end = GCurveConvertFromStr.DEFAULT_TIME_END;
			value = null;
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_END);
			if (value != null && value.length() != 0) {
				end = Integer.parseInt(value);
			}

			// 时间跨度
			int span = GCurveConvertFromStr.DEFAULT_TIME_SPAN;
			value = null;
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_SPAN);
			if (value != null && value.length() != 0) {
				span = Integer.parseInt(value);
			}

			// 是否绝对时间
			int abs_time = GCurveConvertFromStr.BOOLEAN_TRUE;
			value = null;
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_IS_ABS);
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
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_IS_INT);
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
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_INT_TIME_VALUE);
			if (value != null && value.length() != 0) {
				intTimeValue = Integer.parseInt(value);
			}

			// 是否跑动
			int run = GCurveConvertFromStr.BOOLEAN_FALSE;
			value = null;
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_RUN);
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
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_RUN_PACE);
			if (value != null && value.length() != 0) {
				runPace = Integer.parseInt(value);
			}

			// 时间间隔单位
			int unit = GCurveConvertFromStr.AXIS_X_TIME_UNIT_HOUR;
			value = null;
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_TIME_UNIT);
			if (value != null && value.length() != 0) {
				unit = Integer.parseInt(value);
			}

			axisInfo = new TimeAxisInfo();
			axisInfo.setBegin(start);
			axisInfo.setEnd(end);
			((TimeAxisInfo) axisInfo).setFormatType(timeFormat);
			((TimeAxisInfo) axisInfo).setIntTime(isInt);
			((TimeAxisInfo) axisInfo).setIntTimeValue(intTimeValue);
			((TimeAxisInfo) axisInfo).setUnit(unit);
			((TimeAxisInfo) axisInfo).setRun(isRun);
			((TimeAxisInfo) axisInfo).setRunPace(runPace);
			((TimeAxisInfo) axisInfo).setRunUnit(runUnit);
			domainAxis = new TimeAxis();
			domainAxis.setMainTickCount(mainSpace);
			domainAxis.setSubTickCount(subSpace);
			domainAxis.setAutoRange(false);
			domainAxis.setAxisInfo(axisInfo);
			domainAxis.setShow(axisXShow);

		}
		// 数字轴
		else if (scaleType == GCurveConvertFromStr.AXIS_X_SCALE_TYPE_NUM) {
			// 最大值
			float max = GCurveConvertFromStr.DEFAULT_MAX_VALUE;
			value = null;
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_NUMBER_MAX);
			if (value != null && value.length() != 0) {
				max = Float.parseFloat(value);
			}

			// 最小值
			float min = GCurveConvertFromStr.DEFAULT_MIN_VALUE;
			value = null;
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_NUMBER_MIN);
			if (value != null && value.length() != 0) {
				min = Float.parseFloat(value);
			}

			// 小数有效位数
			int decimal = GCurveConvertFromStr.DEFAULT_DECIMAL_VALUE;
			value = null;
			value = xAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_X_NUMBER_DECIMAL);
			if (value != null && value.length() != 0) {
				decimal = Integer.parseInt(value);
			}

			axisInfo = new AxisInfo();
			axisInfo.setBegin((int) min);
			axisInfo.setEnd((int) max);
			axisInfo.setDecimal(decimal);

			domainAxis = new NumberAxis();
			domainAxis.setMainTickCount(mainSpace);
			domainAxis.setSubTickCount(subSpace);
			domainAxis.setAutoRange(false);
			domainAxis.setAxisInfo(axisInfo);
			domainAxis.setShow(axisXShow);

		}

		else if (scaleType == GCurveConvertFromStr.AXIS_X_SCALE_TYPE_RTDB) {

			// MiddataBatchRecordSet set = new MiddataBatchRecordSet(x_keyid,
			// y_keyid, point, x_direction, x_pace, y_direction, y_pace,
			// point_mode, conetxeName, appName);
		}
		chart.setDomainAxis(domainAxis);
	}

	/**
	 * 解析曲线Y轴属性
	 * 
	 * @param yAxisElement
	 */
	private void parseYAxis(Element yAxisElement) {
		String value = null;

		// 左坐标是否自适应
		int auto_scale = GCurveConvertFromStr.BOOLEAN_FALSE;
		value = yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_AUTO_SCALE);
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
		value = yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_SHOW);
		if (value != null && value.length() != 0) {
			axis_y_show = Integer.parseInt(value);
		}
		boolean axisYShow = false;
		if (axis_y_show == GCurveConvertFromStr.BOOLEAN_TRUE) {
			axisYShow = true;
		}

		// 主间距
		int mainSpace = Integer.parseInt(yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_MAIN_SPACE));
		if (mainSpace == 0) {
			mainSpace = 1;
		}
		// 次间距
		int subSpace = Integer.parseInt(yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_SUB_SPACE));
		if (subSpace == 0) {
			subSpace = 1;
		}
		// 最大值
		float max = Float.parseFloat(yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_NUMBER_MAX));
		// 最小值
		float min = Float.parseFloat(yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_NUMBER_MIN));
		// 小数有效位数
		int decimal = Integer.parseInt(yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_NUMBER_DECIMAL));

		// 最大值上浮比例
		float maxRate = 0.0f;
		String axis_y_max_rate = yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_MAX_RATE);
		if (axis_y_max_rate != null && axis_y_max_rate.length() != 0) {
			maxRate = Float.parseFloat(axis_y_max_rate);
		}
		// 最小值下浮比例
		float minRate = 0.0f;
		String axis_y_min_rate = yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_MIN_RATE);
		if (axis_y_min_rate != null && axis_y_min_rate.length() != 0) {
			minRate = Float.parseFloat(axis_y_min_rate);
		}
		// 刻度是否取整
		// boolean isInteger = false;
		// String axis_y_is_integer =
		// yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_IS_INTEGER);
		// if (axis_y_is_integer != null && axis_y_is_integer.length() != 0) {
		// int is_integer = Integer.parseInt(axis_y_is_integer);
		// if (is_integer == 1) {
		// isInteger = true;
		// }
		// }
		// // 自适应下默认取整
		// if (isAutoScale) {
		// isInteger = true;
		// }
		// // 是否限值内自适应
		// boolean isAutoWithLimit = false;
		// String axis_y_auto_with_limit =
		// yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_AUTO_WITH_LIMIT);
		// if (axis_y_auto_with_limit != null && axis_y_auto_with_limit.length()
		// != 0) {
		// int auto_with_limit = Integer.parseInt(axis_y_auto_with_limit);
		// if (auto_with_limit == 1) {
		// isAutoWithLimit = true;
		// }
		// }

		// 字体名称
		String fontFamily = yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_FONT_FAMILY);
		// 字体类型
		int fontWeight = Integer.parseInt(yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_FONT_WEIGHT));
		// 字体大小
		int fontSize = Integer.parseInt(yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_FONT_SIZE));
		// 字体颜色
		int fc = Integer.parseInt(yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_FONT_COLOR));
		Color fontColor = new Color(fc);

		// 曲线数目
		value = null;
		int cnum = 1;
		value = yAxisElement.getAttributeValue(GCurveConvertFromStr.AXIS_Y_CNUM);
		if (value != null && value.length() != 0) {
			cnum = Integer.parseInt(value);
		}

		// 纵坐标多坐标定义
		value = null;
		int multiAxisType = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
		value = yAxisElement.getAttributeValue(GCurveConvertFromStr.MULTI_AXIS_DEF);
		if (value != null && value.length() != 0) {
			multiAxisType = Integer.parseInt(value);
		}
		if (multiAxisType == GCurveConvertFromStr.MULTI_AXIS_SINGLE) {
			multiAxisType = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
		}

		int x = 0;
		int y = this.drawAreaPosY + this.drawAreaHeight;
		if (multiAxisType == GCurveConvertFromStr.MULTI_AXIS_SINGLE || multiAxisType == GCurveConvertFromStr.MULTI_AXIS_LEFT_1) {
			x = this.drawAreaPosX - 4;
		} else if (multiAxisType == GCurveConvertFromStr.MULTI_AXIS_LEFT_2) {
			x = this.drawAreaPosX - 24;
		} else if (multiAxisType == GCurveConvertFromStr.MULTI_AXIS_RIGHT_1) {
			x = this.drawAreaPosX + this.drawAreaWidth + 15;
		} else if (multiAxisType == GCurveConvertFromStr.MULTI_AXIS_RIGHT_2) {
			x = this.drawAreaPosX + this.drawAreaWidth + 35;
		}

		AxisInfo axisInfo = new AxisInfo();
		axisInfo.setBegin((int) min);
		axisInfo.setEnd((int) max);
		axisInfo.setDecimal(decimal);

		Axis axis = new NumberAxis();
		axis.setAutoRange(isAutoScale);
		axis.setMainTickCount(mainSpace);
		axis.setSubTickCount(subSpace);
		axis.setShow(axisYShow);
		axis.setAxisInfo(axisInfo);
		chart.setRangeAxis(axis);

		// Point p = new Point(x, y);
		// DefaultAxisModel axisModel = new DefaultAxisModel(max, min,
		// mainSpace, subSpace);
		// FigAxisY axisY = new FigAxisY(multiAxisType, p, this.drawAreaHeight,
		// FigAxisY.NORTH, axisModel, decimal);
		// axisY.setType(multiAxisType); // 设置Y轴类型
		// this.drawArea.addAxisY(multiAxisType, axisY);
		// // if (multiAxisType == GCurveConvertFromStr.MULTI_AXIS_SINGLE
		// // || multiAxisType == GCurveConvertFromStr.MULTI_AXIS_LEFT_1) {
		// // this.drawArea.setAxisY(axisY);
		// // }
		// axisY.setMediator(this.drawArea);
		//
		// // ((NumberGenerator) axisY.getScaleLabelGenerator()).setMax(max);
		// // ((NumberGenerator) axisY.getScaleLabelGenerator()).setMin(min);
		// // ((NumberGenerator)
		// // axisY.getScaleLabelGenerator()).setDecimal(decimal);
		// ((NumberGenerator)
		// axisY.getScaleLabelGenerator()).setMainSpace(mainSpace);
		// // axisY.getModel().setMainSpace(mainSpace);
		// // axisY.getModel().setSubSpace(subSpace);
		// axisY.setAutoScale(isAutoScale);
		// // axisY.setAutoScaleWithExtend(isAutoWithLimit);
		// // axisY.getModel().setIsChangeInt(isInteger);
		// axisY.getModel().setMaxRate(maxRate);
		// axisY.getModel().setMinRate(minRate);
		// // if (isAutoScale == false) {
		// // axisY.getModel().setMaxValue(max);
		// // axisY.getModel().setMinValue(min);
		// // }
		// axisY.setupScales();
		// axisY.setColor(fontColor);
		// axisY.setFont(new Font(fontFamily, fontWeight, fontSize));
		// axisY.setFontColor(fontColor);
		//
		// axisY.calcYScale(this.drawArea.getH());
		// axisY.setVisible(axisYShow);
		//
		// this.axisYTable.put(multiAxisType, axisY);
	}

	/**
	 * 解析曲线实体属性
	 * 
	 * @param entityElement
	 */
	private void parseEntity(Element entityElement) {
		String value = null;
		// 曲线名称
		value = entityElement.getAttributeValue(GCurveConvertFromStr.ENTITY_TITLE);
		String title = "";
		if (value != null && value.length() != 0) {
			title = value;
		}

		// 自动设置曲线标题
		value = entityElement.getAttributeValue(GCurveConvertFromStr.ENTITY_AUTO);
		boolean autoTitle = false;
		if (value != null && value.length() != 0) {
			if (value.toString().equals("1")) {
				autoTitle = true;
			}
		}

		// 所属纵坐标
		value = null;
		int y_type = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
		value = entityElement.getAttributeValue(GCurveConvertFromStr.ENTITY_AXIS_Y_TYPE);
		if (value != null && value.length() != 0) {
			y_type = Integer.parseInt(value);
		}
		if (y_type == GCurveConvertFromStr.MULTI_AXIS_SINGLE) {
			y_type = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
		}

		// 是否显示图例
		value = null;
		int is_legend_show = GCurveConvertFromStr.BOOLEAN_TRUE;
		value = entityElement.getAttributeValue(GCurveConvertFromStr.ENTITY_LEGEND_SHOW);
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
		value = entityElement.getAttributeValue(GCurveConvertFromStr.ENTITY_LINE_COLOR);
		if (value != null && value.length() != 0) {
			lc = Integer.parseInt(value);
		}
		Color lineColor = new Color(lc);

		// 曲线线宽
		value = null;
		int lineWidth = 1;
		value = entityElement.getAttributeValue(GCurveConvertFromStr.ENTITY_LINE_WIDTH);
		if (value != null && value.length() != 0) {
			lineWidth = Integer.parseInt(value);
		}

		// 曲线线型
		value = null;
		int lineStyle = GCurveConvertFromStr.LINE_STYLE_SOLID;
		value = entityElement.getAttributeValue(GCurveConvertFromStr.ENTITY_LINE_STYLE);
		if (value != null && value.length() != 0) {
			lineStyle = Integer.parseInt(value);
		}

		// 曲线画法
		value = null;
		int ds = GCurveConvertFromStr.ENTITY_DRAW_STRATEGY_LINE;
		value = entityElement.getAttributeValue(GCurveConvertFromStr.ENTITY_DRAW_STRATEGY);
		if (value != null && value.length() != 0) {
			ds = Integer.parseInt(value);
		}

		// 曲线序号
		value = null;
		int index = 0;
		value = entityElement.getAttributeValue(GCurveConvertFromStr.ENTITY_INDEX);
		if (value != null && value.length() != 0) {
			index = Integer.parseInt(value);
		}

		Stroke stroke = StrokeTool.createStroke(lineWidth, lineStyle);
		Series series = new Series(title, lineColor);
		series.setStroke(stroke);
		series.setLegendVisible(isLegendShow);
		series.setIndex(index);
		chart.addSeries(series);

		seriesMap.put(index, series);

		//
		// FigAxisY axisY = this.axisYTable.get(y_type);
		//
		// Series figCurve = new Series(new
		// DefaultCurveModel(Utility.randomSampleData(11,
		// axisX.getModel().getMinValue(), axisX.getModel().getMaxValue(),
		// axisY.getModel().getMinValue(), axisY
		// .getModel().getMaxValue())), this.drawArea);
		// figCurve.setAxisYType(y_type);
		// figCurve.setTitle(title);
		// figCurve.setLegendVisible(isLegendShow);
		// figCurve.setColor(lineColor);
		// figCurve.setLineWidth(lineWidth);
		// figCurve.setAutoTitle(autoTitle);
		// this.drawArea.addNewCurve(figCurve);
		//
		// if (lineStyle == GCurveConvertFromStr.LINE_STYLE_NULL || lineStyle ==
		// GCurveConvertFromStr.LINE_STYLE_SOLID) {
		// figCurve.setDash(null);
		// } else if (lineStyle == GCurveConvertFromStr.LINE_STYLE_DASH) {
		// figCurve.setDash(new float[] { 4.0f, 2.0f });
		// } else if (lineStyle == GCurveConvertFromStr.LINE_STYLE_DOT) {
		// figCurve.setDash(new float[] { 1.0f, 2.0f });
		// } else if (lineStyle == GCurveConvertFromStr.LINE_STYLE_DASH_DOT) {
		// figCurve.setDash(new float[] { 4.0f, 2.0f, 1.0f, 2.0f });
		// } else if (lineStyle == GCurveConvertFromStr.LINE_STYLE_DASH_DOT_DOT)
		// {
		// figCurve.setDash(new float[] { 4.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f });
		// }
		//
		// if (ds == GCurveConvertFromStr.ENTITY_DRAW_STRATEGY_LINE) {
		// figCurve.setDrawStrategy(new FigCurveDrawInLine());
		// } else if (ds == GCurveConvertFromStr.ENTITY_DRAW_STRATEGY_BIAS) {
		//
		// } else if (ds == GCurveConvertFromStr.ENTITY_DRAW_STRATEGY_LADDER) {
		//
		// } else if (ds == GCurveConvertFromStr.ENTITY_DRAW_STRATEGY_DOT) {
		//
		// }
		//
		// figCurve.setAxisYType(y_type);
		// figCurve.setIndex(index);
		//
		// this.curveTable.put(y_type + "+" + index, figCurve);
	}

	/**
	 * 解析曲线动态属性
	 * 
	 * @param dynamicElement
	 */
	/**
	 * @param dynamicElement
	 */
	/**
	 * @param dynamicElement
	 */
	private void parseDynamic(Element dynamicElement) {
		String value = null;
		// 曲线动态类型
		value = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_DATASOURCE_TYPE);
		String type = "1";
		if (value != null && value.length() != 0) {
			type = value;
		}

		// 应用号
		value = null;
		value = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_APP);
		int appID = 10000;
		if (value != null && value.length() != 0) {
			appID = Integer.parseInt(value);
		}

		// 设备关键字
		String keyid = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_KEYID);
		long keyID = -1;
		if (keyid != null && keyid.length() != 0) {
			keyID = Long.parseLong(keyid);
		}

		// 所属纵坐标
		value = null;
		int y_type = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
		value = dynamicElement.getAttributeValue(GCurveConvertFromStr.ENTITY_AXIS_Y_TYPE);
		if (value != null && value.length() != 0) {
			y_type = Integer.parseInt(value);
		}
		if (y_type == GCurveConvertFromStr.MULTI_AXIS_SINGLE) {
			y_type = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
		}

		// 曲线序号
		value = null;
		value = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_INDEX);
		int index = 1;
		if (value != null && value.length() != 0) {
			index = Integer.parseInt(value);
		}

		// 曲线序号
		value = null;
		value = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_DECIMAL);
		int decimal = GCurveConvertFromStr.DEFAULT_DECIMAL_VALUE;
		if (value != null && value.length() != 0) {
			decimal = Integer.parseInt(value);
		}

		// 开始
		CurveModelBean bean = null;
		Series series = seriesMap.get(index);

		// XY曲线
		if (type.equals(GCurveConvertFromStr.DYNAMIC_DATASOURCE_TYPE_XY)) {
			int xPace = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_X_PACE));
			int xDirection = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_X_DIRECTION));
			int yPace = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_Y_PACE));
			int yDirection = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_Y_DIRECTION));
			int point = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_POINT));
			int pointMode = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_POINT_MODE));
			String xkeyID = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_X_KEY_ID);
			long xkeyIDL = -1;
			if (xkeyID != null && xkeyID.length() != 0) {
				xkeyIDL = Long.parseLong(xkeyID);
			}
			bean = new RTDBCurveModelBean(chart.getDomainAxis());
			RTDBCurveModelBean rtdbCurveModelBean = (RTDBCurveModelBean) bean;
			rtdbCurveModelBean.setPoint(point);
			rtdbCurveModelBean.setX_app(appID);
			rtdbCurveModelBean.setX_direction(xDirection);
			rtdbCurveModelBean.setX_pace(xPace);
			rtdbCurveModelBean.setXkeyID(xkeyIDL);
			rtdbCurveModelBean.setY_direction(yDirection);
			rtdbCurveModelBean.setY_pace(yPace);
			rtdbCurveModelBean.setPointMode(pointMode);
			// String contextName = dynamicElement
			// .getAttributeValue(GCurveConvertFromStr.DYNAMIC_CONTEXT_NAME);
			// rSet = new MiddataBatchRecordSet(xkeyID, keyid, point,
			// xDirection, xPace, yDirection, yPace, pointMode, appID, decimal,
			// startYear, startMonth, startDay, startHour, startMinute,
			// startSecond, startWeek,
			// startWeekDay, endYear, endMonth, endDay, endHour, endMinute,
			// endSecond, endWeek, endWeekDay);

		} else {
			int startYear = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_YEAR));
			int startMonth = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_MONTH));
			int startDay = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_DAY));
			int startHour = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_HOUR));
			int startMinute = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_MINUTE));
			int startSecond = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_SECOND));
			int startWeek = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_WEEK));
			int startWeekDay = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_START_WEEK_DAY));
			// 结束
			int endYear = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_YEAR));
			int endMonth = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_MONTH));
			int endDay = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_DAY));
			int endHour = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_HOUR));
			int endMinute = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_MINUTE));
			int endSecond = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_SECOND));
			int endWeek = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_WEEK));
			int endWeekDay = Integer.parseInt(dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_TIME_END_WEEK_DAY));

			// 是否绝对时间
			boolean isAbsTime = false;
			String absTime = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_ABSTIME);
			if (absTime != null && absTime.length() != 0) {
				int is_abs_time = Integer.parseInt(absTime);
				if (is_abs_time == 1) {
					isAbsTime = true;
				}
			}

			// 相对时间,距离当前时间间隔
			int distant = 1;
			value = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_DISTANT);
			if (value != null && value.length() != 0) {
				distant = Integer.valueOf(value);
			}

			// 相对时间,时间间隔单位
			int distantUnit = 2;
			value = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_DISTANT_UNIT);
			if (value != null && value.length() != 0) {
				distantUnit = Integer.valueOf(value);
			}

			// 相对时间的时间跨度
			int len = 1;
			value = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_LEN);
			if (value != null && value.length() != 0) {
				len = Integer.valueOf(value);
			}

			// 相对时间的时间跨度单位
			int len_unit = 2;
			value = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_LEN_UNIT);
			if (value != null && value.length() != 0) {
				len_unit = Integer.valueOf(value);
			}

			// 取值方式
			int mode = 0;
			value = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_MODE);
			if (value != null && value.length() != 0) {
				mode = Integer.valueOf(value);
			}

			// 采样周期
			int period = 1;
			value = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_PERIOD);
			if (value != null && value.length() != 0) {
				period = Integer.valueOf(value);
			}

			// 采样周期单位
			value = dynamicElement.getAttributeValue(GCurveConvertFromStr.DYNAMIC_QUERY_UNIT);
			int query_unit = GCurveConvertFromStr.AXIS_X_TIME_UNIT_SECOND;
			if (value != null && value.length() != 0) {
				query_unit = Integer.parseInt(value);
			}
			bean = new HisCurveModelBean(chart.getDomainAxis());
			HisCurveModelBean hisBean = (HisCurveModelBean) bean;

			hisBean.setStartYear(startYear);
			hisBean.setStartMonth(startMonth);
			hisBean.setStartDay(startDay);
			hisBean.setStartHour(startHour);
			hisBean.setStartMinute(startMinute);
			hisBean.setStartSecond(startSecond);
			hisBean.setEndYear(endYear);
			hisBean.setEndMonth(endMonth);
			hisBean.setEndDay(endDay);
			hisBean.setKeyid(keyID);
			hisBean.setAppID(appID);
			hisBean.setDistance(distant);
			hisBean.setDistanceUnit(distantUnit);
			hisBean.setAbstime(isAbsTime);
			hisBean.setMode(mode);
			hisBean.setPeriod(period);
			hisBean.setPeriodUnit(query_unit);

			// if (type.equals(MidhsCurveStaticFinals.RTNET_RE_DAY_MR)
			// || type.equals(MidhsCurveStaticFinals.RTNET_RE_MONTH_MR)
			// || type.equals(MidhsCurveStaticFinals.RTNET_RE_YEAR_MR)
			// || type.equals(MidhsCurveStaticFinals.CA_PERIOD_AVGTIME)
			// || type
			// .equals(MidhsCurveStaticFinals.CA_DATERATE_AVGSINGLETIME)
			// || type
			// .equals(MidhsCurveStaticFinals.CA_MONTHRATE_AVGSINGLETIME)
			// || type.equals(MidhsCurveStaticFinals.PWRCON_DAY_SR)
			// || type.equals(MidhsCurveStaticFinals.PWRCON_MONTH_SR)
			// || type.equals(MidhsCurveStaticFinals.PWRCON_PERIOD_TM)
			// || type.equals(MidhsCurveStaticFinals.RAPS_SYS_RISK_DAY)) {
			// rSet = new MidhsRecordSet(type, -1, mode, 0, 0, isAbsTime,
			// distant, distantUnit, startYear, startMonth, startDay,
			// startHour, startMinute, startSecond, startWeek,
			// startWeekDay, endYear, endMonth, endDay, endHour,
			// endMinute, endSecond, endWeek, endWeekDay,decimal,len,len_unit);
			// } else
			// if (type.equals(MidhsCurveStaticFinals.SECOND_1) ||
			// type.equals(MidhsCurveStaticFinals.SECOND_5)) {
			// // rSet = new
			// // MidhsSecondRecordSet(MidhsCurveStaticFinals.SECOND_1, keyID,
			// // GCurveConvertFromStr.DYNAMIC_MODE_BEGIN, distant,
			// // distantUnit);
			// rSet = new MidhsSecondRecordSet(type, keyID,
			// GCurveConvertFromStr.DYNAMIC_MODE_BEGIN, 0,
			// GCurveConvertFromStr.AXIS_X_TIME_UNIT_SECOND, isAbsTime, distant,
			// distantUnit, startYear,
			// startMonth, startDay, startHour, startMinute, startSecond,
			// startWeek, startWeekDay, endYear, endMonth, endDay, endHour,
			// endMinute, endSecond, endWeek, endWeekDay, decimal,
			// len, len_unit, appID);
			// } else if (type.equals(MidhsCurveStaticFinals.RTDB_SINGLE)) {
			// rSet = new MiddataSingleRecordSet(String.valueOf(keyID), appID,
			// decimal);
			// } else {
			// rSet = new MidhsRecordSet(type, keyID, mode, period, query_unit,
			// isAbsTime, distant, distantUnit, startYear, startMonth, startDay,
			// startHour, startMinute, startSecond, startWeek,
			// startWeekDay, endYear, endMonth, endDay, endHour, endMinute,
			// endSecond, endWeek, endWeekDay, decimal, len, len_unit);
			// }
		}

		// if (rSet != null) {
		// rSet.setSwitchApp(switchApp);// modify by liurenyong.曲线是否根据应用自动切换
		// Series figCurve = this.curveTable.get(y_type + "+" + index);
		// if (figCurve != null) {
		// figCurve.getModel().setRecordSet(rSet);
		// }
		// }
		bean.setType(type);
		bean.setAppID(appID);
		bean.setKeyid(keyID);
		bean.setModel(series.getModel());
		FigCurveEngine.getInstance().addCurveModelBean(bean);
		
	}

	/**
	 * 解析曲线图例
	 * 
	 * @param legendElement
	 */
	private void parseLegend(Element legendElement) {
		// 左上角X坐标
		String legend_x = legendElement.getAttributeValue(GCurveConvertFromStr.LEGEND_X);
		if (legend_x != null && legend_x.length() != 0) {
			legendPosX = Integer.parseInt(legend_x);
		}
		// 左上角Y坐标
		String legend_y = legendElement.getAttributeValue(GCurveConvertFromStr.LEGEND_Y);
		if (legend_y != null && legend_y.length() != 0) {
			legendPosY = Integer.parseInt(legend_y);
		}
		// 字体名称
		String fontFamily = legendElement.getAttributeValue(GCurveConvertFromStr.LEGEND_FONT_FAMILY);
		if (fontFamily == null || fontFamily.length() == 0) {
			fontFamily = "SimSun";
		}
		// 字体类型
		int fontWeight = Integer.parseInt(legendElement.getAttributeValue(GCurveConvertFromStr.LEGEND_FONT_WEIGHT));
		// 字体大小
		int fontSize = Integer.parseInt(legendElement.getAttributeValue(GCurveConvertFromStr.LEGEND_FONT_SIZE));
		// 字体颜色
		String font_color = legendElement.getAttributeValue(GCurveConvertFromStr.LEGEND_FONT_COLOR);
		int fc = 0;
		if (font_color != null && font_color.length() != 0) {
			fc = Integer.parseInt(font_color);
		}
		Color fontColor = new Color(fc);
		Font font = FontTool.createFont(fontFamily, fontWeight, fontSize);

		Legend legend = chart.getLegend();
		legend.setShow(true);
		legend.setX(legendPosX);
		legend.setY(legendPosY);
		legend.setFont(font);

	}

//	/**
//	 * 解析曲线文字域
//	 * 
//	 * @param textFieldElement
//	 */
//	private void parseTextField(Element textFieldElement) {
//		String value = null;
//		// 左上角X坐标
//		int x = 0;
//		value = textFieldElement.getAttributeValue(GCurveConvertFromStr.TEXT_FIELD_X);
//		if (value != null && value.length() != 0) {
//			x = Integer.parseInt(value);
//		}
//		// 左上角Y坐标
//		value = null;
//		int y = 0;
//		value = textFieldElement.getAttributeValue(GCurveConvertFromStr.TEXT_FIELD_Y);
//		if (value != null && value.length() != 0) {
//			y = Integer.parseInt(value);
//		}
//		// 字体名称
//		String fontFamily = textFieldElement.getAttributeValue(GCurveConvertFromStr.TEXT_FIELD_FONT_FAMILY);
//		if (fontFamily == null || fontFamily.length() == 0) {
//			fontFamily = "SimSun";
//		}
//		// 字体类型
//		value = null;
//		int fontWeight = 0;
//		value = textFieldElement.getAttributeValue(GCurveConvertFromStr.TEXT_FIELD_FONT_WEIGHT);
//		if (value != null && value.length() != 0) {
//			fontWeight = Integer.parseInt(value);
//		}
//		// 字体大小
//		value = null;
//		int fontSize = 12;
//		value = textFieldElement.getAttributeValue(GCurveConvertFromStr.TEXT_FIELD_FONT_SIZE);
//		if (value != null && value.length() != 0) {
//			fontSize = Integer.parseInt(value);
//		}
//		// 字体颜色
//		value = null;
//		value = textFieldElement.getAttributeValue(GCurveConvertFromStr.TEXT_FIELD_FONT_COLOR);
//		int fc = 0;
//		if (value != null && value.length() != 0) {
//			fc = Integer.parseInt(value);
//		}
//		Color fontColor = new Color(fc);
//
//		String content = textFieldElement.getAttributeValue(GCurveConvertFromStr.TEXT_FIELD_CONTENT);
//
//		FigTextField textField = new FigTextField(x, y);
//		textField.setFont(new Font(fontFamily, fontWeight, fontSize));
//		textField.setFontColor(fontColor);
//		textField.setHasBorder(false);
//		textField.setTransparent(true);
//		textField.setContent(content);
//
//		chart.addFig(textField);
//	}
//
//	/**
//	 * 解析曲线翻页域
//	 * 
//	 * @param turnOverFieldElement
//	 */
//	private void parseTurnOverField(Element turnOverFieldElement) {
//		String value = null;
//		// 左上角X坐标
//		int x = 0;
//		value = turnOverFieldElement.getAttributeValue(GCurveConvertFromStr.TURNOVER_FIELD_X);
//		if (value != null && value.length() != 0) {
//			x = Integer.parseInt(value);
//		}
//		// 左上角Y坐标
//		value = null;
//		int y = 0;
//		value = turnOverFieldElement.getAttributeValue(GCurveConvertFromStr.TURNOVER_FIELD_Y);
//		if (value != null && value.length() != 0) {
//			y = Integer.parseInt(value);
//		}
//		// 宽度
//		value = null;
//		int w = 30;
//		value = turnOverFieldElement.getAttributeValue(GCurveConvertFromStr.TURNOVER_FIELD_WIDTH);
//		if (value != null && value.length() != 0) {
//			w = Integer.parseInt(value);
//		}
//		// 高度
//		value = null;
//		int h = 20;
//		value = turnOverFieldElement.getAttributeValue(GCurveConvertFromStr.TURNOVER_FIELD_HEIGHT);
//		if (value != null && value.length() != 0) {
//			h = Integer.parseInt(value);
//		}
//		// 颜色
//		value = null;
//		value = turnOverFieldElement.getAttributeValue(GCurveConvertFromStr.TURNOVER_FIELD_COLOR);
//		int color = Color.red.getRGB();
//		if (value != null && value.length() != 0) {
//			color = Integer.parseInt(value);
//		}
//		Color turnOverFieldColor = new Color(color);
//		// 类型
//		value = null;
//		int type = GCurveConvertFromStr.TURNOVER_FIELD_TYPE_PRE;
//		value = turnOverFieldElement.getAttributeValue(GCurveConvertFromStr.TURNOVER_FIELD_TYPE);
//		if (value != null && value.length() != 0) {
//			type = Integer.parseInt(value);
//		}
//		TurnOverField turnOverField = null;
//		if (type == GCurveConvertFromStr.TURNOVER_FIELD_TYPE_PRE) {
//			turnOverField = new PreTurnOverField(new Point(x, y), turnOverFieldColor);
//		} else {
//			turnOverField = new NextTurnOverField(new Point(x, y), turnOverFieldColor);
//		}
//		chart.addFig(turnOverField);
//	}
//
//	/**
//	 * 解析曲线数据域
//	 * 
//	 * @param dataFieldElement
//	 */
//	private void parseDataField(Element dataFieldElement) {
//		String value = null;
//		// 左上角X坐标
//		int x = 0;
//		value = dataFieldElement.getAttributeValue(GCurveConvertFromStr.DATA_FIELD_X);
//		if (value != null && value.length() != 0) {
//			x = Integer.parseInt(value);
//		}
//		// 左上角Y坐标
//		value = null;
//		int y = 0;
//		value = dataFieldElement.getAttributeValue(GCurveConvertFromStr.DATA_FIELD_Y);
//		if (value != null && value.length() != 0) {
//			y = Integer.parseInt(value);
//		}
//		// 字体名称
//		String fontFamily = dataFieldElement.getAttributeValue(GCurveConvertFromStr.DATA_FIELD_FONT_FAMILY);
//		if (fontFamily == null || fontFamily.length() == 0) {
//			fontFamily = "SimSun";
//		}
//		// 字体类型
//		value = null;
//		int fontWeight = 0;
//		value = dataFieldElement.getAttributeValue(GCurveConvertFromStr.DATA_FIELD_FONT_WEIGHT);
//		if (value != null && value.length() != 0) {
//			fontWeight = Integer.parseInt(value);
//		}
//		// 字体大小
//		value = null;
//		int fontSize = 12;
//		value = dataFieldElement.getAttributeValue(GCurveConvertFromStr.DATA_FIELD_FONT_SIZE);
//		if (value != null && value.length() != 0) {
//			fontSize = Integer.parseInt(value);
//		}
//		// 字体颜色
//		value = null;
//		value = dataFieldElement.getAttributeValue(GCurveConvertFromStr.DATA_FIELD_FONT_COLOR);
//		int fc = 0;
//		if (value != null && value.length() != 0) {
//			fc = Integer.parseInt(value);
//		}
//		Color fontColor = new Color(fc);
//
//		// 所属纵坐标
//		value = null;
//		int y_type = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
//		value = dataFieldElement.getAttributeValue(GCurveConvertFromStr.ENTITY_AXIS_Y_TYPE);
//		if (value != null && value.length() != 0) {
//			y_type = Integer.parseInt(value);
//		}
//		if (y_type == GCurveConvertFromStr.MULTI_AXIS_SINGLE) {
//			y_type = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
//		}
//
//		// 所属曲线序号
//		value = null;
//		int index = 0;
//		value = dataFieldElement.getAttributeValue(GCurveConvertFromStr.DATA_FIELD_INDEX);
//		if (value != null && value.length() != 0) {
//			index = Integer.parseInt(value);
//		}
//
//		// SQL内容
//		value = null;
//		String content = "数据域";
//		value = dataFieldElement.getAttributeValue(GCurveConvertFromStr.DATA_FIELD_CONTENT);
//		if (value != null && value.length() != 0) {
//			content = value;
//		}
//
//		RecordSet rSet = new RecordSetNull();
//		FigDataField dataField = new FigDataField(x, y);
//		FigDataFieldModel dataFieldModel = dataField.getModel();
//		dataField.setFont(new Font(fontFamily, fontWeight, fontSize));
//		dataField.setFontColor(fontColor);
//		dataField.setHasBorder(false);
//		dataField.setTransparent(true);
//		dataField.setContent("数据域");
//		dataFieldModel.setContent("数据域");
//		// 数据域类型
//		value = null;
//		int type = GCurveConvertFromStr.DATA_FIELD_TYPE_VIEW_DATE;
//		value = dataFieldElement.getAttributeValue(GCurveConvertFromStr.DATA_FIELD_TYPE);
//		if (value != null && value.length() != 0) {
//			type = Integer.parseInt(value);
//		}
//
//		FigCurve figCurve = this.curveTable.get(y_type + "+" + index);
//		String title = "";
//		if (figCurve != null) {
//			title = figCurve.getTitle();
//		}
//		if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RDB) {
//			if (index == GCurveConvertFromStr.DATA_FIELD_INDEX_NULL) {
//				rSet = new ReRecordSet(content, null);
//			} else {
//				rSet = new DFReRecordSet(content, null, title, "", index);
//			}
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTDB) {
//			String curveName = dataFieldElement.getAttributeValue("curveName");
//			String serverName = dataFieldElement.getAttributeValue("serverName");
//			String appName = IDTool.getAppName(Integer.valueOf(dataFieldElement.getAttributeValue("appName")).intValue());
//			String systemName = dataFieldElement.getAttributeValue("systemName");
//			String dbName = dataFieldElement.getAttributeValue("dbName");
//			String tableName = dataFieldElement.getAttributeValue("tableName");
//			String id = dataFieldElement.getAttributeValue("id");
//			String id_type = dataFieldElement.getAttributeValue("id_type");
//			String propertyName = dataFieldElement.getAttributeValue("propertyName");
//			String startPoint = dataFieldElement.getAttributeValue("startPoint");
//			String sum = dataFieldElement.getAttributeValue("sum");
//			String pression = dataFieldElement.getAttributeValue("pression");
//			String unit = dataFieldElement.getAttributeValue("unit");
//			boolean pressionFlag = false;
//			if (pression != null) {
//				pressionFlag = true;
//			}
//			if (curveName != null) {
//				rSet = new RTRecordSet(curveName, serverName, systemName, appName, dbName, tableName, id, propertyName, startPoint, sum, pression, unit, pressionFlag, true);
//			} else {
//				rSet = new RTRecordSet(serverName, systemName, appName, dbName, tableName, id, propertyName, startPoint, sum, pression, unit, pressionFlag, false);
//			}
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_VIEW_DATE) {
//			dataField.getModel().setTime(true);
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_DATE) {
//			dataFieldModel.setCurveDate(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_MAX) {
//			dataFieldModel.setMax(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_MAX_TIME) {
//			dataFieldModel.setMaxTime(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_MIN) {
//			dataFieldModel.setMin(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_MIN_TIME) {
//			dataFieldModel.setMinTime(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_AVE) {
//			dataFieldModel.setAverageValue(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_CURRENT) {
//			dataFieldModel.setCurrentValue(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//			// modified by 刘仁勇，处理积分电量
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_INTEGRAL_ELECTRICITY_QUANTITY) {
//			dataFieldModel.setIntegralElectricityQuantity(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理曲线负荷率
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_FHL) {
//			dataFieldModel.setFhl(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理平均负荷
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_AVG_FH) {
//			dataFieldModel.setAVGFH(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//			// 处理整点负荷率
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_INT_FHL) {
//			dataFieldModel.setIntFHL(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理整点最大值
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_INT_MAX) {
//			dataFieldModel.setIntMax(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理整点最小值
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_INT_MIN) {
//			dataFieldModel.setIntMin(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理上午最大值
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_AM_MAX) {
//			dataFieldModel.setAMMax(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理上午最小值
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_AM_MIN) {
//			dataFieldModel.setAMMin(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//			// 处理中午最大值
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_PM_MAX) {
//			dataFieldModel.setPMMax(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理中午最小值
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_PM_MIN) {
//			dataFieldModel.setPMMin(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//			// 处理晚上最大值
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_NG_MAX) {
//			dataFieldModel.setNGMax(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理晚上最小值
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_NG_MIN) {
//			dataFieldModel.setNGMin(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		}
//		// 处理整点最大值时间
//		else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_INT_MAX_TIME) {
//			dataFieldModel.setIntMaxTime(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理整点最小值时间
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_INT_MIN_TIME) {
//			dataFieldModel.setIntMinTime(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理上午最大值时间
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_AM_MAX_TIME) {
//			dataFieldModel.setAMMaxTime(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理上午最小值时间
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_AM_MIN_TIME) {
//			dataFieldModel.setAMMinTime(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//			// 处理中午最大值时间
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_PM_MAX_TIME) {
//			dataFieldModel.setPMMaxTime(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理中午最小值时间
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_PM_MIN_TIME) {
//			dataFieldModel.setPMMinTime(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//			// 处理晚上最大值时间
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_NG_MAX_TIME) {
//			dataFieldModel.setNGMaxTime(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//
//			// 处理晚上最小值时间
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_NG_MIN_TIME) {
//			dataFieldModel.setNGMinTime(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		}
//
//		else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_DAY_CN) {
//			dataFieldModel.setRtnconDayCn(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_DAY_MR) {
//			dataFieldModel.setRtnconDayMr(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_DAY_SR) {
//			dataFieldModel.setRtnconDaySr(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_DAY_SN) {
//			dataFieldModel.setRtnconDaySn(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_MONTH_CN) {
//			dataFieldModel.setRtnconMonthCn(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_MONTH_MR) {
//			dataFieldModel.setRtnconMonthMr(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_MONTH_SR) {
//			dataFieldModel.setRtnconMonthSr(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_MONTH_SN) {
//			dataFieldModel.setRtnconMonthSn(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_YEAR_CN) {
//			dataFieldModel.setRtnconYearCn(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_YEAR_MR) {
//			dataFieldModel.setRtnconYearMr(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_YEAR_SR) {
//			dataFieldModel.setRtnconYearSr(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_YEAR_SN) {
//			dataFieldModel.setRtnconYearSn(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_DAY_CN) {
//			dataFieldModel.setBigRtnconDayCn(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_DAY_MR) {
//			dataFieldModel.setBigRtnconDayMr(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_DAY_SR) {
//			dataFieldModel.setBigRtnconDaySr(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_DAY_SN) {
//			dataFieldModel.setBigRtnconDaySn(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_MONTH_CN) {
//			dataFieldModel.setBigRtnconMonthCn(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_MONTH_MR) {
//			dataFieldModel.setBigRtnconMonthMr(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_MONTH_SR) {
//			dataFieldModel.setBigRtnconMonthSr(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_MONTH_SN) {
//			dataFieldModel.setBigRtnconMonthSn(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_YEAR_CN) {
//			dataFieldModel.setBigRtnconYearCn(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_YEAR_MR) {
//			dataFieldModel.setBigRtnconYearMr(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_YEAR_SR) {
//			dataFieldModel.setBigRtnconYearSr(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		} else if (type == GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_YEAR_SN) {
//			dataFieldModel.setBigRtnconYearSn(true);
//			dataFieldModel.setCurveName(title);
//			rSet = figCurve.getModel().getRecordSet();
//		}
//
//		if (type != GCurveConvertFromStr.DATA_FIELD_TYPE_MAX_TIME && type != GCurveConvertFromStr.DATA_FIELD_TYPE_MIN_TIME && type != GCurveConvertFromStr.DATA_FIELD_TYPE_VIEW_DATE
//				&& type != GCurveConvertFromStr.DATA_FIELD_TYPE_DATE) {
//			// 精度
//			value = null;
//			int decimal = 2;
//			value = dataFieldElement.getAttributeValue(GCurveConvertFromStr.DATA_FIELD_DECIMAL);
//			if (value != null && value.length() != 0) {
//				decimal = Integer.parseInt(value);
//			}
//			dataFieldModel.setValuePresision(decimal);
//			// 单位
//			value = null;
//			String unit = "";
//			value = dataFieldElement.getAttributeValue(GCurveConvertFromStr.DATA_FIELD_UNIT);
//			if (value != null && value.length() != 0) {
//				unit = value;
//			}
//			dataFieldModel.setValueUnit(unit);
//		}
//
//		dataFieldModel.setRecordSet(rSet);
//		chart.addFig(dataField);
//	}
//
//	/**
//	 * 解析曲线焦点域
//	 * 
//	 * @param focusFieldElement
//	 */
//	private void parseFocusField(Element focusFieldElement) {
//
//	}
//
//	public FigCurve getFigCompositeChart() {
//		return chart;
//	}
//
//	public void setSwitchApp(boolean switchApp2) {
//		this.switchApp = switchApp2;
//	}

	// /**
	// * 根据属性名返回属性值
	// *
	// * @param figElement
	// * 解析对象
	// * @param attribName
	// * 属性名
	// * @param className
	// * 类名
	// *
	// * @return Object
	// */
	// private Object getAttributeValue(Element figElement, String attribName,
	// String className) {
	// Object classObj = null;
	// String value = figElement.getAttributeValue(attribName);
	// if (value != null && value.length() != 0) {
	// Class[] para = { String.class };
	// String[] initargs = {value};
	// try {
	// Class classByName = Class.forName(className);
	// Constructor constructor = classByName.getConstructor(para);
	// classObj = constructor.newInstance(initargs);
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (SecurityException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NoSuchMethodException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalArgumentException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InstantiationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return classObj;
	// }

	public static void main(String args[]) {
		JFrame frame= new JFrame();
		frame.setSize(1000,700);
		GCurveParse parse = new GCurveParse();
		Document document = null;
		try {
			document = new SAXBuilder().build(new File("C:/Users/LRY/bin/mmiexec_211/data/graph/SC/curve/scada/SC.东北总出力.scada.curve.g"));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		parse.setDocument(document);
		parse.parse();
		JScrollPane scrollPane = new JScrollPane();
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout());
		panel.add(parse.getChart());
		panel.setPreferredSize(new Dimension(2000, 1000));
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);
	}
}
