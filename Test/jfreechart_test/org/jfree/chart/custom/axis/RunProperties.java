package org.jfree.chart.custom.axis;

public class RunProperties {

	private boolean isRun;
	
	private boolean isInt;
	
	private int intTimeValue;
	
	private int runPace;
	
	private int calendarField;
	
	private int domainStart;
	
	private int domainEnd;

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public boolean isInt() {
		return isInt;
	}

	public void setInt(boolean isInt) {
		this.isInt = isInt;
	}

	public int getIntTimeValue() {
		return intTimeValue;
	}

	public void setIntTimeValue(int intTimeValue) {
		this.intTimeValue = intTimeValue;
	}

	public int getRunPace() {
		return runPace;
	}

	public void setRunPace(int runPace) {
		this.runPace = runPace;
	}

	public int getCalendarField() {
		return calendarField;
	}

	public void setCalendarField(int calendarField) {
		this.calendarField = calendarField;
	}

	public int getDomainStart() {
		return domainStart;
	}

	public void setDomainStart(int domainStart) {
		this.domainStart = domainStart;
	}

	public int getDomainEnd() {
		return domainEnd;
	}

	public void setDomainEnd(int domainEnd) {
		this.domainEnd = domainEnd;
	}
}
