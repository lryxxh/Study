package jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class JMX_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MBeanServer server = MBeanServerFactory.createMBeanServer();

			ObjectName helloName = new ObjectName("chengang:name=HelloWorld");
			server.registerMBean(new HelloWorld(), helloName);

			ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
			HtmlAdaptorServer adapter = new HtmlAdaptorServer(8082);
			server.registerMBean(adapter, adapterName);

			adapter.start();
			System.out.println("start.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
