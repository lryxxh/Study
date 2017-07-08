package kd.mmi.curvechart.beans;

import java.io.Serializable;

import kd.mmi.curvechart.figs.Axis;
import kd.mmi.curvechart.models.SeriesModel;

/**
 * @author LRY
 *
 */
public class CurveModelBean implements Serializable{
	
	
	/** ����ģ��*/
	private SeriesModel model = null;
	
	/** ����*/
	private String type = "1";
	
	/** Ӧ�ú�,Ϊy���Ӧ�ú� */
	private int appID = 100000;
	
	/** �ؼ���,Ϊy��Ĺؼ���*/
	private long keyid = 1l;
	
	/** */
	protected Axis axis = null;
	
	public CurveModelBean(Axis axis) {
		this.axis = axis;
	}

	/**
	 * get model value
	 * @return the model
	 */
	public SeriesModel getModel() {
		return model;
	}

	/**
	 * set model value
	 * @param model 
	 */
	public void setModel(SeriesModel model) {
		this.model = model;
	}

	/**
	 * get type value
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * set type value
	 * @param type 
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * get appID value
	 * @return the appID
	 */
	public int getAppID() {
		return appID;
	}

	/**
	 * set appID value
	 * @param appID 
	 */
	public void setAppID(int appID) {
		this.appID = appID;
	}

	/**
	 * get keyid value
	 * @return the keyid
	 */
	public long getKeyid() {
		return keyid;
	}

	/**
	 * set keyid value
	 * @param keyid 
	 */
	public void setKeyid(long keyid) {
		this.keyid = keyid;
	}
	
}
