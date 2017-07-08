/**
 * SimpleFactory.java
 * Created by liurenyong at 2013-12-18
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.简单工厂;

/**
 * <定义>提供一个创建对象实例的功能，而无须关系其具体实现。被创建实例的类型可以是接口，抽象类，也可以是具体类.
 * <本质>选择实现
 * <优点>
 * 1.帮助封装 ：实现组件封装，让组件外部能真正面向接口编程
 * 2.解耦：实现客户的具体实现类的解耦
 * <缺点>
 * 1.可能增加客户端的复杂度：客户端需要能理解格格参数所代表的的具体功能和含义
 * 2.不方便扩展子工厂：私有化简单工厂的构造方法，使用静态方法来创建接口，也就不能通过写简单工厂类的子类来改变创建接口的方法的行为。
 * <使用场景>
 * 1.完全封装隔离具体实现，让外部只能通过接口来完成操作封装体
 * 2.如果想要把对外创建对象的职责集中管理和控制，可以选用简单工厂，一个简单工厂可以创建很多的，不相关的对象，可以把对外创建对象的职责集中到一个简单工厂来，从而实现集中管理和控制
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
