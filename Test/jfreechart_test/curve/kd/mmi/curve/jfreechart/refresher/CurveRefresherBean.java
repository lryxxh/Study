package curve.kd.mmi.curve.jfreechart.refresher;

public abstract class CurveRefresherBean {
	int refreshTime = 5;

	public int getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(int refreshTime) {
		this.refreshTime = refreshTime;
	}

}
