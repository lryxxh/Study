package ����ģʽ.ö�ٵ���;

public class Client {
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			Singleton.uniqueInstance.singletonOperation();
		}
	}
}
