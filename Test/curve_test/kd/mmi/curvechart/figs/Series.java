package kd.mmi.curvechart.figs;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;

import kd.mmi.curvechart.models.SeriesModel;


/**
 * 曲线对象,用于存储所有的数据.
 * @author LRY
 *
 */
public class Series implements Serializable, Comparable<Series>{
	
	/** */
	private static final long serialVersionUID = 1L;
	
	/** */
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	/** 曲线点形状,默认空,直接绘制为点*/
	protected Shape itemShape = null;
	
	/** 曲线颜色*/
	private Color color = Color.red;

	/** 曲线画笔*/
	private Stroke stroke = null;
	
	/** 曲线点形状,曲线点为中心点*/
	private Shape shape = null;
	
	/** 是否绘制曲线点形状*/
	private boolean isDrawItemShape = false;
	
	/** 是否填充曲线点形状*/
	private boolean isFillItemShape = false;
	
	/** 绘制形状颜色*/
	private Color drawShapeColor = Color.black;
	
	/** 填充形状颜色*/
	private Color fillShapeColor = Color.black;
	
	/** 曲线名称*/
	private String curveName = "Curve";

	/** 是否显示*/
	private boolean isShow = true;
	
	/**曲线数据模型 */
	private SeriesModel seriesModel = new SeriesModel();

	/** 图例是否绘制*/
	private boolean legendVisible = true;
	
	/** 曲线序号*/
	private int index = 0;
	
	public Series(String curveName, Color color) {
		this.curveName = curveName;
		this.color = color;
	}
	
	
	/**
	 * 设置曲线点形状.
	 * @param itemShape
	 */
	public void setItemShape(Shape itemShape) {
		this.itemShape = itemShape;
	}
	
	/**
	 * 获取曲线点形状.
	 * @return
	 */
	public Shape getItemShape() {
		return itemShape;
	}
	
	
	/**
	 * 获取曲线颜色.
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * 设置曲线画笔.
	 * @param stroke
	 */
	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}
	
	/**
	 * 得到曲线画笔.
	 * @return
	 */
	public Stroke getStroke() {
		return stroke;
	}

	/**
	 * 设置曲线点形状.
	 * @return
	 */
	public Shape getShape() {
		return shape;
	}
	
	/**
	 * 获取曲线点形状.
	 * @param shape
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	/**
	 * 设置是否绘制曲线点形状.
	 * @param isDrawItemShape
	 */
	public void setDrawItemShape(boolean isDrawItemShape) {
		this.isDrawItemShape = isDrawItemShape;
	}
	
	
	public void addRepaintListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
		seriesModel.addRepaintListener(listener);
	}
	
	/**
	 * 是否绘制曲线点形状.
	 * @return
	 */
	public boolean isDrawItemShape() {
		return isDrawItemShape;
	}
	
	/**
	 * 设置是否填充曲线点形状.
	 * @param isFillItemShape
	 */
	public void setFillItemShape(boolean isFillItemShape) {
		this.isFillItemShape = isFillItemShape;
	}
	
	/**
	 * 是否填充曲线点形状.
	 * @return
	 */
	public boolean isFillItemShape() {
		return isFillItemShape;
	}
	
	/**
	 * 设置曲线点形状绘制颜色.
	 * @param drawShapeColor
	 */
	public void setDrawShapeColor(Color drawShapeColor) {
		this.drawShapeColor = drawShapeColor;
	}
	
	/**
	 * 获取曲线点形状绘制颜色.
	 * @return
	 */
	public Color getDrawShapeColor() {
		return drawShapeColor;
	}
	
	/**
	 * 设置曲线点形状填充颜色.
	 * @param fillShapeColor
	 */
	public void setFillShapeColor(Color fillShapeColor) {
		this.fillShapeColor = fillShapeColor;
	}
	
	/**
	 * 得到曲线点形状填充颜色.
	 * @return
	 */
	public Color getFillShapeColor() {
		return fillShapeColor;
	}


	/**
	 * get curveName value
	 * @return the curveName
	 */
	public String getCurveName() {
		return curveName;
	}


	/**
	 * get isShow value
	 * @return the isShow
	 */
	public boolean isShow() {
		return isShow;
	}

	/**
	 * set isShow value
	 * @param isShow 
	 */
	public void setShow(boolean isShow) {
		if(this.isShow != isShow) {
			support.firePropertyChange("repaint", this.isShow, isShow);
			this.isShow = isShow;
		}
	}
	
	
	/**
	 * 添加曲线点.
	 * @param item
	 */
	public void addCurveItem(CurveItem item) {
		if(seriesModel != null) {
			seriesModel.addCurveItem(item);
		}
	}
	
	/**
	 * 添加或更新数据.
	 * @param item
	 */
	public void addOrUpdateItem(CurveItem item) {
		//如果数据不存在,则
		if(seriesModel != null) {
			seriesModel.addOrUpdateItem(item);
		}
	}

	/**
	 * 添加监听器.
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		seriesModel.addPropertyChangeListener(listener);
	}


	/**
	 * get seriesModel value
	 * @return the seriesModel
	 */
	public SeriesModel getModel() {
		return seriesModel;
	}


	/**
	 * set seriesModel value
	 * @param seriesModel 
	 */
	public void setModel(SeriesModel seriesModel) {
		this.seriesModel = seriesModel;
	}


	public List<CurveItem> getItemDatas() {
		return seriesModel.getItemDatas();
	}


	public CurveItem binarySearchByXValue(double value) {
		return seriesModel.binarySearchByXValue(value);
	}


	/**
	 * get legendVisible value
	 * @return the legendVisible
	 */
	public boolean isLegendVisible() {
		return legendVisible;
	}


	/**
	 * set legendVisible value
	 * @param legendVisible 
	 */
	public void setLegendVisible(boolean legendVisible) {
		this.legendVisible = legendVisible;
	}

	/**
	 * get index value
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * set index value
	 * @param index 
	 */
	public void setIndex(int index) {
		this.index = index;
	}


	@Override
	public int compareTo(Series o) {
		return this.index - o.getIndex();
	}

}
