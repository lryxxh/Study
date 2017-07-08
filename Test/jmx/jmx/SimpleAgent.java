package jmx;

import javax.management.*;

public class SimpleAgent {
	private MBeanServer server = null;
	ObjectName mbeanObjectName = null;
	String mbeanName = "jmx.SimpleDynamic";

	public SimpleAgent() {
		server = MBeanServerFactory.createMBeanServer();
		String domain = server.getDefaultDomain();
		try {
			mbeanObjectName = new ObjectName(domain + ":type=" + mbeanName);
			server.createMBean(mbeanName, mbeanObjectName);
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
			System.out.println("\nEXITING...\n");
			System.exit(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getState() throws AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException {
		return (String) server.getAttribute(mbeanObjectName, "State");
	}

	public Integer getNChange() throws AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException {
		return (Integer) server.getAttribute(mbeanObjectName, "NbChanges");
	}

	public void printMBeanInfo() {
		MBeanInfo info = null;
		try {
			info = server.getMBeanInfo(mbeanObjectName);
		} catch (Exception e) {
			System.out.println("\t!!! Could not get MBeanInfo object for " + mbeanName + " !!!");
			e.printStackTrace();
			return;
		}
		System.out.println("\nCLASSNAME: \t" + info.getClassName());
		System.out.println("\nDESCRIPTION: \t" + info.getDescription());
		System.out.println("\nATTRIBUTES");
		MBeanAttributeInfo[] attrInfo = info.getAttributes();
		if (attrInfo.length > 0) {
			for (int i = 0; i < attrInfo.length; i++) {
				System.out.println(" ** NAME: \t" + attrInfo[i].getName());
				System.out.println(" DESCR: \t" + attrInfo[i].getDescription());
				System.out.println(" TYPE: \t" + attrInfo[i].getType() + "\tREAD: " + attrInfo[i].isReadable() + "\tWRITE: " + attrInfo[i].isWritable());
			}
		} else
			System.out.println(" ** No attributes **");
		System.out.println("\nCONSTRUCTORS");
		MBeanConstructorInfo[] constrInfo = info.getConstructors();
		for (int i = 0; i < constrInfo.length; i++) {
			System.out.println(" ** NAME: \t" + constrInfo[i].getName());
			System.out.println(" DESCR: \t" + constrInfo[i].getDescription());
			System.out.println(" PARAM: \t" + constrInfo[i].getSignature().length + " parameter(s)");
		}
		System.out.println("\nOPERATIONS");
		MBeanOperationInfo[] opInfo = info.getOperations();
		if (opInfo.length > 0) {
			for (int i = 0; i < opInfo.length; i++) {
				System.out.println(" ** NAME: \t" + opInfo[i].getName());
				System.out.println(" DESCR: \t" + opInfo[i].getDescription());
				System.out.println(" PARAM: \t" + opInfo[i].getSignature().length + " parameter(s)");
			}
		} else
			System.out.println(" ** No operations ** ");
		System.out.println("\nNOTIFICATIONS");
		MBeanNotificationInfo[] notifInfo = info.getNotifications();
		if (notifInfo.length > 0) {
			for (int i = 0; i < notifInfo.length; i++) {
				System.out.println(" ** NAME: \t" + notifInfo[i].getName());
				System.out.println(" DESCR: \t" + notifInfo[i].getDescription());
			}
		}
	}

	public void reset() throws InstanceNotFoundException, MBeanException, ReflectionException {
		server.invoke(mbeanObjectName, "reset", null, null);
	}

	public void setState(String state) {
		Attribute nbChangesAttribute = new Attribute("State", state);
		try {
			server.setAttribute(mbeanObjectName, nbChangesAttribute);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setNChange(Integer nchange) {
		Attribute nbChangesAttribute = new Attribute("NbChanges", nchange);
		try {
			server.setAttribute(mbeanObjectName, nbChangesAttribute);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SimpleAgent agent = new SimpleAgent();
		try {
			agent.printMBeanInfo();
			System.out.println(agent.getState());
			System.out.println(agent.getNChange());
			agent.setState("come on, man");
			// agent.setNChange(new Integer(6));
			System.out.println(agent.getState());
			System.out.println(agent.getNChange());
			agent.reset();
			System.out.println(agent.getState());
			System.out.println(agent.getNChange());
			agent.setState("sds");
			System.out.println(agent.getState());
			System.out.println(agent.getNChange());
			agent.setState("sds2");
			System.out.println(agent.getState());
			System.out.println(agent.getNChange());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}