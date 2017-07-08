package lang;
import java.util.ArrayList;

import agency.SocketConnectBase;
import agency.exception.LocatorException;
import agency.message.base.servicebus.CycleIPServiceBus;
import agency.message.base.servicebus.ServiceBusManager;
import agency.message.locater.Locator;
import agency.message.locater.LocatorResult;


public class Locator_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String para = args[0];
		if(para.equals("1")) {
			printLocators();
		}else if(para.equals("2")) {
			printLocatorPoint();
		} else if(para.equals("3")) {
			printLocator();
		}
	}
	
	public static void printLocators() {
		
		int division = 0;
		int appID = 3300000;
		int sysID = 1;
		String serviceName = ServiceBusManager.FILESERV;
		CycleIPServiceBus bus = new CycleIPServiceBus(division, appID, sysID, serviceName);
		System.out.println("section 1"+bus.getSocketInfos());

		division = 1;
		bus = new CycleIPServiceBus(division, appID, sysID, serviceName);
		System.out.println("section 2"+bus.getSocketInfos());
		
	}
	
	public static void printLocatorPoint() {
		ArrayList<String>locators = SocketConnectBase.getLocatorHostNames(1);
		System.out.println(locators);
	}
	
	public static void printLocator() {
		String serviceName = ServiceBusManager.FILESERV;
		int sysID = 1;
		int appID = 3300000;
		String serverName = null;
		String netMask = null;
		try {
			LocatorResult result = Locator.getLocatorResult(serviceName, sysID, appID, serverName, netMask);
			System.out.println(result);
		} catch (LocatorException e) {
			e.printStackTrace();
		}
	}

}
