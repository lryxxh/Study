/**
 * Adaptee.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.适配器模式;

/**
 * <定义>将一个类的接口转换成客户希望的另外一个接口。Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 * <本质>匹配转换，复用功能
 * <优点>
 * 1.更好的复用性：如果功能存在，但是接口不兼容，那么通过适配器模式可以让这些功能得到复用。
 * 2.更好的扩展性：在实现适配器功能的时候，可以调用自己开发的功能，从而自然地扩展系统的功能。
 * <缺点>
 * 1.过多的使用适配器，会让系统非常凌乱，不容易整体进行把握。
 * <使用场景>
 * 1.如果你想要使用一个已经存在的类，但是它的接口不符合你的需求，这种情况可以使用适配器模式，来把已有的实现转换成你需要的接口。
 * 2.如果你想创建一个可以复用的类，这个类可能和一些不兼容的类一起工作，这种情况可以使用适配器模式，到时候需要什么就适配什么。
 * 3.如果想使用一些已经存在的子类，但是不可能对每一个子类都进行适配，这种情况可以选中对象适配器，直接适配这些子类的父类就可以啦。
 * @author liurenyong 2013-12-20
 */
public class Adapter implements Target{
    
    /** 需要持有被适配的接口对象 */
    private Adaptee adaptee;
    
    /**
     * @param adaptee the adaptee to set
     */
    public void setAdaptee(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    /* (non-Javadoc)
     * @see design.适配器模式.Target#request()
     */
    @Override
    public void request() {
        adaptee.specificRequest();
    }

}
