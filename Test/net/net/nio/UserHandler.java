package net.nio;

public class UserHandler {

    public void login(SocketRequest request, SocketResponse response) {
        System.out.println(request.getQueryString());
        // TODO: �����û���¼
        response.write("��϶��յ���Ϣ��");
    }
}