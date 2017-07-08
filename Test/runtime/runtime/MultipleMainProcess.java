package runtime;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;


public class MultipleMainProcess {
	
//	private static class InnerClass {
//		private static final MultipleMainProcess instance = new MultipleMainProcess();
//	}

	private MultipleMainProcess() {
		JFrame frame = new JFrame();
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
//	public static MultipleMainProcess getInstance() {
//		return InnerClass.instance;
//	}
	
	public static Process lanuchApplicationInProcess() {
		Process process = null;
		try {
			Map<String, String> propertyMap =  System.getenv();
			String[] env = new String[propertyMap.size() + 1];
			Iterator<String> keyIt = propertyMap.keySet().iterator();
			int index = 0;
			while(keyIt.hasNext())
			{
				String key = keyIt.next();
				env[index] = key + "=" + propertyMap.get(key);
				index++;
			}
			Runtime run = Runtime.getRuntime();
			process = run.exec("java test.MultipleMainProcess",null,new File("./bin"));
			System.out.println(process);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return process;
	}
	
	public static void main(String[] args) {
		new MultipleMainProcess();
	}

}
