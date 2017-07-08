/**
 * JMessagePane.java
 * Created by liurenyong at 2013-12-25
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing.textcolor;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * 
 * @author liurenyong 2013-12-25
 */
public class JMessagePane extends JTextPane implements Runnable{
    
    private String sendStr = "SEND";// 发送关键字
    private String recvStr = "RECV";// 接收关键字
    
    private Document document = null;
    
    private StyleContext styleContext = StyleContext.getDefaultStyleContext();
    
    private MutableAttributeSet attributeSet = new SimpleAttributeSet();
    
    public JMessagePane() {
        //启动刷新线程
        new Thread(this).start();
    }
    
    private Document getCurrentDocuemnt() {
        return super.getDocument();
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        String message = null;
        while((message = MessageBuffer.getInstance().getFirstMessage()) != null) {
            int sendIndex = message.indexOf(sendStr);
            int recvIndex = message.indexOf(recvStr);
            boolean showed = false;
            if (sendIndex >= 0) {
                showed = true;
                addMessageToMessagePane(message, sendIndex);
            }
            
            if (recvIndex >= 0) {
                showed = true;
                addMessageToMessagePane(message, recvIndex);
            }
            
            if(!showed) {
                addMessage(Color.DARK_GRAY, message);
            }
        }
        
    }
    
    /** 
     * 将message添加到消息中。
     * @param message
     * @param index
     */
    private void addMessageToMessagePane(String message, int index) {
        addMessage(Color.GREEN, message.substring(0, index));
        addMessage(Color.DARK_GRAY, sendStr);
        addMessage(Color.BLUE, message.substring(4 + index));
    }
    
    
    /** 
     * @param message
     */
    private void addMessage(Color color, String message) {
        synchronized (getDocument()) {
            adjustElement();
            StyleConstants.setForeground(attributeSet, color);  
            int len = getDocument().getLength();
            System.err.println("..................." + len);
            setCaretPosition(len);
    
            setCharacterAttributes(attributeSet, false);
            replaceSelection(message);
        }
    }

    /** 
     * 
     */
    private void adjustElement() {
       
            Element rootElement = getDocument().getDefaultRootElement();
            int count = rootElement.getElementCount();
            while (count > 1000) {
                try {
                    getDocument().remove(rootElement.getElement(0).getStartOffset(), rootElement.getElement(count - 1000).getEndOffset());
                    count = rootElement.getElementCount();
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
       
    }


}
