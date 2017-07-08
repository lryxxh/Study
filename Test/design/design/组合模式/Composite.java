/**
 * Composite.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.���ģʽ;

import java.util.ArrayList;
import java.util.List;

/**
 * <����>��������ϳ����νṹ�Ա�ʾ������-���塱�Ĳ�νṹ�����ģʽʹ���û��Ե����������϶����ʹ�þ���һ���ԡ�
 * <����>ͳһҶ�Ӷ������϶���
 * <�ŵ�>
 * 1.�����˰��������������϶�������νṹ������������Ա���ϳɸ��ӵ���϶��󣬶���϶����ֿ�����ϳɸ����ӵ���϶��󣬿��Բ��ϵصݹ����С����
 * 2.ͳһ����϶����Ҷ�Ӷ��������ģʽ�У����԰�Ҷ�Ӷ������������϶��󿴴���Ϊ���Ƕ���ͳһ�ĸ��࣬�Ӷ�����϶����Ҷ�Ӷ������Ϊͳһ������
 * 3.���˿ͻ��˵��ã����ģʽͨ��ͳһ��϶����Ҷ�Ӷ���ʹ�ÿͻ�����ʹ�����ǵ�ʱ�򣬲���Ҫ���������ǣ��ͻ�������ʹ�õĵ�����ʲô���͵Ķ���
 * 4.��������չ�����ڿͻ���ͳһ�����Component����������ˣ��¶����Composite��Leaf�����ܹ������׵������еĽṹһ���������ͻ��˲���ҪΪ����µ��������ı䡣
 * <ȱ��>
 * 1.������������е�������͡�
 * <ʹ�ó���>
 * 1.��������ʾ����Ĳ���-�����νṹ������ѡ�����ģʽ��������Ͳ��ֵĲ���ͳһ������ʹ�ò�νṹʵ�ָ��򵥣����ⲿ��ʹ�������νṹҲ���ס�
 * 2.�����ϣ��ͳһ��ʹ����Ͻṹ�е����ж��󣬿���ѡ�����ģʽ�����������ģʽ�ṩ����Ҫ���ܡ�
 * @author liurenyong 2013-12-20
 */
//��϶���ͨ����Ҫ�洢�Ӷ��󣬶������Ӳ����Ĳ�����Ϊ
public class Composite extends Component {
    
    /** �����洢��϶����а�������������� */
    private List<Component> childComponents = null;
    

    /* (non-Javadoc)
     * @see design.���ģʽ.Component#someOperation()
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
     * @see design.���ģʽ.Component#addChild(design.���ģʽ.Component)
     */
    @Override
    public void addChild(Component child) {
        if (childComponents == null) {
            childComponents = new ArrayList<Component>();
        }
        childComponents.add(child);
    }
    
    /* (non-Javadoc)
     * @see design.���ģʽ.Component#removeChild(design.���ģʽ.Component)
     */
    @Override
    public void removeChild(Component child) {
        if (childComponents != null) {
            childComponents.remove(child);
        }
    }
    
    /* (non-Javadoc)
     * @see design.���ģʽ.Component#getChildren(int)
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
