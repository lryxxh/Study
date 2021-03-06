/**
 * Commadn.java
 * Created by liurenyong at 2013-12-26
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.命令模式;

/**
 * <定义>将一个请求封装为一个对象,从而是你可用不同的请求对客户进行参数化,对请求排队或记录请求日志,以及支持可撤销的操作.
 * <本质>封装请求
 * <优点>
 * 1.更松散的耦合:命令模式使得发起命令的对象--客户端,和具体实现命令的对象--接收者对象完全解耦.
 * 2.更动态的控制:命令模式把请求封装起来,可以动态地对它进行参数化,队列话,和日志化等操作.
 * 3.很自然的符合命令:命令模式中 的命令对象能够很容易地组合成复合命令.
 * 4.更好的扩展性:由于发起命令的对象和具体的实现完全解耦,因此扩展新的命令就很容易,只需要实现新的命令对象,然后在装配的时候,把具体的实现对象设置到命令对象中,然后就可以使用这个命令对象,已有的实现完全不用变化.
 * 5.允许接收请求的一方决定是哦福要否决请求.
 * <缺点>
 * <使用场景>
 * 1.如果需要抽象出需要执行的动作,并参数化这些对象,可以选中命令模式. 将这些需要执行的动作抽象成命令,然后实现命令的参数化配置.
 * 2.如果需要在不同的时候执行,排列和执行请求,可以选中命令模式. 将这些请求封装成命令对象,然后实现将请求队列化.
 * 3.如果需要支持取消操作,可以选用命令模式,通过管理命令对象,能很容易地实现命令的恢复和重做功能.
 * 4.如果需要支持当系统崩溃时,能将系统的操作功能重新执行一遍,可以选用命令模式.将这些操作功能呢的请求封装成命令对象,然后实现日志命令,就可以在系统恢复以后,通过日志获取命令模式列表,从而重新执行一遍功能.
 * 5.在需要事物的系统中,可以选用命令模式,命令模式提供了对事物进行建模的方法.
 * 
 * @author liurenyong 2013-12-26
 */
//命令接口
public interface Command {
    
    /** 
     * 执行命令对应的操作.
     */
    public void execute();

}
