/**
 * SimpleFactory.java
 * Created by liurenyong at 2013-12-18
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.�򵥹���;

/**
 * <����>�ṩһ����������ʵ���Ĺ��ܣ��������ϵ�����ʵ�֡�������ʵ�������Ϳ����ǽӿڣ������࣬Ҳ�����Ǿ�����.
 * <����>ѡ��ʵ��
 * <�ŵ�>
 * 1.������װ ��ʵ�������װ��������ⲿ����������ӿڱ��
 * 2.���ʵ�ֿͻ��ľ���ʵ����Ľ���
 * <ȱ��>
 * 1.�������ӿͻ��˵ĸ��Ӷȣ��ͻ�����Ҫ����������������ĵľ��幦�ܺͺ���
 * 2.��������չ�ӹ�����˽�л��򵥹����Ĺ��췽����ʹ�þ�̬�����������ӿڣ�Ҳ�Ͳ���ͨ��д�򵥹�������������ı䴴���ӿڵķ�������Ϊ��
 * <ʹ�ó���>
 * 1.��ȫ��װ�������ʵ�֣����ⲿֻ��ͨ���ӿ�����ɲ�����װ��
 * 2.�����Ҫ�Ѷ��ⴴ�������ְ���й���Ϳ��ƣ�����ѡ�ü򵥹�����һ���򵥹������Դ����ܶ�ģ�����صĶ��󣬿��԰Ѷ��ⴴ�������ְ���е�һ���򵥹��������Ӷ�ʵ�ּ��й���Ϳ���
 * @author liurenyong 2013-12-18
 */
public class SimpleFactory {
    
    public static Object createObject(int type) {
        Object obj = null;
        switch (type) {
        case 0:
            obj = new Integer(0);
            break;
        case 1:
            obj = new String("0");
            break;
        default:
            obj = new Object();
            break;
        }
        return obj;
    }

}
