package lang;

public class SuperClassMethod_Test {
	
	public static void main(String[] args) {
		new ClassMethod();
	}
	
}

class ClassMethod extends Teste3 {
	
	@Override
	public void test() {
		System.err.println("lllllllllllllllllllll");
	}
}

class Teste3 {
	public Teste3() {
		test();
	}
	
	public void test() {
		System.out.println("/////////////////");
	}
}
