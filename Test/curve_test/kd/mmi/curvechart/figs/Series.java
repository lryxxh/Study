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
 * ���߶���,���ڴ洢���е�����.
 * @author LRY
 *
 */
public class Series implements Serializable, Comparable<Series>{
	
	/** */
	private static final long serialVersionUID = 1L;
	
	/** */
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	/** ���ߵ���״,Ĭ�Ͽ�,ֱ�ӻ���Ϊ��*/
	protected Shape itemShape = null;
	
	/** ������ɫ*/
	private Color color = Color.red;

	/** ���߻���*/
	private Stroke stroke = null;
	
	/** ���ߵ���״,���ߵ�Ϊ���ĵ�*/
	private Shape shape = null;
	
	/** �Ƿ�������ߵ���״*/
	private boolean isDrawItemShape = false;
	
	/** �Ƿ�������ߵ���״*/
	private boolean isFillItemShape = false;
	
	/** ������״��ɫ*/
	private Color drawShapeColor = Color.black;
	
	/** �����״��ɫ*/
	private Color fillShapeColor = Color.black;
	
	/** ��������*/
	private String curveName = "Curve";

	/** �Ƿ���ʾ*/
	private boolean isShow = true;
	
	/**��������ģ�� */
	private SeriesModel seriesModel = new SeriesModel();

	/** ͼ���Ƿ����*/
	private boolean legendVisible = true;
	
	/** �������*/
	private int index = 0;
	
	public Series(String curveName, Color color) {
		this.curveName = curveName;
		this.color = color;
	}
	
	
	/**
	 * �������ߵ���״.
	 * @param itemShape
	 */
	public void setItemShape(Shape itemShape) {
		this.itemShape = itemShape;
	}
	
	/**
	 * ��ȡ���ߵ���״.
	 * @return
	 */
	public Shape getItemShape() {
		return itemShape;
	}
	
	
	/**
	 * ��ȡ������ɫ.
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * �������߻���.
	 * @param stroke
	 */
	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}
	
	/**
	 * �õ����߻���.
	 * @return
	 */
	public Stroke getStroke() {
		return stroke;
	}

	/**
	 * �������ߵ���״.
	 * @return
	 */
	public Shape getShape() {
		return shape;
	}
	
	/**
	 * ��ȡ���ߵ���״.
	 * @param shape
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	/**
	 * �����Ƿ�������ߵ���״.
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
	 * �Ƿ�������ߵ���״.
	 * @return
	 */
	public boolean isDrawItemShape() {
		return isDrawItemShape;
	}
	
	/**
	 * �����Ƿ�������ߵ���״.
	 * @param isFillItemShape
	 */
	public void setFillItemShape(boolean isFillItemShape) {
		this.isFillItemShape = isFillItemShape;
	}
	
	/**
	 * �Ƿ�������ߵ���״.
	 * @return
	 */
	public boolean isFillItemShape() {
		return isFillItemShape;
	}
	
	/**
	 * �������ߵ���״������ɫ.
	 * @param drawShapeColor
	 */
	public void setDrawShapeColor(Color drawShapeColor) {
		this.drawShapeColor = drawShapeColor;
	}
	
	/**
	 * ��ȡ���ߵ���״������ɫ.
	 * @return
	 */
	public Color getDrawShapeColor() {
		return drawShapeColor;
	}
	
	/**
	 * �������ߵ���״�����ɫ.
	 * @param fillShapeColor
	 */
	public void setFillShapeColor(Color fillShapeColor) {
		this.fillShapeColor = fillShapeColor;
	}
	
	/**
	 * �õ����ߵ���״�����ɫ.
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
	 * ������ߵ�.
	 * @param item
	 */
	public void addCurveItem(CurveItem item) {
		if(seriesModel != null) {
			seriesModel.addCurveItem(item);
		}
	}
	
	/**
	 * ��ӻ��������.
	 * @param item
	 */
	public void addOrUpdateItem(CurveItem item) {
		//������ݲ�����,��
		if(seriesModel != null) {
			seriesModel.addOrUpdateItem(item);
		}
	}

	/**
	 * ��Ӽ�����.
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
