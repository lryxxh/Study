package net.nio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SessionProcessor implements Runnable {

    private static Runnable processor = new SessionProcessor();
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 200, 500, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void start() {
        new Thread(processor).start();
    }

    @Override
    public void run() {
        while (true) {
            Session tmp = null;
            for (String key : SessionManager.getSessionKeys()) {
                tmp = SessionManager.getSession(key);
                // 处理Session未处理的请求
                if (tmp.getReceiveData() != null) {
                    pool.execute(new Process(tmp));
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Log.e(e);
            }
        }
    }

    class Process implements Runnable {

        private SocketRequest request;
        private SocketResponse response;

        public Process(Session session) {
            // 将Session封装成Request和Response
            request = new SocketRequest(session);
            response = new SocketResponse(session);
        }

        @Override
        public void run() {
            new RequestTransform().transfer(request, response);
        }
    }

}