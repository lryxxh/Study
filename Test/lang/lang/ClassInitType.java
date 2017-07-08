/**
 * ClassInitType.java
 * Created by liurenyong at 2013-11-25
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 主动初始化类的六种情形。
 * @author liurenyong 2013-11-25
 */
public class ClassInitType {
    
    
    /**
     * 执行main方法入口的类会被初始化 
     * @param args
     */
    public static void main(String[] args) {
        /*
         * new 关键值，反射，克隆，反序列化会被初始化。
         */
        ClassInitType type = new ClassInitType();
       
        /*
         * 子类初始化时会初始化父类.
         */
        type.new InnerClass();
        
        /*
         * 执行Java API的某些反射方法会对类进行初始化
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
     * 子类初始化时会初始化父类.
     * 
     * @author liurenyong 2013-11-25
     */
    class InnerClass extends ClassInitType {
        
    }
    
    /** 使用静态字段会被初始化，但是final修饰的static类型不会被初始化 */
    public static String field = null;
    
    /**
     * 执行类的静态方法会被初始化。
     * @return the field
     */
    public static String getField() {
        return field;
    }
    
    /**
     *  执行类的静态方法会被初始化。
     * @param field the field to set
     */
    public static void setField(String field) {
        ClassInitType.field = field;
    }

}
