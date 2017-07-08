/**
 * 
 */
package lang;

import java.text.NumberFormat;

/**
 * @author HMI-Lenovo
 *
 */
public class Double_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		doubleToRawLongBits(12132132424322.0002342423);
		System.out.println(Double.longBitsToDouble(4802545254950831104l));
	}
	
	private static void doubleToRawLongBits(double value) {
		long valueL = Double.doubleToRawLongBits(value);
		System.out.println(valueL);
	}

	private static void doubleFormatTest() {
		double d = 1.2234354;
		double t = 12.34242424324242;
		String string = String.valueOf(t);
		d = Double.valueOf(string);
		System.out.println(string);
		double a = 1232.443;
		double b = 12.33334343;
		double c = 11.11111111111111;
		double e = 12;
		NumberFormat format = NumberFormat.getInstance();
		
		format.setMaximumFractionDigits(10);
		format.setMinimumFractionDigits(0);
		
		System.out.println(format.format(a));
		System.out.println(format.format(b));
		System.out.println(format.format(c));
		System.out.println(format.format(d));
		System.out.println(format.format(e));
		
		Integer integer = new Integer(123);
		System.out.println(integer instanceof Number);
	}
}
