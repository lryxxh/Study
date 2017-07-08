/**
 * Component.java
 * Created by liurenyong at 2013-12-24
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.组合模式;

/**
 * 抽象的组件对象，为组合中的对象提供声明接口，实现接口的缺省行为。
 * @author liurenyong 2013-12-24
 */
public abstract class Component {

    /** 
     * 示意方法，子组件对象可能有的功能方法。
     */
    public abstract void someOperation();
    
    /** 
     * 向组合对象中加入组件对象。
     * @param child
     */
    public void addChild(Component child) {
        throw new UnsupportedOperationException();
    }
    
    /** 
     * 从组合对象中移除组件对象。
     * @param child
     */
    public void removeChild(Component child) {
        throw new UnsupportedOperationException();
    }
    
    /** 
     * 返回某个索引对应的组件对象。
     * @param index
     * @return
     */
    public Component getChildren(int index) {
        throw new UnsupportedOperationException();
    }
}
