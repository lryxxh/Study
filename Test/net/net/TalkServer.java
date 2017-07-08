package net;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class TalkServer {
    public static void main(String[] args) {
        UDPServerFrame sf = new UDPServerFrame();
    }
}

class UDPServerFrame extends Frame implements ActionListener {
    Label xsck = new Label("消息显示窗口：                                                                   ");
    Label fsck = new Label("消息发送窗口：                                                                   ");
    TextArea taw = new TextArea("", 8, 40);
    TextArea msg = new TextArea("", 5, 34);
    Button bt = new Button("发送");
    DatagramSocket socket = null;
    DatagramPacket packet, packetsent;
    byte[] buffer = new byte[256];
    InetAddress iadd;
    int port;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss"); // 格式化时间

    UDPServerFrame() {
        setLayout(new FlowLayout());
        this.setTitle("UDP在线聊天--服务端");
        this.add(xsck);
        taw.setEditable(false);
        this.add(taw);
        this.add(fsck);
        this.add(msg);
        this.add(bt);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                (e.getWindow()).dispose();
                System.exit(0);
            }
        });
        bt.addActionListener(this);
        setSize(318, 350);
        setVisible(true);
        this.setResizable(false);
        msg.requestFocus();
        try {
            socket = new DatagramSocket(3333);
            packet = new DatagramPacket(buffer, buffer.length);
            // socket.receive(packet);
            // iadd = packet.getAddress();
            // port = packet.getPort();
            // taw.append("有客户连接：" + iadd + "\n");
            // byte [] mess1 = "连接成功！".getBytes();
            // packetsent = new DatagramPacket(mess1,mess1.length,iadd,port);
            // socket.send(packetsent);
            while (true) {
                socket.receive(packet);
                iadd = packet.getAddress();
                port = packet.getPort();
                String message = new String(packet.getData(), 0, packet.getLength());
                if (message.equals("请求连接")) {
                    taw.append("有客户连接：" + iadd + "\n");
                    byte[] mess1 = "连接成功！".getBytes();
                    packetsent = new DatagramPacket(mess1, mess1.length, iadd, port);
                    socket.send(packetsent);
                } else if (message.equals("断开连接")) {
                    taw.append("客户离开！" + "\n");
                } else {
                    taw.append("客户端：" + sdf.format(new Date()) + "\n" + "  " + message + "\n");
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace(); // 输出出错信息
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt) {
            String message = msg.getText();
            byte[] mess = message.getBytes();
            if (!message.equals("")) {
                try {
                    packetsent = new DatagramPacket(mess, mess.length, iadd, port);
                    socket.send(packetsent);
                    taw.append("服务端：" + sdf.format(new Date()) + "\n" + "  " + message + "\n");
                    msg.setText("");
                } catch (Exception e2) {
                    e2.printStackTrace(); // 输出出错信息
                }
            } else {
                new JOptionPane().showMessageDialog(null, "消息不能为空！");
            }
            msg.requestFocus();
        }
    }
}