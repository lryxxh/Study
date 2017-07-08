/**
 * 
 */
package lang;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author HMI-Lenovo
 *
 */
public class ConcurrentModification_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Map map = new Hashtable();
		for(int i = 0;i<1000000;i++) {
			map.put("a" + i,i);
		}
		
		new Thread() {
			public void run() {
				Set set = map.keySet();
				Iterator item = set.iterator();
				while(item.hasNext()) {
					Object obj = item.next();
					item.remove();
					System.out.println("Thread1 " + obj);
				}
			};
		}.start();
		
		new Thread() {
			public void run() {
				Set set = map.keySet();
				Iterator item = set.iterator();
				while(item.hasNext()) {
					Object obj = item.next();
					item.remove();
					System.out.println("Thread2 " + obj);
				}
			};
		}.start();

		
		System.out.println("--------------------------" + map.size());
	}

}
