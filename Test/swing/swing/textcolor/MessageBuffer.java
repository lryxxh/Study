/**
 * MessageBuffer.java
 * Created by liurenyong at 2013-12-25
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing.textcolor;

import java.util.LinkedList;

/**
 * 
 * @author liurenyong 2013-12-25
 */
public class MessageBuffer {
    
    private LinkedList<String> queue = new LinkedList<String>();
    
    public void addMessage(String message) {
        synchronized (queue) {
            queue.add(message);
            queue.notify();
        }
    }
    
    public String getFirstMessage() {
        synchronized (queue) {
            String message = "";
            if (queue.size() == 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            message = queue.poll();
            return message;
        }
    }
    
    public synchronized void removeMessage(int index) {
        if (queue.size() == 0) {
            try {
                MessageBuffer.this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.remove(index);
    }
    
    public synchronized void removeMessage(int begin, int end) {
        for (int i = begin; i < end; i++ ) {
            queue.remove(i);
        }
    }
    
    public synchronized void removeFirstMessage() {
        queue.removeFirst();
    }
    
    public int getMessageSize() {
        return queue.size();
    }
    
    private MessageBuffer(){}
    
    public static MessageBuffer getInstance() {
        return MessageBufferHolder.instance;
    }
    
    private static class MessageBufferHolder {
        private static MessageBuffer instance = new MessageBuffer();
    }
    
    public static void main(String[] args) {
        new Thread() {
            /* (non-Javadoc)
             * @see java.lang.Thread#run()
             */
            @Override
            public void run() {
                for(;;) {
                    MessageBuffer.getInstance().getFirstMessage();
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
//        try {
//            Thread.sleep(50000);
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }
        new Thread() {
            public void run() {
                for (;;) {
                    MessageBuffer.getInstance().addMessage(Math.random() * 1000 + "");
                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }

}
