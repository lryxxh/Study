/**
 * Caretaker.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.备忘录模式;

/**
 * 负责保存备忘录的对象
 * @author liurenyong 2013-12-20
 */
public class Caretaker {
    
    /** 记录被保存的备忘录对象 */
    private Memento memento = null;
    
    /** 
     * 保存备忘录对象
     * @param memento
     */
    public void saveMemento(Memento memento) {
        this.memento = memento;
    }
    
    /** 
     * 获取被保存的备忘录对象。
     * @return
     */
    public Memento retriveMemento() {
        return this.memento;
    }

}
