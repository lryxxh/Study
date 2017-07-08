/**
 * 
 */
package log;


/**
 * @author HMI-Lenovo
 *
 */
public class Test {
	
	public void test() {
		new Thread(){
			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				while(true) {
					
					int aa[] = test();
					System.out.println(aa[0]);
				}
			}
			
			public int[] test() {
				int a = 1;
				int b = 0;
//				int c = a / b;
				int[] aa = {11111111};
				return aa;
			}
		}.start();
		
		Log.info( "-------------");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
