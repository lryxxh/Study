package kd.mmi.curvechart.engine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import kd.mmi.curvechart.beans.CurveModelBean;
import kd.mmi.curvechart.beans.HisCurveModelBean;
import kd.mmi.curvechart.beans.RTDBCurveModelBean;
import kd.mmi.curvechart.figs.CurveItem;
import kd.mmi.curvechart.models.SeriesModel;
import support.DataClient;
import agency.Debug;
import agency.MidLayerAccessException;
import agency.message.base.DataUnit;
import agency.message.base.IDTool;
import agency.message.base.KeyID;
import agency.message.midhs.MidhsData;
import agency.message.midhs.MidhsService;

public class FigCurveEngine implements Runnable {

	private FigCurveEngine() {
		startCurveRefresh();
	}

	public static FigCurveEngine getInstance() {
		return InstanceHander.instance;
	}

	private static class InstanceHander {
		private static FigCurveEngine instance = new FigCurveEngine();
	}

	private LinkedList<CurveModelBean> curveModelBeans = new LinkedList<CurveModelBean>();
	private LinkedList<CurveModelBean> curveModelBeansBak = new LinkedList<CurveModelBean>();

	/** */
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	private ReadLock readLock = lock.readLock();

	private WriteLock writeLock = lock.writeLock();

	private boolean isRefresh = true;

	private boolean is_exit = false;

	private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);

	public void addCurveModelBean(CurveModelBean bean) {
		writeLock.lock();
		try {
			curveModelBeans.add(bean);
		} catch (Exception e) {
		} finally {
			writeLock.unlock();
		}
	}

	public CurveModelBean getCurveModelBean() {
		CurveModelBean bean = null;
		readLock.lock();
		try {
			bean = curveModelBeans.pop();
		} catch (Exception e) {
		} finally {
			readLock.unlock();
		}
		return bean;
	}
	
	private void addCurveModelBean2Bak(CurveModelBean bean) {
		curveModelBeansBak.add(bean);
	}

	public void startCurveRefresh() {
		new Thread(this).start();
	}

	@Override
	public void run() {

		while (true) {
			if (curveModelBeans.size() > 0) {

				if (is_exit) {
					System.err.println("curve refresh thread exit!");
//					break;
				}
				if (!isRefresh) {
					synchronized (this) {
						try {
							wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} else {
					while(curveModelBeans.size() > 0) {
						final CurveModelBean bean = getCurveModelBean();
						executorService.execute(new Runnable() {

							@Override
							public void run() {
								dealCurveBean(bean);
							}
						});
						addCurveModelBean2Bak(bean);
					}
					curveModelBeans.addAll(curveModelBeansBak);
					curveModelBeansBak.clear();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * @param bean
	 */
	private void dealCurveBean(CurveModelBean bean) {
		if (bean.getClass().getName().equals(HisCurveModelBean.class.getName())) {
			dealHisCurveBean(bean);
		} else {
			dealRTDBCurveBean(bean);
		}
	}

	/**
	 * 处理关系库.
	 * 
	 * @param bean
	 */
	private void dealHisCurveBean(CurveModelBean bean) {

		String domainString = "local";
		String contextName = "realtime";
		String curveType = ((HisCurveModelBean) bean).getType();
		long keyid = ((HisCurveModelBean) bean).getKeyid();
		int period = ((HisCurveModelBean) bean).getPeriod();
		long startTime = ((HisCurveModelBean) bean).getStartTime();
		long endTime = ((HisCurveModelBean) bean).getEndTime();
		int mode = ((HisCurveModelBean) bean).getMode();
		endTime = convertEndTime(curveType, endTime);
		
		List data = createDemoData(startTime, endTime, period);
		updateTimeSeriesModel(bean, data);
		
		
//		try {
//
//			Vector data = MidhsService.receiveCurveData(domainString, contextName, curveType, keyid, period, startTime, endTime, mode);
//			updateTimeSeriesModel(bean, data);
//		} catch (DataFaultException e) {
//			e.printStackTrace();
//		} catch (ServiceConnectionException e) {
//			e.printStackTrace();
//		} catch (LocatorException e) {
//			e.printStackTrace();
//		} catch (ProxyFaultException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}
	
	private List createDemoData(long startTime, long endTime, int period) {
		List data = new ArrayList();
		data.add(0);
		data.add(1);
		MidhsData midhsData = null;
		long offset = 0;
		while((startTime + offset) <= endTime) {
			midhsData = new MidhsData();
			midhsData.setQuality(1);
			midhsData.setValidFlag(0);
			midhsData.setXValue(startTime + offset);
			midhsData.setYValue((float)(100 * Math.sin(2*Math.PI * offset/ (endTime - startTime))) + Calendar.getInstance().get(Calendar.SECOND));
			offset += period * 1000;
			data.add(midhsData);
		}
		return data;
	}

	/**
	 * 处理实时库.
	 * 
	 * @param bean
	 */
	private void dealRTDBCurveBean(CurveModelBean bean) {

		long xKeyid = ((RTDBCurveModelBean) bean).getXkeyID();
		long ykeyid = ((RTDBCurveModelBean) bean).getKeyid();
		Vector<Vector<DataUnit>> xdata = new Vector<Vector<DataUnit>>();
		Vector<Vector<DataUnit>> ydata = new Vector<Vector<DataUnit>>();
		String appName = IDTool.getAppName(((RTDBCurveModelBean) bean).getAppID());
		if (xKeyid != -1) {
			KeyID keyID = new KeyID(xKeyid);
			xdata = getDataByKeyID(appName, keyID);
		}
		if (ykeyid != -1) {
			KeyID keyID = new KeyID(ykeyid);
			ydata = getDataByKeyID(appName, keyID);
		}

	}

	public void updateNumberSeriesModel(CurveModelBean bean, Vector<Vector<DataUnit>> xData, Vector<Vector<DataUnit>> yData) {
		SeriesModel model = bean.getModel();
		int size = yData.size();
		int xsize = yData.size();
		int ysize = yData.size();
		if (xData != null && xData.size() > 0) {
			xsize = xData.size();
		}
		size = xsize > ysize ? ysize : xsize;

		CurveItem curveItem = null;
		double yValue = 0;
		double xValue = 0;
		for (int i = 0; i < size; i++) {
			if (xData != null && xData.size() > 0) {
				xValue = xData.get(0).get(i).getDoubleData();
			}
			yValue = yData.get(0).get(i).getDoubleData();
			curveItem = new CurveItem();
			curveItem.setIsValid(0);
			curveItem.setXValue(xValue);
			curveItem.setYValue(yValue);
			model.addOrUpdateItem(curveItem);
		}
	}

	public void updateTimeSeriesModel(CurveModelBean bean, List data) {
		SeriesModel model = bean.getModel();
		if (data.size() < 3) {
			return;
		}
		CurveItem item = null;
		int isNull = 0;
		float yValue = 0;
		long xValue = 0;
		int quality = 1073741824;
		for (int i = 2; i < data.size(); i++) {
			MidhsData curveData = (MidhsData) data.get(i);
			if (true) {
				System.err.println(curveData.getValidFlag() + "  " + new Date(curveData.getXValue()) + "  " + curveData.getYValue() + "  " + curveData.getQuality());
			}
			isNull = curveData.getValidFlag();
			yValue = curveData.getYValue();
			xValue = curveData.getXValue();
			quality = curveData.getQuality();

			item = new CurveItem();
			item.setXValue(xValue);
			item.setYValue(yValue);
			item.setQuality(quality);
			item.setIsValid(isNull);
			model.addOrUpdateItem(item);
		}
		model.fireRepaint();
	}

	/**
	 * 计算结束时间.
	 * 
	 * @param type
	 * @param time
	 * @return
	 */
	public static long convertEndTime(String type, long time) {
		long returnTime = 0;
		if (type.equals("1") || type.equals("31")) {
			Calendar calendar = Calendar.getInstance(Locale.CHINESE);
			// 基准时间
			long currTime = new Date().getTime();
			calendar.setTimeInMillis(currTime);
			int year1 = calendar.get(Calendar.YEAR);
			int month1 = calendar.get(Calendar.MONTH);
			int day1 = calendar.get(Calendar.DATE);
			int hour1 = calendar.get(Calendar.HOUR_OF_DAY);

			// 传入时间
			calendar.setTimeInMillis(time);
			int year2 = calendar.get(Calendar.YEAR);
			int month2 = calendar.get(Calendar.MONTH);
			int day2 = calendar.get(Calendar.DATE);
			int hour2 = calendar.get(Calendar.HOUR_OF_DAY);

			if ((currTime < time && year1 == year2 && month1 == month2 && day1 == day2) || (currTime < time && year1 == year2 && month1 == month2 && hour2 == 0 && day1 + 1 == day2)) {
				// 基准时间和传入时间在同一天且当前时间小于传入时间,结束时间截取到当前时刻
				returnTime = currTime;
			} else {
				// 基准时间和传入时间在不在同一天,直接返回传入时间
				returnTime = time;
			}
		} else {
			returnTime = time;
		}

		return returnTime;
	}

	public Vector<Vector<DataUnit>> getDataByKeyID(String appName, KeyID keyID) {
		Vector<Vector<DataUnit>> data = new Vector<Vector<DataUnit>>();

		String tableName = keyID.getTableName();
		String fieldName = null;
		try {
			fieldName = keyID.getFieldName();
		} catch (MidLayerAccessException e1) {
			e1.printStackTrace();
		}
		if (tableName != null && tableName.startsWith("dsa_")) {
			appName = "dsa";
		}
		// String dbid = Utility.getViewerDBID(viewer, appName, null);
		String dbid = "local,realtime," + appName + ",null";
		Vector<String> property = new Vector<String>();
		property.add(fieldName);
		try {
			// DataClient.RTDBRead(dbid,tableName,beginRow,beginRow+point,property,fieldName,y_id);
			data = DataClient.RTDBRead(dbid, tableName, property);
		} catch (MidLayerAccessException e) {
			e.printStackTrace();

			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append(dbid);
			strBuffer.append(" ");
			strBuffer.append(keyID.getTableName());
			strBuffer.append(" ");
			strBuffer.append(keyID.getKeyID());
			strBuffer.append(" ");
			strBuffer.append(property);
			// UIErrorDialog.showDialog(viewer.getContainer(),
			// "Exception - middata", strBuffer.toString(), e);
		}
		return data;

	}

}
