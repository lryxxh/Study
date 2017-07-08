/**
 * 
 */
package lang;

import java.util.regex.MatchResult;

/**
 * @author HMI-Lenovo
 *
 */
public class Pattern_Test {
	
	public static void main(String[] args) {
		testPattern();
	}

	/**
	 * 
	 */
	private static void testPattern() {
//		Pattern pattern = Pattern.compile("^[0-9]*[1-9][0-9]*$");
//		Pattern pattern = Pattern.compile("^(0|[1-9][0-9]*)$");
		Pattern2 pattern = Pattern2.compile("[bcd]\\Qttttt\\E[bcd]");
//		Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$");
		Matcher2 matcher = pattern.matcher("abcdd123abc");
		MatchResult result = null;
		while(matcher.find()) {
			result= matcher.toMatchResult();
			System.out.println("start:"+result.start() + "  end:" + result.end() + " value:" + result.group() + " pattern:" + matcher.pattern());
		}
		System.out.println(matcher.matches());
	}

}
