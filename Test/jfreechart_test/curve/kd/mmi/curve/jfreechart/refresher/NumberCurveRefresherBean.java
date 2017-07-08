package curve.kd.mmi.curve.jfreechart.refresher;


public class NumberCurveRefresherBean extends CurveRefresherBean implements
		Cloneable {

	long x_keyid = -1;
	long y_keyid = -1;
	int point = 1440;
	int x_direction = 0;
	int x_pace = 5;
	int y_direction = 0;
	int y_pace = 5;
	int point_mode = 4;
	int appID = 100000;
	int decimal = 2;

	public NumberCurveRefresherBean(long x_keyid, long y_keyid, int point,
			int x_direction, int x_pace, int y_direction, int y_pace,
			int point_mode, int appID, int decimal) {
		super();
		this.x_keyid = x_keyid;
		this.y_keyid = y_keyid;
		this.point = point;
		this.x_direction = x_direction;
		this.x_pace = x_pace;
		this.y_direction = y_direction;
		this.y_pace = y_pace;
		this.point_mode = point_mode;
		this.appID = appID;
		this.decimal = decimal;
	}

	public long getX_keyid() {
		return x_keyid;
	}

	public void setX_keyid(long x_keyid) {
		this.x_keyid = x_keyid;
	}

	public long getY_keyid() {
		return y_keyid;
	}

	public void setY_keyid(long y_keyid) {
		this.y_keyid = y_keyid;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getX_direction() {
		return x_direction;
	}

	public void setX_direction(int x_direction) {
		this.x_direction = x_direction;
	}

	public int getX_pace() {
		return x_pace;
	}

	public void setX_pace(int x_pace) {
		this.x_pace = x_pace;
	}

	public int getY_direction() {
		return y_direction;
	}

	public void setY_direction(int y_direction) {
		this.y_direction = y_direction;
	}

	public int getY_pace() {
		return y_pace;
	}

	public void setY_pace(int y_pace) {
		this.y_pace = y_pace;
	}

	public int getPoint_mode() {
		return point_mode;
	}

	public void setPoint_mode(int point_mode) {
		this.point_mode = point_mode;
	}

	public int getAppName() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}

	public int getDecimal() {
		return decimal;
	}

	public void setDecimal(int decimal) {
		this.decimal = decimal;
	}
}
