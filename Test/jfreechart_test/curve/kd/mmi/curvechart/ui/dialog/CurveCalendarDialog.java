package curve.kd.mmi.curvechart.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.CustomChartPanel;
import org.jfree.data.general.Series;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Month;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.Year;
import org.jfree.data.xy.custom.CustomTimeSeries;

import support.PanelCalender;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class CurveCalendarDialog extends JDialog implements ActionListener {

	JPanel panel1 = new JPanel();
	PanelCalender calendarPane = null;
	JPanel panel3 = new JPanel();
	public JButton buttonOK = new JButton("确定");
	public JButton buttonCancle = new JButton("取消");
	BorderLayout borderLayout1 = new BorderLayout();

	private boolean confirmed; // 用户是否点击了确定

	private CustomChartPanel chartPanel = null;
	Series series = null;
	Class timePeriodClass  = Minute.class;
	int type = 1;
	public CurveCalendarDialog(Frame frame, String title, boolean modal,
			CustomChartPanel chartPanel,Series series) {
		super(frame, title, modal);
		this.series = series;
		this.chartPanel = chartPanel;
		try {
			jbInit();
			pack();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 通过CurveViewPanel获得.
	 * @param frame
	 * @param title
	 * @param modal
	 * @param viewer
	 */
	public CurveCalendarDialog(Dialog dialog, String title, boolean modal,
			CustomChartPanel chartPanel, Series series) {
		super(dialog, title, modal);
		// curve_bug_11 mengxin 2008/08/25
		this.chartPanel = chartPanel;
		this.series = series;
		try {
			jbInit();
			pack();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
//	/**
//	 * 通过CurveViewPanel获得.
//	 *
//	 * @param frame
//	 * @param title
//	 * @param modal
//	 * @param viewer
//	 */
//	public CurveCalendarDialog(Frame frame, String title, boolean modal,
//			CurveViewPanel viewPanel, Series series) {
//		super(frame, title, modal);
//		// curve_bug_11 mengxin 2008/08/25
//		this.series = series;
//		try {
//			jbInit();
//			pack();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}

	public void show() {
		confirmed = false;
		super.show();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonOK) {
			confirmed = true;
		} else if (e.getSource() == buttonCancle) {
			confirmed = false;
		}
		dispose();
	}

	private void jbInit() throws Exception {
		// curve_bug_11 mengxin 2008/08/25
		if (series instanceof CustomTimeSeries) {
			Class timeperClass = ((CustomTimeSeries) series).getTimePeriodClass();
			if (timeperClass == Quarter.class) {
				type = 3;
			} else if (timeperClass == Year.class) {
				type = 4;
			} else if (timeperClass == Month.class) {
				type = 2;
			}
		}
		calendarPane = new PanelCalender(type);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		panel1.setLayout(borderLayout1);
		panel1.add(calendarPane, BorderLayout.CENTER);
		panel1.add(panel3, BorderLayout.SOUTH);
		panel3.add(buttonOK);
		panel3.add(buttonCancle);
		getContentPane().add(panel1);
		buttonOK.addActionListener(this);
		buttonCancle.addActionListener(this);
	}

	public Date getDate() {
		// mengxin 2005-11-02 tj_curve_33 根据曲线类型返回不同的时间
		int year = calendarPane.getYear();
		int month = calendarPane.getMonth();
		int day = calendarPane.getDate();
		GregorianCalendar grd = null;
		// curve_bug_11 mengxin 2008/08/25
		if (type == 2) {
			grd = new GregorianCalendar(year, month - 1, 1, 0, 0, 0);
		} else if (type == 3) {
			int quarter = calendarPane.getQuarter();
			if (quarter == 1) {
				grd = new GregorianCalendar(year, 1, 1, 0, 0, 0);
			} else if (quarter == 2) {
				grd = new GregorianCalendar(year, 4, 1, 0, 0, 0);
			} else if (quarter == 3) {
				grd = new GregorianCalendar(year, 7, 1, 0, 0, 0);
			} else {
				grd = new GregorianCalendar(year, 10, 1, 0, 0, 0);
			}
		} else if (type == 4) {
			grd = new GregorianCalendar(year, 1, 1, 0, 0, 0);
		} else {
			grd = new GregorianCalendar(year, month - 1, day, calendarPane
					.getHour(), calendarPane.getMin(), calendarPane.getSec());
		}

		Date date = grd.getTime();
		return date;
	}

	public String getMark() {
		return calendarPane.getYear() + "-" + calendarPane.getMonth() + "-"
				+ calendarPane.getDate();
	}

	// LangMeng 2003-9-6
	public void setDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		calendarPane.setYear(cal.get(Calendar.YEAR));
		// mengxin 2005-11-01 tj_curve_33 根据月份决定季度
		// curve_bug_11 mengxin 2008/08/25
		if (type == 3) {
			int mon = cal.get(Calendar.MONTH) + 1;
			int quarter = 0;
			if (mon < 4) {
				quarter = 1;
			} else if (mon < 7) {
				quarter = 2;
			} else if (mon < 10) {
				quarter = 3;
			} else {
				quarter = 4;
			}
			calendarPane.setQuarter(quarter);
		} else {
			calendarPane.setMonth(cal.get(Calendar.MONTH) + 1);
			calendarPane.setDate(cal.get(Calendar.DATE));
		}
		calendarPane.setHour(cal.get(Calendar.HOUR_OF_DAY));
		calendarPane.setMinute(cal.get(Calendar.MINUTE));
		calendarPane.setSecond(cal.get(Calendar.SECOND));
	}

	
	/**
	 * 显示选择日期的对话框并返回用户选择的日期
	 * 
	 * @param c
	 *            父组件
	 * @param title
	 *            对话框标题
	 * @param initDate
	 *            初始时的日期，如果为null则取当前时间
	 * @param showHourMinuteSecond
	 *            是否显示时、分、秒的输入框
	 * @param viewer
	 * 
	 * @return 用户选中的日期和时间，如果用户点击取消关闭对话框返回null
	 * 
	 * @sign curve_bug_11
	 * @modify mengxin 2008/08/25
	 **/
	public static Date showDateDialog(Component c, String title, Date initDate,
			boolean showHourMinuteSecond, CustomChartPanel chartPanel, Series series) {
		return showDateDialog(c, title, initDate, showHourMinuteSecond, null, chartPanel, series);
	}

	
	/**
	 * 显示选择日期的对话框并返回用户选择的日期
	 * 
	 * @param c
	 *            父组件
	 * @param title
	 *            对话框标题
	 * @param initDate
	 *            初始时的日期，如果为null则取当前时间
	 * @param showHourMinuteSecond
	 *            是否显示时、分、秒的输入框
	 * @param pos
	 *            对话框弹出的位置(屏幕坐标)
	 * @param viewer 
	 * 
	 * @return 用户选中的日期和时间，如果用户点击取消关闭对话框返回null
	 * 
	 * @sign curve_bug_11
	 * @modify mengxin 2008/08/25
	 */
	public static Date showDateDialog(Component c, String title, Date initDate,
			boolean showHourMinuteSecond, Point pos, CustomChartPanel chartPanel, Series series) {
		Window w = null;
		if (c instanceof Dialog || c instanceof Frame) {
			w = (Window) c;
		} else {
			w = (Window) SwingUtilities.getAncestorOfClass(Window.class, c);
		}
		
		CurveCalendarDialog dialog = null;
		if (w instanceof Dialog) {
			dialog = new CurveCalendarDialog((Dialog) w, title, true, chartPanel, series);
		} else if (w instanceof Frame) {
			dialog = new CurveCalendarDialog((Frame) w, title, true, chartPanel, series);
		} else {
			dialog = new CurveCalendarDialog((Frame) null, title, true, chartPanel, series);
		}
		initDate = (initDate == null ? new Date() : initDate);
		dialog.setDate(initDate);
		dialog.calendarPane.setHourMinuteSecondVisible(showHourMinuteSecond);

		dialog.pack();
		if (pos != null) {
			dialog.setLocation(pos);
		} else {
			dialog.setLocationRelativeTo(c);
		}
		dialog.show();

		if (dialog.confirmed) {
			return dialog.getDate();
		} else {
			return null;
		}
	}

}
