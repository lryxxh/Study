package װ��ģʽ.example5;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Client {
	public static void main(String[] args) throws Exception {
		// ��ʽ����ļ�
		DataOutputStream dout = new DataOutputStream(new EncryptOutputStream2(
				new BufferedOutputStream(

				new FileOutputStream("MyEncrypt.txt"))));
		// Ȼ��Ϳ������������
		dout.write("abcdxy".getBytes());
		dout.close();
	}
}
