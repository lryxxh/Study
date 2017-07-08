package kd.mmi.curvechart.beans;

import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * 绘制信息.
 * 
 * @author LRY
 * 
 */
public class DrawInformation implements Serializable {
	
	/**设置属性 */
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	/** */
	private static final long serialVersionUID = 1L;

	/** 曲线组件左上角横坐标 */
	private int x = 0;

	/** 曲线组件左上角横坐标 */
	private int y = 0;

	/** 曲线组件宽度 */
	private int w = 0;

	/** /** 曲线组件高度 */
	private int h = 0;

	/** 填充模式 */
	private int bgm = 1;

	/** 填充颜色 */
	private int bgc = -16737895;

	/** */
	private int multi_axis_type = 0;

	/** 绘制区域左上角横坐标 */
	private int draw_Area_x = 0;

	/** 绘制区域左上角纵坐标 */
	private int draw_Area_y = 0;

	/** 绘制区域宽度 */
	private int draw_Area_w = 0;

	/** 绘制区域高度 */
	private int draw_Area_h = 0;

	/** 主网格颜色 */
	private int mainGridColor = 0;

	/** 主网格线宽 */
	private int mainGridLineWidth = 1;

	/** 主网格线型 */
	private int mainGridLineStyle = 2;

	/** 是否显示横向主网格 */
	private boolean isMainGridXShow = true;

	/** 是否显示纵向主网格 */
	private boolean isMainGridYShow = true;

	/** 子网格颜色 */
	private int subGridColor = 0;

	/** 子网格线宽 */
	private int subGridLineWidth = 1;

	/** 子网格线型 */
	private int subGridLineStyle = 2;

	/** 横向子网格是否显示 */
	private boolean subGridXShow = false;

	/** 纵向子网格是否显示 */
	private boolean subGridYShow = false;

	/** 绘制区域 */
	private Rectangle drawArea = null;

	/** 画面区域 */
	private Rectangle curveArea = null;

	public int getDraw_Area_x() {
		return draw_Area_x;
	}

	public void setDraw_Area_x(int draw_Area_x) {
		this.draw_Area_x = draw_Area_x;
	}
	
	public void fireDraw_Area_xChange(int draw_Area_x) {
		support.firePropertyChange("draw_x", this.draw_Area_x, draw_Area_x);
		this.draw_Area_x = draw_Area_x;
		drawArea.setLocation(draw_Area_x, draw_Area_y);
	}

	public int getDraw_Area_y() {
		return draw_Area_y;
	}

	public void setDraw_Area_y(int draw_Area_y) {
		this.draw_Area_y = draw_Area_y;
	}

	public int getDraw_Area_w() {
		return draw_Area_w;
	}

	public void setDraw_Area_w(int draw_Area_w) {
		this.draw_Area_w = draw_Area_w;
	}

	public int getDraw_Area_h() {
		return draw_Area_h;
	}

	public void setDraw_Area_h(int draw_Area_h) {
		this.draw_Area_h = draw_Area_h;
	}

	public int getMainGridColor() {
		return mainGridColor;
	}

	public void setMainGridColor(int mainGridColor) {
		this.mainGridColor = mainGridColor;
	}

	public int getMainGridLineWidth() {
		return mainGridLineWidth;
	}

	public void setMainGridLineWidth(int mainGridLineWidth) {
		this.mainGridLineWidth = mainGridLineWidth;
	}

	public int getMainGridLineStyle() {
		return mainGridLineStyle;
	}

	public void setMainGridLineStyle(int mainGridLineStyle) {
		this.mainGridLineStyle = mainGridLineStyle;
	}

	public boolean isMainGridXShow() {
		return isMainGridXShow;
	}

	public void setMainGridXShow(boolean isMainGridXShow) {
		this.isMainGridXShow = isMainGridXShow;
	}

	public boolean isMainGridYShow() {
		return isMainGridYShow;
	}

	public void setMainGridYShow(boolean isMainGridYShow) {
		this.isMainGridYShow = isMainGridYShow;
	}

	public int getSubGridColor() {
		return subGridColor;
	}

	public void setSubGridColor(int subGridColor) {
		this.subGridColor = subGridColor;
	}

	public int getSubGridLineWidth() {
		return subGridLineWidth;
	}

	public void setSubGridLineWidth(int subGridLineWidth) {
		this.subGridLineWidth = subGridLineWidth;
	}

	public int getSubGridLineStyle() {
		return subGridLineStyle;
	}

	public void setSubGridLineStyle(int subGridLineStyle) {
		this.subGridLineStyle = subGridLineStyle;
	}

	public boolean isSubGridXShow() {
		return subGridXShow;
	}

	public void setSubGridXShow(boolean subGridXShow) {
		this.subGridXShow = subGridXShow;
	}

	public boolean isSubGridYShow() {
		return subGridYShow;
	}

	public void setSubGridYShow(boolean subGridYShow) {
		this.subGridYShow = subGridYShow;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getBgm() {
		return bgm;
	}

	public void setBgm(int bgm) {
		this.bgm = bgm;
	}

	public int getBgc() {
		return bgc;
	}

	public void setBgc(int bgc) {
		this.bgc = bgc;
	}

	public int getMulti_axis_type() {
		return multi_axis_type;
	}

	public void setMulti_axis_type(int multi_axis_type) {
		this.multi_axis_type = multi_axis_type;
	}

	public Rectangle getDrawArea() {
		if (drawArea == null) {
			int drawX = getDraw_Area_x();
			int drawY = getDraw_Area_y();
			int drawW = getDraw_Area_w();
			int drawH = getDraw_Area_h();
			drawArea = new Rectangle(drawX, drawY, drawW, drawH);
		}
		return drawArea;
	}

	public Rectangle getCurveArea() {
		if (curveArea == null) {
			int x = getX();
			int y = getY();
			int w = getW();
			int h = getH();
			curveArea = new Rectangle(x, y, w, h);
		}
		return curveArea;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

}
