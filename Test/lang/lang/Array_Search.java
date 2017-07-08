package lang;

import java.util.Arrays;

public class Array_Search {
	
	public static void main(String[] args) {
		
		int[] array = new int[]{1,3,4,6,7,8,9,10,20,25};
		int a = Arrays.binarySearch(array, 22);
		System.out.println(a);
		
	}

}
