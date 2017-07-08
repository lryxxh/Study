/**
 * Builder.java
 * Created by liurenyong at 2013-12-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.建造者模式;

/**
 * <定义>将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 * <本质>分离整体构建算法和部件构造：生成器模式的中心在于分离整理构建算法和部件构造，而分步骤构建对象不过是整体构建算法的一个简单表现，或者说是一个附带产物
 * <优点>
 * 1.松散耦合：生成器模式可以用同一个构建算法构建出表现上完全不同的产品，实现产品构建和产品表现上的分离。
 * 2.很容易的改变产品的内部表示：生成器模式中，由于Builder对象只是提供了接口给Director使用，那么具体的部件创建和装配方式是被Builder接口隐藏了的，Director并不知道这些具体的实现细节。
 * 3.更好的复用性：生成器模式很好地实现了构建算法和具体产品实现的分离。
 * <缺点>
 * <使用场景>
 * 1.如果创建对象的算法，应该独立于该对象的组成部分以及他们的装配方式时。
 * 2.如果同一个构建过程有不同的表示时。
 * 
 *  @author liurenyong 2013-12-19
 */
public interface Builder {

    /** 
     * 构建部件1.
     */
    public void buildPart1();
    
    /** 
     * 构建部件2.
     */
    public void buildPart2();
    
    /** 
     * 构建部件3.
     */
    public void buildPart3();
}
