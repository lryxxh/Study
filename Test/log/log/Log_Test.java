/**
 * 
 */
package log;


/**
 * @author HMI-Lenovo
 * 
 */
public class Log_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Thread.setDefaultUncaughtExceptionHandler(
//				new UncaughtExceptionHandler() {
//
//					@Override
//					public void uncaughtException(Thread t, Throwable e) {
////						e.printStackTrace();
//						StackTraceElement[] stackTraceElements = e.getStackTrace();
//						Log.error("Caused by: " + e.toString());
//						System.out.println(e.toString());
//						for(StackTraceElement element : stackTraceElements) {
//							Log.error(element.toString());
//							System.out.println("\tat" +element.toString());
//						}
//						
//						Throwable cause = e.getCause();
//						while(null != cause) {
//							StackTraceElement[] causeStackTraceElements = cause.getStackTrace();
//							for(StackTraceElement element : causeStackTraceElements) {
//								Log.error(element.toString());
//								System.err.println(element.toString());
//							}
//							cause = cause.getCause();
//						}
//						
////						Log.error(cause.toString());
////						Log.error("Caused by: " + e.toString());
//					}
//
//				});
		Log.info("Hello world");
//		new Test().test();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		TTTT.syso();
//		TTTT.tttt();
	}

}
