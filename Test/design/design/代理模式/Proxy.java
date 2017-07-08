/**
 * Proxy.java
 * Created by liurenyong at 2013-12-18
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.代理模式;

/**
 * <定义>为其他对象提供一种代理以控制这个对象的访问。
 * <本质>控制对象访问：通过代理目标对象，把代理对象插入到客户和目标对象之间，从而为客户和目标对象引入一定的间接性。
 * <分类>
 * 1.虚代理：根据需要来创建开销很大的对象，改对象只有在需要的时候才会被真正创建
 * 2.远程代理：用来在不同的地址空间上代表同一个对象，这个不同的地址空间可以是在本机，也可以在其他机器上。（RMI）
 * 3.copy-on-write代理：在客户端操作的时候，只有对象确实改变了，才会真的拷贝（或克隆）一个目标对象，算是虚代理的一个分支。
 * 4.保护代理：控制对原始对象的访问，如果需要，可以给不同的用户提供不同的访问权限，以控制他们对原始对象的访问。
 * 5.Cache代理：为那些昂贵操作的结果提供临时的存储空间，以便多个客户端可以共享这些结果。
 * 6.防火墙代理：保护对象不被恶意用户访问和操作。
 * 7.同步代理：使多个用户能够同时访问目标对象而没有冲突
 * 8.智能指引：在访问对象时执行一些附加操作，比如，对指向实际对象的引用计数，第一次引用一个持久对象时，将它装入内存等。
 * <优点>
 * <缺点>
 * <使用场景>
 * 1.需要为一个对象在不同的地址空间提供局部代表的时候，可以使用远程代理
 * 2.需要按照需要创建开销很大的对象的时候，可以使用虚代理
 *
 * @author liurenyong 2013-12-18
 */
public class Proxy implements Subject{
    
    RealSubject realSubject = null;
    
    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }
    
    /* (non-Javadoc)
     * @see design.代理模式.Subject#request()
     */
    @Override
    public void request() {
        //在转调具体的目标对象前，可以执行一些功能处理
        //转调具体的目标对象的方法
        realSubject.request();
    }

}
