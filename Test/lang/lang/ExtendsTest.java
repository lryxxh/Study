package lang;
public class ExtendsTest {

	//
	public int a = 123;

	public static int b = 12345;

	public static void t1() {
		System.err.println("t1");
	}

	/**
	 * <pre>
	 * ���ԣ� a = b + c;
	 * ���ƣ� �� b c
	 * </pre>
	 * 
	 * <pre>
	 * ���ԣ� a = b + c;
	 * �Ҷ�����we 
	 * ˵�ķ�ɹ˪ˮ���
	 * </pre>
	 */
	public void t2(String a) {
		System.err.println("t2");
	}

	public static void main(String[] args) {
		ExtendsTest test234 = new Sub();
		test234.t1();
		test234.t2("");
		System.out.println(test234.a);
		System.out.println(test234.b);
	}

}

class Sub extends ExtendsTest {

	public int a = 123456;

	public static int b = 12345678;

	public static void t1() {
		System.err.println("sub t1");
	}

	public void t2(String a) {
		System.err.println("sub t2");
	}
}
