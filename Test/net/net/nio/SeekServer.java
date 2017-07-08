package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SeekServer extends Thread {
    private final int ACCPET_PORT = 55555;
    private final int TIME_OUT = 1000;
    private Selector mSelector = null;
    private ServerSocketChannel mSocketChannel = null;
    private ServerSocket mServerSocket = null;
    private InetSocketAddress mAddress = null;

    public SeekServer() {
        long sign = System.currentTimeMillis();
        try {
            mSocketChannel = ServerSocketChannel.open();
            if (mSocketChannel == null) {
                System.out.println("can't open server socket channel");
            }
            mServerSocket = mSocketChannel.socket();
            mAddress = new InetSocketAddress(ACCPET_PORT);
            mServerSocket.bind(mAddress);
            Log.i("server bind port is " + ACCPET_PORT);
            mSelector = Selector.open();
            mSocketChannel.configureBlocking(false);
            SelectionKey key = mSocketChannel.register(mSelector, SelectionKey.OP_ACCEPT);
            key.attach(new Acceptor());

            // 检测Session状态
            Looper.getInstance().loop();

            // 开始处理Session
            SessionProcessor.start();

            Log.i("Seek server startup in " + (System.currentTimeMillis() - sign) + "ms!");
        } catch (ClosedChannelException e) {
            Log.e(e.getMessage());
        } catch (IOException e) {
            Log.e(e.getMessage());
        }
    }

    public void run() {
        Log.i("server is listening...");
        while (!Thread.interrupted()) {
            System.out.println(".....in loop");
            try {
                if (mSelector.select(TIME_OUT) > 0) {
                    Set<SelectionKey> keys = mSelector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    SelectionKey key = null;
                    while (iterator.hasNext()) {
                        key = iterator.next();
                        Handler at = (Handler) key.attachment();
                        if (at != null) {
                            at.exec();
                        }
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                Log.e(e.getMessage());
            }
        }
    }

    class Acceptor extends Handler {

        public void exec() {
            try {
                SocketChannel sc = mSocketChannel.accept();
                new Session(sc, mSelector);
            } catch (ClosedChannelException e) {
                Log.e(e);
            } catch (IOException e) {
                Log.e(e);
            }
        }
    }
}