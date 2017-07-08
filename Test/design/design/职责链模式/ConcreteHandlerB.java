/**
 * ConcreteHandlerA.java
 * Created by liurenyong at 2013-12-27
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.职责链模式;

/**
 * 
 * @author liurenyong 2013-12-27
 */
public class ConcreteHandlerB extends Handler{

    /* (non-Javadoc)
     * @see design.职责链模式.Handler#handleRequest()
     */
    @Override
    public void handleRequest() {
        boolean condition = false;
        //属于自己的职责
        if (condition) {
            System.out.println("ConcretehandlerB handle request");
        } else {
            //如果不属于自己处理的职责范围,那么就判断是否还有后继的职责对象.
            if (this.successor != null) {
                this.successor.handleRequest();
            }
        }
    }

}
