package ����¼ģʽ.ʹ��ģʽ;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * �������ļ��б���ģ����������A�Ķ���ı���¼����
 */
public class FlowAMementoFileCareTaker {

	/**
	 * ���汸��¼����
	 * 
	 * @param memento
	 *            ������ı���¼����
	 */
	public void saveMemento(FlowAMockMemento memento) {
		// д���ļ���
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream("FlowAMemento")));
			out.writeObject(memento);
			System.out.println("now write file==========");
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ��ȡ������ı���¼����
	 * 
	 * @return ������ı���¼����
	 */
	public FlowAMockMemento retriveMemento() {
		FlowAMockMemento memento = null;
		// ���ļ��л�ȡ����¼����
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream("FlowAMemento")));
			memento = (FlowAMockMemento) in.readObject();
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return memento;
	}
}
