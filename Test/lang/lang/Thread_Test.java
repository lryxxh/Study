/**
 * Thread_Test.java
 * Created by liurenyong at 2013-8-21
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.google.protobuf.ByteString;
import com.kedong.hmi.service.DataClient;
import com.kedong.hmi.service.common.exception.DataFaultException;
import com.kedong.hmi.service.common.exception.LocatorException;
import com.kedong.hmi.service.common.exception.ProxyException;
import com.kedong.hmi.service.common.exception.ServiceConnectionException;

/**
 * 
 * @author liurenyong 2013-8-21
 */
public class Thread_Test {

    public static void main(String[] args) {
        JFrame frame= new JFrame();
        frame.setVisible(true);
        frame.setSize(400,300);
        JButton button = new JButton("kkkkkk");
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Thread thread = new Thread() {
                    /*
                     * (non-Javadoc)
                     * 
                     * @see java.lang.Thread#run()
                     */
                    @Override
                    public void run() {
                        Map data = null;
                        try {
                            ArrayList tagIDs = new ArrayList();
                            tagIDs.add(289919226011984591L);
//                            data = DataClient.RTDBRead("local,null,realtime,public,priv,null", "perm_mmiuser");
                            data =   DataClient.queryOriginalDataByTime("local,null,realtime,scada,psdb,null", Long.valueOf("1371795680000"), Long.valueOf("1377066084000"), 0,
                                    ByteString.copyFromUtf8("TIME_SCOPE_INTER"), tagIDs);
                        } catch (DataFaultException e) {
                            e.printStackTrace();
                        } catch (ServiceConnectionException e) {
                            e.printStackTrace();
                        } catch (LocatorException e) {
                            e.printStackTrace();
                        } catch (ProxyException e) {
                            e.printStackTrace();
                        }
                        System.out.println(data);
                    }
                };
                OodbProgressBar.show(null, thread);
            }
        });
        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       
    }

}

class OodbProgressBar implements ActionListener {

    private static final String DEFAULT_STATUS = "Please Waiting";

    private JDialog dialog;

    private JProgressBar progressBar;

    private JLabel lbStatus;

    private JButton btnCancel;

    private Window parent;

    private Thread thread;
    private String statusInfo;

    public static void show(Window parent, Thread thread) {
        new OodbProgressBar(parent, thread, DEFAULT_STATUS, null, null);
    }

    public static void show(Window parent, Thread thread, String statusInfo) {
        new OodbProgressBar(parent, thread, statusInfo, null, null);
    }

    public static void show(Window parent, Thread thread, String statusInfo, String resultInfo, String cancelInfo) {
        new OodbProgressBar(parent, thread, statusInfo, resultInfo, cancelInfo);
    }

    private OodbProgressBar(Window parent, Thread thread, String statusInfo, String resultInfo, String cancelInfo) {
        this.parent = parent;
        this.thread = thread;
        this.statusInfo = statusInfo;
        initUI();
        startThread();
        dialog.setVisible(true);
    }

    private void initUI() {
        if (parent instanceof Dialog) {
            dialog = new JDialog((Dialog) parent, true);
        } else if (parent instanceof Frame) {
            dialog = new JDialog((Frame) parent, true);
        } else {
            dialog = new JDialog((Frame) null, true);
        }

        final JPanel mainPane = new JPanel(null);
        progressBar = new JProgressBar();
        lbStatus = new JLabel("" + statusInfo);
        btnCancel = new JButton("Cancel");
        progressBar.setIndeterminate(true);
        btnCancel.addActionListener(this);

        mainPane.add(progressBar);
        mainPane.add(lbStatus);
        // mainPane.add(btnCancel);

        dialog.getContentPane().add(mainPane);
        dialog.setUndecorated(true);
        dialog.setResizable(true);
        dialog.setSize(390, 100);
        dialog.setLocationRelativeTo(parent);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        mainPane.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                layout(mainPane.getWidth(), mainPane.getHeight());
            }
        });
    }

    private void startThread() {
        new Thread() {
            public void run() {
                try {
                    thread.start();

                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    dialog.dispose();
                }
            }
        }.start();
    }

    private void layout(int width, int height) {
        progressBar.setBounds(20, 20, 350, 15);
        lbStatus.setBounds(20, 50, 350, 25);
        btnCancel.setBounds(width - 85, height - 31, 75, 21);
    }

    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent e) {
        thread.stop();
    }

}
