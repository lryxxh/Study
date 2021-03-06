/**
 * Handler.java
 * Created by liurenyong at 2013-12-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.职责链模式;

/**
 * <定义>使多个对象都有机会处理请求,从而避免请求的发送者和接收者之间的耦合关系.将这些对象连成一条链,并沿着这条链传递该请求,直到有一个对象处理它为止.
 * <本质>分离职责,动态组合.
 * <优点>
 * 1.请求者和接收者松散耦合:在职责链模式中,请求者并不知道接收者是谁,也不知道具体如何处理,请求者只是负责向职责链发出请求就可以了.
 * 2.动态组合职责:职责链模式会把工恩呢该处理分散到单独的职责对象中,然后在使用的时候,可以动态组合职责形成职责链,从而可以灵活地给对象分配职责,也可以灵活地实现和改变对象的职责.
 * <缺点>
 * 1.产生很多细粒度对象:职责链模式会把功能处理分散到单独的职责对象纵,也就是每个职责对象只处理一个方面的功能,要把整个业务处理完,需要很多职责对象的组合,这样会产生大量的细粒度职责对象.
 * 2.不一定能被处理:职责连模式每个职责对象只负责自己处理的那一部分,因此可能会出现某个请求,把真个链传递完了,都没有职责对象处理它.
 * <使用场景>
 * 1.如果有多个对象可以处理同一个请求,但是具体由哪个对象来处理该请求,是运行时刻动态确定的. 这种情况可以使用职责链模式,把处理请求的对象实现成职责对象,然后把他们构成一个职责链,当请求在这个链中传递的时候,具体由哪个职责对象来处理,会在运行时动态判断.
 * 2.如果想要动态指定处理一个请求的对象集合,可以使用职责链模式.职责链模式能动态地构建职责链,也就是动态地来决定到底哪些职责对象来参与到处理请求当中来,相当于是动态地指定了处理一个请求的职责对象集合.
 * 3.如果想在不明确指定接收者的情况下,向多个对象中的一个提交请求的话,可使用职责链模式.职责链模式实现了请求者和接收者之间的解耦,请求者不需要知道究竟是哪一个接收者对象来处理了请求.
 * @author liurenyong 2013-12-27
 */
public abstract class Handler {
    protected Handler successor;
    
    /**
     * @param successor the successor to set
     */
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
    
    /** 
     * 可以传递参数.
     */
    public abstract void handleRequest();

}
