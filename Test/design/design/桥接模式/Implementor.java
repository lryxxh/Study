/**
 * Implementor.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.桥接模式;

/**
 * <定义>将抽象部分与他的实现部分分离，使他们都可以独立地变化。
 * <本质>分离抽象和实现
 * <理解>
 * 1.什么是桥接：通俗点说就是在不同东西之间搭一个桥，然他们能够连接起来，可以相互通讯和使用。
 * 2.为何要桥接：桥接模式中，抽象部分和实现部分分离开来，虽然从程序结构上是分开了，但是在抽象部分实现的时候，还是需要使用具体的实现，此时，通过搭桥，可以使抽象部分通过这个桥就可以调用到实现部分的功能了。
 * 3.如何桥接：只要让抽象部分拥有实现部分的接口对象，就桥接上了，在抽象部分即可通过这个接口来调用具体实现部分的功能。
 * 4.独立变化：桥接模式的意图是使得抽象和实现可以独立变化，都可以分别扩充。
 * 5.动态变换功能：由于桥接模式中的抽象部分和实现部分是完全分离的，因此可以在运行时动态组合具体的真实实现，从而达到动态变换功能的目的。
 * 6.对象的继承关系是在编译时就定义好了，所以无法再运行时改变父类继承的实现。子类的实现与它的父类有非常紧密的依赖关系，以至于父类实现中的任何变化必然会导致子类发生变化。当你需要复用子类时，如果集成下来的实现不适合解决新的问题，则弗雷必须重写或被其他更适合的类替换。这种依赖关系限制了灵活性并最终显示了复用性。
 * <优点>
 * 1.分离抽象和实现部分：桥接模式分离了抽象部分和实现部分，从而极大地提高了系统的灵活性。
 * 2.更好的扩展性：分别定义接口，使得抽象部分和实现部分可以分别独立地扩展，而不会相互影响，从而大大地提高了系统的可扩展性。
 * 3.可动态地切换实现：由于桥接模式把抽象部分和实现部分分离开了，所以在实现桥接的时候，可以实现动态的选择和使用具体的实现。
 * 4.可减少子类的个数：
 * <缺点>
 * <使用场景>
 * 1.如果不希望在抽象部分和实现部分采用固定的绑定关系，可以采用桥接模式，来把抽象部分和实现部分分开，然后在程序运行期间来动态地设置抽象部分需要用到的具体的实现，还可以动态地切换具体的是吸纳。
 * 2.如果希望实现部分的修改不会对客户产生影响，可以采用桥接模式。由于客户是面向抽象的接口在运行，实现部分的修改可以独立于抽象部分，并不会客户产生影响，可以说是对客户是透明的。
 * 3.如果采用集成的实现方案，会导致产生很多子类，对于这种情况，可以考虑采用桥接模式，分析功能变换的原因。
 * 
 * **个人理解:人听音乐,手机玩游戏,  其中每个事件都可以看做是多种实现的, 大人,小孩, 网游,单机游戏.
 * @author liurenyong 2013-12-24
 */
public interface Implementor {
    
    /** 
     * 实现抽象部分需要的某些具体功能。
     */
    public void operationImpl();

}
