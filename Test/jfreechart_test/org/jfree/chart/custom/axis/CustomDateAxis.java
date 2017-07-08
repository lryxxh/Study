package org.jfree.chart.custom.axis;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Observer;
import java.util.TimeZone;

import javax.swing.Timer;

import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTick;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.axis.Tick;
import org.jfree.chart.axis.TickType;
import org.jfree.chart.axis.Timeline;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.time.DateRange;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.custom.CustomTimeSeries;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;

import curve.kd.mmi.curve.jfreechart.refresher.RefreshObservable;
import curve.kd.mmi.curve.jfreechart.refresher.TurnDateObservable;

/**
 * The base class for axes that display dates. You will find it easier to
 * understand how this axis works if you bear in mind that it really
 * displays/measures integer (or long) data, where the integers are milliseconds
 * since midnight, 1-Jan-1970. When displaying tick labels, the millisecond
 * values are converted back to dates using a <code>DateFormat</code> instance.
 * <P>
 * You can also create a {@link org.jfree.chart.axis.Timeline} and supply in the
 * constructor to create an axis that only contains certain domain values. For
 * example, this allows you to create a date axis that only contains working
 * days.
 */
public class CustomDateAxis extends DateAxis implements Cloneable, Serializable {

	private DateRange initRange = DEFAULT_DATE_RANGE;

	/**
	 * Tick marks can be displayed at the start or the middle of the time
	 * period.
	 */
	private DateTickMarkPosition tickMarkPosition = DateTickMarkPosition.START;

	List<Observer> observers = new ArrayList<Observer>();

	TurnDateObservable observable = new TurnDateObservable();

	/**
	 * A timeline that includes all milliseconds (as defined by
	 * <code>java.util.Date</code>) in the real time line.
	 */
	private static class DefaultTimeline implements Timeline, Serializable {

		/**
		 * Converts a millisecond into a timeline value.
		 * 
		 * @param millisecond
		 *            the millisecond.
		 * 
		 * @return The timeline value.
		 */
		public long toTimelineValue(long millisecond) {
			return millisecond;
		}

		/**
		 * Converts a date into a timeline value.
		 * 
		 * @param date
		 *            the domain value.
		 * 
		 * @return The timeline value.
		 */
		public long toTimelineValue(Date date) {
			return date.getTime();
		}

		/**
		 * Converts a timeline value into a millisecond (as encoded by
		 * <code>java.util.Date</code>).
		 * 
		 * @param value
		 *            the value.
		 * 
		 * @return The millisecond.
		 */
		public long toMillisecond(long value) {
			return value;
		}

		/**
		 * Returns <code>true</code> if the timeline includes the specified
		 * domain value.
		 * 
		 * @param millisecond
		 *            the millisecond.
		 * 
		 * @return <code>true</code>.
		 */
		public boolean containsDomainValue(long millisecond) {
			return true;
		}

		/**
		 * Returns <code>true</code> if the timeline includes the specified
		 * domain value.
		 * 
		 * @param date
		 *            the date.
		 * 
		 * @return <code>true</code>.
		 */
		public boolean containsDomainValue(Date date) {
			return true;
		}

		/**
		 * Returns <code>true</code> if the timeline includes the specified
		 * domain value range.
		 * 
		 * @param from
		 *            the start value.
		 * @param to
		 *            the end value.
		 * 
		 * @return <code>true</code>.
		 */
		public boolean containsDomainRange(long from, long to) {
			return true;
		}

		/**
		 * Returns <code>true</code> if the timeline includes the specified
		 * domain value range.
		 * 
		 * @param from
		 *            the start date.
		 * @param to
		 *            the end date.
		 * 
		 * @return <code>true</code>.
		 */
		public boolean containsDomainRange(Date from, Date to) {
			return true;
		}

		/**
		 * Tests an object for equality with this instance.
		 * 
		 * @param object
		 *            the object.
		 * 
		 * @return A boolean.
		 */
		public boolean equals(Object object) {
			if (object == null) {
				return false;
			}
			if (object == this) {
				return true;
			}
			if (object instanceof DefaultTimeline) {
				return true;
			}
			return false;
		}
	}

	/** A static default timeline shared by all standard DateAxis */
	private static final Timeline DEFAULT_TIMELINE = new DefaultTimeline();

	/** The time zone for the axis. */
	private TimeZone timeZone;

	/**
	 * The locale for the axis (<code>null</code> is not permitted).
	 * 
	 * @since 1.0.11
	 */
	private Locale locale;

	/** Our underlying timeline. */
	private Timeline timeline;

	public CustomDateAxis() {
		this(null);
	}

	/**
	 * Creates a date axis with no label.
	 */
	public CustomDateAxis(List<Observer> observers) {
		this(null, observers);
	}

	/**
	 * Creates a date axis with the specified label.
	 * 
	 * @param label
	 *            the axis label (<code>null</code> permitted).
	 */
	public CustomDateAxis(String label, List<Observer> observers) {
		this(label, TimeZone.getDefault(), observers);
	}

	/**
	 * Creates a date axis. A timeline is specified for the axis. This allows
	 * special transformations to occur between a domain of values and the
	 * values included in the axis.
	 * 
	 * @see org.jfree.chart.axis.SegmentedTimeline
	 * 
	 * @param label
	 *            the axis label (<code>null</code> permitted).
	 * @param zone
	 *            the time zone.
	 * 
	 * @deprecated From 1.0.11 onwards, use
	 *             {@link #DateAxis(String, TimeZone, Locale)} instead, to
	 *             explicitly set the locale.
	 */
	public CustomDateAxis(String label, TimeZone zone, List<Observer> observers) {
		this(label, zone, Locale.getDefault(), observers);
	}

	/**
	 * Creates a date axis. A timeline is specified for the axis. This allows
	 * special transformations to occur between a domain of values and the
	 * values included in the axis.
	 * 
	 * @see org.jfree.chart.axis.SegmentedTimeline
	 * 
	 * @param label
	 *            the axis label (<code>null</code> permitted).
	 * @param zone
	 *            the time zone.
	 * @param locale
	 *            the locale (<code>null</code> not permitted).
	 * 
	 * @since 1.0.11
	 */
	public CustomDateAxis(String label, TimeZone zone, Locale locale,
			List<Observer> observers) {
		super(label, zone, locale);
		setTickUnit(DateAxis.DEFAULT_DATE_TICK_UNIT, false, false);
		setAutoRangeMinimumSize(DEFAULT_AUTO_RANGE_MINIMUM_SIZE_IN_MILLISECONDS);
		setRange(DEFAULT_DATE_RANGE, false, false);
		this.timeZone = zone;
		this.locale = locale;
		this.timeline = DEFAULT_TIMELINE;
		this.observers = observers;
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	public double valueToJava2DCustom(double value, Rectangle2D area,
			RectangleEdge edge, int series) {

		value = this.timeline.toTimelineValue((long) value);

		DateRange range = (DateRange) getRange();
		XYPlot plot = (XYPlot) getPlot();
		TimeSeriesCollection dataset = (TimeSeriesCollection) plot.getDataset();
		long startTime = (long) ((CustomTimeSeries) dataset.getSeries(series))
				.getInitStartXValue();
		long lowDiff = initRange.getLowerMillis() - startTime;
		if (lowDiff > 0) {
			long seriesLength = (long) initRange.getLength();
			long seriesBegin = range.getLowerMillis() - lowDiff;
			long seriesEnd = seriesBegin + seriesLength;
			range = new DateRange(new Date(seriesBegin), new Date(seriesEnd));
		}

		double axisMin = this.timeline.toTimelineValue(range.getLowerMillis());
		double axisMax = this.timeline.toTimelineValue(range.getUpperMillis());
		double result = 0.0;
		if (RectangleEdge.isTopOrBottom(edge)) {
			double minX = area.getX();
			double maxX = area.getMaxX();
			if (isInverted()) {
				result = maxX + ((value - axisMin) / (axisMax - axisMin))
						* (minX - maxX);
			} else {
				result = minX + ((value - axisMin) / (axisMax - axisMin))
						* (maxX - minX);
			}
		} else if (RectangleEdge.isLeftOrRight(edge)) {
			double minY = area.getMinY();
			double maxY = area.getMaxY();
			if (isInverted()) {
				result = minY
						+ (((value - axisMin) / (axisMax - axisMin)) * (maxY - minY));
			} else {
				result = maxY
						- (((value - axisMin) / (axisMax - axisMin)) * (maxY - minY));
			}
		}
		return result;

	}

	public double java2DToValueCustom(double java2DValue, Rectangle2D area,
			RectangleEdge edge, int series) {

		DateRange range = (DateRange) getRange();
		long lowBound = (long) initRange.getLowerMillis();
		XYPlot plot = (XYPlot) getPlot();
		TimeSeriesCollection dataset = (TimeSeriesCollection) plot.getDataset();
		long startTime = (long) ((CustomTimeSeries) dataset.getSeries(series))
				.getInitStartXValue();
		if (lowBound != startTime) {
			long lowDiff = range.getLowerMillis() - lowBound;
			long seriesLength = (long) range.getLength();
			long seriesBegin = startTime + lowDiff;
			long seriesEnd = seriesBegin + seriesLength;
			range = new DateRange(new Date(seriesBegin), new Date(seriesEnd));
		}

		double axisMin = this.timeline.toTimelineValue(range.getLowerMillis());
		double axisMax = this.timeline.toTimelineValue(range.getUpperMillis());

		double min = 0.0;
		double max = 0.0;
		if (RectangleEdge.isTopOrBottom(edge)) {
			min = area.getX();
			max = area.getMaxX();
		} else if (RectangleEdge.isLeftOrRight(edge)) {
			min = area.getMaxY();
			max = area.getY();
		}

		double result;
		if (isInverted()) {
			result = axisMax
					- ((java2DValue - min) / (max - min) * (axisMax - axisMin));
		} else {
			result = axisMin
					+ ((java2DValue - min) / (max - min) * (axisMax - axisMin));
		}

		return this.timeline.toMillisecond((long) result);
	}

	public List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea,
			RectangleEdge edge) {

		List result = new java.util.ArrayList();

		Font tickLabelFont = getTickLabelFont();
		g2.setFont(tickLabelFont);

		if (isAutoTickUnitSelection()) {
			selectAutoTickUnit(g2, dataArea, edge);
		}

		DateTickUnit unit = getTickUnit();
		Date tickDate = calculateLowestVisibleTickValue(unit);
		Date upperDate = getMaximumDate();

		while (tickDate.before(upperDate) || tickDate.equals(upperDate)) {
			// could add a flag to make the following correction optional...
			tickDate = correctTickDateForPosition(tickDate, unit,
					this.tickMarkPosition);

			long lowestTickTime = tickDate.getTime();
			long distance = unit.addToDate(tickDate, this.timeZone).getTime()
					- lowestTickTime;
			int minorTickSpaces = getMinorTickCount();
			if (minorTickSpaces <= 0) {
				minorTickSpaces = unit.getMinorTickCount();
			}
			for (int minorTick = 1; minorTick < minorTickSpaces; minorTick++) {
				long minorTickTime = lowestTickTime - distance * minorTick
						/ minorTickSpaces;
				if (minorTickTime > 0 && getRange().contains(minorTickTime)
						&& (!isHiddenValue(minorTickTime))) {
					result.add(new DateTick(TickType.MINOR, new Date(
							minorTickTime), "", TextAnchor.TOP_CENTER,
							TextAnchor.CENTER, 0.0));
				}
			}

			if (!isHiddenValue(tickDate.getTime())) {
				// work out the value, label and position
				String tickLabel;
				DateFormat formatter = getDateFormatOverride();
				if (formatter != null) {
					tickLabel = formatter.format(tickDate);
				} else {
					tickLabel = getTickUnit().dateToString(tickDate);
				}
				TextAnchor anchor = null;
				TextAnchor rotationAnchor = null;
				double angle = 0.0;
				if (isVerticalTickLabels()) {
					anchor = TextAnchor.CENTER_RIGHT;
					rotationAnchor = TextAnchor.CENTER_RIGHT;
					if (edge == RectangleEdge.TOP) {
						angle = Math.PI / 2.0;
					} else {
						angle = -Math.PI / 2.0;
					}
				} else {
					if (edge == RectangleEdge.TOP) {
						anchor = TextAnchor.BOTTOM_CENTER;
						rotationAnchor = TextAnchor.BOTTOM_CENTER;
					} else {
						anchor = TextAnchor.TOP_CENTER;
						rotationAnchor = TextAnchor.TOP_CENTER;
					}
				}

				Tick tick = new DateTick(tickDate, tickLabel, anchor,
						rotationAnchor, angle);
				result.add(tick);

				long currentTickTime = tickDate.getTime();
				tickDate = unit.addToDate(tickDate, this.timeZone);
				long nextTickTime = tickDate.getTime();
				for (int minorTick = 1; minorTick < minorTickSpaces; minorTick++) {
					long minorTickTime = currentTickTime
							+ (nextTickTime - currentTickTime) * minorTick
							/ minorTickSpaces;
					if (getRange().contains(minorTickTime)
							&& (!isHiddenValue(minorTickTime))) {
						result.add(new DateTick(TickType.MINOR, new Date(
								minorTickTime), "", TextAnchor.TOP_CENTER,
								TextAnchor.CENTER, 0.0));
					}
				}

			} else {
				tickDate = unit.rollDate(tickDate, this.timeZone);
				continue;
			}

		}
		return result;

	}

	private Date correctTickDateForPosition(Date time, DateTickUnit unit,
			DateTickMarkPosition position) {
		Date result = time;
		switch (unit.getUnit()) {
		case (DateTickUnit.MILLISECOND):
		case (DateTickUnit.SECOND):
		case (DateTickUnit.MINUTE):
		case (DateTickUnit.HOUR):
		case (DateTickUnit.DAY):
			break;
		case (DateTickUnit.MONTH):
			result = calculateDateForPosition(new Month(time, this.timeZone,
					this.locale), position);
			break;
		case (DateTickUnit.YEAR):
			result = calculateDateForPosition(new Year(time, this.timeZone,
					this.locale), position);
			break;

		default:
			break;
		}
		return result;
	}

	private Date calculateDateForPosition(RegularTimePeriod period,
			DateTickMarkPosition position) {

		if (position == null) {
			throw new IllegalArgumentException("Null 'position' argument.");
		}
		Date result = null;
		if (position == DateTickMarkPosition.START) {
			result = new Date(period.getFirstMillisecond());
		} else if (position == DateTickMarkPosition.MIDDLE) {
			result = new Date(period.getMiddleMillisecond());
		} else if (position == DateTickMarkPosition.END) {
			result = new Date(period.getLastMillisecond());
		}
		return result;

	}

	public Date calculateLowestVisibleTickValue(DateTickUnit unit) {
		return getMinimumDate();
	}

	/**
	 * Rescales the axis to ensure that all data is visible.
	 */
	protected void autoAdjustRange() {

		Plot plot = getPlot();

		if (plot == null) {
			return; // no plot, no data
		}

		if (!isAutoTickUnitSelection()) {
			setRange(initRange);
			return;
		}
		if (plot instanceof ValueAxisPlot) {
			ValueAxisPlot vap = (ValueAxisPlot) plot;

			Range r = vap.getDataRange(this);
			if (r == null) {
				if (this.timeline instanceof SegmentedTimeline) {
					// Timeline hasn't method getStartTime()
					r = new DateRange(
							((SegmentedTimeline) this.timeline).getStartTime(),
							((SegmentedTimeline) this.timeline).getStartTime() + 1);
				} else {
					r = new DateRange();
				}
			}

			long upper = this.timeline
					.toTimelineValue((long) r.getUpperBound());
			long lower;
			long fixedAutoRange = (long) getFixedAutoRange();
			if (fixedAutoRange > 0.0) {
				lower = upper - fixedAutoRange;
			} else {
				lower = this.timeline.toTimelineValue((long) r.getLowerBound());
				double range = upper - lower;
				long minRange = (long) getAutoRangeMinimumSize();
				if (range < minRange) {
					long expand = (long) (minRange - range) / 2;
					upper = upper + expand;
					lower = lower - expand;
				}
				upper = upper + (long) (range * getUpperMargin());
				lower = lower - (long) (range * getLowerMargin());
			}

			upper = this.timeline.toMillisecond(upper);
			lower = this.timeline.toMillisecond(lower);
			DateRange dr = new DateRange(new Date(lower), new Date(upper));
			setRange(dr, false, false);
		}

	}

	/**
	 * Tests this axis for equality with an arbitrary object.
	 * 
	 * @param obj
	 *            the object (<code>null</code> permitted).
	 * 
	 * @return A boolean.
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof DateAxis)) {
			return false;
		}
		CustomDateAxis that = (CustomDateAxis) obj;
		if (!ObjectUtilities.equal(this.getTickUnit(), that.getTickUnit())) {
			return false;
		}
		if (!ObjectUtilities.equal(this.getDateFormatOverride(),
				that.getDateFormatOverride())) {
			return false;
		}
		if (!ObjectUtilities
				.equal(this.tickMarkPosition, that.tickMarkPosition)) {
			return false;
		}
		if (!ObjectUtilities.equal(this.timeline, that.timeline)) {
			return false;
		}
		return super.equals(obj);
	}

	/**
	 * Returns a clone of the object.
	 * 
	 * @return A clone.
	 * 
	 * @throws CloneNotSupportedException
	 *             if some component of the axis does not support cloning.
	 */
	public Object clone() throws CloneNotSupportedException {
		CustomDateAxis clone = (CustomDateAxis) super.clone();
		// 'dateTickUnit' is immutable : no need to clone
		if (this.getDateFormatOverride() != null) {
			clone.setDateFormatOverride((DateFormat) this
					.getDateFormatOverride().clone());
		}
		// 'tickMarkPosition' is immutable : no need to clone
		return clone;
	}

	public void setInitDateRange(Date begin, Date end) {
		initRange = new DateRange(begin, end);
	}

	public void setInitDateRange(DateRange range) {
		initRange = range;
	}

	public DateRange getInitDateRange() {
		return initRange;
	}

	public void addObserver() {
		for (Observer observer : observers) {
			observable.addObserver(observer);
		}
	}

	public void addObserver(Observer observer) {
		observers.add(observer);
		observable.addObserver(observer);
		if (observer instanceof RefreshObservable) {
			((RefreshObservable) observer).start();
		}
	}

	public void startRunWithProperty(final RunProperties runProperties) {
		if (observers.size() > 0) {
			if (runProperties.isRun()) {
				Timer timer = new Timer(runProperties.getRunPace() * 1000,
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								final Calendar[] calendars = DomainTimeGenerator
										.getDomainDate(runProperties);
								// calendars[0].set(Calendar.MINUTE,
								// Calendar.getInstance().get(Calendar.MINUTE));
								// calendars[1].set(Calendar.MINUTE,
								// Calendar.getInstance().get(Calendar.MINUTE));
								setRange(new DateRange(calendars[0].getTime(),
										calendars[1].getTime()));
								observable.changed();
								observable.notifyObservers(new Object[] {
										"run", runProperties });
							}
						});
				timer.start();
			}
		}
	}
}
