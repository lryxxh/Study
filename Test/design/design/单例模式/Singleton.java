/**
 * Singleton.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.����ģʽ;


/**
 * <����>��֤һ�������һ��ʵ�������ṩһ����������ȫ�ַ��ʵ㡣
 * <����>����ʵ����Ŀ
 * <�ŵ�>
 * 1.ʱ��Ϳռ䣺����ʽ�ǵ��͵�ʱ�任�ռ䣬����ʽ�ǵ��͵Ŀռ任ʱ�䡣
 * 2.�̰߳�ȫ������ͬ��������ʽ���̲߳���ȫ�ġ�
 * 3.����ʽ���̰߳�ȫ�ġ�
 * 4.˫�ؼ������
 * <ȱ��>
 * <ʹ�ó���>
 * 1.����Ҫ����һ�����ʵ��ֻ����һ�������ͻ�ֻ�ܴ�һ��ȫ�ַ��ʵ������ʱ������ѡ�õ���ģʽ��
 * @author liurenyong 2013-12-24
 */
public class Singleton {
    
    private Singleton() {
        
    }
    
    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
    
    /**
     * �༶�ڲ��ౣ֤ͬ����
     * @author liurenyong 2013-12-24
     */
    private static class SingletonHolder {
        private static Singleton instance = new Singleton();
    }

}
