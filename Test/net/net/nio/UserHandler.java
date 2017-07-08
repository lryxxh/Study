package net.nio;

public class UserHandler {

    public void login(SocketRequest request, SocketResponse response) {
        System.out.println(request.getQueryString());
        // TODO: 处理用户登录
        response.write("你肯定收到消息了");
    }
}