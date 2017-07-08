/**
 * 
 */
package lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ������ʽ����.
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
	 * �������ô�Сдƥ��.����i��
	 * 
	 * @throws InterruptedException
	 */
	private static void test_CASE_INSENSITIVE(String string)
			throws InterruptedException {
		Pattern pattern = Pattern.compile("(?i)a{1}bcd");// ���ô�Сдƥ�䣬�����ִ�Сд
		matcher(pattern, "Abcd");
		pattern = Pattern.compile("a{1}bcd");// �����ô�Сдƥ�䣬�����ִ�Сд
		matcher(pattern, "Abcd");

	}

	/**
	 * �������� ��.��^ �� $ ����Ϊ�н�ʶ�� '\n' �н�����,�ۺϲ���������ʽ����ģʽ����dm��
	 * <pre>����ʱ��Ҫע��Ĭ�������������ʽ���������������еĿ�ͷ�ͽ�β��ƥ�䡣�������Ҫ�������������Ҫ��
	 * ������ʽ��������ģʽ.
	 * </pre>
	 * 
	 * @throws InterruptedException
	 */
	private static void test_UNIX_LINES(String string)
			throws InterruptedException {
		//�ڶ�������²��ԣ�����UNIXģʽ��ֻʶ��\nΪ�н�����
		Pattern pattern = Pattern.compile("(?md)^abcd");
		matcher(pattern, "abcdf\rabcd");
		pattern = Pattern.compile("(?m)^abcd");
		matcher(pattern, "abcdf\rabcd");

	}

	
	/**
	 * <pre>
	 * ����������ʽDOTALLģʽ����ģʽ��.����ƥ���κ��ַ���
	 * �����н�������Ĭ������²���ƥ�����������<?s>
	 * </pre>
	 * 
	 * @throws InterruptedException
	 */
	private static void test_DOTALL(String string)
			throws InterruptedException {
		//������DOTALLģʽ�£�.����ƥ���κ��ַ�
		Pattern pattern = Pattern.compile("(?s)abcd.*$");
		matcher(pattern, "abcd\nabc");
		
		//Ĭ������£�.������ƥ���н�����
		pattern = Pattern.compile("abcd.*$");
		matcher(pattern, "abcd\nabc");

	}
	

	/**
	 * <pre>
	 * ����������ʽ��<?i>ģʽ�����Դ�Сд���£�����ͨ��unicode���ж�ƥ�䡣����u��
	 * </pre>
	 * 
	 * @throws InterruptedException
	 */
	private static void test_UNICODE_CASE(String string)
			throws InterruptedException {
		//������UNICODE_CASEģʽ�£�������<��i>�¿���ͨ��unicode������ƥ��
		Pattern pattern = Pattern.compile("(?iu)Abcd");
		matcher(pattern, "\u0041BCD");
		
		//Ĭ������£�����ͨ��unicode������ƥ��
		pattern = Pattern.compile("(i)Abcd");
		matcher(pattern, "\u0041BCD");

	}
	
	/**
	 * <pre>
	 * ����������ʽ�ڴ�ģʽ�����Կհ׺��ڽ�����֮ǰ�� # ��ͷ��Ƕ��ʽע��.<x>
	 * </pre>
	 * 
	 * @throws InterruptedException
	 */
	private static void test_COMMENTS(String string)
			throws InterruptedException {
		//������COMMENTSģʽ�£����ʽ�����Կհ�\s(�ո�tab�����е�.\t\n\x0B\f\r)�Լ�#������֮���ע��
		Pattern pattern = Pattern.compile("(?x)b#123  \ncd");
		matcher(pattern, "bcdwewbcd");
		
		//Ĭ������£������Կհ��Լ�ע��
		pattern = Pattern.compile("bc d");
		matcher(pattern, "bcdwewbcd");

	}
	
	/**
	 * <font color='red'>������Ԥ�飬������</font>
	 *  <pre>ͨ�����ȵ��� lookahead����=X��,(?=X) ��ʾ��ǰλ�ã����ַ��ķ�϶������������ֵ��ַ�������һ���ǻ�ȡƥ�䣬Ҳ����˵����ƥ�䲻��Ҫ��ȡ���Ժ�ʹ�á�
	 *  ���磺��ʾʽ a(?=b)�����ַ���Ϊ ab ʱ������ƥ�� a������� (?=b) ��ʾ��a ����ķ�϶�����Կ��������ȡ�
	 * 	Ԥ�鲻�����ַ���Ҳ����˵����һ��ƥ�䷢���������һ��ƥ��֮��������ʼ��һ��ƥ��������������ǴӰ���Ԥ����ַ�֮��ʼ�� </pre>
	 */
	private static void test_POSITIVE_LOOKAHEAD(String string) throws InterruptedException{
		//��������£�Windows 98, Windows NT, Windows XP, Windows Windows XP���ӵ�һ��ƥ��Windows NT��ʼ��Windowsƥ��ɹ���
		//��������һ���ǲ���ƥ�䣬����ƥ��󷵻ص���Windows������Windows NT������ƥ��Windows XP�� ����Windows
		//����ƥ�䣬��ƥ��Windows Windows��֮����Ϊ���ƥ����Ԥ�鲻�����ַ�����һ��ƥ���Windows Windows ƥ���� �ڶ���Windows��ʼƥ�䣬
		//���Կ��Լ���ƥ���һ��Windows XP
		Pattern pattern = Pattern.compile("Windows (?=NT|XP|Windows)");
		matcher(pattern, "Windows 98, Windows NT, Windows XP, Windows Windows XP");
	}
	
	/**
	 * <font color='red'>����Ԥ�飬������</font>
	 *  <pre>ͨ�����ȵĸ� lookahead������X��,(?��X) ��ʾ��ǰλ�ã����ַ��ķ�϶�����治������ֵ��ַ�������һ���ǻ�ȡƥ�䣬Ҳ����˵����ƥ�䲻��Ҫ��ȡ���Ժ�ʹ�á�
	 *  ���磺��ʾʽ a(?=b)�����ַ���Ϊ ab ʱ������ƥ��ab������� (?=b) ��ʾ��a ����ķ�϶�����Կ��������ȡ�
	 * 	Ԥ�鲻�����ַ���Ҳ����˵����һ��ƥ�䷢���������һ��ƥ��֮��������ʼ��һ��ƥ��������������ǴӰ���Ԥ����ַ�֮��ʼ�� </pre>
	 */
	private static void test_NEGATIVE_LOOKAHEAD(String string) throws InterruptedException{
		//��������£�Windows 98, Windows NT, Windows XP, Windows Windows 98���ӵ�һ��ƥ��Windows 98��ʼ��Windowsƥ��ɹ���
		//��������һ���ǲ���ƥ�䣬����ƥ��󷵻ص���Windows������Windows 98������ƥ��,����ƥ����������ƥ��һ��Windows 98������Windows
		Pattern pattern = Pattern.compile("Windows (?!NT|XP|Windows)");
		matcher(pattern, "Windows 98, Windows NT, Windows XP, Windows Windows 98");
	}
	
	/**
	 * <font color='red'>���Է�����Ԥ�飬������</font>
	 *  <pre>ͨ�����ȵĸ� lookbehind����<=X��,(?<=X) ��ʾ��ǰλ�ã����ַ��ķ�϶��ǰ��������ֵ��ַ�������һ���ǻ�ȡƥ�䣬Ҳ����˵����ƥ�䲻��Ҫ��ȡ���Ժ�ʹ�á�
	 *  ���磺��ʾʽ a(?<=b)�����ַ���Ϊ aba ʱ��ƥ��b������� (?<=b) ��ʾ��a ǰ����ķ�϶�����Կ��������ȡ�
	 * 	Ԥ�鲻�����ַ���Ҳ����˵����һ��ƥ�䷢���������һ��ƥ��֮��������ʼ��һ��ƥ��������������ǴӰ���Ԥ����ַ�֮��ʼ�� �ڣ���<=X���Ҳ�û��ƥ���ַ�������£�
	 * ������(?<=X)���������abc (?<=NT)ȥƥ��abc bcd,�����ƥ�䵽abc����Ϊ����<NT���Ҳ�û��ƥ��������Խ����ԣ���<NT��
	 * </pre>
	 * 
	 */
	private static void test_POSITIVE_LOOKBEHIND(String string) throws InterruptedException {
		//����<=X�����жϵ���࣬������=X�����жϵ��Ҳࡣ�������µ��������жϵ�������
		// Windows�����Ҫ��NT �� XP ��Windows,ͬ����ƥ����������X��
		Pattern pattern = Pattern.compile("(?<=NT|XP|Windows) Windows");
		matcher(pattern, "Windows 98 Windows NT Windows XP Windows Windows 98");
	}
	
	/**
	 * <font color='red'>���Է���Ԥ�飬������</font>
	 *  <pre>ͨ�����ȵĸ� lookbehind����<��X��,(?<��X) ��ʾ��ǰλ�ã����ַ��ķ�϶��ǰ�治������ֵ��ַ�������һ���ǻ�ȡƥ�䣬Ҳ����˵����ƥ�䲻��Ҫ��ȡ���Ժ�ʹ�á�
	 *  ���磺��ʾʽ (?<��b)a�����ַ���Ϊ aba ʱ��ƥ���һ��a��ǰ��� (?<��b) ��ʾ��a ǰ����ķ�϶�����Կ��������ȡ�
	 * 	Ԥ�鲻�����ַ���Ҳ����˵����һ��ƥ�䷢���������һ��ƥ��֮��������ʼ��һ��ƥ��������������ǴӰ���Ԥ����ַ�֮��ʼ�� �ڣ���<=X���Ҳ�û��ƥ���ַ�������£�
	 * ������(?<��X)���������abc(?<��NT)ȥƥ��abcNTabc,�����ƥ�䵽����abc����Ϊ����<��NT���Ҳ�û��ƥ��������Խ����ԣ���<��NT��
	 * </pre>
	 * 
	 */
	private static void test_NEGATIVE_LOOKBEHIND(String string) throws InterruptedException {
		//����<��X�����жϵ���࣬��������X�����жϵ��Ҳࡣ�������µ��������жϵ�������
		// Windows��಻����NT �� XP ��Windows,ͬ����ƥ����������X��
		Pattern pattern = Pattern.compile("(?<!NT|XP|Windows) Windows");
		matcher(pattern, "Windows 98 Windows NT Windows XP Windows Windows 98");
	}
	
	
	/**
	 * ���Թ̻����飬��������²�����ݣ����Ч�ʡ�(?>)
	 * @throws InterruptedException 
	 */
	private static void testFIX_GROUP(String string) throws InterruptedException {
		//1abcde2,ƥ������Ϊ1abcde2����ʱ1ƥ��1��.*ƥ��abcde2,��ʱ��Ϊ2û�п���ƥ��Ķ���
		//��������£���������ӣ�������ݣ�����ƥ����Ϊ1abcde2.����ͨ��ʹ�ã���>����������л��ݣ���û��ƥ����
		Pattern pattern = Pattern.compile("1(?>.*)2");
		matcher(pattern, string);
		
		pattern = Pattern.compile("1(.*)2");
		matcher(pattern, string);
		
		//���������ƥ�䣬��Ϊ�����ݣ�������ƥ��integer���ɹ���ƥ��insert�ɹ�����ƥ��ǹ�
		pattern = Pattern.compile("\\b(?>integer|insert|in)\\b");
		matcher(pattern, "insert");
		
		//��������£���Ϊ�����ݣ�������ƥ��in�ɹ���ֱ�Ӱ���in����ƥ�䣬����û��ƥ����
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
