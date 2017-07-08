package ���󹤳�ģʽ.ʹ��ģʽ;

/**
 * ���󹤳��Ľӿڣ��������������Ʒ����Ĳ���
 */
public interface AbstractFactory {
	/**
	 * ����CPU�Ķ���
	 * 
	 * @return CPU�Ķ���
	 */
	public CPUApi createCPUApi();

	/**
	 * ��������Ķ���
	 * 
	 * @return ����Ķ���
	 */
	public MainboardApi createMainboardApi();
}
