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
    Label xsck = new Label("��Ϣ��ʾ���ڣ�                                                                   ");
    Label fsck = new Label("��Ϣ���ʹ��ڣ�                                                                   ");
    TextArea taw = new TextArea("", 8, 40);
    TextArea msg = new TextArea("", 5, 34);
    Button bt = new Button("����");
    DatagramSocket socket = null;
    DatagramPacket packet, packetsent;
    byte[] buffer = new byte[256];
    InetAddress iadd;
    int port;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss"); // ��ʽ��ʱ��

    UDPServerFrame() {
        setLayout(new FlowLayout());
        this.setTitle("UDP��������--�����");
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
            // taw.append("�пͻ����ӣ�" + iadd + "\n");
            // byte [] mess1 = "���ӳɹ���".getBytes();
            // packetsent = new DatagramPacket(mess1,mess1.length,iadd,port);
            // socket.send(packetsent);
            while (true) {
                socket.receive(packet);
                iadd = packet.getAddress();
                port = packet.getPort();
                String message = new String(packet.getData(), 0, packet.getLength());
                if (message.equals("��������")) {
                    taw.append("�пͻ����ӣ�" + iadd + "\n");
                    byte[] mess1 = "���ӳɹ���".getBytes();
                    packetsent = new DatagramPacket(mess1, mess1.length, iadd, port);
                    socket.send(packetsent);
                } else if (message.equals("�Ͽ�����")) {
                    taw.append("�ͻ��뿪��" + "\n");
                } else {
                    taw.append("�ͻ��ˣ�" + sdf.format(new Date()) + "\n" + "  " + message + "\n");
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace(); // ���������Ϣ
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
                    taw.append("����ˣ�" + sdf.format(new Date()) + "\n" + "  " + message + "\n");
                    msg.setText("");
                } catch (Exception e2) {
                    e2.printStackTrace(); // ���������Ϣ
                }
            } else {
                new JOptionPane().showMessageDialog(null, "��Ϣ����Ϊ�գ�");
            }
            msg.requestFocus();
        }
    }
}