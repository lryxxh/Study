package lang;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;


public class WeakReferenceTest {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<data> list = new ArrayList();
		for(int i =0;i<5440000;i++){
			data d = new data();
			d.setX(i);
			d.setY(i);
			d.setTime(Calendar.getInstance().getTimeInMillis());
			list.add(d);
		}
		ArrayList temp = new ArrayList();
		WeakReference<ArrayList> reference = new WeakReference<ArrayList>(temp);
		for (int j=0;j<500;j++) {
			list.get(0).setTime(1234567890l*j);
			long total = Runtime.getRuntime().totalMemory();
			long free = Runtime.getRuntime().freeMemory();
//			System.err.println("total=" + total +"  " + (float)(total-free) * 100/total+"%" +" "+j);
			System.out.println("total=" + total/1024/1024 +"m  " + (float)(total-free) * 100/total+"%" +" "+j);
//			temp.clear();
//			temp = null;
			ArrayList tt = reference.get();
			if(tt==null) {
				tt = new ArrayList();
				tt.addAll((Collection) list.clone());
				System.out.println("-----------------------------------");
			} else {
				tt = (ArrayList) list.clone();
			}
			System.out.println(tt.size()+"   ");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		for (int j=0;j<500;j++) {
//			list.get(0).setTime(1234567890l*j);
//			long total = Runtime.getRuntime().totalMemory();
//			long free = Runtime.getRuntime().freeMemory();
////			System.err.println("total=" + total +"  " + (float)(total-free) * 100/total+"%" +" "+j);
//			System.out.println("total=" + total/1024/1024 +"m  " + (float)(total-free) * 100/total+"%" +" "+j);
////			temp.clear();
////			temp = null;
//			temp.clear();
//			temp.addAll((Collection) list.clone());
//			System.out.println(temp.size()+"   ");
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
	}

}
class data{
	int x ;
	int y;
	long time ;
	public data() {
	}
	public data(int x,int y, long time) {
		this.x = x;
		this.y = y;
		this.time = time;
				
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public long getTime() {
		return time;
	}
}