/**
 * Adaptee.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.������ģʽ;

/**
 * <����>��һ����Ľӿ�ת���ɿͻ�ϣ��������һ���ӿڡ�Adapterģʽʹ��ԭ�����ڽӿڲ����ݶ�����һ��������Щ�����һ������
 * <����>ƥ��ת�������ù���
 * <�ŵ�>
 * 1.���õĸ����ԣ�������ܴ��ڣ����ǽӿڲ����ݣ���ôͨ��������ģʽ��������Щ���ܵõ����á�
 * 2.���õ���չ�ԣ���ʵ�����������ܵ�ʱ�򣬿��Ե����Լ������Ĺ��ܣ��Ӷ���Ȼ����չϵͳ�Ĺ��ܡ�
 * <ȱ��>
 * 1.�����ʹ��������������ϵͳ�ǳ����ң�������������а��ա�
 * <ʹ�ó���>
 * 1.�������Ҫʹ��һ���Ѿ����ڵ��࣬�������Ľӿڲ�����������������������ʹ��������ģʽ���������е�ʵ��ת��������Ҫ�Ľӿڡ�
 * 2.������봴��һ�����Ը��õ��࣬�������ܺ�һЩ�����ݵ���һ�����������������ʹ��������ģʽ����ʱ����Ҫʲô������ʲô��
 * 3.�����ʹ��һЩ�Ѿ����ڵ����࣬���ǲ����ܶ�ÿһ�����඼�������䣬�����������ѡ�ж�����������ֱ��������Щ����ĸ���Ϳ�������
 * @author liurenyong 2013-12-20
 */
public class Adapter implements Target{
    
    /** ��Ҫ���б�����Ľӿڶ��� */
    private Adaptee adaptee;
    
    /**
     * @param adaptee the adaptee to set
     */
    public void setAdaptee(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    /* (non-Javadoc)
     * @see design.������ģʽ.Target#request()
     */
    @Override
    public void request() {
        adaptee.specificRequest();
    }

}
