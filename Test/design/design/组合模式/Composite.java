/**
 * Composite.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.组合模式;

import java.util.ArrayList;
import java.util.List;

/**
 * <定义>将对象组合成树形结构以表示“部分-整体”的层次结构。组合模式使得用户对单个对象和组合对象的使用具有一致性。
 * <本质>统一叶子对象和组合对象。
 * <优点>
 * 1.定义了包含基本对象和组合对象的类层次结构：基本对象可以被组合成复杂的组合对象，而组合对象又可以组合成更复杂的组合对象，可以不断地递归组合小区。
 * 2.统一了组合对象和叶子对象：在组合模式中，可以把叶子对象当做特殊的组合对象看待，为他们定义统一的父类，从而把组合对象和叶子对象的行为统一起来。
 * 3.简化了客户端调用：组合模式通过统一组合对象和叶子对象，使得客户端在使用它们的时候，不需要再区分它们，客户不关心使用的到底是什么类型的对象。
 * 4.更容易扩展：由于客户端统一地面对Component来操作，因此，新定义的Composite或Leaf子类能够很容易地与已有的结构一起工作，而客户端不需要为添加新的组件类而改变。
 * <缺点>
 * 1.很难限制组合中的组件类型。
 * <使用场景>
 * 1.如果你想表示对象的部分-整体层次结构，可以选用组合模式，把整体和部分的操作统一起来，使得层次结构实现更简单，从外部来使用这个层次结构也容易。
 * 2.如果你希望统一地使用组合结构中的所有对象，可以选用组合模式，这正是组合模式提供的主要功能。
 * @author liurenyong 2013-12-20
 */
//组合对象，通常需要存储子对象，定义有子部件的部件行为
public class Composite extends Component {
    
    /** 用来存储组合对象中包含的子组件对象 */
    private List<Component> childComponents = null;
    

    /* (non-Javadoc)
     * @see design.组合模式.Component#someOperation()
     */
    @Override
    public void someOperation() {
        if (childComponents != null) {
            for (Component child : childComponents) {
                child.someOperation();
            }
        }
    }
    
    /* (non-Javadoc)
     * @see design.组合模式.Component#addChild(design.组合模式.Component)
     */
    @Override
    public void addChild(Component child) {
        if (childComponents == null) {
            childComponents = new ArrayList<Component>();
        }
        childComponents.add(child);
    }
    
    /* (non-Javadoc)
     * @see design.组合模式.Component#removeChild(design.组合模式.Component)
     */
    @Override
    public void removeChild(Component child) {
        if (childComponents != null) {
            childComponents.remove(child);
        }
    }
    
    /* (non-Javadoc)
     * @see design.组合模式.Component#getChildren(int)
     */
    @Override
    public Component getChildren(int index) {
        if (childComponents != null) {
            if (index >= 0 && index < childComponents.size()) {
                return childComponents.get(index);
            }
        }
        return null;
    }

}
