package lang;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.log4j.Logger;

import log.Log;

/**
 * “Ï≥£¡¥
 * 
 * @author JoyoungZhang@gmail.com
 * 
 */
public class UnCaughtException {
	public static void main(String[] args) throws Exception {
		Thread.setDefaultUncaughtExceptionHandler(
				new UncaughtExceptionHandler() {

					@Override
					public void uncaughtException(Thread t, Throwable e) {
						e.printStackTrace();
						e.printStackTrace();
//						try {
//							Thread.sleep(2000);
//						} catch (InterruptedException e1) {
//							e1.printStackTrace();
//						}
						StackTraceElement[] stackTraceElements = e.getStackTrace();
						Log.error(e.toString());
						System.out.println("Caused by: " +e.toString());
						for(StackTraceElement element : stackTraceElements) {
							Log.error(element.toString());
							System.out.println("\tat" +element.toString());
						}
						
						Throwable cause = e.getCause();
						while(null != cause) {
							System.err.println("Caused by: " + cause.toString());
							StackTraceElement[] causeStackTraceElements = cause.getStackTrace();
							for(StackTraceElement element : causeStackTraceElements) {
								Log.error(element.toString());
								System.err.println("\tat"+element.toString());
							}
							cause = cause.getCause();
						}
						
//						Log.error(cause.toString());
//						Log.error("Caused by: " + e.toString());
					}

				});
		test1();
	}

	private static void test1() throws Exception {
		try {
			test2();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			// 1 Exception bussinessEx = new Exception("packag exception");
			// bussinessEx.initCause(ex);
			// throw bussinessEx;
			 throw new Exception("packag exception", new Exception("sdsdsds",ex));
//			 throw (Exception)ex.fillInStackTrace().initCause(ex);
		}
	}

	private static void test2() {
		test3();
	}

	private static void test3() {
		throw new NullPointerException("str is null");
	}

}