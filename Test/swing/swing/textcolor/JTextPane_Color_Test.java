/**
 * JTextPane_Color_Test.java
 * Created by liurenyong at 2013-12-26
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package swing.textcolor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 * @author liurenyong 2013-12-26
 */
public class JTextPane_Color_Test extends JFrame {
    Socket socket = null;

    public JTextPane_Color_Test() {
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    /** 
     * 
     */
    private void init() {
        JScrollPane scrollPane = new JScrollPane();
        final JMessagePane messagePane = new JMessagePane();
        messagePane.setFocusable(false);
        scrollPane.setViewportView(messagePane);
        this.getContentPane().add(scrollPane);
        JPanel panel = new JPanel();
        JButton startButton = new JButton("开始");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    socket = new Socket("192.168.200.211", 6788);
                    OutputStream os = socket.getOutputStream();
                    os.write(new byte[] { -122, -66 });
                    os.write(23);
                    os.write(0);
                    os.write(8);
                    os.write(35);
                    os.write(110);
                    os.write(2);
                    os.write(1);
                    os.write(0);
                    os.write(1);
                    os.write(0);
                    os.write(129);
                    os.write(0);
                    os.write(20);
                    os.write(14);
                    os.write(12);
                    os.write(10);
                    os.write(9);
                    os.write(1);
                    os.write(31);
                    os.write(20);
                    os.write(14);
                    os.write(12);
                    os.write(10);
                    os.write(9);
                    os.write(57);
                    os.write(32);
                    os.write(0x61);
                    os.flush();
                    new ReceiveMessageThread().start();
                    new Thread(messagePane).start();
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        panel.add(startButton);
        getContentPane().add(panel, BorderLayout.SOUTH);
    }

    private class ReceiveMessageThread extends Thread {
        public void run() {
            int realLen, funcNum, number;
            int startW1, startW2;
            int lengthL, lengthH;
            boolean linkOk = false;
            InputStream inputStream;
            String dataString = "";
            while (socket != null && socket.isConnected()) {
                try {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    inputStream = socket.getInputStream();
                    byte[] linkNo = new byte[2];
                    int n = 1;
                    linkOk = false;
                    startW1 = inputStream.read();
                    if (startW1 > 0) {
                        linkOk = true;
                    }
                    startW2 = inputStream.read();
                    if (startW1 == (int) 0x86 && startW2 == (int) 0xBE) {
                        n = 1;
                        lengthL = inputStream.read();
                        lengthH = inputStream.read();
                        funcNum = inputStream.read();
                        switch (funcNum) {
                        case 26:// 未找到需求历史报文
                            this.isInterrupted();
                        case 20:// 原始报文、不解析
                            number = inputStream.read();// 序号
                            realLen = inputStream.read(linkNo);// 链路号低字节、高字节
                            lengthL -= 3;
                            break;
                        case 21:// 解析报文
                            number = inputStream.read();// 序号
                            realLen = inputStream.read(linkNo);// 链路号低字节、高字节
                            lengthL -= 3;
                            break;
                        case 24:// 测试帧，当链路正常但超过3s没有发报文时发送24
                            System.err.println("测试帧"); //$NON-NLS-1$
                            number = inputStream.read();// 序号
                            realLen = inputStream.read(linkNo);// 链路号低字节、高字节
                            lengthL -= 3;
                            break;
                        case 25:// 结束报文
                        default:
                        }
                        byte[] datas = null;
                        datas = new byte[lengthH * 256 + lengthL];
                        realLen = inputStream.read(datas);
                        dataString = new String(datas);
                        MessageBuffer.getInstance().addMessage(dataString);
                        if (inputStream.read() == (int) 0x61) {// 结束符
//                            return;
                        }
                    } else if (startW1 < 0) {
                        return;
                    }
//                    System.out.println(dataString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }
    
    public static void main(String[] args) {
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new JTextPane_Color_Test();
    }

}
