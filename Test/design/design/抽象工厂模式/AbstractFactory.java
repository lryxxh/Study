/**
 * AbstractFactory.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.抽象工厂模式;

/**
 * <定义>提供一个创建一系列相关或相互依赖对象的接口，而无需指定他们具体的类。
 * <本质>选择产品簇的实现：定义在抽象工厂里面的方法通常是有联系的，他们都是产品的某一部分或者是相互以来的。
 * <优点>
 * 1.分离接口和实现：客户端使用抽象工厂来创建需要的对象，而客户端根本就不知道具体的实现是谁，客户端只是面向产品的接口编程而已。
 * 2.使得切换产品簇变得容易：客户端选择不同的工厂实现，就相当于是在切换不同的产品簇。
 * <缺点>
 * 1.不太容易扩展新的产品：如果要添加新的产品，需要需该抽象工厂，这样导致要修改所有的工厂实现类。
 * 2.容易造成类层次复杂：如果要选择的层次过多，那么会造成整个类层次便得复杂。
 * <使用场景>
 * 1.如果希望一个系统独立于它的产品的创建，组合和表示的时候。换句话说，希望一个系统只是知道产品的接口，而不关心实现的时候。
 * 2.如果一个系统要由多个产品系列中的一个来配置的时候。换句话说，就是可以动态地切换产品簇的时候。
 * 3.如果要强调一系列相关产品的接口，以便联合使用它们的时候。
 * @author liurenyong 2013-12-20
 */
public abstract class AbstractFactory {

    public abstract ProductA createProductA();
    
    public abstract ProductB createProductB();
    
    public abstract ProductC createProductC();
}
