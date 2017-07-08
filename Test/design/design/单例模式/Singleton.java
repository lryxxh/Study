/**
 * Singleton.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.单例模式;


/**
 * <定义>保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 * <本质>控制实例数目
 * <优点>
 * 1.时间和空间：懒汉式是典型的时间换空间，饿汉式是典型的空间换时间。
 * 2.线程安全：不加同步的懒汉式是线程不安全的。
 * 3.饿汉式是线程安全的。
 * 4.双重检查锁。
 * <缺点>
 * <使用场景>
 * 1.当需要控制一个类的实例只能有一个，而客户只能从一个全局访问点访问它时，可以选用单例模式。
 * @author liurenyong 2013-12-24
 */
public class Singleton {
    
    private Singleton() {
        
    }
    
    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
    
    /**
     * 类级内部类保证同步。
     * @author liurenyong 2013-12-24
     */
    private static class SingletonHolder {
        private static Singleton instance = new Singleton();
    }

}
