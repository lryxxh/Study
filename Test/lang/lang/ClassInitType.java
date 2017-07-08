/**
 * ClassInitType.java
 * Created by liurenyong at 2013-11-25
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ������ʼ������������Ρ�
 * @author liurenyong 2013-11-25
 */
public class ClassInitType {
    
    
    /**
     * ִ��main������ڵ���ᱻ��ʼ�� 
     * @param args
     */
    public static void main(String[] args) {
        /*
         * new �ؼ�ֵ�����䣬��¡�������л��ᱻ��ʼ����
         */
        ClassInitType type = new ClassInitType();
       
        /*
         * �����ʼ��ʱ���ʼ������.
         */
        type.new InnerClass();
        
        /*
         * ִ��Java API��ĳЩ���䷽���������г�ʼ��
         */
        Method method = null;
        try {
            method.invoke(new Object(), new Object[]{});
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * �����ʼ��ʱ���ʼ������.
     * 
     * @author liurenyong 2013-11-25
     */
    class InnerClass extends ClassInitType {
        
    }
    
    /** ʹ�þ�̬�ֶλᱻ��ʼ��������final���ε�static���Ͳ��ᱻ��ʼ�� */
    public static String field = null;
    
    /**
     * ִ����ľ�̬�����ᱻ��ʼ����
     * @return the field
     */
    public static String getField() {
        return field;
    }
    
    /**
     *  ִ����ľ�̬�����ᱻ��ʼ����
     * @param field the field to set
     */
    public static void setField(String field) {
        ClassInitType.field = field;
    }

}
