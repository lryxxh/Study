package 简单工厂模式.example6;

/**
 * 对某个接口的一种实现
 */
public class Impl implements Api {

	public void test1(String s) {
		System.out.println("Now In Impl. The input s==" + s);
	}
}
