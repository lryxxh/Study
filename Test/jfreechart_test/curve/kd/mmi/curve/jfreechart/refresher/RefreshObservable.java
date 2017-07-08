package curve.kd.mmi.curve.jfreechart.refresher;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import org.jfree.chart.custom.axis.DomainTimeGenerator;
import org.jfree.chart.custom.axis.RunProperties;

import agency.MidLayerAccessException;
import agency.exception.DataFaultException;
import agency.exception.LocatorException;
import agency.exception.ProxyFaultException;
import agency.exception.ServiceConnectionException;
import agency.message.base.DataUnit;
import agency.message.base.DomainManager;
import agency.message.base.IDTool;
import agency.message.base.KeyID;
import agency.message.midhs.MidhsCurveStaticFinals;
import agency.message.midhs.MidhsService;
import context.Context;
import support.DataClient;

public class RefreshObservable extends Observable implements Observer {

	Observable observable = null;
	RefreshThread thread = null;
	CurveRefresherBean bean;
	boolean isRefresh = true;
	boolean is_exit = false;

	public RefreshObservable(CurveRefresherBean beanArg) {
		this.bean = beanArg;
		thread = new RefreshThread();
	}

	public static long convertEndTime(String type, long time) {
		long returnTime = 0;
		if (type.equals(MidhsCurveStaticFinals.DAYLOAD) || type.equals("31")) {
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

			if ((currTime < time && year1 == year2 && month1 == month2 && day1 == day2)
					|| (currTime < time && year1 == year2 && month1 == month2
							&& hour2 == 0 && day1 + 1 == day2)) {
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

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof TurnDateObservable) {
			synchronized (thread) {
				stop_refresh();
				if (bean instanceof TimeCurveRefresherBean) {
					long startTime = ((TimeCurveRefresherBean)bean).getStartTime();
					long endTime = ((TimeCurveRefresherBean)bean).getEndTime();
					double length = ((TimeCurveRefresherBean)bean).getLength();
					if (arg.toString().equals("+")) {
						((TimeCurveRefresherBean)bean).setStartTime((long) (startTime + length));
						((TimeCurveRefresherBean)bean).setEndTime((long) (endTime + length));
					} else if (arg.toString().equals("-")){
						((TimeCurveRefresherBean)bean).setStartTime((long) (startTime - length));
						((TimeCurveRefresherBean)bean).setEndTime((long) (endTime - length));
					} else if (arg instanceof Object[]) {
						Object[] strs = (Object[]) arg;
						if (strs!=null && strs.length == 2 && strs[0].equals("run")) {
							if (strs[1] instanceof RunProperties) {
								Calendar[] calendars = DomainTimeGenerator.getDomainDate((RunProperties) strs[1]);
								((TimeCurveRefresherBean)bean).setStartTime(calendars[0].getTimeInMillis());
								((TimeCurveRefresherBean)bean).setEndTime(calendars[1].getTimeInMillis());
							}
						}
					}
				} else if (bean instanceof NumberCurveRefresherBean) {
					
				}
				
				start_refresh();
			}
		}
	}

	public void start() {
		thread.start();
	}
	
	class RefreshThread extends Thread {

		@Override
		public void run() {

			while (true) {
			    System.out.println(".........." + isRefresh);
				if (is_exit) {
					System.err.println("curve refresh thread exit!");
					break;
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
					Vector data = new Vector();
//					Calendar calendar = Calendar.getInstance();
//					calendar.set(Calendar.HOUR_OF_DAY, 0);
//					calendar.set(Calendar.MINUTE, 0);
//					calendar.set(Calendar.SECOND, 0);
//					calendar.set(Calendar.MILLISECOND, 0);
//					for(int i=0;i<1440;i++) {
//						MidhsData midhsdata = new MidhsData();
//						midhsdata.setXValue(calendar.getTimeInMillis());
//						midhsdata.setYValue(i % 50);
//						midhsdata.setQuality(0);
//						midhsdata.setValidFlag(0);
//						calendar.add(Calendar.MINUTE, 1);
//						data.add(midhsdata);
//					}
				    
					if (bean instanceof TimeCurveRefresherBean) {
						String domainString = DomainManager.LOCAL_DOMAIN;
						String contextName = Context.REALTIME;
						String curveType = ((TimeCurveRefresherBean)bean).getCurveType();
						long keyid = ((TimeCurveRefresherBean)bean).getKeyid();
						int period = ((TimeCurveRefresherBean)bean).getPeriod();
						long startTime = ((TimeCurveRefresherBean)bean).getStartTime();
						long endTime = ((TimeCurveRefresherBean)bean).getEndTime();
						int mode = ((TimeCurveRefresherBean)bean).getMode();
						endTime = convertEndTime(curveType, endTime);
						try {
							data = MidhsService
									.receiveCurveData(domainString,
											contextName, curveType, keyid,
											period, startTime, endTime, mode);
							
						} catch (DataFaultException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ServiceConnectionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (LocatorException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ProxyFaultException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (bean instanceof NumberCurveRefresherBean) {
						long xKeyid = ((NumberCurveRefresherBean) bean).getX_keyid();
						long ykeyid = ((NumberCurveRefresherBean) bean).getY_keyid();
						Vector<Vector<DataUnit>> xdata = new Vector<Vector<DataUnit>>();
						Vector<Vector<DataUnit>> ydata = new Vector<Vector<DataUnit>>();
						String appName = IDTool.getAppName(((NumberCurveRefresherBean) bean).getAppName());
						if (xKeyid != -1) {
							KeyID keyID = new KeyID(xKeyid);
							xdata =  getDataByKeyID(appName, keyID);
							data.add(xdata);
						} 
						if (ykeyid != -1) {
							KeyID keyID = new KeyID(ykeyid);
							ydata = getDataByKeyID(appName, keyID);
							data.add(ydata);
						}
					}
					setChanged();
					notifyObservers(data.clone());
					try {
						Thread.sleep(bean.getRefreshTime() * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void interrupt_refresh() {
		is_exit = true;
		synchronized (thread) {
			thread.notify();
		}
	}
	
	public void stop_refresh() {
		isRefresh = false;
	}
	
	public void start_refresh() {
		isRefresh = true;
		synchronized (thread) {
			thread.notify();
		}
	}
	
	public void setStartTime(long startTime) {
		((TimeCurveRefresherBean)bean).setStartTime(startTime);
	}
	
	public Vector<Vector<DataUnit>> getDataByKeyID(String appName, KeyID keyID){
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
//		String dbid = Utility.getViewerDBID(viewer, appName, null);
		String dbid = "local,realtime,"+appName+",null";
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
