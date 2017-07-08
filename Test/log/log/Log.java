/**
 * 
 */
package log;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author HMI-Lenovo
 *
 */
public class Log {
	
	/**
	 * 用来存储类名以及对应的类的log
	 */
	private static HashMap<String, Logger> logMap = new HashMap<String, Logger>();
	
	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	private Log(){
	}
	
	/**
	 * 打印调试信息.
	 * @param clazz
	 * @param message
	 */
	public static void info(String message) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement element = elements[2];
		String className = element.getClassName();
		String methodInfo = element.getClassName() +"." +element.getMethodName() + "[" + element.getLineNumber()+"]: ";
		info(className, methodInfo + message);
	}
	
	/**
	 * 打印错误信息.
	 * @param message
	 */
	public static void error(String message) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement element = elements[2];
		String className = element.getClassName();
		String methodInfo = element.getClassName() +"." +element.getMethodName() + "[" + element.getLineNumber()+"]: ";
		error(className, methodInfo + message);
	}
	
	/**
	 * 打印调试信息.
	 * @param className
	 * @param message
	 */
	private static void info(String className, String message) {
		getLogMap(className).info(message);
	}
	
	/**
	 * 打印错误信息.
	 * @param className
	 * @param message
	 */
	private static void error(String className, String message) {
		getLogMap(className).error(message);
	}
	
	/**
	 * 得到log类.
	 * @param className
	 * @return
	 */
	private static Logger getLogMap(String className) {
		Logger logger = logMap.get(className);
		if(logger == null) {
			try {
				logger = Logger.getLogger(Class.forName(className));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			logMap.put(className, logger);
		}
		return logger;
	}
	
	public static void main(String[] args) {
		new Log();
		Log.info("ttttttttt");
	}
	
}
