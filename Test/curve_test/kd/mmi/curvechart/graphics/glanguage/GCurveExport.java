//package kd.mmi.curvechart.graphics.glanguage;
//
//import java.awt.Font;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.text.NumberFormat;
//import java.util.Calendar;
//import java.util.Hashtable;
//import java.util.List;
//
//import kd.mmi.curvechart.data.dataset.DFReRecordSet;
//import kd.mmi.curvechart.data.dataset.MiddataBatchRecordSet;
//import kd.mmi.curvechart.data.dataset.MiddataSingleRecordSet;
//import kd.mmi.curvechart.data.dataset.MidhsRecordSet;
//import kd.mmi.curvechart.data.dataset.MidhsSecondRecordSet;
//import kd.mmi.curvechart.data.dataset.RTRecordSet;
//import kd.mmi.curvechart.data.dataset.ReRecordSet;
//import kd.mmi.curvechart.data.dataset.RecordSet;
//import kd.mmi.curvechart.graphics.figure.FigGrid;
//import kd.mmi.curvechart.graphics.figure.axis.FigAxisX;
//import kd.mmi.curvechart.graphics.figure.axis.FigAxisY;
//import kd.mmi.curvechart.graphics.figure.axis.scalelabelgenerator.DBGenerator;
//import kd.mmi.curvechart.graphics.figure.axis.scalelabelgenerator.DayGenerator;
//import kd.mmi.curvechart.graphics.figure.axis.scalelabelgenerator.HourGenerator;
//import kd.mmi.curvechart.graphics.figure.axis.scalelabelgenerator.MinuteGenerator;
//import kd.mmi.curvechart.graphics.figure.axis.scalelabelgenerator.MonthGenerator;
//import kd.mmi.curvechart.graphics.figure.axis.scalelabelgenerator.NumberGenerator;
//import kd.mmi.curvechart.graphics.figure.axis.scalelabelgenerator.ScaleLabelGenerator;
//import kd.mmi.curvechart.graphics.figure.axis.scalelabelgenerator.SecondGenerator;
//import kd.mmi.curvechart.graphics.figure.axis.scalelabelgenerator.TimeGenerator;
//import kd.mmi.curvechart.graphics.figure.compositechart.FigCompositeChart;
//import kd.mmi.curvechart.graphics.figure.curve.FigCurve;
//import kd.mmi.curvechart.graphics.figure.curve.drawstrategy.DrawStrategy;
//import kd.mmi.curvechart.graphics.figure.curve.drawstrategy.FigCurveDrawInLadder;
//import kd.mmi.curvechart.graphics.figure.curve.drawstrategy.FigCurveDrawInLine;
//import kd.mmi.curvechart.graphics.figure.curve.drawstrategy.FigCurveDrawInScatter;
//import kd.mmi.curvechart.graphics.figure.curve.drawstrategy.FigCurveDrawInSmooth;
//import kd.mmi.curvechart.graphics.figure.drawarea.FigDrawArea;
//import kd.mmi.curvechart.graphics.figure.filling.FillImage;
//import kd.mmi.curvechart.graphics.figure.filling.FillPattern;
//import kd.mmi.curvechart.graphics.figure.filling.FillSingle;
//import kd.mmi.curvechart.graphics.figure.filling.FillTransparent;
//import kd.mmi.curvechart.graphics.figure.functionfield.NextTurnOverField;
//import kd.mmi.curvechart.graphics.figure.functionfield.TurnOverField;
//import kd.mmi.curvechart.graphics.figure.legend.FigLegend;
//import kd.mmi.curvechart.graphics.figure.textfield.FigTextField;
//import kd.mmi.curvechart.graphics.figure.textfield.datafield.FigDataField;
//
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.output.Format;
//import org.jdom.output.XMLOutputter;
//
//import context.Context;
//
//import agency.message.base.IDTool;
//import agency.message.base.KeyID;
//import agency.message.midhs.MidhsCurveStaticFinals;
//
///**
// * ����ģ��G�ļ�������
// * 
// * @author mengxin
// * @since 2010/04/07
// */
//public class GCurveExport {
//
//	private static GCurveExport instance = null;
//	private FigCompositeChart chart = null;
//	private Element rootElement = null;;
//
//	private Hashtable<Integer, FigCurve> curveTable = new Hashtable<Integer, FigCurve>();
//	private Document document = new Document();
//
//	private GCurveExport() {
//
//	}
//
//	public static GCurveExport getInstance() {
//		if (instance == null) {
//			instance = new GCurveExport();
//		}
//		return instance;
//	}
//
//	public void setFigCompositeChart(FigCompositeChart figChart) {
//		chart = figChart;
//	}
//
//	/**
//	 * ��������
//	 * 
//	 * @param filePath
//	 *            �ļ�·��
//	 */
//	public void export(String filePath) {
//		rootElement = new Element(GCurveConvertFromStr.SGD);
//		this.exportCurve();
//		this.exportGrid();
//		this.exportXAxis();
//		this.exportYAxis();
//		this.exportEntity();
//		this.exportDynamic();
//		this.exportLegend();
//		this.exportDataField();
//		this.exportTextField();
//		this.exportTurnOverField();
//		this.exportFocusField();
//
//		document.setRootElement(rootElement);
//
//		try {
//			FileOutputStream out = new FileOutputStream(filePath);
//			XMLOutputter outputter = new XMLOutputter();
//			outputter.setFormat(Format.getPrettyFormat().setEncoding("GBK"));
//			outputter.output(document, out);
//			out.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * ��������ʵ������
//	 */
//	private void exportCurve() {
//		Element curveElement = new Element(GCurveConvertFromStr.CURVE);
//		rootElement.addContent(curveElement);
//
//		// ���Ͻ�X����
//		curveElement.setAttribute(GCurveConvertFromStr.CHART_X, String.valueOf(chart.getX()));
//
//		// ���Ͻ�Y����
//		curveElement.setAttribute(GCurveConvertFromStr.CHART_Y, String.valueOf(chart.getY()));
//
//		// ���
//		curveElement.setAttribute(GCurveConvertFromStr.CHART_WIDTH, String.valueOf(chart.getW()));
//
//		// �߶�
//		curveElement.setAttribute(GCurveConvertFromStr.CHART_HEIGHT, String.valueOf(chart.getH()));
//
//		// �������ģʽ
//		FillPattern fillPattern = chart.getFillPattern();
//		int fillPatternValue = GCurveConvertFromStr.CHART_BACKGROUND_FILL_SINGLE;
//		if (fillPattern instanceof FillSingle) {
//			fillPatternValue = GCurveConvertFromStr.CHART_BACKGROUND_FILL_SINGLE;
//		} else if (fillPattern instanceof FillTransparent) {
//			fillPatternValue = GCurveConvertFromStr.CHART_BACKGROUND_FILL_TRANSPARENT;
//		} else if (fillPattern instanceof FillImage) {
//			fillPatternValue = GCurveConvertFromStr.CHART_BACKGROUND_FILL_PIC;
//			// ����ͼƬ·��
//			String picUrl = ((FillImage) fillPattern).getFileName();
//			curveElement.setAttribute(GCurveConvertFromStr.CHART_BACKGROUND_PIC, picUrl);
//		}
//		curveElement.setAttribute(GCurveConvertFromStr.CHART_BACKGROUND_FILLPATTERN, String.valueOf(fillPatternValue));
//
//		// �Ƿ���ʾ����
//		int title_show = GCurveConvertFromStr.BOOLEAN_FALSE;// Ĭ�ϲ���ʾ
//		curveElement.setAttribute(GCurveConvertFromStr.TITLE_SHOW, String.valueOf(title_show));
//
//		// ��������
//		String title_content = "";
//		curveElement.setAttribute(GCurveConvertFromStr.TITLE_CONTENT, title_content);
//
//		// ˢ������
//		int refreshPeriod = chart.getRefreshPeriod();
//		curveElement.setAttribute(GCurveConvertFromStr.REFRESH_INTERVAL, String.valueOf(refreshPeriod));
//
//		// ��������
//		int multiAxisType = GCurveConvertFromStr.MULTI_AXIS_TYPE_SINGLE;// Ĭ�ϵ���
//		curveElement.setAttribute(GCurveConvertFromStr.MULTI_AXIS_TYPE, String.valueOf(multiAxisType));
//
//		// ������ɫ
//		int bgc = GCurveConvertFromStr.COLOR_WITHE; // Ĭ��-1
//		bgc = chart.getColor().getRGB();
//		curveElement.setAttribute(GCurveConvertFromStr.CHART_BACKGROUND_COLOR, String.valueOf(bgc));
//	}
//
//	/**
//	 * ����������������
//	 */
//	private void exportGrid() {
//		Element gridElement = new Element(GCurveConvertFromStr.GRID);
//		rootElement.addContent(gridElement);
//
//		FigDrawArea drawArea = chart.getDrawArea();
//		// ���Ͻ�X����
//		gridElement.setAttribute(GCurveConvertFromStr.MAIN_GRID_X, String.valueOf(drawArea.getX()));
//
//		// ���Ͻ�Y����
//		gridElement.setAttribute(GCurveConvertFromStr.MAIN_GRID_Y, String.valueOf(drawArea.getY()));
//
//		// ���
//		gridElement.setAttribute(GCurveConvertFromStr.MAIN_GRID_WIDTH, String.valueOf(drawArea.getW()));
//
//		// �߶�
//		gridElement.setAttribute(GCurveConvertFromStr.MAIN_GRID_HEIGHT, String.valueOf(drawArea.getH()));
//
//		FigGrid figGrid = drawArea.getGrid();
//		// ��������ɫ
//		int main_grid_c = GCurveConvertFromStr.COLOR_BLACK;// Ĭ�Ϻ�ɫ
//		main_grid_c = figGrid.getColor().getRGB();
//		gridElement.setAttribute(GCurveConvertFromStr.MAIN_GRID_COLOR, String.valueOf(main_grid_c));
//
//		// �������߿�
//		int mainGridLineWidth = GCurveConvertFromStr.LINE_DEFAULT_WIDTH;// Ĭ��1
//		mainGridLineWidth = figGrid.getLineWidth();
//		gridElement.setAttribute(GCurveConvertFromStr.MAIN_GRID_LINE_WIDTH, String.valueOf(mainGridLineWidth));
//
//		// ����������
//		int mainGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DASH; // Ĭ������
//		float[] mainGridDash = figGrid.getDash();
//		if (mainGridDash == null) {
//			mainGridLineStyle = GCurveConvertFromStr.LINE_STYLE_SOLID;
//		} else if (mainGridDash == new float[] { 4.0f, 2.0f }) {
//			mainGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DASH;
//		} else if (mainGridDash == new float[] { 1.0f, 2.0f }) {
//			mainGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DOT;
//		} else if (mainGridDash == new float[] { 4.0f, 2.0f, 1.0f, 2.0f }) {
//			mainGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DASH_DOT;
//		} else if (mainGridDash == new float[] { 4.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f }) {
//			mainGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DASH_DOT_DOT;
//		}
//		gridElement.setAttribute(GCurveConvertFromStr.MAIN_GRID_LINE_STYLE, String.valueOf(mainGridLineStyle));
//
//		// �Ƿ���ʾX����������
//		int main_grid_x_show = GCurveConvertFromStr.BOOLEAN_TRUE;
//		if (figGrid.isMainGridXShow() == false) {
//			main_grid_x_show = GCurveConvertFromStr.BOOLEAN_FALSE;
//		}
//		gridElement.setAttribute(GCurveConvertFromStr.MAIN_GRID_X_SHOW, String.valueOf(main_grid_x_show));
//
//		// �Ƿ���ʾY����������
//		int main_grid_y_show = GCurveConvertFromStr.BOOLEAN_TRUE;
//		if (figGrid.isMainGridYShow() == false) {
//			main_grid_y_show = GCurveConvertFromStr.BOOLEAN_FALSE;
//		}
//		gridElement.setAttribute(GCurveConvertFromStr.MAIN_GRID_Y_SHOW, String.valueOf(main_grid_y_show));
//
//		// ��������ɫ
//		int sub_grid_c = GCurveConvertFromStr.COLOR_BLACK;
//		sub_grid_c = figGrid.getColor().getRGB();
//		gridElement.setAttribute(GCurveConvertFromStr.SUB_GRID_COLOR, String.valueOf(sub_grid_c));
//
//		// �������߿�
//		int subGridLineWidth = GCurveConvertFromStr.LINE_DEFAULT_WIDTH;
//		subGridLineWidth = figGrid.getLineWidth();
//		gridElement.setAttribute(GCurveConvertFromStr.SUB_GRID_LINE_WIDTH, String.valueOf(subGridLineWidth));
//
//		// ����������
//		int subGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DASH;
//		float[] subGridDash = figGrid.getDash();
//		if (subGridDash == null) {
//			subGridLineStyle = GCurveConvertFromStr.LINE_STYLE_SOLID;
//		} else if (subGridDash == new float[] { 4.0f, 2.0f }) {
//			subGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DASH;
//		} else if (subGridDash == new float[] { 1.0f, 2.0f }) {
//			subGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DOT;
//		} else if (subGridDash == new float[] { 4.0f, 2.0f, 1.0f, 2.0f }) {
//			subGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DASH_DOT;
//		} else if (subGridDash == new float[] { 4.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f }) {
//			subGridLineStyle = GCurveConvertFromStr.LINE_STYLE_DASH_DOT_DOT;
//		}
//		gridElement.setAttribute(GCurveConvertFromStr.SUB_GRID_LINE_STYLE, String.valueOf(subGridLineStyle));
//
//		// �Ƿ���ʾX��������
//		int sub_grid_x_show = GCurveConvertFromStr.BOOLEAN_FALSE;
//		if (figGrid.isSubGridXShow()) {
//			sub_grid_x_show = GCurveConvertFromStr.BOOLEAN_TRUE;
//		}
//		gridElement.setAttribute(GCurveConvertFromStr.SUB_GRID_X_SHOW, String.valueOf(sub_grid_x_show));
//
//		// �Ƿ���ʾY��������
//		int sub_grid_y_show = GCurveConvertFromStr.BOOLEAN_FALSE;
//		if (figGrid.isSubGridYShow()) {
//			sub_grid_y_show = GCurveConvertFromStr.BOOLEAN_TRUE;
//		}
//		gridElement.setAttribute(GCurveConvertFromStr.SUB_GRID_Y_SHOW, String.valueOf(sub_grid_y_show));
//	}
//
//	/**
//	 * ��������X������
//	 */
//	private void exportXAxis() {
//		Element xAxisElement = new Element(GCurveConvertFromStr.X_AXIS);
//		rootElement.addContent(xAxisElement);
//
//		FigAxisX axisX = chart.getDrawArea().getAxisX();
//
//		// ����������
//		int scaleType = GCurveConvertFromStr.AXIS_X_SCALE_TYPE_TIME;
//		ScaleLabelGenerator slg = axisX.getScaleLabelGenerator();
//		if (slg instanceof TimeGenerator) {
//			scaleType = GCurveConvertFromStr.AXIS_X_SCALE_TYPE_TIME;
//		} else if (slg instanceof NumberGenerator) {
//			scaleType = GCurveConvertFromStr.AXIS_X_SCALE_TYPE_NUM;
//		} else if(slg instanceof DBGenerator){
//			scaleType = GCurveConvertFromStr.AXIS_X_SCALE_TYPE_RTDB;
//		}
//		xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_SCALE_TYPE, String.valueOf(scaleType));
//
//		// ����Ƿ���ʾ
//		int show = GCurveConvertFromStr.BOOLEAN_TRUE;
//		if (axisX.isVisible()) {
//			show = GCurveConvertFromStr.BOOLEAN_TRUE;
//		} else {
//			show = GCurveConvertFromStr.BOOLEAN_FALSE;
//		}
//		xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_SHOW, String.valueOf(show));
//
//		// �Ƿ��ܶ�
//		int run = GCurveConvertFromStr.BOOLEAN_FALSE;
//		// �ܶ�����
//		int runpace = 0;
//		if (slg instanceof TimeGenerator) {
//			if(((TimeGenerator)slg).isRun()){
//				run = GCurveConvertFromStr.BOOLEAN_TRUE;
//			}
//			runpace = ((TimeGenerator)slg).getRunPace();
//		} 
//		xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_RUN, String.valueOf(run));
//		xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_RUN_PACE, String.valueOf(runpace));
//
//		// �����
//		int mainSpace = axisX.getModel().getMainSpace();
//		xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_MAIN_SPACE, String.valueOf(mainSpace));
//
//		// �μ��
//		int subSpace = axisX.getModel().getSubSpace();
//		xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_SUB_SPACE, String.valueOf(subSpace));
//		
//		// ����̶�
//		// ʱ����
//		if (scaleType == GCurveConvertFromStr.AXIS_X_SCALE_TYPE_TIME) {
//			TimeGenerator timeSLG = (TimeGenerator) slg;
//			// ��߸�ʽ
//			int timeFormat = timeSLG.getTimeFormat();
//			xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_TIME_FORMAT, String.valueOf(timeFormat));
//
//			// ��ʼ
//			int start = timeSLG.getMin();
//			xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_TIME_START, String.valueOf(start));
//			// ����
//			int end = timeSLG.getMin() + timeSLG.getLength();
//			xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_TIME_END, String.valueOf(end));
//			// ʱ����
//			int span = timeSLG.getLength();
//			xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_TIME_SPAN, String.valueOf(span));
//			if (run == GCurveConvertFromStr.BOOLEAN_TRUE) {
//				// ������ռ����
//				float percent = 0;
//				if (start == 0) {
//                    percent = runpace / (float)span * 100;
//				} else {
//					percent = Math.abs(start) / (float) span * 100;
//				}
//				NumberFormat numberFormat = NumberFormat.getInstance();
//				numberFormat.setMaximumFractionDigits(2);
//				xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_PREPARATORY_PERCENT, numberFormat.format(percent));
//			}
//
//			// �뼶�Ĳ�����
//			int pace = (int) ((axisX.getModel().getMaxValue() -  axisX.getModel().getMinValue())/mainSpace);
//			
//			int unit = GCurveConvertFromStr.AXIS_X_TIME_UNIT_HOUR;
//			if (timeSLG instanceof SecondGenerator) {
//				unit = GCurveConvertFromStr.AXIS_X_TIME_UNIT_SECOND;
//			} else if (timeSLG instanceof MinuteGenerator) {
//				unit = GCurveConvertFromStr.AXIS_X_TIME_UNIT_MINUTE;
//				pace *= 60;
//			} else if (timeSLG instanceof HourGenerator) {
//				unit = GCurveConvertFromStr.AXIS_X_TIME_UNIT_HOUR;
//				pace *= 60*60;
//			} else if (timeSLG instanceof DayGenerator) {
//				unit = GCurveConvertFromStr.AXIS_X_TIME_UNIT_DAY;
//				pace *= 60*60*60;
//			} else if (timeSLG instanceof MonthGenerator) {
//				unit = GCurveConvertFromStr.AXIS_X_TIME_UNIT_MONTH;
//				pace *= 60*60*60*60;
//			}
//		
//			//�ܲ�������λ
//			int runUnit = axisX.getModel().getRunUnit();
//			xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_RUN_UNIT, String.valueOf(runUnit));
//			
//			
//			// ʱ������λ
//			xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_TIME_UNIT, String.valueOf(unit));
//			
//			
//			xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_PACE, String.valueOf(pace));
//			
//			// �Ƿ����ʱ��
//			int abs_time = GCurveConvertFromStr.BOOLEAN_TRUE;
//			if (timeSLG.isAbsTime() == false) {
//				abs_time = GCurveConvertFromStr.BOOLEAN_FALSE;
//			}
//			xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_TIME_IS_ABS, String.valueOf(abs_time));
//
//			// �̶�ʱ���Ƿ�ȡ��
//			int int_time = GCurveConvertFromStr.BOOLEAN_FALSE;
//			if (timeSLG.isIntegerTime()) {
//				int_time = GCurveConvertFromStr.BOOLEAN_TRUE;
//			}
//			xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_TIME_IS_INT, String.valueOf(int_time));
//
//			// ȡ��ʱ��ֵ
//			int integerTimeValue = GCurveConvertFromStr.DEFAULT_TIME_INT_VALUE;
//			if (timeSLG instanceof MinuteGenerator) {
//				integerTimeValue = ((MinuteGenerator) timeSLG).getTimeStep();
//			} else if(timeSLG instanceof HourGenerator){
//				integerTimeValue = ((HourGenerator) timeSLG).getTimeStep();
//			}
//			xAxisElement
//					.setAttribute(GCurveConvertFromStr.AXIS_X_TIME_INT_TIME_VALUE, String.valueOf(integerTimeValue));
//
//		}
//		// ������
//		else if (scaleType == GCurveConvertFromStr.AXIS_X_SCALE_TYPE_NUM) {
//			NumberGenerator numSLG = (NumberGenerator) slg;
//			// ���ֵ
//			float max = numSLG.getMax();
//			xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_NUMBER_MAX, String.valueOf(max));
//
//			// ��Сֵ
//			float min = numSLG.getMin();
//			xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_NUMBER_MIN, String.valueOf(min));
//
//			// С����Чλ��
//			int decimal = numSLG.getDecimal();
//			xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_NUMBER_DECIMAL, String.valueOf(decimal));
//		} 
//		//ʵʱ��
//		else if(scaleType == GCurveConvertFromStr.AXIS_X_SCALE_TYPE_RTDB){
//			
//		}
//
//		// ��������
//		Font axisXFont = axisX.getFont();
//		String fontFamily = axisXFont.getFamily();
//		xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_FONT_FAMILY, fontFamily);
//
//		// ��������
//		int fontWeight = axisXFont.getStyle();
//		xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_FONT_WEIGHT, String.valueOf(fontWeight));
//
//		// �����С
//		int fontSize = axisXFont.getSize();
//		xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_FONT_SIZE, String.valueOf(fontSize));
//
//		// ������ɫ
//		int fc = axisX.getFontColor().getRGB();
//		xAxisElement.setAttribute(GCurveConvertFromStr.AXIS_X_FONT_COLOR, String.valueOf(fc));
//		
//	}
//
//	/**
//	 * ��������Y������
//	 */
//	private void exportYAxis() {
//		Element yAxisElement = new Element(GCurveConvertFromStr.Y_AXIS);
//		rootElement.addContent(yAxisElement);
//
//		FigAxisY axisY = chart.getDrawArea().getAxisY(GCurveConvertFromStr.MULTI_AXIS_LEFT_1);
//
//		// �������Ƿ�����Ӧ
//		int auto_scale = GCurveConvertFromStr.BOOLEAN_FALSE;
//		if (axisY.isAutoScale()) {
//			auto_scale = GCurveConvertFromStr.BOOLEAN_TRUE;
//		}
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_AUTO_SCALE, String.valueOf(auto_scale));
//
//		// ����Ƿ���ʾ
//		int axis_y_show = GCurveConvertFromStr.BOOLEAN_TRUE;
//		if (axisY.isVisible() == false) {
//			axis_y_show = GCurveConvertFromStr.BOOLEAN_FALSE;
//		}
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_SHOW, String.valueOf(axis_y_show));
//
//		// ����������궨��
//		int multiAxisType = GCurveConvertFromStr.MULTI_AXIS_SINGLE;
//		yAxisElement.setAttribute(GCurveConvertFromStr.MULTI_AXIS_DEF, String.valueOf(multiAxisType));
//
//		// �����
//		int mainSpace = axisY.getModel().getMainSpace();
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_MAIN_SPACE, String.valueOf(mainSpace));
//
//		// �μ��
//		int subSpace = axisY.getModel().getSubSpace();
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_SUB_SPACE, String.valueOf(subSpace));
//
//		// ���ֵ
//		float max = axisY.getModel().getMaxValue();
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_NUMBER_MAX, String.valueOf(max));
//
//		// ��Сֵ
//		float min = axisY.getModel().getMinValue();
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_NUMBER_MIN, String.valueOf(min));
//
//		// ���ֵ�ϸ�����
//		float maxRate = axisY.getModel().getMaxRate();
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_MAX_RATE, String.valueOf(maxRate));
//
//		// ��Сֵ�¸�����
//		float minRate = axisY.getModel().getMinRate();
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_MIN_RATE, String.valueOf(minRate));
//
//		//������Ŀ
//		int cnum = curveTable.size();
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_CNUM, String.valueOf(cnum));
//		
//		//��߲���
//		int pace = (int) ((max - min) / mainSpace);
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_PACE, String.valueOf(pace));
//		
////		// �̶��Ƿ�ȡ��
////		int is_integer = GCurveConvertFromStr.BOOLEAN_FALSE;
////		if (axisY.getModel().getIsChangeInt()) {
////			is_integer = GCurveConvertFromStr.BOOLEAN_TRUE;
////		}
////		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_IS_INTEGER, String.valueOf(is_integer));
//
//		// �Ƿ���ֵ������Ӧ
//		int auto_with_limit = GCurveConvertFromStr.BOOLEAN_FALSE;
//		if (axisY.isAutoScaleWithExtend()) {
//			auto_with_limit = GCurveConvertFromStr.BOOLEAN_TRUE;
//		}
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_AUTO_WITH_LIMIT, String.valueOf(auto_with_limit));
//
//		ScaleLabelGenerator slg = axisY.getScaleLabelGenerator();
//		if (slg instanceof NumberGenerator) {
//			NumberGenerator numSLG = (NumberGenerator) slg;
//			// С����Чλ��
//			int decimal = numSLG.getDecimal();
//			yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_NUMBER_DECIMAL, String.valueOf(decimal));
//		}
//
//		Font axisYFont = axisY.getFont();
//		// ��������
//		String fontFamily = axisYFont.getFamily();
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_FONT_FAMILY, fontFamily);
//
//		// ��������
//		int fontWeight = axisYFont.getStyle();
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_FONT_WEIGHT, String.valueOf(fontWeight));
//
//		// �����С
//		int fontSize = axisYFont.getSize();
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_FONT_SIZE, String.valueOf(fontSize));
//
//		// ������ɫ
//		int fc = axisY.getFontColor().getRGB();
//		yAxisElement.setAttribute(GCurveConvertFromStr.AXIS_Y_FONT_COLOR, String.valueOf(fc));
//
//	}
//
//	/**
//	 * ��������ʵ������
//	 */
//	private void exportEntity() {
//		List<FigCurve> curveList = chart.getDrawArea().getCurves();
//		if (curveList != null) {
//			for (int i = 0; i < curveList.size(); i++) {
//				Element entityElement = new Element(GCurveConvertFromStr.ENTITY);
//				rootElement.addContent(entityElement);
//				FigCurve figCurve = curveList.get(i);
//				// ��������
//				String title = figCurve.getTitle();
//				
//				//�Ƿ��Զ��������߱���
//				int autoTitle = 0;
//				if(figCurve.getAutoTitle()){
//					autoTitle = 1;
//					RecordSet set = figCurve.getModel().getRecordSet();
//					KeyID key = new KeyID(set.getID());
//					int appID = 100000;
//					if(set instanceof MiddataSingleRecordSet){
//						appID = ((MiddataSingleRecordSet) set).getAppID();
//					}else if(set instanceof MiddataBatchRecordSet){
//						appID = ((MiddataBatchRecordSet) set).getAppID();
//					}
//					title = key.getDescrOfKeyID(Context.REALTIME, IDTool.getAppName(appID));
//					
//					
//				}
//				entityElement.setAttribute(GCurveConvertFromStr.ENTITY_AUTO, ""+autoTitle);
//				entityElement.setAttribute(GCurveConvertFromStr.ENTITY_TITLE, title);
//
//				// ����������
//				int y_type = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
//				entityElement.setAttribute(GCurveConvertFromStr.ENTITY_AXIS_Y_TYPE, String.valueOf(y_type));
//
//				// �Ƿ���ʾͼ��
//				int is_legend_show = GCurveConvertFromStr.BOOLEAN_TRUE;
//				if (figCurve.isLegendVisible() == false) {
//					is_legend_show = GCurveConvertFromStr.BOOLEAN_FALSE;
//				}
//				entityElement.setAttribute(GCurveConvertFromStr.ENTITY_LEGEND_SHOW, String.valueOf(is_legend_show));
//				
//				// ������ɫ
//				int lc = figCurve.getColor().getRGB();
//				entityElement.setAttribute(GCurveConvertFromStr.ENTITY_LINE_COLOR, String.valueOf(lc));
//
//				// �����߿�
//				int lineWidth = figCurve.getLineWidth();
//				entityElement.setAttribute(GCurveConvertFromStr.ENTITY_LINE_WIDTH, String.valueOf(lineWidth));
//
//				// ��������
//				int lineStyle = GCurveConvertFromStr.LINE_STYLE_SOLID;
//				float[] curveDash = figCurve.getDash();
//				if (curveDash == null) {
//					lineStyle = GCurveConvertFromStr.LINE_STYLE_SOLID;
//				} else if (curveDash == new float[] { 4.0f, 2.0f }) {
//					lineStyle = GCurveConvertFromStr.LINE_STYLE_DASH;
//				} else if (curveDash == new float[] { 1.0f, 2.0f }) {
//					lineStyle = GCurveConvertFromStr.LINE_STYLE_DOT;
//				} else if (curveDash == new float[] { 4.0f, 2.0f, 1.0f, 2.0f }) {
//					lineStyle = GCurveConvertFromStr.LINE_STYLE_DASH_DOT;
//				} else if (curveDash == new float[] { 4.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f }) {
//					lineStyle = GCurveConvertFromStr.LINE_STYLE_DASH_DOT_DOT;
//				}
//				entityElement.setAttribute(GCurveConvertFromStr.ENTITY_LINE_STYLE, String.valueOf(lineStyle));
//
//				// ���߻���
//				int ds = GCurveConvertFromStr.ENTITY_DRAW_STRATEGY_LINE;
//				DrawStrategy drawStrategy = figCurve.getDrawStrategy();
//				if (drawStrategy instanceof FigCurveDrawInLine) {
//					ds = GCurveConvertFromStr.ENTITY_DRAW_STRATEGY_LINE;
//				} else if (drawStrategy instanceof FigCurveDrawInSmooth) {
//					ds = GCurveConvertFromStr.ENTITY_DRAW_STRATEGY_BIAS;
//				} else if (drawStrategy instanceof FigCurveDrawInScatter) {
//					ds = GCurveConvertFromStr.ENTITY_DRAW_STRATEGY_LADDER;
//				} else if (drawStrategy instanceof FigCurveDrawInLadder) {
//					ds = GCurveConvertFromStr.ENTITY_DRAW_STRATEGY_DOT;
//				}
//				entityElement.setAttribute(GCurveConvertFromStr.ENTITY_DRAW_STRATEGY, String.valueOf(ds));
//
//				// �������
//				int index = i;
//				entityElement.setAttribute(GCurveConvertFromStr.ENTITY_INDEX, String.valueOf(index));
//				this.curveTable.put(index, figCurve);
//			}
//		}
//	}
//
//	/**
//	 * �������߶�̬����
//	 */
//	private void exportDynamic() {
//		for (int i = 0; i < curveTable.size(); i++) {
//			Element dynamicElement = new Element(GCurveConvertFromStr.DYNAMIC);
//			rootElement.addContent(dynamicElement);
//			
//			// ��ʼ
//			int startYear = GCurveConvertFromStr.THIS_YEAR;
//			int startMonth = GCurveConvertFromStr.THIS_MONTH;
//			int startDay = GCurveConvertFromStr.THIS_DAY;
//			int startHour = 0;
//			int startMinute = 0;
//			int startSecond = 0;
//			int startWeek = 0;
//			int startWeekDay =Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1;;
//			// ����
//			int endYear = GCurveConvertFromStr.THIS_YEAR;
//			int endMonth = GCurveConvertFromStr.THIS_MONTH;
//			int endDay = GCurveConvertFromStr.THIS_DAY;
//			int endHour = 0;
//			int endMinute = 0;
//			int endSecond = 0;
//			int endWeek = 0;
//			int endWeekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1;
//			
//			if(startWeekDay == 0){
//				startWeekDay = 7;
//			}
//			if(endWeekDay == 0){
//				endWeekDay = 7;
//			}
//			
//			// �������
//			int index = i;
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_INDEX, String.valueOf(index));
//
//			FigCurve figCurve = curveTable.get(index);
//
//			// Ӧ�ú�
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_APP, "100000");
//			// ȡֵ��ʽ
//			int mode = GCurveConvertFromStr.DYNAMIC_MODE_BEGIN;
//			// ��������
//			int period = -1;
//			// �������ڵ�λ
//			int query_unit = GCurveConvertFromStr.AXIS_X_TIME_UNIT_SECOND;
//			// �Ƿ����ʱ��
//			int is_abs_time = GCurveConvertFromStr.BOOLEAN_FALSE;
//			// ���ʱ��,���뵱ǰʱ����
//			int distant = 0;
//			// ���ʱ��,ʱ������λ
//			int distantUnit = GCurveConvertFromStr.AXIS_X_TIME_UNIT_DAY;
//			//���߾���
//			int decimal = GCurveConvertFromStr.DEFAULT_DECIMAL_VALUE;
//			//����������
//			int y_type = GCurveConvertFromStr.MULTI_AXIS_LEFT_1;
//			
//			int len = 1; //���ʱ���ʱ����
//			int len_unit =2;//���ʱ���ʱ���ȵ�λ
//			
//			// ���߶�̬����
//			String type = MidhsCurveStaticFinals.DAYLOAD;
//			RecordSet rSet = figCurve.getModel().getRecordSet();
//			if (rSet instanceof MidhsRecordSet) {
//				MidhsRecordSet hisRecordSet = (MidhsRecordSet) rSet;
//				decimal = hisRecordSet.getDecimal();
//				if (rSet instanceof MidhsSecondRecordSet) {
//					type = MidhsCurveStaticFinals.SECOND_5;
//				} else {
//					type = hisRecordSet.getType();
//				}
//				mode = hisRecordSet.getMode();
//				period = hisRecordSet.getSamplePeriod();
//				query_unit = hisRecordSet.getSamplePeriodType();
//				if (hisRecordSet.isAbsTime()) {
//					is_abs_time = GCurveConvertFromStr.BOOLEAN_TRUE;
//				}
//				distant = hisRecordSet.getDistant();
//				distantUnit = hisRecordSet.getDistantUnit();
//				len = hisRecordSet.getLen();
//				len_unit = hisRecordSet.getLenUnit();
//				if (is_abs_time == GCurveConvertFromStr.BOOLEAN_TRUE) {
//					startYear = hisRecordSet.getStartYear();
//					startMonth = hisRecordSet.getStartMonth();
//					startDay = hisRecordSet.getStartDay();
//					startHour = hisRecordSet.getStartHour();
//					startMinute = hisRecordSet.getStartMinute();
//					startSecond = hisRecordSet.getStartSecond();
//					startWeek = hisRecordSet.getStartWeek();
//					startWeekDay = hisRecordSet.getStartWeekDay();
//					
//					endYear = hisRecordSet.getEndYear();
//					endMonth = hisRecordSet.getEndMonth();
//					endDay = hisRecordSet.getEndDay();
//					endHour = hisRecordSet.getEndHour();
//					endMinute = hisRecordSet.getEndMinute();
//					endSecond = hisRecordSet.getEndSecond();
//					endWeek = hisRecordSet.getEndWeek();
//					endWeekDay = hisRecordSet.getEndWeekDay();
//					/*
//					FigAxisX axisX = chart.getDrawArea().getAxisX();
//					// int start = (int) axisX.getModel().getMinValue();
//					// int end = (int) axisX.getModel().getMaxValue();
//					ScaleLabelGenerator slg = axisX.getScaleLabelGenerator();
//					if (slg instanceof TimeGenerator) {
//						TimeGenerator timeSLG = (TimeGenerator) slg;
//						int length = timeSLG.getLength();
//
//						if (slg instanceof MinuteGenerator) {
//
//						} else if (slg instanceof HourGenerator) {
//							// 24Сʱ����
//							if (length == 24) {
//								// ����
//								if (distant == 0) {
//									startDay = GCurveConvertFromStr.THIS_DAY;
//									endDay = GCurveConvertFromStr.NEXT_DAY;
//								}
//								// ����
//								else if (distant == -1 && distantUnit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_DAY) {
//									startDay = GCurveConvertFromStr.LAST_DAY;
//									endDay = GCurveConvertFromStr.THIS_DAY;
//								}
//								// ǰ��
//								else if (distant == -2 && distantUnit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_DAY) {
//									startDay = GCurveConvertFromStr.LAST_TWO_DAY;
//									endDay = GCurveConvertFromStr.LAST_DAY;
//								}
//								// ����
//								else if (distant == 1 && distantUnit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_DAY) {
//									startDay = GCurveConvertFromStr.NEXT_DAY;
//									endDay = GCurveConvertFromStr.NEXT_TWO_DAY;
//								}
//								startHour = 0;
//								endHour = 0;
//								startMinute = 0;
//								endMinute = 0;
//								startSecond = 0;
//								endSecond = 0;
//							}
//						} else if (slg instanceof DayGenerator) {
//							// 1��������
//							if (length == 31) {
//								// ����
//								if (distant == 0) {
//									startMonth = GCurveConvertFromStr.THIS_MONTH;
//									endMonth = GCurveConvertFromStr.NEXT_ONE_MONTH;
//								}
//								// ����
//								else if (distant == -1 && distantUnit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_MONTH) {
//									startMonth = GCurveConvertFromStr.LAST_MONTH;
//									endMonth = GCurveConvertFromStr.THIS_MONTH;
//								}
//								// ǰ2����
//								else if (distant == -2 && distantUnit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_MONTH) {
//									startMonth = GCurveConvertFromStr.LAST_TWO_MONTH;
//									endMonth = GCurveConvertFromStr.LAST_MONTH;
//								}
//								// ǰ3����
//								else if (distant == -2 && distantUnit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_MONTH) {
//									startMonth = GCurveConvertFromStr.LAST_THREE_MONTH;
//									endMonth = GCurveConvertFromStr.LAST_TWO_MONTH;
//								}
//								// ����
//								else if (distant == -1 && distantUnit == GCurveConvertFromStr.AXIS_X_TIME_UNIT_MONTH) {
//									startMonth = GCurveConvertFromStr.NEXT_ONE_MONTH;
//									endMonth = GCurveConvertFromStr.NEXT_TWO_MONTH;
//								}
//								startDay = 1;
//								endDay = 1;
//								startHour = 0;
//								endHour = 0;
//								startMinute = 0;
//								endMinute = 0;
//								startSecond = 0;
//								endSecond = 0;
//							}
//						} else if (slg instanceof MonthGenerator) {
//							// ������
//							if (length == 12) {
//								// ����
//								if (distant == 0) {
//									startYear = GCurveConvertFromStr.THIS_YEAR;
//									endYear = GCurveConvertFromStr.NEXT_ONE_YEAR;
//								}
//								// ȥ��
//								else if (distant == -1) {
//									startYear = GCurveConvertFromStr.LAST_YEAR;
//									endYear = GCurveConvertFromStr.THIS_YEAR;
//								}
//								// ǰ��
//								else if (distant == -2) {
//									startYear = GCurveConvertFromStr.LAST_TWO_YEAR;
//									endYear = GCurveConvertFromStr.LAST_YEAR;
//								}
//								// ������
//								else if (distant == -3) {
//									startYear = GCurveConvertFromStr.LAST_THREE_YEAR;
//									endYear = GCurveConvertFromStr.LAST_TWO_YEAR;
//								}
//								// ������
//								else if (distant == -3) {
//									startYear = GCurveConvertFromStr.LAST_FOUR_YEAR;
//									endYear = GCurveConvertFromStr.LAST_THREE_YEAR;
//								}
//								// ����
//								else if (distant == 1) {
//									startYear = GCurveConvertFromStr.NEXT_ONE_YEAR;
//									endYear = GCurveConvertFromStr.NEXT_TWO_YEAR;
//								}
//
//								startMonth = 1;
//								endMonth = 1;
//								startDay = 1;
//								endDay = 1;
//								startHour = 0;
//								endHour = 0;
//								startMinute = 0;
//								endMinute = 0;
//								startSecond = 0;
//								endSecond = 0;
//							}
//						} else if (slg instanceof SecondGenerator) {
//							
//						}
//					}
//				*/} 
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_MODE, String.valueOf(mode));
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_PERIOD, String.valueOf(period));
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_QUERY_UNIT, String.valueOf(query_unit));
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_ABSTIME, String.valueOf(is_abs_time));
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_DISTANT, String.valueOf(distant));
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_DISTANT_UNIT, String.valueOf(distantUnit));
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_LEN, String.valueOf(len));
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_LEN_UNIT, String.valueOf(len_unit));
//			} else if (rSet instanceof MiddataSingleRecordSet) {
//				type = MidhsCurveStaticFinals.RTDB_SINGLE;
//				decimal = ((MiddataSingleRecordSet)rSet).getDecimal();
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_APP,  ((MiddataSingleRecordSet)rSet).getAppID()+"");
//			} else if (rSet instanceof MiddataBatchRecordSet){
//				MiddataBatchRecordSet midbatchSet = (MiddataBatchRecordSet) rSet;
//				type = GCurveConvertFromStr.DYNAMIC_DATASOURCE_TYPE_XY;
//				int x_space = midbatchSet.getXPace();
//				int appName = midbatchSet.getAppID();
//				int x_app = appName;
//				int y_space = midbatchSet.getYPace();
//				int x_direction = midbatchSet.getXDirection();
//				int y_direction = midbatchSet.getYDirection();
//				int pointMode = midbatchSet.getPointMode();
//				int point = midbatchSet.getPoint();
//				decimal = midbatchSet.getDecimal();
//				String xkeyID = midbatchSet.getXKeyID();
//				if(xkeyID.equals("-1")){
//					x_app =-1;
//				}
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_X_PACE, ""+x_space);
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_Y_PACE, ""+y_space);
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_X_DIRECTION, ""+x_direction);
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_Y_DIRECTION, ""+y_direction);
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_POINT, ""+point);
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_POINT_MODE, ""+pointMode);
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_APP, ""+appName);
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_X_KEY_ID, ""+xkeyID);
//				dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_X_APP,x_app+"");
//			}
//			
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_DATASOURCE_TYPE, type);
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_DECIMAL, ""+decimal);
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_Y_TYPE, ""+y_type);
//			// �豸�ؼ���
//			String keyid = rSet.getID();
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_KEYID, keyid);
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_START_YEAR, String.valueOf(startYear));
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_START_MONTH, String.valueOf(startMonth));
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_START_DAY, String.valueOf(startDay));
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_START_HOUR, String.valueOf(startHour));
//			dynamicElement
//					.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_START_MINUTE, String.valueOf(startMinute));
//			dynamicElement
//					.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_START_SECOND, String.valueOf(startSecond));
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_START_WEEK, String.valueOf(startWeek));
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_START_WEEK_DAY, String
//					.valueOf(startWeekDay));
//
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_END_YEAR, String.valueOf(endYear));
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_END_MONTH, String.valueOf(endMonth));
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_END_DAY, String.valueOf(endDay));
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_END_HOUR, String.valueOf(endHour));
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_END_MINUTE, String.valueOf(endMinute));
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_END_SECOND, String.valueOf(endSecond));
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_END_WEEK, String.valueOf(endWeek));
//			dynamicElement.setAttribute(GCurveConvertFromStr.DYNAMIC_TIME_END_WEEK_DAY, String.valueOf(endWeekDay));
//		}
//	}
//
//	/**
//	 * ��������ͼ������
//	 */
//	private void exportLegend() {
//		List legendList = chart.getSpecificFigs("Legend");
//		if (legendList != null) {
//			for (int i = 0; i < legendList.size(); i++) {
//				Element legendElement = new Element(GCurveConvertFromStr.LEGEND);
//				rootElement.addContent(legendElement);
//				FigLegend legend = (FigLegend) legendList.get(i);
//				// ���Ͻ�X����
//				int legendPosX = legend.getX();
//				legendElement.setAttribute(GCurveConvertFromStr.LEGEND_X, String.valueOf(legendPosX));
//
//				// ���Ͻ�Y����
//				int legendPosY = legend.getY();
//				legendElement.setAttribute(GCurveConvertFromStr.LEGEND_Y, String.valueOf(legendPosY));
//
//				Font legendFont = legend.getFont();
//				// ��������
//				String fontFamily = legendFont.getFamily();
//				legendElement.setAttribute(GCurveConvertFromStr.LEGEND_FONT_FAMILY, fontFamily);
//
//				// ��������
//				int fontWeight = legendFont.getStyle();
//				legendElement.setAttribute(GCurveConvertFromStr.LEGEND_FONT_WEIGHT, String.valueOf(fontWeight));
//
//				// �����С
//				int fontSize = legendFont.getSize();
//				legendElement.setAttribute(GCurveConvertFromStr.LEGEND_FONT_SIZE, String.valueOf(fontSize));
//
//				// ������ɫ
//				int font_color = legend.getFontColor().getRGB();
//				legendElement.setAttribute(GCurveConvertFromStr.LEGEND_FONT_COLOR, String.valueOf(font_color));
//
//				// Qtͼ��λ��
//				legendElement.setAttribute("pos", "3");
//
//			}
//		}
//	}
//
//	/**
//	 * ������������������
//	 */
//	private void exportTextField() {
//		List textFieldList = chart.getSpecificFigs("TextField");
//		if (textFieldList != null) {
//			for (int i = 0; i < textFieldList.size(); i++) {
//				Element textFieldElement = new Element(GCurveConvertFromStr.TEXT_FIELD);
//				rootElement.addContent(textFieldElement);
//
//				FigTextField textField = (FigTextField) textFieldList.get(i);
//
//				// ���Ͻ�X����
//				int x = textField.getX();
//				textFieldElement.setAttribute(GCurveConvertFromStr.TEXT_FIELD_X, String.valueOf(x));
//
//				// ���Ͻ�Y����
//				int y = textField.getY();
//				textFieldElement.setAttribute(GCurveConvertFromStr.TEXT_FIELD_Y, String.valueOf(y));
//
//				Font font = textField.getFont();
//				// ��������
//				String fontFamily = font.getFamily();
//				textFieldElement.setAttribute(GCurveConvertFromStr.TEXT_FIELD_FONT_FAMILY, fontFamily);
//
//				// ��������
//				int fontWeight = font.getStyle();
//				textFieldElement.setAttribute(GCurveConvertFromStr.TEXT_FIELD_FONT_WEIGHT, String.valueOf(fontWeight));
//
//				// �����С
//				int fontSize = font.getSize();
//				textFieldElement.setAttribute(GCurveConvertFromStr.TEXT_FIELD_FONT_SIZE, String.valueOf(fontSize));
//
//				// ������ɫ
//				int font_color = textField.getFontColor().getRGB();
//				textFieldElement.setAttribute(GCurveConvertFromStr.TEXT_FIELD_FONT_COLOR, String.valueOf(font_color));
//
//				// ����
//				int type = 255;
//				textFieldElement.setAttribute(GCurveConvertFromStr.TEXT_FIELD_TYPE, String.valueOf(type));
//
//				// ����
//				String content = textField.getContent();
//				textFieldElement.setAttribute(GCurveConvertFromStr.TEXT_FIELD_CONTENT, content);
//			}
//		}
//	}
//
//	/**
//	 * ������������������
//	 */
//	private void exportDataField() {
//		List dataFieldList = chart.getSpecificFigs("DataField");
//		if (dataFieldList != null) {
//			for (int i = 0; i < dataFieldList.size(); i++) {
//				Element dataFieldElement = new Element(GCurveConvertFromStr.DATA_FIELD);
//				rootElement.addContent(dataFieldElement);
//
//				FigDataField dataField = (FigDataField) dataFieldList.get(i);
//
//				// ���Ͻ�X����
//				int x = dataField.getX();
//				dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_X, String.valueOf(x));
//
//				// ���Ͻ�Y����
//				int y = dataField.getY();
//				dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_Y, String.valueOf(y));
//
//				Font font = dataField.getFont();
//				// ��������
//				String fontFamily = font.getFamily();
//				dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_FONT_FAMILY, fontFamily);
//
//				// ��������
//				int fontWeight = font.getStyle();
//				dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_FONT_WEIGHT, String.valueOf(fontWeight));
//
//				// �����С
//				int fontSize = font.getSize();
//				dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_FONT_SIZE, String.valueOf(fontSize));
//
//				// ������ɫ
//				int font_color = dataField.getFontColor().getRGB();
//				dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_FONT_COLOR, String.valueOf(font_color));
//
//				// ����
//				RecordSet rSet = dataField.getModel().getRecordSet();
//				if (rSet instanceof ReRecordSet) {
//					// ����������
//					dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//							.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RDB));
//					if (rSet instanceof DFReRecordSet) {
//						DFReRecordSet dfSet = (DFReRecordSet) rSet;
//						// �����������
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_INDEX, String.valueOf(dfSet
//								.getIndex()));
//						// ��������
//						String sql = dfSet.getSqlStatement();
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_CONTENT, sql);
//					} else {
//						ReRecordSet reSet = (ReRecordSet) rSet;
//						// �����������
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_INDEX,"" + GCurveConvertFromStr.DATA_FIELD_INDEX_NULL);
//						// ��������
//						String sql = reSet.getStatement();
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_CONTENT, sql);
//					}
//				} else {
//					// �����������
//					for (int j = 0; j < this.curveTable.size(); j++) {
//						FigCurve figCurve = this.curveTable.get(j);
//						if (figCurve.getTitle().equals(dataField.getModel().getCurveName())) {
//							int index = j;
//							dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_INDEX, String.valueOf(index));
//							break;
//						}
//					}
//					if(rSet instanceof RTRecordSet){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTDB));
//						RTRecordSet rset = (RTRecordSet) rSet;
//						if(rset.getCurveName() != null){
//							dataFieldElement.setAttribute("curveName",rset.getCurveName());
//						}
//						dataFieldElement.setAttribute("serverName",rset.getServerName());
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DYNAMIC_APP,IDTool.getAppID(rset.getAppName())+"");
//						dataFieldElement.setAttribute("systemName",rset.getSystemName());
//						dataFieldElement.setAttribute("dbName",rset.getDBName());
//						dataFieldElement.setAttribute("tableName",rset.getTableName());
//						dataFieldElement.setAttribute("id",rset.getID());
//						dataFieldElement.setAttribute("id_type","");
//						dataFieldElement.setAttribute("propertyName",rset.getPropertyName());
//						dataFieldElement.setAttribute("startPoint",rset.getStartPoint());
//						dataFieldElement.setAttribute("sum",rset.getSum());
//						dataFieldElement.setAttribute("pression",rset.getPression());
//						dataFieldElement.setAttribute("unit",rset.getUnit());
//					}
//					// ����������
//					if (dataField.getModel().isTime()) {
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_VIEW_DATE));
//					} else if (dataField.getModel().isCurveDate()) {
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_DATE));
//					} else if (dataField.getModel().isMax()) {
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_MAX));
//					} else if (dataField.getModel().isMaxTime()) {
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_MAX_TIME));
//					} else if (dataField.getModel().isMin()) {
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_MIN));
//					} else if (dataField.getModel().isMinTime()) {
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_MIN_TIME));
//					} else if (dataField.getModel().isAverageValue()) {
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_AVE));
//					} else if (dataField.getModel().isCurrentValue()) {
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_CURRENT));
//					}else if(dataField.getModel().isIntegralElectricityQuantity()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_INTEGRAL_ELECTRICITY_QUANTITY));
//					}else if(dataField.getModel().isRtnconDayCn()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_DAY_CN));
//					}else if(dataField.getModel().isRtnconDayMr()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_DAY_MR));
//					}else if(dataField.getModel().isRtnconDaySn()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_DAY_SN));
//					}else if(dataField.getModel().isRtnconDaySr()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_DAY_SR));
//					}else if(dataField.getModel().isRtnconMonthCn()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_MONTH_CN));
//					}else if(dataField.getModel().isRtnconMonthMr()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_MONTH_MR));
//					}else if(dataField.getModel().isRtnconMonthSn()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_MONTH_SN));
//					}else if(dataField.getModel().isRtnconMonthSr()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_MONTH_SR));
//					}else if(dataField.getModel().isRtnconYearCn()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_YEAR_CN));
//					}else if(dataField.getModel().isRtnconYearMr()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_YEAR_MR));
//					}else if(dataField.getModel().isRtnconYearSn()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_YEAR_SN));
//					}else if(dataField.getModel().isRtnconYearSr()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_RTNCON_YEAR_SR));
//					}else if(dataField.getModel().isBigRtnconDayCn()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_DAY_CN));
//					}else if(dataField.getModel().isBigRtnconDayMr()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_DAY_MR));
//					}else if(dataField.getModel().isBigRtnconDaySn()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_DAY_SN));
//					}else if(dataField.getModel().isBigRtnconDaySr()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_DAY_SR));
//					}else if(dataField.getModel().isBigRtnconMonthCn()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_MONTH_CN));
//					}else if(dataField.getModel().isBigRtnconMonthMr()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_MONTH_MR));
//					}else if(dataField.getModel().isBigRtnconMonthSn()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_MONTH_SN));
//					}else if(dataField.getModel().isBigRtnconMonthSr()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_MONTH_SR));
//					}else if(dataField.getModel().isBigRtnconYearCn()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_YEAR_CN));
//					}else if(dataField.getModel().isBigRtnconYearMr()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_YEAR_MR));
//					}else if(dataField.getModel().isBigRtnconYearSn()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_YEAR_SN));
//					}else if(dataField.getModel().isBigRtnconYearSr()){
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_TYPE, String
//								.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_BIG_RTNCON_YEAR_SR));
//					}else if (dataField.getModel().isFhl()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_FHL));
//					} else if (dataField.getModel().isAVGFH()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_AVG_FH));
//					} else if (dataField.getModel().isIntFHL()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_INT_FHL));
//					} else if (dataField.getModel().isIntMax()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_INT_MAX));
//					} else if (dataField.getModel().isIntMin()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_INT_MIN));
//					} else if (dataField.getModel().isAMMax()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_AM_MAX));
//					} else if (dataField.getModel().isAMMin()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_AM_MIN));
//					} else if (dataField.getModel().isPMMax()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_PM_MAX));
//					} else if (dataField.getModel().isPMMin()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_PM_MIN));
//					} else if (dataField.getModel().isNGMax()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_NG_MAX));
//					} else if (dataField.getModel().isNGMin()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_NG_MIN));
//					} else if (dataField.getModel().isIntMaxTime()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_INT_MAX_TIME));
//					} else if (dataField.getModel().isIntMinTime()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_INT_MIN_TIME));
//					} else if (dataField.getModel().isAMMaxTime()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_AM_MAX_TIME));
//					} else if (dataField.getModel().isAMMinTime()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_AM_MIN_TIME));
//					} else if (dataField.getModel().isPMMaxTime()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_PM_MAX_TIME));
//					} else if (dataField.getModel().isPMMinTime()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_PM_MIN_TIME));
//					} else if (dataField.getModel().isNGMaxTime()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_NG_MAX_TIME));
//					} else if (dataField.getModel().isNGMinTime()) {
//						dataFieldElement
//								.setAttribute(
//										GCurveConvertFromStr.DATA_FIELD_TYPE,
//										String.valueOf(GCurveConvertFromStr.DATA_FIELD_TYPE_NG_MIN_TIME));
//					}
//					
//					if (!dataField.getModel().isMaxTime() && !dataField.getModel().isMinTime()
//							&& !dataField.getModel().isCurveDate() && !dataField.getModel().isTime()) {
//						// ���ݾ���
//						int decimal = dataField.getModel().getValuePresision();
//						dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_DECIMAL, String.valueOf(decimal));
//						// ��λ
//						String unit = dataField.getModel().getValueUnit();
//						if (unit != null && unit.length() != 0) {
//							dataFieldElement.setAttribute(GCurveConvertFromStr.DATA_FIELD_UNIT, unit);
//						}
//					}
//				}
//			}
//		}
//	}
//
//	/**
//	 * ������ҳ��
//	 */
//	private void exportTurnOverField() {
//		List turnOverFieldList = chart.getSpecificFigs("TurnOverField");
//		if (turnOverFieldList != null) {
//			for (int i = 0; i < turnOverFieldList.size(); i++) {
//				Element turnOverFieldElement = new Element(GCurveConvertFromStr.TURNOVER_FIELD);
//				rootElement.addContent(turnOverFieldElement);
//
//				TurnOverField turnOverField = (TurnOverField) turnOverFieldList.get(i);
//
//				// ���Ͻ�X����
//				int x = turnOverField.getX();
//				turnOverFieldElement.setAttribute(GCurveConvertFromStr.TURNOVER_FIELD_X, String.valueOf(x));
//				// ���Ͻ�Y����
//				int y = turnOverField.getY();
//				turnOverFieldElement.setAttribute(GCurveConvertFromStr.TURNOVER_FIELD_Y, String.valueOf(y));
//				// ��ɫ
//				int color = turnOverField.getColor().getRGB();
//				turnOverFieldElement.setAttribute(GCurveConvertFromStr.TURNOVER_FIELD_COLOR, String.valueOf(color));
//				// ���
//				int w = turnOverField.getW();
//				turnOverFieldElement.setAttribute(GCurveConvertFromStr.TURNOVER_FIELD_WIDTH, String.valueOf(w));
//				// �߶�
//				int h = turnOverField.getH();
//				turnOverFieldElement.setAttribute(GCurveConvertFromStr.TURNOVER_FIELD_HEIGHT, String.valueOf(h));
//				// ����
//				int type = GCurveConvertFromStr.TURNOVER_FIELD_TYPE_PRE;
//				if (turnOverField instanceof NextTurnOverField) {
//					type = GCurveConvertFromStr.TURNOVER_FIELD_TYPE_NEXT;
//				}
//				turnOverFieldElement.setAttribute(GCurveConvertFromStr.TURNOVER_FIELD_TYPE, String.valueOf(type));
//			}
//		}
//	}
//
//	/**
//	 * �������߽���������
//	 */
//	private void exportFocusField() {
//
//	}
//
//	public void exportTime() {
//
//	}
//}
