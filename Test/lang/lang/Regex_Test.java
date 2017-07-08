/**
 * 
 */
package lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试.
 * 
 * @author HMI-Lenovo
 * 
 */
@SuppressWarnings("unused")
public class Regex_Test {

	public static void main(String[] args) throws InterruptedException {
//		System.out.println(Integer.toHexString('A'));
//		System.out.println('\u0041');
//		test_CASE_INSENSITIVE("Abcd");
//		test_UNIX_LINES("abcdf\rabcd");
//		test_DOTALL("abcd\nabc");
//		test_UNICODE_CASE("\u0041BCD");
//		test_COMMENTS("bcdwewbcd");
//		test_POSITIVE_LOOKAHEAD("Windows 98, Windows NT, Windows XP, Windows Windows XP");
//		test_NEGATIVE_LOOKAHEAD("Windows 98, Windows NT, Windows XP, Windows Windows 98");
//		test_POSITIVE_LOOKBEHIND("Windows 98 Windows NT Windows XP Windows Windows 98");
//		test_NEGATIVE_LOOKBEHIND("Windows 98 Windows NT Windows XP Windows Windows 98");
//		testFIX_GROUP("1abcde2");
		test_GROUP("");
	}

	/**
	 * 测试启用大小写匹配.（？i）
	 * 
	 * @throws InterruptedException
	 */
	private static void test_CASE_INSENSITIVE(String string)
			throws InterruptedException {
		Pattern pattern = Pattern.compile("(?i)a{1}bcd");// 启用大小写匹配，则不区分大小写
		matcher(pattern, "Abcd");
		pattern = Pattern.compile("a{1}bcd");// 不启用大小写匹配，则区分大小写
		matcher(pattern, "Abcd");

	}

	/**
	 * 测试启用 ，.、^ 和 $ 的行为中仅识别 '\n' 行结束符,综合测试正则表达式多行模式（？dm）
	 * <pre>测试时需要注意默认情况下正则表达式仅在整个输入序列的开头和结尾处匹配。所以如果要测试这个例子需要将
	 * 正则表达式开启多行模式.
	 * </pre>
	 * 
	 * @throws InterruptedException
	 */
	private static void test_UNIX_LINES(String string)
			throws InterruptedException {
		//在多行情况下测试，启动UNIX模式，只识别\n为行结束符
		Pattern pattern = Pattern.compile("(?md)^abcd");
		matcher(pattern, "abcdf\rabcd");
		pattern = Pattern.compile("(?m)^abcd");
		matcher(pattern, "abcdf\rabcd");

	}

	
	/**
	 * <pre>
	 * 测试正则表达式DOTALL模式，此模式下.可以匹配任何字符，
	 * 包括行结束符，默认情况下不能匹配结束结束符<?s>
	 * </pre>
	 * 
	 * @throws InterruptedException
	 */
	private static void test_DOTALL(String string)
			throws InterruptedException {
		//在启用DOTALL模式下，.可以匹配任何字符
		Pattern pattern = Pattern.compile("(?s)abcd.*$");
		matcher(pattern, "abcd\nabc");
		
		//默认情况下，.不可以匹配行结束符
		pattern = Pattern.compile("abcd.*$");
		matcher(pattern, "abcd\nabc");

	}
	

	/**
	 * <pre>
	 * 测试正则表达式在<?i>模式（忽略大小写）下，可以通过unicode来判断匹配。（？u）
	 * </pre>
	 * 
	 * @throws InterruptedException
	 */
	private static void test_UNICODE_CASE(String string)
			throws InterruptedException {
		//在启用UNICODE_CASE模式下，在启动<？i>下可以通过unicode来进行匹配
		Pattern pattern = Pattern.compile("(?iu)Abcd");
		matcher(pattern, "\u0041BCD");
		
		//默认情况下，不能通过unicode来进行匹配
		pattern = Pattern.compile("(i)Abcd");
		matcher(pattern, "\u0041BCD");

	}
	
	/**
	 * <pre>
	 * 测试正则表达式在此模式将忽略空白和在结束行之前以 # 开头的嵌入式注释.<x>
	 * </pre>
	 * 
	 * @throws InterruptedException
	 */
	private static void test_COMMENTS(String string)
			throws InterruptedException {
		//在启用COMMENTS模式下，表达式将忽略空白\s(空格，tab，换行等.\t\n\x0B\f\r)以及#到换行之间的注释
		Pattern pattern = Pattern.compile("(?x)b#123  \ncd");
		matcher(pattern, "bcdwewbcd");
		
		//默认情况下，不忽略空白以及注释
		pattern = Pattern.compile("bc d");
		matcher(pattern, "bcdwewbcd");

	}
	
	/**
	 * <font color='red'>正向正预查，不回溯</font>
	 *  <pre>通过零宽度的正 lookahead（？=X）,(?=X) 表示当前位置（即字符的缝隙）后面允许出现的字符，这是一个非获取匹配，也就是说，该匹配不需要获取供以后使用。
	 *  比如：表示式 a(?=b)，在字符串为 ab 时，可能匹配 a，后面的 (?=b) 表示，a 后面的缝隙，可以看作是零宽度。
	 * 	预查不消耗字符，也就是说，在一个匹配发生后，在最后一次匹配之后立即开始下一次匹配的搜索，而不是从包含预查的字符之后开始。 </pre>
	 */
	private static void test_POSITIVE_LOOKAHEAD(String string) throws InterruptedException{
		//测试情况下，Windows 98, Windows NT, Windows XP, Windows Windows XP将从第一个匹配Windows NT开始的Windows匹配成功，
		//但是这是一个非捕获匹配，所以匹配后返回的是Windows而不是Windows NT。继续匹配Windows XP， 返回Windows
		//继续匹配，则匹配Windows Windows，之后，因为这个匹配中预查不消耗字符，下一次匹配从Windows Windows 匹配后的 第二个Windows开始匹配，
		//所以可以继续匹配出一个Windows XP
		Pattern pattern = Pattern.compile("Windows (?=NT|XP|Windows)");
		matcher(pattern, "Windows 98, Windows NT, Windows XP, Windows Windows XP");
	}
	
	/**
	 * <font color='red'>正向负预查，不回溯</font>
	 *  <pre>通过零宽度的负 lookahead（？！X）,(?！X) 表示当前位置（即字符的缝隙）后面不允许出现的字符，这是一个非获取匹配，也就是说，该匹配不需要获取供以后使用。
	 *  比如：表示式 a(?=b)，在字符串为 ab 时，不能匹配ab，后面的 (?=b) 表示，a 后面的缝隙，可以看作是零宽度。
	 * 	预查不消耗字符，也就是说，在一个匹配发生后，在最后一次匹配之后立即开始下一次匹配的搜索，而不是从包含预查的字符之后开始。 </pre>
	 */
	private static void test_NEGATIVE_LOOKAHEAD(String string) throws InterruptedException{
		//测试情况下，Windows 98, Windows NT, Windows XP, Windows Windows 98将从第一个匹配Windows 98开始的Windows匹配成功，
		//但是这是一个非捕获匹配，所以匹配后返回的是Windows而不是Windows 98。继续匹配,按照匹配规则，则可以匹配一个Windows 98，返回Windows
		Pattern pattern = Pattern.compile("Windows (?!NT|XP|Windows)");
		matcher(pattern, "Windows 98, Windows NT, Windows XP, Windows Windows 98");
	}
	
	/**
	 * <font color='red'>测试反向正预查，不回溯</font>
	 *  <pre>通过零宽度的负 lookbehind（？<=X）,(?<=X) 表示当前位置（即字符的缝隙）前面允许出现的字符，这是一个非获取匹配，也就是说，该匹配不需要获取供以后使用。
	 *  比如：表示式 a(?<=b)，在字符串为 aba 时，匹配b，后面的 (?<=b) 表示，a 前面面的缝隙，可以看作是零宽度。
	 * 	预查不消耗字符，也就是说，在一个匹配发生后，在最后一次匹配之后立即开始下一次匹配的搜索，而不是从包含预查的字符之后开始。 在（？<=X）右侧没有匹配字符的情况下，
	 * 将忽略(?<=X)，例如如果abc (?<=NT)去匹配abc bcd,则可以匹配到abc，因为（？<NT）右侧没有匹配符，所以将忽略（？<NT）
	 * </pre>
	 * 
	 */
	private static void test_POSITIVE_LOOKBEHIND(String string) throws InterruptedException {
		//（？<=X）在判断的左侧，而（？=X）在判断的右侧。所以如下的例子中判断的条件是
		// Windows左侧需要有NT 或 XP 或Windows,同样，匹配结果不返回X，
		Pattern pattern = Pattern.compile("(?<=NT|XP|Windows) Windows");
		matcher(pattern, "Windows 98 Windows NT Windows XP Windows Windows 98");
	}
	
	/**
	 * <font color='red'>测试反向负预查，不回溯</font>
	 *  <pre>通过零宽度的负 lookbehind（？<！X）,(?<！X) 表示当前位置（即字符的缝隙）前面不允许出现的字符，这是一个非获取匹配，也就是说，该匹配不需要获取供以后使用。
	 *  比如：表示式 (?<！b)a，在字符串为 aba 时，匹配第一个a，前面的 (?<！b) 表示，a 前面面的缝隙，可以看作是零宽度。
	 * 	预查不消耗字符，也就是说，在一个匹配发生后，在最后一次匹配之后立即开始下一次匹配的搜索，而不是从包含预查的字符之后开始。 在（？<=X）右侧没有匹配字符的情况下，
	 * 将忽略(?<！X)，例如如果abc(?<！NT)去匹配abcNTabc,则可以匹配到两个abc，因为（？<！NT）右侧没有匹配符，所以将忽略（？<！NT）
	 * </pre>
	 * 
	 */
	private static void test_NEGATIVE_LOOKBEHIND(String string) throws InterruptedException {
		//（？<！X）在判断的左侧，而（？！X）在判断的右侧。所以如下的例子中判断的条件是
		// Windows左侧不能有NT 或 XP 或Windows,同样，匹配结果不返回X，
		Pattern pattern = Pattern.compile("(?<!NT|XP|Windows) Windows");
		matcher(pattern, "Windows 98 Windows NT Windows XP Windows Windows 98");
	}
	
	
	/**
	 * 测试固化分组，此种情况下不会回溯，提高效率。(?>)
	 * @throws InterruptedException 
	 */
	private static void testFIX_GROUP(String string) throws InterruptedException {
		//1abcde2,匹配内容为1abcde2，此时1匹配1，.*匹配abcde2,这时因为2没有可以匹配的对象，
		//正常情况下（下面的例子）将会回溯，最终匹配结果为1abcde2.但是通过使用（？>），不会进行回溯，则没有匹配结果
		Pattern pattern = Pattern.compile("1(?>.*)2");
		matcher(pattern, string);
		
		pattern = Pattern.compile("1(.*)2");
		matcher(pattern, string);
		
		//此种情况下匹配，因为不回溯，所以在匹配integer不成功后匹配insert成功，有匹配记过
		pattern = Pattern.compile("\\b(?>integer|insert|in)\\b");
		matcher(pattern, "insert");
		
		//此种情况下，因为不回溯，所以在匹配in成功后直接按照in进行匹配，所以没有匹配结果
		pattern = Pattern.compile("\\b(?>in|integer|insert)\\b");
		matcher(pattern, "insert");
	}
	
	/**
	 * @param string
	 * @throws InterruptedException
	 */
	private static void test_GROUP(String string) throws InterruptedException {
		Pattern pattern = Pattern.compile("(([def]{3})(abc)(\\d+))Q\\3aaa\\2\\4");
		matcher(pattern, "eeeabc12Qabcaaaeee12");
	}
	
	private static void matcher(Pattern pattern, String string)
			throws InterruptedException {
		Matcher matcher = pattern.matcher(string);
		boolean matches = matcher.matches();
		System.out
				.print("matches : " + matches + " " + pattern.pattern() + " ");
		if (matches) {
			System.out.println(matcher.group());
		}

		Thread.sleep(500);
		boolean find = matcher.find();
		while (find) {
			System.err.println("find : " + find + "  " + matcher.group()
					+ " === start: " + matcher.start() + "  ; end: "
					+ matcher.end());
			find = matcher.find();
		}

		Thread.sleep(500);
		System.out.println();

	}
}
