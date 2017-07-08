package proxy;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


public class ProxyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		String cacheDir = System.getenv("deployment.user.cachedir");
//		System.out.println("env cachedir : " + cacheDir);
//		if(cacheDir == null || "".equals(cacheDir)) {
//			cacheDir= System.getProperty("deployment.user.cachedir");
//		}
//		System.out.println("prop cachedir : " + cacheDir);
//		cacheDir = "C:/Users/LRY/AppData/LocalLow/Sun/Java/Deployment/cache";
////		if(cacheDir != null && !cacheDir.equals("")) {
//			File file = new File(cacheDir);
//			file.setWritable(true);
//			
//			System.out.println(file);
//			deleteFile(file);
//			System.out.println(file.exists());
//		}
		List list2 = new ArrayList();
//		ArrayListInvocationHandler handler = new ArrayListInvocationHandler();
//		handler.setObject(list2);
//		final List list = (List) Proxy.newProxyInstance(ArrayList.class.getClassLoader(), new Class[]{List.class}, handler);
		final List list = (List) Proxy.newProxyInstance(ArrayList.class.getClassLoader(), new Class[]{List.class}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return null;
			}
		});
		new Thread() {
			public void run() {
				list.add(1);
			};
		}.start();
		new Thread() {
			public void run() {
				list.add(2);
			};
		}.start();
		PropertyChangeSupport support = new PropertyChangeSupport(list);
		support.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("------");
			}
		});
		BufferedReader reader =new BufferedReader( new InputStreamReader(System.in));
		try {
			String line = reader.readLine();
			list2.add(Integer.parseInt(line));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteFile(File file) {
		if(file.isDirectory()) {
			for(File subFile : file.listFiles()) {
				deleteFile(subFile);
			}
		} else {
			file.delete();
		}
	}
	
	static class ArrayListInvocationHandler implements InvocationHandler{
		
		Object object = null;
		
		public void setObject(Object object) {
			this.object = object;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			System.out.println("before");
			System.err.println(method + " " + args);
			System.out.println("end");
			return method.invoke(object	, args);
		}
		
	}

}
