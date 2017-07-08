/**
 * Creater.java
 * Created by liurenyong at 2013-12-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.工厂方法;

/**
 * <定义>定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法使一个类的实例化延迟到其子类。
 * <本质>延迟到子类来选择实现。
 * <优点>
 * 1.可以在不知道具体实现的情况下变成：如果要使用某个产品对象，只需要使用产品的接口即可。无须关心具体的实现。
 * 2.更容易扩展对象的新版本：参照参数化工厂方法实现
 * 3.连接平行的类层次
 * <缺点>
 * 1.具体产品对象和工厂方法的耦合性：工厂方式是需要创建产品对象的，也就是需要选择具体的产品对象，并创建他们的实例，因此具体产品对象和工厂方法使耦合的。
 * 2.每一个产品对象都要创建一个对应的产品工厂
 * <使用场景>
 * 1.如果一个类需要创建某个接口的对象，但是又不知道具体的实现，这种情况可以选用工厂方法模式，把创建对象的哦你工作延迟到子类中去实现。
 * 2.如果一个类本身就希望由它的子类来创建所需的对象的时候，应该使用工厂方法模式。
 *
 * @author liurenyong 2013-12-19
 */
public abstract class Creater {
    
    public abstract Product factoryMethod();
    
    public void someOperation() {
        factoryMethod().operate();
    }

}
