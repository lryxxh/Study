/**
 * Mediator.java
 * Created by liurenyong at 2013-12-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.中介者模式;

/**
 * <定义>用一个中介对象来封装一系列的对象交互. 中介者使得各对象不需要显示地相互引用, 从而使其耦合松散,而且可以独立地改变他们之间的交互.
 * <本质>封装交互.
 * <优点>
 * 1.松散耦合:中介者模式通过把多个同事对象之间的交互封装到中介者对象里面,从而使得同事对象之间松散耦合,基本上可以做到互不依赖.
 * 2.集中控制交互:多个同时对象的交互,被封装在中介者对象里面集中管理,使得这些交互行为发生变化的时候,只需要修改中介者对象就可以了,当然如果是已经做好的系统,那就扩展中介者对象,而各个同时类不需要做修改.
 * 3.多对多变成一对多:没有使用中介者模式的时候,同事对象之间的关系通常是多对多的,引入中介者对象以后,中介者对象和同事对象的关系通常变成了双向的一对多,这会让对象的关系更容易理解和实现.
 * <缺点>
 * 1.过度集中化,如果同事对象的交互非常多, 而且比较复杂,当这些复杂性全部集中到中介者的时候,会导致中介者对象变得十分复杂,而且难于管理和维护.
 * <使用场景>
 * 1.如果一组对象之间的通信方式比较复杂,导致相互依赖,结构混乱,可以采用中介者模式,把这些对象相互的交互管理起来,各个对象都只需要和中介者交互,从而使得各个对象松散耦合,结构也更清晰易懂.
 * 2.如果一个对象引用很多的对象,并直接跟这些对象交互,导致难以复用该对象,可以采用中介者模式,把这个对象跟其他对象的交互封装到中介者对象里面,这个对象只需要和中介者对象交互就可以了.
 * 3.中介折模式一般应用于一组对象以定义良好但是复杂的方式进行通信的场合。
 * 4.定制一个分布在多个类中的行为，而又不想生成太多的子类的场合。
 * @author liurenyong 2013-12-27
 */
public interface Mediator {
    
    public void change(Colleague colleague);

}
