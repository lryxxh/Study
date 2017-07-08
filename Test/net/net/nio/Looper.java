package net.nio;

public class Looper extends Thread {
    private static Looper looper = new Looper();
    private static boolean isStart = false;
    private final int INTERVAL = 1000 * 60 * 5;

    private Looper() {
    }

    public static Looper getInstance() {
        return looper;
    }

    public void loop() {
        if (!isStart) {
            isStart = true;
            this.start();
        }
    }

    public void run() {
        Task task = new Task();
        while (true) {
            // Session过期检测
            task.checkState();
            // 心跳包检测
            // task.sendAck();
            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                Log.e(e);
            }
        }
    }
}