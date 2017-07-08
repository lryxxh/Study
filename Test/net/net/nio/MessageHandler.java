package net.nio;

public class MessageHandler {
    public void send(SocketRequest request, SocketResponse response) {
        System.out.println(request.getQueryString());
        // ÏûÏ¢·¢ËÍ
        String key = request.getValue("imei");
        Session session = SessionManager.getSession(key);
        new SocketResponse(session).write(request.getValue("sms"));
    }
}