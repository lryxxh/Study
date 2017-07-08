/**
 * TemplateMethod.java
 * Created by liurenyong at 2013-12-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.模板方法;

/**
 * <定义>定义一个操作中的算法骨架，而将一些步骤延迟到子类中，模板方法使得子类可以不改变一个算法的结构即可冲定义该算法的某些特定步骤。
 * <本质>固定算法骨架
 * <优点>
 * 1.帮助封装 ：实现组件封装，让组件外部能真正面向接口编程
 * 2.解耦：实现客户的具体实现类的解耦
 * 3.代码复用：通过把不变行为搬移到超类，去除子类中的重复代码来体现它的优势
 * <缺点>
 * 1.可能增加客户端的复杂度：客户端需要能理解格格参数所代表的的具体功能和含义
 * 2.不方便扩展子工厂：私有化简单工厂的构造方法，使用静态方法来创建接口，也就不能通过写简单工厂类的子类来改变创建接口的方法的行为。
 * <使用场景>
 * 1.完全封装隔离具体实现，让外部只能通过接口来完成操作封装体
 * 2.如果想要把对外创建对象的职责集中管理和控制，可以选用简单工厂，一个简单工厂可以创建很多的，不相关的对象，可以把对外创建对象的职责集中到一个简单工厂来，从而实现集中管理和控制
 
 * @author liurenyong 2013-12-19
 */
public abstract class TemplateMethod {
    
    /** 
     * 模板方法，定义算法骨架
     */
    public final void templateMethod() {
        doPrimitiveOperation1();
        doPrimitiveOperation2();
    }

    /** 
     * 抽象行为，放到子类实现。
     * 原语操作1.
     */
    public abstract void doPrimitiveOperation2();

    /** 
     * 抽象行为，放到子类实现。
     * 原语操作2.
     */
    public abstract void doPrimitiveOperation1();

}
