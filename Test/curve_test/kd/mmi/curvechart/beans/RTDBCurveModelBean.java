package kd.mmi.curvechart.beans;

import kd.mmi.curvechart.figs.Axis;

/**
 * 实时库取数bean.
 * @author LRY
 *
 */
public class RTDBCurveModelBean extends CurveModelBean {


	/** x轴keyid*/
	private long xkeyID = -1l;
	
	/** 点数 */
	private int point = 1440;
	
	/** 方向*/
	private int x_direction = 0;

	/** 横轴域间隔 */
	private int x_pace = 0;
	
	/** y轴方向 */
	private int y_direction = 0;

	/** 纵轴域间隔 */
	private int y_pace = 0;
	
	/** x轴应用*/
	private int x_app = 100000;
	
	/** 数据源方式*/
	private int pointMode = 0;

	public RTDBCurveModelBean(Axis axis) {
		super(axis);
	}
	
	/**
	 * get xkeyID value
	 * @return the xkeyID
	 */
	public long getXkeyID() {
		return xkeyID;
	}

	/**
	 * set xkeyID value
	 * @param xkeyID 
	 */
	public void setXkeyID(long xkeyID) {
		this.xkeyID = xkeyID;
	}

	/**
	 * get point value
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * set point value
	 * @param point 
	 */
	public void setPoint(int point) {
		this.point = point;
	}

	/**
	 * get x_direction value
	 * @return the x_direction
	 */
	public int getX_direction() {
		return x_direction;
	}

	/**
	 * set x_direction value
	 * @param x_direction 
	 */
	public void setX_direction(int x_direction) {
		this.x_direction = x_direction;
	}

	/**
	 * get x_pace value
	 * @return the x_pace
	 */
	public int getX_pace() {
		return x_pace;
	}

	/**
	 * set x_pace value
	 * @param x_pace 
	 */
	public void setX_pace(int x_pace) {
		this.x_pace = x_pace;
	}

	/**
	 * get y_direction value
	 * @return the y_direction
	 */
	public int getY_direction() {
		return y_direction;
	}

	/**
	 * set y_direction value
	 * @param y_direction 
	 */
	public void setY_direction(int y_direction) {
		this.y_direction = y_direction;
	}

	/**
	 * get y_pace value
	 * @return the y_pace
	 */
	public int getY_pace() {
		return y_pace;
	}

	/**
	 * set y_pace value
	 * @param y_pace 
	 */
	public void setY_pace(int y_pace) {
		this.y_pace = y_pace;
	}

	/**
	 * get x_app value
	 * @return the x_app
	 */
	public int getX_app() {
		return x_app;
	}

	/**
	 * set x_app value
	 * @param x_app 
	 */
	public void setX_app(int x_app) {
		this.x_app = x_app;
	}

	/**
	 * get pointMode value
	 * @return the pointMode
	 */
	public int getPointMode() {
		return pointMode;
	}

	/**
	 * set pointMode value
	 * @param pointMode 
	 */
	public void setPointMode(int pointMode) {
		this.pointMode = pointMode;
	}
	
}
