/**
 * Context.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.״̬ģʽ;

/**
 * ״̬�����ģ�ͨ����������ͻ�����Ȥ�Ľӿڣ�ͬʱά��һ������Ĵ���ǰ״̬��ʵ������
 * @author liurenyong 2013-12-20
 */
public class Context {

    /** ״̬ */
    private State state = null;
    
    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }
    
    public void request(String type) {
        state.handle(type);
    }

}
