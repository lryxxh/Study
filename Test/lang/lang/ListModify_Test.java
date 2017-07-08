package lang;

import java.util.ArrayList;
import java.util.Iterator;

public class ListModify_Test {
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i<20;i++) {
			list.add(i);
		}
		Integer value = 0;
		for(Iterator<Integer> iterator = list.iterator(); iterator.hasNext() ; ) {
			value = iterator.next();
			if(value % 3 == 0) {
				iterator.remove();
				list.add(value * 10);
			}
		}
		System.out.println(list);
	}

}
