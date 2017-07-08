/**
 * ConcreteHandlerA.java
 * Created by liurenyong at 2013-12-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.ְ����ģʽ;

/**
 * 
 * @author liurenyong 2013-12-27
 */
public class ConcreteHandlerB extends Handler{

    /* (non-Javadoc)
     * @see design.ְ����ģʽ.Handler#handleRequest()
     */
    @Override
    public void handleRequest() {
        boolean condition = false;
        //�����Լ���ְ��
        if (condition) {
            System.out.println("ConcretehandlerB handle request");
        } else {
            //����������Լ������ְ��Χ,��ô���ж��Ƿ��к�̵�ְ�����.
            if (this.successor != null) {
                this.successor.handleRequest();
            }
        }
    }

}
